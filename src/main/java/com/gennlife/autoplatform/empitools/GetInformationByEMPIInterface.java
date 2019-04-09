package com.gennlife.autoplatform.empitools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.ExcelUtils;
import com.gennlife.autoplatform.utils.ListAndStringUtils;
import com.gennlife.interfaces.PatientDetailsInfoOfEMPIServerInterface;

/**
 * @Description: 通过请求empi接口，pat返回相关的信息
 * @author: wangmiao
 * @Date: 2017年12月16日 下午3:40:33 
 */
public class GetInformationByEMPIInterface {
	private static Logger logger = Logger.getLogger(GetInformationByEMPIInterface.class); 
	
	/** 
	* @Title: getResultsByPostMethod 
	* @Description: 通过读取excel相关列，统一请求接口，传入多个patID，返回一个大json后，解析对应json，填入对应excel的列
	* @param: Excel excel :传入文件
	* @return: String
	* @throws 
	*/
	public static String getResultsByPostMethod(Excel excel) {
		long startTime = System.currentTimeMillis();    
		Integer patCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "编号");
		Integer patiNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "PatiName");
		Integer idCardCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "IDCard");
		Integer inPatientSnCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "InPatientSn");
		List<String> patList = ExcelUtils.readExcelOfList(excel, patCellNum);
		String patStrs = ListAndStringUtils.dealPatListAddDoubleQuotationMarksReturnPatStrs(patList);
		String allJsons = PatientDetailsInfoOfEMPIServerInterface.getResultsByPostMethod(patStrs);
		JSONObject jsonObject=(JSONObject) JSON.parse(allJsons);
		JSONArray resultsArray = jsonObject.getJSONArray("Results");
		if (patList.size()==resultsArray.size()) {
			List<String> idCardList = new ArrayList<String>();
			List<String> inPatientList = new ArrayList<String>();
			List<String> patiNameList = new ArrayList<String>();
			for (int i = 0; i <resultsArray.size(); i++) {
				JSONObject oneJsonObject = (JSONObject) resultsArray.get(i);
		        Map oneMap = (Map) oneJsonObject;
		        if (oneMap.containsKey("IDCard")) {
		        	for (Object map : oneMap.entrySet()){ 
			        	if ("IDCard"==((Map.Entry)map).getKey()) {
			        		idCardList.add(((Map.Entry)map).getValue().toString());
			        		break;
						}
		        	}
				}else if (!oneMap.containsKey("IDCard")) {
					idCardList.add("");
				}
		        if (oneMap.containsKey("InPatientSn")) {
		        	for (Object map : oneMap.entrySet()){ 
			        	if ("InPatientSn"==((Map.Entry)map).getKey()) {
			        		inPatientList.add(((Map.Entry)map).getValue().toString());
			        		break;
						}
		        	}
				}else if (!oneMap.containsKey("InPatientSn")) {
					inPatientList.add("");
				}
		        if (oneMap.containsKey("PatiName")) {
		        	for (Object map : oneMap.entrySet()){ 
			        	if ("PatiName"==((Map.Entry)map).getKey()) {
			        		patiNameList.add(((Map.Entry)map).getValue().toString());
			        		break;
						}
		        	}
				}else if (!oneMap.containsKey("PatiName")) {
					patiNameList.add("");
				}
			}
			if (idCardList.size()==inPatientList.size() && 
					inPatientList.size()==patiNameList.size()) {
				ExcelUtils.writeOneListAndSaveContent(excel, idCardList, idCardCellNum);
				ExcelUtils.writeOneListAndSaveContent(excel, inPatientList, inPatientSnCellNum);
				ExcelUtils.writeOneListAndSaveContent(excel, patiNameList, patiNameCellNum);
			}
		}else {
			logger.info("errors");
		}
		long endTime = System.currentTimeMillis();
		return "完成！程序运行时间：" + (endTime - startTime) + "ms";    
	}
	
	
	/** 
	* @Title: getOneResultByPostMethod 
	* @Description: 通过读取excel相关列，请求接口，一个一个传入1个patID，返回json后，解析对应json，填入对应excel的列
	* @param: @param excel
	* @return: String
	* @throws 
	*/
	public static String getOneResultByPostMethod(Excel excel) {
		long startTime = System.currentTimeMillis();    
		Integer patCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "编号");
		Integer patiNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "PatiName");
		Integer idCardCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "IDCard");
		Integer inPatientSnCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "InPatientSn");
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, patCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			Integer writeContentRowNum=null;
			String patStr=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				patStr=entry.getValue();
				String allString = PatientDetailsInfoOfEMPIServerInterface.getOneResultByPostMethod(patStr);
				String patiNameString=null;
				String idCardString=null;
				String inPatientString=null;
		        JSONObject jsonObject=(JSONObject) JSON.parse(allString);
		        JSONArray jsonArray = jsonObject.getJSONArray("Results");
		        jsonObject = (JSONObject) jsonArray.get(0);
		        //{"Status":0,"IDCard":"370623197112014021","InPatientSn":["1288385"],"PatiName":"栾彩梅"}
		    	//List<String> list1 = new ArrayList<String>();
		        Map IDCard_map = (Map) jsonObject;
		        for (Object map1 : IDCard_map.entrySet()){ 
		        	if ("IDCard"==((Map.Entry)map1).getKey()) {
		         		idCardString=((Map.Entry)map1).getValue().toString();
		        	}
		        	if ("InPatientSn"==((Map.Entry)map1).getKey()) {
		         		inPatientString=((Map.Entry)map1).getValue().toString();
		        	}
		        	if ("PatiName"==((Map.Entry)map1).getKey()) {
		         		patiNameString=((Map.Entry)map1).getValue().toString();
		        	}
		        }
		        ExcelUtils.writeAndSaveContent(excel, idCardString, writeContentRowNum, idCardCellNum);
		        ExcelUtils.writeAndSaveContent(excel, inPatientString, writeContentRowNum, inPatientSnCellNum);
				ExcelUtils.writeAndSaveContent(excel, patiNameString, writeContentRowNum, patiNameCellNum);
			}
		}
		long endTime = System.currentTimeMillis();
		return "完成！程序运行时间：" + (endTime - startTime) + "ms"; 
	}
	
}

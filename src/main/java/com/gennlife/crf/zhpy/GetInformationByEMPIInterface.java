package com.gennlife.crf.zhpy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gennlife.crf.bean.Excel;
import com.gennlife.crf.utils.ExcelUtils;
import com.gennlife.crf.utils.ListAndStringUtils;
import com.gennlife.interfaces.PatientDetailsInfoOfEMPIServerInterface;

/**
 * @Description: 通过请求empi接口，pat返回相关的信息
 * @author: wangmiao
 * @Date: 2017年12月16日 下午3:40:33 
 */
public class GetInformationByEMPIInterface {
	
	/** 
	* @Title: getResultsByPostMethod 
	* @Description: 通过读取excel相关列，统一请求接口，传入多个patID，返回一个大json后，解析对应json，填入对应excel的列
	* @param: Excel excel :传入文件
	* @return: String
	* @throws 
	*/
	public static String getResultsByPostMethod(Excel excel) {
		//获取开始时间
		long startTime = System.currentTimeMillis();    
		
		Integer patCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "编号");
		Integer patiNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "PatiName");
		Integer idCardCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "IDCard");
		Integer inPatientSnCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "InPatientSn");
			
		//获取excel中的patList
		List<String> patList = ExcelUtils.readExcelOfList(excel, patCellNum);
		//System.out.println("patList："+patList.size());
		
		//处理patList
		String patStrs = ListAndStringUtils.dealPatListAddDoubleQuotationMarksReturnPatStrs(patList);
		//返回结果大的json
		//请求接口
		String allJsons = PatientDetailsInfoOfEMPIServerInterface.getResultsByPostMethod(patStrs);
		
		//获取json中Results的数组
		//将returnStr字符串转换成json对象:JSONObject
		JSONObject jsonObject=(JSONObject) JSON.parse(allJsons);
		JSONArray resultsArray = jsonObject.getJSONArray("Results");
		//System.out.println("resultsArray："+resultsArray.size());
		
		//判断patList与返回结果allJsons中resultsArray的大小，相等证明结构正确
		if (patList.size()==resultsArray.size()) {
			//如果相等，则继续提取resultsArray数组，构造三个list
			List<String> idCardList = new ArrayList<String>();
			List<String> inPatientList = new ArrayList<String>();
			List<String> patiNameList = new ArrayList<String>();
			
			for (int i = 0; i <resultsArray.size(); i++) {
				JSONObject oneJsonObject = (JSONObject) resultsArray.get(i);
				//处理oneJsonObject为map,遍历后将对应值添加到对应的三个list中
		        Map oneMap = (Map) oneJsonObject;
		        //判断是否存在某key：IDCard
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
		        
		        //判断是否存在某key：InPatientSn
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
		        
		        //判断是否存在某key：PatiName
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
			/*验证
			System.out.println("idCardList："+idCardList.size());
			System.out.println("inPatientList："+inPatientList.size());
			System.out.println("patiNameList："+patiNameList.size());
			*/
			//三个list相等才写入excel
			if (idCardList.size()==inPatientList.size() && 
					inPatientList.size()==patiNameList.size()) {
				//写入对应excel
				ExcelUtils.writeOneListAndSaveContent(excel, idCardList, idCardCellNum);
				ExcelUtils.writeOneListAndSaveContent(excel, inPatientList, inPatientSnCellNum);
				ExcelUtils.writeOneListAndSaveContent(excel, patiNameList, patiNameCellNum);
			}
		}else {
			System.out.println("errors");
		}
		//System.out.println("OK");
		//获取结束时间
		long endTime = System.currentTimeMillis();
		//返回程序运行时间
		//System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
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
		//获取开始时间
		long startTime = System.currentTimeMillis();    

		Integer patCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "编号");
		Integer patiNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "PatiName");
		Integer idCardCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "IDCard");
		Integer inPatientSnCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "InPatientSn");
		
		//获取中文名称一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, patCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			//定义“联动”列的行号和内容（即：写入值时对应的行号）
			Integer writeContentRowNum=null;
			String patStr=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				//行号
				writeContentRowNum=entry.getKey();
				//pat
				patStr=entry.getValue();
				//System.out.println(writeContentRowNum+"--"+patStr);
				
				//获取pat，并请求接口
				String allString = PatientDetailsInfoOfEMPIServerInterface.getOneResultByPostMethod(patStr);
				//System.out.println("返回jsonStr："+allString);
				
				//返回String的json，并解析json，获取对应三个数值
				String patiNameString=null;
				String idCardString=null;
				String inPatientString=null;
				
				//将returnStr字符串转换成json对象:JSONObject
		        JSONObject jsonObject=(JSONObject) JSON.parse(allString);
		        JSONArray jsonArray = jsonObject.getJSONArray("Results");
		        //System.out.println(jsonArray);
		        
		        //返回Results的JSONObject
		        jsonObject = (JSONObject) jsonArray.get(0);
		        //System.out.println(jsonObject);
		        //{"Status":0,"IDCard":"370623197112014021","InPatientSn":["1288385"],"PatiName":"栾彩梅"}
		        
		    	//List<String> list1 = new ArrayList<String>();
		    	//处理jsonObject
		        Map IDCard_map = (Map) jsonObject;
		        for (Object map1 : IDCard_map.entrySet()){ 
		        	if ("IDCard"==((Map.Entry)map1).getKey()) {
		        		//list1.add(((Map.Entry)map1).getKey()+":"+((Map.Entry)map1).getValue());
		         		//System.out.println(((Map.Entry)map1).getKey()+":"+((Map.Entry)map1).getValue());
		         		idCardString=((Map.Entry)map1).getValue().toString();
		        	}
		        	
		        	if ("InPatientSn"==((Map.Entry)map1).getKey()) {
		        		//list1.add(((Map.Entry)map1).getKey()+":"+((Map.Entry)map1).getValue());
		         		//System.out.println(((Map.Entry)map1).getKey()+":"+((Map.Entry)map1).getValue());
		         		inPatientString=((Map.Entry)map1).getValue().toString();
		        	}
		        	
		        	if ("PatiName"==((Map.Entry)map1).getKey()) {
		        		//list1.add(((Map.Entry)map1).getKey()+":"+((Map.Entry)map1).getValue());
		         		//System.out.println(((Map.Entry)map1).getKey()+":"+((Map.Entry)map1).getValue());
		         		patiNameString=((Map.Entry)map1).getValue().toString();
		        	}
		        }
		        
				//写入对应excel
		        ExcelUtils.writeAndSaveContent(excel, idCardString, writeContentRowNum, idCardCellNum);
		        ExcelUtils.writeAndSaveContent(excel, inPatientString, writeContentRowNum, inPatientSnCellNum);
				ExcelUtils.writeAndSaveContent(excel, patiNameString, writeContentRowNum, patiNameCellNum);
			}
		}
		//System.out.println("OK");
		//获取结束时间
		long endTime = System.currentTimeMillis();
		
		//返回程序运行时间
		return "完成！程序运行时间：" + (endTime - startTime) + "ms"; 
	}
	
}

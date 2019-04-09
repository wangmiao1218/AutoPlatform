package com.gennlife.crf.tianjin.crfPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gennlife.crf.bean.Excel;
import com.gennlife.crf.utils.ExcelUtils;
import com.gennlife.crf.utils.ListAndStringUtils;

/**
 * @Description: 
 * @author: wangmiao
 * @Date: 2017年12月16日 下午3:40:33 
 */
public class GetInformation {
	
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
		/*
		//请求接口
		String allJsons = PatientDetailsInfoOfInterfaceTools.getResultsByPostMethod(patStrs);
		
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
		
		*/
		//获取结束时间
		long endTime = System.currentTimeMillis();
		//返回程序运行时间
		//System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
		return "完成！程序运行时间：" + (endTime - startTime) + "ms";    
	}
	
}

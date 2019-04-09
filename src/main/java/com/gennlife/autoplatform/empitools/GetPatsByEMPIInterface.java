package com.gennlife.autoplatform.empitools;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.ExcelUtils;
import com.gennlife.autoplatform.utils.ListAndStringUtils;
import com.gennlife.interfaces.GetUuidOfEMPIServerInterface;

/**
 * @Description: 通过请求empi接口，pat返回相关的信息
 * @author: wangmiao
 * @Date: 2017年12月16日 下午3:40:33 
 */
public class GetPatsByEMPIInterface {
	private static Logger logger = Logger.getLogger(GetPatsByEMPIInterface.class); 
	
	/**
	 * @Title: getPatsByPostMethod_bak (请求100多个就不生成pat)
	 * @Description: 通过读取excel相关列，统一请求接口，传入多个ID，返回一个大json后，解析对应json，填入对应excel的列
	 * @param: Excel excel :传入文件
	 * @return: String
	 * @throws 
	 */
	public static String getPatsByPostMethod(Excel excel){
		long startTime = System.currentTimeMillis();    
		Integer oldPatCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "原始患者编号");
		Integer patCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "pat患者编号");
		List<String> oldPatList = ExcelUtils.readExcelOfList(excel, oldPatCellNum);
		logger.info("oldPatList："+oldPatList.size());
		String patStrs = ListAndStringUtils.dealOldPatListAddDoubleQuotationMarksReturnOldPatStrs(oldPatList);
		logger.info(patStrs);
		String allJsons = GetUuidOfEMPIServerInterface.getPatsByPostMethod(patStrs);
		logger.info(allJsons);
		JSONObject jsonObject=(JSONObject) JSON.parse(allJsons);
		JSONArray resultsArray = jsonObject.getJSONArray("Results");
		logger.info("resultsArray："+resultsArray.size());
		//判断oldPatList与返回结果allJsons中resultsArray的大小，相等证明结构正确
		if (oldPatList.size()==resultsArray.size()) {
			List<String> patList = new ArrayList<String>();
			for (int i = 0; i <resultsArray.size(); i++) {
				JSONObject oneJsonObject = (JSONObject) resultsArray.get(i);
				if (oneJsonObject.get("Uuid")==null) {
					patList.add("");
				}else {
					String pat = oneJsonObject.get("Uuid").toString();
					patList.add(pat);
				}
			}
			if (oldPatList.size()==patList.size()) {
				ExcelUtils.writeOneListAndSaveContent(excel, patList, patCellNum);
			}
		}else {
			logger.info("errors");
		}
		long endTime = System.currentTimeMillis();
		logger.info("程序运行时间：" + (endTime - startTime) + "ms");
		return "完成！程序运行时间：" + (endTime - startTime) + "ms";    
	}
}

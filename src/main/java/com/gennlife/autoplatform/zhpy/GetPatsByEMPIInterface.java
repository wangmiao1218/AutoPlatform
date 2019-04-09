package com.gennlife.autoplatform.zhpy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * @Title: getPatsByPostMethod_bak (请求100多个就不生成pat)
	 * @Description: 通过读取excel相关列，统一请求接口，传入多个ID，返回一个大json后，解析对应json，填入对应excel的列
	 * @param: Excel excel :传入文件
	 * @return: String
	 * @throws 
	 */
	public static String getPatsByPostMethod(Excel excel){
		//获取开始时间
		long startTime = System.currentTimeMillis();    
		
		Integer oldPatCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "原始患者编号");
		Integer patCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "pat患者编号");
		
		//获取excel中的patList
		List<String> oldPatList = ExcelUtils.readExcelOfList(excel, oldPatCellNum);
		System.out.println("oldPatList："+oldPatList.size());
		
		//处理oldPatList
		String patStrs = ListAndStringUtils.dealOldPatListAddDoubleQuotationMarksReturnOldPatStrs(oldPatList);
		System.out.println(patStrs);
		
		//返回结果大的json
		//请求接口
		String allJsons = GetUuidOfEMPIServerInterface.getPatsByPostMethod(patStrs);
		System.out.println(allJsons);
		
		//获取json中Results的数组
		//将returnStr字符串转换成json对象:JSONObject
		JSONObject jsonObject=(JSONObject) JSON.parse(allJsons);
		JSONArray resultsArray = jsonObject.getJSONArray("Results");
		System.out.println("resultsArray："+resultsArray.size());
		
		//判断oldPatList与返回结果allJsons中resultsArray的大小，相等证明结构正确
		if (oldPatList.size()==resultsArray.size()) {
			//如果相等，则继续提取resultsArray数组，构造三个list
			List<String> patList = new ArrayList<String>();
			
			for (int i = 0; i <resultsArray.size(); i++) {
				JSONObject oneJsonObject = (JSONObject) resultsArray.get(i);
				//若不存在则加空
				if (oneJsonObject.get("Uuid")==null) {
					patList.add("");
				}else {
					String pat = oneJsonObject.get("Uuid").toString();
					patList.add(pat);
				}
			}
			
			//三个list相等才写入excel
			if (oldPatList.size()==patList.size()) {
				//写入对应excel
				ExcelUtils.writeOneListAndSaveContent(excel, patList, patCellNum);
			}
		}else {
			System.out.println("errors");
		}
		//获取结束时间
		long endTime = System.currentTimeMillis();
		//返回程序运行时间
		//System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
		return "完成！程序运行时间：" + (endTime - startTime) + "ms";    
	}
	
	
}

package com.gennlife.pmtools;

import java.util.List;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.ExcelUtils;


/**
 * @Description: 
 * @author: wangmiao
 * @Date: 2017年8月17日 下午5:44:20 
 */
public class EnglishToEnglish{

	public static String testYujie(Excel excelrx,Integer beginCellrx,Excel exceldc,Integer beginCelldc){
		List<String> list = ExcelUtils.readExcelOfList(excelrx,beginCellrx);
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)!=null) {
				//搜索exceldc返回的行号
				Integer exceldcRowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(exceldc, beginCelldc, list.get(i));
				
				String exceldcenglish = null;
				if (exceldcRowNum!=null) {
					//读英文名称，大肠英文列号3
					exceldcenglish = ExcelUtils.readContent(exceldc, exceldcRowNum, 3);
				}
				
				Integer excelrxRowNum = null;
				if (exceldcenglish!=null) {
					//读excelrx行号
					excelrxRowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excelrx, beginCellrx, list.get(i));
				}
				
				//写入乳腺英文列号3
				if (excelrxRowNum!=null) {
					ExcelUtils.writeAndSaveContent(excelrx, exceldcenglish, excelrxRowNum, 3);
				}
			}
		}
		return "写入完成。。。";
	}
	
	
	
}

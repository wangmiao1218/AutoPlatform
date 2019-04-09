package com.gennlife.pmtools;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.ExcelUtils;
import com.gennlife.autoplatform.utils.ListAndStringUtils;

/**
 * @Description: 根据数据模型来源的中文，变成对应的英文路径
 * @author: wangmiao
 * @Date: 2017年9月13日 上午8:50:58 
 */
public class DataModelChineseToEnglish{

	public static String dataModelChineseToEnglish(Excel excel2,Integer beginCell2,Excel excel1){
    	Workbook workbook = excel2.getWorkbook();  
    	if (workbook == null){
    		return null; 
    	}  
    	Sheet sheet = workbook.getSheet(excel2.getSheetName());
    	for ( int rowNum= 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
    		Row row = sheet.getRow(rowNum);
    		Cell cell = null;
    		if (row!=null) {
    			cell = row.getCell(beginCell2);
    		}
    		String value=null;
    		if (cell!=null) {
    			value = cell.getStringCellValue();
    		}
    		if (value!=null && !"".equals(value)) { 
    			if (value.indexOf("；") != -1) {
    				List<String> list = ListAndStringUtils.valueSpiltBySemicolonToStringList(value);
    				String writeContentString =null;
    				StringBuilder sb = new StringBuilder();
    				for (int i = 0; i < list.size(); i++) {
        				Integer returnRowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excel1, 0, list.get(i));
        				String englishContent = ExcelUtils.readContent(excel1, returnRowNum, 1);
        				sb.append(englishContent).append(";");
    				}
    				sb.deleteCharAt(sb.length() - 1);
    				writeContentString=sb.toString();
    				ExcelUtils.writeAndSaveContent(excel2, writeContentString, rowNum, 10);
    				
    			}else {//不存在，直接找对应
    				Integer returnRowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excel1, 0, value);
    				String englishContent = ExcelUtils.readContent(excel1, returnRowNum, 1);
    				ExcelUtils.writeAndSaveContent(excel2, englishContent, rowNum, 10);
				}
    		}
    	}
		return "ok";
	}
	
}

package com.gennlife.myujie;

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
    	// 构造Workbook
    	Workbook workbook = excel2.getWorkbook();  
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel2.getSheetName());
    	
    	// 循环读取指定列数据
    	for ( int rowNum= 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell2);
    		}
    		
    		String value=null;
    		//判断是否为空
    		if (cell!=null) {
    			value = cell.getStringCellValue();
    		}
    		
    		//判断值，并进行操作
    		if (value!=null && !"".equals(value)) { 
    			//判断分号是否存在
    			//存在
    			if (value.indexOf("；") != -1) {
    				//将value转成list一个个查找
    				List<String> list = ListAndStringUtils.valueSpiltBySemicolonToStringList(value);
    				String writeContentString =null;
    				StringBuilder sb = new StringBuilder();
    				for (int i = 0; i < list.size(); i++) {
    					//中文行号
        				Integer returnRowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excel1, 0, list.get(i));
        				//对应英文内容
        				String englishContent = ExcelUtils.readContent(excel1, returnRowNum, 1);
        				sb.append(englishContent).append(";");
    				}
    				sb.deleteCharAt(sb.length() - 1);
    				writeContentString=sb.toString();
    				//写入excel2中
    				ExcelUtils.writeAndSaveContent(excel2, writeContentString, rowNum, 10);
    				
    			}else {//不存在，直接找对应
    				//中文行号
    				Integer returnRowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excel1, 0, value);
    				//对应英文内容
    				String englishContent = ExcelUtils.readContent(excel1, returnRowNum, 1);
    				//写入excel2中
    				ExcelUtils.writeAndSaveContent(excel2, englishContent, rowNum, 10);
				}
    			
    		}
    	}
    		
		return "ok";
	}
	
	
	
}

package com.gennlife.crf.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.gennlife.crf.bean.Excel;

/**
 * @Description: 对excel的处理(列：line；行：row)
 * @author: wangmiao
 * @Date: 2017年6月9日 上午10:08:05
 */
public class ExcelUtils {

    /** 
    * @Title: checkSheetOfExcelExist 
    * @Description: 搜索某一个文件中,指定sheet是否存在，存在返回名称，不存在返回null
    * @param: Excel excel
    * @return: Boolean ：存在返回true，不存在返回false
    * @throws 
    */
    public static Boolean checkSheetOfExcelExist(Excel excel) {  
        // 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
        if (workbook == null){
        	return null;  //不存在
        }  
        
        //获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	return sheet==null ? false:true;
    }  
    
    
    /** 
     * @Title: readExcelOfListReturnListMap（有重复值时使用）(适合一行行的读取及应用)
     * @Description: 搜索某一个文件中，指定列所有数值，并添加到list中(list中为map，k为行号，v为值)，返回list
     * @param: Excel excel：传入excel
     * @param: Integer beginCell：列号（从0 开始）
     * @return: List<Map<Integer, String>> list中(list中为map，k为行号，v为值)
     * @throws 
     */
    public static List<Map<Integer, String>> readExcelOfListReturnListMap(Excel excel,Integer beginCell) {  
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	
    	List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();
    	// 循环读取指定列数据
    	for ( int rowNum= 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell);
    		}
    		
    		String value=null;
    		//判断是否为空
    		if (cell!=null) {
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell.getStringCellValue();
    		}
    		
    		//将值放入list中
    		if (value!=null && !"".equals(value) && !" ".equals(value)) { 
    			Map<Integer, String> map =new HashMap<Integer, String>();
    			map.put(rowNum, value);
    			list.add(map);
    		}
    		
    	}
    	return list;
    	
    }  
    
    
    /** 
     * @Title: readExcelOfListReturnMap（有重复值时使用）(已经除去表头)
     * @Description: 搜索某一个文件中，指定列所有数值，并添加到map中(k为行号，v为值)，返回map
     * @param: Excel excel：传入excel
     * @param: Integer beginCell：列号（从0 开始）
     * @return:添加到map中(k为行号，v为值)，返回map
     * @throws 
     */
    public static Map<Integer, String> readExcelOfListReturnMap(Excel excel,Integer beginCell) {  
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	
    	Map<Integer, String> map = new HashedMap<Integer, String>();
    	// 循环读取指定列数据(已经除去表头)
    	for ( int rowNum= 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell);
    		}
    		
    		String value=null;
    		//判断是否为空
    		if (cell!=null) {
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				value = cell.getStringCellValue();
    		}
    		
    		//将值放入list中
    		if (value!=null && !"".equals(value) && !" ".equals(value)) { 
    			map.put(rowNum, value);
    		}
    		
    	}
    	return map;
    	
    }  
    
    
    /** 
     * @Title: readExcelOfList （适合整体使用，不用一行行验证行数，
     * 			例如:获取整个list，请求接口返回一个大json再解析）(已经除去表头)
     * @Description: 搜索某一个文件中，指定列所有数值，有值则添加到list中，返回list
     * @param: Excel excel：传入excel
     * @param: Integer beginCell：列号（从0 开始）
     * @return: List<String>
     * @throws 
     */
    public static List<String> readExcelOfList(Excel excel,Integer beginCell) {  
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	
    	List<String> list = new ArrayList<String>();
    	// 循环读取指定列数据(已经除去表头:rowNum=1)
    	for ( int rowNum= 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell);
    		}
    		
    		String value=null;
    		//判断是否为空
    		if (cell!=null) {
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell.getStringCellValue();
    		}
    		
    		//将值放入list中
    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
    			list.add(value);
    		}
    	}
    	return list;
    }  
    
	
    
    /** 
     * @Title: readExcelOfOneLine 
     * @Description: 搜索某一个文件,指定行数据，有值则添加到list中，返回list
     * @param: Excel excel：传入excel
     * @param: Integer beginCell：列号（从0 开始）
     * @return: List<String>
     * @throws 
     */
    public static List<String> readExcelOfOneLine(Excel excel,Integer beginRow) { 
    	List<String> list = new ArrayList<String>();
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	Row row = sheet.getRow(beginRow);
        // 循环读取一行中数据，row.getLastCellNum()：最大列号
  		for ( int cellNum= 0; cellNum <= row.getLastCellNum(); cellNum++) {
  			Cell cell = null;
  			//判断是否为空
  			if (row!=null) {
  				//指定 列beginCell
  	 			cell = row.getCell(cellNum);
 			}
  			String value=null;
  			//判断是否为空
  			if (cell!=null) {
  				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
  				value = cell.getStringCellValue();
 			}
  			
  			//将值放入list中
			if (value!=null && !"".equals(value) && !" ".equals(value)) {  
				list.add(value);
			}
  		}
		
    	return list;
    }  
    
    
    
    
    /** 
     * @Title: readExcelOfTwoList 
     * @Description: 搜索某一个文件中，指定2列所有数值，并顺序添加到list中，返回list
     * @param: Excel excel：传入excel
     * @param: Integer oneCell：列号（从0 开始）
     * @param: Integer twoCell：列号2（从0 开始）
     * @return: List<String>
     * @throws 
     */
    public static List<String> readExcelOfTwoList(Excel excel,Integer oneCell,Integer twoCell) {  
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	
    	List<String> chNameslist = new ArrayList<String>();
    	// 循环读取指定列数据(从第二行开始，除去表头)
    	for ( int rowNum= 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell1 = null;
    		Cell cell2 = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列oneCell,twoCell的cell
    			cell1 = row.getCell(oneCell);
    			cell2 = row.getCell(twoCell);
    		}
    		
    		String value=null;
    		//==============此处有坑，不要轻易改！！！！！================
    		//判断第一列是否为空,不为空则获取值，为空则判断第二列
    		if (cell1!=null) {
    			cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell1.getStringCellValue();
	    		//将值放入list中
	    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
	    			chNameslist.add(value);
	    		}else {//若等于""时，则继续判断
	    			if (cell2!=null) {
	    				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
						value = cell2.getStringCellValue();
			    		//将值放入list中
			    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
			    			chNameslist.add(value);
			    		}
	    			}
				}
    		}else {
    			if (cell2!=null) {
    				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
					value = cell2.getStringCellValue();
		    		//将值放入list中
		    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
		    			chNameslist.add(value);
		    		}
    			}
    		}
    	}
    	return chNameslist;
    	
    }  
    
    
    /** 
     * @Title: readExcelOfThreeList 
     * @Description: 搜索某一个文件中，指定3列所有数值，并顺序添加到list中，返回list
     * @param: Excel excel：传入excel
     * @param: Integer oneCell：列号（从0 开始）
     * @param: Integer twoCell：列号2（从0 开始）
     * @param: Integer threeCell：列号3（从0 开始）
     * @return: List<String>
     * @throws 
     */
    public static List<String> readExcelOfThreeList(Excel excel,Integer oneCell,Integer twoCell,Integer threeCell) {  
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	
    	List<String> chNameslist = new ArrayList<String>();
    	// 循环读取指定列数据(从第二行开始，除去表头)
    	for ( int rowNum= 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell1 = null;
    		Cell cell2 = null;
    		Cell cell3 = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列oneCell,twoCell的cell
    			cell1 = row.getCell(oneCell);
    			cell2 = row.getCell(twoCell);
    			cell3 = row.getCell(threeCell);
    		}
    		
    		String value=null;
    		//==============此处有坑，不要轻易改！！！！！================
    		//判断第一列是否为空,不为空则获取值，为空则判断第二列
    		//第一列为空时：
    		if (cell1!=null) {
    			cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell1.getStringCellValue();
	    		//将值放入list中
	    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
	    			chNameslist.add(value);
	    		}else {//若等于""时，则继续判断
	    			if (cell2!=null) {
	    				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
						value = cell2.getStringCellValue();
			    		//将值放入list中
			    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
			    			chNameslist.add(value);
			    		}else {
			    			if (cell3!=null && !"".equals(cell3)) {
			    				cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
		    					value = cell3.getStringCellValue();
		    		    		//将值放入list中
		    		    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
		    		    			chNameslist.add(value);
		    		    		}
		        			}
						}
	    			}else {
	    				if (cell3!=null && !"".equals(cell3)) {
	    					cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
	    					value = cell3.getStringCellValue();
	    		    		//将值放入list中
	    		    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
	    		    			chNameslist.add(value);
	    		    		}
	        			}
					}
				}
    		}else {
    			if (cell2!=null) {
    				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
					value = cell2.getStringCellValue();
		    		//将值放入list中
		    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
		    			chNameslist.add(value);
		    		}else {
		    			if (cell3!=null && !"".equals(cell3)) {
		    				cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
	    					value = cell3.getStringCellValue();
	    		    		//将值放入list中
	    		    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
	    		    			chNameslist.add(value);
	    		    		}
	        			}
					}
    			}else {
    				if (cell3!=null && !"".equals(cell3)) {
    					cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
    					value = cell3.getStringCellValue();
    		    		//将值放入list中
    		    		if (value!=null && !"".equals(value) && !" ".equals(value)) {  
    		    			chNameslist.add(value);
    		    		}
        			}
				}
    		}
    	}
    	return chNameslist;
    	
    }  
    
    
    /** 
    * @Title: searchKeyWordOfOneLine 
    * @Description: 搜索某一个文件中，指定行，是否包含某个关键字 ,存在返回列号，不存在返回null(若重复，则返回最后一个)
    * @param: Excel excel：传入excel
    * @param: int beginRow：行号（从0 开始）：一般是0，读取表头行
    * @param: String keyWord：要搜索的关键字
    * @return: Integer ：存在返回列号，不存在返回null(若重复，则返回最后一个)
    * @throws 
    */
    public static Integer searchKeyWordOfOneLine(Excel excel,int beginRow,String keyWord) {  
    	//先判断keyWord是否为null
    	if (keyWord ==null) {
			return null;
		}
    	
        // 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
  
        if (workbook == null){
        	return null;  //不存在
        }  
        
        //获取sheet
		Sheet sheet = workbook.getSheet(excel.getSheetName());
		
		Integer returnNum = null;
		Row row = sheet.getRow(beginRow);
		
       // 循环读取一行中数据，row.getLastCellNum()：最大列号
 		for ( int cellNum= 0; cellNum <= row.getLastCellNum(); cellNum++) {
 			Cell cell = null;
 			//判断是否为空
 			if (row!=null) {
 				//指定 列beginCell
 	 			cell = row.getCell(cellNum);
			}
 			
 			String value=null;
 			//判断是否为空
 			if (cell!=null) {
 				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
 				value = cell.getStringCellValue();
			}
 			
 			if (keyWord.equals(value)) {  
 				returnNum= cellNum;
 			}
 		}
		return returnNum;
 		
    }  
	
    
    /** 
     * @Title: searchKeyWordOfListReturnRowNum 
     * @Description: 搜索某一个文件中，指定列，是否包含某个关键字 ,存在返回行号，不存在返回null(若重复，则返回最后一个,就是顺序找的)
     * @param: Excel excel：传入excel
     * @param: Integer beginCell：列号（从0 开始）
     * @param: String keyWord：要搜索的关键字
     * @return: Integer：存在返回行号，不存在返回null(若重复，则返回最后一个,就是顺序找的)
     * @throws 
     */
    public static Integer searchKeyWordOfListReturnRowNum(Excel excel,Integer beginCell,String keyWord) {  
    	//先判断keyWord是否为null
    	if (keyWord ==null) {
    		return null;
    	}
    	
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	Integer returnNum = null;
    	
    	// 循环读取指定列数据
    	for ( int rowNum= 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell);
    		}
    		
    		String value=null;
    		//判断是否为空
    		if (cell!=null) {
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell.getStringCellValue();
    		}
    		
    		if (keyWord.equals(value)) {  
    			returnNum= rowNum;
    		}
    	}
    	return returnNum;
    	
    }  
    
    
    /** 
     * @Title: searchKeyWordOfListByOrderDescReturnRowNum 
     * @Description: 搜索某一个文件中，指定列beginCell，从beginRow开始往上找，是否包含某个关键字 ,存在则返回最近的行号，不存在返回null(若重复，则返回往上离着最近的一个)
     * @param: Excel excel：传入excel
     * @param: Integer beginRow：行号（从0 开始）
     * @param: Integer beginCell：列号（从0 开始）
     * @param: String keyWord：要搜索的关键字
     * @return: Integer：存在则返回最近的行号，不存在返回null(若重复，则返回往上离着最近的一个)
     * @throws 
     */
    public static Integer searchKeyWordOfListByOrderDescReturnRowNum(Excel excel,Integer beginRow,Integer beginCell,String keyWord) {  
    	//先判断keyWord是否为null
    	if (keyWord ==null) {
    		return null;
    	}
    	
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	
    	Integer returnNum = null;
    	// 循环读取指定列数据
    	for ( int rowNum=beginRow; rowNum >= 0; rowNum--) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell);
    		}
    		
    		String value=null;
    		//判断是否为空
    		if (cell!=null) {
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell.getStringCellValue();
    		}
    		
    		//逆序获取第一个有值且相等的，停止
    		if (value!=null && keyWord.equals(value)) {
    			returnNum= rowNum;
    			break;
    		}
    		
    		/*
    		//若value为空，则返回null
    		if (value==null && !keyWord.equals(value)) {
    			returnNum=null;
			}
			*/
    		
    	}
    	return returnNum;
    	
    }  
    
    
    /** 
     * @Title: searchValueOfListByOrder 
     * @Description: 搜索某一个文件中，指定列,从上到下遍历，值返回第一个有值
     * @param: Excel excel：传入excel
     * @param: Integer beginCell：列号（从0 开始）
     * @return: Map<Integer, String>：返回map键值对：行号、值
     * @throws 
     */
    public static Map<Integer, String> searchValueOfListByOrder(Excel excel,Integer beginCell) {  
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	
    	Map<Integer,String> map = new HashedMap<Integer, String>();
    	// 循环读取指定列数据
    	for ( int rowNum= 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell);
    		}
    		
    		String value =null;
    		//判断是否为空
    		if (cell!=null) {
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell.getStringCellValue();
    		}
    		
    		//除去表头
    		if ("联动".equals(value)) {
				continue;
			}
    		
    		//获取第一个有值，停止
    		if (value!=null && !"".equals(value) && !" ".equals(value)) {
    			map.put(rowNum, value);
				break;
			}
    		
    	}
    	return map;
    	
    }  
    
  
    /** 
     * @Title: searchValueOfListByOrderDesc 
     * @Description: 搜索某一个文件中，指定列,从beginRow行开始，往上遍历，返回第一个有值
     * @param: Excel excel：传入excel
     * @param: Integer beginRow：行号（从0 开始）
     * @param: Integer beginCell：列号（从0 开始）
     * @return: Map<Integer, String>：返回map键值对：行号、值
     * @throws 
     */
    public static Map<Integer, String> searchValueOfListByOrderDesc(Excel excel,Integer beginRow,Integer beginCell) {  
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	
        Map<Integer,String> map = new HashedMap<Integer, String>();
    	// 循环读取指定列数据
    	for ( int rowNum =beginRow;rowNum>=0; rowNum--) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell);
    		}
    		
    		String value=null;
    		//判断是否为空
    		if (cell!=null) {
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell.getStringCellValue();
    		}
    		
    		//逆序获取第一个有值，停止
    		if (value!=null && !"".equals(value) && !" ".equals(value)) {
    			map.put(rowNum, value);
				break;
			}
    		
    	}
    	return map;
    	
    }  
    
    /** 
     * @Title: searchValueOfListByOrderDescReturnRowNum 
     * @Description: 搜索某一个文件中，指定列,从beginRow行开始，往上遍历，返回第一个有值行号
     * @param: Excel excel：传入excel
     * @param: Integer beginRow：行号（从0 开始）
     * @param: Integer beginCell：列号（从0 开始）
     * @return: Integer：返回行号
     * @throws 
     */
    public static Integer searchValueOfListByOrderDescReturnRowNum(Excel excel,Integer beginRow,Integer beginCell) {  
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	Integer rowNumInteger=null;
    	// 循环读取指定列数据
    	for ( int rowNum =beginRow;rowNum>=0; rowNum--) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell);
    		}
    		
    		String value=null;
    		//判断是否为空
    		if (cell!=null) {
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell.getStringCellValue();
    		}
    		
    		//逆序获取第一个有值，停止
    		if (value!=null && !"".equals(value) && !" ".equals(value)) {
    			rowNumInteger=rowNum;
    			break;
    		}
    		
    	}
    	return rowNumInteger;
    	
    }  
    
    
    /** 
    * @Title: searchValueOfListBetweenTwoRowNumByOrderDescReturnRowNum 
    * @Description: 搜索某一个文件中，指定列,smallRow和bigRow之间，从bigRow往上到smallRow遍历，返回第一个有值行号，若为空则返回null
    * @param: @param excel
    * @param: @param smallRow
    * @param: @param bigRow
    * @param: @param beginCell
    * @return: Integer ：从bigRow往上到smallRow遍历，返回第一个有值行号，若为空则返回null
    * @throws 
    */
    public static Integer searchValueOfListBetweenTwoRowNumByOrderDescReturnRowNum(Excel excel,Integer smallRow,Integer bigRow,Integer beginCell) {  
    	// 构造Workbook
    	Workbook workbook = excel.getWorkbook();  
    	
    	if (workbook == null){
    		return null;  //不存在
    	}  
    	
    	//获取sheet
    	Sheet sheet = workbook.getSheet(excel.getSheetName());
    	Integer rowNumInteger=null;
    	// 循环读取指定列数据
    	for ( int rowNum =bigRow;rowNum >= smallRow; rowNum--) {
    		Row row = sheet.getRow(rowNum);
    		
    		Cell cell = null;
    		//判断是否为空
    		if (row!=null) {
    			//指定 列beginCell
    			cell = row.getCell(beginCell);
    		}
    		
    		String value=null;
    		//判断是否为空
    		if (cell!=null) {
    			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    			value = cell.getStringCellValue();
    		}
    		
    		//逆序获取第一个有值，停止
    		if (value!=null && !"".equals(value) && !" ".equals(value)) {
    			rowNumInteger=rowNum;
    			break;
    		}
    		
    		//若value为空，则返回null
    		if (value==null) {
    			rowNumInteger=null;
			}
    		
    	}
    	return rowNumInteger;
    	
    }  
    
	
	/** 
	* @Title: readContent 
	* @Description: 读任意坐标数据
	* @param: Excel excel :传入excel
	* @param: int beginRow :行号（从 0 算起）
	* @param: int beginCell :列号（从 0 算起）
	* @return: String
	* @throws 
	*/
	public static String readContent(Excel excel, int beginRow, int beginCell) {
		Workbook workbook = excel.getWorkbook();
		
		if (workbook == null){
        	return null;  //不存在
        }  
		
		Sheet sheet = workbook.getSheet(excel.getSheetName());
		//获取row
		Row row = sheet.getRow(beginRow);
		
		Cell cell = null;
		//判断是否为空
		if (row!=null) {
			//指定 列beginCell
			cell = row.getCell(beginCell);
		}
			
		String value=null;
		//判断是否为空
		if (cell!=null) {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			value = cell.getStringCellValue();
		}
		
		return value;
	}

	
	/** 
	* @Title: readTwoContentAndJudge 
	* @Description: 读取某行中，指定的两列值，判断是否相等
	* @param: Excel excel :传入excel
	* @param: Integer beginRow :行号（从 0 算起）
	* @param: Integer beginCell :前面列号（从 0 算起）
	* @param: Integer endCell：后面列号（从 0 算起）
	* @return: String ：都为null或比较相等，则返回pass，反之返回no
	* @throws 
	*/
	public static String readTwoContentAndJudge(Excel excel, Integer beginRow, Integer beginCell,Integer endCell) {
		Workbook workbook = excel.getWorkbook();
		
		if (workbook == null){
        	return null;  //不存在
        } 
		
		Sheet sheet = workbook.getSheet(excel.getSheetName());
		//根据行获取row
		Row row = sheet.getRow(beginRow);
		
		//分别根据列号获取对应的cell
		Cell beginCellContent = row.getCell(beginCell);
		Cell endCellContent = row.getCell(endCell);
		
		String valueString=null;
		
		//判断：都为空，也为pass
		if (beginCellContent==null && endCellContent ==null) {
			valueString="pass";
		}
		
		//任意一个为空则返回no
		if ((beginCellContent==null && endCellContent !=null) || (beginCellContent!=null && endCellContent ==null) ) {
			valueString="no";
		}
				
		//都不为null时，进行比较
		if (beginCellContent != null && endCellContent != null) {
			String beginCellStr = ListAndStringUtils.trimString(beginCellContent.toString());
			String endCellStr = ListAndStringUtils.trimString(endCellContent.toString());
			
			//判断
			if (beginCellStr.equals(endCellStr)) {
				valueString="pass";
			}else {
				valueString="no";
			}
		}
		
		return valueString;
	}

	
	/**
	* @Title: writeAndSaveContent 
	* @Description: 用于操作Excel，在任意坐标处写入数据并保存
	* @param: Excel excel :传入excel
	* @param: String newContent :内容
	* @param: int beginRow :行号（从 0 算起）
	* @param: int beginCell :列号（从 0 算起）
	* @return: void
	* @throws 
	*/
	public static void writeAndSaveContent(Excel excel,String newContent, int beginRow, int beginCell) {
		//获取行
		Workbook workbook = excel.getWorkbook();
		Sheet sheet = workbook.getSheet(excel.getSheetName());
		Row row = sheet.getRow(beginRow);

		if (null == row) {
			// 如果不做空判断，你必须让你的模板文件画好边框，beginRow和beginCell必须在边框最大值以内
			// 否则会出现空指针异常
			row = sheet.createRow(beginRow);
		}
		
		Cell cell = row.getCell(beginCell);
		if (null == cell) {
			cell = row.createCell(beginCell);
		}
		// 设置存入内容为字符串
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);

		// 向单元格中放入值
		cell.setCellValue(newContent);

		// 保存
		// 根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径，声明一个File文件对象
		File file = new File(excel.getFilePath() + "\\" + excel.getFileName());
		
		//建立输出流
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			workbook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}


	/** 
	* @Title: writeOneListAndSaveContent (除表头)
	* @Description: 用于操作Excel，在任意一列写入数据并保存
	* @param: @param excel:传入excel
	* @param: @param newContentList
	* @param: @param beginCell :列号（从 0 算起）
	* @return: void
	* @throws 
	*/
	public static void writeOneListAndSaveContent(Excel excel,List<String> newContentList, int beginCell) {
		//获取行
		Workbook workbook = excel.getWorkbook();
		
		for (int i =0; i < newContentList.size(); i++) {
			Sheet sheet = workbook.getSheet(excel.getSheetName());
			//设置行（除去表头）
			Row row = sheet.getRow(i+1);

			if (null == row) {
				// 如果不做空判断，你必须让你的模板文件画好边框，beginRow和beginCell必须在边框最大值以内
				// 否则会出现空指针异常
				row = sheet.createRow(newContentList.size());
			}
			
			Cell cell = row.getCell(beginCell);
			if (null == cell) {
				cell = row.createCell(beginCell);
			}
			
			// 设置存入内容为字符串
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	
			// 向单元格中放入值
			cell.setCellValue(newContentList.get(i));
		}
		
		// 保存
		// 根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径，声明一个File文件对象
		File file = new File(excel.getFilePath() + "\\" + excel.getFileName());
		
		//建立输出流
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			workbook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
}

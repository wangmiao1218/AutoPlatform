package com.gennlife.crf.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Description: 封装Excel为bean
 * @author: wangmiao
 * @Date: 2017年6月9日 上午11:30:14 
 */
public class Excel {
	private String filePath;
	private String fileName;
	private String sheetName;

	//private Row row;
	//private Cell cell;

	public Excel() {
	}

	public Excel(String filePath, String fileName, String sheetName) {
		this.filePath = filePath;
		this.fileName = fileName;
		this.sheetName = sheetName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Workbook getWorkbook() {
		// 根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径，声明一个File文件对象
		File file = new File(getFilePath() + "\\" + getFileName());

		// 创建FileInputStream对象用于读取Excel文件
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// 获取文件名参数的扩展名，判断是.xlsx文件还是.xls文件
		String fileExtensionName = getFileName().substring(getFileName().lastIndexOf("."));
		
		Workbook workbook = null;
		
		if (fileExtensionName.equals(".xlsx")) {
			try {
				workbook = new XSSFWorkbook(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (fileExtensionName.equals(".xls")) {
			try {
				workbook = new HSSFWorkbook(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return workbook;
	}

}

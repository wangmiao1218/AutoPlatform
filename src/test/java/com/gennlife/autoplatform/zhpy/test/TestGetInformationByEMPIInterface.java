package com.gennlife.autoplatform.zhpy.test;

import org.junit.Test;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.zhpy.GetInformationByEMPIInterface;

public class TestGetInformationByEMPIInterface {

	private String filePath = "C:\\Users\\www\\Desktop";
	private String fileName = "22.xlsx";
	private String sheetName = "Sheet1";
	
	//测试传入多个pat
	@Test
	public void getResultsByPostMethod() {
		Excel excel = new Excel(filePath, fileName, sheetName);
		String results = GetInformationByEMPIInterface.getResultsByPostMethod(excel);
		System.out.println(results);
	}
	
	
	//测试传入一个pat
	@Test
	public void getOneResultByPostMethod() {
		Excel excel = new Excel(filePath, fileName, sheetName);
		String result = GetInformationByEMPIInterface.getOneResultByPostMethod(excel);
		System.out.println(result);
	}
	
	
}

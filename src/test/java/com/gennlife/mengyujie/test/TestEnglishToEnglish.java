package com.gennlife.mengyujie.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.myujie.EnglishToEnglish;

public class TestEnglishToEnglish {

	private String filePath = "E:\\yujie";
	private String fileName = "英文版乳腺癌2.xlsx";
	private String sheetName = "就诊-介入治疗(package)";
	
	private String fileName2 = "CRF表字段配置2.xlsx";
	
	@Test
	public void testYujie(){
		Excel excelrx = new Excel(filePath, fileName, sheetName);
		
		Excel exceldc = new Excel(filePath, fileName2, sheetName);
		
		String str = EnglishToEnglish.testYujie(excelrx, 2, exceldc, 2);
		
		System.out.println(str);
	}
	
	
	
}
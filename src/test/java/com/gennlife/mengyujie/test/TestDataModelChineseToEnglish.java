package com.gennlife.mengyujie.test;

import org.junit.Test;

import com.gennlife.crf.bean.Excel;
import com.gennlife.myujie.DataModelChineseToEnglish;

public class TestDataModelChineseToEnglish {

	private String filePath = "E:\\yujie\\4";
	private String fileName1 = "1.xlsx";
	private String sheetName1 = "Sheet2";
	
	private String fileName2 = "2.xlsx";
	private String sheetName2 = "合并";
	
	@Test
	public void dataModelChineseToEnglish(){
		Excel excel1 = new Excel(filePath, fileName1, sheetName1);
		Excel excel2 = new Excel(filePath, fileName2, sheetName2);
		String str = DataModelChineseToEnglish.dataModelChineseToEnglish(excel2, 9, excel1);
		System.out.println(str);
	}
	
	
}
package com.gennlife.pmtools.test;

import org.junit.Test;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.pmtools.ConfiguredLinkagePath;

public class TestConfiguredLinkagePath {

	private String filePath = "E:\\yujie\\TranslateToEnglish";
	
	private String fileName = "模板结构-广东省人民结直肠癌淼淼.xlsx";
	private String fileName2 = "结直肠癌字段对接表.xlsx";
	
	private String sheetName = "总体结构";
	private String sheetName2 = "基本信息";
	
	@Test
	public void writeLinkagePath(){
		Excel excelmb = new Excel(filePath, fileName, sheetName);
		Excel excel = new Excel(filePath, fileName2, sheetName);
		ConfiguredLinkagePath.writeLinkagePath(excelmb,excel);
		
		System.out.println("ok");
	}
	
	@Test
	public void writeSchemaOfThreeGroups(){
		Excel excel = new Excel(filePath, fileName2, sheetName2);
		ConfiguredLinkagePath.writeSchemaOfThreeGroups(excel,"Imageological_examination");
		System.out.println("ok");
	}
	
	@Test
	public void writeSchemaOfTwoGroups(){
		Excel excel = new Excel(filePath, fileName2, sheetName2);
		ConfiguredLinkagePath.writeSchemaOfTwoGroups(excel,"patient_info");
		System.out.println("ok");
	}
	
}
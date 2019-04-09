package com.gennlife.mengyujie.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.gennlife.crf.bean.Excel;
import com.gennlife.myujie.ConfiguredDataQualityControl;
import com.gennlife.myujie.ConfiguredLinkagePath;

public class TestConfiguredDataQualityControl {

private String filePath = "E:\\yujie\\10";
	
	private String fileName = "模板结构.xlsx";
	private String sheetName = "配置表";
	
	private String fileName2 = "XX医院表级数据质控_V1.0_181016_MYJ.xlsx";
	private String sheetName2 = "表级别数据质控－转模型";
	
	@Test
	public void writeDataQualityControl(){
		Excel excelmb = new Excel(filePath, fileName, sheetName);
		Excel excel = new Excel(filePath, fileName2, sheetName);
		ConfiguredDataQualityControl.writeDataQualityControl(excelmb,excel);
		System.out.println("ok");
	}
	
	@Test
	public void writeSchemaOfThreeGroups(){
		Excel excel = new Excel(filePath, fileName2, sheetName2);
		ConfiguredLinkagePath.writeSchemaOfThreeGroups(excel,"Imageological_examination");
		System.out.println("ok");
	}
	
	@Test
	public void test001(){
		String seconds="";
		String format="";
		if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
		    
		}  
		if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
			SimpleDateFormat sdf = new SimpleDateFormat(format);  
			sdf.format(new Date(Long.valueOf(seconds+"000")));
		}
	
}
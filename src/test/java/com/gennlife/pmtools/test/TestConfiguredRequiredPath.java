package com.gennlife.pmtools.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.ExcelUtils;
import com.gennlife.pmtools.ConfiguredRequiredPath;

public class TestConfiguredRequiredPath {

private String filePath = "C:\\Users\\www\\Desktop";
	
	private String fileName = "模板结构-安贞心血管1(2).xlsx";
	private String fileName2 = "安贞心血管CRF1.2.2-2018-02-08-最终版增加字段(3).xls";
	
	private String sheetName = "总体结构";
	//private String sheetName2 = "患者信息";

	@Test
	public void writeRequiredPath(){
		Excel excelmb = new Excel(filePath, fileName, sheetName);
		Excel excel = new Excel(filePath, fileName2, sheetName);
		ConfiguredRequiredPath.writeRequiredPath(excelmb, excel);
		System.out.println("ok");
	}
	
}
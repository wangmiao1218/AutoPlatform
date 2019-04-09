package com.gennlife.autoplatform.anzhen.add.gaoxueya.test;

import java.util.List;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.autoplatform.anzhen.add.gaoxueya.WriteExcelCrfTemplateAnzhen;
import com.gennlife.autoplatform.bean.CrfTemplateAnzhen;
import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.service.CrfTemplateAnzhenService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestWriteExcelCrfTemplateAnzhen {
	
	@Autowired
	private CrfTemplateAnzhenService crfTemplateAnzhenService;

	private String filePath = "E:\\安贞\\！安贞数据导出";
	private String fileName = "1.xlsx";
	private String sheetName = "全部";
	
	
	private String fileNameSf = "test.xlsx";
	private String sheetNameSf = "随访3";
	
	
	@Test
	public void readTwoContentAndJudgeAndWriteResult() throws Exception{
		Excel excel = new Excel(filePath, fileName, sheetName);
		Integer allRow=3899;
		Integer beginCell=2;
		Integer endCell=5;
		Integer writeCell=6;
		
		String str = WriteExcelCrfTemplateAnzhen.readTwoContentAndJudgeAndWriteResult(excel, allRow, beginCell, endCell, writeCell);
		System.out.println(str);
	}
	
	
	@Test
	public void writeExcelByCompareEnglishName() throws Exception{
		List<CrfTemplateAnzhen> list = crfTemplateAnzhenService.getCrfTemplateAnzhenList(new HashedMap<String, Object>());
		//Excel excel = new Excel(filePath, fileName, sheetName);
		Excel excel = new Excel(filePath, fileNameSf, sheetNameSf);
		Integer beginCell=3;
		
		String str = WriteExcelCrfTemplateAnzhen.writeExcelByCompareEnglishName(excel, list, beginCell);
		System.out.println(str);
	}
	
	
}
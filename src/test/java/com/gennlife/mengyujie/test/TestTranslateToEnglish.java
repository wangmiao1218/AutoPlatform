package com.gennlife.mengyujie.test;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.gennlife.crf.bean.Excel;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.ExcelUtils;
import com.gennlife.crf.utils.ListAndStringUtils;
import com.gennlife.crf.utils.QuitWebDriver;
import com.gennlife.myujie.TranslateToEnglish;

public class TestTranslateToEnglish {

	private String filePath = "E:\\yujie\\TranslateToEnglish\\1";
	
	//配置表
	private String fileName = "模板结构-房颤-给淼淼2.xlsx";
	//数据表
	private String fileName2 = "房颤数据平台表单2.xlsx";
	
	private String sheetName = "总体结构";
	private String sheetName2 = "患者信息";
	

	//直接运行此方法，翻译英文名
	@Test
	public void writeEnNames() throws Exception{
		Excel excelmb = new Excel(filePath, fileName, sheetName);
		Excel excel = new Excel(filePath, fileName2, sheetName);
		TranslateToEnglish.writeEnNames(excelmb, excel);
		System.out.println("ok");
	}
	
	
	
	//===============================================
	@Test
	public void sameListTransferToSequenceList() throws Exception{
		Excel excel = new Excel(filePath, fileName2, sheetName2);
		List<String> enNamesList = ExcelUtils.readExcelOfList(excel,2);
		List<String> list = ListAndStringUtils.sameListTransferToSequenceList(enNamesList);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void writeEnNamesOfTwoGroups() throws Exception{
		Excel excel = new Excel(filePath, fileName, sheetName);
		List<String> enNamesList = ExcelUtils.readExcelOfList(excel,2);
		
		List<String> enNamesListFilter = ListAndStringUtils.enNamesListFilter(enNamesList);
		for (int i = 0; i < enNamesListFilter.size(); i++) {
			System.out.println(enNamesListFilter.get(i));
			//System.out.println(enNamesList.get(i)+"---"+enNamesListFilter.get(i));
		}
	}
	
	@Test
	public void enNamesListFilter() throws Exception{
		Excel excel = new Excel(filePath, fileName, sheetName);
		List<String> enNamesList = ExcelUtils.readExcelOfList(excel,2);
		
		List<String> enNamesListFilter = ListAndStringUtils.enNamesListFilter(enNamesList);
		for (int i = 0; i < enNamesListFilter.size(); i++) {
			System.out.println(enNamesListFilter.get(i));
			//System.out.println(enNamesList.get(i)+"---"+enNamesListFilter.get(i));
		}
	}
	
	@Test
	public void chNamesListFilter() throws Exception{
		Excel excel = new Excel(filePath, fileName2, sheetName2);
		List<String> chNamesList = ExcelUtils.readExcelOfThreeList(excel, 0, 1,2);
		
		List<String> chNamesListFilter = ListAndStringUtils.chNamesListFilter(chNamesList);
		for (int i = 0; i < chNamesListFilter.size(); i++) {
			System.out.println(chNamesListFilter.get(i));
			//System.out.println(chNamesList.get(i)+"---"+chNamesListFilter.get(i));
		}
	}
	
	@Test
	public void translateChNamesListToEnNamesList() throws Exception{
		Excel excel = new Excel(filePath, fileName, sheetName);
		List<String> chNamesList = ExcelUtils.readExcelOfThreeList(excel, 0, 1,2);
		for (int i = 0; i < chNamesList.size(); i++) {
			System.out.println(chNamesList.get(i));
		}
		System.out.println(chNamesList.size());
		System.out.println("=============");
		
		List<String> enNamesList = TranslateToEnglish.translateChNamesListToEnNamesList(chNamesList);
		for (int i = 0; i < enNamesList.size(); i++) {
			System.out.println(enNamesList.get(i));
		}
		System.out.println(enNamesList.size());
	}
	
	@Test
	public void testSearch() throws Exception{
		String input="院内是否服用醛固酮拮抗剂";
		String output=null;
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		//driver.navigate().to("http://fanyi.baidu.com/translate#zh/en/");
		driver.navigate().to("https://translate.google.cn/#view=home&op=translate&sl=zh-CN&tl=en");
		Thread.sleep(1000);
		
		driver.findElementById("source").sendKeys(input);
		Thread.sleep(2000);
		
		//获取翻译后的值
		output=driver.findElementByXPath("/html/body/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div/span[1]/span").getText();
		//调试
		//Screenshot.screenshot(driver);

		System.out.println(output);
		
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	
}
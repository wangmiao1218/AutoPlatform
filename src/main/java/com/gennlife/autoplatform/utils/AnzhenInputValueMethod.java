package com.gennlife.autoplatform.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

import com.gennlife.autoplatform.bean.CrfTemplateAnzhen;

/**
 * @Description: 安贞环境，录入固定数据的通用的方法
 * @author: wangmiao
 * @Date: 2017年7月28日 下午4:58:39 
 */
public class AnzhenInputValueMethod {
	
	private static Logger logger = Logger.getLogger(AnzhenInputValueMethod.class); 
	
	/** 
	* @Title: inputValueByVariableType(只针对枚举、多选、以及输入框) 
	* @Description: 根据变量类型，输入值（只需传入driver、左侧点击的路径，以及list，根据数据库中配置，录入固定的数据）
	* @param: @param driver
	* @param: @param idXpath 前台点击的左侧路径
	* @param: @param list
	* @param: @throws Exception :
	* @return: void
	* @throws 
	*/
	public static void inputValueByVariableType(PhantomJSDriver driver,String idXpath,List<CrfTemplateAnzhen> list) throws Exception{
		driver.findElementById(idXpath).click();
		Thread.sleep(3000);
		for (int i = 0; i < list.size(); i++) {
			logger.info(list.get(i).getChineseName());
			if ("枚举型".contains(list.get(i).getVariableType())) {
				new Select(driver.findElementById(list.get(i).getIdXpath())).selectByValue(list.get(i).getInputValue());
			}
			else if ("多选".contains(list.get(i).getVariableType())) {
				//for checkBox
				driver.findElementByXPath(list.get(i).getIdXpath()).click();
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					driver.findElementByXPath(list.get(i).getInputValue()+j+"]/a/label").click();
				}
			}
			else if ("多选_联动".contains(list.get(i).getVariableType())) {
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					driver.findElementByXPath(list.get(i).getIdXpath()).click();
					driver.findElementByXPath(list.get(i).getInputValue()+j+"]/a/label").click();
				}
			}
			else if ("日期型".contains(list.get(i).getVariableType()) && "单点击".contains(list.get(i).getDateFormat())){
				driver.findElementById(list.get(i).getIdXpath()).click();
			}
			else if ("日期型".contains(list.get(i).getVariableType()) && "时分".contains(list.get(i).getDateFormat())){
				driver.findElementById(list.get(i).getIdXpath()).click();
				driver.findElementByXPath(".//*[@id='ui-datepicker-div']/div[3]/button[1]").click();
			}
			else if ("图片型".contains(list.get(i).getVariableType())){
				//driver.findElementById(list.get(i).getIdXpath()).click();
				
				
			}
			else if ("本次主要出院诊断".contains(list.get(i).getChineseName())){
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue());
				Thread.sleep(3000);
		        By zd = new By.ByXPath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//span[contains(text(),'原发性高血压')]");
		        driver.findElement(zd).click();
			}
			else if ("其他出院诊断".contains(list.get(i).getChineseName())){
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue());
				Thread.sleep(3000);
		        By qtzd = new By.ByXPath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//span[contains(text(),'1型糖尿病性高血压')]");
		        driver.findElement(qtzd).click();
			}
			else {
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue());
			}
		}
		
		//循环后保存
		driver.findElementById("input-save").click();
		Thread.sleep(1000);
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(2000);
	}

	
	/** 
	 * @Title: inputValueByVariableType_Sf_inputValue
	 * @Description: 随访中添加数据，根据变量类型，输入值（只需传入driver以及list，根据数据库中配置，录入固定的数据inputValue）
	 * @param: @param driver
	 * @param: @param list
	 * @param: @throws Exception :
	 * @return: void
	 * @throws 
	 */
	public static void inputValueByVariableType_Sf_inputValue(PhantomJSDriver driver,List<CrfTemplateAnzhen> list) throws Exception{
		for (int i = 0; i < list.size(); i++) {
			logger.info(list.get(i).getChineseName());
			if ("枚举型".contains(list.get(i).getVariableType())) {
				new Select(driver.findElementById(list.get(i).getIdXpath())).selectByValue(list.get(i).getInputValue());
			}
			else if ("多选".contains(list.get(i).getVariableType())) {
				//for checkBox
				driver.findElementByXPath(list.get(i).getIdXpath()).click();
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					driver.findElementByXPath(list.get(i).getInputValue()+j+"]/a/label").click();
				}
			}
			else if ("多选_联动".contains(list.get(i).getVariableType())) {
				//for checkBox
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					driver.findElementByXPath(list.get(i).getIdXpath()).click();
					driver.findElementByXPath(list.get(i).getInputValue()+j+"]/a/label").click();
				}
			}
			else if ("日期型".contains(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).click();
				Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
				selYear.selectByValue("2018"); 
				Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
				selMouth.selectByValue("0"); 
				driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
			}
			else if ("图片型".contains(list.get(i).getVariableType())){
				//driver.findElementById(list.get(i).getIdXpath()).click();
				
				
			}
			else if ("字符串_诊断".equals(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue());
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue());
				Thread.sleep(5000);
				By zd = new By.ByXPath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//span[contains(text(),'"+list.get(i).getInputValue()+"')]");
				driver.findElement(zd).click();
			}
			else {
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue());
			}
		}
		driver.findElementById("input-save").click();
		Thread.sleep(1000);
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(1000);
	}
	

	/** 
	 * @Title: inputValueByVariableType_Sf_inputValue02
	 * @Description: 随访中添加数据，根据变量类型，输入值（只需传入driver以及list，根据数据库中配置，录入固定的数据inputValue02）
	 * @param: @param driver
	 * @param: @param list
	 * @param: @throws Exception :
	 * @return: void
	 * @throws 
	 */
	public static void inputValueByVariableType_Sf_inputValue02(PhantomJSDriver driver,List<CrfTemplateAnzhen> list) throws Exception{
		for (int i = 0; i < list.size(); i++) {
			logger.info(list.get(i).getChineseName());
			
			if ("枚举型".contains(list.get(i).getVariableType())) {
				new Select(driver.findElementById(list.get(i).getIdXpath())).selectByValue(list.get(i).getInputValue02());
			}
			else if ("多选".contains(list.get(i).getVariableType())) {
				driver.findElementByXPath(list.get(i).getIdXpath()).click();
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					driver.findElementByXPath(list.get(i).getInputValue02()+j+"]/a/label").click();
				}
			}
			else if ("多选_联动".contains(list.get(i).getVariableType())) {
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					driver.findElementByXPath(list.get(i).getIdXpath()).click();
					driver.findElementByXPath(list.get(i).getInputValue02()+j+"]/a/label").click();
				}
			}
			else if ("日期型".contains(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).click();
				Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
				selYear.selectByValue("2018"); 
				Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
				selMouth.selectByValue("0"); 
				driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
			}
			else if ("图片型".contains(list.get(i).getVariableType())){
				//driver.findElementById(list.get(i).getIdXpath()).click();
				
				
			}
			else if ("字符串_诊断".equals(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue02());
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue02());
				Thread.sleep(5000);
				By zd = new By.ByXPath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//span[contains(text(),'"+list.get(i).getInputValue02()+"')]");
				driver.findElement(zd).click();
			}
			else {
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue02());
			}
		}
		
		driver.findElementById("input-save").click();
		Thread.sleep(1000);
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(1000);
	}
	

	/** 
	 * @Title: inputValueByVariableType_Sf_inputValue03
	 * @Description: 随访中添加数据，根据变量类型，输入值（只需传入driver以及list，根据数据库中配置，录入固定的数据inputValue03）
	 * @param: @param driver
	 * @param: @param list
	 * @param: @throws Exception :
	 * @return: void
	 * @throws 
	 */
	public static void inputValueByVariableType_Sf_inputValue03(PhantomJSDriver driver,List<CrfTemplateAnzhen> list) throws Exception{
		for (int i = 0; i < list.size(); i++) {
			logger.info(list.get(i).getChineseName());
			if ("枚举型".contains(list.get(i).getVariableType())) {
				new Select(driver.findElementById(list.get(i).getIdXpath())).selectByValue(list.get(i).getInputValue03());
			}
			else if ("多选".contains(list.get(i).getVariableType())) {
				//for checkBox
				driver.findElementByXPath(list.get(i).getIdXpath()).click();
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					driver.findElementByXPath(list.get(i).getInputValue03()+j+"]/a/label").click();
				}
			}
			else if ("多选_联动".contains(list.get(i).getVariableType())) {
				//for checkBox
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					driver.findElementByXPath(list.get(i).getIdXpath()).click();
					driver.findElementByXPath(list.get(i).getInputValue03()+j+"]/a/label").click();
				}
			}
			else if ("日期型".contains(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).click();
				Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
				selYear.selectByValue("2018"); 
				Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
				selMouth.selectByValue("0"); 
				driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
			}
			else if ("图片型".contains(list.get(i).getVariableType())){
				//driver.findElementById(list.get(i).getIdXpath()).click();
				
				
			}
			else if ("字符串_诊断".equals(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue03());
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue03());
				Thread.sleep(5000);
				By zd = new By.ByXPath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//span[contains(text(),'"+list.get(i).getInputValue03()+"')]");
				driver.findElement(zd).click();
			}
			else {
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue03());
			}
		}
		driver.findElementById("input-save").click();
		Thread.sleep(1000);
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(1000);
	}

}

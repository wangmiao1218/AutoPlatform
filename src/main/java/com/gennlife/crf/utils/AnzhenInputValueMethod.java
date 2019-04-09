package com.gennlife.crf.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

import com.gennlife.crf.bean.CrfTemplateAnzhen;

/**
 * @Description: 安贞环境，录入固定数据的通用的方法
 * @author: wangmiao
 * @Date: 2017年7月28日 下午4:58:39 
 */
public class AnzhenInputValueMethod {

	
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
		// 循环list
		for (int i = 0; i < list.size(); i++) {
			//测试有问题的字段时使用
			System.out.println(list.get(i).getChineseName());
			
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
				//driver.findElementByXPath(list.get(i).getIdXpath()).click();
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					//多选联动时，需要放到内循环里面
					driver.findElementByXPath(list.get(i).getIdXpath()).click();
					driver.findElementByXPath(list.get(i).getInputValue()+j+"]/a/label").click();
				}
			}
			else if ("日期型".contains(list.get(i).getVariableType()) && "单点击".contains(list.get(i).getDateFormat())){
				driver.findElementById(list.get(i).getIdXpath()).click();
			}
			else if ("日期型".contains(list.get(i).getVariableType()) && "时分".contains(list.get(i).getDateFormat())){
				driver.findElementById(list.get(i).getIdXpath()).click();
				//现在时间按钮的xpath
				driver.findElementByXPath(".//*[@id='ui-datepicker-div']/div[3]/button[1]").click();
			}
			else if ("图片型".contains(list.get(i).getVariableType())){
				//driver.findElementById(list.get(i).getIdXpath()).click();
				
				
			}
			//******处理有输入提示的输入框
			else if ("本次主要出院诊断".contains(list.get(i).getChineseName())){
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue());
				//需等待，否则提示框还没出现
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
		// 循环list
		for (int i = 0; i < list.size(); i++) {
			//测试有问题的字段时使用
			System.out.println(list.get(i).getChineseName());
			
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
					//多选联动时，需要放到内循环里面
					driver.findElementByXPath(list.get(i).getIdXpath()).click();
					driver.findElementByXPath(list.get(i).getInputValue()+j+"]/a/label").click();
				}
			}
			else if ("日期型".contains(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).click();
				//固定时间，防止报错
				//定义年：2018
				Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
				selYear.selectByValue("2018"); 
				//定义月：1月
				Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
				selMouth.selectByValue("0"); 
				//定义日：1号
				driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
			
				//driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[3]/a").click();
			}
			else if ("图片型".contains(list.get(i).getVariableType())){
				//driver.findElementById(list.get(i).getIdXpath()).click();
				
				
			}
			//******处理有输入提示的输入框
			else if ("字符串_诊断".equals(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue());
				//第一次输入可能会没有下拉提示
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue());
				//需等待，否则提示框还没出现
				Thread.sleep(5000);
				By zd = new By.ByXPath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//span[contains(text(),'"+list.get(i).getInputValue()+"')]");
				driver.findElement(zd).click();
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
		// 循环list
		for (int i = 0; i < list.size(); i++) {
			//测试有问题的字段时使用
			System.out.println(list.get(i).getChineseName());
			
			if ("枚举型".contains(list.get(i).getVariableType())) {
				new Select(driver.findElementById(list.get(i).getIdXpath())).selectByValue(list.get(i).getInputValue02());
			}
			else if ("多选".contains(list.get(i).getVariableType())) {
				//for checkBox
				driver.findElementByXPath(list.get(i).getIdXpath()).click();
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					driver.findElementByXPath(list.get(i).getInputValue02()+j+"]/a/label").click();
				}
			}
			else if ("多选_联动".contains(list.get(i).getVariableType())) {
				//for checkBox
				for (int j = 1; j < Integer.parseInt(list.get(i).getDateFormat()); j++) {
					//多选联动时，需要放到内循环里面
					driver.findElementByXPath(list.get(i).getIdXpath()).click();
					driver.findElementByXPath(list.get(i).getInputValue02()+j+"]/a/label").click();
				}
			}
			else if ("日期型".contains(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).click();
				//固定时间，防止报错
				//定义年：2018
				Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
				selYear.selectByValue("2018"); 
				//定义月：1月
				Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
				selMouth.selectByValue("0"); 
				//定义日：1号
				driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
			
				//driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[3]/a").click();
			}
			else if ("图片型".contains(list.get(i).getVariableType())){
				//driver.findElementById(list.get(i).getIdXpath()).click();
				
				
			}
			//******处理有输入提示的输入框
			else if ("字符串_诊断".equals(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue02());
				//第一次输入可能会没有下拉提示
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue02());
				//需等待，否则提示框还没出现
				Thread.sleep(5000);
				By zd = new By.ByXPath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//span[contains(text(),'"+list.get(i).getInputValue02()+"')]");
				driver.findElement(zd).click();
			}
			else {
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue02());
			}
		}
		
		//循环后保存
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
		// 循环list
		for (int i = 0; i < list.size(); i++) {
			//测试有问题的字段时使用
			System.out.println(list.get(i).getChineseName());
			
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
					//多选联动时，需要放到内循环里面
					driver.findElementByXPath(list.get(i).getIdXpath()).click();
					driver.findElementByXPath(list.get(i).getInputValue03()+j+"]/a/label").click();
				}
			}
			else if ("日期型".contains(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).click();
				//固定时间，防止报错
				//定义年：2018
				Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
				selYear.selectByValue("2018"); 
				//定义月：1月
				Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
				selMouth.selectByValue("0"); 
				//定义日：1号
				driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
			
				//driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[3]/a").click();
			}
			else if ("图片型".contains(list.get(i).getVariableType())){
				//driver.findElementById(list.get(i).getIdXpath()).click();
				
				
			}
			//******处理有输入提示的输入框
			else if ("字符串_诊断".equals(list.get(i).getVariableType())){
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue03());
				//第一次输入可能会没有下拉提示
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue03());
				//需等待，否则提示框还没出现
				Thread.sleep(5000);
				By zd = new By.ByXPath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//span[contains(text(),'"+list.get(i).getInputValue03()+"')]");
				driver.findElement(zd).click();
			}
			else {
				driver.findElementById(list.get(i).getIdXpath()).clear();
				driver.findElementById(list.get(i).getIdXpath()).sendKeys(list.get(i).getInputValue03());
			}
		}
		
		//循环后保存
		driver.findElementById("input-save").click();
		Thread.sleep(1000);
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(1000);
	}
	
	
	

	/** 
	* @Title: test????????????失败
	* @Description: 根据变量类型，输入值（只需传入driver、左侧点击的路径，以及list，根据数据库中配置，录入固定的数据）
	* @param: @param driver
	* @param: @param idXpath 前台点击的左侧路径
	* @param: @param list
	* @param: @throws Exception :
	* @return: void
	* @throws 
	*/
	public static void test(PhantomJSDriver driver,String idXpath,List<CrfTemplateAnzhen> list) throws Exception{
		driver.findElementById(idXpath).click();
		Thread.sleep(3000);
		
		// 循环list
		for (int i = 0; i < list.size(); i++) {
			//测试有问题的字段时使用
			//System.out.println(list.get(i).getChineseName());
			
			if ("图片型".contains(list.get(i).getVariableType())){
				System.out.println(list.get(i).getChineseName());
				driver.findElementByXPath(list.get(i).getIdXpath()).click();
				Thread.sleep(6000);
				Runtime.getRuntime().exec("C:\\Users\\www\\Desktop\\file.exe");
				//Runtime.getRuntime().exec("D:\\AutoIt3\\AutoIt3.exe C:\\Users\\www\\Desktop\\file.au3");
				Thread.sleep(6000);
				System.out.println("ok");
			}
		}
		
		//循环后保存
		driver.findElementById("input-save").click();
		Thread.sleep(1000);
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(1000);
	}

	
}

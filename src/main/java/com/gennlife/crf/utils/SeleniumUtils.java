package com.gennlife.crf.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @Description: selenium相关工具方法
 * @author: wangmiao
 * @Date: 2017年9月19日 上午10:43:18 
 */
public class SeleniumUtils {
	
	/** 
	* @Title: isElementPresent 
	* @Description: 判断页面元素是否存在
	* @param: @param driver
	* @param: @param idXpath  
	* @return: Boolean 返回布尔类型
	* @throws 
	*/
	public static Boolean isElementPresent(PhantomJSDriver driver, String idXpath) {
		Boolean status = false;
		try {
			driver.findElementById(idXpath);
			status = true;
		//注意坑：是否为org.openqa.selenium.NoSuchElementException
		} catch (org.openqa.selenium.NoSuchElementException e) {
			status = false;
			//我以为是这个新打开的页面加载的时间太慢了，selenium 执行太快，所以检测不到这个页面上的一些元素
			//或者页面缺少id属性
		} catch (org.openqa.selenium.NoSuchWindowException e) {
			status = false;
		}
		return status;
	}
	
	/** 
	 * @Title: isSelectByValuePresent 
	 * @Description: 判断页面下拉框是否能选择对应的选项
	 * @param: @param driver
	 * @param: @param idXpath  
	 * @return: Boolean 返回布尔类型
	 * @throws 
	 */
	public static Boolean isSelectByValuePresent(PhantomJSDriver driver, String idXpath, String selectValue) {
		Boolean status = false;
		try {
			new Select(driver.findElementById(idXpath)).selectByValue(selectValue);
			status = true;
		} catch (Exception e) {
			status = false;
		}
		return status;
	}
	
	
}

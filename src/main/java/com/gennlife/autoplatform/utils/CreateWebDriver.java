package com.gennlife.autoplatform.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * @Description: 创建WebDriver，使用chrome浏览器
 * @author: wangmiao
 * @Date: 2017年6月9日 下午2:12:39 
 */
public class CreateWebDriver {
	
	/** 
	* @Title: createWebDriver 
	* @Description: 获取WebDriver（页面可见）
	* @return: WebDriver
	* @throws 
	*/
	public static WebDriver createChromeWebDriver(){
		// 指定驱动路径
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver2.25\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	/** 
	* @Title: createWebDriverByPhantomJSDriver 
	* @Description: 通过PhantomJSDriver方法获得driver（页面不可见）
	* @return: PhantomJSDriver
	* @throws 
	*/
	public static PhantomJSDriver createWebDriverByPhantomJSDriver(){
		// 指定驱动路径
		System.setProperty("phantomjs.binary.path","D:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();
		desiredCapabilities.setJavascriptEnabled(true); 
		PhantomJSDriver driver = new PhantomJSDriver(desiredCapabilities);
		
		//PhantomJSDriver driver = new PhantomJSDriver();
		
		return driver;
	}
	

}

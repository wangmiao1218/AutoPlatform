package com.gennlife.test;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;


public class TestSelenium {

	@Test
	public void test(){
		System.out.println((int)((Math.random()*9+1)*1000));  
	}
	
	@Test
	public void loginAndToAddByPhantomJSDriver(){
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String string = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		System.out.println(string);
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
	}
	@Test
	public void loginByPhantomJSDriver(){
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String string = LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		System.out.println(string);
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void loginAndToAddByChromeWebDriver(){
		WebDriver driver = CreateWebDriver.createChromeWebDriver();
		String string = LoginCrfOfAnzhen.loginAndToAddByChromeWebDriver(driver);
		System.out.println(string);
		QuitWebDriver.quitWebDriver(driver);
		
	}
	
	
	@Test
	public void loginByChromeWebDriver(){
		WebDriver driver = CreateWebDriver.createChromeWebDriver();
		String string = LoginCrfOfAnzhen.loginByChromeWebDriver(driver);
		System.out.println(string);
		QuitWebDriver.quitWebDriver(driver);
		
	}
	
	@Test
	public void quitWebDriver(){
		WebDriver driver = CreateWebDriver.createChromeWebDriver();
		QuitWebDriver.quitWebDriver(driver);
		
		PhantomJSDriver driver2 = CreateWebDriver.createWebDriverByPhantomJSDriver();
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver2);
		
		
	}
	
	
}
package com.gennlife.crf.anzhen.add.gaoxueya.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.anzhen.add.gaoxueya.Anzhen_Hzxx_AddGrbs;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginCrfOfAnzhen;
import com.gennlife.crf.utils.QuitWebDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestAnzhen_Hzxx_AddGrbs {

	//添加完基本信息后，单病种页面显示的病人编号
	//public static final String xpath = "/html/body/div/div[3]/div/div[3]/table/tbody/tr[7]/td[2]/a";
	public static final String xpath = "//*[@id='case-list-container']/tbody/tr[4]/td[2]/a";
	
	@Test
	public void hzxx_AddGrbs_Gxyb() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		Thread.sleep(3000);
		Anzhen_Hzxx_AddGrbs.hzxx_AddGrbs_Gxyb(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void hzxx_AddGrbs_Tdxyc() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		Thread.sleep(3000);
		Anzhen_Hzxx_AddGrbs.hzxx_AddGrbs_Tdxyc(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void hzxx_AddGrbs_Xzyc() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		Thread.sleep(5000);
		Anzhen_Hzxx_AddGrbs.hzxx_AddGrbs_Xzyc(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void hzxx_AddGrbs_Gxb() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		Thread.sleep(5000);
		Anzhen_Hzxx_AddGrbs.hzxx_AddGrbs_Gxb(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void hzxx_AddGrbs_Nxgb() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		Thread.sleep(5000);
		Anzhen_Hzxx_AddGrbs.hzxx_AddGrbs_Nxgb(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void hzxx_AddGrbs_FcAndMxxsAndMxszjb() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		Thread.sleep(5000);
		Anzhen_Hzxx_AddGrbs.hzxx_AddGrbs_FcAndMxxsAndMxszjb(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void hzxx_AddGrbs_Yymqzd() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		Thread.sleep(5000);
		Anzhen_Hzxx_AddGrbs.hzxx_AddGrbs_Yymqzd(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	
}
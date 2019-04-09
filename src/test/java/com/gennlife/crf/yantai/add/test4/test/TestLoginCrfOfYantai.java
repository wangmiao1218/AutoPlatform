package com.gennlife.crf.yantai.add.test4.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginCrfOfYantai;
import com.gennlife.crf.utils.QuitWebDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestLoginCrfOfYantai {
	
	@Test
	public void loginAndToDanbingzhongByPhantomJSDriver() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		System.out.println(value);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	
	@Test
	public void loginAndToAddBasicInfoByPhantomJSDriver() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToAddBasicInfoByPhantomJSDriver(driver);
		
		System.out.println(value);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	
}
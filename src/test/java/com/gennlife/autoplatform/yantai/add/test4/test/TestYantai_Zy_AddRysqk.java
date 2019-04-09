package com.gennlife.autoplatform.yantai.add.test4.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrfOfYantai;
import com.gennlife.autoplatform.utils.QuitWebDriver;
import com.gennlife.autoplatform.yantai.add.test4.Yantai_Zy_AddRysqk;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestYantai_Zy_AddRysqk {
	
	//添加完基本信息后，单病种页面显示的病人编号
	//4fc795c3b65a7adb7fdb
	public static final String xpath = ".//*[@id='case-list-container']/tbody/tr[9]/td[2]/a";
		
	@Test
	public void zy_AddRysqk() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		//
		Yantai_Zy_AddRysqk.zy_AddRysqk(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	
}
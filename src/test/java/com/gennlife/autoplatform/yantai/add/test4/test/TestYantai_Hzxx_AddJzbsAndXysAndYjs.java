package com.gennlife.autoplatform.yantai.add.test4.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestYantai_Hzxx_AddJzbsAndXysAndYjs {
	
	//添加完基本信息后，单病种页面显示的病人编号
	//4fc795c3b65a7adb7fdb
	public static final String xpath = ".//*[@id='case-list-container']/tbody/tr[9]/td[2]/a";
	/*	
	@Test
	public void hzxx_AddJzbsAndXysAndYjs() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		//
		Yantai_Hzxx_AddJzbsAndXysAndYjs.hzxx_AddJzbsAndXysAndYjs(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}*/
	
	
}
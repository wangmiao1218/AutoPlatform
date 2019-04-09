package com.gennlife.autoplatform.yantai.add.test4.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestYantai_Mjz_AddCysqk {
	
	//添加完基本信息后，单病种页面显示的病人编号
	public static final String xpath = ".//*[@id='case-list-container']/tbody/tr[4]/td[2]/a";
		
	/*@Test
	public void zy_AddCysqk() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		//
		Yantai_Mjz_AddCysqk.mjz_AddCysqk(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	*/
	
}
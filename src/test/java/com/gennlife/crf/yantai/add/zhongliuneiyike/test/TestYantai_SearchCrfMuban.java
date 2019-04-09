package com.gennlife.crf.yantai.add.zhongliuneiyike.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginCrfOfYantai;
import com.gennlife.crf.utils.QuitWebDriver;
import com.gennlife.crf.yantai.add.zhongliuneiyike.Yantai_SearchCrfMuban;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestYantai_SearchCrfMuban {

	@Test
	public void SearchCrfMuban_Level1_List() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		List<String> list = Yantai_SearchCrfMuban.SearchCrfMuban_Level1_List(driver, value);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void SearchCrfMuban_Level1and2_List() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		List<Map<String,List<String>>> list = Yantai_SearchCrfMuban.SearchCrfMuban_Level1and2_List(driver, value);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void SearchCrfMuban_Level1and2_Json() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		String string = Yantai_SearchCrfMuban.SearchCrfMuban_Level1and2_Json(driver, value);
		System.out.println(string);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void SearchCrfMuban_Hzxx_Level1and2and3_List() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		List<Map<String,List<Map<String,List<String>>>>> list = Yantai_SearchCrfMuban.SearchCrfMuban_Hzxx_Level1and2and3_List(driver, value);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void SearchCrfMuban_Hzxx_Level1and2and3_Json() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		String string = Yantai_SearchCrfMuban.SearchCrfMuban_Hzxx_Level1and2and3_Json(driver, value);
		System.out.println(string);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void SearchCrfMuban_Zy_Level1and2and3_Json() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		String string = Yantai_SearchCrfMuban.SearchCrfMuban_Zy_Level1and2and3_Json(driver, value);
		System.out.println(string);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void SearchCrfMuban_Mjz_Level1and2and3_Json() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		String string = Yantai_SearchCrfMuban.SearchCrfMuban_Mjz_Level1and2and3_Json(driver, value);
		System.out.println(string);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
}
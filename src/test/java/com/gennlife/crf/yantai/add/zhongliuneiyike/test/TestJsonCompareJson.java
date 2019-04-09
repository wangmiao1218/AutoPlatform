package com.gennlife.crf.yantai.add.zhongliuneiyike.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.JsonUtil_bak;
import com.gennlife.crf.utils.LoginCrfOfYantai;
import com.gennlife.crf.utils.QuitWebDriver;
import com.gennlife.crf.yantai.add.zhongliuneiyike.Yantai_SearchCrfMuban;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestJsonCompareJson {

	@Test
	public void JsonCompareJson() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		String string = Yantai_SearchCrfMuban.SearchCrfMuban_Hzxx_Level1and2and3_Json(driver, value);
		//String string2 = SearchCrfMuban.SearchCrfMuban_Hzxx_Level1and2and3_Json(driver, value);
		String string2 = Yantai_SearchCrfMuban.SearchCrfMuban_Level1and2_Json(driver, value);
		
		boolean b = JsonUtil_bak.JsonCompareJson(string, string2);
		System.out.println(b);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	
}
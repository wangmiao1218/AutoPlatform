package com.gennlife.crf.yantai.add.zhongliuneiyike.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginCrfOfYantai;
import com.gennlife.crf.utils.QuitWebDriver;
import com.gennlife.crf.yantai.add.zhongliuneiyike.Yantai_Hzxx_AddHzjbxx;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestYantai_Hzxx_AddHzjbxx {

	@Test
	public void hzxx_AddHzjbxx() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToAddBasicInfoByPhantomJSDriver(driver);
		
		String text = Yantai_Hzxx_AddHzjbxx.hzxx_AddHzjbxx(driver, value);
		System.out.println(text);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	
}
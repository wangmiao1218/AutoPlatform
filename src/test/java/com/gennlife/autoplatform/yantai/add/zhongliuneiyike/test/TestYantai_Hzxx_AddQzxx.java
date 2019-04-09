package com.gennlife.autoplatform.yantai.add.zhongliuneiyike.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.autoplatform.anzhen.add.gaoxueya.Anzhen_Hzxx_AddGrbs;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.LoginCrfOfYantai;
import com.gennlife.autoplatform.utils.QuitWebDriver;
import com.gennlife.autoplatform.yantai.add.zhongliuneiyike.Yantai_Hzxx_AddHzjbxx;
import com.gennlife.autoplatform.yantai.add.zhongliuneiyike.Yantai_Hzxx_AddQzxx;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestYantai_Hzxx_AddQzxx {
	
	//添加完基本信息后，单病种页面显示的病人编号
	//44a68a74c396ea7f9cb8
	public static final String xpath = "/html/body/div/div[3]/div/div[3]/table/tbody/tr[5]/td[2]/a";
		
	@Test
	public void hzxx_AddQzxx() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		Thread.sleep(2000);
		//找到xpath（最后一页）(???????)
		driver.findElementByXPath("/html/body/div/div[3]/div/div[4]/div[1]/div/div/input").sendKeys("81");
		driver.findElementByXPath("/html/body/div/div[3]/div/div[4]/div[1]/div/div/button").click();
		
		Yantai_Hzxx_AddQzxx.hzxx_AddQzxx(driver, xpath);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	
}
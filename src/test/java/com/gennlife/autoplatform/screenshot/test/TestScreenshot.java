package com.gennlife.autoplatform.screenshot.test;

import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.gennlife.autoplatform.screenshot.Screenshot;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrf;
import com.gennlife.autoplatform.utils.QuitWebDriver;

public class TestScreenshot {

	// 添加完基本信息后，单病种页面显示的病人编号
	public static final String xpath = "";

	@Test
	public void screenshot() throws Exception {
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrf.loginAndToDanbingzhongByPhantomJSDriver(driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Screenshot.screenshot(driver);
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}

	
}
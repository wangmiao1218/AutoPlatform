package com.gennlife.crf.screenshot.test;

import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import com.gennlife.crf.screenshot.Screenshot;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginCrf;
import com.gennlife.crf.utils.QuitWebDriver;

public class TestScreenshot {

	// 添加完基本信息后，单病种页面显示的病人编号
	public static final String xpath = "";

	@Test
	public void screenshot() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrf.loginAndToDanbingzhongByPhantomJSDriver(driver);
		//LoginCrf.loginAndToBasicInfoByPhantomJSDriver(driver);

		//到指定元素界面
		//driver.findElementByXPath("html/body/div/div[3]/div/div[3]/table/tbody/tr[1]/td[2]/a").click();;
		//driver.findElementByXPath("//*[@id='crf-data-map']/div[2]/div[1]/img").click();
		// 等待
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Screenshot.screenshot(driver);
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}

	
}
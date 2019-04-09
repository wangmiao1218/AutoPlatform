package com.gennlife.crf.screenshot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @Description: 江苏环境：添加患者信息_既往史
 * @author: wangmiao
 * @Date: 2017年7月6日 下午2:15:26
 */
public class Screenshot {

	/** 
	* @Title: screenshot 
	* @Description: 截取屏幕
	* @author: wangmiao
	* @Date: 2018年6月29日 下午2:28:20 
	* @param: @param driver
	* @param: @return
	* @return: String
	* @throws 
	*/
	public static String screenshot(PhantomJSDriver driver){
		driver.manage().window().maximize();// 浏览器窗口最大化 
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 截图到output
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String savePath = "F:\\screenshot.png";
		// 复制内容到指定文件中
		try {
			FileUtils.copyFile(scrFile, new File(savePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "ok";
	}
	

}

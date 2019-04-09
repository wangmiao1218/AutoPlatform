package com.gennlife.autoplatform.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @Description: 登录crf系统相关方法
 * @author: wangmiao
 * @Date: 2018年3月5日 上午11:34:14 
 */
public class LoginCrf {
	
	public static final String loginName ="3333";
	public static final String pwd ="333333";
	public static final String danbingzhongUrl="http://10.0.2.184/uranus/crf_case.html";
	
	public static final String xpath ="//*[@id='case-list-container']/tbody/tr[1]/td[2]/a";
	
	/**
	 * @Title: loginAndToDanbingzhongByPhantomJSDriver
	 * @Description: 仅登录,通过PhantomJSDriver，无页面显示的方式,到单病种页面
	 * @param: PhantomJSDriver driver :传入PhantomJSDriver
	 * @return: String： 返回，登陆成功返回“登陆成功”
	 * @throws
	 */
	public static String loginAndToDanbingzhongByPhantomJSDriver(PhantomJSDriver driver) {
		driver.get(danbingzhongUrl);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElementById("loginName").clear();
		driver.findElementById("loginName").sendKeys(loginName);
		driver.findElementById("pwd").clear();
		driver.findElementById("pwd").sendKeys(pwd);
		driver.findElementById("login").click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String returnString=null;
		String text = driver.findElementByXPath(".//*[@id='action-container']/div[1]/button[1]").getText();
		if ("添加".equals(text)) {
			returnString = "登陆成功";
		}
		return returnString;
	}

	
	/**
	 * @Title: loginAndToAddBasicInfoByPhantomJSDriver
	 * @Description: 登录并到病例详情页面,患者信息，通过PhantomJSDriver，无页面显示的方式
	 * @param: PhantomJSDriver driver :传入driver
	 * @return: String：返回，登录并到添加页面，成功返回“添加页面”
	 * @throws
	 */
	public static String loginAndToBasicInfoByPhantomJSDriver(PhantomJSDriver driver) {
		String returnString = loginAndToDanbingzhongByPhantomJSDriver(driver);
		if ("登陆成功".equals(returnString)) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			driver.findElementByXPath(xpath).click();;
			Set<String> winHandels = driver.getWindowHandles();
			List<String> it = new ArrayList<String>(winHandels);
			driver.switchTo().window(it.get(1));
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String value = null;
		String text = driver.findElementById("input-save").getText();
		if ("保存".equals(text)) {
			value = "溯源页面";
		}
		return value;
	}
	
}

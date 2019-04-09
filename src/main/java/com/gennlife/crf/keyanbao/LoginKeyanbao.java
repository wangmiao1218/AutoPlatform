package com.gennlife.crf.keyanbao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @Description: 科研宝登录方法
 * @author: wangmiao
 * @Date: 2017年8月23日 上午9:22:16 
 */
public class LoginKeyanbao {
	public static final String keyanbaoUrl="https://crabyter.sinyoo.net/";

	public static final String loginName ="15960237795";
	public static final String pwd ="liaodoctor";
	
	/**
	 * @Title: loginByPhantomJSDriver
	 * @Description: 仅登录,通过PhantomJSDriver，无页面显示的方式，登录科研宝
	 * @param: PhantomJSDriver driver :传入PhantomJSDriver
	 * @return: String： 返回，登陆成功返回“登陆成功”
	 * @throws
	 */
	public static String loginByPhantomJSDriver(PhantomJSDriver driver) {
		driver.get(keyanbaoUrl);

		// 等待
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 登录
		driver.findElementById("Username").clear();
		driver.findElementById("Username").sendKeys(loginName);
		driver.findElementById("pwd").clear();
		driver.findElementById("pwd").sendKeys(pwd);

		driver.findElementByXPath(".//*[@id='sign-in']/div/div[3]/div/span[1]").click();
		
		// 等待
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String returnValue = null;
		// 获取title
		String title = driver.getTitle();
		System.out.println(title);
		if ("科研管理 - Crabyter肿瘤科研数据管理系统".equals(title)) {
			returnValue = "登陆成功";
		}
		
		return returnValue;
	}

	
	/**
	 * @Title: loginAndToAddByPhantomJSDriver
	 * @Description: 登录科研宝，并到"病例录入向导 - 向导式录入乳腺癌数据库 - Crabyter肿瘤科研数据管理系统"
	 * @param: PhantomJSDriver driver :传入driver
	 * @return: String：返回，登录并到添加页面，成功返回“添加页面”
	 * @throws
	 */
	public static String loginAndToAddByPhantomJSDriver(PhantomJSDriver driver) {
		String returnString = loginByPhantomJSDriver(driver);

		if ("登陆成功".equals(returnString)) {
			// 向导式录入乳腺癌数据库
			driver.findElementByXPath(".//*[@id='studylist']/div[3]/div[3]/a/span").click();
			//病历管理
			driver.findElementByXPath(".//*[@id='jobsdeskleftmenu']/div/div[3]/div[1]/span[1]").click();
			//病例列表
			driver.findElementByXPath(".//*[@id='jobsdeskleftmenu']/div/div[3]/div[2]/div/a").click();
						
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 新增
			driver.findElementById("btnadd").click();
			
			// 得到当前窗口的set集合
			Set<String> winHandels = driver.getWindowHandles();
			// 将set集合存入list对象
			List<String> it = new ArrayList<String>(winHandels);
			// 切换到弹出的新窗口
			driver.switchTo().window(it.get(1));
			
			driver.findElementById("btnnew").click();
			driver.findElementByXPath(".//*[@id='layui-layer1']/div[2]/ul/li[1]").click();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String returnValue = null;
		// 获取title
		String title = driver.getTitle();
		System.out.println(title);
		if ("病例录入向导 - 向导式录入乳腺癌数据库 - Crabyter肿瘤科研数据管理系统".equals(title)) {
			returnValue = "添加页面";
		}
		
		return returnValue;
	}
	
	
}

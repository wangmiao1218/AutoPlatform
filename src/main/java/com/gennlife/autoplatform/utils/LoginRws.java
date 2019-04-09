package com.gennlife.autoplatform.utils;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @Description: 登录rws系统相关方法（rws定时程序使用，动态传用户与密码）
 * @author: wangmiao
 * @Date: 2018年3月14日 下午2:35:41 
 */
public class LoginRws {
	
	//public static final String rwsUrl="http://10.0.2.162/uranus/project_index.html";
	//public static final String firstProjectXpath="//*[@id='item-list-container']/tbody/tr[1]/td[2]/a";
	//public static final String projectId="rws_defined.html";
	//public static final String firstIndexXpath=" //*[@id='app']/div/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td[2]/a";
	//public static final String indexToSaveButtonXpath="//*[@id='app']/div/div/div[2]/div/div[2]/button[2]";
	//public static final String eventToSaveButtonXpath=" //*[@id='app']/div/div/div[2]/div/button[2]";
	
	/** 
	* @Title: loginAndToRwsByPhantomJSDriver 
	* @Description:  仅登录,通过PhantomJSDriver，无页面显示的方式,到rws我的项目页面
	* @param: @param driver
	* @param: @param rwsUrl
	* @param: @param loginName
	* @param: @param pwd
	* @param: @return :
	* @return: String
	* @throws 
	*/
	public static String loginAndToRwsByPhantomJSDriver(PhantomJSDriver driver,String rwsUrl,
			String loginName,String pwd) {
		driver.get(rwsUrl);
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
		//String text = driver.findElementByClassName("u-btn u-new").getText();
		//坑：WerbDriver中by.className() 不支持含有空格的class名称。采用通配符：
		String text = driver.findElementByClassName("u-new").getText();
		if ("新建项目".equals(text)) {
			returnString = "登陆成功";
		}
		return returnString;
	}

}

package com.gennlife.rws;

import java.util.Date;
import java.util.concurrent.Callable;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginRws;
import com.gennlife.autoplatform.utils.QuitWebDriver;

/**
 * @Description: 执行rws的计算相关方法（rws定时程序使用，动态传url地址、用户与密码）
 * @author: wangmiao
 * @Date: 2018年3月14日 下午2:35:41 
 */
public class RwsTask {
	
	private static final Logger logger = LoggerFactory.getLogger(RwsTask.class);
	
	public static final String firstProjectXpath="//*[@id='item-list-container']/tbody/tr[1]/td[2]/a";
	public static final String projectId="rws_defined.html";
	public static final String firstIndexXpath=" //*[@id='app']/div/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td[2]/a";
	public static final String indexToSaveButtonXpath="//*[@id='model']/div[2]/div/div[2]/button[2]";
	public static final String firstConditionDatepickerInputXpath="html/body/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div[2]/div/div/div/div[3]/div/input";
	public static final String presentTimeButtonXpath="html/body/div[3]/div[3]/button[1]";
	
	
	/** 
	* @Title: createRwsTask 
	* @Description: 创建rws定时任务：登录并到列表中第一个项目，且到列表中第一个指标的修改页面，并进行修改。
	* 				修改指标：为写死的 修改第一个当前时间，这样保证进行最新的计算
	* @param: @param driver
	* @param: @param rwsUrl 登录地址
	* @param: @param loginName  用户名
	* @param: @param pwd 密码
	* @return: String：返回，登录并到添加页面，成功返回“保存成功”,否则是“error”
	* @throws 
	*/
	public static String createRwsTask(final PhantomJSDriver driver,
			final String rwsUrl,final String loginName,final String pwd) {
		String returnString=null;
		String loginValue = LoginRws.loginAndToRwsByPhantomJSDriver(driver, rwsUrl, loginName, pwd);
		if ("登陆成功".equals(loginValue)) {
			logger.info("login success");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.findElementByXPath(firstProjectXpath).click();
			driver.findElementById(projectId).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("To firstProject success");
			driver.findElementByXPath(firstIndexXpath).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("To firstIndex success");
			String text = driver.findElementByXPath(indexToSaveButtonXpath).getText();
			if ("保存指标".equals(text)) {
				driver.findElementByXPath(firstConditionDatepickerInputXpath).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				driver.findElementByXPath(presentTimeButtonXpath).click();
				driver.findElementByXPath(indexToSaveButtonXpath).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String value = driver.findElementByClassName("u-modal-alert-text").getText();
				if ("保存成功".equals(value)) {
					returnString = "保存成功";
				}
			}
		}else {
			returnString="error";
		}
		return returnString;
	}
	
	/** 
	* @Title: CreateRwsCallable 
	* @Description: 创建rws的Callable，返回Callable实例，便于多线程调用
	* @param: @param rwsUrl
	* @param: @param loginName
	* @param: @param pwd
	* @return: Callable<String> 返回Callable实例
	* @throws 
	*/
	public static Callable<String> CreateRwsCallable(final String rwsUrl,
			final String loginName,final String pwd) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
				String value = RwsTask.createRwsTask(driver, rwsUrl,loginName, pwd);
				QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
				if ("保存成功".equals(value)) {
					logger.info(loginName+"_success_"+new Date());
					return "success";
				}else {
					return "error";
				}
			}
		};
	}
	
}

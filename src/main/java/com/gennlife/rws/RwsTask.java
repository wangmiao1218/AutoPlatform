package com.gennlife.rws;

import java.util.Date;
import java.util.concurrent.Callable;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginRws;
import com.gennlife.crf.utils.QuitWebDriver;

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
	//保存指标的按钮的xpath
	public static final String indexToSaveButtonXpath="//*[@id='model']/div[2]/div/div[2]/button[2]";
	//保存事件的按钮的xpath
	//public static final String eventToSaveButtonXpath=" //*[@id='app']/div/div/div[2]/div/button[2]";
	//第一个条件的日期输入框
	public static final String firstConditionDatepickerInputXpath="html/body/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div[2]/div/div/div/div[3]/div/input";
	//日期控件的“现在时间”按钮
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
			
			//到列表中第一个项目界面
			driver.findElementByXPath(firstProjectXpath).click();
			//到列表中第一个指标或事件界面
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
			/*
			 * 页面没有切换，所以不用切换到新的窗口
			// 得到当前窗口的set集合,将set集合存入list对象, 切换到弹出的新窗口
			Set<String> winHandels = driver.getWindowHandles();
			List<String> it = new ArrayList<String>(winHandels);
			driver.switchTo().window(it.get(1));
			*/
			
			// 获取保存指标按钮，若存在则在添加页面
			String text = driver.findElementByXPath(indexToSaveButtonXpath).getText();
			//
			if ("保存指标".equals(text)) {
				//修改指标_修改第一个当前时间，这样保证进行最新的计算
				//点击第一个条件的日期输入框
				driver.findElementByXPath(firstConditionDatepickerInputXpath).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//点击现在时间，更新条件
				driver.findElementByXPath(presentTimeButtonXpath).click();
				//点保存指标
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
				//执行登录并改条件执行计算
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

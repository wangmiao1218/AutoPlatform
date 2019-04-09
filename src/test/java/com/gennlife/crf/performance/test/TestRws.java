package com.gennlife.crf.performance.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.screenshot.Screenshot;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginRws;
import com.gennlife.crf.utils.QuitWebDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestRws {
	
	public static final String rwsUrl = "http://10.0.2.162/uranus/project_index.html";
	public static final String newuiUrl = "http://10.0.2.162/newui/#/22e0b2ca-4bee-4e73-b394-20a682bde354/rws-uql%E6%B5%8B%E8%AF%953%E8%BD%AE/cdebb876-96ac-45b7-ad97-790576a6df27/EMR";
	private static final String loginName = "3333";
	private static final String pwd = "33333333";

	public static final String projectXpath = "//*[@id='item-list-container']/tbody/tr[2]/td[2]/a";
	public static final String indexsXpath = "//*[@id='rws_defined.html']";
	public static final String newuiXpath = "//*[@id='action-container']/div[1]/button";
	
	public static final String indexXpath = "//*[@id='content___7zMqn']/div[1]/div[2]/div[1]/ul[1]/li/ul/li[1]";
	
	public static final String projectId = "rws_defined.html";
	public static final String firstIndexXpath = " //*[@id='app']/div/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td[2]/a";
	
	
	@Test
	public void rwsEvent() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver
				.createWebDriverByPhantomJSDriver();
		String loginValue = LoginRws.loginAndToRwsByPhantomJSDriver(driver,
				rwsUrl, loginName, pwd);
		System.out.println(loginValue);
		if ("登陆成功".equals(loginValue)) {
			System.out.println("ok");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 到列表中第一个项目界面
			driver.findElementByXPath(projectXpath).click();
			// 到自定义指标列表
			driver.findElementByXPath(indexsXpath).click();
			
			//验证页面
			Screenshot.screenshot(driver);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 到newui页面
			driver.findElementByXPath(newuiXpath).click();
			//Screenshot.screenshot(driver);
			
			String current_url = driver.getCurrentUrl();
			System.out.println(current_url);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			driver.get(newuiUrl);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String current_url2 = driver.getCurrentUrl();
			System.out.println(current_url2);
			
			//Screenshot.screenshot(driver);
			
			//报错 页面空白
			//driver.findElementByXPath(indexXpath).click();

			//System.out.println(text);

		}
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}

}
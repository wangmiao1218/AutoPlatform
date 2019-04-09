package com.gennlife.crf.base.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.service.CrfTemplateService;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginCrfOfAnzhen;
import com.gennlife.crf.utils.QuitWebDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestAddInfo {

	@Autowired
	private CrfTemplateService crfTemplateService;

	@Test
	public void testChrome() throws Exception {
		// 登录并到add页面
		WebDriver driver = CreateWebDriver.createChromeWebDriver();
		LoginCrfOfAnzhen.loginByChromeWebDriver(driver);
		
		Thread.sleep(2000);
		
		///html/body/div/div[3]/div/div[3]/table/tbody/tr[1]/td[2]/a
		//driver.findElementByXPath("/html/body/div/div[3]/div/div[3]/table/tbody/tr[1]/td[2]/a").click();
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[3]/table/tbody/tr[1]/td[2]/a")).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(2000);

		// 基本信息
		driver.findElement(By.id("u-crf-PATIENT_ID_NUMBER")).sendKeys("1102301988111106789");
		Thread.sleep(3000);
		
		// 保存
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[3]/button")).click();
		
		// 关闭driver
		QuitWebDriver.quitWebDriver(driver);

	}

	@Test
	public void testPhantomJSDriver() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		//LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		
		LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		
		//修改
		//driver.findElementByXPath("/html/body/div/div[3]/div/div[3]/table/tbody/tr[1]/td[2]/a").click();
		
		/*
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		*/
		
		Thread.sleep(2000);

		// 基本信息
		driver.findElementById("u-crf-PATIENT_ID_NUMBER").clear();
		driver.findElementById("u-crf-PATIENT_ID_NUMBER").sendKeys("1102301988111106789");

		// 调查时间(日期空间div)，直接xpath定位
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div/input").click();
		driver.findElementByXPath("/html/body/div[9]/table/tbody/tr[2]/td[4]/a").click();
		
		//
		Thread.sleep(3000);
		
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div/input").sendKeys("姓名1");

		// 性别
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div/select"))
				.selectByValue("男");
		
		// 出生日期
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div/input").click();
		driver.findElementByXPath("/html/body/div[9]/div/div/select[1]").click();
		driver.findElementByXPath("/html/body/div[9]/div/div/select[1]/option[82]").click();
		driver.findElementByXPath("/html/body/div[9]/table/tbody/tr[2]/td[3]/a").click();
		
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div/input").sendKeys("110230198811110678");
		
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div/input").sendKeys("13088889999");
		
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div/input").sendKeys("13088889999");
		
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[10]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[10]/div/input").sendKeys("联系人姓名");

		// 联系人与患者关系
		new Select(driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[11]/div/select")).selectByValue("本人");

		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[12]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[12]/div/input").sendKeys("13088889999");
		
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[13]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[13]/div/input").sendKeys("通讯地址");

		// 民族、职业、受教育程度、婚姻状况、主要医疗付费方式
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[14]/div/select"))
				.selectByValue("满");
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[15]/div/select"))
				.selectByValue("专业技术人员");
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[16]/div/select"))
				.selectByValue("硕士及以上");
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[17]/div/select"))
				.selectByValue("已婚");
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[18]/div/select"))
				.selectByValue("城镇居民基本医疗保险");
		
		Thread.sleep(5000);
		
		// 保存
		//driver.findElementById("input-save").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();

		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

	}
	@Test
	public void testPhantomJSDriver1() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		
		Thread.sleep(2000);
		
		//获取默认添加的研究序列号
		String text = driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div/input").getAttribute("value");
		System.out.println(text);
		
	//	/html/body/div/div[3]/div/div[3]/table/tbody/tr[5]/td[2]/a
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
	}
}
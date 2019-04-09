package com.gennlife.autoplatform.base.test;

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

import com.gennlife.autoplatform.service.CrfTemplateService;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestAddInfo {

	@Autowired
	private CrfTemplateService crfTemplateService;

	@Test
	public void testChrome() throws Exception {
		WebDriver driver = CreateWebDriver.createChromeWebDriver();
		LoginCrfOfAnzhen.loginByChromeWebDriver(driver);
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[3]/table/tbody/tr[1]/td[2]/a")).click();
		Set<String> winHandels = driver.getWindowHandles();
		List<String> it = new ArrayList<String>(winHandels);
		driver.switchTo().window(it.get(1));
		Thread.sleep(2000);
		driver.findElement(By.id("u-crf-PATIENT_ID_NUMBER")).sendKeys("1102301988111106789");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[3]/button")).click();
		QuitWebDriver.quitWebDriver(driver);

	}

	@Test
	public void testPhantomJSDriver() throws Exception {
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		Thread.sleep(2000);
		driver.findElementById("u-crf-PATIENT_ID_NUMBER").clear();
		driver.findElementById("u-crf-PATIENT_ID_NUMBER").sendKeys("1102301988111106789");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div/input").click();
		driver.findElementByXPath("/html/body/div[9]/table/tbody/tr[2]/td[4]/a").click();
		Thread.sleep(3000);
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div/input").sendKeys("姓名1");
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div/select"))
				.selectByValue("男");
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

		new Select(driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[11]/div/select")).selectByValue("本人");

		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[12]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[12]/div/input").sendKeys("13088889999");
		
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[13]/div/input").clear();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[13]/div/input").sendKeys("通讯地址");

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
		
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

	}
	@Test
	public void testPhantomJSDriver1() throws Exception {
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		
		Thread.sleep(2000);
		String text = driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div/input").getAttribute("value");
		System.out.println(text);
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
	}
}
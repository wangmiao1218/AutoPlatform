package com.gennlife.crf.base.testNG;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gennlife.crf.base.SelectBase;
import com.gennlife.crf.bean.CrfTemplate;
import com.gennlife.crf.service.CrfTemplateService;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginCrfOfAnzhen;
import com.gennlife.crf.utils.QuitWebDriver;

@ContextConfiguration("classpath:spring.xml")
public class TestSelectBaseTestNGTest {
	@Autowired
	private CrfTemplateService crfTemplateService;
	
	/*@BeforeMethod
	public void setup() {
		System.out.println("begin test");
	}*/
	
	PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
	String value = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
	
	//@BeforeClass
	@BeforeMethod
	public void setup() {
		//登录
		System.out.println("begin test");
		
	}
	
	@Test
	public void test1() throws Exception {
		System.out.println("at test1");
		
		CrfTemplate oldCrfTemplate = crfTemplateService.getCrfTemplateByEnglishName("GENDER");
		System.out.println(oldCrfTemplate);
		//获取newCrfTemplate
		CrfTemplate newCrfTemplate = SelectBase.selectBase(driver,value, oldCrfTemplate);
		
		//保存到数据库
		crfTemplateService.updateCrfTemplate(newCrfTemplate);
		
	}

	/*@Test
	public void test2() throws Exception {
		System.out.println("at test2");
		
		CrfTemplate oldCrfTemplate = crfTemplateService.getCrfTemplateByEnglishName("CONTACT_AND_PATIENT_RELATIONSHIP");
		System.out.println(oldCrfTemplate);
		//获取newCrfTemplate
		CrfTemplate newCrfTemplate = SelectBase.selectBase(driver,value, oldCrfTemplate);
		
		//保存到数据库
		crfTemplateService.updateCrfTemplate(newCrfTemplate);
	}

	@Test
	public void test3() throws Exception {
		System.out.println("at test3");
		
		CrfTemplate oldCrfTemplate = crfTemplateService.getCrfTemplateByEnglishName("NATION");
		System.out.println(oldCrfTemplate);
		//获取newCrfTemplate
		CrfTemplate newCrfTemplate = SelectBase.selectBase(driver,value, oldCrfTemplate);
		
		//保存到数据库
		crfTemplateService.updateCrfTemplate(newCrfTemplate);
	}
	
	@Test
	public void test4() throws Exception {
		System.out.println("at test4");
		
		CrfTemplate oldCrfTemplate = crfTemplateService.getCrfTemplateByEnglishName("OCCUPATION");
		System.out.println(oldCrfTemplate);
		//获取newCrfTemplate
		CrfTemplate newCrfTemplate = SelectBase.selectBase(driver,value, oldCrfTemplate);
		
		//保存到数据库
		crfTemplateService.updateCrfTemplate(newCrfTemplate);
	}*/

	@AfterClass
	public void teardown() {
		//关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		System.out.println("end test");
	}
	
	/*@AfterMethod
	public void teardown() {
		System.out.println("end test");
	}*/
	
	
}

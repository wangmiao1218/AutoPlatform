package com.gennlife.autoplatform.base.testNG;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gennlife.autoplatform.base.SelectBase;
import com.gennlife.autoplatform.bean.CrfTemplate;
import com.gennlife.autoplatform.service.CrfTemplateService;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;

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
		System.out.println("begin test");
	}
	
	@Test
	public void test1() throws Exception {
		System.out.println("at test1");
		CrfTemplate oldCrfTemplate = crfTemplateService.getCrfTemplateByEnglishName("GENDER");
		System.out.println(oldCrfTemplate);
		CrfTemplate newCrfTemplate = SelectBase.selectBase(driver,value, oldCrfTemplate);
		crfTemplateService.updateCrfTemplate(newCrfTemplate);
		
	}

	@AfterClass
	public void teardown() {
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		System.out.println("end test");
	}
	
	/*@AfterMethod
	public void teardown() {
		System.out.println("end test");
	}*/
	
	
}

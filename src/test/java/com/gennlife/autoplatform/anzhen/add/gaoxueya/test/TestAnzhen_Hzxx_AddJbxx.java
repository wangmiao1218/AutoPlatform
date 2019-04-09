package com.gennlife.autoplatform.anzhen.add.gaoxueya.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.autoplatform.anzhen.add.gaoxueya.Anzhen_Hzxx_AddJbxx;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestAnzhen_Hzxx_AddJbxx {

	@Test
	public void loginByPhantomJSDriver() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		System.out.println(value);
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void hzxx_AddJbxx_MenZhen() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		System.out.println(value);
		String text = Anzhen_Hzxx_AddJbxx.hzxx_AddJbxx_MenZhen_ZhuYuan_SheQu(driver, value);
		System.out.println(text);
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	@Test
	public void hzxx_AddJbxx_ZhuYuan() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfZhuYuanAndBasicInfoByPhantomJSDriver(driver);
		
		String text = Anzhen_Hzxx_AddJbxx.hzxx_AddJbxx_MenZhen_ZhuYuan_SheQu(driver, value);
		System.out.println(text);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	//问题（需要debug运行，就能添加成功）
	@Test
	public void hzxx_AddJbxx_SheQu() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfSheQuAndBasicInfoByPhantomJSDriver(driver);
		System.out.println(value);
		
		String text = Anzhen_Hzxx_AddJbxx.hzxx_AddJbxx_MenZhen_ZhuYuan_SheQu(driver, value);
		System.out.println(text);
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	//问题（需要debug运行，就能添加成功）
	@Test
	public void hzxx_AddJbxx_TiJian() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		
		String value = LoginCrfOfAnzhen.loginAndToAddOfTiJianAndBasicInfoByPhantomJSDriver(driver);
		System.out.println(value);
		String text = Anzhen_Hzxx_AddJbxx.hzxx_AddJbxx_TiJian(driver, value);
		System.out.println(text);

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	
}
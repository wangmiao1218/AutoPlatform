package com.gennlife.autoplatform.utils.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginRws;
import com.gennlife.autoplatform.utils.QuitWebDriver;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestLoginRws {
	
	public static final String rwsUrl="http://10.0.2.162/uranus/project_index.html";
	private static final String loginName="testrws001";
	private static final String pwd="testrws001";

	@Test
	public void loginAndToRWSByPhantomJSDriver() throws Exception {
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginRws.loginAndToRwsByPhantomJSDriver(driver, rwsUrl,loginName, pwd);
		System.out.println(value);
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	

	
}
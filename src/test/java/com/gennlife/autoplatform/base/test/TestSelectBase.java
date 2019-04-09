package com.gennlife.autoplatform.base.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.autoplatform.base.SelectBase;
import com.gennlife.autoplatform.bean.CrfTemplate;
import com.gennlife.autoplatform.service.CrfTemplateService;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestSelectBase {
	
	@Autowired
	private CrfTemplateService crfTemplateService;
	
	@Test
	public void testSelectBase() throws Exception{
		List<CrfTemplate> list = crfTemplateService.getCrfTemplateListByVariableType("枚举型");
		
		//登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		
		//循环list，根据xpth值，查询页面下拉框，并保存到数据库
		for (int i = 0; i < list.size(); i++) {
			//根据list值，获取对应的englishName
			CrfTemplate oldCrfTemplate = crfTemplateService.getCrfTemplateByEnglishName(list.get(i).getEnglishName());

			//获取newCrfTemplate
			CrfTemplate newCrfTemplate = SelectBase.selectBase(driver,value,oldCrfTemplate);
			
			//保存到数据库
			crfTemplateService.updateCrfTemplate(newCrfTemplate);
			
		}
		//关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
}
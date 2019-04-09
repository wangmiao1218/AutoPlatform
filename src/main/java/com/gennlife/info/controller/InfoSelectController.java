package com.gennlife.info.controller;

import java.util.List;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gennlife.autoplatform.base.SelectBase;
import com.gennlife.autoplatform.bean.CrfTemplate;
import com.gennlife.autoplatform.service.CrfTemplateService;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;

/**
 * @Description: 验证下拉框
 * @author: wangmiao
 * @Date: 2017年7月14日 上午10:58:54 
 */
@Controller
@RequestMapping("infoSelectController")
public class InfoSelectController extends SelectBase {
	
	@Autowired
	private CrfTemplateService crfTemplateService;

	/** 
	* @Title: verifySelect 
	* @Description: 验证下拉框
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("verifySelect")
	public String verifySelect() throws Exception {
		List<CrfTemplate> list = crfTemplateService.getCrfTemplateListByVariableType("枚举型");
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		for (int i = 0; i < list.size(); i++) {
			CrfTemplate oldCrfTemplate = crfTemplateService.getCrfTemplateByEnglishName(list.get(i).getEnglishName());
			CrfTemplate newCrfTemplate = SelectBase.selectBase(driver,value,oldCrfTemplate);
			crfTemplateService.updateCrfTemplate(newCrfTemplate);
		}
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		return "redirect:/page/crfList/crfMgnt.jsp";
	}	
	
}
package com.gennlife.info.controller;

import java.util.List;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gennlife.crf.base.SelectBase;
import com.gennlife.crf.bean.CrfTemplate;
import com.gennlife.crf.service.CrfTemplateService;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginCrfOfAnzhen;
import com.gennlife.crf.utils.QuitWebDriver;

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
		
		//登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		
		//循环list，根据xpth值，查询页面下拉框，并保存到数据库
		for (int i = 0; i < list.size(); i++) {
			//根据list值，获取对应的englishName
			CrfTemplate oldCrfTemplate = crfTemplateService.getCrfTemplateByEnglishName(list.get(i).getEnglishName());
			//System.out.println(oldCrfTemplate);
			//获取newCrfTemplate
			CrfTemplate newCrfTemplate = SelectBase.selectBase(driver,value,oldCrfTemplate);
			//保存到数据库
			crfTemplateService.updateCrfTemplate(newCrfTemplate);
		}
		//关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		return "redirect:/page/crfList/crfMgnt.jsp";
	}	
	
}
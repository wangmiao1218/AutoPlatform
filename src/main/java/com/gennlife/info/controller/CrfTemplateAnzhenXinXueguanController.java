package com.gennlife.info.controller;

import java.util.List;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gennlife.autoplatform.bean.CrfTemplateAnzhenXinXueguan;
import com.gennlife.autoplatform.bean.CrfTemplateStructure;
import com.gennlife.autoplatform.service.CrfTemplateAnzhenXinXueguanService;
import com.gennlife.autoplatform.service.CrfTemplateStructureService;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.ListAndStringUtils;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;

/**
 * @Description: 安贞环境，心血管单病种，相关controller
 * @author: wangmiao
 * @Date: 2017年9月19日 上午11:24:41
 */
@Controller
@RequestMapping("crfTemplateAnzhenXinXueguanController")
public class CrfTemplateAnzhenXinXueguanController {

	@Autowired
	private CrfTemplateAnzhenXinXueguanService crfTemplateAnzhenXinXueguanService;
	
	@Autowired
	private CrfTemplateStructureService crfTemplateStructureService;

	/**
	 * @Title: verifyLinkageField_test
	 * @Description: 验证安贞心血管，联动字段(测试返回json:直接加@ResponseBody注解，返回json格式的list)
	 * @param: @throws Exception :
	 * @return: List<CrfTemplateAnzhenXinXueguan>
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("verifyLinkageField_test")
	public List<CrfTemplateAnzhenXinXueguan> verifyLinkageField_test() throws Exception {
		List<CrfTemplateStructure> list = crfTemplateStructureService
					.getCrfTemplateStructureListByHospitalDepartment("安贞心血管");
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfXinxueguanByPhantomJSDriver(driver);
		if ("添加页面".contains(value) && list.size()>0) {
			for (int i= 0; i< list.size(); i++) {
				List<CrfTemplateAnzhenXinXueguan> listByBaseName = crfTemplateAnzhenXinXueguanService
						.getCrfTemplateAnzhenXinXueguanListByBaseName(list.get(i).getBaseName());
				Boolean b = ListAndStringUtils.isCRFListLGroupLinkage(listByBaseName);
				if (b) {
					crfTemplateAnzhenXinXueguanService
							.updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(list.get(i).getBaseName());
					crfTemplateAnzhenXinXueguanService
							.verifyLinkageFieldGeneralServiceMethodWithGroupLinkage(list.get(i).getBaseName());
				}else {
					driver.findElementByClassName("dropdown-toggle").click();
					driver.findElementById("add-followup").click();
					Thread.sleep(2000);
					driver.findElementById(list.get(i).getIdXpath()).click();
					Thread.sleep(2000);
					crfTemplateAnzhenXinXueguanService
							.updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(list.get(i).getBaseName());
					crfTemplateAnzhenXinXueguanService
							.verifyLinkageFieldGeneralServiceMethod(driver,list.get(i).getBaseName());
				}
			}
		}
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		return crfTemplateAnzhenXinXueguanService.getVerifyLinkageFieldResultList();
	}
	
	
	/**
	 * @Title: verifyLinkageField
	 * @Description: 验证安贞心血管，联动字段(适用于界面的方式，页面直接展示)
	 * @param: @throws Exception :
	 * @return: String
	 * @throws
	 */
	@RequestMapping("verifyLinkageField")
	public String verifyLinkageField() throws Exception {
		List<CrfTemplateStructure> list = crfTemplateStructureService
					.getCrfTemplateStructureListByHospitalDepartment("安贞心血管");
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfXinxueguanByPhantomJSDriver(driver);
		if ("添加页面".contains(value) && list.size()>0) {
			for (int i= 0; i< list.size(); i++) {
				List<CrfTemplateAnzhenXinXueguan> listByBaseName = crfTemplateAnzhenXinXueguanService
						.getCrfTemplateAnzhenXinXueguanListByBaseName(list.get(i).getBaseName());
				Boolean b = ListAndStringUtils.isCRFListLGroupLinkage(listByBaseName);
				if (b) {
					crfTemplateAnzhenXinXueguanService
							.updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(list.get(i).getBaseName());
					crfTemplateAnzhenXinXueguanService
							.verifyLinkageFieldGeneralServiceMethodWithGroupLinkage(list.get(i).getBaseName());
				}else {
					driver.findElementByClassName("dropdown-toggle").click();
					driver.findElementById("add-followup").click();
					Thread.sleep(2000);
					driver.findElementById(list.get(i).getIdXpath()).click();
					Thread.sleep(2000);
					crfTemplateAnzhenXinXueguanService
							.updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(list.get(i).getBaseName());
					crfTemplateAnzhenXinXueguanService
							.verifyLinkageFieldGeneralServiceMethod(driver,list.get(i).getBaseName());
				}
			}
		}	
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		return "redirect:/page/ok.html";
	}
	
	
	/**
	 * @Title: verifyLinkageFieldReturnJSON
	 * @Description: 验证安贞心血管，联动字段(适用于接口测试方式，直接刚返回不成功的json)
	 * @param: @throws Exception :
	 * @return: List<CrfTemplateAnzhenXinXueguan>
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("verifyLinkageFieldReturnJSON")
	public List<CrfTemplateAnzhenXinXueguan> verifyLinkageFieldReturnJSON() throws Exception {
		List<CrfTemplateStructure> list = crfTemplateStructureService
					.getCrfTemplateStructureListByHospitalDepartment("安贞心血管");
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfXinxueguanByPhantomJSDriver(driver);
		if ("添加页面".contains(value) && list.size()>0) {
			for (int i= 0; i< list.size(); i++) {
				List<CrfTemplateAnzhenXinXueguan> listByBaseName = crfTemplateAnzhenXinXueguanService
						.getCrfTemplateAnzhenXinXueguanListByBaseName(list.get(i).getBaseName());
				Boolean b = ListAndStringUtils.isCRFListLGroupLinkage(listByBaseName);
				if (b) {
					crfTemplateAnzhenXinXueguanService
							.updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(list.get(i).getBaseName());
					crfTemplateAnzhenXinXueguanService
							.verifyLinkageFieldGeneralServiceMethodWithGroupLinkage(list.get(i).getBaseName());
				}else {
					driver.findElementByClassName("dropdown-toggle").click();
					driver.findElementById("add-followup").click();
					Thread.sleep(2000);
					driver.findElementById(list.get(i).getIdXpath()).click();
					Thread.sleep(2000);
					crfTemplateAnzhenXinXueguanService
							.updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(list.get(i).getBaseName());
					crfTemplateAnzhenXinXueguanService
							.verifyLinkageFieldGeneralServiceMethod(driver,list.get(i).getBaseName());
				}
			}
		}	
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		return crfTemplateAnzhenXinXueguanService.getVerifyLinkageFieldResultList();
	}
	
	
	/**
	 * @Title: verifyLinkageFieldWithBaseName
	 * @Description: 带baseName参数的，验证安贞心血管，联动字段验证
	 * (测试返回json:直接加@ResponseBody注解，返回json格式的list)
	 * @param: @throws Exception :
	 * @return: List<CrfTemplateAnzhenXinXueguan>
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/verifyLinkageFieldWithBaseName",method=RequestMethod.GET)
	public List<CrfTemplateAnzhenXinXueguan> verifyLinkageFieldWithBaseName(@RequestParam("baseName") String baseName) throws Exception {
		CrfTemplateStructure crfTemplateStructure = crfTemplateStructureService.getCrfTemplateStructureByBaseName(baseName);
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfXinxueguanByPhantomJSDriver(driver);
		if ("添加页面".contains(value)) {
			List<CrfTemplateAnzhenXinXueguan> listByBaseName = crfTemplateAnzhenXinXueguanService
					.getCrfTemplateAnzhenXinXueguanListByBaseName(baseName);
			Boolean b = ListAndStringUtils.isCRFListLGroupLinkage(listByBaseName);
			if (b) {
				crfTemplateAnzhenXinXueguanService
						.updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(baseName);
				crfTemplateAnzhenXinXueguanService
						.verifyLinkageFieldGeneralServiceMethodWithGroupLinkage(baseName);
			}else {
				driver.findElementByClassName("dropdown-toggle").click();
				driver.findElementById("add-followup").click();
				Thread.sleep(2000);
				driver.findElementById(crfTemplateStructure.getIdXpath()).click();
				Thread.sleep(2000);
				crfTemplateAnzhenXinXueguanService
						.updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(baseName);
				crfTemplateAnzhenXinXueguanService
						.verifyLinkageFieldGeneralServiceMethod(driver,baseName);
			}
		}
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return crfTemplateAnzhenXinXueguanService
				.getVerifyLinkageFieldResultListByBaseName(baseName);
		
	}
	
}

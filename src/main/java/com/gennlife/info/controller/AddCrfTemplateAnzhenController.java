package com.gennlife.info.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gennlife.autoplatform.bean.CrfTemplateAnzhen;
import com.gennlife.autoplatform.service.CrfTemplateAnzhenService;
import com.gennlife.autoplatform.utils.AnzhenInputValueMethod;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;

/**
 * @Description:  安贞环境，添加单病种数据库中crf
 * @author: wangmiao
 * @Date: 2017年7月28日 上午8:54:50 
 */
@Controller
@RequestMapping("addCrfTemplateAnzhenController")
public class AddCrfTemplateAnzhenController{

	@Autowired
	private CrfTemplateAnzhenService crfTemplateAnzhenService;

	/** 
	* @Title: addCrfTemplateAnzhen_Mz_Jbxx 
	* @Description: 添加门诊_患者信息_基本信息
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Mz_Jbxx")
	public String addCrfTemplateAnzhen_Mz_Jbxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		System.out.println(value);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	/** 
	 * @Title: addCrfTemplateAnzhen_Mz_Hzxx 
	 * @Description: 添加门诊_患者信息(包括基本信息、个人病史、家族史、生活方式、本次入院前2周内药物治疗史)
	 * @param: @throws Exception :
	 * @return: String
	 * @throws 
	 */
	@RequestMapping("addCrfTemplateAnzhen_Mz_Hzxx")
	public String addCrfTemplateAnzhen_Mz_Hzxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");
		
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
			
			//个人病史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_3_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("个人病史"));
			
			//家族史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_4_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("家族史"));
			
			//生活方式
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_5_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("生活方式"));
			
			//本次入院前2周内药物治疗史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_6_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("本次入院前2周内药物治疗史"));
		}
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		return "redirect:/page/ok.html";
	}
	
	
	/** 
	* @Title: addCrfTemplateAnzhen_MzWithoutHzxx 
	* @Description: 添加门诊_门诊-1（门诊下面所有信息，无随访）
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_MzWithoutHzxx")
	public String addCrfTemplateAnzhen_MzWithoutHzxx() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		
		if ("登陆成功".contains(value)) {
			//已有基本信息的病例xpath
			driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[6]/td[2]/a").click();
			// 得到当前窗口的set集合
			Set<String> winHandels = driver.getWindowHandles();
			// 将set集合存入list对象
			List<String> it = new ArrayList<String>(winHandels);
			// 切换到弹出的新窗口
			driver.switchTo().window(it.get(1));
			Thread.sleep(2000);
			
			//就诊－体格检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_8_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－体格检查"));
			
			//就诊－24小时动态血压监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－24小时动态血压监测"));
			
			//就诊－四肢血压及动脉弹性检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_10_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－四肢血压及动脉弹性检查"));
			
			//就诊－电生理检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_11_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));
			
			//就诊－超声检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_12_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－超声检查"));
			
			//就诊－CT检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_13_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－CT检查"));
			
			//就诊－眼底检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_14_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－眼底检查"));
			
			//就诊－多导睡眠图(PSG)监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_15_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－多导睡眠图(PSG)监测"));
			
			//就诊－实验室检验
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_16_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－实验室检验"));
			
			//就诊－门诊带药
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_17_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－门诊带药"));
			
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}

	/** 
	* @Title: addCrfTemplateAnzhen_Mz_All 
	* @Description: 添加门诊全部信息，包括三次随访
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Mz_All")
	public String addCrfTemplateAnzhen_Mz_All() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
			
			//个人病史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_3_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("个人病史"));
			//家族史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_4_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("家族史"));
			//生活方式
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_5_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("生活方式"));
			//本次入院前2周内药物治疗史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_6_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("本次入院前2周内药物治疗史"));
		
			//就诊－体格检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_8_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－体格检查"));
			//就诊－24小时动态血压监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－24小时动态血压监测"));
			//就诊－四肢血压及动脉弹性检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_10_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－四肢血压及动脉弹性检查"));
			//就诊－电生理检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_11_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));
			//就诊－超声检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_12_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－超声检查"));
			//就诊－CT检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_13_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－CT检查"));
			//就诊－眼底检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_14_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－眼底检查"));
			//就诊－多导睡眠图(PSG)监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_15_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－多导睡眠图(PSG)监测"));
			//就诊－实验室检验
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_16_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－实验室检验"));
			//就诊－门诊带药
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_17_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－门诊带药"));
			
			//随访1
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
			//随访2
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue02(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
			//随访3
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue03(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
		
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	/** 
	* @Title: addCrfTemplateAnzhen_Zy_Jbxx 
	* @Description: 添加住院_患者信息_基本信息
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Zy_Jbxx")
	public String addCrfTemplateAnzhen_Zy_Jbxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfZhuYuanAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}	
	
	
	/** 
	 * @Title: addCrfTemplateAnzhen_Zy_Hzxx 
	 * @Description: 添加住院_患者信息(包括基本信息、个人病史、家族史、生活方式、本次入院前2周内药物治疗史)
	 * @param: @throws Exception :
	 * @return: String
	 * @throws 
	 */
	@RequestMapping("addCrfTemplateAnzhen_Zy_Hzxx")
	public String addCrfTemplateAnzhen_Zy_Hzxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");
		
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfZhuYuanAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
			
			//个人病史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_3_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("个人病史"));
			
			//家族史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_4_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("家族史"));
			
			//生活方式
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_5_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("生活方式"));
			
			//本次入院前2周内药物治疗史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_6_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("本次入院前2周内药物治疗史"));
		}
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		return "redirect:/page/ok.html";
	}
	
	
	
	/** 
	* @Title: addCrfTemplateAnzhen_ZyWithoutHzxx 
	* @Description: 添加住院（住院下面所有信息，无随访）
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_ZyWithoutHzxx")
	public String addCrfTemplateAnzhen_ZyWithoutHzxx() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		
		if ("登陆成功".contains(value)) {
			//已有基本信息的病例xpath
			driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[7]/td[2]/a").click();
			// 得到当前窗口的set集合
			Set<String> winHandels = driver.getWindowHandles();
			// 将set集合存入list对象
			List<String> it = new ArrayList<String>(winHandels);
			// 切换到弹出的新窗口
			driver.switchTo().window(it.get(1));
			Thread.sleep(2000);
			
			//就诊－体格检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_8_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－体格检查"));
			
			//就诊－24小时动态血压监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－24小时动态血压监测"));
			
			//就诊－四肢血压及动脉弹性检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_10_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－四肢血压及动脉弹性检查"));
			
			//就诊－电生理检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_11_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));
			
			//就诊－超声检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_12_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－超声检查"));
			
			//就诊－CT检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_13_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－CT检查"));
			
			//就诊－眼底检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_14_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－眼底检查"));
			
			//就诊－多导睡眠图(PSG)监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_15_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－多导睡眠图(PSG)监测"));
			
			//就诊－实验室检验
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_16_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－实验室检验"));
			
			//就诊－住院与诊断
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_17_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－住院与诊断"));
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}

	/** 
	* @Title: addCrfTemplateAnzhen_Zy_All 
	* @Description: 添加住院全部信息，包括三次随访
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Zy_All")
	public String addCrfTemplateAnzhen_Zy_All() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfZhuYuanAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
			
			//个人病史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_3_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("个人病史"));
			//家族史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_4_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("家族史"));
			//生活方式
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_5_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("生活方式"));
			//本次入院前2周内药物治疗史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_6_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("本次入院前2周内药物治疗史"));
		
			//就诊－体格检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_8_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－体格检查"));
			//就诊－24小时动态血压监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－24小时动态血压监测"));
			//就诊－四肢血压及动脉弹性检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_10_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－四肢血压及动脉弹性检查"));
			//就诊－电生理检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_11_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));
			//就诊－超声检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_12_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－超声检查"));
			//就诊－CT检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_13_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－CT检查"));
			//就诊－眼底检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_14_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－眼底检查"));
			//就诊－多导睡眠图(PSG)监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_15_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－多导睡眠图(PSG)监测"));
			//就诊－实验室检验
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_16_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－实验室检验"));
			//就诊－住院与诊断
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_17_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－住院与诊断"));
		
			//随访1
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
			//随访2
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue02(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
			//随访3
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue03(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
		
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	/** 
	* @Title: addCrfTemplateAnzhen_Tjzx_Jbxx
	* @Description: 添加体检中心_患者信息_基本信息
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Tjzx_Jbxx")
	public String addCrfTemplateAnzhen_Tjzx_Jbxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfTiJianAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	/** 
	 * @Title: addCrfTemplateAnzhen_Tjzx_Hzxx 
	 * @Description: 添加体检中心_患者信息(包括基本信息、个人病史、家族史、生活方式、本次入院前2周内药物治疗史)
	 * @param: @throws Exception :
	 * @return: String
	 * @throws 
	 */
	@RequestMapping("addCrfTemplateAnzhen_Tjzx_Hzxx")
	public String addCrfTemplateAnzhen_Tjzx_Hzxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");
		
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfTiJianAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
			
			//个人病史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_3_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("个人病史"));
			
			//家族史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_4_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("家族史"));
			
			//生活方式
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_5_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("生活方式"));
			
			//本次入院前2周内药物治疗史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_6_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("本次入院前2周内药物治疗史"));
		}
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		return "redirect:/page/ok.html";
	}
	
	
	/** 
	* @Title: addCrfTemplateAnzhen_TjzxWithoutHzxx 
	* @Description: 添加体检中心（体检中心下面所有信息，无随访）
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_TjzxWithoutHzxx")
	public String addCrfTemplateAnzhen_TjzxWithoutHzxx() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		
		if ("登陆成功".contains(value)) {
			//已有基本信息的病例xpath
			driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[8]/td[2]/a").click();
			// 得到当前窗口的set集合
			Set<String> winHandels = driver.getWindowHandles();
			// 将set集合存入list对象
			List<String> it = new ArrayList<String>(winHandels);
			// 切换到弹出的新窗口
			driver.switchTo().window(it.get(1));
			Thread.sleep(2000);
			
			//就诊－体格检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_8_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－体格检查"));
			
			//就诊－24小时动态血压监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－24小时动态血压监测"));
			
			//就诊－四肢血压及动脉弹性检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_10_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－四肢血压及动脉弹性检查"));
			
			//就诊－电生理检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_11_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));
			
			//就诊－超声检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_12_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－超声检查"));
			
			//就诊－CT检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_13_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－CT检查"));
			
			//就诊－眼底检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_14_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－眼底检查"));
			
			//就诊－多导睡眠图(PSG)监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_15_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－多导睡眠图(PSG)监测"));
			
			//就诊－实验室检验
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_16_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－实验室检验"));
			
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	/** 
	* @Title: addCrfTemplateAnzhen_Tjzx_All 
	* @Description: 添加体检中心全部信息，包括三次随访
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Tjzx_All")
	public String addCrfTemplateAnzhen_Tjzx_All() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfTiJianAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
			
			//个人病史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_3_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("个人病史"));
			//家族史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_4_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("家族史"));
			//生活方式
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_5_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("生活方式"));
			//本次入院前2周内药物治疗史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_6_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("本次入院前2周内药物治疗史"));
		
			//就诊－体格检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_8_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－体格检查"));
			//就诊－24小时动态血压监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－24小时动态血压监测"));
			//就诊－四肢血压及动脉弹性检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_10_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－四肢血压及动脉弹性检查"));
			//就诊－电生理检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_11_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));
			//就诊－超声检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_12_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－超声检查"));
			//就诊－CT检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_13_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－CT检查"));
			//就诊－眼底检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_14_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－眼底检查"));
			//就诊－多导睡眠图(PSG)监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_15_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－多导睡眠图(PSG)监测"));
			//就诊－实验室检验
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_16_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－实验室检验"));
		
			//随访1
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
			//随访2
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue02(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
			//随访3
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue03(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
		
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	/** 
	* @Title: addCrfTemplateAnzhen_Sq_Jbxx 
	* @Description: 添加社区_患者信息_基本信息
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Sq_Jbxx")
	public String addCrfTemplateAnzhen_Sq_Jbxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfSheQuAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	/** 
	 * @Title: addCrfTemplateAnzhen_Sq_Hzxx 
	 * @Description: 添加社区_患者信息(包括基本信息、个人病史、家族史、生活方式、本次入院前2周内药物治疗史)
	 * @param: @throws Exception :
	 * @return: String
	 * @throws 
	 */
	@RequestMapping("addCrfTemplateAnzhen_Sq_Hzxx")
	public String addCrfTemplateAnzhen_Sq_Hzxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");
		
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfSheQuAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
			
			//个人病史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_3_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("个人病史"));
			
			//家族史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_4_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("家族史"));
			
			//生活方式
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_5_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("生活方式"));
			
			//本次入院前2周内药物治疗史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_6_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("本次入院前2周内药物治疗史"));
		}
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		return "redirect:/page/ok.html";
	}
	
	
	/** 
	* @Title: addCrfTemplateAnzhen_SqWithoutHzxx 
	* @Description: 添加社区（社区下面所有信息，无随访）
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_SqWithoutHzxx")
	public String addCrfTemplateAnzhen_SqWithoutHzxx() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		
		if ("登陆成功".contains(value)) {
			//已有基本信息的病例xpath
			driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[9]/td[2]/a").click();
			// 得到当前窗口的set集合
			Set<String> winHandels = driver.getWindowHandles();
			// 将set集合存入list对象
			List<String> it = new ArrayList<String>(winHandels);
			// 切换到弹出的新窗口
			driver.switchTo().window(it.get(1));
			Thread.sleep(2000);
			
			//就诊－体格检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_8_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－体格检查"));
			
			//就诊－24小时动态血压监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－24小时动态血压监测"));
			
			//就诊－四肢血压及动脉弹性检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_10_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－四肢血压及动脉弹性检查"));
			
			//就诊－电生理检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_11_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));
			
			//就诊－超声检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_12_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－超声检查"));
			
			//就诊－CT检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_13_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－CT检查"));
			
			//就诊－眼底检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_14_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－眼底检查"));
			
			//就诊－多导睡眠图(PSG)监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_15_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－多导睡眠图(PSG)监测"));
			
			//就诊－实验室检验
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_16_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－实验室检验"));
			
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
		
	/** 
	* @Title: addCrfTemplateAnzhen_Sq_All 
	* @Description: 添加社区全部信息，包括三次随访
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Sq_All")
	public String addCrfTemplateAnzhen_Sq_All() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfSheQuAndBasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：2018
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("2018"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//定义年：1990
						Select selYear = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[1]"));
						selYear.selectByValue("1990"); 
						//定义月：1月
						Select selMouth = new Select(driver.findElementByXPath("//*[@id='ui-datepicker-div']/div/div/select[2]"));
						selMouth.selectByValue("0"); 
						//定义日：1号
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[1]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(1000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(1000);
			
			//个人病史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_3_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("个人病史"));
			//家族史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_4_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("家族史"));
			//生活方式
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_5_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("生活方式"));
			//本次入院前2周内药物治疗史
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_6_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("本次入院前2周内药物治疗史"));
		
			//就诊－体格检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_8_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－体格检查"));
			//就诊－24小时动态血压监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－24小时动态血压监测"));
			//就诊－四肢血压及动脉弹性检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_10_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－四肢血压及动脉弹性检查"));
			//就诊－电生理检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_11_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));
			//就诊－超声检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_12_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－超声检查"));
			//就诊－CT检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_13_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－CT检查"));
			//就诊－眼底检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_14_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－眼底检查"));
			//就诊－多导睡眠图(PSG)监测
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_15_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－多导睡眠图(PSG)监测"));
			//就诊－实验室检验
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_16_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－实验室检验"));
			
			//随访1
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
			//随访2
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue02(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
			//随访3
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue03(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
		
		
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
		
	
	/** 
	* @Title: addCrfTemplateAnzhen_Sf 
	* @Description: 添加随访
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Sf")
	public String addCrfTemplateAnzhen_Sf() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		
		if ("登陆成功".contains(value)) {
			//已有基本信息的病例xpath
			driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[10]/td[2]/a").click();
			// 得到当前窗口的set集合
			Set<String> winHandels = driver.getWindowHandles();
			// 将set集合存入list对象
			List<String> it = new ArrayList<String>(winHandels);
			// 切换到弹出的新窗口
			driver.switchTo().window(it.get(1));
			Thread.sleep(2000);
			
			//随访1
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
			
			//随访2
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue02(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
		
			//随访3
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			AnzhenInputValueMethod.inputValueByVariableType_Sf_inputValue03(driver, crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("随访"));
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}

	
	/** 
	* @Title: addCrfTemplateAnzhen_test 
	* @Description: 测试图片上传????????????失败，图片上传不行
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_test")
	public String addCrfTemplateAnzhen_test() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		
		if ("登陆成功".contains(value)) {
			//已有基本信息的病例xpath
			driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[2]/td[2]/a").click();
			// 得到当前窗口的set集合
			Set<String> winHandels = driver.getWindowHandles();
			// 将set集合存入list对象
			List<String> it = new ArrayList<String>(winHandels);
			// 切换到弹出的新窗口
			driver.switchTo().window(it.get(1));
			Thread.sleep(2000);
			
			//就诊－24小时动态血压监测
			AnzhenInputValueMethod.test(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－24小时动态血压监测"));
			
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
		
}

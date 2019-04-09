package com.gennlife.info.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gennlife.crf.bean.CrfTemplateAnzhen;
import com.gennlife.crf.service.CrfTemplateAnzhenService;
import com.gennlife.crf.utils.AnzhenInputValueMethod;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.LoginCrfOfAnzhen;
import com.gennlife.crf.utils.QuitWebDriver;

/**
 * @Description:  安贞环境(高血压测试环境2：只有社区)，添加单病种数据库中crf
 * @author: wangmiao
 * @Date: 2018年5月17日 下午3:29:11 
 */
@Controller
@RequestMapping("addCrfTemplateAnzhen2Controller")
public class AddCrfTemplateAnzhen2Controller{

	@Autowired
	private CrfTemplateAnzhenService crfTemplateAnzhenService;

	/** 
	* @Title: addCrfTemplateAnzhen_Jbxx 
	* @Description: 添加门诊_患者信息_基本信息
	* @param: @throws Exception 
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Jbxx")
	public String addCrfTemplateAnzhen_Jbxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		//String value = LoginCrfOfAnzhen.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		String value = LoginCrfOfAnzhen.loginAndToAddOf2BasicInfoByPhantomJSDriver(driver);
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
						//driver.findElementByXPath(".//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[2]/a").click();
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[4]/a").click();
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						driver.findElementByXPath(".//*[@id='ui-datepicker-div']/div/div/select[1]").click();
						driver.findElementByXPath(".//*[@id='ui-datepicker-div']/div/div/select[1]/option[82]").click();//1998
						driver.findElementByXPath(".//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[6]/a").click();
					}
					else {
						driver.findElementById(jbxxList.get(i).getIdXpath()).clear();
						driver.findElementById(jbxxList.get(i).getIdXpath()).sendKeys(jbxxList.get(i).getInputValue());
					}
				}
			}
			
			//循环后保存
			driver.findElementById("input-save").click();
			Thread.sleep(2000);
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	/** 
	 * @Title: addCrfTemplateAnzhen_Hzxx 
	 * @Description: 添加门诊_患者信息(包括基本信息、个人病史、家族史、生活方式、本次入院前2周内药物治疗史)
	 * @param: @throws Exception :
	 * @return: String
	 * @throws 
	 */
	@RequestMapping("addCrfTemplateAnzhen_Hzxx")
	public String addCrfTemplateAnzhen_Hzxx() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");
		
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOf2BasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//driver.findElementByXPath(".//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[2]/a").click();
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[4]/a").click();
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						driver.findElementByXPath(".//*[@id='ui-datepicker-div']/div/div/select[1]").click();
						driver.findElementByXPath(".//*[@id='ui-datepicker-div']/div/div/select[1]/option[82]").click();//1998
						driver.findElementByXPath(".//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[6]/a").click();
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
	* @Description: 添加门诊_社区-1（社区下面所有信息，无随访）
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
			//driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[6]/td[2]/a").click();
			driver.findElementByXPath("//*[@id='case-list-container']/tbody/tr/td[2]/a").click();
			// 得到当前窗口的set集合
			Set<String> winHandels = driver.getWindowHandles();
			// 将set集合存入list对象
			List<String> it = new ArrayList<String>(winHandels);
			// 切换到弹出的新窗口
			driver.switchTo().window(it.get(1));
			Thread.sleep(2000);
			
			//就诊－体格检查
			AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_8_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－体格检查"));
			//就诊－电生理检查(暂时无字段)
			//AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));

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
			driver.findElementByXPath("//*[@id='case-list-container']/tbody/tr/td[2]/a").click();
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
	* @Title: addCrfTemplateAnzhen_Sq_All 
	* @Description: 添加门诊全部信息，包括三次随访
	* @param: @throws Exception 
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateAnzhen_Sq_All")
	public String addCrfTemplateAnzhen_Sq_All() throws Exception {
		// 获取所有基本信息list
		List<CrfTemplateAnzhen> jbxxList = crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("基本信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOf2BasicInfoByPhantomJSDriver(driver);
		
		if ("添加页面".contains(value)) {
			// 循环jbxxList
			for (int i = 0; i < jbxxList.size(); i++) {
				if ("枚举型".contains(jbxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(jbxxList.get(i).getIdXpath())).selectByValue(jbxxList.get(i).getInputValue());
				}
				else {
					if ("调查时间".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						//driver.findElementByXPath(".//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[2]/a").click();
						driver.findElementByXPath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[4]/a").click();
					}
					else if ("出生日期".contains(jbxxList.get(i).getChineseName())) {
						driver.findElementById(jbxxList.get(i).getIdXpath()).click();
						driver.findElementByXPath(".//*[@id='ui-datepicker-div']/div/div/select[1]").click();
						driver.findElementByXPath(".//*[@id='ui-datepicker-div']/div/div/select[1]/option[82]").click();//1998
						driver.findElementByXPath(".//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[6]/a").click();
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
			//就诊－电生理检查
			//AnzhenInputValueMethod.inputValueByVariableType(driver, "crf-data-tree_9_span", crfTemplateAnzhenService.getCrfTemplateAnzhenListByBaseName("就诊－电生理检查"));

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

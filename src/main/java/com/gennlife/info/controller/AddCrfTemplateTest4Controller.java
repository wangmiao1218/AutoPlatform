package com.gennlife.info.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gennlife.autoplatform.bean.CrfTemplateTest4;
import com.gennlife.autoplatform.service.CrfTemplateTest4Service;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.ListAndStringUtils;
import com.gennlife.autoplatform.utils.LoginCrfOfYantai;
import com.gennlife.autoplatform.utils.QuitWebDriver;
import com.gennlife.autoplatform.utils.RandomIdCardGenerator;
import com.gennlife.autoplatform.utils.RandomValue;

/**
 * @Description: 烟台环境，test4科室，添加单病种数据库中crf的患者信息
 * @author: wangmiao
 * @Date: 2017年7月12日 下午2:25:33 
 */
@Controller
@RequestMapping("addCrfTemplateTest4Controller")
public class AddCrfTemplateTest4Controller{

	@Autowired
	private CrfTemplateTest4Service crfTemplateTest4Service;

	/** 
	* @Title: addCrfTemplateTest4_Ybxx 
	* @Description: 添加患者信息_一般信息
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateTest4_Ybxx")
	public String addCrfTemplateTest4_Ybxx() throws Exception {
		// 获取所有一般信息list
		List<CrfTemplateTest4> list = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("一般信息");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToAddBasicInfoByPhantomJSDriver(driver);
		if ("添加页面".contains(value)) {
			// 循环list
			for (int i = 0; i < list.size(); i++) {
				if ("字符串".contains(list.get(i).getVariableType())) {
					driver.findElementById(list.get(i).getIdXpath()).clear();
					driver.findElementById(list.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(list.get(i).getVariableType())) {
					driver.findElementById(list.get(i).getIdXpath()).clear();
					driver.findElementById(list.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(list.get(i).getVariableType())) {
					driver.findElementById(list.get(i).getIdXpath()).clear();
					driver.findElementById(list.get(i).getIdXpath()).sendKeys("");
				}
				else if ("枚举".contains(list.get(i).getVariableType())) {
					new Select(driver.findElementById(list.get(i).getIdXpath())).selectByValue("男");
				}
			}
			
		}

		// 保存
		driver.findElementById("input-save").click();
		Thread.sleep(2000);
		//driver.findElementByXPath(".//*[@id='alert-container']/div/div/div/div[3]/button").click();
		driver.findElementByClassName("u-btn").click();
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	/** 
	* @Title: addCrfTemplateTest4_Hzxx
	* @Description: 添加患者信息
	* @param: @throws Exception 
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateTest4_Hzxx")
	public String addCrfTemplateTest4_Hzxx() throws Exception {
		// 获取所有分类list
		List<CrfTemplateTest4> ybxxList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("一般信息");
		List<CrfTemplateTest4> jwsList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("既往史");
		List<CrfTemplateTest4> jzbsList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("家族病史");
		List<CrfTemplateTest4> xysList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("吸烟史");
		List<CrfTemplateTest4> yjsList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("饮酒史");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToAddBasicInfoByPhantomJSDriver(driver);

		if ("添加页面".contains(value)) {
			//一般病史
			//添加判断姓名、家庭住址、电话、身份证
			for (int i = 0; i < ybxxList.size(); i++) {
				if ("字符串".contains(ybxxList.get(i).getVariableType())) {
					//姓名
					if (ybxxList.get(i).getDateFormat()!=null && "姓名".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomChineseName());
					}
					else if (ybxxList.get(i).getDateFormat()!=null && "家庭住址".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomHomeAddress());
					}
					else if (ybxxList.get(i).getDateFormat()!=null && "身份证".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomIdCardGenerator.RandomIdCardGenerator());
					}
					else if (ybxxList.get(i).getDateFormat()!=null && "电话".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomTel());
					}
					else if (ybxxList.get(i).getDateFormat()==null) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
					}
				}
				else if ("数值".contains(ybxxList.get(i).getVariableType())) {
					driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
					driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(ybxxList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(ybxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(ybxxList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(ybxxList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//既往史
			driver.findElementById("crf-data-tree_3_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < jwsList.size(); i++) {
				if ("字符串".contains(jwsList.get(i).getVariableType())) {
					driver.findElementById(jwsList.get(i).getIdXpath()).clear();
					driver.findElementById(jwsList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(jwsList.get(i).getVariableType())) {
					driver.findElementById(jwsList.get(i).getIdXpath()).clear();
					driver.findElementById(jwsList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(jwsList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(jwsList.get(i).getDateFormat())) {
						driver.findElementById(jwsList.get(i).getIdXpath()).clear();
						driver.findElementById(jwsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(jwsList.get(i).getDateFormat())) {
						driver.findElementById(jwsList.get(i).getIdXpath()).clear();
						driver.findElementById(jwsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(jwsList.get(i).getVariableType())) {
					new Select(driver.findElementById(jwsList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(jwsList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//家族病史
			driver.findElementById("crf-data-tree_4_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < jzbsList.size(); i++) {
				if ("字符串".contains(jzbsList.get(i).getVariableType())) {
					driver.findElementById(jzbsList.get(i).getIdXpath()).clear();
					driver.findElementById(jzbsList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(jzbsList.get(i).getVariableType())) {
					driver.findElementById(jzbsList.get(i).getIdXpath()).clear();
					driver.findElementById(jzbsList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(jzbsList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(jzbsList.get(i).getDateFormat())) {
						driver.findElementById(jzbsList.get(i).getIdXpath()).clear();
						driver.findElementById(jzbsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(jzbsList.get(i).getDateFormat())) {
						driver.findElementById(jzbsList.get(i).getIdXpath()).clear();
						driver.findElementById(jzbsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(jzbsList.get(i).getVariableType())) {
					new Select(driver.findElementById(jzbsList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(jzbsList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//吸烟史
			driver.findElementById("crf-data-tree_5_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < xysList.size(); i++) {
				if ("字符串".contains(xysList.get(i).getVariableType())) {
					driver.findElementById(xysList.get(i).getIdXpath()).clear();
					driver.findElementById(xysList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(xysList.get(i).getVariableType())) {
					driver.findElementById(xysList.get(i).getIdXpath()).clear();
					driver.findElementById(xysList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(xysList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(xysList.get(i).getDateFormat())) {
						driver.findElementById(xysList.get(i).getIdXpath()).clear();
						driver.findElementById(xysList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(xysList.get(i).getDateFormat())) {
						driver.findElementById(xysList.get(i).getIdXpath()).clear();
						driver.findElementById(xysList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(xysList.get(i).getVariableType())) {
					new Select(driver.findElementById(xysList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(xysList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//饮酒史
			driver.findElementById("crf-data-tree_6_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < yjsList.size(); i++) {
				if ("字符串".contains(yjsList.get(i).getVariableType())) {
					driver.findElementById(yjsList.get(i).getIdXpath()).clear();
					driver.findElementById(yjsList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(yjsList.get(i).getVariableType())) {
					driver.findElementById(yjsList.get(i).getIdXpath()).clear();
					driver.findElementById(yjsList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(yjsList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(yjsList.get(i).getDateFormat())) {
						driver.findElementById(yjsList.get(i).getIdXpath()).clear();
						driver.findElementById(yjsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(yjsList.get(i).getDateFormat())) {
						driver.findElementById(yjsList.get(i).getIdXpath()).clear();
						driver.findElementById(yjsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(yjsList.get(i).getVariableType())) {
					new Select(driver.findElementById(yjsList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(yjsList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			Thread.sleep(2000);
			driver.findElementByClassName("u-btn").click();
			
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	/** 
	 * @Title: addCrfTemplateTest4_Zy
	 * @Description: 添加住院
	 * @param: @throws Exception 
	 * @return: String
	 * @throws 
	 */
	@RequestMapping("addCrfTemplateTest4_Zy")
	public String addCrfTemplateTest4_Zy() throws Exception {
		// 获取所有分类list
		List<CrfTemplateTest4> rysqkList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("入院时情况");
		List<CrfTemplateTest4> zysqkList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("住院时情况");
		List<CrfTemplateTest4> cysqkList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("出院时情况");
		
		// 登录
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		//到所添加基本信息的病例中
		driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[6]/td[2]/a").click();
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();
		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);
		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		Thread.sleep(2000);

		// 添加住院
		driver.findElementByXPath("html/body/div[2]/div[1]/ul/li/a").click();
		driver.findElementById("add-hospital").click();
		
		//入院时情况
		for (int i = 0; i < rysqkList.size(); i++) {
			if ("字符串".contains(rysqkList.get(i).getVariableType())) {
				driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
			}
			else if ("数值".contains(rysqkList.get(i).getVariableType())) {
				driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
			}
			else if ("日期".contains(rysqkList.get(i).getVariableType())) {
				if ("yyyy-MM-dd".contains(rysqkList.get(i).getDateFormat())) {
					driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
				}else if ("yyyy-MM-dd HH:mm:ss".contains(rysqkList.get(i).getDateFormat())) {
					driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
				}
			}
			else if ("枚举".contains(rysqkList.get(i).getVariableType())) {
				new Select(driver.findElementById(rysqkList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(rysqkList.get(i).getRangeData()));
			}
		}
		// 保存
		driver.findElementById("input-save").click();
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(2000);
		
		
		//住院时情况
		driver.findElementById("crf-data-tree_9_span").click();
		Thread.sleep(1500);
		for (int i = 0; i < zysqkList.size(); i++) {
			if ("字符串".contains(zysqkList.get(i).getVariableType())) {
				driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
			}
			else if ("数值".contains(zysqkList.get(i).getVariableType())) {
				driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
			}
			else if ("日期".contains(zysqkList.get(i).getVariableType())) {
				if ("yyyy-MM-dd".contains(zysqkList.get(i).getDateFormat())) {
					driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
				}else if ("yyyy-MM-dd HH:mm:ss".contains(zysqkList.get(i).getDateFormat())) {
					driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
				}
			}
			else if ("枚举".contains(zysqkList.get(i).getVariableType())) {
				new Select(driver.findElementById(zysqkList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(zysqkList.get(i).getRangeData()));
			}
		}
		// 保存
		driver.findElementById("input-save").click();
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(3000);

		
		//出院时情况
		driver.findElementById("crf-data-tree_10_span").click();
		Thread.sleep(1500);
		for (int i = 0; i < cysqkList.size(); i++) {
			if ("字符串".contains(cysqkList.get(i).getVariableType())) {
				driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
			}
			else if ("数值".contains(cysqkList.get(i).getVariableType())) {
				driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
			}
			else if ("日期".contains(cysqkList.get(i).getVariableType())) {
				if ("yyyy-MM-dd".contains(cysqkList.get(i).getDateFormat())) {
					driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
				}else if ("yyyy-MM-dd HH:mm:ss".contains(cysqkList.get(i).getDateFormat())) {
					driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
				}
			}
			else if ("枚举".contains(cysqkList.get(i).getVariableType())) {
				new Select(driver.findElementById(cysqkList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(cysqkList.get(i).getRangeData()));
			}
		}
		// 保存
		driver.findElementById("input-save").click();
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(2000);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		return "redirect:/page/ok.html";
	}
	
	
	/** 
	 * @Title: addCrfTemplateTest4_Mjz
	 * @Description: 添加门急诊
	 * @param: @throws Exception 
	 * @return: String
	 * @throws 
	 */
	@RequestMapping("addCrfTemplateTest4_Mjz")
	public String addCrfTemplateTest4_Mjz() throws Exception {
		// 获取所有分类list
		List<CrfTemplateTest4> rysqkList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("入院时情况");
		List<CrfTemplateTest4> zysqkList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("住院时情况");
		List<CrfTemplateTest4> cysqkList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("出院时情况");
		
		// 登录
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		//到所添加基本信息的病例中
		driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[6]/td[2]/a").click();
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();
		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);
		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		Thread.sleep(2000);

		// 添加门急诊
		driver.findElementByXPath("html/body/div[2]/div[1]/ul/li/a").click();
		driver.findElementById("add-outpatient").click();
		
		//门急诊_入院时情况
		for (int i = 0; i < rysqkList.size(); i++) {
			if ("字符串".contains(rysqkList.get(i).getVariableType())) {
				driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
			}
			else if ("数值".contains(rysqkList.get(i).getVariableType())) {
				driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
			}
			else if ("日期".contains(rysqkList.get(i).getVariableType())) {
				if ("yyyy-MM-dd".contains(rysqkList.get(i).getDateFormat())) {
					driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
				}else if ("yyyy-MM-dd HH:mm:ss".contains(rysqkList.get(i).getDateFormat())) {
					driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
				}
			}
			else if ("枚举".contains(rysqkList.get(i).getVariableType())) {
				new Select(driver.findElementById(rysqkList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(rysqkList.get(i).getRangeData()));
			}
		}
		// 保存
		driver.findElementById("input-save").click();
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(2000);
		
		
		//门急诊_住院时情况
		driver.findElementById("crf-data-tree_13_span").click();
		Thread.sleep(1500);
		for (int i = 0; i < zysqkList.size(); i++) {
			if ("字符串".contains(zysqkList.get(i).getVariableType())) {
				driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
			}
			else if ("数值".contains(zysqkList.get(i).getVariableType())) {
				driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
			}
			else if ("日期".contains(zysqkList.get(i).getVariableType())) {
				if ("yyyy-MM-dd".contains(zysqkList.get(i).getDateFormat())) {
					driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
				}else if ("yyyy-MM-dd HH:mm:ss".contains(zysqkList.get(i).getDateFormat())) {
					driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
				}
			}
			else if ("枚举".contains(zysqkList.get(i).getVariableType())) {
				new Select(driver.findElementById(zysqkList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(zysqkList.get(i).getRangeData()));
			}
		}
		// 保存
		driver.findElementById("input-save").click();
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(3000);

		
		//门急诊_出院时情况
		driver.findElementById("crf-data-tree_14_span").click();
		Thread.sleep(1500);
		for (int i = 0; i < cysqkList.size(); i++) {
			if ("字符串".contains(cysqkList.get(i).getVariableType())) {
				driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
			}
			else if ("数值".contains(cysqkList.get(i).getVariableType())) {
				driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
				driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
			}
			else if ("日期".contains(cysqkList.get(i).getVariableType())) {
				if ("yyyy-MM-dd".contains(cysqkList.get(i).getDateFormat())) {
					driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
				}else if ("yyyy-MM-dd HH:mm:ss".contains(cysqkList.get(i).getDateFormat())) {
					driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
				}
			}
			else if ("枚举".contains(cysqkList.get(i).getVariableType())) {
				new Select(driver.findElementById(cysqkList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(cysqkList.get(i).getRangeData()));
			}
		}
		// 保存
		driver.findElementById("input-save").click();
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(2000);
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		return "redirect:/page/ok.html";
	}
	
	
	/** 
	 * @Title: addCrfTemplateTest4_Sf
	 * @Description: 添加随访
	 * @param: @throws Exception 
	 * @return: String
	 * @throws 
	 */
	@RequestMapping("addCrfTemplateTest4_Sf")
	public String addCrfTemplateTest4_Sf() throws Exception {
		// 获取所有分类list
		List<CrfTemplateTest4> sfList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("随访");
		
		// 登录
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfYantai.loginAndToDanbingzhongByPhantomJSDriver(driver);
		
		//到所添加基本信息的病例中
		driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[6]/td[2]/a").click();
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();
		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);
		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		Thread.sleep(2000);

		// 添加随访
		driver.findElementByXPath("html/body/div[2]/div[1]/ul/li/a").click();
		driver.findElementById("add-followup").click();
		
		//随访
		for (int i = 0; i < sfList.size(); i++) {
			if ("字符串".contains(sfList.get(i).getVariableType())) {
				driver.findElementById(sfList.get(i).getIdXpath()).clear();
				driver.findElementById(sfList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
			}
			else if ("数值".contains(sfList.get(i).getVariableType())) {
				driver.findElementById(sfList.get(i).getIdXpath()).clear();
				driver.findElementById(sfList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
			}
			else if ("日期".contains(sfList.get(i).getVariableType())) {
				if ("yyyy-MM-dd".contains(sfList.get(i).getDateFormat())) {
					driver.findElementById(sfList.get(i).getIdXpath()).clear();
					driver.findElementById(sfList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
				}else if ("yyyy-MM-dd HH:mm:ss".contains(sfList.get(i).getDateFormat())) {
					driver.findElementById(sfList.get(i).getIdXpath()).clear();
					driver.findElementById(sfList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
				}
			}
			else if ("枚举".contains(sfList.get(i).getVariableType())) {
				new Select(driver.findElementById(sfList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(sfList.get(i).getRangeData()));
			}
		}
		// 保存
		driver.findElementById("input-save").click();
		driver.findElementByClassName("u-btn").click();
		Thread.sleep(2000);

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		return "redirect:/page/ok.html";
	}
	
	
	/** 
	* @Title: addCrfTemplateTest4_Hzxx_Zy_Mjz_Sf
	* @Description: 添加患者信息(全)，包括一次住院、门急诊、随访
	* @param: @throws Exception 
	* @return: String
	* @throws 
	*/
	@RequestMapping("addCrfTemplateTest4_Hzxx_Zy_Mjz_Sf")
	public String addCrfTemplateTest4_Hzxx_Zy_Mjz_Sf() throws Exception {
		// 获取所有分类list
		List<CrfTemplateTest4> ybxxList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("一般信息");
		List<CrfTemplateTest4> jwsList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("既往史");
		List<CrfTemplateTest4> jzbsList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("家族病史");
		List<CrfTemplateTest4> xysList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("吸烟史");
		List<CrfTemplateTest4> yjsList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("饮酒史");

		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfYantai.loginAndToAddBasicInfoByPhantomJSDriver(driver);

		if ("添加页面".contains(value)) {
			//一般病史
			//添加判断姓名、家庭住址、电话、身份证
			for (int i = 0; i < ybxxList.size(); i++) {
				if ("字符串".contains(ybxxList.get(i).getVariableType())) {
					//姓名
					if (ybxxList.get(i).getDateFormat()!=null && "姓名".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomChineseName());
					}
					else if (ybxxList.get(i).getDateFormat()!=null && "家庭住址".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomHomeAddress());
					}
					else if (ybxxList.get(i).getDateFormat()!=null && "身份证".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomIdCardGenerator.RandomIdCardGenerator());
					}
					else if (ybxxList.get(i).getDateFormat()!=null && "电话".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomTel());
					}
					else if (ybxxList.get(i).getDateFormat()==null) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
					}
				}
				else if ("数值".contains(ybxxList.get(i).getVariableType())) {
					driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
					driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(ybxxList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(ybxxList.get(i).getDateFormat())) {
						driver.findElementById(ybxxList.get(i).getIdXpath()).clear();
						driver.findElementById(ybxxList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(ybxxList.get(i).getVariableType())) {
					new Select(driver.findElementById(ybxxList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(ybxxList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//既往史
			driver.findElementById("crf-data-tree_3_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < jwsList.size(); i++) {
				if ("字符串".contains(jwsList.get(i).getVariableType())) {
					driver.findElementById(jwsList.get(i).getIdXpath()).clear();
					driver.findElementById(jwsList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(jwsList.get(i).getVariableType())) {
					driver.findElementById(jwsList.get(i).getIdXpath()).clear();
					driver.findElementById(jwsList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(jwsList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(jwsList.get(i).getDateFormat())) {
						driver.findElementById(jwsList.get(i).getIdXpath()).clear();
						driver.findElementById(jwsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(jwsList.get(i).getDateFormat())) {
						driver.findElementById(jwsList.get(i).getIdXpath()).clear();
						driver.findElementById(jwsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(jwsList.get(i).getVariableType())) {
					new Select(driver.findElementById(jwsList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(jwsList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//家族病史
			driver.findElementById("crf-data-tree_4_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < jzbsList.size(); i++) {
				if ("字符串".contains(jzbsList.get(i).getVariableType())) {
					driver.findElementById(jzbsList.get(i).getIdXpath()).clear();
					driver.findElementById(jzbsList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(jzbsList.get(i).getVariableType())) {
					driver.findElementById(jzbsList.get(i).getIdXpath()).clear();
					driver.findElementById(jzbsList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(jzbsList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(jzbsList.get(i).getDateFormat())) {
						driver.findElementById(jzbsList.get(i).getIdXpath()).clear();
						driver.findElementById(jzbsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(jzbsList.get(i).getDateFormat())) {
						driver.findElementById(jzbsList.get(i).getIdXpath()).clear();
						driver.findElementById(jzbsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(jzbsList.get(i).getVariableType())) {
					new Select(driver.findElementById(jzbsList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(jzbsList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//吸烟史
			driver.findElementById("crf-data-tree_5_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < xysList.size(); i++) {
				if ("字符串".contains(xysList.get(i).getVariableType())) {
					driver.findElementById(xysList.get(i).getIdXpath()).clear();
					driver.findElementById(xysList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(xysList.get(i).getVariableType())) {
					driver.findElementById(xysList.get(i).getIdXpath()).clear();
					driver.findElementById(xysList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(xysList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(xysList.get(i).getDateFormat())) {
						driver.findElementById(xysList.get(i).getIdXpath()).clear();
						driver.findElementById(xysList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(xysList.get(i).getDateFormat())) {
						driver.findElementById(xysList.get(i).getIdXpath()).clear();
						driver.findElementById(xysList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(xysList.get(i).getVariableType())) {
					new Select(driver.findElementById(xysList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(xysList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//饮酒史
			driver.findElementById("crf-data-tree_6_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < yjsList.size(); i++) {
				if ("字符串".contains(yjsList.get(i).getVariableType())) {
					driver.findElementById(yjsList.get(i).getIdXpath()).clear();
					driver.findElementById(yjsList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(yjsList.get(i).getVariableType())) {
					driver.findElementById(yjsList.get(i).getIdXpath()).clear();
					driver.findElementById(yjsList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(yjsList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(yjsList.get(i).getDateFormat())) {
						driver.findElementById(yjsList.get(i).getIdXpath()).clear();
						driver.findElementById(yjsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(yjsList.get(i).getDateFormat())) {
						driver.findElementById(yjsList.get(i).getIdXpath()).clear();
						driver.findElementById(yjsList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(yjsList.get(i).getVariableType())) {
					new Select(driver.findElementById(yjsList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(yjsList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			Thread.sleep(2000);
			driver.findElementByClassName("u-btn").click();
			
			//住院
			// 获取所有分类list
			List<CrfTemplateTest4> rysqkList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("入院时情况");
			List<CrfTemplateTest4> zysqkList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("住院时情况");
			List<CrfTemplateTest4> cysqkList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("出院时情况");
			// 添加住院
			driver.findElementByXPath("html/body/div[2]/div[1]/ul/li/a").click();
			driver.findElementById("add-hospital").click();
			Thread.sleep(1500);
			
			//入院时情况
			for (int i = 0; i < rysqkList.size(); i++) {
				if ("字符串".contains(rysqkList.get(i).getVariableType())) {
					driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(rysqkList.get(i).getVariableType())) {
					driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(rysqkList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(rysqkList.get(i).getDateFormat())) {
						driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
						driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(rysqkList.get(i).getDateFormat())) {
						driver.findElementById(rysqkList.get(i).getIdXpath()).clear();
						driver.findElementById(rysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(rysqkList.get(i).getVariableType())) {
					new Select(driver.findElementById(rysqkList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(rysqkList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			//住院时情况
			driver.findElementById("crf-data-tree_9_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < zysqkList.size(); i++) {
				if ("字符串".contains(zysqkList.get(i).getVariableType())) {
					driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(zysqkList.get(i).getVariableType())) {
					driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(zysqkList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(zysqkList.get(i).getDateFormat())) {
						driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
						driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(zysqkList.get(i).getDateFormat())) {
						driver.findElementById(zysqkList.get(i).getIdXpath()).clear();
						driver.findElementById(zysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(zysqkList.get(i).getVariableType())) {
					new Select(driver.findElementById(zysqkList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(zysqkList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(3000);
			
			//出院时情况
			driver.findElementById("crf-data-tree_10_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < cysqkList.size(); i++) {
				if ("字符串".contains(cysqkList.get(i).getVariableType())) {
					driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(cysqkList.get(i).getVariableType())) {
					driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
					driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(cysqkList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(cysqkList.get(i).getDateFormat())) {
						driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
						driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(cysqkList.get(i).getDateFormat())) {
						driver.findElementById(cysqkList.get(i).getIdXpath()).clear();
						driver.findElementById(cysqkList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(cysqkList.get(i).getVariableType())) {
					new Select(driver.findElementById(cysqkList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(cysqkList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//门急诊
			// 获取所有分类list
			List<CrfTemplateTest4> rysqkList_mjz = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("入院时情况");
			List<CrfTemplateTest4> zysqkList_mjz = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("住院时情况");
			List<CrfTemplateTest4> cysqkList_mjz = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("出院时情况");
			
			// 添加门急诊
			driver.findElementByXPath("html/body/div[2]/div[1]/ul/li/a").click();
			driver.findElementById("add-outpatient").click();
			Thread.sleep(1500);
			
			//门急诊_入院时情况
			for (int i = 0; i < rysqkList_mjz.size(); i++) {
				if ("字符串".contains(rysqkList_mjz.get(i).getVariableType())) {
					driver.findElementById(rysqkList_mjz.get(i).getIdXpath()).clear();
					driver.findElementById(rysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(rysqkList_mjz.get(i).getVariableType())) {
					driver.findElementById(rysqkList_mjz.get(i).getIdXpath()).clear();
					driver.findElementById(rysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(rysqkList_mjz.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(rysqkList_mjz.get(i).getDateFormat())) {
						driver.findElementById(rysqkList_mjz.get(i).getIdXpath()).clear();
						driver.findElementById(rysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(rysqkList_mjz.get(i).getDateFormat())) {
						driver.findElementById(rysqkList_mjz.get(i).getIdXpath()).clear();
						driver.findElementById(rysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(rysqkList_mjz.get(i).getVariableType())) {
					new Select(driver.findElementById(rysqkList_mjz.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(rysqkList_mjz.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//门急诊_住院时情况
			driver.findElementById("crf-data-tree_13_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < zysqkList_mjz.size(); i++) {
				if ("字符串".contains(zysqkList_mjz.get(i).getVariableType())) {
					driver.findElementById(zysqkList_mjz.get(i).getIdXpath()).clear();
					driver.findElementById(zysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(zysqkList_mjz.get(i).getVariableType())) {
					driver.findElementById(zysqkList_mjz.get(i).getIdXpath()).clear();
					driver.findElementById(zysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(zysqkList_mjz.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(zysqkList_mjz.get(i).getDateFormat())) {
						driver.findElementById(zysqkList_mjz.get(i).getIdXpath()).clear();
						driver.findElementById(zysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(zysqkList_mjz.get(i).getDateFormat())) {
						driver.findElementById(zysqkList_mjz.get(i).getIdXpath()).clear();
						driver.findElementById(zysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(zysqkList_mjz.get(i).getVariableType())) {
					new Select(driver.findElementById(zysqkList_mjz.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(zysqkList_mjz.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(3000);

			
			//门急诊_出院时情况
			driver.findElementById("crf-data-tree_14_span").click();
			Thread.sleep(1500);
			for (int i = 0; i < cysqkList_mjz.size(); i++) {
				if ("字符串".contains(cysqkList_mjz.get(i).getVariableType())) {
					driver.findElementById(cysqkList_mjz.get(i).getIdXpath()).clear();
					driver.findElementById(cysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(cysqkList_mjz.get(i).getVariableType())) {
					driver.findElementById(cysqkList_mjz.get(i).getIdXpath()).clear();
					driver.findElementById(cysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(cysqkList_mjz.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(cysqkList_mjz.get(i).getDateFormat())) {
						driver.findElementById(cysqkList_mjz.get(i).getIdXpath()).clear();
						driver.findElementById(cysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(cysqkList_mjz.get(i).getDateFormat())) {
						driver.findElementById(cysqkList_mjz.get(i).getIdXpath()).clear();
						driver.findElementById(cysqkList_mjz.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(cysqkList_mjz.get(i).getVariableType())) {
					new Select(driver.findElementById(cysqkList_mjz.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(cysqkList_mjz.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);
			
			
			//随访
			// 获取所有分类list
			List<CrfTemplateTest4> sfList = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName("随访");
			// 添加随访
			driver.findElementByXPath("html/body/div[2]/div[1]/ul/li/a").click();
			driver.findElementById("add-followup").click();
			Thread.sleep(1500);
			
			//随访
			for (int i = 0; i < sfList.size(); i++) {
				if ("字符串".contains(sfList.get(i).getVariableType())) {
					driver.findElementById(sfList.get(i).getIdXpath()).clear();
					driver.findElementById(sfList.get(i).getIdXpath()).sendKeys(RandomValue.UUIDString());
				}
				else if ("数值".contains(sfList.get(i).getVariableType())) {
					driver.findElementById(sfList.get(i).getIdXpath()).clear();
					driver.findElementById(sfList.get(i).getIdXpath()).sendKeys(RandomValue.randomRangeInt(20, 200));
				}
				else if ("日期".contains(sfList.get(i).getVariableType())) {
					if ("yyyy-MM-dd".contains(sfList.get(i).getDateFormat())) {
						driver.findElementById(sfList.get(i).getIdXpath()).clear();
						driver.findElementById(sfList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMdd());
					}else if ("yyyy-MM-dd HH:mm:ss".contains(sfList.get(i).getDateFormat())) {
						driver.findElementById(sfList.get(i).getIdXpath()).clear();
						driver.findElementById(sfList.get(i).getIdXpath()).sendKeys(RandomValue.randomDateyyyyMMddHHmmss());
					}
				}
				else if ("枚举".contains(sfList.get(i).getVariableType())) {
					new Select(driver.findElementById(sfList.get(i).getIdXpath())).selectByValue(ListAndStringUtils.stringListReturnRandomString(sfList.get(i).getRangeData()));
				}
			}
			// 保存
			driver.findElementById("input-save").click();
			driver.findElementByClassName("u-btn").click();
			Thread.sleep(2000);

		}else {
			return "redirect:/page/error.html";
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);

		return "redirect:/page/ok.html";
	}
	
	
	
}

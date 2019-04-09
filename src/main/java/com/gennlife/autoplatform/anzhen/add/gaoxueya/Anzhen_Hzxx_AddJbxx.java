package com.gennlife.autoplatform.anzhen.add.gaoxueya;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gennlife.autoplatform.utils.RandomValue;

/**
 * @Description: 安贞环境：添加患者信息_添加基本信息方法
 * @author: wangmiao
 * @Date: 2017年6月26日 下午3:15:53 
 */
public class Anzhen_Hzxx_AddJbxx{

	private static final Logger logger = LoggerFactory.getLogger(Anzhen_Hzxx_AddJbxx.class);

	/** 
	* @Title: hzxx_AddJbxx 
	* @Description: 患者信息_添加基本信息（门诊、住院、社区）
	* @param: PhantomJSDriver driver
	* @param: String value 登录到添加页面，返回的成功标志
	* @param: @throws Exception :
	* @return: String 返回默认添加的“研究序列号”
	* @throws 
	*/
	public static String hzxx_AddJbxx_MenZhen_ZhuYuan_SheQu(PhantomJSDriver driver,String value) throws Exception{
		// 日志
		logger.debug("填加患者信息_基本信息");
		
		//研究序列号
		String text=null;
		if ("添加页面".contains(value)) {
			
			//添加操作
			// 病案号
			driver.findElementById("u-crf-PATIENT_ID_NUMBER").clear();
			driver.findElementById("u-crf-PATIENT_ID_NUMBER").sendKeys(RandomValue.UUIDString());

			// 调查时间(日期空间div)，直接xpath定位
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div/input").click();
			driver.findElementByXPath("/html/body/div[9]/div/div/select[2]").click();
			driver.findElementByXPath("html/body/div[9]/div/div/select[2]/option[4]").click();
			driver.findElementByXPath("html/body/div[9]/table/tbody/tr[4]/td[3]/a").click();
			
			//
			Thread.sleep(2000);
			
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div/input").clear();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div/input").sendKeys("姓名"+RandomValue.UUIDString());

			// 性别
			new Select(
					driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div/select"))
					.selectByValue("男");
			
			// 出生日期
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div/input").click();
			driver.findElementByXPath("/html/body/div[9]/div/div/select[1]").click();
			driver.findElementByXPath("/html/body/div[9]/div/div/select[1]/option[82]").click();
			driver.findElementByXPath("/html/body/div[9]/table/tbody/tr[2]/td[3]/a").click();
			
			//
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div/input").clear();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div/input").sendKeys("110230198811110678");
			
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div/input").clear();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div/input").sendKeys("13088889999");
			
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div/input").clear();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div/input").sendKeys("13088889999");
			
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[10]/div/input").clear();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[10]/div/input").sendKeys("姓名"+RandomValue.UUIDString());

			// 联系人与患者关系
			new Select(driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[11]/div/select")).selectByValue("本人");

			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[12]/div/input").clear();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[12]/div/input").sendKeys("13088889999");
			
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[13]/div/input").clear();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[13]/div/input").sendKeys("通讯地址"+RandomValue.UUIDString());

			// 民族、职业、受教育程度、婚姻状况、主要医疗付费方式
			new Select(
					driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[14]/div/select"))
					.selectByValue("满");
			new Select(
					driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[15]/div/select"))
					.selectByValue("专业技术人员");
			new Select(
					driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[16]/div/select"))
					.selectByValue("硕士及以上");
			new Select(
					driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[17]/div/select"))
					.selectByValue("已婚");
			new Select(
					driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[18]/div/select"))
					.selectByValue("城镇居民基本医疗保险");
			
			Thread.sleep(2000);
			
			// 保存
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//*[@id='alert-container']/div/div/div/div[3]/button").click();
			
			//获取默认添加的研究序列号
			text = driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div/input").getAttribute("value");
			
		}else {
			logger.debug("登陆失败");
		}
		
		return text;

	}
	
	
	/** 
	 * @Title: hzxx_AddJbxx 
	 * @Description: 患者信息_添加基本信息（体检）
	 * @param: PhantomJSDriver driver
	 * @param: String value 登录到添加页面，返回的成功标志
	 * @param: @throws Exception :
	 * @return: String 返回默认添加的“研究序列号”
	 * @throws 
	 */
	public static String hzxx_AddJbxx_TiJian(PhantomJSDriver driver,String value) throws Exception{
		// 日志
		logger.debug("填加患者信息_基本信息");
		
		//研究序列号
		String text=null;
		if ("添加页面".contains(value)) {
			
			//添加操作
			// 体检登记号crf-PATIENT_ID_NUMBER1
			driver.findElementById("u-crf-PATIENT_ID_NUMBER1").clear();
			driver.findElementById("u-crf-PATIENT_ID_NUMBER1").sendKeys("1102301988111106789");
			
			// 调查时间(日期空间div)，直接xpath定位
			// 调查时间(日期空间div)，直接xpath定位
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div/input").click();
			driver.findElementByXPath("/html/body/div[9]/div/div/select[2]").click();
			driver.findElementByXPath("html/body/div[9]/div/div/select[2]/option[4]").click();
			driver.findElementByXPath("html/body/div[9]/table/tbody/tr[4]/td[3]/a").click();
			
			//
			Thread.sleep(2000);
			
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div/input").clear();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div/input").sendKeys("姓名"+RandomValue.UUIDString());
			
			// 性别
			new Select(
					driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div/select"))
			.selectByValue("男");
			
			// 出生日期
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div/input").click();
			driver.findElementByXPath("/html/body/div[9]/div/div/select[1]").click();
			driver.findElementByXPath("/html/body/div[9]/div/div/select[1]/option[82]").click();
			driver.findElementByXPath("/html/body/div[9]/table/tbody/tr[2]/td[3]/a").click();
			
			//通讯地址
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div/input").clear();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div/input").sendKeys("通讯地址"+RandomValue.UUIDString());
			
			// 民族
			new Select(
					driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div/select"))
			.selectByValue("满");
			
			// 保存
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
			Thread.sleep(2000);
			driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
			
			//获取默认添加的研究序列号
			text = driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div/input").getAttribute("value");
			
		}else {
			logger.debug("登陆失败");
		}
		
		return text;
		
	}
	
}

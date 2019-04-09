package com.gennlife.crf.yantai.add.zhongliuneiyike;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gennlife.crf.utils.RandomValue;

/**
 * @Description: 烟台环境：添加患者信息_患者基本信息方法
 * @author: wangmiao
 * @Date: 2017年7月3日 下午3:27:06 
 */
public class Yantai_Hzxx_AddHzjbxx{

	private static final Logger logger = LoggerFactory.getLogger(Yantai_Hzxx_AddHzjbxx.class);

	/** 
	* @Title: hzxx_AddHzjbxx 
	* @Description: 患者信息_患者基本信息
	* @param: PhantomJSDriver driver
	* @param: String value 登录到添加页面，返回的成功标志
	* @param: @throws Exception :
	* @return: String 返回所添加的“患者编号”
	* @throws 
	*/
	public static String hzxx_AddHzjbxx(PhantomJSDriver driver,String value) throws Exception{
		
		// 日志
		logger.debug("患者信息_患者基本信息");
		
		//患者编号
		String text=null;
		if ("添加页面".contains(value)) {
			
			//添加操作
			//患者编号（必填项）
			driver.findElementById("PATIENT_SN").clear();
			driver.findElementById("PATIENT_SN").sendKeys(RandomValue.UUIDString());
			
			//姓名
			driver.findElementById("NAME").clear();
			driver.findElementById("NAME").sendKeys("姓名"+RandomValue.UUIDString());

			// 性别
			new Select(
					driver.findElementById("GENDER"))
					.selectByValue("男");
			
			//民族
			new Select(
					driver.findElementById("ETHNIC"))
					.selectByValue("回族");
			
			//出生日期
			driver.findElementById("BIRTH_DATE").click();
			driver.findElementByXPath("/html/body/div[10]/div/div/select[1]").click();
			driver.findElementByXPath("/html/body/div[10]/div/div/select[1]/option[82]").click();
			driver.findElementByXPath("/html/body/div[10]/table/tbody/tr[1]/td[3]/a").click();
			
			//婚姻
			new Select(
					driver.findElementById("MARITAL_STATUS"))
			.selectByValue("已婚");
			
			//文化程度
			new Select(
					driver.findElementById("EDUCATION_DEGREE"))
			.selectByValue("初中");
			
			//患者ABO血型
			new Select(
					driver.findElementById("BLOOD_ABO"))
			.selectByValue("A");
			
			//患者Rh血型
			new Select(
					driver.findElementById("BLOOD_RH"))
			.selectByValue("阴性");
			
			//患者类型
			new Select(
					driver.findElementById("PATIENT_TYPE"))
			.selectByValue("本市城镇");
			
			Thread.sleep(2000);
			
			//身高（cm）
			driver.findElementById("HEIGHT").clear();
			driver.findElementById("HEIGHT").sendKeys(RandomValue.randomRangeInt(100,200));
			
			//体重（kg）
			driver.findElementById("WEIGHT").clear();
			driver.findElementById("WEIGHT").sendKeys(RandomValue.randomRangeInt(100,200));
			
			//BMI（kg/m2）
			driver.findElementById("BMI").clear();
			driver.findElementById("BMI").sendKeys(RandomValue.randomRangeInt(10,20));
			
			//患者联系地址
			driver.findElementById("PATIENT_ADDRESS").clear();
			driver.findElementById("PATIENT_ADDRESS").sendKeys("患者联系地址"+RandomValue.UUIDString());
			
			//患者联系电话
			driver.findElementById("PATIENT_CONTACT").clear();
			driver.findElementById("PATIENT_CONTACT").sendKeys("13300001111");
			
			//患者工作单位
			driver.findElementById("PATIENT_WORKUNIT").clear();
			driver.findElementById("PATIENT_WORKUNIT").sendKeys("患者工作单位"+RandomValue.UUIDString());
			
			//亲属联系地址
			driver.findElementById("RELATIVE_ADDRESS").clear();
			driver.findElementById("RELATIVE_ADDRESS").sendKeys("亲属联系地址"+RandomValue.UUIDString());
			
			//亲属联系电话
			driver.findElementById("RELATIVE_CONTACT").clear();
			driver.findElementById("RELATIVE_CONTACT").sendKeys("13300002222");
			
			// 保存
			driver.findElementById("input-save").click();
			Thread.sleep(2000);
			driver.findElementByXPath("/html/body/div[5]/div/div/div/div[3]/button").click();
			
			//获取默认添加的研究序列号
			text = driver.findElementById("PATIENT_SN").getAttribute("value");
			
		}else {
			logger.debug("登陆失败");
		}
		
		return text;

	}
	
	
	
	
}

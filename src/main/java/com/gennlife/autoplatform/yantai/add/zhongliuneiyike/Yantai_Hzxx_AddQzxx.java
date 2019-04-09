package com.gennlife.autoplatform.yantai.add.zhongliuneiyike;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

import com.gennlife.autoplatform.utils.RandomValue;

/**
 * @Description: 烟台环境：添加患者信息_确诊信息
 * @author: wangmiao
 * @Date: 2017年7月4日 上午9:05:04 
 */
public class Yantai_Hzxx_AddQzxx{

	/** 
	* @Title: hzxx_AddQzxx 
	* @Description: 添加患者信息_确诊信息
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddQzxx(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();
		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);
		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(2000);
		
		//到确诊信息
		driver.findElementByXPath("/html/body/div[2]/div[2]/li/ul/li[2]/a/span[2]").click();
		
		//主要诊断
		driver.findElementById("MAIN_DIAGNOSIS").clear();
		driver.findElementById("MAIN_DIAGNOSIS").sendKeys(RandomValue.UUIDString());
		
		//主要诊断时间
		driver.findElementById("MAIN_DIAGNOSIS_DATE").click();
		driver.findElementByXPath("/html/body/div[10]/table/tbody/tr[1]/td[6]/a").click();
		
		//次要诊断1
		driver.findElementById("SECONDARY_DIAGNOSIS1").clear();
		driver.findElementById("SECONDARY_DIAGNOSIS1").sendKeys(RandomValue.UUIDString());
		
		//次要诊断1时间
		driver.findElementById("SECONDARY_DIAGNOSIS1_DATE").click();
		driver.findElementByXPath("/html/body/div[10]/table/tbody/tr[1]/td[7]/a").click();

		//次要诊断2
		driver.findElementById("SECONDARY_DIAGNOSIS2").clear();
		driver.findElementById("SECONDARY_DIAGNOSIS2").sendKeys(RandomValue.UUIDString());
		
		//次要诊断2时间
		driver.findElementById("SECONDARY_DIAGNOSIS2_DATE").click();
		driver.findElementByXPath("/html/body/div[10]/table/tbody/tr[1]/td[6]/a").click();
		
		//次要诊断3
		driver.findElementById("SECONDARY_DIAGNOSIS3").clear();
		driver.findElementById("SECONDARY_DIAGNOSIS3").sendKeys(RandomValue.UUIDString());
		
		//次要诊断3时间
		driver.findElementById("SECONDARY_DIAGNOSIS3_DATE").click();
		driver.findElementByXPath("/html/body/div[10]/table/tbody/tr[1]/td[7]/a").click();
		
		//次要诊断4
		driver.findElementById("SECONDARY_DIAGNOSIS4").clear();
		driver.findElementById("SECONDARY_DIAGNOSIS4").sendKeys(RandomValue.UUIDString());
		
		//次要诊断4时间
		driver.findElementById("SECONDARY_DIAGNOSIS4_DATE").click();
		driver.findElementByXPath("/html/body/div[10]/table/tbody/tr[1]/td[6]/a").click();
		
		//确诊年龄
		driver.findElementById("AGE_CONFIRMED").clear();
		driver.findElementById("AGE_CONFIRMED").sendKeys(RandomValue.randomRangeInt(20,80));
		
		//入院时间
		driver.findElementById("INPATIENT_DATE").click();
		driver.findElementByXPath("/html/body/div[10]/table/tbody/tr[1]/td[6]/a").click();

		//肿瘤位置
		driver.findElementById("G_CA_LOCATION").clear();
		driver.findElementById("G_CA_LOCATION").sendKeys(RandomValue.UUIDString());
		
		//住院时间（天）
		driver.findElementById("INPATIENT_DAYS").clear();
		driver.findElementById("INPATIENT_DAYS").sendKeys("2017-07-01 00:00:00");
		
		//主诉
		driver.findElementById("G_INPATIENT_COMPLAINT").clear();
		driver.findElementById("G_INPATIENT_COMPLAINT").sendKeys(RandomValue.UUIDString());
		
		//主诉时间
		driver.findElementById("G_COMPLAINT_DAYS").click();
		driver.findElementByXPath("/html/body/div[10]/table/tbody/tr[1]/td[6]/a").click();

		//ECOG评分
		new Select(
				driver.findElementById("G_ECOG_SCORE"))
				.selectByValue("3");
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
}

package com.gennlife.autoplatform.yantai.add.test4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gennlife.autoplatform.utils.RandomValue;

/**
 * @Description: 烟台环境：添加随访
 * @author: wangmiao
 * @Date: 2017年7月11日 上午8:36:04 
 */
public class Yantai_Sf_Addsf {

	private static final Logger logger = LoggerFactory.getLogger(Yantai_Sf_Addsf.class);

	/** 
	 * @Title: sf_AddSf
	 * @Description: 添加随访
	 * @param: PhantomJSDriver driver
	 * @param: String xpath  新添加的个人信息的xpath
	 * @param: @throws Exception
	 * @return: String
	 * @throws 
	 */
	public static String sf_AddSf(PhantomJSDriver driver, String xpath) throws Exception {

		// 日志
		logger.debug("随访");
		
		// 到已添加基本信息页面
		driver.findElementByXPath(xpath).click();

		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();
		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);
		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));

		Thread.sleep(2000);

		// 添加随访
		driver.findElementByXPath("html/body/div[2]/div[1]/ul/li/a").click();
		driver.findElementByXPath(".//*[@id='add-followup']").click();
		
		// 随访
		//随访日期
		driver.findElementById("FOLLOW_UP_DATE").sendKeys("2010-07-01 00:00:00");

		//随访方式
		new Select(driver.findElementById("FOLLOW_UP_TYPE")).selectByValue("电话");
		
		// 回访时主诉
		driver.findElementById("FOLLOW_UP_COMPLAINT").clear();
		driver.findElementById("FOLLOW_UP_COMPLAINT").sendKeys(RandomValue.UUIDString());
		
		//回访时NIHSS评分
		driver.findElementById("FOLLOW_UP_NIHSS").clear();
		driver.findElementById("FOLLOW_UP_NIHSS").sendKeys(RandomValue.randomRangeInt(10, 80));
		
		//回访时mRS评分
		driver.findElementById("FOLLOW_UP_MRS").clear();
		driver.findElementById("FOLLOW_UP_MRS").sendKeys(RandomValue.randomRangeInt(10, 80));
		
		//血浆总胆固醇浓度
		driver.findElementById("FOLLOW_UP_TC").clear();
		driver.findElementById("FOLLOW_UP_TC").sendKeys(RandomValue.randomRangeInt(10, 80));
		
		//高密度脂蛋白
		driver.findElementById("FOLLOW_UP_HDL").clear();
		driver.findElementById("FOLLOW_UP_HDL").sendKeys(RandomValue.randomRangeInt(20, 80));
		
		//低密度脂蛋白
		driver.findElementById("FOLLOW_UP_LDL").clear();
		driver.findElementById("FOLLOW_UP_LDL").sendKeys(RandomValue.randomRangeInt(50, 90));
		
		//甘油三酯
		driver.findElementById("FOLLOW_UP_TRIG").clear();
		driver.findElementById("FOLLOW_UP_TRIG").sendKeys(RandomValue.randomRangeInt(10, 40));
		
		//肌酐
		driver.findElementById("FOLLOW_UP_CREA").clear();
		driver.findElementById("FOLLOW_UP_CREA").sendKeys(RandomValue.randomRangeInt(10, 70));
		
		//尿素氮
		driver.findElementById("FOLLOW_UP_BUN").clear();
		driver.findElementById("FOLLOW_UP_BUN").sendKeys(RandomValue.randomRangeInt(10, 60));
		
		//血尿酸
		driver.findElementById("FOLLOW_UP_UA").clear();
		driver.findElementById("FOLLOW_UP_UA").sendKeys(RandomValue.randomRangeInt(10, 80));
		
		//红细胞计数
		driver.findElementById("FOLLOW_UP_RBC").clear();
		driver.findElementById("FOLLOW_UP_RBC").sendKeys(RandomValue.randomRangeInt(100, 500));
		
		//血红蛋白
		driver.findElementById("FOLLOW_UP_HGB").clear();
		driver.findElementById("FOLLOW_UP_HGB").sendKeys(RandomValue.randomRangeInt(100, 400));
		
		//白细胞计数
		driver.findElementById("FOLLOW_UP_WBC").clear();
		driver.findElementById("FOLLOW_UP_WBC").sendKeys(RandomValue.randomRangeInt(150, 400));
		
		//血小板计数
		driver.findElementById("FOLLOW_UP_PLT").clear();
		driver.findElementById("FOLLOW_UP_PLT").sendKeys(RandomValue.randomRangeInt(200, 400));
		
		//空腹血糖
		driver.findElementById("FOLLOW_UP_FASTING_GLUCOSE").clear();
		driver.findElementById("FOLLOW_UP_FASTING_GLUCOSE").sendKeys(RandomValue.randomRangeInt(10, 50));
		
		//血浆同型半胱氨酸
		driver.findElementById("FOLLOW_UP_CYSTEINE").clear();
		driver.findElementById("FOLLOW_UP_CYSTEINE").sendKeys(RandomValue.randomRangeInt(100, 400));
		
		//颈动脉彩超结果
		driver.findElementById("FOLLOW_UP_CA").clear();
		driver.findElementById("FOLLOW_UP_CA").sendKeys(RandomValue.UUIDString());
		
		//经颅多普勒结果
		driver.findElementById("FOLLOW_UP_TCD").clear();
		driver.findElementById("FOLLOW_UP_TCD").sendKeys(RandomValue.UUIDString());

		// 保存
		driver.findElementById("input-save").click();
		Thread.sleep(2000);
		driver.findElementByXPath(".//*[@id='alert-container']/div/div/div/div[3]/button").click();
				
		return "ok";

	}

}

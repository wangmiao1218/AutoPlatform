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
 * @Description: 烟台环境：添加住院_出院时情况
 * @author: wangmiao
 * @Date: 2017年7月6日 下午2:15:26
 */
public class Yantai_Zy_AddCysqk {

	private static final Logger logger = LoggerFactory.getLogger(Yantai_Zy_AddCysqk.class);

	/** 
	 * @Title: zy_AddRysqk
	 * @Description: 添加住院_出院时情况
	 * @param: PhantomJSDriver driver
	 * @param: String xpath  新添加的个人信息的xpath
	 * @param: @throws Exception
	 * @return: String
	 * @throws 
	 */
	public static String zy_AddCysqk(PhantomJSDriver driver, String xpath) throws Exception {
		// 日志
		logger.debug("出院时情况");
		
		// 到已添加基本信息页面
		driver.findElementByXPath(xpath).click();

		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();
		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);
		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));

		Thread.sleep(2000);

		//到出院时情况
		driver.findElementByXPath(".//*[@id='crf-data-tree_10_span']").click();
		
		// 出院时NIHSS评分
		driver.findElementById("DIS_NIHSSA").clear();
		driver.findElementById("DIS_NIHSSA").sendKeys(RandomValue.randomRangeInt(20, 67));
		
		//出院时GCS评分
		driver.findElementById("DIS_GCS").clear();
		driver.findElementById("DIS_GCS").sendKeys(RandomValue.randomRangeInt(20, 67));
		
		//出院时mRS评分
		driver.findElementById("DIS_MRS").clear();
		driver.findElementById("DIS_MRS").sendKeys(RandomValue.randomRangeInt(20, 67));
		
		// 出院诊断_主要诊断
		new Select(driver.findElementById("DIS_MAIN_DIAGNOSIS")).selectByValue("脑梗死");
		
		//TOAST分型
		new Select(driver.findElementById("TOAST")).selectByValue("心源性脑栓塞");
		
		// 保存
		driver.findElementById("input-save").click();
		Thread.sleep(2000);
		driver.findElementByXPath(".//*[@id='alert-container']/div/div/div/div[3]/button").click();
				
		return "ok";

	}

}

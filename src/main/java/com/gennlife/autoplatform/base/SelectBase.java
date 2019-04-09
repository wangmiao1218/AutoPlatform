package com.gennlife.autoplatform.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gennlife.autoplatform.bean.CrfTemplate;
import com.gennlife.autoplatform.utils.ListAndStringUtils;

/**
 * @Description: 下拉框通用的方法,其他下拉框直接继承即可
 * @author: wangmiao
 * @Date: 2017年6月9日 上午10:05:20 
 */
public class SelectBase {
	
	private static final Logger logger = LoggerFactory.getLogger(SelectBase.class);
	
	/** 
	* @Title: selectBase 
	* @Description: 下拉框基本方法，其他下拉框直接使用
	* @param: CrfTemplate crfTemplate 
	* @param: String string 
	* @param: PhantomJSDriver driver
	* @return: void
	* @throws 
	*/
	public static CrfTemplate selectBase(PhantomJSDriver driver,String value,CrfTemplate crfTemplate){
		if (crfTemplate.getEnglishName()==null) {
			logger.debug("无"+crfTemplate.getEnglishName()+"字段");
			return null;
		}
		if (crfTemplate.getEnglishName()!=null) {
			logger.debug("开始登陆...");
			String listToString = null;
			if ("添加页面".equals(value)) {
				String xpath = crfTemplate.getXpath();
				Select selall = new Select(driver.findElement(By.xpath(xpath)));
				List<WebElement> lw= selall.getOptions();
				listToString = ListAndStringUtils.listWebElementToSelectString(lw);
			}else {
				logger.debug("页面加载失败...");
			}
			if (listToString!=null) {
				crfTemplate.setOutput(listToString);
			}
			String rangeData =ListAndStringUtils.trimString(crfTemplate.getRangeData());
			String output = crfTemplate.getOutput();
			if (rangeData.contains(output) && output.contains(rangeData)) {
				crfTemplate.setResult("pass");
			}else if(!rangeData.contains(output) || !output.contains(rangeData)){
				crfTemplate.setResult("no");
			}
		}
		return crfTemplate;
	}
	
}

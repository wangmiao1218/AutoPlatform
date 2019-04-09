package com.gennlife.crf.keyanbao;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @Description: 科研宝爬页面元素
 * @author: wangmiao
 * @Date: 2017年8月23日 下午12:27:20 
 */
public class FindElementsOfKeyanbao {

	public static List<WebElement> findElements(PhantomJSDriver driver,String value) {
		List<WebElement> list =null;
		if ("添加页面".equals(value)) {
			list = driver.findElementsByXPath(".//*[@id='inputform']/div/div/table/tbody//span");
			
		}
		
		/*for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			System.out.println(list.get(i).getText());
		}*/
		
		return list;
	}
	
	
}

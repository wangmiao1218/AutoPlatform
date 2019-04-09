package com.gennlife.autoplatform.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @Description: 测试rws页面响应时间
 * @author: wangmiao
 * @Date: 2018年11月16日 下午2:46:41 
 */
public class RwsResponseTime{

	
	
	
	public static String rwsEvent(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(2000);
		
		//到个人病史页面
		driver.findElementByXPath("/html/body/div[2]/div[1]/div[2]/li[1]/ul/li[2]/a").click();
		
		//高血压病
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div/select"))
				.selectByValue("有");
		
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[2]/div/input").sendKeys("5");

		//是否自备血压测量工具
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[22]/div/select"))
				.selectByValue("有");

		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
}

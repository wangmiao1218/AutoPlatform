package com.gennlife.autoplatform.anzhen.add.gaoxueya;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @Description: 添加患者信息_家族史
 * @author: wangmiao
 * @Date: 2017年6月27日 上午8:36:28 
 */
public class Anzhen_Hzxx_AddJzs{

	/** 
	* @Title: hzxx_AddJzs 
	* @Description: 患者信息_添加家族史
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddJzs(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(2000);
		//到家族史页面
		driver.findElementByXPath("/html/body/div[2]/div[1]/div[2]/li[1]/ul/li[3]/a/span[2]").click();
		
		//有无冠心病家族史
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div/select"))
				.selectByValue("有");

		//冠心病家族史类型
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div/div/ul/li[1]/a/label").click();
		
		//有无最小发病年龄（冠心病）
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div/input").sendKeys("55");
		
		//有无脑卒中家族史
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div/select"))
				.selectByValue("有");
		
		//脑卒中家族史类型
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div/div/ul/li[2]/a/label").click();
		
		//有无最小发病年龄（脑卒中）
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div/input").sendKeys("55");
		
		//有无高血压家族史
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div/select"))
				.selectByValue("有");
		
		//高血压家族史类型
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[10]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[10]/div/div/ul/li[3]/a/label").click();
				
		//有无最小发病年龄（高血压）
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[11]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[12]/div/input").sendKeys("45");
		
		//有无糖尿病家族史
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[13]/div/select"))
				.selectByValue("有");
		
		//糖尿病家族史类型
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[14]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[14]/div/div/ul/li[2]/a/label").click();
		
		//有无最小发病年龄（糖尿病）
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[15]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[16]/div/input").sendKeys("60");
		
		//有无血脂异常家族史
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[17]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[18]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[18]/div/div/ul/li[2]/a/label").click();
		
		//有无最小发病年龄（血脂异常）
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[19]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[20]/div/input").sendKeys("30");
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
}

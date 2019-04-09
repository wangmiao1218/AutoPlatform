package com.gennlife.autoplatform.anzhen.add.gaoxueya;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @Description: 安贞环境：添加患者信息_添加个人病史
 * @author: wangmiao
 * @Date: 2017年6月26日 下午3:15:53 
 */
public class Anzhen_Hzxx_AddGrbs{

	/** 
	* @Title: hzxx_AddGrbs_Gxyb 
	* @Description: 患者信息_添加个人病史_高血压病
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddGrbs_Gxyb(PhantomJSDriver driver,String xpath) throws Exception{
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
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[3]/div/input").sendKeys("5");
		
		//一年内服降压药物频率
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[4]/div/select"))
				.selectByValue("每天");
		
		//是否定期开药/买药
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[5]/div/select"))
				.selectByValue("是");
		//取药间隔时间
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[6]/div/select"))
				.selectByValue("半个月");
		
		//取药地点
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[7]/div/select"))
				.selectByValue("医疗机构");
		
		//一年内是否测血压
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[8]/div/select"))
				.selectByValue("是");
		
		//血压测量频率单位
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[9]/div/select"))
				.selectByValue("次/天");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[10]/div/input").sendKeys("5");
		
		//是否有最高血压水平数值
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[11]/div/select"))
				.selectByValue("有");
		//曾达到最高收缩压（mmHg）
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[12]/div/input").sendKeys("100");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[13]/div/input").sendKeys("100");
		
		//一年内，是否做过动态血压监测
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[14]/div/select"))
				.selectByValue("是");
		
		//最近两周血压情况
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[15]/div/select"))
				.selectByValue("测量多次");

		//测量多次有无最高极值
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[16]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[17]/div/input").sendKeys("100");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[18]/div/input").sendKeys("100");
		
		//测量多次有无最低极值
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[19]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[20]/div/input").sendKeys("100");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[21]/div/input").sendKeys("100");
		
		//是否自备血压测量工具
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[22]/div/select"))
				.selectByValue("有");
		
		//自备血压测量工具类型(多选)
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[23]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[23]/div/div/ul/li[1]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[23]/div/div/ul/li[2]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[23]/div/div/ul/li[3]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[23]/div/div/ul/li[4]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[23]/div/div/ul/li[5]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[23]/div/div/ul/li[6]/a/label").click();
		
		//是否自我记录血压
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[24]/div/select"))
				.selectByValue("是");

		//自我记录血压方式(多选)
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[25]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[25]/div/div/ul/li[1]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[25]/div/div/ul/li[2]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[25]/div/div/ul/li[3]/a/label").click();
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
	/** 
	* @Title: hzxx_AddGrbs_Tdxyc 
	* @Description: 患者信息_添加个人病史_糖代谢异常
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddGrbs_Tdxyc(PhantomJSDriver driver,String xpath) throws Exception{
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
		
		//糖代谢异常
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[1]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[2]/div/input").sendKeys("5");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[3]/div/input").sendKeys("5");
		
		//一年内降糖药物治疗频率
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[4]/div/select"))
				.selectByValue("每天");
		
		//糖代谢异常分型
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[5]/div/select"))
				.selectByValue("1 型糖尿病");
		
		//有无空腹血糖情况
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[6]/div/select"))
				.selectByValue("有");
		
		//空腹血糖情况单位
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[7]/div/select"))
				.selectByValue("mmol/L");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[8]/div/input").sendKeys("5");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[9]/div/input").sendKeys("5");
		
		//有无餐后2小时血糖情况
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[10]/div/select"))
				.selectByValue("有");
		
		//餐后2小时血糖情况单位
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[11]/div/select"))
				.selectByValue("mmol/L");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[12]/div/input").sendKeys("5");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[13]/div/input").sendKeys("5");
		
		//有无糖化血红蛋白情况
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[14]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[15]/div/input").sendKeys("5");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[16]/div/input").sendKeys("5");
		
		//并发症
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[17]/div/select"))
				.selectByValue("有");
		
		//并发症类型(多选)
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[18]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[18]/div/div/ul/li[1]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[18]/div/div/ul/li[2]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[18]/div/div/ul/li[3]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[18]/div/div/ul/li[4]/a/label").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[18]/div/div/ul/li[5]/a/label").click();

		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
	/** 
	* @Title: hzxx_AddGrbs_Xzyc 
	* @Description: 患者信息_添加个人病史_血脂异常
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddGrbs_Xzyc(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(3000);
		
		//到个人病史页面
		driver.findElementByXPath("/html/body/div[2]/div[1]/div[2]/li[1]/ul/li[2]/a").click();
		
		//血脂异常
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[1]/div/select"))
				.selectByValue("有");
		
		//是否有血脂异常分型不详
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[2]/div/select"))
				.selectByValue("有异常类型");
		
		Thread.sleep(3000);
		
		//血脂异常类型(多选)
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[3]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[3]/div/div/ul/li[1]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[3]/div/div/ul/li[2]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[3]/div/div/ul/li[3]/a/label").click();

		//高胆固醇血症病程（年）
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[4]/div/input").sendKeys("5");;
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[5]/div/input").sendKeys("5");;
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[6]/div/input").sendKeys("5");;
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
	/** 
	* @Title: hzxx_AddGrbs_Gxb 
	* @Description: 患者信息_添加个人病史_冠心病
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddGrbs_Gxb(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(3000);
		
		//到个人病史页面
		driver.findElementByXPath("/html/body/div[2]/div[1]/div[2]/li[1]/ul/li[2]/a").click();
		
		//冠心病
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[1]/div/select"))
				.selectByValue("有");
		
		//是否冠心病分型不详
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[2]/div/select"))
				.selectByValue("有异常类型");
		
		Thread.sleep(3000);
		
		//冠心病类型(多选)
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[3]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[3]/div/div/ul/li[1]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[3]/div/div/ul/li[2]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[3]/div/div/ul/li[3]/a/label").click();
		
		//心绞痛首次发作时间（年份）
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[4]/div/input").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[5]/div/input").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[6]/div/input").click();
		
		//有无冠状动脉血运重建史
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[5]/div/select"))
				.selectByValue("未做");
		
		//冠状动脉血运重建史类型（多选）
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[6]/div/div/button").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[8]/div/div/ul/li[1]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[8]/div/div/ul/li[2]/a/label").click();
		
		//PCI首次时间（年份）
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[9]/div/input").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[10]/div/input").click();
		
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
	/** 
	* @Title: hzxx_AddGrbs_Nxgb 
	* @Description: 患者信息_添加个人病史_脑血管病
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddGrbs_Nxgb(PhantomJSDriver driver,String xpath) throws Exception{
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
		
		//脑血管病
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[1]/div/select"))
				.selectByValue("有");
		
		Thread.sleep(2000);
		
		//是否脑血管分型不详
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[2]/div/select"))
				.selectByValue("有异常类型");
		
		Thread.sleep(2000);
		
		//脑血管病分型(多选)
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/ul/li[1]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/ul/li[2]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/ul/li[3]/a/label").click();
		
		//脑缺血首次发作时间（年份）
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[4]/div/input").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[5]/div/input").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[6]/div/input").click();
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
	/** 
	* @Title: hzxx_AddGrbs_FcAndMxxsAndMxszjb 
	* @Description: 患者信息_添加个人病史_房颤_慢性心衰_慢性肾脏疾病
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddGrbs_FcAndMxxsAndMxszjb(PhantomJSDriver driver,String xpath) throws Exception{
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
		
		//房颤
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[1]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[2]/div/input").sendKeys("5");
		
		//慢性心衰
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[1]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[2]/div/input").sendKeys("5");
		
		//慢性肾脏疾病
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div[3]/div[1]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div[3]/div[2]/div/input").sendKeys("5");
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	/** 
	* @Title: hzxx_AddGrbs_Yymqzd 
	* @Description: 患者信息_添加个人病史_医院明确诊断的有无下肢/颈动脉粥样硬化_医院明确诊断的焦虑/抑郁
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddGrbs_Yymqzd(PhantomJSDriver driver,String xpath) throws Exception{
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
		
		//医院明确诊断的有无下肢/颈动脉粥样硬化
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div[3]/div[1]/div/select"))
				.selectByValue("有");

		//下肢/颈动脉粥样硬化类型(多选)
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div[3]/div[2]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div[3]/div[2]/div/div/ul/li[1]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div[3]/div[2]/div/div/ul/li[2]/a/label").click();
		
		Thread.sleep(2000);
		
		//下肢动脉症状
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div[3]/div[3]/div/select"))
				.selectByValue("有症状(如下肢发凉、麻木,间歇性跛行,静息痛等)");
		
		//颈动脉症状
		/*new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[9]/div[3]/div[4]/div/select"))
				.selectByValue("有症状(如黑矇)");*/
		
		//医院明确诊断的焦虑/抑郁
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[10]/div[3]/div[1]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[10]/div[3]/div[2]/div/input").sendKeys("5");
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
}

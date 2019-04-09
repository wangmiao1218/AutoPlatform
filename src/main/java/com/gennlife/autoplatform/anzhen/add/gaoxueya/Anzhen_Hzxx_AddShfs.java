package com.gennlife.autoplatform.anzhen.add.gaoxueya;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @Description: 安贞环境：添加患者信息_添加生活方式
 * @author: wangmiao
 * @Date: 2017年6月26日 下午3:15:53 
 */
public class Anzhen_Hzxx_AddShfs{

	/** 
	* @Title: hzxx_AddShfs_XyAndYj 
	* @Description: 患者信息_添加生活方式_吸烟_饮酒
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddShfs_XyAndYj(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(2000);
		
		//到生活方式页面
		driver.findElementByXPath("/html/body/div[2]/div[1]/div[2]/li[1]/ul/li[4]/a/span[2]").click();
		
		//吸烟频率
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div/select"))
				.selectByValue("经常吸");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[2]/div/input").sendKeys("5");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[3]/div/input").sendKeys("6");
		
		//一天之中在家中、工作及公共场所接触15分钟以上二手烟频率
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[1]/div[3]/div[4]/div/select"))
				.selectByValue("≥5 天/周");
		
		//饮酒频率
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[1]/div/select"))
				.selectByValue("经常饮(≥1 次/周)");
		
		//饮酒类型
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[2]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[2]/div/div/ul/li[1]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[2]/div/div/ul/li[2]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[2]/div/div/ul/li[3]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[2]/div/div/ul/li[4]/a/label").click();
		
		Thread.sleep(2000);
		
		//饮白酒量（两）
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[3]/div/input").sendKeys("10");
		//饮白酒频率（次／周）
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[4]/div/input").sendKeys("6");
		//白酒度数
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[5]/div/input").sendKeys("50");
		
		//红/黄酒量(ml)
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[6]/div/input").sendKeys("77");
		//红/黄酒频率（次／周）
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[7]/div/input").sendKeys("5");
		
		//啤酒量(ml)
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[8]/div/input").sendKeys("500");
		//啤酒频率（次／周）
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[9]/div/input").sendKeys("4");
		
		//米酒量(ml)
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[10]/div/input").sendKeys("500");
		//米酒频率（次／周）
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[2]/div[3]/div[11]/div/input").sendKeys("6");
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
	/** 
	* @Title: hzxx_AddShfs_SsAndYdAndJsyl 
	* @Description: 患者信息_添加生活方式_膳食_运动_精神压力
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddShfs_SsAndYdAndJsyl(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(2000);
		
		//到生活方式页面
		driver.findElementByXPath("/html/body/div[2]/div[1]/div[2]/li[1]/ul/li[4]/a/span[2]").click();
				
		//膳食
		//过去一年来,膳食咸淡口味
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[1]/div/select"))
				.selectByValue("口味清淡");
		
		//过去一年来,膳食荤素习惯
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[3]/div[3]/div[2]/div/select"))
		.selectByValue("以素食为主");
		
		//运动
		//过去一年来,工作之外,每次超过 30 分钟的体力活动(如步行、慢跑、骑车、游泳、做操、跳舞等)的频率
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[1]/div/select"))
				.selectByValue("3-4天/周");
		
		//过去一年来,坐姿的时间（小时／天）（工作日）
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[2]/div/input").sendKeys("3");
		
		//过去一年来,坐姿的时间（小时／天）（周末或休息日）
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[4]/div[3]/div[3]/div/input").sendKeys("10");
		
		//精神压力
		//过去一个月您是否有精神压力
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[1]/div/select"))
				.selectByValue("是");
		
		//精神压力频率
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[2]/div/select"))
				.selectByValue("经常感到");
		
		//来源(多选)
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/ul/li[1]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/ul/li[2]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/ul/li[3]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/ul/li[4]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/ul/li[5]/a/label").click();
		//driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[5]/div[3]/div[3]/div/div/ul/li[6]/a/label").click();

		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
	
	/** 
	* @Title: hzxx_AddShfs_Gqygyqxzk 
	* @Description: 患者信息_添加生活方式_过去一个月情绪状况
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddShfs_Gqygyqxzk(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(2000);
		
		//到生活方式页面
		driver.findElementByXPath("/html/body/div[2]/div[1]/div[2]/li[1]/ul/li[4]/a/span[2]").click();
				
		//过去一个月情绪状况
		//感到紧张或痛苦
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[1]/div/select"))
				.selectByValue("几乎所有时候");
		
		//对以往感兴趣的事还是有兴趣
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[2]/div/select"))
				.selectByValue("不像以前那样多");
		
		//感到有点害怕,好像预感到有什么可怕事情要发生
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[3]/div/select"))
				.selectByValue("有一点,但并不苦恼");
		
		//能开心大笑,并看到事物好的一面
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[4]/div/select"))
				.selectByValue("经常这样");
		
		//心中充满烦恼
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[5]/div/select"))
				.selectByValue("时时但不经常");

		//感到愉快
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[6]/div/select"))
				.selectByValue("并不经常");
		
		//能安静而轻松地坐着
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[7]/div/select"))
				.selectByValue("并不经常");
		
		//对仪容失去兴趣
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[8]/div/select"))
				.selectByValue("肯定");
		
		//有点坐立不安,好像感到非要活动不可
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[9]/div/select"))
				.selectByValue("是不少");
		
		//对一切都乐观地向前看
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[10]/div/select"))
				.selectByValue("很少这样");
		
		//突然出现恐慌感
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[11]/div/select"))
				.selectByValue("有时");
		
		//能好像感到情绪在渐渐低落
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[12]/div/select"))
				.selectByValue("经常");
		
		//感到有点害怕,好像某个内脏器官变坏
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[13]/div/select"))
				.selectByValue("有时");
		
		//能欣赏一本好书或一项好的广播或电视节目
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[6]/div[3]/div[14]/div/select"))
				.selectByValue("常常");
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}
	
	
	/** 
	* @Title: hzxx_AddShfs_Smzk 
	* @Description: 患者信息_添加生活方式_睡眠状况
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddShfs_Smzk(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(3000);
		
		//到生活方式页面
		driver.findElementByXPath("/html/body/div[2]/div[1]/div[2]/li[1]/ul/li[4]/a/span[2]").click();
		
		Thread.sleep(3000);	
		
		//睡眠状况
		//过去一个月,夜间平均睡眠时间（小时）
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[1]/div/input").sendKeys("10");
		
		//是否需要上夜班
		
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[2]/div/select"))
				.selectByValue("是");
		
		//是否有午睡习惯
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[3]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[4]/div/input").sendKeys("100");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[5]/div/input").sendKeys("110");
		
		//晚上睡眠时有无异常情况出现
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[6]/div/select"))
				.selectByValue("有");
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[7]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[7]/div/div/ul/li[1]/a/label").click();
		
		//睡眠时是否打鼾
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[8]/div/select"))
				.selectByValue("是");
		Thread.sleep(3000);
		//打鼾频率
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[9]/div/select"))
				.selectByValue("3-4 天/周");
		//打鼾响亮程度
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[10]/div/select"))
				.selectByValue("与说话时声音一样响");

		//是否影响他人
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[11]/div/select"))
				.selectByValue("是");

		//打鼾时有无下列情况
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[12]/div/select"))
				.selectByValue("有呼吸停顿但无憋醒");

		//呼吸停顿频率
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[13]/div/select"))
				.selectByValue("3-4 天/周");

		//有确诊的阻塞型睡眠呼吸暂停低通气综合征
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[14]/div/select"))
				.selectByValue("是");

		//有无确诊的阻塞型睡眠呼吸暂停低通气综合征治疗方式
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[15]/div/select"))
				.selectByValue("有治疗");

		//确诊的阻塞型睡眠呼吸暂停低通气综合征治疗方式(问题)
		/*for (int i = 1; i < 4; i++) {
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[16]/div/div/button").click();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[16]/div/div/ul/li["+i+"]/a/label").click();
		}*/

		//白天清醒状态下是否有疲劳现象
		
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[17]/div/select"))
				.selectByValue("有");
		
		//白天清醒状态下何时会出现疲劳现象(问题)
		/*for (int i = 1; i < 4; i++) {
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[18]/div/div/button").click();
			driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[7]/div[3]/div[18]/div/div/ul/li["+i+"]/a/label").click();
		}
		*/
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}

	
	/** 
	* @Title: hzxx_AddShfs_Dbqk 
	* @Description: 患者信息_添加生活方式_大便情况（过去一个月）
	* @param: PhantomJSDriver driver
	* @param: String xpath  新添加的个人信息的xpath
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static String hzxx_AddShfs_Dbqk(PhantomJSDriver driver,String xpath) throws Exception{
		//到已添加基本信息页面
		driver.findElementByXPath(xpath).click();
		
		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();

		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);

		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));
		
		Thread.sleep(2000);
		
		//到生活方式页面
		driver.findElementByXPath("/html/body/div[2]/div[1]/div[2]/li[1]/ul/li[4]/a/span[2]").click();
		
		Thread.sleep(3000);	
		
		//大便情况（过去一个月）
		//过去一个月，大便情况-频次
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div[3]/div[1]/div/select"))
				.selectByValue("2-3 次/天");
		
		//过去一个月排便时间是否确定
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div[3]/div[2]/div/select"))
				.selectByValue("确定");
		//过去一个月，排便时间
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div[3]/div[3]/div/div/button").click();
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div[3]/div[3]/div/div/ul/li[1]/a/label/input").click();
		
		//过去一个月，每次排便时长
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div[3]/div[4]/div/select"))
				.selectByValue("≤5 分钟");
		
		//过去一个月，粪便性状
		new Select(
				driver.findElementByXPath("/html/body/div[2]/div[2]/div[2]/div[8]/div[3]/div[5]/div/select"))
				.selectByValue("干结样硬便");
		
		// 保存
		driver.findElementByXPath("/html/body/div[2]/div[2]/div[1]/div[2]/button[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("/html/body/div[4]/div/div/div/div[3]/button").click();
		
		return "ok";
	}

	
	
}

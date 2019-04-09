package com.gennlife.crf.yantai.add.test4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gennlife.crf.utils.RandomValue;

/**
 * @Description: 烟台环境：添加门急诊_住院时情况
 * @author: wangmiao
 * @Date: 2017年7月11日 上午8:42:40 
 */
public class Yantai_Mjz_AddZysqk {

	private static final Logger logger = LoggerFactory.getLogger(Yantai_Mjz_AddZysqk.class);

	/** 
	 * @Title: mjz_AddZysqk
	 * @Description: 门急诊_住院时情况
	 * @param: PhantomJSDriver driver
	 * @param: String xpath  新添加的个人信息的xpath
	 * @param: @throws Exception
	 * @return: String
	 * @throws 
	 */
	public static String mjz_AddZysqk(PhantomJSDriver driver, String xpath) throws Exception {
		// 日志
		logger.debug("门急诊_住院时情况");
		
		// 到已添加基本信息页面
		driver.findElementByXPath(xpath).click();

		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();
		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);
		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));

		Thread.sleep(2000);

		// 添加住院时情况
		driver.findElementByXPath(".//*[@id='crf-data-tree_13_span']").click();
		
		// 住院期间检查
		//脑MRI_梗死部位
		driver.findElementById("DHS_EXAMINATION_MRI_EXAMINATION_MRI_INFARCTION").sendKeys(RandomValue.UUIDString());
		
		//脑CT_梗死部位
		driver.findElementById("DHS_EXAMINATION_CT_EXAMINATION_CT_INFARCTION").sendKeys(RandomValue.UUIDString());

		//脑血管造影
		driver.findElementById("DHS_EXAMINATION_BA_EXAMINATION_0_BA_ANGIOSTEGNOSIS_YES_NO").sendKeys(RandomValue.UUIDString());
		new Select(driver.findElementById("DHS_EXAMINATION_BA_EXAMINATION_0_BA_ANGIOSTEGNOSIS_LOCATION")).selectByValue("颈外动脉左侧");
		driver.findElementById("DHS_EXAMINATION_BA_EXAMINATION_0_BA_ANGIOSTEGNOSIS_EXTENT").sendKeys(RandomValue.randomRangeInt(10, 30));
		
		//脑MRA
		driver.findElementById("DHS_EXAMINATION_MRA_EXAMINATION_0_MRA_ANGIOSTEGNOSIS_YES_NO").sendKeys(RandomValue.UUIDString());
		new Select(driver.findElementById("DHS_EXAMINATION_MRA_EXAMINATION_0_MRA_ANGIOSTEGNOSIS_LOCATION")).selectByValue("颈总动脉左侧");
		driver.findElementById("DHS_EXAMINATION_MRA_EXAMINATION_0_MRA_ANGIOSTEGNOSIS_EXTENT").sendKeys(RandomValue.randomRangeInt(10, 30));
		
		//颈动脉彩超
		driver.findElementById("DHS_EXAMINATION_CA_EXAMINATION_0_CA_ANGIOSTEGNOSIS_YES_NO").sendKeys(RandomValue.UUIDString());
		new Select(driver.findElementById("DHS_EXAMINATION_CA_EXAMINATION_0_CA_ANGIOSTEGNOSIS_LOCATION")).selectByValue("颈总动脉左侧");
		driver.findElementById("DHS_EXAMINATION_CA_EXAMINATION_0_CA_ANGIOSTEGNOSIS_EXTENT").sendKeys(RandomValue.randomRangeInt(10, 30));

		//住院期间用药
		//缺血性卒中溶栓治疗
		new Select(driver.findElementById("DHS_DRUG_THROMBOLYSIS_TREATMENT")).selectByValue("rtPA");
		//缺血性卒中抗栓治疗
		new Select(driver.findElementById("DHS_DRUG_ANTITHROMBOSIS_TREATMENT")).selectByValue("阿司匹林");
		//是否应用抗凝治疗
		new Select(driver.findElementById("DHS_DRUG_ANTICOAGULATION_TREATMENT")).selectByValue("华法林");
		//降压药物
		new Select(driver.findElementById("DHS_DRUG_HYPOTENSOR_DRUG")).selectByValue("是");
		//降压药种类
		new Select(driver.findElementById("DHS_DRUG_HYPOTENSOR_DRUG_TYPE")).selectByValue("钙通道阻滞剂");
		//调脂药物
		new Select(driver.findElementById("DHS_DRUG_LIPID_DRUG")).selectByValue("他汀类");
		//是否应用胰岛素
		new Select(driver.findElementById("DHS_DRUG_INSULIN_YES_NO")).selectByValue("是");
		//口服降糖药
		new Select(driver.findElementById("DHS_DRUG_HYPOGLYCEMIC_DRUG")).selectByValue("是");
		//降糖药种类
		new Select(driver.findElementById("DHS_DRUG_HYPOGLYCEMIC_DRUG_TYPE")).selectByValue("双胍类");
		
		//住院期间病情变化
		//梗死后脑出血
		new Select(driver.findElementById("DHS_CHANGE_CEREBRAL_HEMORRHAGE_INFARCTION")).selectByValue("是");
		//再发卒中
		new Select(driver.findElementById("DHS_CHANGE_APOPLEXY_AGAIN")).selectByValue("是");
		//是否出现心肌梗死
		new Select(driver.findElementById("DHS_CHANGE_MYOCARDIAL_INFARCTION_YES_NO")).selectByValue("是");
		//是否出现下肢静脉血栓
		new Select(driver.findElementById("DHS_CHANGE_LEDVT_YES_NO")).selectByValue("是");
		//是否出现肺部感染
		new Select(driver.findElementById("DHS_CHANGE_PULMONARY_INFECTION")).selectByValue("是");
		//是否出现泌尿系感染
		new Select(driver.findElementById("DHS_CHANGE_URINARY_INFECTION")).selectByValue("是");
		//消化道出血
		new Select(driver.findElementById("DHS_CHANGE_GASTROINTESTINAL_HEMORRHAGE")).selectByValue("是");
		//泌尿系出血
		new Select(driver.findElementById("DHS_CHANGE_URINARY_BLEEDING")).selectByValue("是");
		//药物过敏
		new Select(driver.findElementById("DHS_CHANGE_ALLERGIC_DRUG_YES_NO")).selectByValue("是");
		//过敏药物
		driver.findElementById("DHS_CHANGE_ALLERGIC_DRUG_YES_NO").sendKeys(RandomValue.UUIDString());
		//是否死亡
		new Select(driver.findElementById("DHS_CHANGE_DEAD_YES_NO")).selectByValue("是");
		
		// 保存
		driver.findElementById("input-save").click();
		Thread.sleep(2000);
		driver.findElementByXPath(".//*[@id='alert-container']/div/div/div/div[3]/button").click();
				
		return "ok";

	}

}

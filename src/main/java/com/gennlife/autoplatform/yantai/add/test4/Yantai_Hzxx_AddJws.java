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
 * @Description: 烟台环境：添加患者信息_既往史
 * @author: wangmiao
 * @Date: 2017年7月6日 下午2:15:26
 */
public class Yantai_Hzxx_AddJws {

	private static final Logger logger = LoggerFactory.getLogger(Yantai_Hzxx_AddJws.class);

	/** 
	 * @Title: hzxx_AddJws
	 * @Description: 患者信息_既往史
	 * @param: PhantomJSDriver driver
	 * @param: String xpath  新添加的个人信息的xpath
	 * @param: @throws Exception
	 * @return: String
	 * @throws 
	 */
	public static String hzxx_AddJws(PhantomJSDriver driver, String xpath) throws Exception {

		// 日志
		logger.debug("患者信息_既往史");
		
		// 到已添加基本信息页面
		driver.findElementByXPath(xpath).click();

		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();
		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);
		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));

		Thread.sleep(2000);

		// 到既往史
		driver.findElementByXPath(".//*[@id='crf-data-tree_3_span']").click();

		// 发病前mRS评分
		new Select(driver.findElementById("MRS_BEFORE")).selectByValue("3");
		
		// 是否首次卒中
		new Select(driver.findElementById("CVA_FIRST")).selectByValue("是");
		
		//高血压史
		//有无高血压史
		new Select(driver.findElementById("HBP_HISTORY_HBP_YES_NO")).selectByValue("有");
		
		//高血压史年限
		driver.findElementById("HBP_HISTORY_HBP_YEAR").clear();
		driver.findElementById("HBP_HISTORY_HBP_YEAR").sendKeys(RandomValue.randomRangeInt(5, 10));
		
		//最高收缩压（mmHg）
		driver.findElementById("HBP_HISTORY_HIGHEST_SYSTOLIC").clear();
		driver.findElementById("HBP_HISTORY_HIGHEST_SYSTOLIC").sendKeys(RandomValue.randomRangeInt(80, 120));
		
		//最高舒张压（mmHg）
		driver.findElementById("HBP_HISTORY_HIGHEST_DIASTOLIC").clear();
		driver.findElementById("HBP_HISTORY_HIGHEST_DIASTOLIC").sendKeys(RandomValue.randomRangeInt(90, 100));
		
		//药物控制后舒张压（mmHg）
		driver.findElementById("HBP_HISTORY_SYSTOLIC_AFTER_DRUG").clear();
		driver.findElementById("HBP_HISTORY_SYSTOLIC_AFTER_DRUG").sendKeys(RandomValue.randomRangeInt(90, 100));
		
		//药物控制后收缩压（mmHg）
		driver.findElementById("HBP_HISTORY_DIASTOLIC_AFTER_DRUG").clear();
		driver.findElementById("HBP_HISTORY_DIASTOLIC_AFTER_DRUG").sendKeys(RandomValue.randomRangeInt(90, 130));
		
		//糖尿病史
		//有无糖尿病史
		new Select(driver.findElementById("DIABETES_HISTORY_DIABETES_YES_NO")).selectByValue("有");
		
		//糖尿病类型
		new Select(driver.findElementById("DIABETES_HISTORY_DIABETES_TYPE")).selectByValue("I型糖尿病");
		
		//糖尿病时长（年）
		driver.findElementById("DIABETES_HISTORY_DIABETES_YEARS").clear();
		driver.findElementById("DIABETES_HISTORY_DIABETES_YEARS").sendKeys(RandomValue.randomRangeInt(5, 13));
		
		//高脂血症史
		//有无高脂血症
		new Select(driver.findElementById("HYPERLIPOIDEMIA_HISTORY_HYPERLIPOIDEMIA")).selectByValue("有");
		
		//脑血管疾病史
		//缺血性卒中
		new Select(driver.findElementById("CVD_HISTORY_CVD_STROKE")).selectByValue("有");
		
		//脑出血
		new Select(driver.findElementById("CVD_HISTORY_CEREBRAL_HEMORRHAGE")).selectByValue("有");
		
		//蛛网膜下腔出血
		new Select(driver.findElementById("CVD_HISTORY_SAH")).selectByValue("有");
		
		//是否进行颈动脉支架
		new Select(driver.findElementById("CVD_HISTORY_CAROTID_ARTERY_STENT")).selectByValue("是");
		
		//是否进行颈动脉内膜剥脱
		new Select(driver.findElementById("CVD_HISTORY_CAROTID_ARTERY_ENDARTERECTOMY")).selectByValue("是");
		
		//是否进行颅内动脉成形术
		new Select(driver.findElementById("CVD_HISTORY_INTRACRANIAL_ANGIOPLASTY_STENT")).selectByValue("是");
		
		//心源性或其他血管源性疾病
		//心力衰竭
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_HEART_FAILURE")).selectByValue("是");
		
		//周围血管病
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_PERIPHERAL_ANGIOPATHY")).selectByValue("是");
		
		//房颤
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_ATRIAL_FIBRILLATION")).selectByValue("是");
		
		//心脏瓣膜病
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_VALVULAR_HEART_DISEASE")).selectByValue("是");
		
		//心瓣膜置换术
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_CARDIAC_VALVE_REPLACEMENT")).selectByValue("是");
		
		//冠心病
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_CORONARY_HEART_DISEASE")).selectByValue("是");
		
		//是否陈旧性心梗
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_OMI")).selectByValue("是");

		//是否冠脉支架
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_CORONARY_STENT")).selectByValue("是");
		
		//是否冠脉搭桥
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_CORONARY_ARTERY_BYPASS")).selectByValue("是");
		
		//下肢静脉血栓
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_LEDVT")).selectByValue("是");
		
		//下肢肺栓塞
		new Select(driver.findElementById("CARDIOGENIC_VASCULAR_HISTORY_PULMONARY_EMBOLISM")).selectByValue("是");
		
		//其他病史
		//癌症
		new Select(driver.findElementById("OTHER_HISTORY_CANCER")).selectByValue("是");
		
		//痴呆
		new Select(driver.findElementById("OTHER_HISTORY_DEMENTIA")).selectByValue("是");
		
		//肝硬化
		new Select(driver.findElementById("OTHER_HISTORY_HEPATOCIRRHOSIS")).selectByValue("是");
		
		//哮喘
		new Select(driver.findElementById("OTHER_HISTORY_ASTHMA")).selectByValue("是");
		
		//肾透析
		new Select(driver.findElementById("OTHER_HISTORY_RENAL_DIALYSIS")).selectByValue("是");
		
		//消化道溃疡
		new Select(driver.findElementById("OTHER_HISTORY_PEPTIC_ULCER")).selectByValue("是");
		
		//消化道出血
		new Select(driver.findElementById("OTHER_HISTORY_GASTROINTESTINAL_HEMORRHAGE")).selectByValue("是");
		
		//关节炎
		new Select(driver.findElementById("OTHER_HISTORY_ARTHRITIS")).selectByValue("是");
		
		//精神障碍
		new Select(driver.findElementById("OTHER_HISTORY_ALLEOSIS")).selectByValue("是");
		
		//妊娠
		new Select(driver.findElementById("OTHER_HISTORY_GESTATION")).selectByValue("是");
		
		//用药情况
		//抗高血压药物
		new Select(driver.findElementById("DRUG_HISTORY_ANTIHYPERTENSIVE_DRUG")).selectByValue("是");
		
		//抗血小板药物
		new Select(driver.findElementById("DRUG_HISTORY_ANTIPLATELET_DRUG")).selectByValue("是");
		
		//抗凝药物
		new Select(driver.findElementById("DRUG_HISTORY_ANTICOAGULANT_DRUG")).selectByValue("是");
		
		//抗惊厥药物
		new Select(driver.findElementById("DRUG_HISTORY_ANTICONVULSANT_DRUG")).selectByValue("是");
		
		//口服避孕药
		new Select(driver.findElementById("DRUG_HISTORY_CONTRACEPTIVE_DRUG")).selectByValue("是");
		
		//激素替代治疗
		new Select(driver.findElementById("DRUG_HISTORY_HRT")).selectByValue("是");
		
		//口服降糖药物
		new Select(driver.findElementById("DRUG_HISTORY_HYPOGLYCEMIC_DRUG")).selectByValue("是");
		
		//胰岛素
		new Select(driver.findElementById("DRUG_HISTORY_INSULIN")).selectByValue("是");
		
		//降血脂药物
		new Select(driver.findElementById("DRUG_HISTORY_LIPID_LOWERINGDRUG")).selectByValue("是");
		
		//精神科药品
		new Select(driver.findElementById("DRUG_HISTORY_PSYCHOTROPIC_DRUGS")).selectByValue("是");
		
		// 保存
		driver.findElementById("input-save").click();
		Thread.sleep(2000);
		driver.findElementByXPath(".//*[@id='alert-container']/div/div/div/div[3]/button").click();

		return "ok";

	}

}

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
 * @Description: 烟台环境：添加门急诊_入院时情况
 * @author: wangmiao
 * @Date: 2017年7月11日 上午8:36:04 
 */
public class Yantai_Mjz_AddRysqk {

	private static final Logger logger = LoggerFactory.getLogger(Yantai_Mjz_AddRysqk.class);

	/** 
	 * @Title: mjz_AddRysqk
	 * @Description: 添加门急诊_入院时情况
	 * @param: PhantomJSDriver driver
	 * @param: String xpath  新添加的个人信息的xpath
	 * @param: @throws Exception
	 * @return: String
	 * @throws 
	 */
	public static String mjz_AddRysqk(PhantomJSDriver driver, String xpath) throws Exception {

		// 日志
		logger.debug("门急诊_入院时情况");
		
		// 到已添加基本信息页面
		driver.findElementByXPath(xpath).click();

		// 得到当前窗口的set集合
		Set<String> winHandels = driver.getWindowHandles();
		// 将set集合存入list对象
		List<String> it = new ArrayList<String>(winHandels);
		// 切换到弹出的新窗口
		driver.switchTo().window(it.get(1));

		Thread.sleep(2000);

		// 添加门急诊
		driver.findElementByXPath("html/body/div[2]/div[1]/ul/li/a").click();
		driver.findElementByXPath(".//*[@id='add-outpatient']").click();
		
		// 入院基本信息
		//入院时间
		driver.findElementById("ADMIS_INFO_INPATIENT_DATE").sendKeys("2000-07-01 00:00:00");

		//出院时间
		driver.findElementById("ADMIS_INFO_DISCHARGE_DATE").sendKeys("2001-07-01 00:00:00");
		
		//入院主诉
		driver.findElementById("ADMIS_INFO_INPATIENT_COMPLAINT").clear();
		driver.findElementById("ADMIS_INFO_INPATIENT_COMPLAINT").sendKeys(RandomValue.UUIDString());
		
		//主诉时间（天）
		driver.findElementById("ADMIS_INFO_COMPLAINT_DAYS").clear();
		driver.findElementById("ADMIS_INFO_COMPLAINT_DAYS").sendKeys(RandomValue.randomRangeInt(10, 23));
		
		//发病时间
		driver.findElementById("ADMIS_INFO_MORBIDITY_TIME").sendKeys("2002-07-01 00:00:00");
		
		//既往史
		// 发病前mRS评分
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_MRS_BEFORE")).selectByValue("3");
		
		// 是否首次卒中
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CVA_FIRST")).selectByValue("是");
		
		//高血压史
		//有无高血压史
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_HBP_YES_NO")).selectByValue("有");
		
		//高血压史年限
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_HBP_YEAR").clear();
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_HBP_YEAR").sendKeys(RandomValue.randomRangeInt(5, 10));
		
		//最高收缩压（mmHg）
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_HIGHEST_SYSTOLIC").clear();
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_HIGHEST_SYSTOLIC").sendKeys(RandomValue.randomRangeInt(80, 120));
		
		//最高舒张压（mmHg）
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_HIGHEST_DIASTOLIC").clear();
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_HIGHEST_DIASTOLIC").sendKeys(RandomValue.randomRangeInt(90, 100));
		
		//药物控制后舒张压（mmHg）
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_SYSTOLIC_AFTER_DRUG").clear();
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_SYSTOLIC_AFTER_DRUG").sendKeys(RandomValue.randomRangeInt(90, 100));
		
		//药物控制后收缩压（mmHg）
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_DIASTOLIC_AFTER_DRUG").clear();
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_HBP_HISTORY_HOS_DIASTOLIC_AFTER_DRUG").sendKeys(RandomValue.randomRangeInt(90, 130));
		
		//糖尿病史
		//有无糖尿病史
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DIABETES_HISTORY_HOS_DIABETES_YES_NO")).selectByValue("有");
		
		//糖尿病类型
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DIABETES_HISTORY_HOS_DIABETES_TYPE")).selectByValue("I型糖尿病");
		
		//糖尿病时长（年）
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_DIABETES_HISTORY_HOS_DIABETES_YEARS").clear();
		driver.findElementById("HOS_DISEASE_HISTORY_HOS_DIABETES_HISTORY_HOS_DIABETES_YEARS").sendKeys(RandomValue.randomRangeInt(5, 13));
		
		//高脂血症史
		//有无高脂血症
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_HYPERLIPOIDEMIA_HISTORY_HOS_HYPERLIPOIDEMIA")).selectByValue("有");
		
		//脑血管疾病史
		//缺血性卒中
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CVD_HISTORY_HOS_CVD_STROKE")).selectByValue("有");
		
		//脑出血
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CVD_HISTORY_HOS_CEREBRAL_HEMORRHAGE")).selectByValue("有");
		
		//蛛网膜下腔出血
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CVD_HISTORY_HOS_SAH")).selectByValue("有");
		
		//是否进行颈动脉支架
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CVD_HISTORY_HOS_CAROTID_ARTERY_STENT")).selectByValue("是");
		
		//是否进行颈动脉内膜剥脱
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CVD_HISTORY_HOS_CAROTID_ARTERY_ENDARTERECTOMY")).selectByValue("是");
		
		//是否进行颅内动脉成形术
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CVD_HISTORY_HOS_INTRACRANIAL_ANGIOPLASTY_STENT")).selectByValue("是");
		
		//心源性或其他血管源性疾病
		//心力衰竭
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_HEART_FAILURE")).selectByValue("是");
		
		//周围血管病
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_PERIPHERAL_ANGIOPATHY")).selectByValue("是");
		
		//房颤
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_ATRIAL_FIBRILLATION")).selectByValue("是");
		
		//心脏瓣膜病
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_VALVULAR_HEART_DISEASE")).selectByValue("是");
		
		//心瓣膜置换术
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_CARDIAC_VALVE_REPLACEMENT")).selectByValue("是");
		
		//冠心病
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_CORONARY_HEART_DISEASE")).selectByValue("是");
		
		//是否陈旧性心梗
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_OMI")).selectByValue("是");

		//是否冠脉支架
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_CORONARY_STENT")).selectByValue("是");
		
		//是否冠脉搭桥
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_CORONARY_ARTERY_BYPASS")).selectByValue("是");
		
		//下肢静脉血栓
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_LEDVT")).selectByValue("是");
		
		//下肢肺栓塞
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_CARDIOGENIC_VASCULAR_HISTORY_HOS_PULMONARY_EMBOLISM")).selectByValue("是");
		
		//其他病史
		//癌症
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_CANCER")).selectByValue("是");
		
		//痴呆
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_DEMENTIA")).selectByValue("是");
		
		//肝硬化
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_HEPATOCIRRHOSIS")).selectByValue("是");
		
		//哮喘
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_ASTHMA")).selectByValue("是");
		
		//肾透析
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_RENAL_DIALYSIS")).selectByValue("是");
		
		//消化道溃疡
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_PEPTIC_ULCER")).selectByValue("是");
		
		//消化道出血
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_GASTROINTESTINAL_HEMORRHAGE")).selectByValue("是");
		
		//关节炎
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_ARTHRITIS")).selectByValue("是");
		
		//精神障碍
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_ALLEOSIS")).selectByValue("是");
		
		//妊娠
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_OTHER_HISTORY_HOS_GESTATION")).selectByValue("是");
		
		//用药情况
		//抗高血压药物
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_ANTIHYPERTENSIVE_DRUG")).selectByValue("是");
		
		//抗血小板药物
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_ANTIPLATELET_DRUG")).selectByValue("是");
		
		//抗凝药物
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_ANTICOAGULANT_DRUG")).selectByValue("是");
		
		//抗惊厥药物
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_ANTICONVULSANT_DRUG")).selectByValue("是");
		
		//口服避孕药
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_CONTRACEPTIVE_DRUG")).selectByValue("是");
		
		//激素替代治疗
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_HRT")).selectByValue("是");
		
		//口服降糖药物
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_HYPOGLYCEMIC_DRUG")).selectByValue("是");
		
		//胰岛素
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_INSULIN")).selectByValue("是");
		
		//降血脂药物
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_LIPID_LOWERINGDRUG")).selectByValue("是");
		
		//精神科药品
		new Select(driver.findElementById("HOS_DISEASE_HISTORY_HOS_DRUG_HISTORY_HOS_PSYCHOTROPIC_DRUGS")).selectByValue("是");

		// 家族病史
		// 有无卒中家族史
		new Select(driver.findElementById("HOS_FAMILY_INHERITANCE_HISTORY_HOS_CVA_FAMILY")).selectByValue("是");
		
		// 亲属类型
		driver.findElementById("HOS_FAMILY_INHERITANCE_HISTORY_HOS_FAMILY_TYPE").clear();
		driver.findElementById("HOS_FAMILY_INHERITANCE_HISTORY_HOS_FAMILY_TYPE").sendKeys(RandomValue.UUIDString());
		
		// 吸烟史
		//有无吸烟史
		new Select(driver.findElementById("HOS_SMOKE_HISTORY_HOS_PAST_SMOKE_HISTORY_YES_NO")).selectByValue("有");
		// 吸烟量（支/天）
		driver.findElementById("HOS_SMOKE_HISTORY_HOS_SMOKE_RATE").clear();
		driver.findElementById("HOS_SMOKE_HISTORY_HOS_SMOKE_RATE").sendKeys(RandomValue.randomRangeInt(3,9));
		// 吸烟时长（年）
		driver.findElementById("HOS_SMOKE_HISTORY_HOS_SMOKE_YEARS").clear();
		driver.findElementById("HOS_SMOKE_HISTORY_HOS_SMOKE_YEARS").sendKeys(RandomValue.randomRangeInt(1,15));
		//是否戒烟
		new Select(driver.findElementById("HOS_SMOKE_HISTORY_HOS_SMOKE_QUIT_YES_NO")).selectByValue("是");
		//戒烟时间（年）
		driver.findElementById("HOS_SMOKE_HISTORY_HOS_QUIT_YEARS").clear();
		driver.findElementById("HOS_SMOKE_HISTORY_HOS_QUIT_YEARS").sendKeys(RandomValue.randomRangeInt(1,15));

		// 饮酒史
		//有无饮酒史
		new Select(driver.findElementById("HOS_DRINK_HISTORY_HOS_DRINK_HISTORY_YES_NO")).selectByValue("有");
		// 饮酒量
		new Select(driver.findElementById("HOS_DRINK_HISTORY_HOS_DRINK_VOLUME")).selectByValue("中度饮酒");
		// 饮酒时长（年）
		driver.findElementById("HOS_DRINK_HISTORY_HOS_DRINK_TIME").clear();
		driver.findElementById("HOS_DRINK_HISTORY_HOS_DRINK_TIME").sendKeys(RandomValue.randomRangeInt(1,15));
		//是否戒酒
		new Select(driver.findElementById("HOS_DRINK_HISTORY_HOS_QUIT_DRINK_YES_NO")).selectByValue("是");
		//戒酒时间（年）
		driver.findElementById("HOS_DRINK_HISTORY_HOS_QUIT_DRINK_TIME").clear();
		driver.findElementById("HOS_DRINK_HISTORY_HOS_QUIT_DRINK_TIME").sendKeys(RandomValue.randomRangeInt(1,15));
		
		//本次发病的主要表现
		//患侧
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_AFFECTED_SIDE")).selectByValue("双侧");
				
		//肌力下降
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_MUSCLE_WEAKNESS")).selectByValue("是");
		
		//感觉症状
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_SENSORY_SYMPTOM")).selectByValue("是");
		
		//失语
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_APHASIA")).selectByValue("是");
		
		//构音障碍
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_DYSARTHRIA")).selectByValue("是");
		
		//痫性发作
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_SEIZURE")).selectByValue("是");
		
		//单眼盲
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_LUSCITAS")).selectByValue("是");
		
		//视物不清
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_UNCLEAR_VISION")).selectByValue("是");
		
		//视野缺失
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_VISUAL_FIELD_DIFICIENCY")).selectByValue("是");
		
		//眩晕
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_VERTIGO")).selectByValue("是");
		
		//复视
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_DIPLOPIA")).selectByValue("是");
		
		//认知功能障碍
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_COGNITIVE_IMPAIRMENT")).selectByValue("是");
		
		//吞咽困难
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_DYSPHAGIA")).selectByValue("是");
		
		//头痛
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_HEADACHE")).selectByValue("是");
		
		//意识障碍
		new Select(driver.findElementById("HOS_MAIN_SYMPTOMS_CONSCIOUSNESS_DISORDER")).selectByValue("是");
		
		//查体及化验
		//到院时意识状态
		new Select(driver.findElementById("PHYSICAL_EXAMINATION_TEST_CONSCIOUS_STATE")).selectByValue("清醒");
		
		//入院后首次NIHSS评分
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_NIHSS_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_NIHSS_FIRST").sendKeys(RandomValue.randomRangeInt(46,80));
		
		//入院后首次GCS评分
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_GCS_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_GCS_FIRST").sendKeys(RandomValue.randomRangeInt(54,95));
		
		//入院后首次空腹血糖（mmol/l）
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_FASTING_GLUCOSE_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_FASTING_GLUCOSE_FIRST").sendKeys(RandomValue.randomRangeInt(1,15));
		
		//入院后糖化血红蛋白（%）
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_HGB").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_HGB").sendKeys(RandomValue.randomRangeInt(6,78));
		
		//入院后血浆同型半胱氨酸（umol/l）
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_CYSTEINE_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_CYSTEINE_FIRST").sendKeys(RandomValue.randomRangeInt(55,95));
		
		
		for (int i = 1; i < 11; i++) {
			//入院后10次血压监测情况_第i次收缩压
			driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_SYSTOLIC_"+i).clear();
			driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_SYSTOLIC_"+i).sendKeys(RandomValue.randomRangeInt(80,120));
			
			//入院后10次血压监测情况_第i次舒张压
			driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_DIASTOLIC_"+i).clear();
			driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_DIASTOLIC_"+i).sendKeys(RandomValue.randomRangeInt(90,150));
			
		}
		
		//最高收缩压
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_HIGHEST_SYSTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_HIGHEST_SYSTOLIC").sendKeys(RandomValue.randomRangeInt(80,150));
		
		//最高舒张压
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_HIGHEST_DIASTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_HIGHEST_DIASTOLIC").sendKeys(RandomValue.randomRangeInt(80,150));
		
		//最低收缩压
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_LOWEST_SYSTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_LOWEST_SYSTOLIC").sendKeys(RandomValue.randomRangeInt(80,150));
		
		//最低舒张压
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_LOWEST_DIASTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_LOWEST_DIASTOLIC").sendKeys(RandomValue.randomRangeInt(80,150));
		
		//收缩压最大压差
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_MAX_PRESSURE_DIFF_SYSTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_MAX_PRESSURE_DIFF_SYSTOLIC").sendKeys(RandomValue.randomRangeInt(10,40));
		
		//舒张压最大压差
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_MAX_PRESSURE_DIFF_DIASTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_MAX_PRESSURE_DIFF_DIASTOLIC").sendKeys(RandomValue.randomRangeInt(10,40));
		
		//收缩压平均值
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_AVERAGE_SYSTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_AVERAGE_SYSTOLIC").sendKeys(RandomValue.randomRangeInt(12,50));
		
		//舒张压平均值
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_AVERAGE_DIASTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_AVERAGE_DIASTOLIC").sendKeys(RandomValue.randomRangeInt(12,50));
		
		//收缩压标准差
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_SD_SYSTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_SD_SYSTOLIC").sendKeys(RandomValue.randomRangeInt(12,50));
		
		//舒张压标准差
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_SD_DIASTOLIC").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_SD_DIASTOLIC").sendKeys(RandomValue.randomRangeInt(12,50));
		
		//入院首次血常规_红细胞计数
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_RBC_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_RBC_FIRST").sendKeys(RandomValue.randomRangeInt(12,50));
		
		//入院首次血常规_血红蛋白
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_HGB_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_HGB_FIRST").sendKeys(RandomValue.randomRangeInt(12,45));
		
		//入院首次血常规_白细胞计数
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_WBC_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_WBC_FIRST").sendKeys(RandomValue.randomRangeInt(11,35));
		
		//入院首次血常规_血红蛋白
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_HGB_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_HGB_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//入院首次血常规_白细胞计数
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_WBC_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_WBC_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//入院首次血常规_血小板计数
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_PLT_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_PLT_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//入院首次血脂情况_血浆总胆固醇浓度
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_TC_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_TC_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//入院首次血脂情况_高密度脂蛋白
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_HDL_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_HDL_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//入院首次血脂情况_低密度脂蛋白
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_LDL_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_LDL_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//入院首次血脂情况_甘油三酯
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_TRIG_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_TRIG_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//入院次晨肾功/尿酸_肌酐
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_CREA_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_CREA_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//入院次晨肾功/尿酸_尿素氮
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_BUN_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_BUN_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//入院次晨肾功/尿酸_血尿酸
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_UA_FIRST").clear();
		driver.findElementById("PHYSICAL_EXAMINATION_TEST_ADMISS_UA_FIRST").sendKeys(RandomValue.randomRangeInt(23,65));
		
		//CYP2C19基因检测 
		new Select(driver.findElementById("PHYSICAL_EXAMINATION_TEST_CYP2C19_GENETEST")).selectByValue("未化验");
		
		//诊断
		//初步诊断
		new Select(driver.findElementById("DIAGNOSIS_PRELIMINARY_DIAGNOSIS")).selectByValue("短暂性脑缺血");
		
		//TOAST分型
		new Select(driver.findElementById("DIAGNOSIS_TOAST")).selectByValue("大动脉粥样硬化性卒中");
		
		// 保存
		driver.findElementById("input-save").click();
		Thread.sleep(2000);
		driver.findElementByXPath(".//*[@id='alert-container']/div/div/div/div[3]/button").click();
				
		return "ok";

	}

}

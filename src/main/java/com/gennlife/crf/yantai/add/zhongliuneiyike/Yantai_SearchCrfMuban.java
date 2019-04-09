package com.gennlife.crf.yantai.add.zhongliuneiyike;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

/**
 * @Description: 查询crf模板
 * @author: wangmiao
 * @Date: 2017年6月29日 上午10:10:18 
 */
public class Yantai_SearchCrfMuban{

	private static final Logger logger = LoggerFactory.getLogger(Yantai_SearchCrfMuban.class);

	/** 
	* @Title: SearchCrfMuban_Level1_List
	* @Description: 查询crf模板第一层(返回list)
	* @param: PhantomJSDriver driver
	* @param: String value
	* @param: @throws Exception :
	* @return: List<String> 返回list
	* @throws 
	*/
	public static List<String> SearchCrfMuban_Level1_List(PhantomJSDriver driver,String value) throws Exception{
		// 日志
		logger.debug("查询crf模板");
		
		List<String> list = new ArrayList<>();
		
		if ("登陆成功".contains(value)) {
			//查询左侧crf模板
			driver.findElementByXPath("/html/body/div[1]/div[2]/aside/section/ul/li[2]/ul/li/a").click();
			Thread.sleep(2000);
			
			for (int i = 1; i < 5; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li["+i+"]/a/span[2]").getText();
				list.add(text);
			}
			
		}else {
			logger.debug("登陆失败");
		}
		
		return list;

	}
	
	
	/** 
	* @Title: SearchCrfMuban_Level1and2_List
	* @Description: 查询crf模板第一层+第二层(返回list)
	* @param: PhantomJSDriver driver
	* @param: String value
	* @param: @throws Exception :
	* @return: List 返回list
	* @throws 
	*/
	public static List<Map<String, List<String>>> SearchCrfMuban_Level1and2_List(PhantomJSDriver driver,String value) throws Exception{
		// 日志
		logger.debug("查询crf模板第一层+第二层");
		
		List<Map<String, List<String>>> returnList = new ArrayList<>();
		
		Map<String, List<String>> map1 = new HashedMap<>();
		Map<String, List<String>> map2 = new HashedMap<>();
		Map<String, List<String>> map3 = new HashedMap<>();
		Map<String, List<String>> map4 = new HashedMap<>();
		List<String> list1 = new ArrayList<>(); 
		List<String> list2 = new ArrayList<>(); 
		List<String> list3 = new ArrayList<>(); 
		List<String> list4 = new ArrayList<>(); 
		
		
		if ("登陆成功".contains(value)) {
			//查询左侧crf模板
			driver.findElementByXPath("/html/body/div[1]/div[2]/aside/section/ul/li[2]/ul/li/a").click();
			Thread.sleep(2000);
			
			//获取患者信息第二层信息
			for (int i = 1; i < 21; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li["+i+"]/a/span[2]").getText();
				list1.add(text);
			}
			map1.put("患者信息", list1);
			returnList.add(map1);
			
			
			//获取住院第二层信息
			for (int i = 1; i < 17; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li["+i+"]/a/span[2]").getText();
				list2.add(text);
			}
			map2.put("住院", list2);
			returnList.add(map2);
			
			
			//获取门急诊第二层信息
			for (int i = 1; i < 17; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li["+i+"]/a/span[2]").getText();
				list3.add(text);
			}
			map3.put("门急诊", list3);
			returnList.add(map3);
			
			
			//获取随访第二层信息
			for (int i = 1; i < 24; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[4]/ul/li["+i+"]/a/span[2]").getText();
				list4.add(text);
			}
			map4.put("随访", list4);
			returnList.add(map4);
			
		}else {
			logger.debug("登陆失败");
		}
		
		return returnList;
		
	}
	
	/** 
	* @Title: SearchCrfMuban_Level1and2_Json 
	* @Description: 查询crf模板第一层+第二层(返回json)
	* @param: PhantomJSDriver driver
	* @param: String value
	* @param: @throws Exception :
	* @return: String 返回json
	* @throws 
	*/
	public static String SearchCrfMuban_Level1and2_Json(PhantomJSDriver driver,String value) throws Exception{
		// 日志
		logger.debug("查询crf模板第一层+第二层");
		
		List<Map<String, List<String>>> returnList = new ArrayList<>();
		
		Map<String, List<String>> map1 = new HashedMap<>();
		Map<String, List<String>> map2 = new HashedMap<>();
		Map<String, List<String>> map3 = new HashedMap<>();
		Map<String, List<String>> map4 = new HashedMap<>();
		List<String> list1 = new ArrayList<>(); 
		List<String> list2 = new ArrayList<>(); 
		List<String> list3 = new ArrayList<>(); 
		List<String> list4 = new ArrayList<>(); 
		
		
		if ("登陆成功".contains(value)) {
			//查询左侧crf模板
			driver.findElementByXPath("/html/body/div[1]/div[2]/aside/section/ul/li[2]/ul/li/a").click();
			Thread.sleep(2000);
			
			//获取患者信息第二层信息
			for (int i = 1; i < 21; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li["+i+"]/a/span[2]").getText();
				list1.add(text);
			}
			map1.put("患者信息", list1);
			returnList.add(map1);
			
			
			//获取住院第二层信息
			for (int i = 1; i < 17; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li["+i+"]/a/span[2]").getText();
				list2.add(text);
			}
			map2.put("住院", list2);
			returnList.add(map2);
			
			
			//获取门急诊第二层信息
			for (int i = 1; i < 17; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li["+i+"]/a/span[2]").getText();
				list3.add(text);
			}
			map3.put("门急诊", list3);
			returnList.add(map3);
			
			
			//获取随访第二层信息
			for (int i = 1; i < 24; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[4]/ul/li["+i+"]/a/span[2]").getText();
				list4.add(text);
			}
			map4.put("随访", list4);
			returnList.add(map4);
			
		}else {
			logger.debug("登陆失败");
		}
		
		return JSONArray.toJSONString(returnList); 
	}
	
	
	/** 
	* @Title: SearchCrfMuban_Hzxx_Level1and2and3_List
	* @Description: 查询crf模板第一层+第二层+第三层(返回list)
	* @param: PhantomJSDriver driver
	* @param: String value
	* @param: @throws Exception :
	* @return: List 返回list
	* @throws 
	*/
	public static List<Map<String, List<Map<String, List<String>>>>> SearchCrfMuban_Hzxx_Level1and2and3_List(PhantomJSDriver driver,String value) throws Exception{
		// 日志
		logger.debug("查询crf模板，患者信息第一层+第二层+第三层");
		//最终返回的list（最后统一封装）
		List<Map<String, List<Map<String, List<String>>>>> returnListLast = new ArrayList<>();
		Map<String, List<Map<String, List<String>>>> map_list_map_list = new HashedMap<>();
		List<Map<String, List<String>>>  list_map_list =  new ArrayList<>();
		
		//每次遍历完封装一次
		Map<String, List<String>> map_list= new HashedMap<>();
		
		if ("登陆成功".contains(value)) {
			//查询左侧crf模板
			driver.findElementByXPath("/html/body/div[1]/div[2]/aside/section/ul/li[2]/ul/li/a").click();
			Thread.sleep(2000);
			
			//获取“患者信息”第二层信息
			List<String> list2 =new ArrayList<String>();
			String text2 = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[16]/a/span[2]").getText();
			
			for (int i = 1; i < 21; i++) {
				driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li["+i+"]/a/span[2]").click();
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li["+i+"]/a/span[2]").getText();
				list2.add(text);	
			}
			
			//患者信息_患者基本信息
			List<String> list3_1 =new ArrayList<String>();
			for (int i = 1; i < 19; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[1]/ul/li["+i+"]/a/span[2]").getText();
				list3_1.add(text);
			}
			map_list.put(list2.get(0),list3_1);
			
			//患者信息_确诊信息
			List<String> list3_2 =new ArrayList<String>();
			for (int i = 1; i < 18; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[2]/ul/li["+i+"]/a/span[2]").getText();
				list3_2.add(text);
			}
			map_list.put(list2.get(1),list3_2);
			
			//患者信息_既往疾病史
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[3]/ul/li[20]/a/span[2]
			List<String> list3_3 =new ArrayList<String>();
			for (int i = 1; i < 21; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[3]/ul/li["+i+"]/a/span[2]").getText();
				list3_3.add(text);
			}
			map_list.put(list2.get(2),list3_3);
			
			//患者信息_家族肿瘤史
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[4]/ul/li[11]/a/span[2]
			List<String> list3_4 =new ArrayList<String>();
			for (int i = 1; i < 12; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[4]/ul/li["+i+"]/a/span[2]").getText();
				list3_4.add(text);
			}
			map_list.put(list2.get(3),list3_4);
			
			//患者信息_个人肿瘤史
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[5]/ul/li[6]/a/span[2]
			List<String> list3_5 =new ArrayList<String>();
			for (int i = 1; i < 7; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[5]/ul/li["+i+"]/a/span[2]").getText();
				list3_5.add(text);
			}
			map_list.put(list2.get(4),list3_5);
			
			//患者信息_吸烟史
			List<String> list3_6 =new ArrayList<String>();
			for (int i = 1; i < 6; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[6]/ul/li["+i+"]/a/span[2]").getText();
				list3_6.add(text);
			}
			map_list.put(list2.get(5),list3_6);
			
			//患者信息_饮酒史
			List<String> list3_7 =new ArrayList<String>();
			for (int i = 1; i < 9; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[7]/ul/li["+i+"]/a/span[2]").getText();
				list3_7.add(text);
			}
			map_list.put(list2.get(6),list3_7);
			
			//患者信息_首次术前B超检查
			List<String> list3_8 =new ArrayList<String>();
			for (int i = 1; i < 5; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[8]/ul/li["+i+"]/a/span[2]").getText();
				list3_8.add(text);
			}
			map_list.put(list2.get(7),list3_8);
			
			//患者信息_首次术前CT检查
			List<String> list3_9 =new ArrayList<String>();
			for (int i = 1; i < 6; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[9]/ul/li["+i+"]/a/span[2]").getText();
				list3_9.add(text);
			}
			map_list.put(list2.get(8),list3_9);
			
			//患者信息_首次术前MRI检查
			List<String> list3_10 =new ArrayList<String>();
			for (int i = 1; i < 4; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[10]/ul/li["+i+"]/a/span[2]").getText();
				list3_10.add(text);
			}
			map_list.put(list2.get(9),list3_10);
			
			//患者信息_首次手术信息
			List<String> list3_11 =new ArrayList<String>();
			for (int i = 1; i < 10; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[11]/ul/li["+i+"]/a/span[2]").getText();
				list3_11.add(text);
			}
			map_list.put(list2.get(10),list3_11);
			
			//患者信息_首次术后病理
			List<String> list3_12 =new ArrayList<String>();
			for (int i = 1; i < 16; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[12]/ul/li["+i+"]/a/span[2]").getText();
				list3_12.add(text);
			}
			map_list.put(list2.get(11),list3_12);
			
			//患者信息_远处转移
			List<String> list3_13 =new ArrayList<String>();
			for (int i = 1; i < 6; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[13]/ul/li["+i+"]/a/span[2]").getText();
				list3_13.add(text);
			}
			map_list.put(list2.get(12),list3_13);
			
			//患者信息_首次术后治疗
			List<String> list3_14 =new ArrayList<String>();
			for (int i = 1; i < 4; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[14]/ul/li["+i+"]/a/span[2]").getText();
				list3_14.add(text);
			}
			map_list.put(list2.get(13),list3_14);
			
			//患者信息_首次术后复发
			List<String> list3_15 =new ArrayList<String>();
			for (int i = 1; i < 3; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[15]/ul/li["+i+"]/a/span[2]").getText();
				list3_15.add(text);
			}
			map_list.put(list2.get(14),list3_15);
			
			//患者信息_一线免疫治疗
			List<String> list3_16 =new ArrayList<String>();
			for (int i = 1; i < 10; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[16]/ul/li["+i+"]/a/span[2]").getText();
				list3_16.add(text);
			}
			map_list.put(text2,list3_16);
			
			//患者信息_二线免疫治疗
			List<String> list3_17 =new ArrayList<String>();
			for (int i = 1; i < 10; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[17]/ul/li["+i+"]/a/span[2]").getText();
				list3_17.add(text);
			}
			map_list.put(list2.get(16),list3_17);
			
			//患者信息_一线靶向治疗
			List<String> list3_18 =new ArrayList<String>();
			for (int i = 1; i < 14; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[18]/ul/li["+i+"]/a/span[2]").getText();
				list3_18.add(text);
			}
			map_list.put(list2.get(17),list3_18);
			
			//患者信息_二线靶向治疗
			List<String> list3_19 =new ArrayList<String>();
			for (int i = 1; i < 14; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[19]/ul/li["+i+"]/a/span[2]").getText();
				list3_19.add(text);
			}
			map_list.put(list2.get(18),list3_19);
			
			//患者信息_首次手术随访
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[20]/ul/li[15]/a/span[2]
			/*List<String> list3_20 =new ArrayList<String>();
			for (int i = 1; i < 16; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[20]/ul/li["+i+"]/a/span[2]").getText();
				list3_20.add(text);
			}
			map_list.put(list2.get(19),list3_20);*/
			
			//最后统一封装
			list_map_list.add(map_list);
			map_list_map_list.put("患者信息", list_map_list);
			returnListLast.add(map_list_map_list);
			
		}else {
			logger.debug("登陆失败");
		}
		
		return returnListLast;
		
	}
	
	
	/** 
	 * @Title: SearchCrfMuban_Hzxx_Level1and2and3_Json
	 * @Description: 查询crf模板，患者信息第一层+第二层+第三层"(返回json)
	 * @param: PhantomJSDriver driver
	 * @param: String value
	 * @param: @throws Exception :
	 * @return: String 返回json
	 * @throws 
	 */
	public static String SearchCrfMuban_Hzxx_Level1and2and3_Json(PhantomJSDriver driver,String value) throws Exception{
		// 日志
		logger.debug("查询crf模板，患者信息第一层+第二层+第三层");
		//最终返回的list（最后统一封装）
		List<Map<String, List<Map<String, List<String>>>>> returnListLast = new ArrayList<>();
		Map<String, List<Map<String, List<String>>>> map_list_map_list = new HashedMap<>();
		List<Map<String, List<String>>>  list_map_list =  new ArrayList<>();
		
		//每次遍历完封装一次
		Map<String, List<String>> map_list= new HashedMap<>();
		
		if ("登陆成功".contains(value)) {
			//查询左侧crf模板
			driver.findElementByXPath("/html/body/div[1]/div[2]/aside/section/ul/li[2]/ul/li/a").click();
			Thread.sleep(2000);
			
			//获取“患者信息”第二层信息
			List<String> list2 =new ArrayList<String>();
			String text2 = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[16]/a/span[2]").getText();
			
			for (int i = 1; i < 21; i++) {
				driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li["+i+"]/a/span[2]").click();
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li["+i+"]/a/span[2]").getText();
				list2.add(text);	
			}
			
			//患者信息_患者基本信息
			List<String> list3_1 =new ArrayList<String>();
			for (int i = 1; i < 19; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[1]/ul/li["+i+"]/a/span[2]").getText();
				list3_1.add(text);
			}
			map_list.put(list2.get(0),list3_1);
			
			//患者信息_确诊信息
			List<String> list3_2 =new ArrayList<String>();
			for (int i = 1; i < 18; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[2]/ul/li["+i+"]/a/span[2]").getText();
				list3_2.add(text);
			}
			map_list.put(list2.get(1),list3_2);
			
			//患者信息_既往疾病史
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[3]/ul/li[20]/a/span[2]
			List<String> list3_3 =new ArrayList<String>();
			for (int i = 1; i < 21; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[3]/ul/li["+i+"]/a/span[2]").getText();
				list3_3.add(text);
			}
			map_list.put(list2.get(2),list3_3);
			
			//患者信息_家族肿瘤史
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[4]/ul/li[11]/a/span[2]
			List<String> list3_4 =new ArrayList<String>();
			for (int i = 1; i < 12; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[4]/ul/li["+i+"]/a/span[2]").getText();
				list3_4.add(text);
			}
			map_list.put(list2.get(3),list3_4);
			
			//患者信息_个人肿瘤史
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[5]/ul/li[6]/a/span[2]
			List<String> list3_5 =new ArrayList<String>();
			for (int i = 1; i < 7; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[5]/ul/li["+i+"]/a/span[2]").getText();
				list3_5.add(text);
			}
			map_list.put(list2.get(4),list3_5);
			
			//患者信息_吸烟史
			List<String> list3_6 =new ArrayList<String>();
			for (int i = 1; i < 6; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[6]/ul/li["+i+"]/a/span[2]").getText();
				list3_6.add(text);
			}
			map_list.put(list2.get(5),list3_6);
			
			//患者信息_饮酒史
			List<String> list3_7 =new ArrayList<String>();
			for (int i = 1; i < 9; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[7]/ul/li["+i+"]/a/span[2]").getText();
				list3_7.add(text);
			}
			map_list.put(list2.get(6),list3_7);
			
			//患者信息_首次术前B超检查
			List<String> list3_8 =new ArrayList<String>();
			for (int i = 1; i < 5; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[8]/ul/li["+i+"]/a/span[2]").getText();
				list3_8.add(text);
			}
			map_list.put(list2.get(7),list3_8);
			
			//患者信息_首次术前CT检查
			List<String> list3_9 =new ArrayList<String>();
			for (int i = 1; i < 6; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[9]/ul/li["+i+"]/a/span[2]").getText();
				list3_9.add(text);
			}
			map_list.put(list2.get(8),list3_9);
			
			//患者信息_首次术前MRI检查
			List<String> list3_10 =new ArrayList<String>();
			for (int i = 1; i < 4; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[10]/ul/li["+i+"]/a/span[2]").getText();
				list3_10.add(text);
			}
			map_list.put(list2.get(9),list3_10);
			
			//患者信息_首次手术信息
			List<String> list3_11 =new ArrayList<String>();
			for (int i = 1; i < 10; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[11]/ul/li["+i+"]/a/span[2]").getText();
				list3_11.add(text);
			}
			map_list.put(list2.get(10),list3_11);
			
			//患者信息_首次术后病理
			List<String> list3_12 =new ArrayList<String>();
			for (int i = 1; i < 16; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[12]/ul/li["+i+"]/a/span[2]").getText();
				list3_12.add(text);
			}
			map_list.put(list2.get(11),list3_12);
			
			//患者信息_远处转移
			List<String> list3_13 =new ArrayList<String>();
			for (int i = 1; i < 6; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[13]/ul/li["+i+"]/a/span[2]").getText();
				list3_13.add(text);
			}
			map_list.put(list2.get(12),list3_13);
			
			//患者信息_首次术后治疗
			List<String> list3_14 =new ArrayList<String>();
			for (int i = 1; i < 4; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[14]/ul/li["+i+"]/a/span[2]").getText();
				list3_14.add(text);
			}
			map_list.put(list2.get(13),list3_14);
			
			//患者信息_首次术后复发
			List<String> list3_15 =new ArrayList<String>();
			for (int i = 1; i < 3; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[15]/ul/li["+i+"]/a/span[2]").getText();
				list3_15.add(text);
			}
			map_list.put(list2.get(14),list3_15);
			
			//患者信息_一线免疫治疗
			List<String> list3_16 =new ArrayList<String>();
			for (int i = 1; i < 10; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[16]/ul/li["+i+"]/a/span[2]").getText();
				list3_16.add(text);
			}
			map_list.put(text2,list3_16);
			
			//患者信息_二线免疫治疗
			List<String> list3_17 =new ArrayList<String>();
			for (int i = 1; i < 10; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[17]/ul/li["+i+"]/a/span[2]").getText();
				list3_17.add(text);
			}
			map_list.put(list2.get(16),list3_17);
			
			//患者信息_一线靶向治疗
			List<String> list3_18 =new ArrayList<String>();
			for (int i = 1; i < 14; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[18]/ul/li["+i+"]/a/span[2]").getText();
				list3_18.add(text);
			}
			map_list.put(list2.get(17),list3_18);
			
			//患者信息_二线靶向治疗
			List<String> list3_19 =new ArrayList<String>();
			for (int i = 1; i < 14; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[19]/ul/li["+i+"]/a/span[2]").getText();
				list3_19.add(text);
			}
			map_list.put(list2.get(18),list3_19);
			
			//患者信息_首次手术随访
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[20]/ul/li[15]/a/span[2]
			/*List<String> list3_20 =new ArrayList<String>();
			for (int i = 1; i < 16; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[1]/ul/li[20]/ul/li["+i+"]/a/span[2]").getText();
				list3_20.add(text);
			}
			map_list.put(list2.get(19),list3_20);
			*/
			//最后统一封装
			list_map_list.add(map_list);
			map_list_map_list.put("患者信息", list_map_list);
			returnListLast.add(map_list_map_list);
			
		}else {
			logger.debug("登陆失败");
		}
		
		return JSONArray.toJSONString(returnListLast); 
		
	}
	
	/** 
	 * @Title: SearchCrfMuban_Zy_Level1and2and3_Json
	 * @Description: 查询crf模板，住院第一层+第二层+第三层"(返回json)
	 * @param: PhantomJSDriver driver
	 * @param: String value
	 * @param: @throws Exception :
	 * @return: String 返回json
	 * @throws 
	 */
	public static String SearchCrfMuban_Zy_Level1and2and3_Json(PhantomJSDriver driver,String value) throws Exception{
		// 日志
		logger.debug("查询crf模板，住院第一层+第二层+第三层");
		//最终返回的list（最后统一封装）
		List<Map<String, List<Map<String, List<String>>>>> returnListLast = new ArrayList<>();
		Map<String, List<Map<String, List<String>>>> map_list_map_list = new HashedMap<>();
		List<Map<String, List<String>>>  list_map_list =  new ArrayList<>();
		
		//每次遍历完封装一次
		Map<String, List<String>> map_list= new HashedMap<>();
		
		if ("登陆成功".contains(value)) {
			//查询左侧crf模板
			driver.findElementByXPath("/html/body/div[1]/div[2]/aside/section/ul/li[2]/ul/li/a").click();
			Thread.sleep(2000);
			
			//获取“住院”第二层信息
			List<String> list2 =new ArrayList<String>();
			String text2 = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[10]/a/span[2]").getText();
			
			for (int i = 1; i < 17; i++) {
				driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li["+i+"]/a/span[2]").click();
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li["+i+"]/a/span[2]").getText();
				list2.add(text);	
			}
			
			//住院_就诊基本信息
			List<String> list3_1 =new ArrayList<String>();
			for (int i = 1; i < 9; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[1]/ul/li["+i+"]/a/span[2]").getText();
				list3_1.add(text);
			}
			map_list.put(list2.get(0),list3_1);
			
			//住院_住院与诊断
			List<String> list3_2 =new ArrayList<String>();
			for (int i = 1; i < 11; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[2]/ul/li["+i+"]/a/span[2]").getText();
				list3_2.add(text);
			}
			map_list.put(list2.get(1),list3_2);
			
			//住院_B超检查
			List<String> list3_3 =new ArrayList<String>();
			for (int i = 1; i < 8; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[3]/ul/li["+i+"]/a/span[2]").getText();
				list3_3.add(text);
			}
			map_list.put(list2.get(2),list3_3);
			
			//住院_CT检查
			List<String> list3_4 =new ArrayList<String>();
			for (int i = 1; i < 8; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[4]/ul/li["+i+"]/a/span[2]").getText();
				list3_4.add(text);
			}
			map_list.put(list2.get(3),list3_4);
			
			//住院_MRI检查
			List<String> list3_5 =new ArrayList<String>();
			for (int i = 1; i < 7; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[5]/ul/li["+i+"]/a/span[2]").getText();
				list3_5.add(text);
			}
			map_list.put(list2.get(4),list3_5);
			
			//住院_内窥镜检查
			List<String> list3_6 =new ArrayList<String>();
			for (int i = 1; i < 14; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[6]/ul/li["+i+"]/a/span[2]").getText();
				list3_6.add(text);
			}
			map_list.put(list2.get(5),list3_6);
			
			//住院_病理学检查
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[7]/ul/li[26]/a/span[2]
			List<String> list3_7 =new ArrayList<String>();
			for (int i = 1; i < 27; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[7]/ul/li["+i+"]/a/span[2]").getText();
				list3_7.add(text);
			}
			map_list.put(list2.get(6),list3_7);
			
			//住院_实验室检验
			List<String> list3_8 =new ArrayList<String>();
			for (int i = 1; i < 3; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[8]/ul/li["+i+"]/a/span[2]").getText();
				list3_8.add(text);
			}
			map_list.put(list2.get(7),list3_8);
			
			//住院_分子生物学检查
			List<String> list3_9 =new ArrayList<String>();
			for (int i = 1; i < 3; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[9]/ul/li["+i+"]/a/span[2]").getText();
				list3_9.add(text);
			}
			map_list.put(list2.get(8),list3_9);
			
			//住院_电生理检查
			List<String> list3_10 =new ArrayList<String>();
			for (int i = 1; i < 7; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[10]/ul/li["+i+"]/a/span[2]").getText();
				list3_10.add(text);
			}
			map_list.put(text2,list3_10);
			
			//住院_体格检查
			List<String> list3_11 =new ArrayList<String>();
			for (int i = 1; i < 3; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[11]/ul/li["+i+"]/a/span[2]").getText();
				list3_11.add(text);
			}
			map_list.put(list2.get(10),list3_11);
			
			//住院_化疗信息
			///html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[12]/ul/li[26]/a/span[2]
			List<String> list3_12 =new ArrayList<String>();
			for (int i = 1; i < 27; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[12]/ul/li["+i+"]/a/span[2]").getText();
				list3_12.add(text);
			}
			map_list.put(list2.get(11),list3_12);
			
			//住院_手术治疗
			List<String> list3_13 =new ArrayList<String>();
			for (int i = 1; i < 5; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[13]/ul/li["+i+"]/a/span[2]").getText();
				list3_13.add(text);
			}
			map_list.put(list2.get(12),list3_13);
			
			//住院_放疗信息
			List<String> list3_14 =new ArrayList<String>();
			for (int i = 1; i < 15; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[14]/ul/li["+i+"]/a/span[2]").getText();
				list3_14.add(text);
			}
			map_list.put(list2.get(13),list3_14);
			
			//住院_不良事件
			List<String> list3_15 =new ArrayList<String>();
			for (int i = 1; i < 15; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[15]/ul/li["+i+"]/a/span[2]").getText();
				list3_15.add(text);
			}
			map_list.put(list2.get(14),list3_15);
			
			//住院_疗效评估
			List<String> list3_16 =new ArrayList<String>();
			for (int i = 1; i < 6; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[2]/ul/li[16]/ul/li["+i+"]/a/span[2]").getText();
				list3_16.add(text);
			}
			map_list.put(list2.get(15),list3_16);
			
			//最后统一封装
			list_map_list.add(map_list);
			map_list_map_list.put("住院", list_map_list);
			returnListLast.add(map_list_map_list);
			
		}else {
			logger.debug("登陆失败");
		}
		
		return JSONArray.toJSONString(returnListLast); 
		
	}
	
	/** 
	 * @Title: SearchCrfMuban_Mjz_Level1and2and3_Json
	 * @Description: 查询crf模板，门急诊第一层+第二层+第三层"(返回json)
	 * @param: PhantomJSDriver driver
	 * @param: String value
	 * @param: @throws Exception :
	 * @return: String 返回json
	 * @throws 
	 */
	public static String SearchCrfMuban_Mjz_Level1and2and3_Json(PhantomJSDriver driver,String value) throws Exception{
		// 日志
		logger.debug("查询crf模板，住院第一层+第二层+第三层");
		//最终返回的list（最后统一封装）
		List<Map<String, List<Map<String, List<String>>>>> returnListLast = new ArrayList<>();
		Map<String, List<Map<String, List<String>>>> map_list_map_list = new HashedMap<>();
		List<Map<String, List<String>>>  list_map_list =  new ArrayList<>();
		
		//每次遍历完封装一次
		Map<String, List<String>> map_list= new HashedMap<>();
		
		if ("登陆成功".contains(value)) {
			//查询左侧crf模板
			driver.findElementByXPath("/html/body/div[1]/div[2]/aside/section/ul/li[2]/ul/li/a").click();
			Thread.sleep(2000);
			
			//获取“门急诊”第二层信息
			List<String> list2 =new ArrayList<String>();
			String text2 = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[10]/a/span[2]").getText();
			
			for (int i = 1; i < 17; i++) {
				driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li["+i+"]/a/span[2]").click();
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li["+i+"]/a/span[2]").getText();
				list2.add(text);	
			}
			
			//门急诊_就诊基本信息
			List<String> list3_1 =new ArrayList<String>();
			for (int i = 1; i < 9; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[1]/ul/li["+i+"]/a/span[2]").getText();
				list3_1.add(text);
			}
			map_list.put(list2.get(0),list3_1);
			
			//门急诊_门急诊
			List<String> list3_2 =new ArrayList<String>();
			for (int i = 1; i < 11; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[2]/ul/li["+i+"]/a/span[2]").getText();
				list3_2.add(text);
			}
			map_list.put(list2.get(1),list3_2);
			
			//门急诊_B超检查
			List<String> list3_3 =new ArrayList<String>();
			for (int i = 1; i < 8; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[3]/ul/li["+i+"]/a/span[2]").getText();
				list3_3.add(text);
			}
			map_list.put(list2.get(2),list3_3);
			
			//门急诊_CT检查
			List<String> list3_4 =new ArrayList<String>();
			for (int i = 1; i < 8; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[4]/ul/li["+i+"]/a/span[2]").getText();
				list3_4.add(text);
			}
			map_list.put(list2.get(3),list3_4);
			
			//门急诊_MRI检查
			List<String> list3_5 =new ArrayList<String>();
			for (int i = 1; i < 7; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[5]/ul/li["+i+"]/a/span[2]").getText();
				list3_5.add(text);
			}
			map_list.put(list2.get(4),list3_5);
			
			//门急诊_内窥镜检查
			List<String> list3_6 =new ArrayList<String>();
			for (int i = 1; i < 14; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[6]/ul/li["+i+"]/a/span[2]").getText();
				list3_6.add(text);
			}
			map_list.put(list2.get(5),list3_6);
			
			//门急诊_病理学检查
			List<String> list3_7 =new ArrayList<String>();
			for (int i = 1; i < 27; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[7]/ul/li["+i+"]/a/span[2]").getText();
				list3_7.add(text);
			}
			map_list.put(list2.get(6),list3_7);
			
			//门急诊_实验室检验
			List<String> list3_8 =new ArrayList<String>();
			for (int i = 1; i < 3; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[8]/ul/li["+i+"]/a/span[2]").getText();
				list3_8.add(text);
			}
			map_list.put(list2.get(7),list3_8);
			
			//门急诊_分子生物学检查
			List<String> list3_9 =new ArrayList<String>();
			for (int i = 1; i < 3; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[9]/ul/li["+i+"]/a/span[2]").getText();
				list3_9.add(text);
			}
			map_list.put(list2.get(8),list3_9);
			
			//门急诊_电生理检查
			List<String> list3_10 =new ArrayList<String>();
			for (int i = 1; i < 7; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[10]/ul/li["+i+"]/a/span[2]").getText();
				list3_10.add(text);
			}
			map_list.put(text2,list3_10);
			
			//门急诊_体格检查
			List<String> list3_11 =new ArrayList<String>();
			for (int i = 1; i < 3; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[11]/ul/li["+i+"]/a/span[2]").getText();
				list3_11.add(text);
			}
			map_list.put(list2.get(10),list3_11);
			
			//门急诊_化疗信息
			List<String> list3_12 =new ArrayList<String>();
			for (int i = 1; i < 27; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[12]/ul/li["+i+"]/a/span[2]").getText();
				list3_12.add(text);
			}
			map_list.put(list2.get(11),list3_12);
			
			//门急诊_手术治疗
			List<String> list3_13 =new ArrayList<String>();
			for (int i = 1; i < 5; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[13]/ul/li["+i+"]/a/span[2]").getText();
				list3_13.add(text);
			}
			map_list.put(list2.get(12),list3_13);
			
			//门急诊_放疗信息
			List<String> list3_14 =new ArrayList<String>();
			for (int i = 1; i < 15; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[14]/ul/li["+i+"]/a/span[2]").getText();
				list3_14.add(text);
			}
			map_list.put(list2.get(13),list3_14);
			
			//门急诊_不良事件
			List<String> list3_15 =new ArrayList<String>();
			for (int i = 1; i < 15; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[15]/ul/li["+i+"]/a/span[2]").getText();
				list3_15.add(text);
			}
			map_list.put(list2.get(14),list3_15);
			
			//门急诊_疗效评估
			List<String> list3_16 =new ArrayList<String>();
			for (int i = 1; i < 6; i++) {
				String text = driver.findElementByXPath("/html/body/div[1]/div[3]/div/div[1]/section/aside/ul/div/div/li[3]/ul/li[16]/ul/li["+i+"]/a/span[2]").getText();
				list3_16.add(text);
			}
			map_list.put(list2.get(15),list3_16);
			
			//最后统一封装
			list_map_list.add(map_list);
			map_list_map_list.put("门急诊", list_map_list);
			returnListLast.add(map_list_map_list);
			
		}else {
			logger.debug("登陆失败");
		}
		
		return JSONArray.toJSONString(returnListLast); 
		
	}
	
}

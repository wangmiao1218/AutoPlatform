package com.gennlife.mengyujie.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONException;
//import net.sf.json.JSONObject;
import org.json.JSONObject;
import org.junit.Test;

import com.gennlife.autoplatform.bean.CrfPackageJsonBean;
import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.JsonUtil_bak;
import com.gennlife.autoplatform.utils.ListAndStringUtils;
import com.gennlife.myujie.ConfiguredCrfPackageJson;

public class TestConfiguredCrfPackageJson {

	//private String filePath = "E:\\CRF重组\\自动工具\\工具";
	private String filePath = "D:\\我的文档\\Desktop\\json";
	private String fileName = "模版样例.xlsx";
	private String sheetName = "Sheet1";
	//private JSONObject detailJson = new JSONObject();
	//private JSONArray jsonArray = new JSONArray(); 
	
	
	
	@Test
	public void selectCondition_test3() throws JSONException{
		String strs="A:attribute.NEG,,isnull;B:attribute.FAM,,isnull;C:label,medicalproblem,equal;D:normalized_value,(?<!肾)癌|淋巴瘤|白血病|黑色素瘤|肉瘤,regex;E:normalized_value,瘤,contain;F :attribute.PRO, 恶性, contain; ";
		Map<String,String> elementMap = ListAndStringUtils.valueSpiltBySemicolonToStringMap(strs);
		String selectContent="A&(B&(C&(D||(E&F))))";
		//表达式处理
		selectContent=selectContent.replaceAll("\\|\\|","\\|");
		System.out.println(selectContent);
		
		String substring = selectContent.substring(selectContent.lastIndexOf("(")+1);
		System.out.println(selectContent.lastIndexOf("(")+1);
		System.out.println(substring);
		
		String substring2 = substring.substring(0,substring.indexOf(")"));
		System.out.println(substring2);
		
		System.out.println("===========");
		String substring3 = selectContent.substring(selectContent.lastIndexOf("(")).substring(0,substring.indexOf(")")+2);
		System.out.println(substring3);
		
		String replace = selectContent.replace(substring3, "");
		System.out.println(replace);
	}
	
	
	@Test
	public void selectCondition_test2() throws JSONException{
		String strs="A:attribute.NEG,,isnull;B:attribute.FAM,,isnull;C:label,medicalproblem,equal;D:normalized_value,(?<!肾)癌|淋巴瘤|白血病|黑色素瘤|肉瘤,regex;E:normalized_value,瘤,contain;F :attribute.PRO, 恶性, contain; ";
		Map<String,String> elementMap = ListAndStringUtils.valueSpiltBySemicolonToStringMap(strs);
		String selectContent="FE&D||C&B&A&";
		//表达式处理
		selectContent=selectContent.replaceAll("\\|\\|","\\|");
		System.out.println(selectContent);
		
		//定义变量
		JSONObject detailJson = new JSONObject();
		
		
		//
		CrfPackageJsonBean returnJsonBean = JsonUtil_bak.jsontest(selectContent, elementMap);
		System.out.println(returnJsonBean.getOperator()==null);
		
		detailJson.put("select_condition", BeanMap.create(returnJsonBean));
		System.out.println(detailJson);
		
	}
	
	
	@Test
	public void selectCondition_test() throws JSONException{
		String strs="A:attribute.NEG,,isnull;B:attribute.FAM,,isnull;C:label,medicalproblem,equal;D:normalized_value,(?<!肾)癌|淋巴瘤|白血病|黑色素瘤|肉瘤,regex;E:normalized_value,瘤,contain;F :attribute.PRO, 恶性, contain; ";
		Map<String,String> elementMap = ListAndStringUtils.valueSpiltBySemicolonToStringMap(strs);
		
		String selectContent="FE&D||C&B&A&";
		
		JSONObject detailJson = new JSONObject();
		
		//表达式处理
		selectContent=selectContent.replaceAll("\\|\\|","\\|");
		System.out.println(selectContent);
		
		
		//selectContent表达式解析(没有括号情况)
		if (!selectContent.contains("(") || !selectContent.contains(")")) {
			
			//定义变量
			CrfPackageJsonBean jsonBean = new CrfPackageJsonBean();
			List<Map> listMaps = new ArrayList<Map>();
			BeanMap jsonBeanMap = null;  
			
			
			CrfPackageJsonBean jsonBean2 = new CrfPackageJsonBean();
			List<Map> listMaps2 = new ArrayList<Map>();
			
			
			for (int i = 0; i < selectContent.length(); i++) {
				String element =  String.valueOf(selectContent.charAt(i));
				
				//CrfPackageJsonBean jsonBean = new CrfPackageJsonBean();
				
				//判断
				if (!"&".equals(element) && !"|".equals(element)) {
					//CrfPackageJsonBean jsonBean = new CrfPackageJsonBean();
					//判断对象jsonBean是否为空
					//不为空，则封装成整个Object
					//if (jsonBean.getOperator()!=null) {
					if (jsonBeanMap!=null) {
						listMaps2.add(jsonBeanMap);
						//System.out.println(listMaps);
						//元素
						String[] strings = ListAndStringUtils.valueSpiltByCommaToStrings(elementMap.get(element));
						if (strings.length==3) {
							Map<String,Object> mapJson = new HashedMap<String, Object>();
							mapJson.put("source", strings[0]);
							mapJson.put("target_value", strings[1]);
							mapJson.put("operator", strings[2]);
							
							//封装成list
							listMaps2.add(mapJson);
						}else {
							continue;
						}
						jsonBean2.setDetail(listMaps2);
					}else {
						//元素
						String[] strings = ListAndStringUtils.valueSpiltByCommaToStrings(elementMap.get(element));
						if (strings.length==3) {
							Map<String,Object> mapJson = new HashedMap<String, Object>();
							mapJson.put("source", strings[0]);
							mapJson.put("target_value", strings[1]);
							mapJson.put("operator", strings[2]);
							
							//封装成list
							listMaps.add(mapJson);
						}else {
							continue;
						}
						
						jsonBean.setDetail(listMaps);
					}
					
					
					//System.out.println(listMaps);
					
				}else if ("&".equals(element)) {
					jsonBean.setOperator("and");
					//break;
					
				}else if ("|".equals(element)) {
					jsonBean2.setOperator("or");
					
				}
				
			}
			
			jsonBeanMap = BeanMap.create(jsonBean);  
			
			listMaps2.add(jsonBeanMap);
			jsonBean2.setDetail(listMaps2);
			//
			
			detailJson.put("select_condition", BeanMap.create(jsonBean2));
			System.out.println(detailJson);
			
		}else if (selectContent.contains("(") && selectContent.contains(")")) {
			//selectContent表达式解析(有括号情况)
			
			
		}
		
	}
	
	
	/*
	@Test
	public void selectCondition(){
		String strs="A:attribute.NEG,,isnull;B:attribute.FAM,,isnull;C:label,medicalproblem,equal;D:normalized_value,(?<!肾)癌|淋巴瘤|白血病|黑色素瘤|肉瘤,regex;E:normalized_value,瘤,contain;F :attribute.PRO, 恶性, contain; ";
		Map<String,String> map = ListAndStringUtils.valueSpiltBySemicolonToStringMap(strs);
		
		String selectContent="FE&D||C&B&A&";
		
		JSONObject detailJson = new JSONObject();
		JSONObject fromObject = null;
		
		
		//表达式处理
		selectContent=selectContent.replaceAll("\\|\\|","\\|");
		System.out.println(selectContent);
		
		//selectContent表达式解析(没有括号情况)
		if (!selectContent.contains("(") || !selectContent.contains(")")) {
			for (int i = 0; i < selectContent.length(); i++) {
				String element =  String.valueOf(selectContent.charAt(i));
				
				if (!"&".equals(element) && !"|".equals(element)) {
					//元素
					String[] strings = ListAndStringUtils.valueSpiltByCommaToStrings(map.get(element));
					
					if (strings.length==3) {
						Map<String,Object> mapJson = new HashedMap<String, Object>();
						mapJson.put("source", strings[0]);
						mapJson.put("target_value", strings[1]);
						mapJson.put("operator", strings[2]);
						
						//jsonArray.put(mapJson);
						//System.out.println(jsonArray);
						
						detailJson.accumulate("detail", mapJson);
						System.out.println(detailJson);
						
						
						JSONObject objJson=new JSONObject();
						objJson.put("source", strings[0]);
						objJson.put("target_value", strings[1]);
						objJson.put("operator", strings[2]);
						jsonArray.put(objJson);
						System.out.println(jsonArray);
						
						detailJson.put("detail", jsonArray);
						System.out.println(detailJson);
						
						 
					}else {
						continue;
					}
					
				}else if ("&".equals(element)) {
					fromObject = JSONObject.fromObject(detailJson);
					
					detailJson.accumulate("operator","and");
					detailJson.accumulate("detail",fromObject);
					
					
				}else if ("|".equals(element)) {
					fromObject = JSONObject.fromObject(detailJson);
					detailJson.accumulate("operator","or");
					detailJson.accumulate("detail",fromObject);
					
				}
				
				
			}
			System.out.println(detailJson);
			
		}else if (selectContent.contains("(") && selectContent.contains(")")) {
			//selectContent表达式解析(有括号情况)
			
			
		}
		
	}
	*/

	
	@Test
	public void getJsonFile(){
		Excel excel = new Excel(filePath, fileName, sheetName);
		ConfiguredCrfPackageJson.getJsonFile(excel);
	}
}
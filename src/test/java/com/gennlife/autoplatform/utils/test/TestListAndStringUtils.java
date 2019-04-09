package com.gennlife.autoplatform.utils.test;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.ExcelUtils;
import com.gennlife.autoplatform.utils.ListAndStringUtils;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;

public class TestListAndStringUtils {
	
	private String filePath = "E:\\安贞\\！安贞心血管";
	private String fileName = "参会医院名称更改0923－添加上级科室.xlsx";
	private String sheetName = "Sheet1";
	

	@Test
	public void countChar(){
		String strs="$.visits[*].record.admissions_record	.admissions_records_chief_complaint ";
		int a= ListAndStringUtils.countChar(strs,';');
		System.out.println(a);
		System.out.println(a==1);
		System.out.println(a==3);
	}
	
	
	@Test
	public void replaceBlank(){
		String strs="$.visits[*].record.admissions_record	.admissions_records_chief_complain; ";
		String strings= ListAndStringUtils.replaceBlankAndLastSemicolon(strs);
		System.out.println(strings);
		String value ="dsadasdasd;hgjghjhjgh;";
		String lastStr = value.substring(value.length()-1,value.length());
		if (";".equals(lastStr)) {
			value=value.substring(0,value.length()-1); 
			System.out.println("1--"+value);
		}else {
			System.out.println("2--"+value);
		}
	}
	
	
	@Test
	public void dealWithpatientDetailByAsteriskToString(){
		String strs="$.visits[*].record.admissions_record.admissions_records_chief_complaint";
		String strings= ListAndStringUtils.dealWithpatientDetailByAsteriskToString(strs);
		System.out.println(strings);
	}
	
	@Test
	public void valueSpiltByCommaToStrings(){
		String strs="attribute.NEG,,isnull";
		String[] strings= ListAndStringUtils.valueSpiltByCommaToStrings(strs);
		System.out.println(strings.length);
		if (strings.length==3) {
			JSONObject objJson=new JSONObject();
			objJson.put("source", strings[0]);
			objJson.put("target_value", strings[1]);
			objJson.put("operator", strings[2]);
			System.out.println(objJson);
		}
	}
	
	
	@Test
	public void valueSpiltBySemicolonToStringListMap(){
		String strs="A:attribute.NEG,,isnull;B:attribute.FAM,,isnull;C:label,medicalproblem,equal;D:normalized_value,(?<!肾)癌|淋巴瘤|白血病|黑色素瘤|肉瘤,regex;E:normalized_value,瘤,contain;F :attribute.PRO, 恶性, contain; ";
		Map<String,String> map = ListAndStringUtils.valueSpiltBySemicolonToStringMap(strs);
		System.out.println(map);
		System.out.println(map.get("A"));
	}
	
	
	@Test
	public void compareTwoListReturnDiffrent() throws Exception{
		Excel excel = new Excel(filePath, fileName, sheetName);
		List<String> list = ExcelUtils.readExcelOfList(excel, 0);
		List<String> listExcel = list.subList(1, list.size());
		
		System.out.println(listExcel.size());
		for (int i = 0; i < listExcel.size(); i++) {
			System.out.println(listExcel.get(i));
		}
		System.out.println("===============");
		
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		Thread.sleep(10000);
		//去页面 遍历下拉列表所有选项
		Select selall = new Select(driver.findElementByXPath(".//*[@id='crf-lab']/select"));
		//封装成List<WebElement>
		List<WebElement> lw= selall.getOptions();
		
		List<String> listWeb= ListAndStringUtils.listWebElementToListString(lw);
		System.out.println(listWeb.size());
		for (int i = 0; i < listWeb.size(); i++) {
			System.out.println(listWeb.get(i));
		}
		System.out.println("===============");
		
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
				
		List<String> returnDiffrent = ListAndStringUtils
				.compareTwoListReturnDiffrent(listExcel,listWeb);
		System.out.println(returnDiffrent.size());
		for (int i = 0; i < returnDiffrent.size(); i++) {
			System.out.println(returnDiffrent.get(i));
		}
		System.out.println("===============");
		
	}

	
	@Test
	public void displayMainKeyToEnglishName(){
		String displayMainKey="schema.Hospitalization.GENERAL_SITUATION.CONSOLIDATION";
		String value = ListAndStringUtils.displayMainKeyToEnglishName(displayMainKey);
		System.out.println(value);
	}
	
	@Test
	public void valueSpiltToStringList(){
		String value="就诊.就诊基本信息.入院（就诊）时间；就诊.就诊基本信息.挂号日期";
		List<String> list = ListAndStringUtils.valueSpiltBySemicolonToStringList(value);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void chNamesListFilter(){
		String value=" 剂  量（mg/天）a，d ff,d sdf,dfdsf，fsf、bc：1.2.3.bcd？";
		//去掉中英文（）之间
		if (value.indexOf("（")!=-1) {
			value=value.replaceAll(value.substring(value.indexOf("（"),value.indexOf("）")+1),"");
		}
		if (value.indexOf("(")!=-1) {
			value=value.replaceAll(value.substring(value.indexOf("("),value.indexOf(")")+1),"");
		}
		//去掉中英文:之后
		if (value.indexOf("：")!=-1) {
			value = value.substring(0, value.indexOf("："));
		}
		if (value.indexOf(":")!=-1) {
			value = value.substring(0, value.indexOf(":"));
		}
		
		//去掉中英文？之后
		if (value.indexOf("？")!=-1) {
			value = value.substring(0, value.indexOf("？"));
		}
		if (value.indexOf("?")!=-1) {
			value = value.substring(0, value.indexOf("?"));
		}
		
		//删掉、
		if (value.indexOf("、")!=-1) {
			value = value.replace("、",""); 
		}
		
		//删掉空格
		if (value.indexOf(" ")!=-1) {
			value = value.replace(" ",""); 
		}
		
		//删掉中引文，
		if (value.indexOf("，")!=-1) {
			value = value.replace("，","");
		}
		if (value.indexOf(",")!=-1) {
			value = value.replace(",","");
		}
		
		System.out.println(value);
	}
	
	@Test
	public void trimStringOfEqualSign(){
		String value="有无疾病史=有";
		String[] strings = ListAndStringUtils.trimStringOfEqualSign(value);
		System.out.println(strings.length);
		System.out.println(strings[0]);
		System.out.println(strings[1]);
	}
	
	@Test
	public void stringToSubstringReturnFilePath(){
		String value="E:\\yujie\\2\\模板结构-乳腺癌.xlsx";
		String str = ListAndStringUtils.stringToSubstringReturnFilePath(value);
		System.out.println(str);
	}
	
	@Test
	public void stringToSubstringReturnFileName(){
		String value="E:\\yujie\\2\\test.xlsx";
		String str = ListAndStringUtils.stringToSubstringReturnFileName(value);
		System.out.println(str);
	}
	
	@Test
	public void StringListReturnRandomString(){
		String value="I型糖尿病;II型糖尿病";
		String string = ListAndStringUtils.stringListReturnRandomString(value);
		System.out.println(string);
	}
	
	
}
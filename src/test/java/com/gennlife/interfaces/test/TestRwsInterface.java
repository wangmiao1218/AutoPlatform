package com.gennlife.interfaces.test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.junit.Test;

import com.alibaba.fastjson.JSONPath;
import com.gennlife.autoplatform.utils.JsonUtils;
import com.gennlife.interfaces.RwsInterface;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;


public class TestRwsInterface {
	
	final static String loginURL = "http://10.0.2.162/uranus/UI/user/Login?param=%7B%22user%22:%223333%22,%22pwd%22:%22123456%22%7D&timeStamp=1534478147831";
	final static String rwsCalculateURL = "http://10.0.2.162/uranus/UI/rws/SaveOrSearchActive";
	final static String rwsResultURL = "http://10.0.2.162/uranus/UI/rws/FindTotalForImport";
	
	
	@Test
	public void doGet() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String sessionId = null;
		String id = null;
		String applyTotal = null;
		
		//第一步：请求登录,获取session
		JSONObject loginResultObject = RwsInterface.doGet(httpClient,loginURL);
		//System.out.println(loginResultObject);
		//System.out.println("1".contains(loginResultObject.getString("code")));
		//判断返回结果
		if ("1".contains(loginResultObject.getString("code"))) {
			sessionId = ((JSONObject) loginResultObject.get("data")).getString("uid");
			System.out.println(sessionId);
			//第二步：请求计算接口,获取id
			String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			JSONObject calculateResultObject =  RwsInterface.rwsCalculate(httpClient,rwsCalculateURL,sessionId, timeStr);
			System.out.println(calculateResultObject);
			System.out.println("200".contains(calculateResultObject.getString("status")));
			
			if ("200".contains(calculateResultObject.getString("status"))) {
				id = ((JSONObject) calculateResultObject.get("data")).getString("id");
				System.out.println(id);
				System.out.println("sleep...");
				Thread.sleep(120000);
				//第三步：查看计算结果接口，直到有数值类型，保存到map中
				JSONObject resultObject = RwsInterface.rwsResult(httpClient,rwsResultURL,sessionId, id);
				if ("200".contains(calculateResultObject.getString("status"))) {
					applyTotal = ((JSONObject) resultObject.get("data")).getString("applyTotal");
					System.out.println(applyTotal);
				}
			}
		}else {
			System.out.println("error");
		}
	}
	
	@Test
	public void test(){
		String str ="21312";
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			System.out.println("false"); 
		}else {
			System.out.println("true"); 
		} 
	}
	
	@Test
	public void test2() throws Exception{
		JSONObject json = JsonUtils.readFileContentReturnJson("E:\\RWS\\rws稳定性\\json.json");
		
		String regex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";

        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(json.toString());
        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            for (int i = 0; i < matcher.groupCount(); i++) {
				
			}
            System.out.println(matcher.group(0));
        }
        
       /* 
        String all = null;
        while (matcher.find ()){
            System.out.println (matcher.group ());
            String a=matcher.group ();
            all = json.toString().replaceAll(a, 
            		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        
        Matcher matcher2 = p.matcher(all);
        while (matcher2.find ()){
            System.out.println (matcher2.group ());
        }*/
	}
	
	
	
}
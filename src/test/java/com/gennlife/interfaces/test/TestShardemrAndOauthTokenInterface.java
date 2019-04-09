package com.gennlife.interfaces.test;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONObject;
import org.junit.Test;

import com.gennlife.interfaces.ShardemrAndOauthTokenInterface;

public class TestShardemrAndOauthTokenInterface {
	
	final static String oauthURL = "http://10.0.2.184:8072/oauth/oauth/token";
	final static String shardemrURL = "http://10.0.2.184:8072/shardingdata-service/vitark/sharding/shardemr";
	
	@Test
	public void getOauthToken() throws Exception{
		Map<String,String> map = new HashedMap<String, String>();
		map.put("grant_type", "password");
		map.put("username", "testshard");
		map.put("password", "123456");
		map.put("client_id", "webapp");
		map.put("client_secret", "web");
		
		String responses = ShardemrAndOauthTokenInterface.getOauthToken(oauthURL, map);
		System.out.println(responses);
		JSONObject jsonObject = new JSONObject(responses);
		System.out.println("JSONObject--"+jsonObject);
	}
	
	@Test
	public void getShardemr() throws Exception{
		Map<String,String> shardemrMap = new HashedMap<String, String>();
		//只是测试用，10min过期
		shardemrMap.put("access_token", "60db63b2-55f9-4832-9a56-ee7e48b3a828");
		//shardemrMap.put("inpatient_sn", "");
		//shardemrMap.put("patient_id", "");
		shardemrMap.put("patient_sn", "pat_60d87d6c8b784976ef2dd0df2e541f1c");
		//shardemrMap.put("scopes", "");
		
		String responses = ShardemrAndOauthTokenInterface.getShardemr(shardemrURL, shardemrMap);
		System.out.println(responses);
	}
	
	
}
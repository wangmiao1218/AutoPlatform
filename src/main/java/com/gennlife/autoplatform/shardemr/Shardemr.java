package com.gennlife.autoplatform.shardemr;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

import com.gennlife.autoplatform.utils.FileUtils;
import com.gennlife.interfaces.ShardemrAndOauthTokenInterface;


/**
 * @Description:测试shardemr接口
 * @author: wangmiao
 * @Date: 2018年7月5日 下午2:27:55 
 */
public class Shardemr { 
	
	/** 
	* @Title: getAccessToken 
	* @Description: 根据接口，解析接口获取AccessToken，因为有10min的显示，所以每次请求加上重新请求接口
	* @Date: 2018年7月5日 下午2:34:58 
	* @param: @param url
	* @param: @param map
	* @param: @return
	* @return: String
	* @throws 
	*/
	public static String getAccessToken(String url,Map<String, String> map) {
		String responses = ShardemrAndOauthTokenInterface.getOauthToken(url, map);
		JSONObject jsonObject=null;
		String accessToken=null;
		try {
			jsonObject = new JSONObject(responses);
			accessToken = (String) jsonObject.get("access_token");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	
	/** 
	* @Title: getShardemr 
	* @Description: 先请求oauthToken接口获取token，在请求shardemr，获取数据
	* @author: wangmiao
	* @Date: 2018年7月5日 下午2:46:13 
	* @param: @param url
	* @param: @param oauthTokenMap
	* @param: @param shardemrMap
	* @param: @return
	* @return: String
	* @throws 
	*/
	public static String getShardemr(String oauthURL,String shardemrURL,
			Map<String, String> oauthTokenMap,
			Map<String, String> shardemrMap) {
		String accessToken=getAccessToken(oauthURL, oauthTokenMap);
		shardemrMap.put("access_token", accessToken);
		String shardemr = ShardemrAndOauthTokenInterface.getShardemr(shardemrURL, shardemrMap);
		return shardemr;
	}
	
	
	/** 
	* @Title: CreateShardemrCallable 
	* @Description: 创建查询接口的callable，便于循环创建线程，进行接口压力测试
	* @author: wangmiao
	* @Date: 2018年7月6日 下午4:49:19 
	* @param: @param oauthURL
	* @param: @param shardemrURL
	* @param: @param oauthTokenMap
	* @param: @param shardemrMap
	* @param: @return
	* @return: Callable<String>
	* @throws 
	*/
	public static Callable<String> CreateShardemrCallable(final String oauthURL,
			final String shardemrURL,final Map<String, String> oauthTokenMap,
			final Map<String, String> shardemrMap) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				String shardemr = Shardemr.getShardemr(oauthURL, shardemrURL, oauthTokenMap, shardemrMap);
				String value = FileUtils.writeContentToFile("F:/test_"+UUID.randomUUID()+".json",shardemr);
				if ("ok".equals(value)) {
					return "success";
				}else {
					return "error";
				}
			}
		};
	}
	
	
	/** 
	* @Title: createOauthTokenMap 
	* @Description: 不同用户，创建OauthTokenMap，确定返回不同的token
	* @author: wangmiao
	* @Date: 2018年7月6日 下午5:17:48 
	* @param: @param name
	* @param: @return
	* @return: Map<String,String>
	* @throws 
	*/
	public static Map<String, String> createOauthTokenMap(String name) {
		ConcurrentHashMap<String,String> oauthTokenMap = new ConcurrentHashMap<>();
		oauthTokenMap.put("grant_type", "password");
		oauthTokenMap.put("username", name);
		oauthTokenMap.put("password", "123456");
		oauthTokenMap.put("client_id", "webapp");
		oauthTokenMap.put("client_secret", "web");
		return oauthTokenMap;
	}
}

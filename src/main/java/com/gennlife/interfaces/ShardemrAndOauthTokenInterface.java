package com.gennlife.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @Description: 通过接口：http://10.0.2.184:8072/oauth/oauth/token，获取接口数据
 *               使用“application/x-www-form-urlencoded”格式
 * @author: wangmiao
 * @Date: 2018年7月5日 上午10:57:29
 */
public class ShardemrAndOauthTokenInterface {

	/** 
	* @Title: getOauthToken 
	* @Description: 使用application/x-www-form-urlencoded格式，请求接口返回OauthToken数据
	* (不需要设置请求头)
	* @author: wangmiao
	* @Date: 2018年7月5日 下午2:17:28 
	* @param: @param url
	* @param: @param params
	* @param: @return
	* @return: String
	* @throws 
	*/
	public static String getOauthToken(String url,Map<String, String> map) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String responses = null;
		try {
			httpClient = HttpClients.createDefault();
			/**
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(20000)
					.setConnectionRequestTimeout(15000).setSocketTimeout(20000)
					.build(); 
			 */
			httpPost = new HttpPost(url);
			httpPost.addHeader("content-type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			// 表单中的域用类NameValuePair来表示，该类的构造函数第一个参数是域名，第二参数是该域的值；
			for (String pKey : map.keySet()) {
				formparams.add(new BasicNameValuePair(pKey, map.get(pKey)));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String postResult = EntityUtils.toString(entity, "UTF-8");
			responses = StringEscapeUtils.unescapeJava(postResult);
			if (responses != null) {
				EntityUtils.consume(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpPost != null) {
					httpPost.releaseConnection();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responses;
	}
	
	
	/** 
	* @Title: getShardemr 
	* @Description: 使用application/x-www-form-urlencoded格式，请求接口返回Shardemr数据
	* (需要设置请求头：api-version：v1)
	* @author: wangmiao
	* @Date: 2018年7月5日 下午2:55:43 
	* @param: @param url
	* @param: @param map
	* @param: @return
	* @return: String
	* @throws 
	*/
	public static String getShardemr(String url,Map<String, String> map) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String responses = null;
		try {
			httpClient = HttpClients.createDefault();
			/**
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(20000)
					.setConnectionRequestTimeout(15000).setSocketTimeout(20000)
					.build(); 
			*/
			httpPost = new HttpPost(url);
			httpPost.addHeader("content-type","application/x-www-form-urlencoded; charset=UTF-8");
			httpPost.addHeader("api-version","v1");
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (String pKey : map.keySet()) {
				formparams.add(new BasicNameValuePair(pKey, map.get(pKey)));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String postResult = EntityUtils.toString(entity, "UTF-8");
			responses = StringEscapeUtils.unescapeJava(postResult);
			if (responses != null) {
				EntityUtils.consume(entity);// 关闭内容流
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpPost != null) {
					httpPost.releaseConnection();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responses;
	}

}

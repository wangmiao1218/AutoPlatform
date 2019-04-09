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
		// 创建默认的httpClient实例
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		// 定义返回内容
		String responses = null;
		try {
			httpClient = HttpClients.createDefault();
			// 设置连接超时时间和获取数据超时时间
			/**
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(20000)
					.setConnectionRequestTimeout(15000).setSocketTimeout(20000)
					.build(); 
			 */
			// 创建Post请求的实例，并传入待连接的地址
			httpPost = new HttpPost(url);
			//httpPost.setConfig(requestConfig);

			// 设置请求头
			httpPost.addHeader("content-type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			// 创建Post请求参数队列
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			// 表单中的域用类NameValuePair来表示，该类的构造函数第一个参数是域名，第二参数是该域的值；
			for (String pKey : map.keySet()) {
				formparams.add(new BasicNameValuePair(pKey, map.get(pKey)));
			}
			// 设置表单的编码格式
			httpPost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
			// 执行post请求，调用第一步中创建好的实例的execute方法来执行第二步中创建好的method实例
			HttpResponse response = httpClient.execute(httpPost);
			// 读response，获取响应实体
			HttpEntity entity = response.getEntity();
			// 读入内容流，并以字符串形式返回
			String postResult = EntityUtils.toString(entity, "UTF-8");
			// 把Unicode编码转中文
			responses = StringEscapeUtils.unescapeJava(postResult);
			if (responses != null) {
				// 打印响应内容
				//System.out.println("接口响应：" + responses + "\n"); // 打印响应内容
				EntityUtils.consume(entity);// 关闭内容流
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpPost != null) {
					// 关闭连接
					httpPost.releaseConnection();
				}
				if (httpClient != null) {
					// 关闭连接
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
		// 创建默认的httpClient实例
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		// 定义返回内容
		String responses = null;
		try {
			httpClient = HttpClients.createDefault();
			// 设置连接超时时间和获取数据超时时间
			/**
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(20000)
					.setConnectionRequestTimeout(15000).setSocketTimeout(20000)
					.build(); 
			*/
			// 创建Post请求的实例，并传入待连接的地址
			httpPost = new HttpPost(url);
			//httpPost.setConfig(requestConfig);
			
			// 设置请求头
			httpPost.addHeader("content-type","application/x-www-form-urlencoded; charset=UTF-8");
			//新增请求头的参数api-version：v1
			httpPost.addHeader("api-version","v1");
			
			// 创建Post请求参数队列
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			// 表单中的域用类NameValuePair来表示，该类的构造函数第一个参数是域名，第二参数是该域的值；
			for (String pKey : map.keySet()) {
				formparams.add(new BasicNameValuePair(pKey, map.get(pKey)));
			}
			// 设置表单的编码格式
			httpPost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
			// 执行post请求，调用第一步中创建好的实例的execute方法来执行第二步中创建好的method实例
			HttpResponse response = httpClient.execute(httpPost);
			// 读response，获取响应实体
			HttpEntity entity = response.getEntity();
			// 读入内容流，并以字符串形式返回
			String postResult = EntityUtils.toString(entity, "UTF-8");
			// 把Unicode编码转中文
			responses = StringEscapeUtils.unescapeJava(postResult);
			if (responses != null) {
				// 打印响应内容
				//System.out.println("接口响应：" + responses + "\n"); // 打印响应内容
				EntityUtils.consume(entity);// 关闭内容流
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpPost != null) {
					// 关闭连接
					httpPost.releaseConnection();
				}
				if (httpClient != null) {
					// 关闭连接
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return responses;
	}

}

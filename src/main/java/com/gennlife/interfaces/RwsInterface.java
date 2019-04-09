package com.gennlife.interfaces;

import java.io.IOException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @Description: rws相关接口
 * @author: wangmiao
 * @Date: 2018年8月17日 下午1:47:28 
 */
public class RwsInterface {

	private static Logger logger = Logger.getLogger(RwsInterface.class); 
	
	
	/**
	* @Title: doGet 
	* @Description: get请求登录，获取session
	* 		注意：传入httpClient，方便后续使用同一个httpClient，保持会话的session，便于进行rws计算的请求
	* @author: wangmiao
	* @Date: 2018年8月20日 上午9:44:14 
	* @param: @param httpClient
	* @param: @param url
	* @param: @return
	* @param: @throws Exception
	* @return: JSONObject
	* @throws 
	*/
	public static JSONObject doGet(CloseableHttpClient httpClient,String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try {
			response = httpClient.execute(httpGet);
			logger.info("请求的状态码:"+response.getStatusLine().getStatusCode());
			entity = response.getEntity();
			
			/*
			 * debug信息使用
			// 获取所有头信息 
			Header[] allHeaders = response.getAllHeaders(); 
			for(Header allHeader : allHeaders) {
				logger.info(allHeader.getName());
				logger.info(allHeader.getValue());
				logger.info(allHeader.toString());
			}
			 */
			
			// 方法一 官方不推荐
			if (entity != null) {
				return new JSONObject(EntityUtils.toString(entity, "utf-8"));
			}
			
			/*
			// 方法二 官方推荐 使用流的形式处理请求结果
			if (entity != null) {
				InputStream content = entity.getContent();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				}
				bufferedReader.close();
			}
			*/
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			response.close();
		}
		
		return new JSONObject(EntityUtils.toString(entity, "utf-8"));
	}

	
	/** 
	* @Title: rwsCalculate 
	* @Description: 请求rws计算接口，返回json结果
	* 			注：与登录传入同一个httpClient，便于session管理
	* @author: wangmiao
	* @Date: 2018年8月20日 上午9:54:05 
	* @param: @param httpClient
	* @param: @param url
	* @param: @param sessionId
	* @param: @param timeStr
	* @param: @return
	* @param: @throws Exception
	* @return: String
	* @throws 
	*/
	public static JSONObject rwsCalculate(CloseableHttpClient httpClient,String url,
			String sessionId,String timeStr) throws JSONException {
		JSONObject calculateResultObject = null;
		String params="{\"active\":{\"isTmp\":0,\"dataGroup\":[\"patient_info\",\"visit_info\","
				+ "\"inspection_reports\"],\"sortKey\":\"visits.inspection_reports.REPORT_TIME\","
				+ "\"activeType\":1,\"confirmActiveId\":\"9BA19E5522694F3C9BFCBD0AE9BA68B9\","
				+ "\"createTime\":1534758315000,\"name\":\"test1\",\"updateTime\":1534761534000,"
				+ "\"id\":\"103879B685E04CC89BAB029F32FB0290\",\"projectName\":\"稳定性\","
				+ "\"config\":[{\"activeIndexId\":\"103879B685E04CC89BAB029F32FB0290\","
				+ "\"indexTypeDesc\":\"\",\"dateFormat\":\"\",\"searchScope\":\"\","
				+ "\"operator\":\"all\",\"activeResult\":\"visits.inspection_reports\","
				+ "\"indexType\":\"\",\"operatorNum\":\"\",\"indexColumn\":\"\","
				+ "\"indexColumnDesc\":\"\",\"indexResultValue\":\"\",\"function\":\"\","
				+ "\"activeResultDesc\":\"就诊.检验报告\",\"functionParam\":\"\","
				+ "\"id\":\"252B913F719F428A80B6A5D04927658E\",\"conditions\":[{\"level\":1,"
				+ "\"operatorSign\":\"and\",\"strongRef\":[],\"details\":[{\"operatorSign\":\"simpleDate#<\","
				+ "\"sourceTagName\":\"visits.visit_info.ADMISSION_DATE\",\"logicSing\":\"\","
				+ "\"operatorSignDesc\":\"早于\",\"strongRef\":[],\"targetTagName\":\"\","
				+ "\"targetTagNameDesc\":\"\",\"activeIndexConfigId\":\"252B913F719F428A80B6A5D04927658E\","
				+ "\"needPath\":\".\",\"refRelation\":\"direct\",\"type\":2,\"inner\":[],"
				+ "\"parentId\":\"094078C4EB8147B3AFAC45D445C01C9E\",\"refActiveId\":\"\","
				+ "\"sourceTagNameDesc\":\"就诊.就诊基本信息.入院（就诊）时间\",\"refActiveName\":\"\","
				+ "\"details\":[],\"detail\":[],\"id\":\"30E529F8F36A48CCB786884590F89704\","
				+ "\"value\":[\""+timeStr+"\"]}],\"detail\":[{\"operatorSign\":\"simpleDate#<\","
				+ "\"sourceTagName\":\"visits.visit_info.ADMISSION_DATE\",\"logicSing\":\"\","
				+ "\"operatorSignDesc\":\"早于\",\"strongRef\":[],\"targetTagName\":\"\","
				+ "\"targetTagNameDesc\":\"\",\"activeIndexConfigId\":\"252B913F719F428A80B6A5D04927658E\","
				+ "\"needPath\":\".\",\"refRelation\":\"direct\",\"type\":2,\"inner\":[],"
				+ "\"parentId\":\"094078C4EB8147B3AFAC45D445C01C9E\",\"refActiveId\":\"\","
				+ "\"sourceTagNameDesc\":\"就诊.就诊基本信息.入院（就诊）时间\",\"refActiveName\":\"\","
				+ "\"details\":[],\"detail\":[],\"id\":\"30E529F8F36A48CCB786884590F89704\","
				+ "\"value\":[\""+timeStr+"\"]}],\"id\":\"094078C4EB8147B3AFAC45D445C01C9E\","
				+ "\"activeIndexConfigId\":\"252B913F719F428A80B6A5D04927658E\",\"needPath\":\".\","
				+ "\"type\":1,\"inner\":[],\"uuid\":\"7beef6ea-3d08-46ae-b8c3-5d4fe2a6a33c\"}],"
				+ "\"indexResultValueIsEqual\":\"1\"}],\"projectId\":\"c5d9cb4d-1c53-40a9-8f3b-76373501ba7e\"},"
				+ "\"crfId\":\"EMR\",\"isSearch\":0}";
		
		HttpPost httpPost = null;
		String responses = null;
		try {
			httpPost = new HttpPost(url);
			logger.info("请求地址："+httpPost);
			httpPost.addHeader("content-type","application/json; charset=UTF-8");
			httpPost.addHeader("session", sessionId);
			StringEntity se = new StringEntity(params);
			httpPost.setEntity(se);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (responses.contains("{")) {
			calculateResultObject=new JSONObject(responses);
		}else {
			calculateResultObject=null;
		}
		return calculateResultObject;
	}
	
	
	/** 
	* @Title: rwsResult 
	* @Description: 请求rws结果接口，id为上面计算结果返回的id
	* 		注：与登录传入同一个httpClient，便于session管理
	* @author: wangmiao
	* @Date: 2018年8月20日 上午9:58:16 
	* @param: @param httpClient
	* @param: @param url
	* @param: @param sessionId
	* @param: @param id
	* @param: @return
	* @param: @throws Exception
	* @return: JSONObject
	* @throws 
	*/
	public static JSONObject rwsResult(CloseableHttpClient httpClient,String url,
			String sessionId,String id) throws Exception {
		String params="{\"projectId\":\"c5d9cb4d-1c53-40a9-8f3b-76373501ba7e\","
				+ "\"type\":2,\"activeId\":\""+id+"\"}";
		
		HttpPost httpPost = null;
		String responses = null;
		try {
			httpPost = new HttpPost(url);
			logger.info("请求地址："+httpPost);
			httpPost.addHeader("content-type","application/json; charset=UTF-8");
			httpPost.addHeader("session", sessionId);
			StringEntity se = new StringEntity(params);
			httpPost.setEntity(se);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new JSONObject(responses);
	}
	
}

package com.gennlife.rws;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gennlife.autoplatform.bean.RwsBean;
import com.gennlife.autoplatform.utils.ListAndStringUtils;
import com.gennlife.interfaces.RwsInterface;


/**
 * @Description: rws计算稳定性测试
 * @author: wangmiao
 * @Date: 2018年8月17日 下午1:52:23 
 */
public class RwsCalculateStabilityTask {
	
	private static final Logger logger = LoggerFactory.getLogger(RwsCalculateStabilityTask.class);
	//定义返回结果
	//private static final Map<String, String> map = new ConcurrentHashMap<>();
			
	
	/** 
	* @Title: rwsCal 
	* @Description:  rws稳定性计算，请求携带session后，请求rws计算环境
	* @author: wangmiao
	* @Date: 2018年8月20日 下午2:33:38 
	* @param: @param loginURL
	* @param: @param rwsCalculateURL
	* @param: @param rwsResultURL
	* @param: @return
	* @param: @throws Exception
	* @return: RwsBean
	* @throws 
	*/
	public static RwsBean rwsCal(String loginURL,String rwsCalculateURL
			,String rwsResultURL) throws Exception{
		RwsBean rwsBean=null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String sessionId = null;
		String id = null;
		String applyTotal = null;
		
		JSONObject loginResultObject = RwsInterface.doGet(httpClient,loginURL);
		if (loginResultObject.has("code") && "1".contains(loginResultObject.getString("code"))) {
			sessionId = ((JSONObject) loginResultObject.get("data")).getString("uid");
			logger.info("sessionId:"+sessionId);

			String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			JSONObject calculateResultObject =  RwsInterface.rwsCalculate(httpClient,rwsCalculateURL,sessionId, timeStr);
			if (ListAndStringUtils.isJsonObject(calculateResultObject) &&
					calculateResultObject.has("status") && 
					"200".contains(calculateResultObject.getString("status"))) {
				id = ((JSONObject) calculateResultObject.get("data")).getString("id");
				logger.info("id:"+id);
				
				//暂停2min钟，看结果，（固定2min时间，没算出来即为未计算）
				logger.info("sleep中...");
				Thread.sleep(120000);
				JSONObject resultObject = RwsInterface.rwsResult(httpClient,rwsResultURL,sessionId, id);
				
				if (resultObject.has("status") && "200".contains(resultObject.getString("status"))){
					applyTotal = ((JSONObject) resultObject.get("data")).getString("applyTotal");
					
					//map.put(id, applyTotal);
					//判断是否为数字
					if (ListAndStringUtils.isNumeric(applyTotal)) {
						rwsBean = new RwsBean(timeStr,applyTotal,timeStr);
					}else {
						rwsBean = new RwsBean(timeStr,"非数值",timeStr);
					}
				}
				
				/*
				//后续可动态判断
				//判断是否为数字
				ListAndStringUtils.isNumeric(applyTotal);
				while (!ListAndStringUtils.isNumeric(applyTotal)) {
					
			    }
				*/
			}
		}
		return rwsBean;
	}
	
	
}

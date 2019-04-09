package com.gennlife.autoplatform.service.impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gennlife.autoplatform.service.CreateShardemrTaskSerive;
import com.gennlife.autoplatform.shardemr.Shardemr;

@Service
public class CreateShardemrTaskSeriveImpl implements CreateShardemrTaskSerive{
	
	private static final Logger logger = LoggerFactory.getLogger(CreateShardemrTaskSeriveImpl.class);
	
	public static final String oauthURL = "http://10.0.2.184:8072/oauth/oauth/token";
	public static final String shardemrURL = "http://10.0.2.184:8072/shardingdata-service/vitark/sharding/shardemr";
	
	@Override
	public void createShardemrTask() throws Exception {
		logger.info("Create Shardemr thread");
		ConcurrentHashMap<String,String> shardemrMap = new ConcurrentHashMap<>();
		shardemrMap.put("inpatient_sn", "278684");
		shardemrMap.put("patient_id", "320622196104016430");
		shardemrMap.put("patient_sn", "pat_60d87d6c8b784976ef2dd0df2e541f1c");
		ExecutorService threadPool =Executors.newFixedThreadPool(10); 
		for (int i = 1; i < 11; i++) {
			Future<String> futureTest=threadPool.submit(
					Shardemr.CreateShardemrCallable(oauthURL, shardemrURL, Shardemr.createOauthTokenMap("testshard"+i), shardemrMap));
			try {  
				futureTest.get();  
			} catch (InterruptedException e) {  
				e.printStackTrace();  
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		threadPool.shutdown();
	}
	
}

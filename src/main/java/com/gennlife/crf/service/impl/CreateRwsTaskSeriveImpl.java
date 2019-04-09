package com.gennlife.crf.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.gennlife.crf.service.CreateRwsTaskSerive;
import com.gennlife.rws.RwsTask;

@Service
public class CreateRwsTaskSeriveImpl implements CreateRwsTaskSerive{
	
	private static final Logger logger = LoggerFactory.getLogger(CreateRwsTaskSeriveImpl.class);
	
	public static final String rwsUrl="http://10.0.2.162/uranus/project_index.html";
	
	@Override
	public void createRwsTask() throws Exception {
		//开始执行定时任务（用三个线程,模拟三个用户）
		//=====================线程池方法====================================
		//创建线程池并返回ExecutorService实例 
		// 执行任务
		logger.info("Create Rws thread");
		ExecutorService threadPool =Executors.newFixedThreadPool(3); 
		Future<String> futureTest1 = threadPool.submit(RwsTask.CreateRwsCallable(rwsUrl, "testrws001", "testrws001"));
		Future<String> futureTest2 = threadPool.submit(RwsTask.CreateRwsCallable(rwsUrl, "testrws002", "testrws002"));
		Future<String> futureTest3 = threadPool.submit(RwsTask.CreateRwsCallable(rwsUrl, "testrws003", "testrws003"));
		
		try {
			futureTest1.get();  
			futureTest2.get();
			futureTest3.get(); 
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } 

		//关闭线程池
		if (futureTest1.isDone() && futureTest2.isDone() && futureTest3.isDone()) {
			 //关闭线程池和服务  
            threadPool.shutdown();
            logger.info("threadPool shutdown");
		} else {
			logger.info("Error");
		} 
	}
	
}

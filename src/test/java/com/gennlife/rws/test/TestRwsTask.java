package com.gennlife.rws.test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.QuitWebDriver;
import com.gennlife.rws.RwsTask;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestRwsTask {
	
	public static final String rwsUrl="http://10.0.2.162/uranus/project_index.html";
	private static final String loginName="testrws001";
	private static final String pwd="testrws001";

	@Test
	public void createRwsTask() throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = RwsTask.createRwsTask(driver, rwsUrl,loginName, pwd);
		System.out.println(value);
		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
	
	//优化过的多线程
	@Test
	public void CreateRwsCallable() throws Exception {
		ExecutorService threadPool =Executors.newFixedThreadPool(3); 
		// 执行任务
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
		} else {
			System.out.println("Error");
		} 
	}
	
	
	//测试单个线程，是否成功
	@Test
	public void createRwsTaskThreadReturnCallable2() throws Exception {
		String callableTest = new Callable<String>() {
			@Override
			public String call() throws Exception {
				// 登录并到add页面
				PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
				String value = RwsTask.createRwsTask(driver, rwsUrl,loginName, pwd);
				System.out.println(value);
				// 关闭driver
				QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
				return "success";
			}
		}.call();
		System.out.println(callableTest);
	}


	@Test
	public void createRwsTaskThreadReturnCallable() throws Exception {
		ExecutorService threadPool =Executors.newFixedThreadPool(3); 
		// 执行任务
		Future<String> futureTest1 = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
				//执行登录并改条件执行计算
				String value = RwsTask.createRwsTask(driver, rwsUrl,"testrws001", "testrws001");
				// 关闭driver
				QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
				if ("保存成功".equals(value)) {
					System.out.println("testrws001_success_"+new Date());
					return "success";
				}else {
					return "error";
				}
			}
		});
		Future<String> futureTest2 = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
				//执行登录并改条件执行计算
				String value = RwsTask.createRwsTask(driver, rwsUrl,"testrws002", "testrws002");
				// 关闭driver
				QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
				if ("保存成功".equals(value)) {
					System.out.println("testrws002_success_"+new Date());
					return "success";
				}else {
					return "error";
				}
			}
		});
		Future<String> futureTest3 = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
				//执行登录并改条件执行计算
				String value = RwsTask.createRwsTask(driver, rwsUrl,"testrws003", "testrws003");
				// 关闭driver
				QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
				if ("保存成功".equals(value)) {
					System.out.println("testrws003_success_"+new Date());
					return "success";
				}else {
					return "error";
				}
			}
		});
		
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
		} else {
        	System.out.println("Error");
		} 
	}
	
	
}
package com.gennlife.rws.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.mapper.RwsMapper;
import com.gennlife.crf.service.CreateRwsCalculateStabilityTaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestRwsCalculateStability {
	
	final static String loginURL = "http://10.0.2.162/uranus/UI/user/Login?param=%7B%22user%22:%223333%22,%22pwd%22:%22123456%22%7D&timeStamp=1534478147831";
	final static String rwsCalculateURL = "http://10.0.2.162/uranus/UI/rws/SaveOrSearchActive";
	final static String rwsResultURL = "http://10.0.2.162/uranus/UI/rws/FindTotalForImport";
	
	@Autowired
	private RwsMapper rwsMapper;
	
	@Autowired
	private CreateRwsCalculateStabilityTaskService createRwsCalculateStabilityTaskSerive;
	
	
	@Test
	public void RwsCalculateStability() throws Exception {
		
		//RwsBean rwsBean = RwsCalculateStabilityTask.rwsCal(loginURL, rwsCalculateURL, rwsResultURL);
		//rwsMapper.insertRws(rwsBean);
		
		/*
		for (Map.Entry<String, String> entry: map.entrySet()) {  
			System.out.println(entry.getKey()+"------"+entry.getValue());
		}
		*/
		for (int i = 0; i < 2; i++) {
			createRwsCalculateStabilityTaskSerive.createRwsCalculateStabilityTask();
		}
		
	}
	
	
}
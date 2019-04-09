package com.gennlife.autoplatform.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.autoplatform.bean.RwsBean;
import com.gennlife.autoplatform.mapper.RwsMapper;
import com.gennlife.autoplatform.service.CreateRwsCalculateStabilityTaskService;
import com.gennlife.rws.RwsCalculateStabilityTask;

@Service
public class CreateRwsCalculateStabilityTaskSeriveImpl implements CreateRwsCalculateStabilityTaskService{
	
	//private static final Logger logger = LoggerFactory.getLogger(CreateRwsCalculateStabilityTaskSeriveImpl.class);
	
	final static String loginURL = "http://10.0.2.162/uranus/UI/user/Login?param=%7B%22user%22:%223333%22,%22pwd%22:%22123456%22%7D&timeStamp=1534478147831";
	final static String rwsCalculateURL = "http://10.0.2.162/uranus/UI/rws/SaveOrSearchActive";
	final static String rwsResultURL = "http://10.0.2.162/uranus/UI/rws/FindTotalForImport";
	
	@Autowired
	private RwsMapper rwsMapper;

	@Override
	public void createRwsCalculateStabilityTask() throws Exception {
		RwsBean rwsBean = RwsCalculateStabilityTask.rwsCal(loginURL, rwsCalculateURL, rwsResultURL);
		if (rwsBean!=null) {
			rwsMapper.insertRws(rwsBean);
		}else {
			RwsBean rwsBeanError = new RwsBean(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),"接口请求失败","接口请求失败");
			rwsMapper.insertRws(rwsBeanError);
		}
	}
}

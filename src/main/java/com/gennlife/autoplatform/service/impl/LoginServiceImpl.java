package com.gennlife.autoplatform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.autoplatform.bean.SysFuncBean;
import com.gennlife.autoplatform.bean.SysOp;
import com.gennlife.autoplatform.mapper.SysFuncMapper;
import com.gennlife.autoplatform.mapper.SysOpMapper;
import com.gennlife.autoplatform.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SysOpMapper sysOpMapper;
	
	@Autowired
	private SysFuncMapper sysFuncMapper;
	
	@Override
	public SysOp getSysOpByUnameAndPwd(SysOp sysOp) throws Exception {
		return sysOpMapper.getSysOpByUnameAndPwd(sysOp);
	}

	@Override
	public List<SysFuncBean> getSysFuncByOpId(Map<String, Object> map)
			throws Exception {
		return sysFuncMapper.getSysFuncByOpId(map);
	}


}

package com.gennlife.crf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.crf.bean.SysFuncBean;
import com.gennlife.crf.bean.SysOp;
import com.gennlife.crf.mapper.SysFuncMapper;
import com.gennlife.crf.mapper.SysOpMapper;
import com.gennlife.crf.service.LoginService;

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

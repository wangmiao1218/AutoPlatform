package com.gennlife.autoplatform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.autoplatform.bean.SysOp;
import com.gennlife.autoplatform.mapper.SysOpMapper;
import com.gennlife.autoplatform.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private SysOpMapper sysOpMapper;

	@Override
	public List<SysOp> getSysOpList(Map<String, Object> map) throws Exception {
		return sysOpMapper.getSysOpList(map);
	}

	@Override
	public SysOp getSysOpById(Integer opId) throws Exception {
		return sysOpMapper.getSysOpById(opId);
	}
	
}

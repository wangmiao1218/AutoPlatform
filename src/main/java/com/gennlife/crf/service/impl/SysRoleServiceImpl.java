package com.gennlife.crf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.crf.bean.SysFuncBeanForRoleTree;
import com.gennlife.crf.bean.SysRole;
import com.gennlife.crf.mapper.SysRoleMapper;
import com.gennlife.crf.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public List<SysRole> geRoleList(Map<String, Object> map) {
		return sysRoleMapper.geRoleList(map);
	}

	@Override
	public List<SysFuncBeanForRoleTree> getRolFuncTree(Long roleId) {
		return sysRoleMapper.getRolFuncTree(roleId);
	}

}

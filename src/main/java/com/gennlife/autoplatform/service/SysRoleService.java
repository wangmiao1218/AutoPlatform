package com.gennlife.autoplatform.service;

import java.util.List;
import java.util.Map;

import com.gennlife.autoplatform.bean.SysFuncBeanForRoleTree;
import com.gennlife.autoplatform.bean.SysRole;

public interface SysRoleService {
	
	/** 
	* @Title: geRoleList 
	* @Description: 获取与角色集合（用PageHelper进行分页）
	* @param: @param map
	* @param: @return :
	* @return: List<SysRole>
	* @throws 
	*/
	public List<SysRole> geRoleList(Map<String, Object> map);
	
	
	/** 
	* @Title: getRolFuncTree 
	* @Description: 获取角色菜单模块需要显示的功能树
	* @param: @param roleId
	* @param: @return :
	* @return: List<SysFuncBeanForRoleTree>
	* @throws 
	*/
	public List<SysFuncBeanForRoleTree> getRolFuncTree(Long roleId);
	
}

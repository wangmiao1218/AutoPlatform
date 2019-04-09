package com.gennlife.crf.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gennlife.crf.bean.SysFuncBeanForRoleTree;
import com.gennlife.crf.bean.SysRole;
import com.gennlife.crf.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("sysRoleController")
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/** 
	* @Title: getRoleList 
	* @Description: 获取角色集合 (pageHelper)
	* @param: @param page
	* @param: @param limit
	* @param: @param sysRole
	* @param: @return 
	* @return: String:返回json
	* @throws 
	*/
	@ResponseBody
	@RequestMapping("getRoleList")
	public PageInfo<SysRole> getRoleList(SysRole sysRole,
			@RequestParam(defaultValue="0",required=false)Integer page,
			@RequestParam(defaultValue="10",required=false)Integer limit) throws Exception {
		Map<String, Object> map = new HashedMap<>();
		if(sysRole != null){
			map.put("roleName", sysRole.getRoleName());
		}
		//设置分页信息
		PageHelper.startPage(page, limit);
		// 获取集合
		List<SysRole> list = sysRoleService.geRoleList(map);
		// 用分页类包装集合
		PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
		
		// 转化为JSON数据并返回
		//return JSON.toJSONString(pageInfo);
		return pageInfo;
	}
	
	
	/** 
	* @Title: getRolFuncTree 
	* @Description: 获取角色菜单模块需要显示的功能树
	* @param: @param roleId
	* @param: @return
	* @return: String
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value="getRolFuncTree",method=RequestMethod.GET)
	public String getRolFuncTree(Long roleId){
	    List<SysFuncBeanForRoleTree> list =	sysRoleService.getRolFuncTree(roleId);
		return JSON.toJSONString(list);
	}
	
}

package com.gennlife.autoplatform.service;

import java.util.List;
import java.util.Map;

import com.gennlife.autoplatform.bean.SysOp;

/**
 * @Description: 获取用户列表
 * @author: wangmiao
 * @Date: 2017年7月14日 上午9:13:18 
 */
public interface UserService {
	
	/** 
	* @Title: getUserList 
	* @Description: 获取SysOp列表（PageHelper）
	* @param: Map<String, Object> map
	* @param: @throws Exception :
	* @return: List<SysOp>
	* @throws 
	*/
	public List<SysOp> getSysOpList(Map<String, Object> map) throws Exception;

	
	/** 
	* @Title: getSysOpById 
	* @Description: 通过opId获取用户
	* @param: @param opId
	* @param: @return
	* @param: @throws Exception :
	* @return: SysOp
	* @throws 
	*/
	public SysOp getSysOpById(Integer opId) throws Exception;
	
	
}

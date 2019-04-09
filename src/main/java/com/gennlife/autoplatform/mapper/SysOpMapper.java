package com.gennlife.autoplatform.mapper;

import java.util.List;
import java.util.Map;

import com.gennlife.autoplatform.bean.SysOp;

/**
 * @Description: 查询用户的接口
 * @author: wangmiao
 * @Date: 2017年6月14日 下午6:47:05 
 */
public interface SysOpMapper {
	
    /** 
    * @Title: getSysOpByUnameAndPwd 
    * @Description: 根据用户名密码查询
    * @param: SysOp sysOp
    * @param: @throws Exception :
    * @return: SysOp
    * @throws 
    */
    public SysOp getSysOpByUnameAndPwd(SysOp sysOp) throws Exception;
    
   
	/** 
	* @Title: getSysOpList 
	* @Description: 获取用户列表（PageHelper）
	* @param: @param map
	* @param: @return
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
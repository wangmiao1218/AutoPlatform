package com.gennlife.crf.service;

import java.util.List;
import java.util.Map;

import com.gennlife.crf.bean.SysFuncBean;
import com.gennlife.crf.bean.SysOp;

/**
 * @Description: 登录的接口
 * @author: wangmiao
 * @Date: 2017年7月14日 上午9:13:18 
 */
public interface LoginService {
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
    * @Title: getSysFuncByOpId 
    * @Description: 查询菜单列表数据
    * @param: @param map
    * @param: @return
    * @param: @throws Exception :
    * @return: List<SysFuncBean>
    * @throws 
    */
    public List<SysFuncBean> getSysFuncByOpId(Map<String, Object> map) throws Exception;
	

}

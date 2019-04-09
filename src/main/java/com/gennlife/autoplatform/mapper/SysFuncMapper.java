package com.gennlife.autoplatform.mapper;

import java.util.List;
import java.util.Map;

import com.gennlife.autoplatform.bean.SysFuncBean;


public interface SysFuncMapper {
	
    /** 
    * @Title: getSysFuncByOpId 
    * @Description: 根据OP_ID，查询菜单列表数据
    * @param: @param map
    * @param: @return
    * @param: @throws Exception :
    * @return: List<SysFuncBean>
    * @throws 
    */
    public List<SysFuncBean> getSysFuncByOpId(Map<String,Object> map) throws Exception;
	
}

package com.gennlife.autoplatform.mapper;

import com.gennlife.autoplatform.bean.RwsBean;
/**
 * @Description: rws保存接口
 * @author: wangmiao
 * @Date: 2018年8月20日 下午1:48:35 
 */
public interface RwsMapper {
	 
    /** 
    * @Title: insertRws 
    * @Description: 保存rws
    * @author: wangmiao
    * @Date: 2018年8月20日 下午1:48:51 
    * @param: @param rwsBean
    * @param: @return
    * @param: @throws Exception
    * @return: int
    * @throws 
    */
    public int insertRws(RwsBean rwsBean) throws Exception;
    

}
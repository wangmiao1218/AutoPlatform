package com.gennlife.crf.service;

import com.gennlife.crf.bean.RwsBean;


/**
 * @Description: rws稳定性计算结果
 * @author: wangmiao
 * @Date: 2018年8月20日 下午2:00:46 
 */
public interface RwsService {
	

	 /** 
	* @Title: insertRws 
	* @Description: 插入rws
	* @author: wangmiao
	* @Date: 2018年8月20日 下午2:01:17 
	* @param: @param rwsBean
	* @param: @return
	* @param: @throws Exception
	* @return: int
	* @throws 
	*/
	public int insertRws(RwsBean rwsBean) throws Exception;
     
    
}

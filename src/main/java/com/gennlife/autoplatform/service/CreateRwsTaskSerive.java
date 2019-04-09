package com.gennlife.autoplatform.service;

/**
 * @Description: 创建rws的定时任务
 * @author: wangmiao
 * @Date: 2018年3月14日 上午9:48:38 
 */
public interface CreateRwsTaskSerive {

	/** 
	* @Title: createRwsTask 
	* @Description: 自动创建rws的定时任务（定时执行自动计算，方便rws的稳定性测试）
	* @param: @return
	* @param: @throws Exception
	* @return: void
	* @throws 
	*/
	public void createRwsTask() throws Exception;
}

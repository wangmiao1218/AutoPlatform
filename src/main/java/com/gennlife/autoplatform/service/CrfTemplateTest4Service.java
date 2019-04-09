package com.gennlife.autoplatform.service;

import java.util.List;
import java.util.Map;

import com.gennlife.autoplatform.bean.CrfTemplateTest4;

/**
 * @Description: 获取CrfTemplateTest4相关方法
 * @author: wangmiao
 * @Date: 2017年6月16日 下午12:36:12
 */
public interface CrfTemplateTest4Service {

	/**
	 * @Title: getCrfTemplateTest4List
	 * @Description: 查询CrfTemplateTest4列表数据
	 * @param: Map<String, Object> map
	 * @param: @throws Exception :
	 * @return: List<CrfTemplateTest4>
	 * @throws
	 */
	public List<CrfTemplateTest4> getCrfTemplateTest4List(Map<String, Object> map) throws Exception;

	/**
	 * @Title: getCrfTemplateTest4ListByBaseName
	 * @Description: 通过baseName查询列表
	 * @param: @param baseName
	 * @param: @throws Exception :
	 * @return: List<CrfTemplateTest4>
	 * @throws
	 */
	public List<CrfTemplateTest4> getCrfTemplateTest4ListByBaseName(String baseName) throws Exception;

}

package com.gennlife.autoplatform.service;

import java.util.List;
import java.util.Map;

import com.gennlife.autoplatform.bean.CrfTemplateAnzhen;

/**
 * @Description: 获取CrfTemplateAnzhen相关方法
 * @author: wangmiao
 * @Date: 2017年7月28日 上午8:51:50 
 */
public interface CrfTemplateAnzhenService {

	/**
	 * @Title: getCrfTemplateAnzhenList
	 * @Description: 查询CrfTemplateAnzhen列表数据
	 * @param: Map<String, Object> map
	 * @param: @throws Exception :
	 * @return: List<CrfTemplateAnzhen>
	 * @throws
	 */
	public List<CrfTemplateAnzhen> getCrfTemplateAnzhenList(Map<String, Object> map) throws Exception;

	/**
	 * @Title: getCrfTemplateAnzhenListByBaseName
	 * @Description: 通过baseName查询列表
	 * @param: @param baseName
	 * @param: @throws Exception :
	 * @return: List<CrfTemplateAnzhen>
	 * @throws
	 */
	public List<CrfTemplateAnzhen> getCrfTemplateAnzhenListByBaseName(String baseName) throws Exception;

}

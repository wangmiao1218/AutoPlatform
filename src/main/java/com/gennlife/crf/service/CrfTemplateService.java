package com.gennlife.crf.service;

import java.util.List;
import java.util.Map;

import com.gennlife.crf.bean.CrfTemplate;


/**
 * @Description: 获取CrfTemplate相关方法
 * @author: wangmiao
 * @Date: 2017年6月16日 下午12:36:12 
 */
public interface CrfTemplateService {
	
	
	/** 
	* @Title: getCrfTemplateList 
	* @Description: 获取CrfTemplate列表（PageHelper）
	* @param: Map<String, Object> map
	* @param: @throws Exception :
	* @return: List<CrfTemplate>
	* @throws 
	*/
	public List<CrfTemplate> getCrfTemplateList(Map<String, Object> map) throws Exception;

	 /** 
     * @Title: getCrfTemplateListByVariableType 
     * @Description: 根据VariableType查询列表
     * @param: String variableType
     * @param: @throws Exception :
     * @return: List<CrfTemplate>
     * @throws 
     */
    public List<CrfTemplate> getCrfTemplateListByVariableType(String variableType) throws Exception;
    
    
    /** 
     * @Title: getCrfTemplateByEnglishName 
     * @Description: 通过englishName查询CrfTemplate
     * @param: String englishName
     * @param: @throws Exception :
     * @return: CrfTemplate
     * @throws 
     */
     public CrfTemplate getCrfTemplateByEnglishName(String englishName) throws Exception;
     
     /** 
      * @Title: updateCrfTemplate 
      * @Description: 更新CrfTemplate
      * @param: CrfTemplate crfTemplate
      * @param: @throws Exception :
      * @return: int
      * @throws 
      */
     public int updateCrfTemplate(CrfTemplate crfTemplate) throws Exception;
     
    
}

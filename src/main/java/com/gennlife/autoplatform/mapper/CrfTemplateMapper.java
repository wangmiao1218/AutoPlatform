package com.gennlife.autoplatform.mapper;

import java.util.List;
import java.util.Map;

import com.gennlife.autoplatform.bean.CrfTemplate;

/**
 * @Description: 查询CrfTemplate列表数据
 * @author: wangmiao
 * @Date: 2017年6月14日 下午6:47:05 
 */
public interface CrfTemplateMapper {
	
    /** 
    * @Title: getCrfTemplateList 
    * @Description: 查询CrfTemplate列表数据（PageHelper）
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
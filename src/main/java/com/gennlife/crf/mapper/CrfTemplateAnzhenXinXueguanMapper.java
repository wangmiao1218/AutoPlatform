package com.gennlife.crf.mapper;

import java.util.List;
import java.util.Map;
import com.gennlife.crf.bean.CrfTemplateAnzhenXinXueguan;

/**
 * @Description: CrfTemplateAnzhenXinXueguan相关接口
 * @author: wangmiao
 * @Date: 2017年9月19日 上午9:36:22 
 */
public interface CrfTemplateAnzhenXinXueguanMapper {
	
    /** 
    * @Title: getCrfTemplateAnzhenXinXueguanList 
    * @Description: 查询CrfTemplateAnzhenXinXueguan列表数据（PageHelper）
    * @param: Map<String, Object> map
    * @param: @throws Exception :
    * @return: List<CrfTemplateAnzhenXinXueguan>
    * @throws 
    */
    public List<CrfTemplateAnzhenXinXueguan> getCrfTemplateAnzhenXinXueguanList(Map<String, Object> map) throws Exception;
    
    /** 
     * @Title: getCrfTemplateAnzhenXinXueguanListByVariableType 
     * @Description: 根据VariableType查询列表
     * @param: String variableType
     * @param: @throws Exception :
     * @return: List<CrfTemplateAnzhenXinXueguan>
     * @throws 
     */
    public List<CrfTemplateAnzhenXinXueguan> getCrfTemplateAnzhenXinXueguanListByVariableType(String variableType) throws Exception;
    
    
    /** 
    * @Title: getCrfTemplateAnzhenXinXueguanByEnglishName 
    * @Description: 通过englishName查询CrfTemplateAnzhenXinXueguan
    * @param: String englishName
    * @param: @throws Exception :
    * @return: CrfTemplateAnzhenXinXueguan
    * @throws 
    */
    public CrfTemplateAnzhenXinXueguan getCrfTemplateAnzhenXinXueguanByEnglishName(String englishName) throws Exception;
    
    
    /** 
     * @Title: getCrfTemplateAnzhenXinXueguanListByBaseName 
     * @Description: 通过baseName查询列表
     * @param: @param baseName
     * @param: @throws Exception :
     * @return: List<CrfTemplateAnzhenXinXueguan>
     * @throws 
     */
     public List<CrfTemplateAnzhenXinXueguan> getCrfTemplateAnzhenXinXueguanListByBaseName(String baseName) throws Exception;
    
     
     /** 
      * @Title: getVerifyLinkageFieldResultList 
      * @Description: 获取linkageResult验证结果列表
      * @param: @throws Exception :
      * @return: List<CrfTemplateAnzhenXinXueguan>
      * @throws 
      */
     public List<CrfTemplateAnzhenXinXueguan> getVerifyLinkageFieldResultList() throws Exception;
     
     
     /** 
    * @Title: getVerifyLinkageFieldResultListByBaseName 
    * @Description: 根据baseName，获取linkageResult验证结果列表
    * @param: @param baseName
    * @param: @return
    * @param: @throws Exception :
    * @return: List<CrfTemplateAnzhenXinXueguan>
    * @throws 
    */
    public List<CrfTemplateAnzhenXinXueguan> getVerifyLinkageFieldResultListByBaseName(String baseName) throws Exception;
     
     
    /** 
     * @Title: updateCrfTemplateAnzhenXinXueguan 
     * @Description: 更新CrfTemplateAnzhenXinXueguan
     * @param: CrfTemplateAnzhenXinXueguan CrfTemplateAnzhenXinXueguan
     * @param: @throws Exception :
     * @return: int
     * @throws 
     */
    public int updateCrfTemplateAnzhenXinXueguan(CrfTemplateAnzhenXinXueguan CrfTemplateAnzhenXinXueguan) throws Exception;
    
    /** 
     * @Title: updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName 
     * @Description: 根据BaseName，清空linkageResult结果
     * @param: String baseName
     * @param: @throws Exception :
     * @return: int
     * @throws 
     */
    public int updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName(String baseName) throws Exception;
    

}
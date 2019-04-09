package com.gennlife.crf.mapper;

import java.util.List;
import java.util.Map;
import com.gennlife.crf.bean.CrfTemplateAnzhen;

/**
 * @Description: 查询CrfTemplateAnzhen列表数据
 * @author: wangmiao
 * @Date: 2017年7月28日 上午8:48:12 
 */
public interface CrfTemplateAnzhenMapper {
	
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
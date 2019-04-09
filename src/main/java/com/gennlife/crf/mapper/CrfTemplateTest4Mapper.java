package com.gennlife.crf.mapper;

import java.util.List;
import java.util.Map;

import com.gennlife.crf.bean.CrfTemplateTest4;

/**
 * @Description: 查询CrfTemplateTest4列表数据
 * @author: wangmiao
 * @Date: 2017年6月14日 下午6:47:05 
 */
public interface CrfTemplateTest4Mapper {
	
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
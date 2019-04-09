package com.gennlife.crf.service;

import java.util.List;

import com.gennlife.crf.bean.CrfTemplateStructure;


/**
 * @Description: CrfTemplateStructureService层相关接口
 * @author: wangmiao
 * @Date: 2017年9月25日 下午4:10:04 
 */
public interface CrfTemplateStructureService{
	 
    /** 
     * @Title: getCrfTemplateStructureListByHospitalDepartment 
     * @Description: 根据HospitalDepartment查询列表
     * @param: String hospitalDepartment
     * @param: @throws Exception :
     * @return: List<CrfTemplateStructure>
     * @throws 
     */
    public List<CrfTemplateStructure> getCrfTemplateStructureListByHospitalDepartment(String hospitalDepartment) throws Exception;
    
    /** 
     * @Title: getCrfTemplateStructureByBaseName 
     * @Description: 通过baseName查询CrfTemplateStructure
     * @param: String baseName
     * @param: @throws Exception :
     * @return: CrfTemplateStructure
     * @throws 
     */
     public CrfTemplateStructure getCrfTemplateStructureByBaseName(String baseName) throws Exception;
     
    
}
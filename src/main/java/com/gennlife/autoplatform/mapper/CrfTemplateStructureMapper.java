package com.gennlife.autoplatform.mapper;

import java.util.List;

import com.gennlife.autoplatform.bean.CrfTemplateAnzhenXinXueguan;
import com.gennlife.autoplatform.bean.CrfTemplateStructure;

/**
 * @Description: CrfTemplateStructureMapper相关接口
 * @author: wangmiao
 * @Date: 2017年9月25日 下午4:04:27 
 */
public interface CrfTemplateStructureMapper {
	
    
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
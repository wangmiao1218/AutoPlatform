package com.gennlife.crf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.crf.bean.CrfTemplateStructure;
import com.gennlife.crf.mapper.CrfTemplateStructureMapper;
import com.gennlife.crf.service.CrfTemplateStructureService;

/**
 * @Description: 获取CrfTemplateAnzhenXinXueguan的impl
 * @author: wangmiao
 * @Date: 2017年9月19日 上午9:36:22 
 */
@Service
public class CrfTemplateStructureServiceImpl implements CrfTemplateStructureService {

	@Autowired
	private CrfTemplateStructureMapper crfTemplateStructureMapper;


	@Override
	public List<CrfTemplateStructure> getCrfTemplateStructureListByHospitalDepartment(
			String hospitalDepartment) throws Exception {
		return crfTemplateStructureMapper.getCrfTemplateStructureListByHospitalDepartment(hospitalDepartment);
	}

	@Override
	public CrfTemplateStructure getCrfTemplateStructureByBaseName(
			String baseName) throws Exception {
		return crfTemplateStructureMapper.getCrfTemplateStructureByBaseName(baseName);
	}

	
}

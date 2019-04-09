package com.gennlife.autoplatform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.autoplatform.bean.CrfTemplate;
import com.gennlife.autoplatform.mapper.CrfTemplateMapper;
import com.gennlife.autoplatform.service.CrfTemplateService;

/**
 * @Description: 获取CrfTemplate的impl
 * @author: wangmiao
 * @Date: 2017年6月16日 下午1:24:33 
 */
@Service
public class CrfTemplateServiceImpl implements CrfTemplateService {

	@Autowired
	private CrfTemplateMapper crfTemplateMapper;
	
	@Override
	public List<CrfTemplate> getCrfTemplateList(Map<String, Object> map)
			throws Exception {
		return crfTemplateMapper.getCrfTemplateList(map);
	}

	@Override
	public List<CrfTemplate> getCrfTemplateListByVariableType(
			String variableType) throws Exception {
		return crfTemplateMapper.getCrfTemplateListByVariableType(variableType);
	}

	@Override
	public CrfTemplate getCrfTemplateByEnglishName(String englishName)
			throws Exception {
		return crfTemplateMapper.getCrfTemplateByEnglishName(englishName);
	}

	@Override
	public int updateCrfTemplate(CrfTemplate crfTemplate) throws Exception {
		return crfTemplateMapper.updateCrfTemplate(crfTemplate);
	}
	
}

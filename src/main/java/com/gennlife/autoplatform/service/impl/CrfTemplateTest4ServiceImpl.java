package com.gennlife.autoplatform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.autoplatform.bean.CrfTemplateTest4;
import com.gennlife.autoplatform.mapper.CrfTemplateTest4Mapper;
import com.gennlife.autoplatform.service.CrfTemplateTest4Service;

/**
 * @Description: 获取CrfTemplateTest4的impl
 * @author: wangmiao
 * @Date: 2017年6月16日 下午1:24:33 
 */
@Service
public class CrfTemplateTest4ServiceImpl implements CrfTemplateTest4Service {

	@Autowired
	private CrfTemplateTest4Mapper crfTemplateTest4Mapper;

	@Override
	public List<CrfTemplateTest4> getCrfTemplateTest4List(
			Map<String, Object> map) throws Exception {
		return crfTemplateTest4Mapper.getCrfTemplateTest4List(map);
	}

	@Override
	public List<CrfTemplateTest4> getCrfTemplateTest4ListByBaseName(
			String baseName) throws Exception {
		return crfTemplateTest4Mapper.getCrfTemplateTest4ListByBaseName(baseName);
	}
	

}

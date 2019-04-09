package com.gennlife.crf.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gennlife.crf.bean.CrfTemplateAnzhen;
import com.gennlife.crf.mapper.CrfTemplateAnzhenMapper;
import com.gennlife.crf.service.CrfTemplateAnzhenService;

/**
 * @Description: 获取CrfTemplateAnzhen的impl
 * @author: wangmiao
 * @Date: 2017年7月28日 上午8:53:04 
 */
@Service
public class CrfTemplateAnzhenServiceImpl implements CrfTemplateAnzhenService {

	@Autowired
	private CrfTemplateAnzhenMapper crfTemplateAnzhenMapper;

	@Override
	public List<CrfTemplateAnzhen> getCrfTemplateAnzhenList(Map<String, Object> map) throws Exception {
		return crfTemplateAnzhenMapper.getCrfTemplateAnzhenList(map);
	}

	@Override
	public List<CrfTemplateAnzhen> getCrfTemplateAnzhenListByBaseName(String baseName) throws Exception {
		return crfTemplateAnzhenMapper.getCrfTemplateAnzhenListByBaseName(baseName);
	}

}

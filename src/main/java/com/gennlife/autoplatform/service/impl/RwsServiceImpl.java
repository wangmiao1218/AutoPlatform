package com.gennlife.autoplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gennlife.autoplatform.bean.RwsBean;
import com.gennlife.autoplatform.mapper.RwsMapper;
import com.gennlife.autoplatform.service.RwsService;

/**
 * @Description: 获取CrfTemplate的impl
 * @author: wangmiao
 * @Date: 2017年6月16日 下午1:24:33 
 */
@Service
public class RwsServiceImpl implements RwsService {

	@Autowired
	private RwsMapper rwsMapper;
	
	@Override
	public int insertRws(RwsBean rwsBean) throws Exception {
		return rwsMapper.insertRws(rwsBean);
	}
	
}

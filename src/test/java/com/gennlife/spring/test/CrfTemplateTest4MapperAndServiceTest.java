package com.gennlife.spring.test;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.bean.CrfTemplateTest4;
import com.gennlife.crf.mapper.CrfTemplateTest4Mapper;
import com.gennlife.crf.service.CrfTemplateTest4Service;

/**
 * @Description: 测试CrfTemplateTest4
 * @author: wangmiao
 * @Date: 2017年7月12日 下午2:01:39 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class CrfTemplateTest4MapperAndServiceTest {
	
	@Autowired
	private CrfTemplateTest4Mapper crfTemplateTest4Mapper;
	
	@Autowired
	private CrfTemplateTest4Service crfTemplateTest4Service;
	
	
	@Test
	public void testGetCrfPatientInfoList() throws Exception{
		List<CrfTemplateTest4> list = crfTemplateTest4Service.getCrfTemplateTest4List(new HashedMap());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testGetCrfPatientInfoListByBaseName() throws Exception{
		String baseName ="一般信息";
		List<CrfTemplateTest4> list = crfTemplateTest4Service.getCrfTemplateTest4ListByBaseName(baseName);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}

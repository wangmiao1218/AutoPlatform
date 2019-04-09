package com.gennlife.spring.test;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.autoplatform.bean.CrfTemplate;
import com.gennlife.autoplatform.mapper.CrfTemplateMapper;
import com.gennlife.autoplatform.service.CrfTemplateService;

/**
 * @Description: 测试数据库连接
 * @author: wangmiao
 * @Date: 2017年6月14日 下午6:06:24 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class CrfTemplateMapperAndServiceTest {
	
	@Autowired
	private CrfTemplateMapper crfTemplateMapper;
	
	@Autowired
	private CrfTemplateService crfTemplateServic;
	
	
	/*@Test
	public void testgetCrfTemplateByEnglishName() throws Exception{
		CrfTemplate crfTemplate = crfTemplateServic.getCrfTemplateByEnglishName("GENDER");
		System.out.println(crfTemplate.getId());
	}*/
	
	
	@Test
	public void testCrfTemplateMapper() throws Exception{
		Map<String, Object> map = new HashedMap();
		CrfTemplate crfTemplate = new CrfTemplate();
		crfTemplate.setId(5);
		map.put("CrfTemplate", crfTemplate);
		
		crfTemplateMapper.getCrfTemplateList(map);
		
	}

}

package com.gennlife.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.bean.SysOp;
import com.gennlife.crf.mapper.SysOpMapper;
import com.gennlife.crf.service.LoginService;

/**
 * @Description: 测试数据库连接
 * @author: wangmiao
 * @Date: 2017年6月14日 下午6:06:24 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class LoginMapperAndServiceTest {
	
	@Autowired
	private SysOpMapper sysOpMapper;
	
	@Autowired
	private LoginService loginService;
	
	/*
	@Test
	public void testLoginService() throws Exception{
		SysOp sysOp = new SysOp("admin", "admin");
		SysOp op = loginService.getSysOpByUnameAndPwd(sysOp);
		System.out.println(op.getLoginName());
		System.out.println(op.getLoginPasswd());
	}
	
	@Test
	public void testSysOpMapper() throws Exception{
		SysOp sysOp = new SysOp("admin", "admin");
		
		SysOp op = sysOpMapper.getSysOpByUnameAndPwd(sysOp);
		System.out.println(op.getLoginName());
		System.out.println(op.getLoginPasswd());
		
	}*/

}

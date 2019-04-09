package com.gennlife.spring.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class saltTest {
	
	//测试：加密后的密码，然后该数据库字段
	@Test
	public void saltTest() {
		String algorithmName = "MD5";
		String source = "123456";
		ByteSource salt = ByteSource.Util.bytes("admin");
		int hashIterations = 1024;

		Object result = new SimpleHash(algorithmName, source, salt,hashIterations);
		System.out.println(result);
	}

}

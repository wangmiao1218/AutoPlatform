package com.gennlife.autoplatform.realm.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestSalt {
	
	//测试：加密后的密码，然后改数据库字段
	@Test
	public void salt() {
		String algorithmName = "MD5";
		String source = "123456";//038bdaf98f2037b31f1e75b5b4c9b26e
		ByteSource salt = ByteSource.Util.bytes("admin");
		int hashIterations = 1024;

		Object result = new SimpleHash(algorithmName, source, salt,hashIterations);
		System.out.println(result);
	}

}

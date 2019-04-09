package com.gennlife.autoplatform.utils.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.autoplatform.utils.RandomValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestRandomString {
	
	@Test
	public void UUIDString(){
		String string = RandomValue.UUIDString();
		System.out.println(string);
	}
	@Test
	public void randomRangeInt(){
		String string = RandomValue.randomRangeInt(10,20);
		System.out.println(string);
	}
	
	@Test
	public void randomDateyyyyMMdd() throws Exception{
		String string = RandomValue.randomDateyyyyMMdd();
		System.out.println(string);
	}
	
	@Test
	public void randomDateyyyyMMddHHmmss() throws Exception{
		String string = RandomValue.randomDateyyyyMMddHHmmss();
		System.out.println(string);
	}
	
	
	
}
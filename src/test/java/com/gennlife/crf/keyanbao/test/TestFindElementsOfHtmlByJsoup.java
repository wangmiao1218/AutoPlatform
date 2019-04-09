package com.gennlife.crf.keyanbao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.gennlife.crf.keyanbao.FindElementsOfHtmlByJsoup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestFindElementsOfHtmlByJsoup {
	
	@Test
	public void findElements() throws Exception {
		
		FindElementsOfHtmlByJsoup.findElements();
	}
	
	
}
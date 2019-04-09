package com.gennlife.crf.anzhen.xinxueguan.excel.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gennlife.crf.bean.CrfTemplateAnzhenXinXueguan;
import com.gennlife.crf.mapper.CrfTemplateAnzhenXinXueguanMapper;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.ListAndStringUtils;
import com.gennlife.crf.utils.LoginCrfOfAnzhen;
import com.gennlife.crf.utils.QuitWebDriver;
import com.gennlife.crf.utils.SeleniumUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestAnzhenXinxueguan {

	@Autowired
	private CrfTemplateAnzhenXinXueguanMapper crfTemplateAnzhenXinXueguanMapper;

	@Test
	public void test06() throws Exception {
		List<CrfTemplateAnzhenXinXueguan> list = crfTemplateAnzhenXinXueguanMapper
						.getVerifyLinkageFieldResultListByBaseName("就诊－超声检查");
		
		
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			
		}
		System.out.println("======================");
	}
	
	
	
	
	@Test
	public void test05() throws Exception {
		List<CrfTemplateAnzhenXinXueguan> list = crfTemplateAnzhenXinXueguanMapper
						.getVerifyLinkageFieldResultList();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			System.out.println("======================");
		}
	}	
	
	
	@Test
	public void test04() throws Exception {
		// 获取所有就诊－住院与诊断list
		List<CrfTemplateAnzhenXinXueguan> listAll = crfTemplateAnzhenXinXueguanMapper
				.getCrfTemplateAnzhenXinXueguanListByBaseName("就诊－超声检查");
		System.out.println(listAll.size());
		
		//新建list，便于接受递归返回的结果，每次循环则变化
		List<CrfTemplateAnzhenXinXueguan> listcrf=new ArrayList<CrfTemplateAnzhenXinXueguan>();
		//for (int i = 0; i < listAll.size(); i++) {
		List<CrfTemplateAnzhenXinXueguan> returnlist = ListAndStringUtils.
				searchCrfListReturnAllLinkageFieldsList(listAll, listAll.get(29),listcrf);
		
		System.out.println(listAll.get(29));
		
		System.out.println(returnlist.size());
		for (int i = 0; i < returnlist.size(); i++) {
			System.out.println(returnlist.get(i));
		}
		
		System.out.println("======================");
		
		//}
		
	}	
	
	
	@Test
	public void test03() throws Exception {
		// 获取所有就诊－住院与诊断list
		int a = crfTemplateAnzhenXinXueguanMapper.
				updateCrfTemplateAnzhenXinXueguanListLinkageResultByBaseName("就诊－超声检查");
		System.out.println(a);
	}
	
	
	
	@Test
	public void test02() throws Exception {
		// 获取所有就诊－住院与诊断list
		List<CrfTemplateAnzhenXinXueguan> list = crfTemplateAnzhenXinXueguanMapper
				.getCrfTemplateAnzhenXinXueguanListByBaseName("个人病史");
		
		// 循环list
		for (int i = 0; i < list.size(); i++) {
			List<CrfTemplateAnzhenXinXueguan> listcrf=new ArrayList<CrfTemplateAnzhenXinXueguan>();
			//System.out.println(list.get(i));
			//test001(list, list.get(i));
			List<CrfTemplateAnzhenXinXueguan> list2 = ListAndStringUtils.searchCrfListReturnAllLinkageFieldsList(list, list.get(i),listcrf);
			for (int j = 0; j < list2.size(); j++) {
				System.out.println(list2.get(j));
			}
			System.out.println("=================");
		}
	}

	
	@Test
	public void test01() {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver
				.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen
				.loginAndToAddOfMenZhenAndBasicInfoByPhantomJSDriver(driver);
		System.out.println(value);

		driver.findElementById("crf-data-tree_3_span").click();

		boolean b = SeleniumUtils.isElementPresent(driver,
				"u-crf-HYPERTENSION_HISTORY_OF_HYPERTENSION");
		System.out.println(b);

		// 联动字段
		if (!b) {
			new Select(
					driver.findElementById("u-crf-HYPERTENSION_HAS_HYPERTENSION"))
					.selectByValue("有");
			driver.findElementById("u-crf-HYPERTENSION_HISTORY_OF_HYPERTENSION");
			System.out.println("存在");
		}

		new Select(
				driver.findElementById("u-crf-HYPERTENSION_HAS_HYPERTENSION"))
				.selectByIndex(0);

		boolean b2 = SeleniumUtils.isElementPresent(driver,
				"u-crf-HYPERTENSION_REGULAR_MEDICATION_HISTORY");
		System.out.println(b2);

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}

}
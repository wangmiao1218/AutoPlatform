package com.gennlife.autoplatform.anzhen.xinxueguan.excel;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.CreateWebDriver;
import com.gennlife.autoplatform.utils.ExcelUtils;
import com.gennlife.autoplatform.utils.ListAndStringUtils;
import com.gennlife.autoplatform.utils.LoginCrfOfAnzhen;
import com.gennlife.autoplatform.utils.QuitWebDriver;
import com.gennlife.autoplatform.utils.SeleniumUtils;

/**
 * @Description: 验证页面联动字段(注意：联动只有三层逻辑)
 * @author: wangmiao
 * @Date: 2017年9月20日 下午5:57:54 
 */
public class CrfTemplateVerifyLinkageField{

	/** 
	* @Title: verifyLinkageField 
	* @Description: 验证联动字段
	* @param: @param excelmb
	* @param: @param excel
	* @return: void
	* @throws 
	*/
	public static void verifyLinkageField(Excel excelmb,Excel excel) throws Exception {
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "中文名称");
		Integer idXpathCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "idXpath");
		//获取中文名称一列
		List<String> list = ExcelUtils.readExcelOfList(excelmb, chNameCellNum);
		System.out.println(list.size());
		//除去表头开始遍历
		for (int i = 1; i < list.size(); i++) {
			//设置excel的sheet值
			excel.setSheetName(list.get(i));
			//判断excel是否存在该sheet
			if (ExcelUtils.checkSheetOfExcelExist(excel)) {
				//获取模板中对应的idXpath，传入下方
				Integer rowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excelmb, chNameCellNum, list.get(i));
				String idXpath = ExcelUtils.readContent(excelmb, rowNum, idXpathCellNum);
				if (idXpath!=null && !"".equals(idXpath) && !" ".equals(idXpath)) {
					//存在则进行去验证
					verifyLinkageOfWebDriver(excel,idXpath);
				}
			}
			
			//如果对应的sheet不存在则继续
			if (!ExcelUtils.checkSheetOfExcelExist(excel)) {
				continue;
			}
		}
	}
	
	
	/** 
	* @Title: verifyLinkageOfWebDriver 
	* @Description: 验证联动字段，去页面创建webDriver，相当于每个页面创建一个连接
	* @param: @param excel
	* @param: @param idXpath
	* @param: @throws Exception :
	* @return: void
	* @throws 
	*/
	public static void verifyLinkageOfWebDriver(Excel excel,String idXpath) throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		//使用安贞高血压进行测试
		String value = LoginCrfOfAnzhen.loginAndToAddOfXinxueguanByPhantomJSDriver(driver);

		if ("添加页面".equals(value)) {
			//随访需要
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			Thread.sleep(1500);
			
			//测试
			driver.findElementById(idXpath).click();
			Thread.sleep(2000);
			//验证
			verifyLinkageOfExcel(driver, excel);		
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
		
		

	/** 
	* @Title: verifyLinkageOfExcel 
	* @Description: 验证联动字段，去excel遍历所有字段
	* @param: @param driver
	* @param: @param excel
	* @param: @throws Exception :
	* @return: void
	* @throws 
	*/
	public static void verifyLinkageOfExcel(PhantomJSDriver driver,Excel excel) throws Exception {
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		Integer idXpathCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "idXpath");
		Integer displayMainKeyCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "__displayMainKey");
		Integer displayMainValueCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "__displayMainValue");
		Integer linkageResultCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "linkageResult");
		
		//防止没有联动字段的情况
		if (displayMainKeyCellNum!=null && displayMainValueCellNum!=null && linkageResultCellNum!=null ) {

			//获取displayMainKeyCellNum列
			Map<Integer, String> displayMainKeyMap = ExcelUtils.readExcelOfListReturnMap(excel, displayMainKeyCellNum);
			for (Entry<Integer, String> entry: displayMainKeyMap.entrySet()) {
				//遍历
				Integer fieldRowNum = entry.getKey();
				String displayMainKey = entry.getValue();
				//当前存在联动配置的idXpath
				String idXpath = ExcelUtils.readContent(excel, fieldRowNum, idXpathCellNum);
				//当前存在联动配置的displayMainValue
				String displayMainValue = ExcelUtils.readContent(excel, fieldRowNum, displayMainValueCellNum);
	
				//获取联动的英文字段
				String linkageFieldEnglishName1 = ListAndStringUtils.displayMainKeyToEnglishName(displayMainKey);
				//获取联动字段所在的行
				Integer linkageFieldRowNum1 = ExcelUtils.searchKeyWordOfListReturnRowNum(excel, enNameCellNum, linkageFieldEnglishName1);
				//联动字段配置的idXpath
				String linkageFieldIdXpath1 = ExcelUtils.readContent(excel, linkageFieldRowNum1, idXpathCellNum);
				//联动字段配置的idXpath
				String linkageFieldDisplayMainValue1 = ExcelUtils.readContent(excel, linkageFieldRowNum1, displayMainValueCellNum);
	
				//获取联动字段的DisplayMainKey
				String linkageFieldDisplayMainKey1 =null;
				if (linkageFieldRowNum1 != null) {
					//判断是否为空，为空则为两层结构，不为空为多层结构
					linkageFieldDisplayMainKey1 = ExcelUtils.readContent(excel, linkageFieldRowNum1, displayMainKeyCellNum);
				}else {
					ExcelUtils.writeAndSaveContent(excel, "没有联动字段的行号", fieldRowNum, linkageResultCellNum);
					continue;
				}
				
				//===================二层逻辑开始============================
				//两层结构
				if (linkageFieldDisplayMainKey1==null || "".equals(linkageFieldDisplayMainKey1) || " ".equals(linkageFieldDisplayMainKey1)) {
					//不为空，去页面查看是否存在
					//存在,则结果直接为no
					if (SeleniumUtils.isElementPresent(driver,idXpath)) {
						ExcelUtils.writeAndSaveContent(excel, "no", fieldRowNum, linkageResultCellNum);
					}else {//不存在 ，则开启联动的字段
						//判断页面中是否有所联动的值
						//页面中设置联动字段为对应选项值
						Boolean b = SeleniumUtils.isSelectByValuePresent(driver,linkageFieldIdXpath1, 
								ListAndStringUtils.displayMainValueToSelectByValue(displayMainValue));
						if (b) {
							new Select(driver.findElementById(linkageFieldIdXpath1)).
								selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(displayMainValue));
							//检查是否存在字段
							//存在,则结果直接为pass
							if (SeleniumUtils.isElementPresent(driver,idXpath)) {
								ExcelUtils.writeAndSaveContent(excel, "pass", fieldRowNum, linkageResultCellNum);
							}else {//不存在,则结果直接no
								ExcelUtils.writeAndSaveContent(excel, "no", fieldRowNum, linkageResultCellNum);
							}
							//判断之后跟选项归位，以防影响后面元素判断
							new Select(driver.findElementById(linkageFieldIdXpath1)).selectByIndex(0);
						}else {
							ExcelUtils.writeAndSaveContent(excel, "no", fieldRowNum, linkageResultCellNum);
						}
					}
					//继续循环，不进入下面操作
					continue;
				}
				//===================二层逻辑结束============================
				
				//===================三层逻辑开始============================
				else if (linkageFieldDisplayMainKey1!=null && !"".equals(linkageFieldDisplayMainKey1) && !" ".equals(linkageFieldDisplayMainKey1)) {
					//已知一级联动的：linkageFieldDisplayMainKey1
					
					//继续获取第二层的联动
					//获取联动的英文字段
					String linkageFieldEnglishName2 = ListAndStringUtils.displayMainKeyToEnglishName(linkageFieldDisplayMainKey1);
					//获取联动字段所在的行
					Integer linkageFieldRowNum2 = ExcelUtils.searchKeyWordOfListReturnRowNum(excel, enNameCellNum, linkageFieldEnglishName2);
					//联动字段配置的idXpath
					String linkageFieldIdXpath2 = ExcelUtils.readContent(excel, linkageFieldRowNum2, idXpathCellNum);
					//联动字段配置的idXpath
					String linkageFieldDisplayMainValue2 = ExcelUtils.readContent(excel, linkageFieldRowNum2, displayMainValueCellNum);
					
					//获取联动字段的DisplayMainKey
					String linkageFieldDisplayMainKey2 =null;
					if (linkageFieldRowNum2 != null) {
						//判断是否为空，为空则为两层结构，不为空为多层结构
						linkageFieldDisplayMainKey2 = ExcelUtils.readContent(excel, linkageFieldRowNum2, displayMainKeyCellNum);
					}else {
						ExcelUtils.writeAndSaveContent(excel, "没有联动字段的行号", fieldRowNum, linkageResultCellNum);
						continue;
					}
					
					//三层结构
					if (linkageFieldDisplayMainKey2==null || "".equals(linkageFieldDisplayMainKey2) || " ".equals(linkageFieldDisplayMainKey2)) {
						//不为空，去页面查看是否存在
						//存在,则结果直接为no
						if (SeleniumUtils.isElementPresent(driver,idXpath)) {
							ExcelUtils.writeAndSaveContent(excel, "no", fieldRowNum, linkageResultCellNum);
						}else {//不存在 ，则开启联动的字段
							//即：开启linkageFieldRowNum2，再开启linkageFieldRowNum1（关闭则逆序）
							//1.先判断linkageFieldRowNum2是否能选择
							//页面中设置联动字段为对应选项值,不能则直接no
							Boolean bb = SeleniumUtils.isSelectByValuePresent(driver,linkageFieldIdXpath2, 
									ListAndStringUtils.displayMainValueToSelectByValue(linkageFieldDisplayMainValue1));
							if (bb) {
								//2.开启linkageFieldRowNum2
								new Select(driver.findElementById(linkageFieldIdXpath2)).
										selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(linkageFieldDisplayMainValue1));
								//3.检查linkageFieldRowNum1是否存在
								if (SeleniumUtils.isElementPresent(driver,linkageFieldIdXpath1)) {
									//4.存在，则判断linkageFieldRowNum1能否设置联动字段
									//页面中设置联动字段为对应选项值
									Boolean bbb = SeleniumUtils.isSelectByValuePresent(driver,linkageFieldIdXpath1, 
											ListAndStringUtils.displayMainValueToSelectByValue(displayMainValue));
									if (bbb) {
										new Select(driver.findElementById(linkageFieldIdXpath1)).
												selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(displayMainValue));
										//5.检查是否存在字段
										//存在,则结果直接为pass
										if (SeleniumUtils.isElementPresent(driver,idXpath)) {
											ExcelUtils.writeAndSaveContent(excel, "pass", fieldRowNum, linkageResultCellNum);
										}else {//不存在,则结果直接no
											ExcelUtils.writeAndSaveContent(excel, "no", fieldRowNum, linkageResultCellNum);
										}
										//6.判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭linkageFieldIdXpath1，再关闭linkageFieldIdXpath2)
										new Select(driver.findElementById(linkageFieldIdXpath1)).selectByIndex(0);
									}else {
										ExcelUtils.writeAndSaveContent(excel, "no", fieldRowNum, linkageResultCellNum);
									}
								}else {//不存在,则结果直接no
									ExcelUtils.writeAndSaveContent(excel, "no", fieldRowNum, linkageResultCellNum);
								}
								
								//判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭linkageFieldIdXpath1，再关闭linkageFieldIdXpath2)
								new Select(driver.findElementById(linkageFieldIdXpath2)).selectByIndex(0);
							}else {
								ExcelUtils.writeAndSaveContent(excel, "no", fieldRowNum, linkageResultCellNum);
							}
						}
						//继续循环
						continue;
					}else {
						ExcelUtils.writeAndSaveContent(excel, "超过三层，人工验证", fieldRowNum, linkageResultCellNum);
					}
					/*
					//三层中嵌套四层逻辑
					//===================四层逻辑开始============================
					else if (linkageFieldDisplayMainKey2!=null && !"".equals(linkageFieldDisplayMainKey2) && !" ".equals(linkageFieldDisplayMainKey2)) {
						//已知二级联动的：linkageFieldDisplayMainKey2
						
						//继续获取第三层的联动
						//获取联动的英文字段
						String linkageFieldEnglishName3 = ListAndStringUtils.displayMainKeyToEnglishName(linkageFieldDisplayMainKey2);
						//获取联动字段所在的行
						Integer linkageFieldRowNum3 = ExcelUtils.searchKeyWordOfListReturnRowNum(excel,enNameCellNum,linkageFieldEnglishName3);
						//联动字段配置的idXpath
						String linkageFieldIdXpath3 = ExcelUtils.readContent(excel, linkageFieldRowNum3, idXpathCellNum);
		
						//获取联动字段的DisplayMainKey
						String linkageFieldDisplayMainKey3 =null;
						if (linkageFieldRowNum3 != null) {
							//判断是否为空，为空则为两层结构，不为空为多层结构
							linkageFieldDisplayMainKey3 = ExcelUtils.readContent(excel, linkageFieldRowNum3, displayMainKeyCellNum);
						}else {
							ExcelUtils.writeAndSaveContent(excel, "没有联动字段的行号", fieldRowNum, linkageResultCellNum);
							//continue;
						}
						//四层结构
						if (linkageFieldDisplayMainKey3==null || "".equals(linkageFieldDisplayMainKey3) || " ".equals(linkageFieldDisplayMainKey3)) {
							//不为空，去页面查看是否存在
							//存在,则结果直接为no
							if (SeleniumUtils.isElementPresent(driver,idXpath)) {
								ExcelUtils.writeAndSaveContent(excel, "no1", fieldRowNum, linkageResultCellNum);
							}else {//不存在 ，则开启联动的字段
								//即：开启linkageFieldDisplayMainKey3，linkageFieldDisplayMainKey2，再开启linkageFieldDisplayMainKey1（关闭则逆序）
								//1.先判断linkageFieldDisplayMainKey3是否能选择
								//页面中设置联动字段为对应选项值,不能则直接no
								Boolean bbbb = SeleniumUtils.isSelectByValuePresent(driver, linkageFieldDisplayMainKey3,
										ListAndStringUtils.displayMainValueToSelectByValue(linkageFieldDisplayMainValue2));
								if (bbbb) {
									//2.开启linkageFieldRowNum3
									new Select(driver.findElementById(linkageFieldIdXpath3)).
											selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(linkageFieldDisplayMainValue2));
									//3.检查linkageFieldRowNum2是否存在
									if (SeleniumUtils.isElementPresent(driver,linkageFieldIdXpath2)) {
										//4.存在，则判断linkageFieldRowNum2能否设置联动字段
										//页面中设置联动字段为对应选项值
										Boolean bbbbb = SeleniumUtils.isSelectByValuePresent(driver,linkageFieldIdXpath2, 
												ListAndStringUtils.displayMainValueToSelectByValue(linkageFieldDisplayMainValue1));
										if (bbbbb) {
											new Select(driver.findElementById(linkageFieldIdXpath2)).
													selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(linkageFieldDisplayMainValue1));
											//5.检查是否存在linkageFieldRowNum1
											if (SeleniumUtils.isElementPresent(driver,linkageFieldIdXpath1)) {
												//6.存在，则判断linkageFieldRowNum1能否设置联动字段
												Boolean bbbbbb = SeleniumUtils.isSelectByValuePresent(driver,linkageFieldIdXpath1, 
														ListAndStringUtils.displayMainValueToSelectByValue(displayMainValue));
												//7.能设置，去判断是否存在要判断的元素
												if (bbbbbb) {
													new Select(driver.findElementById(linkageFieldIdXpath1)).
															selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(displayMainValue));
													//检查是否存在字段
													//存在,则结果直接为pass
													if (SeleniumUtils.isElementPresent(driver,idXpath)) {
														ExcelUtils.writeAndSaveContent(excel, "pass", fieldRowNum, linkageResultCellNum);
													}else {//不存在,则结果直接no
														ExcelUtils.writeAndSaveContent(excel, "no2", fieldRowNum, linkageResultCellNum);
													}
													//8.判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭linkageFieldIdXpath1,linkageFieldIdXpath2，再关闭linkageFieldIdXpath3)
													new Select(driver.findElementById(linkageFieldIdXpath1)).selectByIndex(0);
												}else {
													ExcelUtils.writeAndSaveContent(excel, "no3", fieldRowNum, linkageResultCellNum);
												}	
											}else {
												ExcelUtils.writeAndSaveContent(excel, "no4", fieldRowNum, linkageResultCellNum);
											}
											//9.判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭linkageFieldIdXpath1,linkageFieldIdXpath2，再关闭linkageFieldIdXpath3)
											new Select(driver.findElementById(linkageFieldIdXpath2)).selectByIndex(0);
										}else {
											ExcelUtils.writeAndSaveContent(excel, "no5", fieldRowNum, linkageResultCellNum);
										}
									}else {//不存在,则结果直接no
										ExcelUtils.writeAndSaveContent(excel, "no6", fieldRowNum, linkageResultCellNum);
									}
									
									//判断之后跟选项归位，以防影响后面元素判断(需要逆序关闭，则关闭linkageFieldIdXpath1,linkageFieldIdXpath2，再关闭linkageFieldIdXpath3)
									new Select(driver.findElementById(linkageFieldIdXpath3)).selectByIndex(0);
								}else {
									ExcelUtils.writeAndSaveContent(excel, "no7", fieldRowNum, linkageResultCellNum);
								}
							}
						}else {
							ExcelUtils.writeAndSaveContent(excel, "超过四层", fieldRowNum, linkageResultCellNum);
						}
					}*/
					//===================四层逻辑结束============================
					/*else {
						ExcelUtils.writeAndSaveContent(excel, "超过三层，人工验证", fieldRowNum, linkageResultCellNum);
					}*/
					
					//继续循环，不进入下面操作
					//continue;
				}
				//===================三层逻辑结束============================
				
				else {
					ExcelUtils.writeAndSaveContent(excel, "超过三层，人工验证", fieldRowNum, linkageResultCellNum);
				}
			}
		}
	}
	
	
	
}

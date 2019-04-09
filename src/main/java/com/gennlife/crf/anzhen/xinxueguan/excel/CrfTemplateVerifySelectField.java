package com.gennlife.crf.anzhen.xinxueguan.excel;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

import com.gennlife.crf.bean.Excel;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.ExcelUtils;
import com.gennlife.crf.utils.ListAndStringUtils;
import com.gennlife.crf.utils.LoginCrfOfAnzhen;
import com.gennlife.crf.utils.QuitWebDriver;
import com.gennlife.crf.utils.SeleniumUtils;

/**
 * @Description: 验证页面枚举字段(自动验证联动字段，注意：联动只有三层逻辑)
 * @author: wangmiao
 * @Date: 2017年9月22日 上午10:24:13 
 */
public class CrfTemplateVerifySelectField{

	/** 
	* @Title: verifySelectField 
	* @Description: 验证下拉框字段
	* @param: @param excelmb
	* @param: @param excel
	* @return: void
	* @throws 
	*/
	public static void verifySelectField(Excel excelmb,Excel excel) throws Exception {
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "中文名称");
		Integer idXpathCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "idXpath");
		//获取中文名称一列
		List<String> list = ExcelUtils.readExcelOfList(excelmb, chNameCellNum);
		
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
					verifySelectFieldOfWebDriver(excel,idXpath);
				}
			}
			
			//如果对应的sheet不存在则继续
			if (!ExcelUtils.checkSheetOfExcelExist(excel)) {
				continue;
			}
		}
	}
	
	
	/** 
	* @Title: verifySelectFieldOfWebDriver 
	* @Description: 验证下拉框字段，去页面创建webDriver，相当于每个页面创建一个连接
	* @param: @param excel
	* @param: @param idXpath
	* @param: @throws Exception :
	* @return: void
	* @throws 
	*/
	public static void verifySelectFieldOfWebDriver(Excel excel,String idXpath) throws Exception {
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		String value = LoginCrfOfAnzhen.loginAndToAddOfXinxueguanByPhantomJSDriver(driver);

		//使用安贞高血压进行测试
		if ("添加页面".equals(value)) {
			//随访需要
			driver.findElementByClassName("dropdown-toggle").click();
			driver.findElementById("add-followup").click();
			Thread.sleep(1500);
			
			//测试
			driver.findElementById(idXpath).click();
			Thread.sleep(2000);
			
			//验证之前，先把联动打开
			openLinkageFieldOfExcel(driver, excel);
			
			//验证
			verifySelectFieldOfExcel(driver, excel);	
		}

		// 关闭driver
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
	}
		
	
	/** 
	* @Title: verifySelectFieldOfExcel 
	* @Description: 验证下拉框字段，去excel遍历所有字段
	* @param: @param driver
	* @param: @param excel
	* @param: @throws Exception :
	* @return: void
	* @throws 
	*/
	public static void verifySelectFieldOfExcel(PhantomJSDriver driver,Excel excel) throws Exception {
		Integer variableTypeCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "变量类型");
		Integer rangeDataCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "取值范围");
		Integer idXpathCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "idXpath");
		Integer selectOutputValueCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "selectOutputValue");
		Integer selectResultCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "selectResult");
		
		//防止没有写字段的情况
		if (selectOutputValueCellNum!=null && selectResultCellNum!=null) {
			//获取variableTypeCellNum列
			Map<Integer, String> variableTypeMap = ExcelUtils.readExcelOfListReturnMap(excel, variableTypeCellNum);
			for (Entry<Integer, String> entry: variableTypeMap.entrySet()) {
				//遍历
				Integer fieldRowNum = entry.getKey();
				String variableType = entry.getValue();
				//当前的idXpath
				String idXpath = ExcelUtils.readContent(excel, fieldRowNum, idXpathCellNum);
				
				//获取枚举值
				if ("枚举型".equals(variableType)) {
					//System.out.println(fieldRowNum+"======"+variableType);
					//获取取值范围的值
					String rangeData = ExcelUtils.readContent(excel,fieldRowNum,rangeDataCellNum);
					//将其；;等进行处理，统一用中文分号；，返回string
					String neededRangeData = ListAndStringUtils.rangeDataReturnNeededRangeData(rangeData);
					
					//判断元素是否存在
					//不存在,则结果直接为：页面中没找到对应的id
					if (SeleniumUtils.isElementPresent(driver,idXpath)) {
						//去页面 遍历下拉列表所有选项
						Select selall = new Select(driver.findElement(By.id(idXpath)));
						//封装成List<WebElement>
						List<WebElement> lw= selall.getOptions();
						//将list转为string
						String selectRangeData = ListAndStringUtils.listWebElementToSelectString(lw);
						//处理页面中的范围的
						String neededSelectRangeData = ListAndStringUtils.rangeDataReturnNeededRangeData(selectRangeData);
						
						//处理页面结果，写入到excel中
						ExcelUtils.writeAndSaveContent(excel,neededSelectRangeData,fieldRowNum, selectOutputValueCellNum);
						
						//比较后写入结果
						if (neededSelectRangeData.equals(neededRangeData)) {
							ExcelUtils.writeAndSaveContent(excel,"pass",fieldRowNum, selectResultCellNum);
						}else {
							ExcelUtils.writeAndSaveContent(excel,"no",fieldRowNum, selectResultCellNum);
						}
						
					}else {//不存在直接：页面中没找到对应的id
						ExcelUtils.writeAndSaveContent(excel,"超过三层或页面中没找到对应的id",fieldRowNum, selectResultCellNum);
					}
					
				}else {
					continue;
				}
				
			}
		}
	}
	
	
	
	/** 
	* @Title: openLinkageFieldOfExcel 
	* @Description: 打开联动字段，去excel遍历所有字段
	* @param: @param driver
	* @param: @param excel
	* @param: @throws Exception :
	* @return: void
	* @throws 
	*/
	public static void openLinkageFieldOfExcel(PhantomJSDriver driver,Excel excel) throws Exception {
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		Integer idXpathCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "idXpath");
		Integer displayMainKeyCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "__displayMainKey");
		Integer displayMainValueCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "__displayMainValue");
		
		//防止没有联动字段的情况
		if (displayMainKeyCellNum!=null && displayMainValueCellNum!=null) {

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
				}
				
				//===================二层逻辑开始============================
				//两层结构
				if (linkageFieldDisplayMainKey1==null || "".equals(linkageFieldDisplayMainKey1) || " ".equals(linkageFieldDisplayMainKey1)) {
					//判断页面中是否有所联动的值
					//页面中设置联动字段为对应选项值
					Boolean b = SeleniumUtils.isSelectByValuePresent(driver,linkageFieldIdXpath1, 
							ListAndStringUtils.displayMainValueToSelectByValue(displayMainValue));
					//打开联动
					if (b) {
						new Select(driver.findElementById(linkageFieldIdXpath1)).
							selectByValue(ListAndStringUtils.displayMainValueToSelectByValue(displayMainValue));
					}
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
					}
					
					//三层结构
					if (linkageFieldDisplayMainKey2==null || "".equals(linkageFieldDisplayMainKey2) || " ".equals(linkageFieldDisplayMainKey2)) {
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
								}else {
									continue;
								}
							}else {
								continue;
							}
						}else {
							continue;
						}
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
						}
						
						//四层结构
						if (linkageFieldDisplayMainKey3==null || "".equals(linkageFieldDisplayMainKey3) || " ".equals(linkageFieldDisplayMainKey3)) {
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
											}//bbbbbb
										}
									}//bbbbb
								}
							}
							//bbbb
						}
					}*/
				}
				//===================四层逻辑结束============================
				else {
					//继续循环，不进入下面操作
					continue;
				}
				//===================三层逻辑结束============================
			}//for
		}
	}
	
	
}

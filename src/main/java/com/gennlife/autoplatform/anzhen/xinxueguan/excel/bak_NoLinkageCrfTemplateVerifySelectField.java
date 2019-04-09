package com.gennlife.autoplatform.anzhen.xinxueguan.excel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
 * @Description: 验证页面枚举字段
 * （注意：因为有联动，所以在页面创建时候，需要传入一个全字段的病例。后期会把联动加上，只需要填写必填字段）
 * @author: wangmiao
 * @Date: 2017年9月22日 上午10:24:13 
 */
public class bak_NoLinkageCrfTemplateVerifySelectField{

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
				//存在则进行去验证
				verifySelectFieldOfWebDriver(excel,idXpath);
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
		//
		String value = LoginCrfOfAnzhen.loginByPhantomJSDriver(driver);
		Thread.sleep(2000);
		//使用安贞高血压进行测试

		if ("登陆成功".equals(value)) {
			//到全字段的病例页面（目前是第一个病例）
			driver.findElementByXPath(".//*[@id='case-list-container']/tbody/tr[1]/td[2]/a").click();
			
			String currentWindow = driver.getWindowHandle();// 获取当前窗口句柄
		    Set<String> handles = driver.getWindowHandles();// 获取所有窗口句柄
		    Iterator<String> it = handles.iterator();
		    while (it.hasNext()) {
		        if (currentWindow == it.next()) {
		            continue;
		        }
		        driver = (PhantomJSDriver) driver.switchTo().window(it.next());// 切换到新窗口
		    }
			Thread.sleep(2000);
			
			//测试
			driver.findElementById(idXpath).click();
			Thread.sleep(1500);
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
						ExcelUtils.writeAndSaveContent(excel,"页面中没找到对应的id",fieldRowNum, selectResultCellNum);
					}
					
				}else {
					continue;
				}
				
			}
		}
	}
	
	
	
}

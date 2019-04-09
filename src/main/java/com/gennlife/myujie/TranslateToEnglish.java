package com.gennlife.myujie;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.gennlife.crf.bean.Excel;
import com.gennlife.crf.utils.CreateWebDriver;
import com.gennlife.crf.utils.ExcelUtils;
import com.gennlife.crf.utils.ListAndStringUtils;
import com.gennlife.crf.utils.QuitWebDriver;

/**
 * @Description: 将中文翻译成英文
 * @author: wangmiao
 * @Date: 2017年9月7日 下午2:37:08 
 */
public class TranslateToEnglish{

	/**
	* @Title: writeEnNames 
	* @Description: 根据结构，在crf模板中，写入英文名称
	* @param: @param excelmb 结构的excel
	* @param: @param excel 要获取的excel
	* @throws 
	*/
	public static void writeEnNames(Excel excelmb,Excel excel) throws Exception {
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "中文名称");
		Integer groupInfoCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "组结构信息");
		
		//获取中文名称一列
		List<String> list = ExcelUtils.readExcelOfList(excelmb, chNameCellNum);
		//
		System.out.println(list);
		//除去表头开始遍历(上面读取时候已经去除表头了，所以从0开始)
		for (int i = 0; i < list.size(); i++) {
			//设置excel的sheet值
			excel.setSheetName(list.get(i));
			System.out.println(list.get(i));
			//判断excel是否存在该sheet
			if (ExcelUtils.checkSheetOfExcelExist(excel)) {
				//继续判断为两组还是三组
				Integer rowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excelmb, chNameCellNum, list.get(i));
				String groupInfo = ExcelUtils.readContent(excelmb, rowNum, groupInfoCellNum);
				
				if ("两组".equals(groupInfo)) {
					//转到第二组的逻辑,写入英文名称
					writeEnNamesOfTwoGroups(excel);
				}
				
				if ("三组".equals(groupInfo)) {
					//转到第三组的逻辑，写入英文名称
					writeEnNamesOfThreeGroups(excel);
				}
			}
			
			//如果对应的sheet不存在则继续
			if (!ExcelUtils.checkSheetOfExcelExist(excel)) {
				continue;
			}
		}
	}
	
	
	/**
	* @Title: writeEnNamesOfTwoGroups 
	* @Description: 两组的情况，在crf模板中，获取中文列表
	* @param: @param excel 传入excel
	* @throws 
	*/
	public static void writeEnNamesOfTwoGroups(Excel excel) throws Exception {
		Integer twoGroupCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "第二组");
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "中文名");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		
		//获取中文名称(2列的情况)
		List<String> chNamesList = ExcelUtils.readExcelOfTwoList(excel, twoGroupCellNum, chNameCellNum);
		//中文过滤
		List<String> chNamesListFilter = ListAndStringUtils.chNamesListFilter(chNamesList);
		
		//转换成英文
		List<String> enNamesList = translateChNamesListToEnNamesList(chNamesListFilter);
		//英文过滤
		List<String> enNamesListFilter = ListAndStringUtils.enNamesListFilter(enNamesList);
		//加序号
		List<String> sequenceList = ListAndStringUtils.sameListTransferToSequenceList(enNamesListFilter);
		
		//写入
		for (int i = 0; i < sequenceList.size(); i++) {
			ExcelUtils.writeAndSaveContent(excel, sequenceList.get(i), i+1, enNameCellNum);
		}
	}
	

	/**
	* @Title: writeEnNamesOfThreeGroups 
	* @Description: 两组的情况，在crf模板中，获取中文列表
	* @param: @param excel 传入excel
	* @return: List<String> 中文名称的列表
	* @throws 
	*/
	public static void writeEnNamesOfThreeGroups(Excel excel) throws Exception {
		Integer twoGroupCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "第二组");
		Integer threeGroupCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "第三组");
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "中文名");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		
		//获取中文名称(3列的情况)
		List<String> chNamesList = ExcelUtils.readExcelOfThreeList(excel,twoGroupCellNum,threeGroupCellNum,chNameCellNum);
		//中文过滤
		List<String> chNamesListFilter = ListAndStringUtils.chNamesListFilter(chNamesList);
		
		//转换成英文
		List<String> enNamesList = translateChNamesListToEnNamesList(chNamesListFilter);
		//英文过滤
		List<String> enNamesListFilter = ListAndStringUtils.enNamesListFilter(enNamesList);
		//加序号
		List<String> sequenceList = ListAndStringUtils.sameListTransferToSequenceList(enNamesListFilter);
		
		//写入
		for (int i = 0; i < sequenceList.size(); i++) {
			ExcelUtils.writeAndSaveContent(excel, sequenceList.get(i), i+1, enNameCellNum);
		}
	}
	
	
	/** 
	* @Title: translatechNamesListToEnNamesList 
	* @Description: 将list中每个中文，转换成英文的list
	* @param: @param chNamesList 传入的中文list
	* @param: @return
	* @return: List<String> 返回英文list
	* @throws 
	*/
	public static List<String> translateChNamesListToEnNamesList(List<String> chNamesList) throws Exception{
		//先定义两个list，分别存放中文、翻译后的英文
		List<String> enNamesList = new ArrayList<String>();
		
		// 登录并到add页面
		PhantomJSDriver driver = CreateWebDriver.createWebDriverByPhantomJSDriver();
		driver.navigate().to("http://fanyi.baidu.com/translate#zh/en/");
		
		String output=null;
		//开始处理翻译，并返回list
		for (int i = 0; i < chNamesList.size(); i++) {
			//使用谷歌翻译
			driver.navigate().to("https://translate.google.cn/#view=home&op=translate&sl=zh-CN&tl=en");
			Thread.sleep(1000);
			driver.findElementById("source").clear();
			Thread.sleep(1000);
			driver.findElementById("source").sendKeys(chNamesList.get(i));
			Thread.sleep(1800);
			
			/*百度翻译不好使了。。。。
			driver.findElementById("baidu_translate_input").clear();
			driver.findElementById("baidu_translate_input").sendKeys(chNamesList.get(i));
			Thread.sleep(1000);
			driver.findElementById("translate-button").click();
			Thread.sleep(1800);
			*/
			//获取翻译后的值
			output=driver.findElementByXPath("/html/body/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div/span[1]/span").getText();
			if (output==null) {
				enNamesList.add("a");
			}else {
				enNamesList.add(output);
			}
			
			//Thread.sleep(800);
		}
		QuitWebDriver.quitWebDriverByPhantomJSDriver(driver);
		
		return enNamesList;
	}

	
}

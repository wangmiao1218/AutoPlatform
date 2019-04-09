package com.gennlife.crf.tianjin.crfPackage.test;

import org.junit.Test;
import com.gennlife.crf.bean.Excel;
import com.gennlife.crf.utils.ExcelUtils;

public class Testtianjin {

	private String filePath = "E:\\CRF重组\\自动工具";
	private String fileName = "test_溯源详情页与病例数据schema对应关系.xlsx";
	private String sheetName = "简化数据模型";
	private String sheetName1 = "数据模型";
	
	
	private String fileNameTest = "test_预定实现字段.xlsx";
	private String sheetNameTest = "例子";
	
	@Test
	public void search() {
		//读取需要测试的excel
		Excel excelTest = new Excel(filePath, fileNameTest, sheetNameTest);
		String content = ExcelUtils.readContent(excelTest, 1, 12);
		System.out.println("content:"+content);
		String preString = content.substring(0,content.lastIndexOf("."));
		String lastString = content.substring(content.lastIndexOf(".")+1);
		System.out.println("preString:"+preString);
		System.out.println("lastString:"+lastString);
		
		Excel excel = new Excel(filePath, fileName, sheetName);
		Integer rowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excel, 0, preString);
		System.out.println(rowNum);
		String engContent = ExcelUtils.readContent(excel, rowNum, 1);
		System.out.println(engContent);
		String tableString = engContent.substring(engContent.lastIndexOf(".")+1);
		System.out.println("tableString:"+tableString);
		
		//ExcelUtils.readExcelOfListReturnListMap(excel, beginCell);
		String fieldName ="DISCUSSION_CONTENT";
		
		//查数据库
		
		
		
		/*
		Excel excel = new Excel(filePath, fileName, sheetName);
		List<String> enNamesList = ExcelUtils.readExcelOfList(excel, 3);
		//英文过滤
		List<String> enNamesListFilter = ListAndStringUtils.enNamesListFilter(enNamesList);
		//加序号
		List<String> sequenceList = ListAndStringUtils.sameListTransferToSequenceList(enNamesListFilter);
		*/
        
		
		
		
	}
	
	
	
}

package com.gennlife.autoplatform.mongodb.test;

import java.util.List;

import org.junit.Test;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.mongodb.YantaiMongodbDataProcess;
import com.gennlife.autoplatform.utils.ExcelUtils;

public class TestYantaiMongodbDataProcess {

	private String patient_sn = "pat_019a52f2f08aa7a837fdf52cb5dbb58c";
	private String rydate = "2014-10-13 10:51:00";
	
	@Test
	public void search() {
		String str = YantaiMongodbDataProcess.RysqkYongyaoqkReturnDataSources(patient_sn, rydate);
		//System.out.println(str);
        String filePath = "E:\\烟台升级\\crf数据测试";
    	String fileName = "神内脑血管药物.xlsx";
    	String sheetName = "降血脂药物";
    	String sheetName2 = "精神科药";
    	String sheetName3 = "降糖药物";
    	
    	Excel excel = new Excel(filePath, fileName, sheetName);
        List<String> list = ExcelUtils.readExcelOfList(excel, 1);
        for (int i = 0; i < list.size(); i++) {
        	if (str.contains(list.get(i))) {
				System.out.println("存在的_"+sheetName+"："+list.get(i));
			}
		}
        
        Excel excel2 = new Excel(filePath, fileName, sheetName2);
        List<String> list2 = ExcelUtils.readExcelOfList(excel2, 0);
        for (int i = 0; i < list2.size(); i++) {
        	if (str.contains(list2.get(i))) {
				System.out.println("存在的_"+sheetName2+"："+list2.get(i));
			}
		}
		
        Excel excel3 = new Excel(filePath, fileName, sheetName3);
        List<String> list3 = ExcelUtils.readExcelOfList(excel3, 1);
        for (int i = 0; i < list3.size(); i++) {
        	if (str.contains(list3.get(i))) {
				System.out.println("存在的_"+sheetName3+"："+list3.get(i));
			}
		}
        
        if (str.contains("来得时")) {
			System.out.println("存在的_胰岛素：来得时");
		}
        if (str.contains("诺和灵")) {
        	System.out.println("存在的_胰岛素：诺和灵");
        }
        
        
	}
	
	
}

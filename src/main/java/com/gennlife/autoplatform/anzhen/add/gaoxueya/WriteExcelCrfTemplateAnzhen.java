package com.gennlife.autoplatform.anzhen.add.gaoxueya;

import java.util.List;

import com.gennlife.autoplatform.bean.CrfTemplateAnzhen;
import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.ExcelUtils;

/**
 * @Description: 安贞环境，根据数据库，写入excel
 * @author: wangmiao
 * @Date: 2017年8月4日 上午9:11:45 
 */
public class WriteExcelCrfTemplateAnzhen{

	/** 
	* @Title: writeExcelByCompareEnglishName 
	* @Description: 读取数据库列表的englishname，比较excel中某列是否存在，存在则从数据库取出相应的值填入到excel对应列
	* @param: Excel excel： 需要写入的excel
	* @param: List<CrfTemplateAnzhen> list ：查询数据库的list
	* @param: Integer beginCell ：传入的列号
	* @param: @throws Exception :
	* @return: String
	* @throws 
	*/
	public static String writeExcelByCompareEnglishName(Excel excel,List<CrfTemplateAnzhen> list,Integer beginCell) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			Integer beginRow = ExcelUtils.searchKeyWordOfListReturnRowNum(excel,1,list.get(i).getEnglishName());
			//随访专用
			//Integer beginRowSf = ExcelUtils.searchKeyWord(excel,1,"FOLLOW_UP_3_"+list.get(i).getEnglishName());
			//判断是否为null
			if (beginRow != null) {
				//设计excel时，要确定写入几列
				ExcelUtils.writeAndSaveContent(excel,list.get(i).getChineseName(),beginRow,beginCell);
				ExcelUtils.writeAndSaveContent(excel,list.get(i).getEnglishName(),beginRow,beginCell+1);
				ExcelUtils.writeAndSaveContent(excel,list.get(i).getInputValue(),beginRow,beginCell+2);
			}
		}
		return "写入完成。。。";
	}
	
	
	/** 
	* @Title: readTwoContentAndJudgeAndWriteResult 
	* @Description: 读excel两列值，并比较是否相等，在对应列写入比较结果，pass为相等，no为不相等
	* @param: @param excel ：出入的excel
	* @param: @param allRow  ：总共需要比较的行数
	* @param: @param beginCell  ：需要比较的列1（从0开始）
	* @param: @param endCell  ：需要比较的列2（从0开始）
	* @param: @param writeCell  ：写入结果的列（从0开始）
	* @param: @return :
	* @return: String
	* @throws 
	*/
	public static String readTwoContentAndJudgeAndWriteResult(Excel excel, Integer allRow, Integer beginCell,Integer endCell,Integer writeCell ) {
		for (int i = 0; i < allRow; i++) {
			String result = ExcelUtils.readTwoContentAndJudge(excel,i, beginCell,endCell);
			if (result!=null) {
				ExcelUtils.writeAndSaveContent(excel,result,i,writeCell);
			}
		}
		return "比较完成。。。";
	}

	
}

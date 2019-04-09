package com.gennlife.myujie;

import java.util.List;
import java.util.Map;
import com.gennlife.crf.bean.Excel;
import com.gennlife.crf.utils.ExcelUtils;
import com.gennlife.crf.utils.ListAndStringUtils;

/**
 * @Description: 数据质控
 * @author: wangmiao
 * @Date: 2018年10月24日 下午2:20:37 
 */
public class ConfiguredDataQualityControl {

	/** 
	* @Title: writeDataQualityControl 
	* @Description: 
	* @author: wangmiao
	* @Date: 2018年10月24日 下午2:38:06 
	* @param: @param excelmb 结构的excel
	* @param: @param excel 要写入的excel
	* @return: void
	* @throws 
	*/
	public static void writeDataQualityControl(Excel excelmb,Excel excel) {
		Integer sourceCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "源Sheet");
		Integer endCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "目的Sheet");
		System.out.println(sourceCellNum);
		System.out.println(endCellNum);
		
		//获取源Sheet
		List<String> list = ExcelUtils.readExcelOfList(excelmb, sourceCellNum);
		
		//除去表头开始遍历
		for (int i = 0; i < list.size(); i++) {
			//设置excel的sheet值
			System.out.println(list.get(i));
			excel.setSheetName(list.get(i));
			//判断excel是否存在源sheet
			if (ExcelUtils.checkSheetOfExcelExist(excel)) {
				//目标sheet
				Integer rowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excelmb, sourceCellNum, list.get(i));
				String tableName = ExcelUtils.readContent(excelmb, rowNum, endCellNum);
				System.out.println(tableName);
				
				if ("年份分布".equals(list.get(i))) {
					System.out.println(list.get(i)+"--"+tableName);
					//annualDistribution(excel,list.get(i),tableName);
				}
				/*if ("数据完整性".equals(list.get(i))) {
					writeSchemaOfThreeGroups(excel,tableName);
				}
				if ("唯一约束".equals(list.get(i))) {
					writeSchemaOfThreeGroups(excel,tableName);
				}*/
			}
			
			//如果对应的sheet不存在则继续
			if (!ExcelUtils.checkSheetOfExcelExist(excel)) {
				continue;
			}
		}
		
	}
	
	
	/** 
	* @Title: writeSchemaOfTwoGroups 
	* @Description: 两组的情况，在crf模板中，写入schema路径
	* @param: @param excel 传入excel
	* @param: @param tableName :传入的表名
	* @throws 
	*/
	/*public static void annualDistribution(Excel excel,String sourceTableName,
			String endTableName) {
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "表中文名");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "表英文名");
		Integer timeCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "时间字段");
		Integer totalNumberCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "总记录数");
		Integer yearCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "年份");
		Integer yearTotalNumberCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "所在年份记录数");
		Integer yearRatioCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "所在年份记录占比");
		
		//获取中文名称一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, enNameCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			//定义行号和内容（即：写入值时对应的行号）
			Integer writeContentRowNum=null;
			String allString=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				allString=entry.getValue();
			}
			
			//分割
			String[] strings = ListAndStringUtils.trimStringOfEqualSign(allString);
			if (strings.length==1 || strings.length==0) {
				continue;
			}
			//获取=前字段名的行号及英文名(由于有重复值，所以使用searchKeyWordOfListByOrderDescReturnRowNum，逆序查找离着最近的值)
			Integer chNameRowNum = ExcelUtils.searchKeyWordOfListByOrderDescReturnRowNum(excel, writeContentRowNum, chNameCellNum, strings[0]);
			if (chNameRowNum==null) {
				continue;
			}
			String fieldEnName = ExcelUtils.readContent(excel, chNameRowNum, enNameCellNum);
			
			//获取对应第二组的行号英文名(从上面字段名所在行号，往上查找，查最近一个有值的)
			Integer twoGroupRowNum = ExcelUtils.searchValueOfListByOrderDescReturnRowNum(excel, chNameRowNum, twoGroupCellNum);
			if (twoGroupRowNum==null) {
				continue;
			}
			String twoGroupEnName = ExcelUtils.readContent(excel,twoGroupRowNum,enNameCellNum);
			
			//最终内容
			String newContent="schema."+tableName+"."+twoGroupEnName+"."+fieldEnName;
			
			ExcelUtils.writeAndSaveContent(excel, newContent, writeContentRowNum, mainKeyCellNum);
			ExcelUtils.writeAndSaveContent(excel, strings[1], writeContentRowNum, mainValueCellNum);
		}
		
	}*/
	

}

package com.gennlife.pmtools;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.ExcelUtils;
import com.gennlife.autoplatform.utils.ListAndStringUtils;

/**
 * @Description:配置联动路径
 * @author: wangmiao
 * @Date: 2017年8月30日 上午9:16:08
 */
public class ConfiguredLinkagePath {
	private static Logger logger = Logger.getLogger(ConfiguredLinkagePath.class); 
	
	/** 
	* @Title: writeLinkagePath 
	* @Description: 根据结构，在crf模板中，写入schema路径
	* @param: @param excelmb 结构的excel
	* @param: @param excel :要写入的excel
	* @throws 
	*/
	public static void writeLinkagePath(Excel excelmb,Excel excel) {
		logger.info("start。。。");
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "中文名称");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "英文名称");
		Integer groupInfoCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "组结构信息");
		List<String> list = ExcelUtils.readExcelOfList(excelmb, chNameCellNum);
		for (int i = 1; i < list.size(); i++) {
			excel.setSheetName(list.get(i));
			if (ExcelUtils.checkSheetOfExcelExist(excel)) {
				Integer rowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excelmb, chNameCellNum, list.get(i));
				String groupInfo = ExcelUtils.readContent(excelmb, rowNum, groupInfoCellNum);
				String tableName = ExcelUtils.readContent(excelmb, rowNum, enNameCellNum);
				if ("两组".equals(groupInfo)) {
					writeSchemaOfTwoGroups(excel,tableName);
				}
				if ("三组".equals(groupInfo)) {
					writeSchemaOfThreeGroups(excel,tableName);
				}
			}
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
	public static void writeSchemaOfTwoGroups(Excel excel,String tableName) {
		Integer twoGroupCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "第二组");
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "中文名");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		Integer mainKeyCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "__displayMainKey");
		Integer mainValueCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "__displayMainValue");
		Integer linkageCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "联动");
		
		//获取中文名称一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, linkageCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			Integer writeContentRowNum=null;
			String allString=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				allString=entry.getValue();
			}
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
			Integer twoGroupRowNum = ExcelUtils.searchValueOfListByOrderDescReturnRowNum(excel, chNameRowNum, twoGroupCellNum);
			if (twoGroupRowNum==null) {
				continue;
			}
			String twoGroupEnName = ExcelUtils.readContent(excel,twoGroupRowNum,enNameCellNum);
			String newContent="schema."+tableName+"."+twoGroupEnName+"."+fieldEnName;
			ExcelUtils.writeAndSaveContent(excel, newContent, writeContentRowNum, mainKeyCellNum);
			ExcelUtils.writeAndSaveContent(excel, strings[1], writeContentRowNum, mainValueCellNum);
		}
	}
	

	/** 
	* @Title: writeSchemaOfThreeGroups 
	* @Description: 三组的情况，在crf模板中，写入schema路径
	* @param: @param excel 传入excel
	* @param: @param tableName :传入的表名
	* @throws 
	*/
	public static void writeSchemaOfThreeGroups(Excel excel,String tableName) {
		Integer twoGroupCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "第二组");
		Integer threeGroupCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "第三组");
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "中文名");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		Integer mainKeyCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "__displayMainKey");
		Integer mainValueCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "__displayMainValue");
		Integer linkageCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "联动");
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, linkageCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			Integer writeContentRowNum=null;
			String allString=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				allString=entry.getValue();
			}
			String[] strings = ListAndStringUtils.trimStringOfEqualSign(allString);
			if (strings.length==1 || strings.length==0) {
				continue;
			}
			Integer chNameRowNum = ExcelUtils.searchKeyWordOfListByOrderDescReturnRowNum(excel, writeContentRowNum, chNameCellNum, strings[0]);
			if (chNameRowNum==null) {
				continue;
			}
			String fieldEnName = ExcelUtils.readContent(excel, chNameRowNum, enNameCellNum);
			Integer twoGroupRowNum = ExcelUtils.searchValueOfListByOrderDescReturnRowNum(excel, chNameRowNum, twoGroupCellNum);
			if (twoGroupRowNum==null) {
				continue;
			}
			String twoGroupEnName = ExcelUtils.readContent(excel,twoGroupRowNum,enNameCellNum);
			Integer threeGroupRowNum = ExcelUtils.searchValueOfListBetweenTwoRowNumByOrderDescReturnRowNum(excel, twoGroupRowNum, chNameRowNum, threeGroupCellNum);
			String newContent=null;
			if (threeGroupRowNum==null) {
				newContent="schema."+tableName+"."+twoGroupEnName+"."+fieldEnName;
			}else {
				String threeGroupEnName = ExcelUtils.readContent(excel,threeGroupRowNum,enNameCellNum);
				newContent="schema."+tableName+"."+twoGroupEnName+"."+threeGroupEnName+"."+fieldEnName;
			}
			ExcelUtils.writeAndSaveContent(excel, newContent, writeContentRowNum, mainKeyCellNum);
			ExcelUtils.writeAndSaveContent(excel, strings[1], writeContentRowNum, mainValueCellNum);
		}
	}
	
}

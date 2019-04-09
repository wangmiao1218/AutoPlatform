package com.gennlife.pmtools;

import java.util.List;
import java.util.Map;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.ExcelUtils;

/**
 * @Description: 配置是否必填字段的路径
 * @author: wangmiao
 * @Date: 2017年9月29日 下午2:56:26 
 */
public class ConfiguredRequiredPath {

	/** 
	* @Title: writeRequiredPath 
	* @Description: 根据结构，在crf模板中，写入是否必填路径
	* @param: @param excelmb 结构的excel
	* @param: @param excel :要写入的excel
	* @throws 
	*/
	public static void writeRequiredPath(Excel excelmb,Excel excel) {
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
				if ("一组".equals(groupInfo)) {
					writePathOfOneGroups(excel,tableName);
				}
				if ("两组".equals(groupInfo)) {
					writePathOfTwoGroups(excel,tableName);
				}
				if ("三组".equals(groupInfo)) {
					writePathOfThreeGroups(excel,tableName);
				}
			}
			if (!ExcelUtils.checkSheetOfExcelExist(excel)) {
				continue;
			}
		}
	}
	
	
	/** 
	* @Title: writePathOfOneGroups 
	* @Description: 一组的情况，在crf模板中，写入必填字段路径
	* @param: @param excel 传入excel
	* @param: @param tableName :传入的表名
	* @throws 
	*/
	public static void writePathOfOneGroups(Excel excel,String tableName) {
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		Integer ngRequiredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否必填");
		Integer requiredPathCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "必填路径");
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, ngRequiredCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			Integer writeContentRowNum=null;
			String isStr=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				isStr=entry.getValue();
			}
			String fieldEnName = ExcelUtils.readContent(excel, writeContentRowNum, enNameCellNum);
			String newContent="schema."+tableName+"."+fieldEnName;
			ExcelUtils.writeAndSaveContent(excel, newContent, writeContentRowNum, requiredPathCellNum);
		}
	}
	
	
	/** 
	 * @Title: writeSchemaOfTwoGroups 
	 * @Description: 两组的情况，在crf模板中，写入必填字段路径
	 * @param: @param excel 传入excel
	 * @param: @param tableName :传入的表名
	 * @throws 
	 */
	public static void writePathOfTwoGroups(Excel excel,String tableName) {
		Integer twoGroupCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "第二组");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		Integer ngRequiredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否必填");
		Integer requiredPathCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "必填路径");
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, ngRequiredCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			Integer writeContentRowNum=null;
			String isStr=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				isStr=entry.getValue();
			}
			String fieldEnName = ExcelUtils.readContent(excel, writeContentRowNum, enNameCellNum);
			Integer twoGroupRowNum = ExcelUtils.searchValueOfListByOrderDescReturnRowNum(excel, writeContentRowNum, twoGroupCellNum);
			if (twoGroupRowNum==null) {
				continue;
			}
			String twoGroupEnName = ExcelUtils.readContent(excel,twoGroupRowNum,enNameCellNum);
			String newContent=null;
			if ("英文名".equals(twoGroupEnName)) {
				newContent="schema."+tableName+"."+fieldEnName;
			}else {
				newContent="schema."+tableName+"."+twoGroupEnName+"."+fieldEnName;
			}
			ExcelUtils.writeAndSaveContent(excel, newContent, writeContentRowNum, requiredPathCellNum);
		}
	}
	

	/** 
	* @Title: writePathOfThreeGroups 
	* @Description: 三组的情况，在crf模板中，写入必填字段路径
	* @param: @param excel 传入excel
	* @param: @param tableName :传入的表名
	* @throws 
	*/
	public static void writePathOfThreeGroups(Excel excel,String tableName) {
		Integer twoGroupCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "第二组");
		Integer threeGroupCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "第三组");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		Integer ngRequiredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否必填");
		Integer requiredPathCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "必填路径");
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, ngRequiredCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			Integer writeContentRowNum=null;
			String isStr=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				isStr=entry.getValue();
			}
			String fieldEnName = ExcelUtils.readContent(excel, writeContentRowNum, enNameCellNum);
			Integer twoGroupRowNum = ExcelUtils.searchValueOfListByOrderDescReturnRowNum(excel, writeContentRowNum, twoGroupCellNum);
			if (twoGroupRowNum==null) {
				continue;
			}
			String twoGroupEnName = ExcelUtils.readContent(excel,twoGroupRowNum,enNameCellNum);
			String newContent=null;
			//如果获取第二组的英文名为“英文名”，则为：没有第二组第三组的情况
			if ("英文名".equals(twoGroupEnName)) {
				newContent="schema."+tableName+"."+fieldEnName;
			}else {
				Integer threeGroupRowNum = ExcelUtils.searchValueOfListBetweenTwoRowNumByOrderDescReturnRowNum(excel, twoGroupRowNum, writeContentRowNum, threeGroupCellNum);
				if (threeGroupRowNum==null) {
					newContent="schema."+tableName+"."+twoGroupEnName+"."+fieldEnName;
				}else {
					String threeGroupEnName = ExcelUtils.readContent(excel,threeGroupRowNum,enNameCellNum);
					newContent="schema."+tableName+"."+twoGroupEnName+"."+threeGroupEnName+"."+fieldEnName;
				}
			}
			ExcelUtils.writeAndSaveContent(excel, newContent, writeContentRowNum, requiredPathCellNum);
		}
	}
	

}

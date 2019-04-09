package com.gennlife.autoplatform.crfLogic;

import java.util.List;
import java.util.Map;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.ExcelUtils;

/**
 * @Description: 配置crfdata数据源
 * @author: wangmiao
 * @Date: 2018年1月13日 下午1:52:25 
 */
public class ConfiguredCrfdataPath {

	/** 
	* @Title: writeRequiredPath 
	* @Description: 根据结构，在crf模板中，写入是否必填路径
	* @param: @param excelmb 结构的excel
	* @param: @param excel :要写入的excel
	* @throws 
	*/
	public static void writeCrfdataPath(Excel excelmb,Excel excel) {
		//System.out.println("start。。。");
		Integer chNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "中文名称");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "英文名称");
		Integer groupInfoCellNum = ExcelUtils.searchKeyWordOfOneLine(excelmb, 0, "组结构信息");
		
		//获取中文名称一列
		List<String> list = ExcelUtils.readExcelOfList(excelmb, chNameCellNum);
		
		//除去表头开始遍历
		for (int i = 1; i < list.size(); i++) {
			//设置excel的sheet值
			excel.setSheetName(list.get(i));
			//判断excel是否存在该sheet
			if (ExcelUtils.checkSheetOfExcelExist(excel)) {
				//继续判断为两组还是三组
				Integer rowNum = ExcelUtils.searchKeyWordOfListReturnRowNum(excelmb, chNameCellNum, list.get(i));
				String groupInfo = ExcelUtils.readContent(excelmb, rowNum, groupInfoCellNum);
				//获取表名
				String tableName = ExcelUtils.readContent(excelmb, rowNum, enNameCellNum);
				
				if ("一组".equals(groupInfo)) {
					//转到第一组的逻辑
					writePathOfOneGroups(excel,tableName);
				}
				if ("两组".equals(groupInfo)) {
					//转到第二组的逻辑
					writePathOfTwoGroups(excel,tableName);
				}
				if ("三组".equals(groupInfo)) {
					//转到第三组的逻辑
					writePathOfThreeGroups(excel,tableName);
				}
			}
			
			//如果对应的sheet不存在则继续
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
		
		//获取ngRequiredCellNum一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, ngRequiredCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			//定义是否必填列的行号和内容（即：写入值时对应的行号）
			Integer writeContentRowNum=null;
			String isStr=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				isStr=entry.getValue();
			}
			//获取英文名
			String fieldEnName = ExcelUtils.readContent(excel, writeContentRowNum, enNameCellNum);
			
			//最终内容
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
		
		//获取ngRequiredCellNum一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, ngRequiredCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			//定义是否必填列的行号和内容（即：写入值时对应的行号）
			Integer writeContentRowNum=null;
			String isStr=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				isStr=entry.getValue();
			}
			//获取英文名
			String fieldEnName = ExcelUtils.readContent(excel, writeContentRowNum, enNameCellNum);
			
			//获取对应第二组的行号英文名(从上面字段名所在行号，往上查找，查最近一个有值的)
			Integer twoGroupRowNum = ExcelUtils.searchValueOfListByOrderDescReturnRowNum(excel, writeContentRowNum, twoGroupCellNum);
			if (twoGroupRowNum==null) {
				continue;
			}
			String twoGroupEnName = ExcelUtils.readContent(excel,twoGroupRowNum,enNameCellNum);
			
			//最终内容
			String newContent=null;
			
			//如果获取第二组的英文名为“英文名”，则为：没有第二组的情况
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
		
		//获取ngRequiredCellNum一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, ngRequiredCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			//定义是否必填列的行号和内容（即：写入值时对应的行号）
			Integer writeContentRowNum=null;
			String isStr=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				writeContentRowNum=entry.getKey();
				isStr=entry.getValue();
			}
			//获取英文名
			String fieldEnName = ExcelUtils.readContent(excel, writeContentRowNum, enNameCellNum);
			
			//获取对应第二组的行号、英文名(从上面字段名所在行号，往上查找，查最近一个有值的)
			Integer twoGroupRowNum = ExcelUtils.searchValueOfListByOrderDescReturnRowNum(excel, writeContentRowNum, twoGroupCellNum);
			
			if (twoGroupRowNum==null) {
				continue;
			}
			String twoGroupEnName = ExcelUtils.readContent(excel,twoGroupRowNum,enNameCellNum);
			
			//最终内容
			String newContent=null;
			
			//如果获取第二组的英文名为“英文名”，则为：没有第二组第三组的情况
			if ("英文名".equals(twoGroupEnName)) {
				newContent="schema."+tableName+"."+fieldEnName;
			}else {
				//获取对应第三组的行号、英文名(从上面字段名所在行号，往上查找，查最近一个有值的，直到到第二组名称的行号)
				Integer threeGroupRowNum = ExcelUtils.searchValueOfListBetweenTwoRowNumByOrderDescReturnRowNum(excel, twoGroupRowNum, writeContentRowNum, threeGroupCellNum);

				//可能没有第三组情况
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

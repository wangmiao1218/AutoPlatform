package com.gennlife.myujie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import net.sf.json.JSONObject;

import com.gennlife.crf.bean.Excel;
import com.gennlife.crf.utils.ExcelUtils;
import com.gennlife.crf.utils.ListAndStringUtils;

/**
 * @Description: 配置crf组装的json
 * @author: wangmiao
 * @Date: 2017年12月22日 下午3:08:37 
 */
public class ConfiguredCrfPackageJson {

	private static JSONObject lastJson=new JSONObject();
	
	
	/** 
	* @Title: getJsonFile 
	* @Description: 读excel相关配置，生成crf组装的json文件
	* @param: @param excel :
	* @return: void
	* @throws 
	*/
	public static void getJsonFile(Excel excel) {
		//System.out.println("start。。。");
		Integer isConfiguredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否配置");
		Integer sourceCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "source");
		Integer enNameCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "英文名");
		Integer nlpCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "数据模型来源（NLP）");
		Integer blockCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "block");
		Integer patientDetailCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "PatientDetail");
		Integer conditionCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "condition");
		Integer selectCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "select");
		Integer inCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "in");
		Integer elementCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "element");
		Integer computeCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "compute");
		
		//获取isConfiguredCellNum一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, isConfiguredCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			//定义是否填的行号和内容
			Integer isConfiguredRowNum=null;
			String isConfiguredStr=null;
			for (Map.Entry<Integer, String> entry : map.entrySet()) {  
				isConfiguredRowNum=entry.getKey();
				isConfiguredStr=entry.getValue();
			}
			
			if ("是".equals(isConfiguredStr)) {
				System.out.println("配置字段！正在配置");
				String enNameContent = ExcelUtils.readContent(excel, isConfiguredRowNum, enNameCellNum);
				//读取compute
				String computeContent = ExcelUtils.readContent(excel, isConfiguredRowNum, computeCellNum);
				
				//判断source
				String fieldEnName = ExcelUtils.readContent(excel, isConfiguredRowNum, sourceCellNum);
				if ("nlp".equals(fieldEnName)) {
					//读取npl的source
					String nlpSourceContent = ExcelUtils.readContent(excel, isConfiguredRowNum, nlpCellNum);
					
					//==============condition======================
					//判断condition是什么
					String conditionContent = ExcelUtils.readContent(excel, isConfiguredRowNum, conditionCellNum);
					String selectContent = ExcelUtils.readContent(excel, isConfiguredRowNum, selectCellNum);
					String inContent = ExcelUtils.readContent(excel, isConfiguredRowNum, inCellNum);
					String elementContent = ExcelUtils.readContent(excel, isConfiguredRowNum, elementCellNum);
					
					int conditionNum = ListAndStringUtils.valueSpiltBySemicolonToStringList(conditionContent).size();
					if (conditionNum==1) {
						if ("select".equals(conditionContent)) {
							//构建select_condition
							ConfiguredCrfPackageJson.selectCondition("select_condition",selectContent,elementContent);
							
						}else if ("in".equals(conditionContent)) {
							//构建in_condition
							ConfiguredCrfPackageJson.inCondition("in_condition",inContent,elementContent);
						
						}
					}else if(conditionNum==2){
						//构建select_condition
						ConfiguredCrfPackageJson.selectCondition("select_condition",selectContent,elementContent);
						
						//构建in_condition
						ConfiguredCrfPackageJson.inCondition("in_condition",inContent,elementContent);
						
					}
					//====================================
					
					//获取对应的值，传入到构造nlp方法
					ConfiguredCrfPackageJson.createNlpJson(enNameContent,nlpSourceContent,computeContent);
				}else if ("block".equals(fieldEnName)) {
					//读取block的source
					String blockSourceContent = ExcelUtils.readContent(excel, isConfiguredRowNum, blockCellNum);
					
					
					
					//获取对应的值，传入到构造block方法
					ConfiguredCrfPackageJson.createBlockJson(enNameContent,blockSourceContent,computeContent);
				}else if ("PatientDetail".equals(fieldEnName)) {
					//读取patientDetail的source
					String patientDetailSourceContent = ExcelUtils.readContent(excel, isConfiguredRowNum, patientDetailCellNum);
					
					
					
					//获取对应的值，传入到构造PatientDetail方法
					ConfiguredCrfPackageJson.createPatientDetailJson(enNameContent,patientDetailSourceContent,computeContent);
				}
			}else {
				System.out.println("非配置字段！");
			}
		}
		
		ConfiguredCrfPackageJson.jsonToJsonFile(lastJson);
		System.out.println("ok");
	}
	
	/** 
	* @Title: selectCondition 
	* @Description: 构建selectCondition的json
	* @param: String keyName
	* @param: String selectContent
	* @param: String elementContent
	* @return: void
	* @throws 
	*/
	public static void selectCondition(String keyName,String selectContent,String elementContent) {
		//元素集合
		Map<String,String> map = ListAndStringUtils.valueSpiltBySemicolonToStringMap(elementContent);
		map.get("A");
		
		//selectContent表达式解析(没有括号情况)
		if (!selectContent.contains("(") || !selectContent.contains(")")) {
			for (int i = 0; i < selectContent.length(); i++) {
				char charAt = selectContent.charAt(i);
				System.out.println(charAt);
				
				
				if (!"&".equals(charAt) && !"|".equals(charAt)) {
					//元素
					map.get(charAt);
					
				}
			}
			
			
		}else if (selectContent.contains("(") && selectContent.contains(")")) {
			//selectContent表达式解析(有括号情况)
			
		}
		
	
	}
	
	
	/** 
	 * @Title: inCondition 
	 * @Description: 构建inCondition的json
	 * @param: String keyName
	 * @param: String inContent
	 * @param: String elementContent
	 * @return: void
	 * @throws 
	 */
	public static void inCondition(String keyName,String inContent,String elementContent) {
		
		
		
	}
	
	
	/** 
	* @Title: createNlpJson 
	* @Description: 创建nlp的block
	* @param: @param chNameContent
	* @param: @param nlpSourceContent
	* @param: @param computeContent 
	* @throws 
	*/
	public static void createNlpJson(String enNameContent,String nlpSourceContent,String computeContent) {
		//nlpJson
		JSONObject nlpSourceJson=new JSONObject();
		nlpSourceJson.put("nlp", ListAndStringUtils.valueSpiltBySemicolonToJSONArray(nlpSourceContent));
		
		//condition
		
		
		//定义map，放source、compute、condition
		Map<String,Object> map = new HashedMap<String, Object>();
		map.put("condition","");
		
		map.put("compute", ListAndStringUtils.valueSpiltBySemicolonToJSONArray(computeContent));
		map.put("source", nlpSourceJson);
		
		//blockJson
		JSONObject blockJson=new JSONObject();
		blockJson.put("block_"+enNameContent, map);
		
		//最后的json
		lastJson.put("blocks", blockJson);
	}

	
	/** 
	* @Title: createBlockJson 
	* @Description: 生成Block的json
	* @param: @param tableName :传入的表名
	* @throws 
	*/
	public static void createBlockJson(String enNameContent,String blockSourceContent,String computeContent) {
		//blockJson
		JSONObject blockSourceJson=new JSONObject();
		blockSourceJson.put("block", ListAndStringUtils.valueSpiltBySemicolonToJSONArray(blockSourceContent));
		
		//condition
		
		
		//定义map，放source、compute、condition
		Map<String,Object> map = new HashedMap<String, Object>();
		map.put("condition","");
		
		map.put("compute", ListAndStringUtils.valueSpiltBySemicolonToJSONArray(computeContent));
		map.put("source", blockSourceJson);
		
		//blockJson
		JSONObject blockJson=new JSONObject();
		blockJson.put("block_"+enNameContent, map);
		
		//最后的json
		lastJson.put("blocks", blockJson);
	}
	
	
	/** 
	 * @Title: createPatientDetailJson 
	 * @Description: 生成PatientDetail的json
	 * @param: @param tableName :传入的表名
	 * @throws 
	 */
	public static void createPatientDetailJson(String enNameContent,String patientDetailSourceContent,String computeContent) {
		//patientDetailSourceJson
		//patientDetail:visits
		JSONObject patientDetailSourceJson=new JSONObject();
		patientDetailSourceJson.put("patientDetail",patientDetailSourceContent);
		
		//condition
		
		
		//定义map，放source、compute、condition
		Map<String,Object> map = new HashedMap<String, Object>();
		map.put("condition", "");
		
		map.put("compute", ListAndStringUtils.valueSpiltBySemicolonToJSONArray(computeContent));
		map.put("source", patientDetailSourceJson);
		
		//blockJson
		JSONObject blockJson=new JSONObject();
		blockJson.put("block_"+enNameContent, map);
		
		//最后的json
		lastJson.put("blocks", blockJson);
	}
	
	
	/** 
	* @Title: jsonToJsonFile 
	* @Description: 将json生成json文件
	* @param: JSONObject lastJson 
	* @return: void
	* @throws 
	*/
	public static void jsonToJsonFile(JSONObject lastJson) {
		System.out.println(lastJson);
		String path="E:\\CRF重组\\自动工具\\工具\\test.json";
        File file=new File(path);
        try {
        	 Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        	 write.write(lastJson.toString());
        	 write.flush();
             write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

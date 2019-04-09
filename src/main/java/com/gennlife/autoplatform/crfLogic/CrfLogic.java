package com.gennlife.autoplatform.crfLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.mongodb.CrfdataOrPatientDetailMongodbDataProcess;
import com.gennlife.autoplatform.utils.ExcelUtils;
import com.gennlife.autoplatform.utils.JsonUtils;
import com.gennlife.autoplatform.utils.ListAndStringUtils;
import com.gennlife.interfaces.ManualEMRAutoCRFV2OfCrfAutoInterface;

/**
 * @Description: 测试crf逻辑,20180410:excel中增加firstPat列，为手动指定pat
 * @author: wangmiao
 * @Date: 2017年12月25日 下午5:31:22 
 */
public class CrfLogic {

	private static Logger logger = Logger.getLogger(CrfLogic.class); 
	
	private static final String patPath = "patient_info.patient_info_patient_sn";
	//存放批量的json，统一插入到mongodb(插入到数据库，要先判断pat是否存在 ,所以,以map形式存储)
	private static List<Map<String, JSONObject>> listMapJsons = new ArrayList<Map<String,JSONObject>>();
	//将行号和pat号对应，存到map里，方便后续写入，和批量请求
	private static Map<Integer, String> cellNumAndPatMap = new HashedMap<Integer, String>();
	

	/**
	* @Title: addFirstPat_insertDatasIntoPatientDetailAndPostAndWritePatIntoExcel 
	* @Description: 读excel相关配置，根据组装规则，组装数据并插入数据库，请求接口，将pat写入excel（增加firstPat列）
	* @param: @param excel
	* @param: @param path
	* @param: @throws JSONException :
	* @return: void
	* @throws 
	*/
	public static void addFirstPat_insertDatasIntoPatientDetailAndPostAndWritePatIntoExcel(final Excel excel,
			String path,final String mongodbIp,final String httpUrl,final String disease)throws Exception{
		Integer isConfiguredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否配置");
		Integer firstPatCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "firstPat");
		Integer reusePatRowNumCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "reusePatRowNum");
		Integer patientDetailCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "patientDetail");
		Integer insertContentCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "输入文本");
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, isConfiguredCellNum);
		logger.info("获取列-配置"); 
		for (int i = 1; i < list.size(); i++) {
			org.json.JSONObject baseJson = JsonUtils.readFileContentReturnJson(path);
			Map<Integer, String> map = list.get(i);
			Integer isConfiguredRowNum=null;
			String isConfiguredStr=null;
			for (Map.Entry<Integer, String> entry: map.entrySet()) {  
				isConfiguredRowNum=entry.getKey();
				isConfiguredStr=entry.getValue();
			}
			if ("是".equals(isConfiguredStr)) {
				String firstPatContent = ExcelUtils.readContent(excel, isConfiguredRowNum, firstPatCellNum);
				String reusePatContent = ExcelUtils.readContent(excel, isConfiguredRowNum, reusePatRowNumCellNum);
				String patientDetailContent = ExcelUtils.readContent(excel, isConfiguredRowNum, patientDetailCellNum);
				String insertContent = ExcelUtils.readContent(excel, isConfiguredRowNum, insertContentCellNum);
				if (firstPatContent!=null && firstPatContent.contains("pat")) {
					cellNumAndPatMap.put(isConfiguredRowNum, firstPatContent);
				}else if (firstPatContent==null || "".equals(firstPatContent)) {
					//===================firtPat为空的情况，仍是原有逻辑================================
					//只有满足以下才进行计算：组装数据(不复用pat，且数据源与输入文本不为空)(且reusePatContent为空，且其他两个不为空)
					if ((reusePatContent==null || "".equals(reusePatContent) || " ".equals(reusePatContent)) && 
							(patientDetailContent!=null && !"".equals(patientDetailContent)&& !" ".equals(patientDetailContent)) 
							&& (insertContent!=null && !"".equals(patientDetailContent) && !" ".equals(patientDetailContent))){
						String patContent="pat_"+UUID.randomUUID().toString().split("-")[0]+"_"+(isConfiguredRowNum+1);
						cellNumAndPatMap.put(isConfiguredRowNum, patContent);
						JSONObject newJSONObject = null;
						//============单个数据源处理============（目前是update方式，后续改成增加方式.....................）
						//=============
						//20180412对数据源进行处理：开头末尾去掉空格、换行符，结尾的分号
						patientDetailContent=ListAndStringUtils.replaceBlankAndLastSemicolon(patientDetailContent);
						//insertContent=ListAndStringUtils.replaceBlankAndLastSemicolon(insertContent);
						if (!patientDetailContent.contains(";")){
							String[] dealWithpatientDetailByDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContent);
							newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dealWithpatientDetailByDotToStrings, insertContent);
						}else if (patientDetailContent.contains(";") && insertContent.contains(";") ) {
							//============多个数据源处理============	
							String byAsteriskToString = ListAndStringUtils.dealWithpatientDetailByAsteriskToString(patientDetailContent);
							List<String> patientDetailContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(byAsteriskToString);
							List<String> insertContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(insertContent);
							for (int j = 0; j < patientDetailContents.size(); j++) {
								String[] dbyDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContents.get(j));
								newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dbyDotToStrings, insertContents.get(j));
							}
						} 
						Map<String,JSONObject> patAndJsonMap = new HashedMap<String, JSONObject>();
						patAndJsonMap.put(patContent, newJSONObject);
						listMapJsons .add(patAndJsonMap);
					}
					//========原有逻辑结束================================
				}
			}
		}
		/**
		//测试用:查看cellNumAndPatMap
		for (Map.Entry<Integer, String>  entry: cellNumAndPatMap.entrySet()) {  
			entry.getKey();
			entry.getValue();
			System.out.println("cellNumAndPatMap"+entry.getKey()+"-----"+entry.getValue());
		}
		//测试有用：查看封装的listMapJsons
		for (int i = 0; i < listMapJsons.size(); i++) {
			for (Entry<String, JSONObject>  entry: listMapJsons.get(i).entrySet()) {  
				entry.getKey();
				entry.getValue();
				System.out.println("listMapJsons:"+entry.getKey()+"-----"+entry.getValue());
			}
		}
		*/
		ExecutorService threadPool =Executors.newFixedThreadPool(2); 
		Future<String> futureTest = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				logger.info("callableTest线程开始");
					CrfdataOrPatientDetailMongodbDataProcess
						.insertDatasIntoPatientDetailMongodb(mongodbIp,listMapJsons);
				return "success";
			}
		});
		Future<String> futureDevelop = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				logger.info("callableDevelop线程开始");
				CrfdataOrPatientDetailMongodbDataProcess
					.insertDatasIntoPatientDetailMongodbOfDevelop("10.0.0.166",listMapJsons);
				return "success";
			}
		});
		try {  
			futureTest.get();  
			futureDevelop.get();
		} catch (InterruptedException e) {  
			e.printStackTrace();  
		} 
		if (futureTest.isDone() && futureDevelop.isDone()) {
			Future<String> excelResults = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.writePatIntoExcel(excel, cellNumAndPatMap);
					return "success";
				}
			});
			Future<String> interfaceResults = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.requestCrfAutoInterfaceByPat(cellNumAndPatMap, httpUrl, disease);
					return "success";
				}
			});
			try {  
				interfaceResults.get();  
				excelResults.get();
			} catch (InterruptedException e) {  
				e.printStackTrace();  
			} 
			if (interfaceResults.isDone() && excelResults.isDone()) {
				logger.info("ok");
	            threadPool.shutdown();
			} else {
				logger.info("Error");
			} 
		}
	}
	

	/**
	* @Title: insertDatasIntoPatientDetailAndPostAndWritePatIntoExcel 
	* @Description: 读excel相关配置，根据组装规则，组装数据并插入数据库，请求接口，将pat写入excel
	* @param: @param excel
	* @param: @param path
	* @param: @throws JSONException :
	* @return: void
	* @throws 
	*/
	public static void insertDatasIntoPatientDetailAndPostAndWritePatIntoExcel(final Excel excel,
			String path,final String mongodbIp,final String httpUrl,final String disease)throws Exception{
		Integer isConfiguredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否配置");
		Integer reusePatRowNumCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "reusePatRowNum");
		Integer patientDetailCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "patientDetail");
		Integer insertContentCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "输入文本");
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, isConfiguredCellNum);
		for (int i = 1; i < list.size(); i++) {
			org.json.JSONObject baseJson = JsonUtils.readFileContentReturnJson(path);
			Map<Integer, String> map = list.get(i);
			Integer isConfiguredRowNum=null;
			String isConfiguredStr=null;
			for (Map.Entry<Integer, String> entry: map.entrySet()) {  
				isConfiguredRowNum=entry.getKey();
				isConfiguredStr=entry.getValue();
			}
			if ("是".equals(isConfiguredStr)) {
				String reusePatContent = ExcelUtils.readContent(excel, isConfiguredRowNum, reusePatRowNumCellNum);
				String patientDetailContent = ExcelUtils.readContent(excel, isConfiguredRowNum, patientDetailCellNum);
				String insertContent = ExcelUtils.readContent(excel, isConfiguredRowNum, insertContentCellNum);
				if ((reusePatContent==null ||"".equals(reusePatContent)) && patientDetailContent!=null && insertContent!=null) {
					String patContent="pat_"+UUID.randomUUID().toString().split("-")[0]+"_"+(isConfiguredRowNum+1);
					cellNumAndPatMap.put(isConfiguredRowNum, patContent);
					JSONObject newJSONObject = null;
					//============单个数据源处理============（目前是update方式，后续改成增加方式）
					if (!patientDetailContent.contains(";")) {
						String[] dealWithpatientDetailByDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContent);
						newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dealWithpatientDetailByDotToStrings, insertContent);
					}else if (patientDetailContent.contains(";") && insertContent.contains(";")) {
						//============多个数据源处理============	
						String byAsteriskToString = ListAndStringUtils.dealWithpatientDetailByAsteriskToString(patientDetailContent);
						List<String> patientDetailContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(byAsteriskToString);
						List<String> insertContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(insertContent);
						for (int j = 0; j < patientDetailContents.size(); j++) {
							String[] dbyDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContents.get(j));
							newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dbyDotToStrings, insertContents.get(j));
						}
					} 
					Map<String,JSONObject> patAndJsonMap =new  HashedMap<String, JSONObject>();
					patAndJsonMap.put(patContent, newJSONObject);
					listMapJsons .add(patAndJsonMap);
				}
			}
		}
		//测试用
		for (Map.Entry<Integer, String>  entry: cellNumAndPatMap.entrySet()) {  
			entry.getKey();
			entry.getValue();
			System.out.println(entry.getKey()+"-----"+entry.getValue());
		}
		
		ExecutorService threadPool =Executors.newFixedThreadPool(2); 
		Future<String> futureTest = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				logger.info("callableTest线程开始");
					CrfdataOrPatientDetailMongodbDataProcess
						.insertDatasIntoPatientDetailMongodb(mongodbIp,listMapJsons);
				return "success";
			}
		});
		Future<String> futureDevelop = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				logger.info("callableDevelop线程开始");
				CrfdataOrPatientDetailMongodbDataProcess
					.insertDatasIntoPatientDetailMongodbOfDevelop("10.0.0.166",listMapJsons);
				return "success";
			}
		});
		try {  
			futureTest.get();  
			futureDevelop.get();
		} catch (InterruptedException e) {  
			e.printStackTrace();  
		} 
		
		if (futureTest.isDone() && futureDevelop.isDone()) {
			Future<String> interfaceResults = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.requestCrfAutoInterfaceByPat(cellNumAndPatMap, httpUrl, disease);
					return "success";
				}
			});
			
			Future<String> excelResults = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.writePatIntoExcel(excel, cellNumAndPatMap);
					return "success";
				}
			});
			try {  
				interfaceResults.get();  
				excelResults.get();
			} catch (InterruptedException e) {  
				e.printStackTrace();  
			} 
			if (interfaceResults.isDone() && excelResults.isDone()) {
				logger.info("ok");
	            threadPool.shutdown();
			} else {
				logger.info("Error");
			} 
		}
	}
	
	
	/**
	* @Title: queryCrfdataByPatAndWriteResults 
	* @Description:  去crfdata数据库，查询对应数据，返回结果和行号的map
	* @param: @param excel :
	* @return: void
	* @throws 
	*/
	public static void queryCrfdataByPatAndWriteResults(Excel excel,String mongodbIp) throws JSONException{
		Map<Integer,Map<String, String>> rowNumAndpatCrfdataMapMap = new HashedMap<Integer, Map<String,String>>();
		Integer isConfiguredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否配置");
		Integer reusePatRowNumCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "reusePatRowNum");
		Integer crfdataCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "crfdata");
		Integer patCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "pat");
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, isConfiguredCellNum);
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			Integer isConfiguredRowNum=null;
			String isConfiguredStr=null;
			for (Map.Entry<Integer, String> entry: map.entrySet()) {  
				isConfiguredRowNum=entry.getKey();
				isConfiguredStr=entry.getValue();
			}
			if ("是".equals(isConfiguredStr)) {
				Map<String, String> cellNumAndCrfdataValueMap = new HashedMap<String, String>();
				String reusePatRowNumContent = ExcelUtils.readContent(excel, isConfiguredRowNum, reusePatRowNumCellNum);
				String patContent = ExcelUtils.readContent(excel, isConfiguredRowNum, patCellNum);
				String crfdataContent = ExcelUtils.readContent(excel, isConfiguredRowNum, crfdataCellNum);
				if ((reusePatRowNumContent==null || "".equals(reusePatRowNumContent)) && patContent!=null && crfdataContent!=null){
					cellNumAndCrfdataValueMap.put(patContent, crfdataContent);
					rowNumAndpatCrfdataMapMap.put(isConfiguredRowNum, cellNumAndCrfdataValueMap);
				}else if (reusePatRowNumContent!=null && !"".equals(reusePatRowNumContent) && crfdataContent!=null) {
					String reusePatContent = ExcelUtils.readContent(excel, Integer.valueOf(reusePatRowNumContent)-1, patCellNum);
					if (reusePatContent!=null) {
						cellNumAndCrfdataValueMap.put(reusePatContent, crfdataContent);
						rowNumAndpatCrfdataMapMap.put(isConfiguredRowNum, cellNumAndCrfdataValueMap);
					}
				}
			}
		}
		try {
			Map<Integer,String> rowNumAndcrfdataMap = CrfdataOrPatientDetailMongodbDataProcess
					.queryDatasOfCrfdataMongodb(mongodbIp,rowNumAndpatCrfdataMapMap);
			CrfLogic.writeCrfdataIntoExcel(excel, rowNumAndcrfdataMap);
		} catch (Exception e) {
			logger.info("出错了");
			e.printStackTrace();
		}
		logger.info("ok");
	}
		

	/** 
	* @Title: writeCrfdataIntoExcel 
	* @Description: 将生成的crf结果写入到excel
	* @param: @param excel
	* @param: @param rowNumAndcrfdataMap :
	* @return: void
	* @throws 
	*/
	public static void writeCrfdataIntoExcel(Excel excel,Map<Integer, String> rowNumAndcrfdataMap ){
		Integer outputCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "实际输出");
		Integer rowNum=null;
		String crfdata=null;
		for (Entry<Integer, String> entry: rowNumAndcrfdataMap.entrySet()) {  
			rowNum=entry.getKey();
			crfdata=entry.getValue();
			ExcelUtils.writeAndSaveContent(excel, crfdata.toString(), rowNum, outputCellNum);
		}
		logger.info("crfdata写入excel完成...");
	}
	
	
	/** 
	* @Title: requestCrfAutoInterfaceByPat 
	* @Description: 批量请求crf组装接口，返回接口处理的结果
	* @param: @param cellNumAndPatMap
	* @param: @return
	* @param: @throws JSONException :
	* @return: JSONObject
	* @throws 
	*/
	public static JSONObject requestCrfAutoInterfaceByPat(Map<Integer, String> cellNumAndPatMap,
			String httpUrl,String disease) throws JSONException {
		String pat=null;
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, String> entry: cellNumAndPatMap.entrySet()) {  
			pat=entry.getValue();
			String str = "\""+pat+"\"";
			sb.append(str.trim()).append(",");
		}
		String patStrs = sb.deleteCharAt(sb.length() - 1).toString();
		String str = ManualEMRAutoCRFV2OfCrfAutoInterface.getResultsByPostMethod(httpUrl, disease, patStrs);
		logger.info("请求接口end...");
		return new JSONObject(str);
	}
	
	
	/** 
	* @Title: writePatIntoExcel 
	* @Description: 将生成的pat号写入到excel
	* @param: @param excel
	* @param: @param cellNumAndPatMap将pat写入excel
	* @return: void
	* @throws 
	*/
	public static void writePatIntoExcel(Excel excel,Map<Integer, String> cellNumAndPatMap){
		Integer patCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "pat");
		Integer rowNum=null;
		String pat=null;
		for (Map.Entry<Integer, String> entry: cellNumAndPatMap.entrySet()) {  
			rowNum=entry.getKey();
			pat=entry.getValue();
			ExcelUtils.writeAndSaveContent(excel, pat, rowNum, patCellNum);
		}
		logger.info("pat写入excel完成...");
	}
}

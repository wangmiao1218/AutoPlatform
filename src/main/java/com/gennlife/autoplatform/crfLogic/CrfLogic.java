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
		//增加firstPat列，为手工指定pat，存在则直接存入到，map中
		Integer firstPatCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "firstPat");
		Integer reusePatRowNumCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "reusePatRowNum");
		Integer patientDetailCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "patientDetail");
		Integer insertContentCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "输入文本");
		
		//获取isConfiguredCellNum一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, isConfiguredCellNum);
		logger.info("获取列-配置"); 
		//获取是否配置的列，开始遍历
		for (int i = 1; i < list.size(); i++) {
			//放到上面设置变量，则不循环，但是每个值都保存了
			//读取基础文本文件，并转为json
			org.json.JSONObject baseJson = JsonUtils.readFileContentReturnJson(path);
			
			Map<Integer, String> map = list.get(i);
			//定义行号和内容
			Integer isConfiguredRowNum=null;
			String isConfiguredStr=null;
			for (Map.Entry<Integer, String> entry: map.entrySet()) {  
				isConfiguredRowNum=entry.getKey();
				isConfiguredStr=entry.getValue();
			}
			
			if ("是".equals(isConfiguredStr)) {
				//获取并行的四列，进行判断
				String firstPatContent = ExcelUtils.readContent(excel, isConfiguredRowNum, firstPatCellNum);
				String reusePatContent = ExcelUtils.readContent(excel, isConfiguredRowNum, reusePatRowNumCellNum);
				String patientDetailContent = ExcelUtils.readContent(excel, isConfiguredRowNum, patientDetailCellNum);
				String insertContent = ExcelUtils.readContent(excel, isConfiguredRowNum, insertContentCellNum);

				//增加新逻辑，若firstPat存在且不为空，则把firstPat统一写入到pat列，方便后续查询
				if (firstPatContent!=null && firstPatContent.contains("pat")) {
					//若存在，不涉及到封装json，直接保存pat即可
					cellNumAndPatMap.put(isConfiguredRowNum, firstPatContent);
					
				}else if (firstPatContent==null || "".equals(firstPatContent)) {
					//===================firtPat为空的情况，仍是原有逻辑================================
					//只有满足以下才进行计算：组装数据(不复用pat，且数据源与输入文本不为空)(且reusePatContent为空，且其他两个不为空)
					if ((reusePatContent==null || "".equals(reusePatContent) || " ".equals(reusePatContent)) && 
							(patientDetailContent!=null && !"".equals(patientDetailContent)&& !" ".equals(patientDetailContent)) 
							&& (insertContent!=null && !"".equals(patientDetailContent) && !" ".equals(patientDetailContent))){
						//pat编号
						String patContent="pat_"+UUID.randomUUID().toString().split("-")[0]+"_"+(isConfiguredRowNum+1);
						//存行号和pat
						cellNumAndPatMap.put(isConfiguredRowNum, patContent);
						JSONObject newJSONObject = null;
						//============单个数据源处理============（目前是update方式，后续改成增加方式.....................）
						//=============
						//20180412对数据源进行处理：开头末尾去掉空格、换行符，结尾的分号
						patientDetailContent=ListAndStringUtils.replaceBlankAndLastSemicolon(patientDetailContent);
						//对插入的数据进行去掉空格等，但实际使用时，对内容不允许去掉，所以取消
						//insertContent=ListAndStringUtils.replaceBlankAndLastSemicolon(insertContent);
						//============
						if (!patientDetailContent.contains(";")){
							//对数据源patientDetail进行处理
							String[] dealWithpatientDetailByDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContent);
							//解析json，将pat、和输入文本插入到json中
							newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dealWithpatientDetailByDotToStrings, insertContent);
						}else if (patientDetailContent.contains(";") && insertContent.contains(";") ) {
							//============多个数据源处理============	
							//处理patientDetail，然后用英文；分割
							String byAsteriskToString = ListAndStringUtils.dealWithpatientDetailByAsteriskToString(patientDetailContent);
							List<String> patientDetailContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(byAsteriskToString);
							//处理insertContent，然后用;分割
							List<String> insertContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(insertContent);
							
							//循环处理json放入一个人的数据里
							for (int j = 0; j < patientDetailContents.size(); j++) {
								//=======================
								//后续会加是否重复的判断,第几次出现
								//int count = Collections.frequency(patientDetailContents, patientDetailContents.get(j));
								
								//=======================
								String[] dbyDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContents.get(j));
								newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dbyDotToStrings, insertContents.get(j));
							}
						} 
						//添加到listJsons（map只有一个值，方便后面遍历）
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
		//=====================线程池方法====================================
		//创建线程池并返回ExecutorService实例 
		ExecutorService threadPool =Executors.newFixedThreadPool(2); 
		
		// 执行任务
		//将新的json的list插入mongodb的patientDetail中
		Future<String> futureTest = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("callableTest线程开始");
					CrfdataOrPatientDetailMongodbDataProcess
						.insertDatasIntoPatientDetailMongodb(mongodbIp,listMapJsons);
				return "success";
			}
		});
		//同时加写入开发库
		Future<String> futureDevelop = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("callableDevelop线程开始");
				CrfdataOrPatientDetailMongodbDataProcess
					.insertDatasIntoPatientDetailMongodbOfDevelop("10.0.0.166",listMapJsons);
				return "success";
			}
		});
		
		//天坑坑坑坑……，不知道为什么要加这个，明明之前都好使，啊啊啊啊啊啊啊
		try {  
			futureTest.get();  
			futureDevelop.get();
		} catch (InterruptedException e) {  
			e.printStackTrace();  
		} 
		
        //判断入库完成,天坑坑坑坑……，不知道为什么改成这个，明明之前都好使，啊啊啊啊啊啊啊
		//if ("success".equals(futureTest) && "success".equals(futureDevelop)) {
		if (futureTest.isDone() && futureDevelop.isDone()) {
			//将pat写入excel
			Future<String> excelResults = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.writePatIntoExcel(excel, cellNumAndPatMap);
					return "success";
				}
			});
			
			//批量请求接口
			Future<String> interfaceResults = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.requestCrfAutoInterfaceByPat(cellNumAndPatMap, httpUrl, disease);
					return "success";
				}
			});
			//天坑坑坑坑……，不知道为什么要加这个，明明之前都好使，啊啊啊啊啊啊啊
			try {  
				interfaceResults.get();  
				excelResults.get();
			} catch (InterruptedException e) {  
				e.printStackTrace();  
			} 
			
			//if ("success".equals(interfaceResults) && "success".equals(excelResults)) {
			if (interfaceResults.isDone() && excelResults.isDone()) {
				System.out.println("ok");
				 //关闭线程池和服务  
	            threadPool.shutdown();
			} else {
	        	System.out.println("Error");
			} 
		}
		//======================线程池方法结束===================================
		
		
		/*
		//======================普通多线程方法===================================
		//将新的json的list插入mongodb的patientDetail中
		String callableTest = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("callableTest");
				CrfdataOrPatientDetailMongodbDataProcess
					.insertDatasIntoPatientDetailMongodb(mongodbIp,listMapJsons);
				return "success";
			}
		}.call();
		//同时加写入开发库
		String callableDevelop = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("callableDevelop");
				CrfdataOrPatientDetailMongodbDataProcess
					.insertDatasIntoPatientDetailMongodbOfDevelop("10.0.0.166",listMapJsons);
				return "success";
			}
		}.call();
		
		//判断入库完成
		if ("success".equals(callableTest) && "success".equals(callableDevelop)) {
			//批量请求接口
			String callableAuto = new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.requestCrfAutoInterfaceByPat(cellNumAndPatMap, httpUrl, disease);
					return "success";
				}
			}.call();
			//将pat写入excel
			String callableExcel = new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.writePatIntoExcel(excel, cellNumAndPatMap);
					return "success";
				}
			}.call();
			
			if ("success".equals(callableAuto) && "success".equals(callableExcel)) {
				System.out.println("ok");
			}
		}
		//======================普通多线程方法结束===================================
		*/
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
		
		//获取isConfiguredCellNum一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, isConfiguredCellNum);
		//获取是否配置的列，开始遍历
		for (int i = 1; i < list.size(); i++) {
			//放到上面设置变量，则不循环，但是每个值都保存了
			//读取基础文本文件，并转为json
			org.json.JSONObject baseJson = JsonUtils.readFileContentReturnJson(path);
			
			Map<Integer, String> map = list.get(i);
			//定义行号和内容
			Integer isConfiguredRowNum=null;
			String isConfiguredStr=null;
			for (Map.Entry<Integer, String> entry: map.entrySet()) {  
				isConfiguredRowNum=entry.getKey();
				isConfiguredStr=entry.getValue();
			}
			
			if ("是".equals(isConfiguredStr)) {
				//获取并行的三列，进行判断
				String reusePatContent = ExcelUtils.readContent(excel, isConfiguredRowNum, reusePatRowNumCellNum);
				String patientDetailContent = ExcelUtils.readContent(excel, isConfiguredRowNum, patientDetailCellNum);
				String insertContent = ExcelUtils.readContent(excel, isConfiguredRowNum, insertContentCellNum);

				//只有满足以下才进行计算(不复用pat，且数据源与输入文本不为空)
				if ((reusePatContent==null ||"".equals(reusePatContent)) && patientDetailContent!=null && insertContent!=null) {
					//pat编号
					String patContent="pat_"+UUID.randomUUID().toString().split("-")[0]+"_"+(isConfiguredRowNum+1);
					//存行号和pat
					cellNumAndPatMap.put(isConfiguredRowNum, patContent);
					JSONObject newJSONObject = null;
					//============单个数据源处理============（目前是update方式，后续改成增加方式）
					if (!patientDetailContent.contains(";")) {
						//对数据源patientDetail进行处理
						String[] dealWithpatientDetailByDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContent);
						//解析json，将pat、和输入文本插入到json中
						newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dealWithpatientDetailByDotToStrings, insertContent);
					}else if (patientDetailContent.contains(";") && insertContent.contains(";")) {
						//============多个数据源处理============	
						//处理patientDetail，然后用；分割
						String byAsteriskToString = ListAndStringUtils.dealWithpatientDetailByAsteriskToString(patientDetailContent);
						List<String> patientDetailContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(byAsteriskToString);
						//处理insertContent，然后用;分割
						List<String> insertContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(insertContent);
						
						//循环处理json放入一个人的数据里
						for (int j = 0; j < patientDetailContents.size(); j++) {
							//=======================
							//后续会加是否重复的判断,第几次出现
							//int count = Collections.frequency(patientDetailContents, patientDetailContents.get(j));
							
							//=======================
							String[] dbyDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContents.get(j));
							newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dbyDotToStrings, insertContents.get(j));
						}
					} 
					//添加到listJsons（map只有一个值，方便后面遍历）
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
		
		//=====================线程池方法====================================
		//创建线程池并返回ExecutorService实例 
		ExecutorService threadPool =Executors.newFixedThreadPool(2); 
		
		// 执行任务
		//将新的json的list插入mongodb的patientDetail中
		Future<String> futureTest = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("callableTest线程开始");
					CrfdataOrPatientDetailMongodbDataProcess
						.insertDatasIntoPatientDetailMongodb(mongodbIp,listMapJsons);
				return "success";
			}
		});
		//同时加写入开发库
		Future<String> futureDevelop = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("callableDevelop线程开始");
				CrfdataOrPatientDetailMongodbDataProcess
					.insertDatasIntoPatientDetailMongodbOfDevelop("10.0.0.166",listMapJsons);
				return "success";
			}
		});
		
		//天坑坑坑坑……，不知道为什么要加这个，明明之前都好使，啊啊啊啊啊啊啊
		try {  
			futureTest.get();  
			futureDevelop.get();
		} catch (InterruptedException e) {  
			e.printStackTrace();  
		} 
		
        //判断入库完成,天坑坑坑坑……，不知道为什么改成这个，明明之前都好使，啊啊啊啊啊啊啊
		//if ("success".equals(futureTest) && "success".equals(futureDevelop)) {
		if (futureTest.isDone() && futureDevelop.isDone()) {
			//批量请求接口
			Future<String> interfaceResults = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.requestCrfAutoInterfaceByPat(cellNumAndPatMap, httpUrl, disease);
					return "success";
				}
			});
			
			//将pat写入excel
			Future<String> excelResults = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.writePatIntoExcel(excel, cellNumAndPatMap);
					return "success";
				}
			});
			
			//天坑坑坑坑……，不知道为什么要加这个，明明之前都好使，啊啊啊啊啊啊啊
			try {  
				interfaceResults.get();  
				excelResults.get();
			} catch (InterruptedException e) {  
				e.printStackTrace();  
			} 
			
			//if ("success".equals(interfaceResults) && "success".equals(excelResults)) {
			if (interfaceResults.isDone() && excelResults.isDone()) {
				System.out.println("ok");
				 //关闭线程池和服务  
	            threadPool.shutdown();
			} else {
	        	System.out.println("Error");
			} 
		}
		//======================线程池方法结束===================================
		
		
		/*
		//======================普通多线程方法===================================
		//将新的json的list插入mongodb的patientDetail中
		String callableTest = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("callableTest");
				CrfdataOrPatientDetailMongodbDataProcess
					.insertDatasIntoPatientDetailMongodb(mongodbIp,listMapJsons);
				return "success";
			}
		}.call();
		//同时加写入开发库
		String callableDevelop = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("callableDevelop");
				CrfdataOrPatientDetailMongodbDataProcess
					.insertDatasIntoPatientDetailMongodbOfDevelop("10.0.0.166",listMapJsons);
				return "success";
			}
		}.call();
		
		//判断入库完成
		if ("success".equals(callableTest) && "success".equals(callableDevelop)) {
			//批量请求接口
			String callableAuto = new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.requestCrfAutoInterfaceByPat(cellNumAndPatMap, httpUrl, disease);
					return "success";
				}
			}.call();
			//将pat写入excel
			String callableExcel = new Callable<String>() {
				@Override
				public String call() throws Exception {
					CrfLogic.writePatIntoExcel(excel, cellNumAndPatMap);
					return "success";
				}
			}.call();
			
			if ("success".equals(callableAuto) && "success".equals(callableExcel)) {
				System.out.println("ok");
			}
		}
		//======================普通多线程方法结束===================================
		*/
	}
	
	
	/**
	* @Title: queryCrfdataByPatAndWriteResults 
	* @Description:  去crfdata数据库，查询对应数据，返回结果和行号的map
	* @param: @param excel :
	* @return: void
	* @throws 
	*/
	public static void queryCrfdataByPatAndWriteResults(Excel excel,String mongodbIp) throws JSONException{
		//因为key有重复，不用IdentityHashMap，则放到list中
		//将pat和crfdata路径，存到map里，再讲行号和map封装成map，方便查询
		Map<Integer,Map<String, String>> rowNumAndpatCrfdataMapMap = new HashedMap<Integer, Map<String,String>>();
		
		Integer isConfiguredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否配置");
		Integer reusePatRowNumCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "reusePatRowNum");
		Integer crfdataCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "crfdata");
		Integer patCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "pat");

		//获取isConfiguredCellNum一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, isConfiguredCellNum);
		//获取是否配置的列，开始遍历
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			//定义是否填的行号和内容
			Integer isConfiguredRowNum=null;
			String isConfiguredStr=null;
			for (Map.Entry<Integer, String> entry: map.entrySet()) {  
				isConfiguredRowNum=entry.getKey();
				isConfiguredStr=entry.getValue();
			}
			if ("是".equals(isConfiguredStr)) {
				//因为key可能相同，value可能不同，所以在for里新建map，最后再放到list中
				Map<String, String> cellNumAndCrfdataValueMap = new HashedMap<String, String>();
				//获取并行的三列，进行判断
				String reusePatRowNumContent = ExcelUtils.readContent(excel, isConfiguredRowNum, reusePatRowNumCellNum);
				String patContent = ExcelUtils.readContent(excel, isConfiguredRowNum, patCellNum);
				String crfdataContent = ExcelUtils.readContent(excel, isConfiguredRowNum, crfdataCellNum);
				//只有满足以下才进行查询
				if ((reusePatRowNumContent==null || "".equals(reusePatRowNumContent)) && patContent!=null && crfdataContent!=null){
					//存pat和crfdata源
					cellNumAndCrfdataValueMap.put(patContent, crfdataContent);
					rowNumAndpatCrfdataMapMap.put(isConfiguredRowNum, cellNumAndCrfdataValueMap);
				}else if (reusePatRowNumContent!=null && !"".equals(reusePatRowNumContent) && crfdataContent!=null) {
					//不为空，则复用pat，直接存复用的pat和crfdata
					//根据reusePatRowNum，查pat（行号要减1，因为之前方便看，增加了1）
					String reusePatContent = ExcelUtils.readContent(excel, Integer.valueOf(reusePatRowNumContent)-1, patCellNum);
					//判断是否为空
					if (reusePatContent!=null) {
						cellNumAndCrfdataValueMap.put(reusePatContent, crfdataContent);
						rowNumAndpatCrfdataMapMap.put(isConfiguredRowNum, cellNumAndCrfdataValueMap);
					}
				}
			}
		}
		
		//传入查询crfdata的方法，返回行号和查询结果的map
		try {
			Map<Integer,String> rowNumAndcrfdataMap = CrfdataOrPatientDetailMongodbDataProcess
					.queryDatasOfCrfdataMongodb(mongodbIp,rowNumAndpatCrfdataMapMap);
			//将crfdata结果写入excel
			CrfLogic.writeCrfdataIntoExcel(excel, rowNumAndcrfdataMap);
		} catch (Exception e) {
			System.out.println("出错了");
			e.printStackTrace();
		}
		
		System.out.println("ok");
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
		System.out.println("crfdata写入excel完成...");
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
		//处理map,将pat封装成list
		String pat=null;
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, String> entry: cellNumAndPatMap.entrySet()) {  
			pat=entry.getValue();
			String str = "\""+pat+"\"";
			sb.append(str.trim()).append(",");
		}
		String patStrs = sb.deleteCharAt(sb.length() - 1).toString();
		String str = ManualEMRAutoCRFV2OfCrfAutoInterface.getResultsByPostMethod(httpUrl, disease, patStrs);
		System.out.println("请求接口end...");
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
		System.out.println("pat写入excel完成...");
	}
	
	
	/** 
	 * @Title: readExcelReturnJsonMapList (测试用)
	 * @Description: 读excel相关配置，根据组装规则，返回json的list，方便后续插入数据库
	 * @param: @param excel
	 * @param: @param path
	 * @return: List<Map<String, JSONObject>>
	 * @throws 
	 */
	public static List<Map<String,JSONObject>> readExcelReturnJsonMapList(Excel excel,String path) throws JSONException {
		//存放批量的json，统一插入到mongodb
		List<Map<String, JSONObject>> returnListMapJsons = new ArrayList<Map<String,JSONObject>>();
		Integer isConfiguredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否配置");
		Integer reusePatNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "reusePatRowNum");
		Integer patientDetailCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "patientDetail");
		Integer insertContentCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "输入文本");
		
		//获取isConfiguredCellNum一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, isConfiguredCellNum);
		//获取是否配置的列，开始遍历
		for (int i = 1; i < list.size(); i++) {
			//放到上面设置变量，则不循环，但是每个值都保存了
			//读取基础文本文件，并转为json
			org.json.JSONObject baseJson = JsonUtils.readFileContentReturnJson(path);
			
			Map<Integer, String> map = list.get(i);
			//定义是否填的行号和内容
			Integer isConfiguredRowNum=null;
			String isConfiguredStr=null;
			for (Map.Entry<Integer, String> entry: map.entrySet()) {  
				isConfiguredRowNum=entry.getKey();
				isConfiguredStr=entry.getValue();
			}
			
			if ("是".equals(isConfiguredStr)) {
				//获取并行的三列，进行判断
				String reusePatContent = ExcelUtils.readContent(excel, isConfiguredRowNum, reusePatNum);
				String patientDetailContent = ExcelUtils.readContent(excel, isConfiguredRowNum, patientDetailCellNum);
				String insertContent = ExcelUtils.readContent(excel, isConfiguredRowNum, insertContentCellNum);
				
				//只有满足以下才进行计算(不服用pat，且数据源与输入文本不为空)
				if (reusePatContent==null && patientDetailContent!=null && insertContent!=null) {
					//pat编号
					String patContent="pat_"+(isConfiguredRowNum+1);
					JSONObject newJSONObject = null;
					//============单个数据源处理============（目前是update方式，后续改成增加方式）
					if (!patientDetailContent.contains(";")) {
						//对数据源patientDetail进行处理
						String[] dealWithpatientDetailByDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContent);
						//解析json，将pat、和输入文本插入到json中
						newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dealWithpatientDetailByDotToStrings, insertContent);
					}else if (patientDetailContent.contains(";") && insertContent.contains(";")) {
						//============多个数据源处理============	
						//处理patientDetail，然后用；分割
						String byAsteriskToString = ListAndStringUtils.dealWithpatientDetailByAsteriskToString(patientDetailContent);
						List<String> patientDetailContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(byAsteriskToString);
						//处理insertContent，然后用;分割
						List<String> insertContents = ListAndStringUtils.dealWithpatientDetailBySemicolonToStrings(insertContent);
						//循环处理json放入一个人的数据里
						for (int j = 0; j < patientDetailContents.size(); j++) {
							//=======================
							//后续会加是否重复的判断
							
							//=======================
							String[] dbyDotToStrings = ListAndStringUtils.dealWithpatientDetailByDotToStrings(patientDetailContents.get(j));
							newJSONObject = JsonUtils.updatePatAndValueReturnNewJSONObject(baseJson, patPath, patContent, dbyDotToStrings, insertContents.get(j));
						}
					} 
					//添加到listJsons（map只有一个值，方便后面遍历）
					Map<String,JSONObject> patAndJsonMap =new  HashedMap<String, JSONObject>();
					patAndJsonMap.put(patContent, newJSONObject);
					returnListMapJsons.add(patAndJsonMap);
				}
			}else {
				//System.out.println("非配置字段！");
			}
		}
		return returnListMapJsons;
	}
	
	
	/** 
	* @Title: readExcelReturnCellNumAndPatMap (测试用)
	* @Description: 读excel相关配置，返回行号和pat的
	* @param: @param excel
	* @param: @return
	* @param: @throws JSONException
	* @return: Map<Integer,String>
	* @throws 
	*/
	public static Map<Integer, String> readExcelReturnCellNumAndPatMap(Excel excel) throws JSONException {
		//将行号和pat号对应，存到map里，方便后续写入，和批量请求
		Map<Integer, String> returnCellNumAndPatMap = new HashedMap<Integer, String>();
		
		Integer isConfiguredCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "是否配置");
		Integer reusePatNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "reusePatRowNum");
		Integer patientDetailCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "patientDetail");
		Integer insertContentCellNum = ExcelUtils.searchKeyWordOfOneLine(excel, 0, "输入文本");
	
		//获取isConfiguredCellNum一列（用readExcelOfListReturnListMap，因为有重复值）(除表头)
		List<Map<Integer,String>> list = ExcelUtils.readExcelOfListReturnListMap(excel, isConfiguredCellNum);
		//获取是否配置的列，开始遍历
		for (int i = 1; i < list.size(); i++) {
			Map<Integer, String> map = list.get(i);
			//定义是否填的行号和内容
			Integer isConfiguredRowNum=null;
			String isConfiguredStr=null;
			for (Map.Entry<Integer, String> entry: map.entrySet()) {  
				isConfiguredRowNum=entry.getKey();
				isConfiguredStr=entry.getValue();
			}
			
			if ("是".equals(isConfiguredStr)) {
				//获取并行的三列，进行判断
				String reusePatContent = ExcelUtils.readContent(excel, isConfiguredRowNum, reusePatNum);
				String patientDetailContent = ExcelUtils.readContent(excel, isConfiguredRowNum, patientDetailCellNum);
				String insertContent = ExcelUtils.readContent(excel, isConfiguredRowNum, insertContentCellNum);
				
				//只有满足以下才进行计算(不服用pat，且数据源与输入文本不为空)
				if (reusePatContent==null && patientDetailContent!=null && insertContent!=null) {
					String patContent="pat_"+(isConfiguredRowNum+1);
					//存行号和pat
					returnCellNumAndPatMap.put(isConfiguredRowNum, patContent);
				}
			}
		}
		return returnCellNumAndPatMap;
	}
	
	
}

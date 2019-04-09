package com.gennlife.autoplatform.mongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.gennlife.autoplatform.utils.JsonUtils;
import com.gennlife.autoplatform.utils.MongodbJDBCUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;

/**
 * @Description:mongodb的数据处理
 * @author: wangmiao
 * @Date: 2017年12月18日 下午2:41:11 
 */
public class CrfdataOrPatientDetailMongodbDataProcess {
	
	private static Logger logger = Logger.getLogger(CrfdataOrPatientDetailMongodbDataProcess.class); 
	
	/** 
	* @Title: queryDatasOfCrfdataMongodb 
	* @Description: 根据传入的mapMap传入到数据库进行查询，返回map封装行号和查询结果
	* @param: @param rowNumAndpatCrfdataMapMap
	* @param: @return
	* @param: @throws JSONException 
	* @return: Map<Integer,org.bson.BSONObject>
	* @throws 
	*/
	public static Map<Integer,String> queryDatasOfCrfdataMongodb(String mongodbIp,Map<Integer,Map<String, String>> rowNumAndpatCrfdataMapMap) throws JSONException {
		if (!rowNumAndpatCrfdataMapMap.isEmpty()) {
			Map<Integer,String> rowNumAndQueryJsonMap = new HashMap<Integer,String>();
			//连接数据库
			DBCollection dbCollection = MongodbJDBCUtils
					.connectMongodbOfQueryReturnDBCollection(mongodbIp, "CRF_Model", "crfdata");
			for (Entry<Integer, Map<String, String>>  mapMap : rowNumAndpatCrfdataMapMap.entrySet()) {
				Integer rowNum=mapMap.getKey();
				Map<String, String> patCrfdataMap = mapMap.getValue();
				String pat = null;
				String crfdata = null;
				for (Entry<String, String> map: patCrfdataMap.entrySet()) {  
					pat = map.getKey();
					crfdata = map.getValue();
				}
				BasicDBObject queryCondition = new BasicDBObject();  
				queryCondition.put("data.patient_info.PATIENT_BASICINFO.PATIENT_SN.value",pat);
				BasicDBObject returnField = new BasicDBObject();
				returnField.put(crfdata,"");
				DBCursor cursor=dbCollection.find(queryCondition,returnField);
				String returnStr = null;
		        try {
		            while (cursor.hasNext()) {
		            	String middleStr = cursor.next().toString();
		            	JSONObject jsonObject=new JSONObject(middleStr);
		            	returnStr=((JSONObject) jsonObject.get("data")).getJSONArray("visits").get(0).toString();
		            	returnStr= JsonUtils.analysisCrfdataPathAndReturnNewValue(returnStr, crfdata);
		            }
		        } finally {
		            cursor.close();
		        }
		        rowNumAndQueryJsonMap.put(rowNum, returnStr);
			}
			return rowNumAndQueryJsonMap;  
		}else {
			return null;
		}
	}
	
	
	/** 
	 * @Title: insertDatasIntoPatientDetailMongodb 
	 * @Description: 测试数据库，批量插入json
	 * @param: @param listJsons 
	 * @return: void
	 * @throws 
	 */
	public static void insertDatasIntoPatientDetailMongodb(String mongodbIp,List<Map<String, JSONObject>> listMapJsons) {
		MongoCollection<Document> mongoCollection = MongodbJDBCUtils
				.connectTestMongodbOfInsertReturnMongoCollection(mongodbIp, "CRF_Model", "patientDetail");
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < listMapJsons.size(); i++) {
			Map<String, JSONObject> map = listMapJsons.get(i);
			String pat=null;
			JSONObject json=null;
			for (Entry<String, JSONObject> entry: map.entrySet()) {  
				pat = entry.getKey();
				json = entry.getValue();
			}
			BasicDBObject queryCondition = new BasicDBObject();  
			queryCondition.put("patient_info.patient_info_patient_sn",pat);
			mongoCollection.deleteOne(queryCondition);
			
			Document document = Document.parse(json.toString());
			documents.add(document);
		}
		mongoCollection.insertMany(documents);
		logger.info("插入测试库完成。"+"insert "+listMapJsons.size()+" successfully");
	}
	
	
	/** 
	 * @Title: insertDatasIntoPatientDetailMongodbOfDevelop 
	 * @Description: 开发数据库，批量插入json（由于开发库没有密码，所以单一个方法）
	 * @param: @param listJsons 
	 * @return: void
	 * @throws 
	 */
	public static void insertDatasIntoPatientDetailMongodbOfDevelop(String mongodbIp,List<Map<String, JSONObject>> listMapJsons) {
		MongoCollection<Document> mongoCollection = MongodbJDBCUtils
				.connectDevelopMongodbOfInsertReturnMongoCollection(mongodbIp, "CRF_Model", "patientDetail");
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < listMapJsons.size(); i++) {
			Map<String, JSONObject> map = listMapJsons.get(i);
			String pat=null;
			JSONObject json=null;
			for (Entry<String, JSONObject> entry: map.entrySet()) {  
				pat = entry.getKey();
				json = entry.getValue();
			}
			BasicDBObject queryCondition = new BasicDBObject();  
			queryCondition.put("patient_info.patient_info_patient_sn",pat);
			mongoCollection.deleteOne(queryCondition);
			
			Document document = Document.parse(json.toString());
			documents.add(document);
		}
		mongoCollection.insertMany(documents);
		logger.info("插入开发库完成。"+"insert "+listMapJsons.size()+" successfully");
	}
	
}

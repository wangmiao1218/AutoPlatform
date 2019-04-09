package com.gennlife.crf.mongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.gennlife.crf.utils.JsonUtils;
import com.gennlife.crf.utils.MongodbJDBCUtils;
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
			//定义返回结果
			Map<Integer,String> rowNumAndQueryJsonMap = new HashMap<Integer,String>();
			//连接数据库
			DBCollection dbCollection = MongodbJDBCUtils
					.connectMongodbOfQueryReturnDBCollection(mongodbIp, "CRF_Model", "crfdata");
			//rowNumAndpatCrfdataMapMap
			for (Entry<Integer, Map<String, String>>  mapMap : rowNumAndpatCrfdataMapMap.entrySet()) {
				//行号
				Integer rowNum=mapMap.getKey();
				//需要查询的两个变量
				Map<String, String> patCrfdataMap = mapMap.getValue();
				
				//解析patCrfdataMap
				String pat = null;
				String crfdata = null;
				for (Entry<String, String> map: patCrfdataMap.entrySet()) {  
					pat = map.getKey();
					crfdata = map.getValue();
				}
				//大的查询条件
				BasicDBObject queryCondition = new BasicDBObject();  
				queryCondition.put("data.patient_info.PATIENT_BASICINFO.PATIENT_SN.value",pat);
				//查询后，值显示该查询字段的值
				BasicDBObject returnField = new BasicDBObject();
				returnField.put(crfdata,"");
				DBCursor cursor=dbCollection.find(queryCondition,returnField);
				//将想要的返回数据转成JSONObject，放入map中
				String returnStr = null;
		        try {
		            while (cursor.hasNext()) {
		            	String middleStr = cursor.next().toString();
		            	//方便后续解析(先除去data，和visits[0]默认一次就诊，剩下的节点)
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
		//连接数据库
		MongoCollection<Document> mongoCollection = MongodbJDBCUtils
				.connectTestMongodbOfInsertReturnMongoCollection(mongodbIp, "CRF_Model", "patientDetail");
		//转换
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < listMapJsons.size(); i++) {
			Map<String, JSONObject> map = listMapJsons.get(i);
			//由于封装的map只有一个值，所以不在下面for循环
			String pat=null;
			JSONObject json=null;
			for (Entry<String, JSONObject> entry: map.entrySet()) {  
				pat = entry.getKey();
				json = entry.getValue();
			}
			//删除//插入时，直接删除原有的pat
			BasicDBObject queryCondition = new BasicDBObject();  
			queryCondition.put("patient_info.patient_info_patient_sn",pat);
			mongoCollection.deleteOne(queryCondition);
			
			//放入到documents，方便批量插入
			Document document = Document.parse(json.toString());
			documents.add(document);
		}
		//批量插入
		mongoCollection.insertMany(documents);
		System.out.println("插入测试库完成。"+"insert "+listMapJsons.size()+" successfully");
	}
	
	
	/** 
	 * @Title: insertDatasIntoPatientDetailMongodbOfDevelop 
	 * @Description: 开发数据库，批量插入json（由于开发库没有密码，所以单一个方法）
	 * @param: @param listJsons 
	 * @return: void
	 * @throws 
	 */
	public static void insertDatasIntoPatientDetailMongodbOfDevelop(String mongodbIp,List<Map<String, JSONObject>> listMapJsons) {
		//连接数据库
		MongoCollection<Document> mongoCollection = MongodbJDBCUtils
				.connectDevelopMongodbOfInsertReturnMongoCollection(mongodbIp, "CRF_Model", "patientDetail");
		//转换
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < listMapJsons.size(); i++) {
			Map<String, JSONObject> map = listMapJsons.get(i);
			//由于封装的map只有一个值，所以不在下面for循环
			String pat=null;
			JSONObject json=null;
			for (Entry<String, JSONObject> entry: map.entrySet()) {  
				pat = entry.getKey();
				json = entry.getValue();
			}
			//删除//插入时，直接删除原有的pat
			BasicDBObject queryCondition = new BasicDBObject();  
			queryCondition.put("patient_info.patient_info_patient_sn",pat);
			mongoCollection.deleteOne(queryCondition);
			
			//放入到documents，方便批量插入
			Document document = Document.parse(json.toString());
			documents.add(document);
		}
		//批量插入
		mongoCollection.insertMany(documents);
		System.out.println("插入开发库完成。"+"insert "+listMapJsons.size()+" successfully");
	}
	
	
}

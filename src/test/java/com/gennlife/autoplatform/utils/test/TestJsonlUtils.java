package com.gennlife.autoplatform.utils.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.gennlife.autoplatform.utils.JsonUtils;
import com.gennlife.autoplatform.utils.MongodbJDBCUtils;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class TestJsonlUtils {
	
	private String path = "E:\\CRFLogic\\test\\New1.json";
	private String path2 = "E:\\CRFLogic\\test\\little.json";
	private String path3 = "C:\\Users\\www\\Desktop\\91个pat.json";
	
	
	@Test
	public void test09() throws JSONException {
		JSONObject json = JsonUtils.readFileContentReturnJson(path3);
		JSONArray jsonArray = ((JSONObject) json.get("hits")).getJSONArray("hits");
		//System.out.println(jsonArray);
		//System.out.println(jsonArray.length());
		
		List<String> list = new ArrayList<>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			String pat=((JSONObject) jsonArray.get(i)).get("_id").toString();
			list.add(pat);
		}
		
		System.out.println(list.size());
		
		MongoCollection<Document> collection = MongodbJDBCUtils.connectRwsMongodbReturnMongoCollection(
				"10.0.2.79", "rws", "55EBE5FC650B4752A53B18C6A0EA7BDB");
		
		System.out.println(collection.count());
		
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		System.out.println(mongoCursor.next());
		
	}
	
	
	
	@Test
	public void test08() throws Exception {
		//String patientDetailContent="$.visits[0].record[0].admissions_record[0].admissions_records_physical_examination";
		//JSONObject  baseJson = JsonUtils.readFileContentReturnJson(path);
		//ReadContext context = JsonPath.parse(baseJson.toString());
		/**
		 * {"inpatientDetails":{"IP_CC":[{"IP_CHIEF_COMPLAINT":{"value":["右侧肾癌"]}},{"IP_CHIEF_COMPLAINT":{"value":["肺转移"]}}]}}
		   {"inpatientDetails":{"IP_CC":[{"IP_CHIEF_COMPLAINT_DURATION":{"value":["730.0"]}},{"IP_CHIEF_COMPLAINT_DURATION":{"value":["7.0"]}}]}}
		   {"inpatientDetails":{"IP_PE":{"IP_HEIGHT":{"value":"165.0"}}}}
		 */
		
		String path="inpatientDetails.IP_CC.IP_CHIEF_COMPLAINT_DURATION.value";
		String str=" {\"inpatientDetails\":{\"IP_CC\":[{\"IP_CHIEF_COMPLAINT_DURATION\":{\"value\":[\"730.0\"]}},{\"IP_CHIEF_COMPLAINT_DURATION\":{\"value\":[\"7.0\"]}}]}}";
		//String str="{ \"inpatientDetails\" : { \"IP_PE\" : { \"IP_HEIGHT\" : { \"value\" : \"165.0\"}}}}";
		
		//ReadContext context = JsonPath.parse(str);
		//Object obj = context.read("inpatientDetails.IP_PE");
		//Object obj = context.read("inpatientDetails.IP_CC");
		String returnStr= JsonUtils.analysisCrfdataPathAndReturnNewValue(str, path);
		System.out.println(returnStr);
	}
	
	@Test
	public void test07() throws Exception {
		//String patientDetailContent="$.visits[0].record[0].admissions_record[0].admissions_records_physical_examination";
		//JSONObject  baseJson = JsonUtils.readFileContentReturnJson(path);
		//ReadContext context = JsonPath.parse(baseJson.toString());
		/**
		 * {"inpatientDetails":{"IP_CC":[{"IP_CHIEF_COMPLAINT":{"value":["右侧肾癌"]}},{"IP_CHIEF_COMPLAINT":{"value":["肺转移"]}}]}}
		   {"inpatientDetails":{"IP_CC":[{"IP_CHIEF_COMPLAINT_DURATION":{"value":["730.0"]}},{"IP_CHIEF_COMPLAINT_DURATION":{"value":["7.0"]}}]}}
		   {"inpatientDetails":{"IP_PE":{"IP_HEIGHT":{"value":"165.0"}}}}
		 */
		
		String path="inpatientDetails.IP_PE.IP_HEIGHT.value";
		String str=" {\"inpatientDetails\":{\"IP_CC\":[{\"IP_CHIEF_COMPLAINT_DURATION\":{\"value\":[\"730.0\"]}},{\"IP_CHIEF_COMPLAINT_DURATION\":{\"value\":[\"7.0\"]}}]}}";
		//String str="{ \"inpatientDetails\" : { \"IP_PE\" : { \"IP_HEIGHT\" : { \"value\" : \"165.0\"}}}}";
		
		ReadContext context = JsonPath.parse(str);
		//Object obj = context.read("inpatientDetails.IP_PE");
		Object obj = context.read("inpatientDetails.IP_CC");
		//String returnStr= JsonUtils.analysisCrfdataPathAndReturnNewValue(str, path);
		System.out.println(obj instanceof ArrayList);
		System.out.println(obj);
		
	}
	
	
	@Test
	public void test06() throws Exception {
		String patientDetailContent="visits.record.admissions_record.admissions_records_physical_examination";
		String insertContent="test";
		String[] strings = null;
		if (patientDetailContent.contains(".")) {
			strings = patientDetailContent.split("\\.");
			for (int i = 0; i < strings.length; i++) {
				strings[i] = strings[i].trim();
			}
		}
		
	}
	
	@Test
	public void test03() throws Exception {
		org.json.JSONObject baseJson = JsonUtils.readFileContentReturnJson(path2);
		String patientDetailContent="visits.record.admissions_record.admissions_records_physical_examination";
		String insertContent="test";
		String[] strings = null;
		if (patientDetailContent.contains(".")) {
			strings = patientDetailContent.split("\\.");
			for (int i = 0; i < strings.length; i++) {
				strings[i] = strings[i].trim();
			}
		}
		List<JSONObject> list = new ArrayList<JSONObject>();
		Map<Object,Object> map = new HashMap<Object, Object>();
		JSONObject middleJsonObject = new JSONObject();
		JSONArray jsonArray = baseJson.getJSONArray(strings[0]);
		middleJsonObject=(JSONObject) jsonArray.get(0);
		System.out.println(middleJsonObject);
		String value = "{\"record\":[{\"admissions_record\":[{\"admissions_records_physical_examination\" : \"test\"}]}]}";
		JSONObject jsonObject  = new JSONObject(value);
		System.out.println(jsonObject);
		map.put("visits", jsonObject);
		System.out.println(map);
		System.out.println(baseJson);
	}
	
	
	@Test
	public void test02() throws Exception {
		org.json.JSONObject baseJson = JsonUtils.readFileContentReturnJson(path2);
		String patientDetailContent="visits.record.admissions_record.admissions_records_physical_examination";
		String insertContent="test";
		String[] strings = null;
		if (patientDetailContent.contains(".")) {
			strings = patientDetailContent.split("\\.");
			for (int i = 0; i < strings.length; i++) {
				strings[i] = strings[i].trim();
			}
		}
		
		JSONObject middleJsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray(); 
		System.out.println(baseJson);
		
	}
	

	
	@Test
	public void test01() throws Exception {
		String patientDetailContent="visits.record.admissions_record.admissions_records_physical_examination";
		if (!patientDetailContent.contains(";")) {
			System.out.println("ok");
		}
	}
	
	
	@Test
	public void getJSONode() throws Exception {
		org.json.JSONObject baseJson = JsonUtils.readFileContentReturnJson(path2);
		String patientDetailContent="visits.record.admissions_record.admissions_records_physical_examination";
		System.out.println(baseJson);
		
		JSONObject jsonObject = (JSONObject) baseJson.getJSONArray("visits").get(0);
	}
	
	
	@Test
	public void readFileContentReturnJson() throws Exception {
		//String path="D:\\我的文档\\Desktop\\crf工具\\New1.json";
		String path="E:\\CRFLogic\\test\\New1.json";
		org.json.JSONObject jsonObject = JsonUtils.readFileContentReturnJson(path);
		System.out.println(jsonObject);
	}
	

}
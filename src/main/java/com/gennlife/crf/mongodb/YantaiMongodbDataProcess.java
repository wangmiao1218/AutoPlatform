package com.gennlife.crf.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gennlife.crf.utils.MongodbJDBCUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

/**
 * @Description: 烟台mongodb的数据处理（patientDetail表）
 * @author: wangmiao
 * @Date: 2017年9月15日 下午5:44:47 
 */

public class YantaiMongodbDataProcess {

	/** 
	* @Title: RysqkYongyaoqk 
	* @Description: 烟台入院时情况_用药情况，返回数据来源相关的字段值
	* @param: @param patient_sn 传入patient_sn
	* @param: @param rydate 传入入院时间
	* @return: String 返回y用药情况的数据来源相关的字段值
	* @throws 
	*/
	public static String RysqkYongyaoqkReturnDataSources(String patient_sn,String rydate) {
		List<String> list = new ArrayList<String>();
		
		//连接烟台数据库
		DBCollection dbCollection = MongodbJDBCUtils.connectYantaiMongodbReturnDBCollection();
		
		//大的查询条件
		BasicDBObject query = new BasicDBObject();  
		query.put("patient_info.patient_info_patient_sn",patient_sn); 
		
		//数组内查询条件
		BasicDBObject edit = new BasicDBObject();
		edit.put("visits", new BasicDBObject("$elemMatch", 
				new BasicDBObject("type.visit_info_admission_date",rydate)
				.append("type.visit_info_admission_dept","神经内科")));
		
        DBCursor cursor=dbCollection.find(query,edit);
        //将想要的返回数据转成String，方便后期处理
        String returnStr =null;
        try {
            while (cursor.hasNext()) {
            	//BSONObject dbObject = cursor.next();
            	returnStr=cursor.next().toString();
            }
        } finally {
            cursor.close();
        }
        
        //将returnStr字符串转换成json对象:JSONObject
        JSONObject jsonObject=(JSONObject) JSON.parse(returnStr);
        JSONArray jsonArray = jsonObject.getJSONArray("visits");
        
        //返回visits的JSONObject
        jsonObject = (JSONObject) jsonArray.get(0);
        
        //返回record的JSONObject
        jsonArray = jsonObject.getJSONArray("record");
        jsonObject = (JSONObject) jsonArray.get(0);
        
        //获取course_record的JSONObject
        //first_course_record_medical_feature
        //first_course_record_diagnosis_basis
        JSONArray course_record_Array = jsonObject.getJSONArray("course_record");
        JSONObject course_record_Object = (JSONObject) course_record_Array.get(0);
        JSONArray fir_course_record_Array= course_record_Object.getJSONArray("fir");
        JSONObject fir_course_record_Object= (JSONObject) fir_course_record_Array.get(0);
        //处理fir_course_record_Object
        Map fir_course_record_map = (Map) fir_course_record_Object;
        for (Object map : fir_course_record_map.entrySet()){ 
        	if ("first_course_record_medical_feature"==((Map.Entry)map).getKey() || 
        		"first_course_record_diagnosis_basis"==((Map.Entry)map).getKey()) {
        		list.add(((Map.Entry)map).getKey()+":"+((Map.Entry)map).getValue());
         		//System.out.println(((Map.Entry)map).getKey()+":"+((Map.Entry)map).getValue());
 			}
        }
        
        
        //获取discharge_record的JSONObject
        //discharge_records_hospital_admission_condition
        //discharge_records_hospital_admission_diagnose
        JSONArray discharge_record = jsonObject.getJSONArray("discharge_record");
        JSONObject discharge_record_Object = (JSONObject) discharge_record.get(0);
        //处理discharge_record_Object
        Map discharge_record_map = (Map) discharge_record_Object;
        for (Object map : discharge_record_map.entrySet()){ 
        	if ("discharge_records_hospital_admission_condition"==((Map.Entry)map).getKey() || 
        		"discharge_records_hospital_admission_diagnose"==((Map.Entry)map).getKey()) {
        		list.add(((Map.Entry)map).getKey()+":"+((Map.Entry)map).getValue());
         		//System.out.println(((Map.Entry)map).getKey()+":"+((Map.Entry)map).getValue());
 			}
        }
        
        //获取admissions_record的JSONObject
        //admissions_records_past_medical_history
        //admissions_records_prsent_illness_history
        JSONArray admissions_record = jsonObject.getJSONArray("admissions_record");
        JSONObject admissions_record_Object = (JSONObject) admissions_record.get(0);
        //处理admissions_record_Object
        Map admissions_record_map = (Map) admissions_record_Object;
        for (Object map : admissions_record_map.entrySet()){ 
        	if ("admissions_records_past_medical_history"==((Map.Entry)map).getKey() || 
        		"admissions_records_prsent_illness_history"==((Map.Entry)map).getKey()) {
        		list.add(((Map.Entry)map).getKey()+":"+((Map.Entry)map).getValue());
         		//System.out.println(((Map.Entry)map).getKey()+":"+((Map.Entry)map).getValue());
 			}
        }
		return JSON.toJSON(list).toString();  
	}
	
}

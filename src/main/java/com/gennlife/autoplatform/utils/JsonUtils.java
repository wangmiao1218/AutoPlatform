package com.gennlife.autoplatform.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

/**
 * @Description: json格式的处理工具类
 * @author: wangmiao
 * @Date: 2017年11月27日 下午4:32:42
 */
public class JsonUtils {
	private static Logger logger = Logger.getLogger(JsonUtils.class); 
	
	/** 
	* @Title: analysisCrfdataPathAndReturnNewValue 
	* @Description: 根据传入的路径，分析json数据，返回查询后的值
	* @param: @param objJson ：传入的查询出来的JSONObject
	* @param: @param oldJsonPath：crfdata原路径
	* @param: @return 
	* @return: String
	* @throws 
	*/
	public static String analysisCrfdataPathAndReturnNewValue(String jsonStr,String oldJsonPath){ 
		String jsonPath=oldJsonPath.replace("data.visits.", "");
		jsonPath=jsonPath.substring(0,jsonPath.lastIndexOf("."));
		logger.info("去掉首尾的路径："+jsonPath);
		ReadContext context = JsonPath.parse(jsonStr);
		String[] strings = null;
		if (jsonPath.contains(".")) {
			strings = jsonPath.split("\\.");
			for (int i = 0; i < strings.length; i++) {
				strings[i] = strings[i].trim();
			}
		}
		logger.info("路径长度："+strings.length);
		String newJsonPath=null;
		//20180412：新增：表.字段
		if (strings.length==2) {
			newJsonPath=strings[0]+"."+strings[1]+".value";
		}
		
		//表.第二组.字段
		//inpatientDetails.IP_CC.IP_CHIEF_COMPLAINT
		if (strings.length==3) {
			Object obj = context.read(strings[0]+"."+strings[1]);
			if(obj instanceof ArrayList){ 
				newJsonPath=strings[0]+"."+strings[1]+"[*]"+"."+strings[2]+".value";
		    }else{ 
				newJsonPath=strings[0]+"."+strings[1]+"."+strings[2]+".value";
		    } 
		}
		
		//表.第二组.第三组.字段
		//inpatientDetails.IP_CANCER_HX.IPH_CANCER.IPH_CANCER_NAME
		if (strings.length==4) {
			Object obj2 = context.read(strings[0]+"."+strings[1]);
			if(obj2 instanceof ArrayList){ 
				newJsonPath=strings[0]+"."+strings[1]+"[*]"+"."+strings[2]+"."+strings[3]+".value";
			} else{
				Object obj3 = context.read(strings[0]+"."+strings[1]+"."+strings[2]);
				if (obj3 instanceof ArrayList) {
					newJsonPath=strings[0]+"."+strings[1]+"."+strings[2]+"[*]"+"."+strings[3]+".value";
				}else{ 
					newJsonPath=strings[0]+"."+strings[1]+"."+strings[2]+"."+strings[3]+".value";
				}
			}
		}
		logger.info("新组装后的路径："+newJsonPath);
		return context.read(newJsonPath).toString();
    } 


	
	/**
	 * @Title: updatePatAndValueReturnNewJSONObject
	 * @Description: 更新patContent和字段的值，返回新的JSONObject（使用全量的json时使用）
	 * @param: @param baseJson
	 * @param: @param patPath
	 * @param: @param patContent
	 * @param: @param strs
	 * @param: @param insertContent
	 * @param: @return
	 * @return: JSONObject
	 * @throws
	 */
	public static JSONObject updatePatAndValueReturnNewJSONObject(
			JSONObject baseJson, String patPath, String patContent,
			String[] strs, String insertContent) throws JSONException {
		JSONObject middleJsonObject = new JSONObject();
		switch (strs.length) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			middleJsonObject = ((JSONObject) baseJson.getJSONArray(strs[0])
					.get(0));
			middleJsonObject.put(strs[1], insertContent);
			break;
		case 3:
			middleJsonObject = ((JSONObject) ((JSONObject) baseJson
					.getJSONArray(strs[0]).get(0)).getJSONArray(strs[1]).get(0));
			middleJsonObject.put(strs[2], insertContent);
			break;
		case 4:
			middleJsonObject = ((JSONObject) ((JSONObject) ((JSONObject) baseJson
					.getJSONArray(strs[0]).get(0)).getJSONArray(strs[1]).get(0))
					.getJSONArray(strs[2]).get(0));
			middleJsonObject.put(strs[3], insertContent);
			break;
		case 5:
			middleJsonObject = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) baseJson
					.getJSONArray(strs[0]).get(0)).getJSONArray(strs[1]).get(0))
					.getJSONArray(strs[2]).get(0)).getJSONArray(strs[3]).get(0);
			middleJsonObject.put(strs[4], insertContent);
			break;
		default:
			break;
		}
		String[] patSplit = patPath.split("\\.");
		((JSONObject) baseJson.get(patSplit[0])).put(patSplit[1], patContent);
		return baseJson;
	}

	/**
	 * @Title: readFileContent
	 * @Description: 读取文件内容，返回JSONObject
	 * @param: @param Path
	 * @param: @return :
	 * @return: JSONObject
	 * @throws
	 */
	public static JSONObject readFileContentReturnJson(String path)
			throws JSONException {
		String laststr = "";
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			fileInputStream = new FileInputStream(path);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new JSONObject(laststr);
	}

	/**
	 * @Title: jsonToJsonFile
	 * @Description: 将json生成json文件
	 * @param: JSONObject lastJson
	 * @return: void
	 * @throws
	 */
	public static void jsonToJsonFile(JSONObject lastJson, String path) {
		File file = new File(path);
		try {
			Writer write = new OutputStreamWriter(new FileOutputStream(file),
					"UTF-8");
			write.write(lastJson.toString());
			write.flush();
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: JsonCompareJson
	 * @Description: 比较两个json字符串是否相等
	 * @param: @param str1
	 * @param: @param str2
	 * @param: @return :
	 * @return: boolean
	 * @throws
	 */
	public static boolean JsonCompareJson(String str1, String str2) {
		Gson gson1 = new GsonBuilder().create();// or new Gson()
		JsonElement e1 = gson1.toJsonTree(str1);// or new Gson()

		Gson gson2 = new GsonBuilder().create();
		JsonElement e2 = gson2.toJsonTree(str2);

		return e1.equals(e2);
	}

}

package com.gennlife.crf.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONException;
import org.json.JSONObject;

import com.gennlife.crf.bean.CrfPackageJsonBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * @Description: json格式的处理工具类
 * @author: wangmiao
 * @Date: 2017年11月27日 下午4:32:42
 */
public class JsonUtil_bak {

	
	public static CrfPackageJsonBean jsontest2(String selectContent,Map<String,String> elementMap) throws JSONException{
		//返回的CrfPackageJsonBean
		CrfPackageJsonBean returnJsonBean = new CrfPackageJsonBean();
		List<Map> listMaps = new ArrayList<Map>();
		//找最后一个“(”和它之后的第一个“)”
		
		
		/*
		//
		for (int i = 0; i < selectContent.length(); i++) {
			String element =  String.valueOf(selectContent.charAt(i));
			System.out.println(element);
			
			//判断
			if (!"&".equals(element) && !"|".equals(element)) {
				//判断对象jsonBean是否为空
				//不为空，则封装成整个Object
				//if (BeanMap.create(returnJsonBean)!=null) {
				if (returnJsonBean.getOperator()!=null) {
					JsonUtil.jsontest(selectContent, elementMap);
					//如果returnJsonBean的operator不为空，则把returnJsonBean转为map添加到listmap中
					//先把listMap清空
					//listMaps.clear();
					//listMaps.add(BeanMap.create(returnJsonBean));
					//System.out.println(listMaps);
				}	
				//元素
				String[] strings = ListAndStringUtils.valueSpiltByCommaToStrings(elementMap.get(element));
				if (strings.length==3) {
					Map<String,Object> mapJson = new HashedMap<String, Object>();
					mapJson.put("source", strings[0]);
					mapJson.put("target_value", strings[1]);
					mapJson.put("operator", strings[2]);
					
					//封装成list
					listMaps.add(mapJson);
				}
				
				returnJsonBean.setDetail(listMaps);
				
			}else if ("&".equals(element)) {
				returnJsonBean.setOperator("and");
				//break;
				
			}else if ("|".equals(element)) {
				returnJsonBean.setOperator("or");
				
			}
			
		}
		*/
		return returnJsonBean;
		
	
	}
	
	
	
	public static CrfPackageJsonBean jsontest(String selectContent,Map<String,String> elementMap) throws JSONException{
		//返回的CrfPackageJsonBean
		CrfPackageJsonBean returnJsonBean = new CrfPackageJsonBean();
		List<Map> listMaps = new ArrayList<Map>();
		
		//
		for (int i = 0; i < selectContent.length(); i++) {
			String element =  String.valueOf(selectContent.charAt(i));
			System.out.println(element);
			
			//判断
			if (!"&".equals(element) && !"|".equals(element)) {
				//判断对象jsonBean是否为空
				//不为空，则封装成整个Object
				//if (BeanMap.create(returnJsonBean)!=null) {
				if (returnJsonBean.getOperator()!=null) {
					JsonUtil_bak.jsontest(selectContent, elementMap);
					//如果returnJsonBean的operator不为空，则把returnJsonBean转为map添加到listmap中
					//先把listMap清空
					//listMaps.clear();
					//listMaps.add(BeanMap.create(returnJsonBean));
					//System.out.println(listMaps);
				}	
				//元素
				String[] strings = ListAndStringUtils.valueSpiltByCommaToStrings(elementMap.get(element));
				if (strings.length==3) {
					Map<String,Object> mapJson = new HashedMap<String, Object>();
					mapJson.put("source", strings[0]);
					mapJson.put("target_value", strings[1]);
					mapJson.put("operator", strings[2]);
					
					//封装成list
					listMaps.add(mapJson);
				}
				
				returnJsonBean.setDetail(listMaps);
				
			}else if ("&".equals(element)) {
				returnJsonBean.setOperator("and");
				//break;
				
			}else if ("|".equals(element)) {
				returnJsonBean.setOperator("or");
				
			}
			
		}
		
		return returnJsonBean;
		
		
	}
	
	
	
	
	
	
	
	
	
	/*忘记逻辑功能了！！！
	public static void test2(Object o,Map<String,Object> m)  {  
        if(o.getClass().equals(JSONObject.class))  {  
           JSONObject temp= (JSONObject)o;  
           for(String keyMap:m.keySet()) {  
               if(m.get(keyMap)==null) {  
                    if(temp.containsKey(keyMap)){  
                        System.out.println("find out.....................");  
                       // System.out.println(temp.get(keyCondition));  
                        //Object result = temp.get(keyMap);  
                        m.put(keyMap, temp.get(keyMap));  
                        //System.out.println(result);  
                    }  
                    else{  
                        Iterator it=temp.keySet().iterator();    
                         while(it.hasNext()){      
                               String key;      
  
                               key=it.next().toString();      
                               Object v=temp.get(key);   
                              if(v.getClass().equals(JSONObject.class)||v.getClass().equals(JSONArray.class))  {  
                                  test2(v,m);  
                              }  
                          }     
                    }  
               }  
           }  
            
        }  
        else if(o.getClass().equals(JSONArray.class))  {  
            JSONArray tempArray=(JSONArray) o;  
            for(Object ob:tempArray)  {  
                if(ob.getClass().equals(JSONObject.class))  {  
                    JSONObject tempJb=(JSONObject) ob;  
                     for(String keyMap:m.keySet())   {  
                        if(m.get(keyMap)==null)   {  
                            if(tempJb.containsKey(keyMap))  {  
                                System.out.println("find out....2222.................");  
                                // System.out.println(tempJb.get(keyCondition));  
                               // Object result=tempJb.get(keyMap);  
                                  m.put(keyMap, tempJb.get(keyMap));  
                            }  
                            else {  
                                test2(tempJb,m);  
                            }  
                        }  
                     }     
                }  
                else if(ob.getClass().equals(JSONArray.class))  {  
                    test2(ob,m);  
                }  
            }  
        }  
    }  
*/
	
	/**
	 * @Title: getJSONObjectAllKeys
	 * @Description: 获取jsonObject中所有的key（递归），输出所有key
	 * @param: @param jsonObject
	 * @param: @return :
	 * @return: List<String>
	 * @throws
	 */
	// public static List<String> getJSONObjectAllKeysList(JSONObject jsonObj){
	/*public static String getJSONObjectAllKeys(JSONObject jsonObj) {
		// List<String> list = new ArrayList<String>();
		String result = null;
		Iterator keys = jsonObj.keys();
		while (keys.hasNext()) {
			try {
				String key = keys.next().toString();
				String value = jsonObj.optString(key);
				// 判断value的值，是否是Object、Array
				int i = stringIsArrayORObject(value);

				//
				// if (list.size()==0 || list.isEmpty()) {
				if (result == null || result.equals("")) {
					if (i == 0) {
						// list.add(key);
						result = key + ",";
						System.out.println("i=0 | key=" + key + "| result="
								+ result);
					} else if (i == 1) {
						result = key + ",";
						System.out.println("i=1 | key=" + key + "| result="
								+ result);
						result = getJSONObjectAllKeys(new JSONObject(value))
								+ ",";
						// getJSONObjectAllKeysList(new JSONObject(value));
					} else if (i == 2) {
						result = key + ",";
						System.out.println("i=2 | key=" + key + "| result="
								+ result);
						JSONArray arrays = new JSONArray(value);
						for (int j = 0; j < arrays.length(); j++) {
							JSONObject array = new JSONObject(arrays.get(j));
							result = getJSONObjectAllKeys(array) + ",";
							// list.add(key);
							// getJSONObjectAllKeysList(array);
						}
					}
				} else {
					if (i == 0) {
						// list.add(key);
						result = result + key + ",";
						System.out.println("i=0 | key=" + key + "| result="
								+ result);
					} else if (i == 1) {
						result = result + key + ",";
						System.out.println("i=1 | key=" + key + "| result="
								+ result);
						result = result
								+ getJSONObjectAllKeys(new JSONObject(value));
						// getJSONObjectAllKeysList(new JSONObject(value));
					} else if (i == 2) {
						result = result + key + ",";
						System.out.println("i=2 | key=" + key + "| result="
								+ result);
						JSONArray arrays = new JSONArray(value);
						for (int k = 0; k < arrays.length(); k++) {
							JSONObject array = new JSONObject(arrays.get(k));
							result = result + getJSONObjectAllKeys(array) + ",";
							// list.add(key);
							// getJSONObjectAllKeysList(array);
						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		// return list;
		return result;
	}
*/
	
	/**
	 * @Title: stringIsArrayORObject
	 * @Description: 判断一个字符串是array类型还是object（上面使用）
	 * @param: @param jsonStr
	 * @param: @return : 0:既不是array也不是object 1：是object 2 ：是Array
	 * @return: int
	 * @throws
	 */
	/*public static int stringIsArrayORObject(String jsonStr) {
		try {
			JSONArray array = new JSONArray(jsonStr);
			return 2;
		} catch (JSONException e) {// 报错 说明JSON字符不是数组或根本就不是JSON
			try {
				JSONObject object = new JSONObject(jsonStr);
				return 1;
			} catch (JSONException e2) {// 报错 说明JSON字符根本就不是JSON
				// System.out.println("非法的JSON字符串");
				return 0;
			}
		}
	}
*/
	
	
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

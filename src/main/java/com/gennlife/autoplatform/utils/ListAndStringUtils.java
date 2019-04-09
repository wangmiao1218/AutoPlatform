package com.gennlife.autoplatform.utils;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: 字符串及集合的处理的工具集
 * @author: wangmiao
 * @Date: 2017年6月9日 上午9:58:00
 */
public class ListAndStringUtils {
	private static Logger logger = Logger.getLogger(ListAndStringUtils.class); 
	
/*
	
	/**
	 * 将Long类型的时间戳转换成String类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
	 */
	public static String convertTimeToString(Long time) {
		//Assert.notNull(time, "time is null");
		DateTimeFormatter ftf = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time),
				ZoneId.systemDefault()));
	}
	
	/**
	 * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
	 */
	public static Long convertTimeToLong(String time) {
		//Assert.notNull(time, "time is null");
		DateTimeFormatter ftf = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime parse = LocalDateTime.parse("2018-05-29 13:52:50", ftf);
		return LocalDateTime.from(parse).atZone(ZoneId.systemDefault())
				.toInstant().toEpochMilli();
	}

	/**
	 * @Title: isJsonObject
	 * @Description: 判断是否为JsonObject
	 * @author: wangmiao
	 * @Date: 2018年8月21日 下午2:30:34
	 * @param: @param obj
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public static Boolean isJsonObject(Object obj) {
		if (obj == null) {
			return false;
		} else {
			String content = obj.toString();
			try {
				JSONObject.parseObject(content);
				if (content.startsWith("{")) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * @Title: isNumeric
	 * @Description: 判断字符串是否为数字
	 * @author: wangmiao
	 * @Date: 2018年8月20日 上午11:31:24
	 * @param: @param str
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public static Boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @Title: countChar 字符串中判断含有单个字符串的个数
	 * @Description:
	 * @param: @param str
	 * @param: @param ch 用单引号''
	 * @param: @return :
	 * @return: int
	 * @throws
	 */
	public static int countChar(String str, char ch) {
		char[] chs = str.toCharArray();
		int count = 0;
		for (int i = 0; i < chs.length; i++) {
			if (chs[i] == ch) {
				count++;
			}
		}
		return count;
	}

	/**
	 * @Title: replaceBlankAndLastSemicolon
	 * @Description: 去掉字符串的空格、回车、换行符、制表符，并且去掉结尾的分号
	 * @author: wangmiao
	 * @Date: 2018年4月17日 下午5:21:29
	 * @param: @param str
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String replaceBlankAndLastSemicolon(String str) {
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		if (str.contains(";")) {
			String lastStr = str.substring(str.length() - 1, str.length());
			if (";".equals(lastStr)) {
				str = str.substring(0, str.length() - 1);
			}
		}
		return str;
	}

	/**
	 * @Title: dealWithpatientDetailByAsteriskToString
	 * @Description: 处理patientDetail，去掉$.以及[*] (希望用于传入路径，写值)，目前只在多源时，去掉多余符号
	 * @param: @param value
	 * @return: String
	 * @throws
	 */
	public static String dealWithpatientDetailByAsteriskToString(String value) {
		if (value.indexOf("$") != -1) {
			value = value.replace("$.", "");
		}
		if (value.indexOf("[") != -1 && value.indexOf("]") != -1) {
			value = value.replace("[*]", "");
		}
		return value;
	}

	/**
	 * @Title: dealWithpatientDetailByDotToStrings
	 * @Description: 处理patientDetail，去掉$.以及[*],然后将value以“.”进行分割,返回数组
	 * @param: @param value
	 * @return: String[]
	 * @throws
	 */
	public static String[] dealWithpatientDetailByDotToStrings(String value) {
		if (value.indexOf("$") != -1) {
			value = value.replace("$.", "");
		}
		if (value.indexOf("[") != -1 && value.indexOf("]") != -1) {
			value = value.replace("[*]", "");
		}
		String[] strings = null;
		if (value.contains(".")) {
			strings = value.split("\\.");
			for (int i = 0; i < strings.length; i++) {
				strings[i] = strings[i].trim();
			}
		}
		return strings;
	}

	/**
	 * @Title: dealWithpatientDetailBySemicolonToStrings
	 * @Description: value以“;”进行分割,返回List<String>
	 * @param: @param value
	 * @return: List<String>
	 * @throws
	 */
	public static List<String> dealWithpatientDetailBySemicolonToStrings(
			String value) {
		List<String> list = new ArrayList<String>();
		String[] strings = null;
		if (value.contains(";")) {
			strings = value.split(";");
			for (int i = 0; i < strings.length; i++) {
				if (strings[i] == null) {
					list.add("");
				} else {
					list.add(strings[i].trim());
				}
			}
		}
		return list;
	}

	/**
	 * @Title: valueSpiltByCommaToStrings
	 * @Description: 将value以“,”进行分割,返回数组
	 * @param: @param value
	 * @return: String[]
	 * @throws
	 */
	public static String[] valueSpiltByCommaToStrings(String value) {
		String[] strings = null;
		if (value.contains(",")) {
			strings = value.split(",");
			for (int i = 0; i < strings.length; i++) {
				if (strings[i] == null) {
					strings[i] = null;
				} else {
					strings[i] = strings[i].trim();
				}
			}
		}
		return strings;
	}

	/**
	 * @Title: valueSpiltBySemicolonToStringMap
	 * @Description: 将value用“;”分割,然后用map，转成listMap
	 * @param: @param value
	 * @return: Map<String, String>
	 * @throws
	 */
	public static Map<String, String> valueSpiltBySemicolonToStringMap(
			String value) {
		Map<String, String> map = new HashedMap<String, String>();
		if (value.contains(";")) {
			String[] strings = value.split(";");
			for (int i = 0; i < strings.length; i++) {
				String[] mapStrs = null;
				if (strings[i].contains(":")) {
					mapStrs = strings[i].split(":");
					map.put(mapStrs[0].trim(), mapStrs[1].trim());
				}
			}
		} else {
			if (value.trim().contains(":")) {
				String[] mapStrs = value.split(":");
				map.put(mapStrs[0].trim(), mapStrs[1].trim());
			}
		}
		return map;
	}

	/**
	 * @Title: valueSpiltBySemicolonToJSONArray
	 * @Description: 将value用“;”分割,转成JSONArray
	 * @param: @param value
	 * @return: JSONArray
	 * @throws
	 */
	public static JSONArray valueSpiltBySemicolonToJSONArray(String value) {
		List<String> list = new ArrayList<String>();
		if (value.contains(";")) {
			String[] strings = value.split(";");
			for (int i = 0; i < strings.length; i++) {
				list.add(strings[i]);
			}
		} else {
			list.add(value);
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}

	/**
	 * @Title: dealOldPatListAddDoubleQuotationMarksReturnOldPatStrs
	 * @Description: 处理从excel读取的oldPatlist,返回一个处理后的String
	 * @param: @param patList
	 * @param: @return :
	 * @return: String
	 * @throws
	 */
	public static String dealOldPatListAddDoubleQuotationMarksReturnOldPatStrs(
			List<String> oldPatList) {
		if (oldPatList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < oldPatList.size(); i++) {
				String str = "{\"PatientID\":\"" + oldPatList.get(i)
						+ "\",\"VisitType\":\"1\"}";
				sb.append(str.trim()).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		} else {
			return null;
		}

	}

	/**
	 * @Title: dealPatListAddDoubleQuotationMarksReturnPatStrs
	 * @Description: 处理从excel读取的patlist,将每个元素加双引号及每个末尾加逗号，返回一个处理后的String
	 * @param: @param patList
	 * @param: @return :
	 * @return: String
	 * @throws
	 */
	public static String dealPatListAddDoubleQuotationMarksReturnPatStrs(
			List<String> patList) {
		if (patList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < patList.size(); i++) {
				String str = "\"" + patList.get(i) + "\"";
				sb.append(str.trim()).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		} else {
			return null;
		}

	}

	/**
	 * @Title: compareTwoListReturnDiffrent
	 * @Description: 获取两个List的不同元素
	 *               用一个map存放lsit的所有元素，其中的key为lsit1的各个元素，value为该元素出现的次数,
	 *               接着把list2的所有元素也放到map里，如果已经存在则value加1，
	 *               最后我们只要取出map里value为1的元素即可，这样我们只需循环m+n次，大大减少了循环的次数。
	 * @param: List<String> list1
	 * @param: List<String> list2
	 * @return: List<String>
	 * @throws
	 */
	public static List<String> compareTwoListReturnDiffrent(List<String> list1,
			List<String> list2) {
		long st = System.nanoTime();
		Map<String, Integer> map = new HashMap<String, Integer>(list1.size()
				+ list2.size());
		List<String> diff = new ArrayList<String>();
		List<String> maxList = list1;
		List<String> minList = list2;
		if (list2.size() > list1.size()) {
			maxList = list2;
			minList = list1;
		}

		for (String string : maxList) {
			map.put(string, 1);
		}
		for (String string : minList) {
			Integer cc = map.get(string);
			if (map.get(string) != null) {
				map.put(string, ++cc);
				continue;
			}
			map.put(string, 1);
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				diff.add(entry.getKey());
			}
		}
		logger.info("getDiffrent4 total times "
				+ (System.nanoTime() - st));

		return diff;
	}


	/**
	 * @Title: rangeDataReturnNeededRangeData
	 * @Description: 将rangeData处理，都换成中文分号
	 * @param: @param value
	 * @return: String
	 * @throws
	 */
	public static String rangeDataReturnNeededRangeData(String rangeData) {
		if (rangeData.indexOf(" ") != -1) {
			return rangeData.replaceAll(" ", "").trim();
		} else if (rangeData.indexOf(";") != -1) {
			return rangeData.replaceAll(";", "；").trim();
		} else {
			return rangeData.trim();
		}
	}

	/**
	 * @Title: displayMainValueToSelectByValue
	 * @Description: 若displayMainValueToSelectByValue有分号，则选择最后一个，没有直接返回值
	 * @param: @param value
	 * @return: String
	 * @throws
	 */
	public static String displayMainValueToSelectByValue(String displayMainValue) {
		if (displayMainValue.indexOf(";") != -1) {
			return displayMainValue.substring(
					displayMainValue.lastIndexOf(".") + 1).trim();
		} else if (displayMainValue.indexOf("；") != -1) {
			return displayMainValue.substring(
					displayMainValue.lastIndexOf("；") + 1).trim();
		} else {
			return displayMainValue.trim();
		}
	}

	/**
	 * @Title: displayMainKeyToEnglishName
	 * @Description: 将路径中去掉最后一个.之前，返回字段名称,若没有.，则直接返回传入的值
	 * @param: @param value
	 * @return: String
	 * @throws
	 */
	public static String displayMainKeyToEnglishName(String displayMainKey) {
		if (displayMainKey.indexOf(".") != -1) {
			return displayMainKey
					.substring(displayMainKey.lastIndexOf(".") + 1).trim();
		} else {
			return displayMainKey.trim();
		}
	}

	/**
	 * @Title: sameListTransferToSequenceList
	 * @Description: 将list中重复的数据，加序号,返回带序号的List
	 * @param: @param list :
	 * @return: List<String>
	 */
	public static List<String> sameListTransferToSequenceList(List<String> list) {
		String[] indexArr;
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);
			String old = map.get(key);
			if (old != null) {
				map.put(key, old + "," + (i + 1));
			} else {
				map.put(key, "" + (i + 1));
			}
		}
		Iterator<String> it = map.keySet().iterator();
		int index = -1;
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			if (value.indexOf(",") != -1) {
				System.out.println(key + " 重复,行： " + value);
				indexArr = value.split(",");

				for (int i = 0; i < indexArr.length; i++) {
					index = Integer.parseInt(indexArr[i]) - 1;
					list.set(index, list.get(index) + (1 + i));
				}
			}
		}
		return list;
	}

	/**
	 * @Title: chNamesListFilter
	 * @Description: 将chNamesList每个元素：1.去掉中英文:之后 2.去掉中英文？之后 3.去掉。之后
	 *               4.去掉中英文（）之间、5.删掉、 6.删掉空格， 7.删掉中英文，
	 * @param: List<String> chNamesList：中文list
	 * @return: List<String> 返回过滤后的list
	 * @throws
	 */
	public static List<String> chNamesListFilter(List<String> chNamesList) {
		List<String> returnList = new ArrayList<String>();
		for (int i = 0; i < chNamesList.size(); i++) {
			String value = chNamesList.get(i);
			if (value.indexOf("：") != -1) {
				value = value.substring(0, value.indexOf("："));
			}
			if (value.indexOf(":") != -1) {
				value = value.substring(0, value.indexOf(":"));
			}
			if (value.indexOf("？") != -1) {
				value = value.substring(0, value.indexOf("？"));
			}
			if (value.indexOf("?") != -1) {
				value = value.substring(0, value.indexOf("?"));
			}
			if (value.indexOf("。") != -1) {
				value = value.substring(0, value.indexOf("。"));
			}
			if (value.indexOf("（") != -1) {
				value = value.replaceAll(value.substring(value.indexOf("（"),
						value.indexOf("）") + 1), "");
			}
			if (value.indexOf("(") != -1) {
				value = value.replaceAll(value.substring(value.indexOf("("),
						value.indexOf(")") + 1), "");
			}
			if (value.indexOf("、") != -1) {
				value = value.replace("、", "");
			}
			if (value.indexOf(" ") != -1) {
				value = value.replace(" ", "");
			}
			if (value.indexOf("，") != -1) {
				value = value.replace("，", "");
			}
			if (value.indexOf(",") != -1) {
				value = value.replace(",", "");
			}

			returnList.add(value);
		}
		return returnList;
	}

	/**
	 * @Title: enNamesListFilter
	 * @Description: 将enNamesList每个元素：1.去掉中英文 ？之后 2.去掉.之后 3.去掉中英文()之内 4.去掉-
	 *               5.中英文，和、变成空格 6.空格变为_ 7.转为大写 8.trim
	 * @param: List<String> enNamesList：英文list
	 * @return: List<String> 返回过滤后的list
	 * @throws
	 */
	public static List<String> enNamesListFilter(List<String> enNamesList) {
		List<String> returnList = new ArrayList<String>();

		for (int i = 0; i < enNamesList.size(); i++) {
			String value = enNamesList.get(i);
			if (value.indexOf("？") != -1) {
				value = value.substring(0, value.indexOf("？"));
			}
			if (value.indexOf("?") != -1) {
				value = value.substring(0, value.indexOf("?"));
			}
			if (value.indexOf(".") != -1) {
				value = value.substring(0, value.indexOf("."));
			}
			if (value.indexOf("（") != -1) {
				value = value.replaceAll(value.substring(value.indexOf("（"),
						value.indexOf("）") + 1), "");
			}
			if (value.indexOf("(") != -1) {
				value = value.replaceAll(value.substring(value.indexOf("("),
						value.indexOf(")") + 1), "");
			}
			if (value.indexOf("-") != -1) {
				value = value.replace("-", "");
			}
			if (value.indexOf("，") != -1) {
				value = value.replace("，", " ");
			}
			if (value.indexOf(",") != -1) {
				value = value.replace(",", " ");
			}
			if (value.indexOf("、") != -1) {
				value = value.replace("、", " ");
			}
			if (value.indexOf(" ") != -1) {
				value = value.replace(" ", "_");
			}
			value = value.toUpperCase();
			value = value.trim();
			returnList.add(value);
		}
		return returnList;
	}

	/**
	 * @Title: listWebElementToSelectString
	 * @Description: 将List<WebElement> 转换成string，并用“；”分割
	 * @param: List<WebElement> list 下拉框内容
	 * @return: String
	 * @throws
	 */
	public static String listWebElementToSelectString(List<WebElement> list) {
		StringBuilder sb = new StringBuilder();
		if (list.get(0) == null || "".equals(list.get(0))
				|| " ".equals(list.get(0))) {
			for (int i = 1; i < list.size(); i++) {// 一般下拉框第一个为空，所以从1开始
				String attribute = list.get(i).getAttribute("value");
				sb.append(attribute + "；");
			}
			sb.deleteCharAt(sb.length() - 1);
		} else {
			for (int i = 0; i < list.size(); i++) {// 一般下拉框第一个为空，所以从1开始
				String attribute = list.get(i).getAttribute("value");
				sb.append(attribute + "；");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * @Title: listWebElementToListString
	 * @Description: 将List<WebElement> 转换成string类型的List
	 * @param: List<WebElement> list 下拉框内容
	 * @return: List<String>
	 * @throws
	 */
	public static List<String> listWebElementToListString(List<WebElement> list) {
		List<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String attribute = list.get(i).getText();
			arrayList.add(attribute);
		}
		return arrayList;
	}

	/**
	 * @Title: valueSpiltBySemicolonToStringList
	 * @Description: 将value用“;”分割,转成list
	 * @param: @param value
	 * @return: List<String>
	 * @throws
	 */
	public static List<String> valueSpiltBySemicolonToStringList(String value) {
		List<String> list = new ArrayList<String>();
		if (value.contains(";")) {
			String[] strings = value.split(";");
			for (int i = 0; i < strings.length; i++) {
				list.add(strings[i]);
			}
		} else if (value.contains("；")) {
			String[] strings = value.split("；");
			for (int i = 0; i < strings.length; i++) {
				list.add(strings[i]);
			}
		} else {
			list.add(value);
		}
		return list;
	}

	/**
	 * @Title: trimString
	 * @Description: 将excel中字符串取出，以；分割后，去掉空格，并重新组装成String
	 * @param: String value
	 * @return: String
	 * @throws
	 */
	public static String trimString(String value) {
		String[] strings = value.split("；");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			sb.append(strings[i].trim()).append("；");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * @Title: trimStringOfEqualSign
	 * @Description: 将字符串用=分割，然后去掉空格，返回数组
	 * @param: @param value
	 * @return: String[]
	 * @throws
	 */
	public static String[] trimStringOfEqualSign(String value) {
		String[] strings = value.split("=");
		for (int i = 0; i < strings.length; i++) {
			strings[i] = strings[i].trim();
		}
		return strings;
	}

	/**
	 * @Title: stringListReturnRandomString
	 * @Description: 将string从数据库中取出，转为list，随机取出list中元素
	 * @param: @param value
	 * @return: String
	 * @throws
	 */
	public static String stringListReturnRandomString(String value) {
		String[] strings = value.split(";");
		ArrayList<String> items = new ArrayList<>();
		for (int i = 0; i < strings.length; i++) {
			items.add(strings[i].trim());
		}
		Random rand = new Random();
		int size = items.size();
		String returnValue = null;
		for (int i = 0; i < size; i++) {
			int myRand = rand.nextInt(items.size());
			returnValue = items.get(myRand);
		}
		return returnValue;
	}

	/**
	 * @Title: stringReplaceReturnValue
	 * @Description: 将\变成\\，返回filePath
	 * @param: @param value
	 * @return: String
	 * @throws
	 */
	public static String stringReplaceReturnValue(String filePath) {
		return filePath.replaceAll("\\\\", "\\\\\\\\");
	}

	/**
	 * @Title: stringToSubstringReturnFilePath
	 * @Description: 将\变成\\后，再将路径中去掉最后一个\\之后，返回filePath
	 * @param: @param value
	 * @return: String
	 * @throws
	 */
	public static String stringToSubstringReturnFilePath(String value) {
		value = value.replaceAll("\\\\", "\\\\\\\\");
		String fileName = value.substring(value.lastIndexOf("\\") + 1);
		String[] strings = value.split(fileName);
		strings[0] = strings[0].substring(0, strings[0].length() - 2);
		return strings[0];
	}

	/**
	 * @Title: stringToSubstringReturnFileName
	 * @Description: 将\变成\\后，再将路径中去掉最后一个\\之前，只剩下文件名及后缀名,返回filename.后缀
	 * @param: @param value
	 * @return: String:只剩下文件名及后缀名,返回filename.后缀
	 * @throws
	 */
	public static String stringToSubstringReturnFileName(String value) {
		value = value.replaceAll("\\\\", "\\\\\\\\");
		return value.substring(value.lastIndexOf("\\") + 1);
	}

	/**
	 * @Title: arrayListFilesToStringList
	 * @Description: 将ArrayFiles中\变成\\后，存到数组中去
	 * @param: @param files
	 * @param: @return :
	 * @return: List<String> 返回String类型的list
	 * @throws
	 */
	public static List<String> arrayListFilesToStringList(ArrayList<File> files) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			String str = files.get(i).toString().replaceAll("\\\\", "\\\\\\\\");
			list.add(str);
		}
		return list;
	}

	/**
	 * @Title: stringToSubstring
	 * @Description: 将路径中去掉最后一个\\之前，以及后缀名，只剩下纯文件名
	 * @param: @param value
	 * @return: String
	 * @throws
	 */
	public static String stringToSubstring(String value) {
		return value.substring(value.lastIndexOf("\\") + 1,
				value.lastIndexOf("."));
	}

	/**
	 * @Title: segmentFileAllNameToFileName
	 * @Description: 分割文件名，去掉后缀，仅剩文件名称
	 * @param: @param value
	 * @param: @return :
	 * @return: String
	 * @throws
	 */
	public static String segmentFileAllNameToFileName(String value) {
		return value.substring(0, value.lastIndexOf("."));
	}

}

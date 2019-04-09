package com.gennlife.autoplatform.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

/**
 * @Description: csv文件的处理工具集
 * @author: wangmiao
 * @Date: 2017年8月3日 下午6:31:09 
 */
public class CsvUtils {

	/** 
	* @Title: readCsvOfLine 
	* @Description: 读取csv文件，某一列
	* @param: @param readFilePathAndFileName:读入路径和文件名F:\\DRGs\\newadd\\990002_武汉市中心医院.csv
	* @param: @return :
	* @return: List<String>  返回list
	* @throws 
	*/
	public static List<String> readCsvOfLine(String readFilePathAndFileName) {
		List<String> csvList = new ArrayList<String>();
		try {
			CsvReader csvReader = new CsvReader(readFilePathAndFileName,',',Charset.forName("UTF-8"));
			csvReader.readHeaders();
			while (csvReader.readRecord()) {
				String value = csvReader.get("src.jzlsh");
				csvList.add(value);
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvList;
	}
	
}

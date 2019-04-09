package com.gennlife.crf.utils;

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
		// 用来保存数据
		List<String> csvList = new ArrayList<String>();

		try {
			// 创建CSV读对象
			CsvReader csvReader = new CsvReader(readFilePathAndFileName,',',Charset.forName("UTF-8"));
			//CsvReader csvReader = new CsvReader(readFilePath,',',Charset.forName("GBK"));
			// 读表头
			csvReader.readHeaders();
			
			// 逐行读入除表头的数据
			while (csvReader.readRecord()) {
				// 读这行的某一列s
				String value = csvReader.get("src.jzlsh");
				csvList.add(value);
			}
			// 关闭
			csvReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return csvList;
	}
	
	
	
}

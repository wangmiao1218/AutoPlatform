package com.gennlife.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description: 通过接口：http://10.0.2.184:8113/EMPI-Server/GetUuid，传入原始编号，返回pat
 * @author: wangmiao
 * @Date: 2018年1月8日 下午3:10:26 
 */
public class GetUuidOfEMPIServerInterface {

	final static String strURL ="http://10.0.2.184:8113/EMPI-Server/GetUuid";

	/**
	 * @Title: getPatsByPostMethod
	 * @Description: 发送HttpPost请求，传入多个原始编号，返回pat，返回一个大json包含多个子json
	 * @param: String patString：传入pat的id集合（注意：pat要带引号）
	 * 		         例如：{  
					  "userId":"qilaijun",
					  "PatientIDs":[
					    {"PatientID":"377473","VisitType":"1"},
					    {"PatientID":"344242","VisitType":"1"},
					    {"PatientID":"344479","VisitType":"1"},
					    {"PatientID":"349784","VisitType":"1"},
					    {"PatientID":"412763","VisitType":"1"}
					    ]
					}
	 * @return: String：返回一个大的json字符串
	 * @throws
	 */
	public static String getPatsByPostMethod(String oldPatStrs) {
		//String strURL = "http://10.0.2.162:8113/EMPI-Server/PatientDetailsInfo";
		String params = "{\"UserId\":\"test\",\"PatientIDs\":[" + oldPatStrs+ "]}";
		try {
			// 创建连接
			URL url = new URL(strURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			// 设置请求方式
			connection.setRequestMethod("POST");
			// 设置接收数据的格式
			connection.setRequestProperty("Accept", "application/json");
			// 设置发送数据的格式
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			// utf-8编码
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			// 发送给接口的json格式内容
			out.append(params);
			out.flush();
			out.close();
			// 读取响应
			// 获取长度
			int length = (int) connection.getContentLength();
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				// utf-8编码
				String result = new String(data, "UTF-8");
				// System.out.println(result);
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 自定义错误信息
		return "error";
	}
	

}

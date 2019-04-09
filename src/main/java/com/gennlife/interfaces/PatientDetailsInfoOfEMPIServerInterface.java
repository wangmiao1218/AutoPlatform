package com.gennlife.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description: 通过接口：http://10.0.2.162:8113/EMPI-Server/PatientDetailsInfo，
 *               传入一个pat返回一个json；或传入多个pat，返回一个大json包含多个子json。
 * @author: wangmiao
 * @Date: 2017年11月27日 上午11:37:33
 */
public class PatientDetailsInfoOfEMPIServerInterface {

	// final static String url ="http://10.0.2.162:8113/EMPI-Server/PatientDetailsInfo";
	// final static String params = "{\"id\":\"12345\"}";
	// final static String params ="{\"UserId\":\"\",\"Uuids\":[\"pat_80984333786fed39043eba14de206d9b\"],\"Source\":[\"PatiName\",\"IDCard\",\"InPatientSn\"]}";

	
	/**
	 * @Title: getResultsByPostMethod
	 * @Description: 发送HttpPost请求，传入多个pat，返回一个大json包含多个子json
	 * @param: String patString：传入pat的id集合（注意：pat要带引号）
	 * 		         例如：["pat_01549152a8bdd9731848671e3bb12900",
				    "pat_0165fecd407e5c9ba9421d4c390d2831",
					"pat_019a52f2f08aa7a837fdf52cb5dbb58c"]
	 * @return: String：返回一个大的json字符串
	 * @throws
	 */
	public static String getResultsByPostMethod(String patStrs) {
		String strURL = "http://10.0.2.162:8113/EMPI-Server/PatientDetailsInfo";
		String params = "{\"UserId\":\"\",\"Uuids\":[" + patStrs
				+ "],\"Source\":[\"PatiName\",\"IDCard\",\"InPatientSn\"]}";
		try {
			URL url = new URL(strURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			out.append(params);
			out.flush();
			out.close();
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
				String result = new String(data, "UTF-8");
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	
	/**
	 * @Title: getOneResultByPostMethod
	 * @Description: 发送HttpPost请求，传入一个pat返回一个json
	 * @param: String patString：传入pat的id（可提炼成params，但属性必须加双引号） 例如:
	 *         "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
	 * @return: String：返回json字符串
	 * @throws
	 */
	public static String getOneResultByPostMethod(String patStr) {
		String strURL = "http://10.0.2.162:8113/EMPI-Server/PatientDetailsInfo";
		String params = "{\"UserId\":\"\",\"Uuids\":[\"" + patStr
				+ "\"],\"Source\":[\"PatiName\",\"IDCard\",\"InPatientSn\"]}";
		try {
			URL url = new URL(strURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			out.append(params);
			out.flush();
			out.close();
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
				String result = new String(data, "UTF-8");
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}

}

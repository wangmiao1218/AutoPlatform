package com.gennlife.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @Description: 通过接口：http://10.0.2.172:6060/auto/ManualEMRAutoCRFV2 （crf组装接口）
 *               传入一个pat返回一个json；或传入多个pat，返回一个大json包含多个子json。20180410：增加接口条件
 * @author: wangmiao
 * @Date: 2017年12月26日 下午2:22:05 
 */
public class ManualEMRAutoCRFV2OfCrfAutoInterface {

	//final static String strURL ="http://10.0.2.184:6060/auto/ManualEMRAutoCRFV2";
	//final static String strURL ="http://10.0.2.175:6060/auto/ManualEMRAutoCRFV2";
	//final static String str ="kidney_cancer";
	//final static String str ="lymphoma";
	
	/**
	 * @Title: getResultsByPostMethod(天津环境)
	 * @Description: 发送HttpPost请求，传入多个pat，返回一个大json
	 * @param: String patString：传入pat的id集合（注意：pat要带引号）
	 *  例如：请求
	 *  {
	 *    "crf_id":"kidney_cancer","list":[ 
     *     		 "pat_6",
     *       	 "pat_7",
     * 			 "pat_25f9f33f82460a5628fbdc85200a45b1"
     * 			]
     * 	}
     * 改成：
     * {
		    "crf_id":"lymphoma_release_1.0",
		    "updateDetail":"FALSE",
		    "list":[
		    "pat_64807f10_4",
		    "pat_308c0cab_5",
		    "pat_bcd53275_9"
		    ]
		}
     * 
	 * 返回{ "pat_6": true,
  	 *		"pat_7": true,
  	 *		"pat_25f9f33f82460a5628fbdc85200a45b1": false
	 *	}
	 * @return: String：返回一个大的json字符串
	 * @throws
	 */
	public static String getResultsByPostMethod(String httpUrl,String disease,String patStrs) {
		String params = "{\"crf_id\":\""+disease+"\",\"updateDetail\":\"FALSE\",\"list\":[" + patStrs+"]}";
		try {
			URL url = new URL(httpUrl);
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

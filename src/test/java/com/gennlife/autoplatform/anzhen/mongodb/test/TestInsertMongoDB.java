package com.gennlife.autoplatform.anzhen.mongodb.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.junit.Test;

public class TestInsertMongoDB {

	@Test
	public void testInsert() throws Exception {
		String fileName="E:\\安贞\\_wm数据json&js\\1.json";
		File file = new File(fileName);
		String str = null;
		FileReader fr = null;
		BufferedReader br = null;
		StringBuffer buf = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			buf = new StringBuffer();
			
			while ((str = br.readLine()) != null) {
				buf = buf.append(str);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(buf);
		
		String s=buf.toString().replace("50b3b83a-cb39-4406-8dd8-64d29747686615dbb8ab022", "21166666-cb39-4406-8dd8-64d29747686615dbb8ab022");
		String s2=s.replace("21100003", "21166666");
		//System.out.println(s2);
		
		//JSONObject jsonObject = JSONObject.fromObject(s2); 
		//JSONObject jsonObject = new JSONObject(s2);
		//System.out.println(jsonObject);
		
	}
	
}

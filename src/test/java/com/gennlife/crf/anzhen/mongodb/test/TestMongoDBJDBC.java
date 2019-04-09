package com.gennlife.crf.anzhen.mongodb.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class TestMongoDBJDBC {

	@Test
	public void testMongoDBConnection() {
		// 连接到 mongodb 服务
		// MongoClient mongoClient = new MongoClient("119.253.137.125", 9001);
		// 连接到数据库
		// MongoDatabase mongoDatabase = mongoClient.getDatabase("CRF_Model");
		// System.out.println("Connect to database successfully");

		try {
			// 连接到MongoDB服务
			// ServerAddress()两个参数分别为 服务器地址 和 端口
			ServerAddress serverAddress = new ServerAddress("119.253.137.125",9001);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);

			// MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
			MongoCredential credential = MongoCredential.createScramSha1Credential("UserCRF_Model", "CRF_Model","@CRF_Model2015".toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);

			// 通过连接认证获取MongoDB连接
			MongoClient mongoClient = new MongoClient(addrs, credentials);

			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase("CRF_Model");
			System.out.println("Connect to database successfully");
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
	}
	
	
}

package com.gennlife.autoplatform.anzhen.mongodb.test;

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
		try {
			ServerAddress serverAddress = new ServerAddress("119.253.137.125",9001);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
			MongoCredential credential = MongoCredential.createScramSha1Credential("UserCRF_Model", "CRF_Model","@CRF_Model2015".toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);
			MongoClient mongoClient = new MongoClient(addrs, credentials);
			MongoDatabase mongoDatabase = mongoClient.getDatabase("CRF_Model");
			System.out.println("Connect to database successfully");
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	
}

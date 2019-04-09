package com.gennlife.autoplatform.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @Description: 连接mongodb
 * @author: wangmiao
 * @Date: 2017年9月14日 下午4:27:55
 */
public class MongodbJDBCUtils {
	private static Logger logger = Logger.getLogger(MongodbJDBCUtils.class); 
	
	private static final String ipYantai="10.0.2.176";

	/** 
	* @Title: connectRwsMongodbReturnMongoCollection 
	* @Description: 链接到rws库，无用户密码
	* @author: wangmiao
	* @Date: 2018年10月30日 下午6:00:01 
	* @param: @param mongodbIp
	* @param: @param dataBaseName
	* @param: @param dbCollectionName
	* @param: @return
	* @return: MongoCollection<Document>
	* @throws 
	*/
	public static MongoCollection<Document> connectRwsMongodbReturnMongoCollection(
			String mongodbIp,String dataBaseName,String dbCollectionName) {
		MongoCollection<Document> mongoCollection = null;
		try {
			ServerAddress serverAddress = new ServerAddress(mongodbIp, 20000);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
			MongoClient mongoClient = new MongoClient(addrs);
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dataBaseName);
			logger.info("Connect to database successfully");
			mongoCollection = mongoDatabase.getCollection(dbCollectionName);
		} catch (Exception e) {
			logger.info(e.getClass().getName() + ": " + e.getMessage());
		}
		return mongoCollection;
	}
	
	
	/** 
	* @Title: connectDevelopMongodbOfInsertReturnMongoCollection 
	* @Description: 连接开发环境mongodb数据库（PatientDetail）,返回MongoCollection<Document>
	* @return: MongoCollection<Document> 返回 MongoCollection<Document>
	* @throws 
	*/
	public static MongoCollection<Document> connectDevelopMongodbOfInsertReturnMongoCollection(
			String mongodbIp,String dataBaseName,String dbCollectionName) {
		MongoCollection<Document> mongoCollection = null;
		try {
			ServerAddress serverAddress = new ServerAddress(mongodbIp, 27017);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
			MongoClient mongoClient = new MongoClient(addrs);
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dataBaseName);
			logger.info("Connect to database successfully");
			mongoCollection = mongoDatabase.getCollection(dbCollectionName);
		} catch (Exception e) {
			logger.info(e.getClass().getName() + ": " + e.getMessage());
		}
		return mongoCollection;
	}
	
	/** 
	* @Title: connectTestMongodbOfInsertReturnMongoCollection(测试环境用于操作数据：如插入、删除等) 
	* @Description: 连接测试环境mongodb数据库（PatientDetail）,返回MongoCollection<Document>
	* @param: @return :
	* @return: MongoCollection<Document> 返回 MongoCollection<Document>
	* @throws 
	*/
	public static MongoCollection<Document> connectTestMongodbOfInsertReturnMongoCollection(
			String mongodbIp,String dataBaseName,String dbCollectionName) {
		MongoCollection<Document> mongoCollection = null;
		try {
			ServerAddress serverAddress = new ServerAddress(mongodbIp, 27017);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
			MongoCredential credential = MongoCredential.createScramSha1Credential("Wangmiao", dataBaseName,"@Wangmiao2015".toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);
			MongoClient mongoClient = new MongoClient(addrs, credentials);
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dataBaseName);
			logger.info("Connect to database successfully");
			mongoCollection = mongoDatabase.getCollection(dbCollectionName);
		} catch (Exception e) {
			logger.info(e.getClass().getName() + ": " + e.getMessage());
		}
		return mongoCollection;

	}
	
	/** 
	 * @Title: connectMongodbOfQueryReturnDBCollection 
	 * @Description: 连接测试环境mongodb数据库,返回DBCollection(用于查询)
	 * (官方文档和源代码均建议使用MongoClient类，而且，在不久的将来，会废弃Mongo类。)
	 * @param: @return :
	 * @return: DBCollection
	 * @throws 
	 */
	public static DBCollection connectMongodbOfQueryReturnDBCollection(String mongodbIp,
			String dataBaseName,String dbCollectionName) {
		DBCollection dbCollection =null;
		try {
			MongoCredential credential = MongoCredential.createCredential("Wangmiao", dataBaseName, "@Wangmiao2015".toCharArray()); 
			ServerAddress serverAddress = new ServerAddress(mongodbIp, 27017);
			MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential)); 
			DB db = mongoClient.getDB(dataBaseName);
			logger.info("Connect to database successfully");
			dbCollection = db.getCollection(dbCollectionName);
		}catch (Exception e) {
			logger.info(e.getClass().getName() + ": " + e.getMessage());
		}
		return dbCollection;
	}
	
//==========================================================================================
	
	/** 
	* @Title: connectYantaiMongodbReturnMongoCollection 
	* @Description: 连接烟台测试环境mongodb数据库,返回MongoCollection<Document>
	* @param: @return :
	* @return: MongoCollection<Document> 返回 MongoCollection<Document>
	* @throws 
	*/
	public static MongoCollection<Document> connectYantaiMongodbReturnMongoCollection() {
		MongoCollection<Document> mongoCollection = null;
		try {
			ServerAddress serverAddress = new ServerAddress(ipYantai, 27017);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
			MongoCredential credential = MongoCredential.createScramSha1Credential("wangmiao", "CRF_Model","@wangmiao2015".toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);
			MongoClient mongoClient = new MongoClient(addrs, credentials);
			MongoDatabase mongoDatabase = mongoClient.getDatabase("CRF_Model");
			logger.info("Connect to database successfully");
			mongoCollection = mongoDatabase.getCollection("patientDetail");
		} catch (Exception e) {
			logger.info(e.getClass().getName() + ": " + e.getMessage());
		}
		return mongoCollection;
	}
	
	/** 
	* @Title: connectYantaiMongodbReturnDBCollection 
	* @Description: 连接烟台测试环境mongodb数据库,返回DBCollection
	* (官方文档和源代码均建议使用MongoClient类，而且，在不久的将来，会废弃Mongo类。)
	* @param: @return :
	* @return: DBCollection
	* @throws 
	*/
	public static DBCollection connectYantaiMongodbReturnDBCollection() {
		DBCollection dbCollection =null;
		try {
			MongoCredential credential = MongoCredential.createCredential("wangmiao", "CRF_Model", "@wangmiao2015".toCharArray()); 
			ServerAddress serverAddress = new ServerAddress(ipYantai, 27017);
			MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential)); 
			DB db = mongoClient.getDB("CRF_Model");
			logger.info("Connect to database successfully");
			dbCollection = db.getCollection("patientDetail");
		}catch (Exception e) {
			logger.info(e.getClass().getName() + ": " + e.getMessage());
		}
		return dbCollection;
	}
	
}

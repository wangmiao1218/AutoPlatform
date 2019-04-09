package com.gennlife.crf.utils;
/*
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
*/

import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description: 
 * @author: wangmiao
 * @Date: 2018年6月26日 下午12:36:33 
 */
public class HbaseJDBCUtils {
	
	public static void main(String[] args) {
	}
    // 用HBaseconfiguration初始化配置信息是会自动加载当前应用的classpath下的hbase-site.xml
   /* public static Configuration configuration = HBaseConfiguration.create();

    public HbaseTest() throws Exception {
        // 对connection进行初始化、
        // 当然也可以手动加载配置文件，手动加载配置文件时要调用configuration的addResource方法
        // configuration.addResource("hbase-site.xml");
        connection = ConnectionFactory.createConnection(configuration);
    }*/
	
/*
    private ConcurrentLinkedQueue<Connection> connections = new ConcurrentLinkedQueue<Connection>();
    private Connection connection;
    public Connection getHbaseConnection() throws Exception{
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop1.in.gennlife.cn,hadoop2.in.gennlife.cn,hadoop3.in.gennlife.cn");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        connection = ConnectionFactory.createConnection(conf);
        System.out.println("==========");
	        return connection;
	}*/
    
}

package com.gennlife.autoplatform.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * @Description: 连接mysql
 * @author: wangmiao
 * @Date: 2017年12月18日 下午4:59:57
 */
public class MysqlJDBCUtils {
	private static Logger logger = Logger.getLogger(MysqlJDBCUtils.class); 
	
	// 避免中文乱码要指定useUnicode和characterEncoding
	private static final String url = "jdbc:mysql://10.0.2.238:9003/gennlife_tjzl?"
			+ "user=gennlife_tjzl_user&password=@Gennlife_tjzl2015&useUnicode=true&characterEncoding=UTF8";
	
	/**
	 * @Title: connectTianjinMysqlReturnResultSetByExecuteQuery
	 * @Description: 连接天津mysql单表数据库,根据表名和字段名查询，返回符合条件的一个map
	 * @param: @param sql
	 * @param: @return
	 * @return: ResultSet
	 * @throws
	 */
	public static ResultSet connectTianjinMysqlReturnResultSetByExecuteQuery(String sql) throws SQLException{
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			// or:
			// com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			// or：
			// new com.mysql.jdbc.Driver();
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			logger.info("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//conn.close();
		}
		return rs;
	}
	
}

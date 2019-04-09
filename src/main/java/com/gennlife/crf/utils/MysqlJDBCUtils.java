package com.gennlife.crf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description: 连接mysql
 * @author: wangmiao
 * @Date: 2017年12月18日 下午4:59:57
 */
public class MysqlJDBCUtils {
	
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
			// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
			// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			// or:
			// com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			// or：
			// new com.mysql.jdbc.Driver();
			//System.out.println("成功加载MySQL驱动程序");
			// 一个Connection代表一个数据库连接
			conn = DriverManager.getConnection(url);
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			Statement stmt = conn.createStatement();
			// executeQuery会返回结果的集合，否则返回空值
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//conn.close();
		}
		return rs;
	}
	
}

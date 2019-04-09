package com.gennlife.autoplatform.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.gennlife.autoplatform.utils.MysqlJDBCUtils;

/**
 * @Description: 天津的mysql的数据处理
 * @author: wangmiao
 * @Date: 2017年12月18日 下午2:38:10
 */
public class TianjinMysqlDataProcess {

	/**
	 * @Title: getRequiredValueMapByCondition
	 * @Description: 根据表名、字段名、查询条件以及返回个数，返回符合条件的map,包含PATIENT_SN和fieldName
	 * @param: @param tableName
	 * @param: @param fieldName
	 * @param: @param condition
	 * @param: @param num
	 * @param: @throws SQLException :
	 * @return: Map<String,String>
	 * @throws
	 */
	public static Map<String, String> getRequiredValueMapByCondition(
			String tableName, String fieldName, String condition, Integer num)
			throws SQLException {
		String sql = "select PATIENT_SN," + fieldName + " from " + tableName
				+ " where " + fieldName + " like \"%" + condition
				+ "%\" limit 0," + num + "";

		Map<String, String> map = new HashedMap<String, String>();
		ResultSet rs = MysqlJDBCUtils
				.connectTianjinMysqlReturnResultSetByExecuteQuery(sql);
		while (rs.next()) {
			map.put(rs.getString(1), rs.getString(2));
		}
		return map;
	}

}

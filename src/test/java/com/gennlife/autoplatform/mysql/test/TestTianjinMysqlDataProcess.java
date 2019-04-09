package com.gennlife.autoplatform.mysql.test;

import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.gennlife.autoplatform.mysql.TianjinMysqlDataProcess;

public class TestTianjinMysqlDataProcess {
	
	@Test
	public void getOneValuesMapByCondition() throws SQLException {
		String tableName = "operation_pre_conference_records";
		String fieldName = "DISCUSSION_CONTENT";
		String condition = "RENAL";

		Map<String, String> map = TianjinMysqlDataProcess
				.getRequiredValueMapByCondition(tableName, fieldName,condition, 3);
		for (Entry entry : map.entrySet()) {
			System.out.println(entry.getKey() + "--" + entry.getValue());
		}

	}

}

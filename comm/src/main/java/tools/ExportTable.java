package tools;

import utils.pojo.DbName;

public class ExportTable {

	public static void main(String[] args) {
		//导出数据库中的所有表
		String tableName = "USER_TAB_COMMENTS";
		ExportTableTools.ExportSingleTable(DbName.ORACLE,tableName);
	}

}

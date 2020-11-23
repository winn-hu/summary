package tools;

import utils.ExcelUtils;
import utils.JDBCUtil;
import utils.pojo.DbName;

import java.util.List;
import java.util.Map;

public class ExportTableTools {

    public static void ExportMultipleTable(DbName dbName, List<String> tables) {
        for(String table : tables) {
            ExportSingleTable(dbName,table);
        }
    }

    public static void ExportSingleTable(DbName dbName, String tableName) {
        try {
            List<Map<String, Object>> dataList = JDBCUtil.query(dbName, "select * from " + tableName);
            ExcelUtils.write(tableName,dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

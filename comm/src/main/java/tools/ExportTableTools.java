package tools;

import utils.ExcelUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExportTableTools {

    public static void ExportMultipleTable(List<String> tables) {
        for(String table : tables) {
            ExportSingleTable(table);
        }
    }

    public static void ExportSingleTable(String tableName) {
        try {
            //List<Map<String, Object>> dataList = JDBCUtil.query("select * from " + tableName, new ArrayList<>(0));
            List<Map<String, Object>> dataList = new ArrayList<>();
            ExcelUtils.write(tableName,dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

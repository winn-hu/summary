package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlUtils {

    public static List<Map<String,Object>>  ResultSetToList(ResultSet rs) throws SQLException {
        List<Map<String,Object>> result = new ArrayList<>();
        ResultSetMetaData mataData = rs.getMetaData();
        //the count of records
        int count = mataData.getColumnCount();

        while(rs.next()){
            Map<String, Object> record = new HashMap<>();
            for(int i = 1; i <= count; i++){
                String columnName = mataData.getColumnName(i);
                record.put(columnName,rs.getObject(i));
            }
            result.add(record);
        }
        return result;
    }

}

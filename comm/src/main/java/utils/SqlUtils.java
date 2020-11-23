package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlUtils {

    public static final int SQL_BUILDER_TYPE_APPEND = 1;
    public static final int SQL_BUILDER_TYPE_PLUS = 2;

    /**
     * Éú³Ésql×Ö¶Î´Ü´úÂë
     * @param type
     * @param path
     */
    public static void sqlBuilder(int type, String path){
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            if(type == SQL_BUILDER_TYPE_APPEND){
                System.out.println("StringBuilder sql = new StringBuilder();");
                System.out.println("sql");
                String context;
                while((context = br.readLine()) != null){
                    System.out.println(String.format(".append(\" %s \\n\")", context));
                }
            }else{
                String context;
                while((context = br.readLine()) != null){
                    System.out.println(String.format("\" %s \\n\"+", context));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String,Object>> ResultSetToList(ResultSet rs) throws SQLException {
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        ResultSetMetaData mataData = rs.getMetaData();
        //the count of records
        int count = mataData.getColumnCount();

        while(rs.next()){
            Map<String, Object> record = new HashMap<String, Object>();
            for(int i = 1; i <= count; i++){
                String columnName = mataData.getColumnName(i);
                record.put(columnName,rs.getObject(i));
            }
            result.add(record);
        }
        return result;
    }

}

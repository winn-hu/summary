package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
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

    public static void setParameters(PreparedStatement pstmt, List<Object> params) throws SQLException {
        if (params == null) return;

        for (int i = 0,size = params.size(); i < size; i++) {
            Object o = params.get(i);
            if (o instanceof String) {
                pstmt.setString(i + 1, (String) o);
            } else if (o instanceof Number) {
                if (o instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) o);
                } else if (o instanceof Long) {
                    pstmt.setLong(i + 1, (Long) o);
                } else if (o instanceof BigDecimal) {
                    pstmt.setBigDecimal(i + 1, (BigDecimal) o);
                } else if (o instanceof Float) {
                    pstmt.setFloat(i + 1, (Float) o);
                } else if (o instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) o);
                } else if (o instanceof Byte) {
                    pstmt.setByte(i + 1, (Byte) o);
                } else if (o instanceof Short) {
                    pstmt.setShort(i + 1, (Short) o);
                }
            } else if (o instanceof Date) {
                pstmt.setDate(i + 1, (Date) o);
            } else if (o instanceof Timestamp) {
                pstmt.setTimestamp(i + 1, (Timestamp) o);
            } else if (o instanceof Boolean) {
                pstmt.setBoolean(i + 1, (Boolean) o);
            } else if (o instanceof Time) {
                pstmt.setTime(i + 1, (Time) o);
            } else {
                pstmt.setObject(i + 1, o);
            }
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

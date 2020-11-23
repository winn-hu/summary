package utils;

import java.sql.*;
import java.util.*;

import static utils.SqlUtils.ResultSetToList;

public class JDBCUtil {

	private static final String DB_NAME_ORACLE = "ORACLE";
	private static final String DB_NAME_MYSQL = "MYSQL";

	public static Connection getConection() throws Exception{
		String driver = "";
		String url = "";
		String username = "";
		String password = "";
		Properties properties = PropertyUtil.instance("/db.properties");

		String dbName = properties.getProperty("dbName").toUpperCase();
		if(DB_NAME_MYSQL.equals(dbName)) {
			driver = properties.getProperty("mysql.driver");
			url = properties.getProperty("mysql.url");
			username = properties.getProperty("mysql.username");
			password = properties.getProperty("mysql.password");
		}else if(DB_NAME_ORACLE.equals(dbName)){
			driver = properties.getProperty("oracle.driver");
			url = properties.getProperty("oracle.url");
			username = properties.getProperty("oracle.username");
			password = properties.getProperty("oracle.password");
		}
		Class.forName(driver);
		return DriverManager.getConnection(url,username,password);
	}

	public static List<Map<String, Object>> query(String sql, List<Object> params) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConection();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				pst.setObject(i+1, params.get(i));
			}
			rs=pst.executeQuery();
			dataList = ResultSetToList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pst, rs);
		}
		return dataList;
	}

	public static void update(String sql, List<Object> params){
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = JDBCUtil.getConection();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				pst.setObject(i+1, params.get(i));
			}
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(conn, pst, null);
		}
	}
	
	public static void close(Connection conn,PreparedStatement pst,ResultSet rs){
		try {
			//先赋值的后关闭
			if(rs != null){
				rs.close();
			}
			if(pst != null){
				pst.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}



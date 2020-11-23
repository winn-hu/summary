package utils;

import utils.pojo.DbName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {

	public static Connection getConection(DbName dbName) throws Exception{
		String driver = "";
		String url = "";
		String username = "";
		String password = "";
		Properties properties = PropertyUtil.instance("/db.properties");
		if(DbName.MYSQL.equals(dbName)) {
			driver = properties.getProperty("mysql.driver");
			url = properties.getProperty("mysql.url");
			username = properties.getProperty("mysql.username");
			password = properties.getProperty("mysql.password");
		}else if(DbName.ORACLE.equals(dbName)){
			driver = properties.getProperty("oracle.driver");
			url = properties.getProperty("oracle.url");
			username = properties.getProperty("oracle.username");
			password = properties.getProperty("oracle.password");
		}
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,username,password);
		return conn;
	}

	public static List<Map<String, Object>> query(DbName dbName,String sql) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConection(dbName);
			sta=conn.createStatement();
			rs=sta.executeQuery(sql);
			dataList = SqlUtils.ResultSetToList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, sta, rs);
		}
		return dataList;
	}
	
	public static void close(Connection conn,Statement sta,ResultSet rs){
		try {
			//先赋值的后关闭
			if(rs != null){
				rs.close();
			}
			if(sta != null){
				sta.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}



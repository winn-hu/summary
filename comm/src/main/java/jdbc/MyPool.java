package jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyPool {
	
	private final String URL = "jdbc:mysql://localhost:3306/test";
	private final  String USER = "root";
	private final  String PASSWORD = "root";
	
	private final  int INIT_COUNT = 3;
	private final  int MAX_COUNT = 6;
	
	private int current_count = 0;
	private LinkedList<Connection> connPool = new LinkedList<Connection>();
	
	public MyPool() {
		for (int i = 0; i < INIT_COUNT; i++) {
			connPool.addLast(newConnection());
		}
	}
	
	public  Connection getConnection(){
		Connection conn = null;
		if(connPool.size() > 0){
			conn = connPool.removeFirst();
		}else if(current_count < MAX_COUNT){
			conn = newConnection();
		}else{
			throw new RuntimeException("当前连接数已达到最大值！");
		}
		++current_count;
		
		return conn;
	}

	private  Connection newConnection() {
		Connection proxyConn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			final Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			proxyConn = (Connection)Proxy.newProxyInstance(conn.getClass().getClassLoader(),new Class[]{Connection.class}, 
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args)
								throws Throwable {
							Object result = null;
							String methodName = method.getName();
							if("close".equals(methodName)){
								if(connPool.size() < INIT_COUNT){
									connPool.addLast(conn);
								}else{
									result = method.invoke(conn, args);
								}
								--current_count;
							}else{
								result = method.invoke(conn, args);
							}
							return result;
						}
					}
			);
			
		} catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		}
		return proxyConn;
	}
	
}

package com.blueStarWei.spring.properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-properties.xml");
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource.getConnection());
	}

}

package com.blueStarWei.spring.cycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-cycle.xml");
		
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		
		ctx.close();
	}

}

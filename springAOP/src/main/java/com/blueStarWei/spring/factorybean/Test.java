package com.blueStarWei.spring.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-beanfactory.xml");
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);

	}

}

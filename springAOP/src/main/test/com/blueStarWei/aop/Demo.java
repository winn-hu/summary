package com.blueStarWei.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonService service = (PersonService) context.getBean("personService");
		service.printPerson();
	}
}

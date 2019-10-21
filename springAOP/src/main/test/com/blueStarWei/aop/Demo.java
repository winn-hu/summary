package com.blueStarWei.aop;

import com.blueStarWei.aop.service.CaculationService;
import com.blueStarWei.aop.service.ComparableService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void testAdvice() {
		CaculationService service = (CaculationService) context.getBean("caculationService");
		service.add(2,3);
		System.out.println(">>>>>>>>>华丽的分割线>>>>>>>>>>>>>>>>>>>>>>>>");
		service.sub(2,6);
		System.out.println(">>>>>>>>>华丽的分割线>>>>>>>>>>>>>>>>>>>>>>>>");
		service.divid(1,0);
		System.out.println(">>>>>>>>>华丽的分割线>>>>>>>>>>>>>>>>>>>>>>>>");

	}

	@Test
	public void testDeclareParents() {
		ComparableService comparableService = (ComparableService) context.getBean("caculationService",ComparableService.class);
		System.out.println("max is "+comparableService.max(4,9));
	}
}

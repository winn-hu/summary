package com.blueStarWei.aop;

import com.blueStarWei.aop.service.CalculationService;
import com.blueStarWei.aop.service.ComparableService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void testAdvice() {
		CalculationService service = (CalculationService) context.getBean("calculationService");
		service.add(2,3);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		service.sub(2,6);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		service.divid(1,0);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

	}

	@Test
	public void testDeclareParents() {
		ComparableService comparableService = (ComparableService) context.getBean("caculationService",ComparableService.class);
		System.out.println("max is "+comparableService.max(4,9));
	}
}

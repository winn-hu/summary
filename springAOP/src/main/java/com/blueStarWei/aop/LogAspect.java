package com.blueStarWei.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	@Before("execution(* com.blueStarWei.aop.PersonService.printPerson())")
	public void beforeMethod(){
		System.out.println("before....");
	} 
}

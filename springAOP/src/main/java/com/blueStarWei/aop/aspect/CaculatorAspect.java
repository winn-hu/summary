package com.blueStarWei.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;

/**
 * 切点表达式： 修饰符 含包路径的类名.方法名(参数)
 * 		修饰符、含包路径的类名、方法名 ：可以使用*进行模糊指定
 * 		参数 可以使用..模糊指定（无参时可以省略）
 * 切入点表达式可以通过操作符 &&, ||, ! 结合起来
 * <code>@Order</code> 指定切面的优先级，数字越小，优先级越高
 */
@Aspect
@Component
@Order(0)
public class CaculatorAspect {

	/**
	 *
	 * @param joinPoint joinPoint.getArgs() : 获取参数
	 */
	@Before("execution(* *.add(..))")
	public void beforeMethod(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		System.out.printf("before add(%s,%s)....\n", args[0],args[1]);
	}

	/**
	 * 无论连接点是正常返回还是抛出异常,都会触发.
	 * com.blueStarWei.aop包下的任意类中的任意方法
	 */
	@After("execution(* com.blueStarWei.aop.*.*(..))" )
	public void afterMethod(){
		System.out.println("after....");
	}

	/**
	 *只在连接点返回的时候触发【发生异常不会触发】
	 * @param joinPoint 获取请求参数等
	 * @param result 在返回通知中, 只要将 returning 属性添加到 @AfterReturning 注解中, 才可以访问连接点的返回值. 该属性的值即为用来传入返回值的参数名称.
	 */
	@AfterReturning(value = "execution(public int *.sub(int,int))",returning = "result")
	public void returnMethod(JoinPoint joinPoint, Object result){
		Object[] args = joinPoint.getArgs();
		System.out.printf("%s - %s = %s \n",args[0],args[1],result);
	}

	/**
	 *
	 * @param joinPoint
	 * 		joinPoint.getSignature().getName() : 返回方法名
	 * 		joinPoint.getSignature().getDeclaringTypeName() : 获取文件的包路径
	 * @param e 需要使用throwing = "e"指定参数名
	 */
	@AfterThrowing(value = "execution(public int com.blueStarWei.aop.service.CaculationService.divid(..))",throwing = "e")
	public void afterThrowingMethod(JoinPoint joinPoint, ArithmeticException e) {
		System.out.printf("An exception(%s) has been throwing in %s.",e.getMessage(),joinPoint.getSignature().getName());
	}

	/**
	 * 连接点的参数类型必须是 ProceedingJoinPoint
	 * @param point
	 *  	必须调用 ProceedingJoinPoint 的 proceed() 方法来执行被代理的方法. 如果忘记这样做就会导致通知被执行了, 但目标方法没有被执行
	 * 	    point.proceed()  ： 执行切入点的方法
	 * @return  切入点的返回值
	 * @throws Throwable proceed()需要抛出异常
	 */
	@Around(value = "execution(* *.*(..))")
	public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
		System.out.println("around ： before invoking...");
		Object result = point.proceed();
		System.out.println("around : after invoking...");
		return result;
	}
}

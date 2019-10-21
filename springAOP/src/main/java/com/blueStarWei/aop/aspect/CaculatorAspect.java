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
 * �е���ʽ�� ���η� ����·��������.������(����)
 * 		���η�������·���������������� ������ʹ��*����ģ��ָ��
 * 		���� ����ʹ��..ģ��ָ�����޲�ʱ����ʡ�ԣ�
 * �������ʽ����ͨ�������� &&, ||, ! �������
 * <code>@Order</code> ָ����������ȼ�������ԽС�����ȼ�Խ��
 */
@Aspect
@Component
@Order(0)
public class CaculatorAspect {

	/**
	 *
	 * @param joinPoint joinPoint.getArgs() : ��ȡ����
	 */
	@Before("execution(* *.add(..))")
	public void beforeMethod(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		System.out.printf("before add(%s,%s)....\n", args[0],args[1]);
	}

	/**
	 * �������ӵ����������ػ����׳��쳣,���ᴥ��.
	 * com.blueStarWei.aop���µ��������е����ⷽ��
	 */
	@After("execution(* com.blueStarWei.aop.*.*(..))" )
	public void afterMethod(){
		System.out.println("after....");
	}

	/**
	 *ֻ�����ӵ㷵�ص�ʱ�򴥷��������쳣���ᴥ����
	 * @param joinPoint ��ȡ���������
	 * @param result �ڷ���֪ͨ��, ֻҪ�� returning ������ӵ� @AfterReturning ע����, �ſ��Է������ӵ�ķ���ֵ. �����Ե�ֵ��Ϊ�������뷵��ֵ�Ĳ�������.
	 */
	@AfterReturning(value = "execution(public int *.sub(int,int))",returning = "result")
	public void returnMethod(JoinPoint joinPoint, Object result){
		Object[] args = joinPoint.getArgs();
		System.out.printf("%s - %s = %s \n",args[0],args[1],result);
	}

	/**
	 *
	 * @param joinPoint
	 * 		joinPoint.getSignature().getName() : ���ط�����
	 * 		joinPoint.getSignature().getDeclaringTypeName() : ��ȡ�ļ��İ�·��
	 * @param e ��Ҫʹ��throwing = "e"ָ��������
	 */
	@AfterThrowing(value = "execution(public int com.blueStarWei.aop.service.CaculationService.divid(..))",throwing = "e")
	public void afterThrowingMethod(JoinPoint joinPoint, ArithmeticException e) {
		System.out.printf("An exception(%s) has been throwing in %s.",e.getMessage(),joinPoint.getSignature().getName());
	}

	/**
	 * ���ӵ�Ĳ������ͱ����� ProceedingJoinPoint
	 * @param point
	 *  	������� ProceedingJoinPoint �� proceed() ������ִ�б�����ķ���. ��������������ͻᵼ��֪ͨ��ִ����, ��Ŀ�귽��û�б�ִ��
	 * 	    point.proceed()  �� ִ�������ķ���
	 * @return  �����ķ���ֵ
	 * @throws Throwable proceed()��Ҫ�׳��쳣
	 */
	@Around(value = "execution(* *.*(..))")
	public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
		System.out.println("around �� before invoking...");
		Object result = point.proceed();
		System.out.println("around : after invoking...");
		return result;
	}
}

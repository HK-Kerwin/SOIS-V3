package com.tedu.sois.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class SysTimeAspect {
	//@Pointcut("bean(sysUserServiceImpl)")
	//@Pointcut("bean(*ServiceImpl)")
	@Pointcut("@annotation(com.tedu.sois.annotation.RequiredLog)")
	public void doTime(){}
	
	@Before("doTime()")
	public void doBefore(JoinPoint jp){
		System.out.println("SysTimeAspect.time doBefore()");
	}
	
	@After("doTime()")
	public void doAfter(){
		System.out.println("SysTimeAspect.time doAfter()");
	}
	/**核心业务正常结束时执行
	 * 说明：假如有after，先执行after,再执行returning*/
	@AfterReturning("doTime()")
	public void doAfterReturning(){
		System.out.println("SysTimeAspect.time doAfterReturning");
	}
	/**核心业务出现异常时执行
	 * 说明：假如有after，先执行after,再执行Throwing*/
	@AfterThrowing("doTime()")
	public void doAfterThrowing(){
		System.out.println("SysTimeAspect.time doAfterThrowing");
	}

	/**
	 * 环绕通知
	 * @param jp 连接对象,但ProceedingJoinPoint对象只能应用于环绕通知方法
	 * @return
	 * @throws Throwable
	 */
	@Around("doTime()")
	public Object doAround(ProceedingJoinPoint jp) throws Throwable{
		try {
			System.out.println("SysTimeAspect.doAround.before");
			Object obj=jp.proceed();
			System.out.println("SysTimeAspect.doAround.after");
			return obj;
		} catch (Throwable e){
			System.out.println("SysTimeAspect.around.exception");
			throw  e;
		}
	}

}

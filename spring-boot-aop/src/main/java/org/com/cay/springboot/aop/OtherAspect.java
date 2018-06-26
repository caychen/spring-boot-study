package org.com.cay.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2017/10/23.
 */
@Aspect
@Component
@Order(2)
public class OtherAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(public * org.com.cay.springboot.controller..*.*(..))")
	public void otherPointCut() {
	}

	@Before("otherPointCut()")
	public void doBefore() {
		System.out.println("otherAspect before...");
	}

	@Around("otherPointCut()")
	public Object doRround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("开始时间：" + System.currentTimeMillis());
		Thread.sleep(2000);

		//执行后序操作
		Object proceed = proceedingJoinPoint.proceed();

		logger.info("结束时间：" + System.currentTimeMillis());
		return proceed;
	}

	@AfterReturning(pointcut = "otherPointCut()")
	public void doAfter() {
		System.out.println("otherAspect after...");
	}
}

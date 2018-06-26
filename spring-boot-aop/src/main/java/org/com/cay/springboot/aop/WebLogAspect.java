package org.com.cay.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by Cay on 2017/10/23.
 */
@Aspect
@Component
@Order(1)//定义切面的优先级，值越小，优先级越高
public class WebLogAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private ThreadLocal<Long> time = new ThreadLocal<>();

	@Pointcut("execution(public * org.com.cay.springboot.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {

		time.set(System.currentTimeMillis());

		//获取请求
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		//记录请求的信息
		logger.info("URL: " + request.getRequestURL().toString());
		logger.info("Method: " + request.getMethod());
		logger.info("IP: " + request.getRemoteAddr());
		logger.info("Class_Method: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info("Args: " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(returning = "result", pointcut = "webLog()")
	public void doReturn(Object result) {
		logger.info("消耗时间：" + (System.currentTimeMillis() - time.get()));

		logger.info("Result: " + result.toString());
	}
}

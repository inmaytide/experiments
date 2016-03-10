package com.inmaytide.framework.aop.springdemo;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

@Component
public class GreetingBeforeAndAfterAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(GreetingBeforeAndAfterAdvice.class);
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		logger.info("After");
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		logger.info("Before");
	}

}

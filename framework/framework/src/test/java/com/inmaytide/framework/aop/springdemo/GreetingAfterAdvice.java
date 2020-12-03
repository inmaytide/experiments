///**
// * 
// */
//package com.inmaytide.framework.aop.springdemo;
//
//import java.lang.reflect.Method;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.AfterReturningAdvice;
//import org.springframework.stereotype.Component;
//
///**
// * @author Administrator
// *
// */
//@Component
//public class GreetingAfterAdvice implements AfterReturningAdvice {
//	
//	private static final Logger logger = LoggerFactory.getLogger(GreetingAfterAdvice.class);
//
//	/* (non-Javadoc)
//	 * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
//	 */
//	@Override
//	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
//		logger.info("After");
//	}
//
//}

//package com.inmaytide.framework.aop.springdemo;
//
//import java.lang.reflect.Method;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.ThrowsAdvice;
//import org.springframework.stereotype.Component;
//
//@Component
//public class GreetingThrowAdvice implements ThrowsAdvice {
//	
//	private static final Logger logger = LoggerFactory.getLogger(GreetingThrowAdvice.class);
//	
//	public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
//		logger.info("-----------------Throw Exception-----------------");
//		logger.info("Target Class      :" + target.getClass().getName());
//		logger.info("Method Name       :" + method.getName());
//		logger.info("Exception Message :" + e.getMessage() );
//		logger.info("-------------------------------------------------");
//	}
//}

///**
// * 
// */
//package com.inmaytide.framework.aop.springdemo;
//
//import org.aopalliance.intercept.MethodInterceptor;
//import org.aopalliance.intercept.MethodInvocation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @author Administrator
// *
// */
//@Component
//public class GreetingAroundAdvice implements MethodInterceptor {
//	
//	private static final Logger logger = LoggerFactory.getLogger(GreetingAroundAdvice.class);
//
//	/* (non-Javadoc)
//	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
//	 */
//	@Override
//	public Object invoke(MethodInvocation invocation) throws Throwable {
//		before();
//		Object result = invocation.proceed();
//		after();
//		return result;
//	}
//	
//	private void before() {
//		logger.info("before");
//	}
//	
//	private void after() {
//		logger.info("after");
//	}
//
//}

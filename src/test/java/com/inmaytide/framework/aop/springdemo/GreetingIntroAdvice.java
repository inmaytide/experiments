///**
// * 
// */
//package com.inmaytide.framework.aop.springdemo;
//
//import org.aopalliance.intercept.MethodInvocation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.support.DelegatingIntroductionInterceptor;
//import org.springframework.stereotype.Component;
//
///**
// * @author inmaytide
// *
// */
//@Component
//public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -1375635091770567666L;
//	private static final Logger logger = LoggerFactory.getLogger(GreetingIntroAdvice.class);
//	
//	@Override
//	public void saySorry(String name) {
//		logger.info("Sorry! " + name);
//	}
//
//	@Override
//	public Object invoke(MethodInvocation mi) throws Throwable {
//		return super.invoke(mi);
//	}
//	
//	
//	
//}

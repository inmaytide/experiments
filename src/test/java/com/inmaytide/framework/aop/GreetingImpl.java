///**
// * 
// */
//package com.inmaytide.framework.aop;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.inmaytide.framework.aop.spring_aspectj.Tag;
//
///**
// * @author Administrator
// *
// */
//@Component
//public class GreetingImpl implements Greeting {
//	
//	private static final Logger logger = LoggerFactory.getLogger(GreetingImpl.class);
//	
//	/* (non-Javadoc)
//	 * @see com.inmaytide.framework.aop.Geeting#say(java.lang.String)
//	 */
//	@Override
//	@Tag
//	public void say(String name) {
//		logger.info("Hello! " + name);
//	//	throw new RuntimeException("Error");
//	}
//	
//	public void goodMorning(String name) {
//		logger.info("Good morning! " + name);
//	}
//	
//	public void goodNight(String name) {
//		logger.info("Good night! " + name);
//	}
//	
//}

package com.inmaytide.framework.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingProxy implements Greeting {
	
	private static final Logger logger = LoggerFactory.getLogger(GreetingProxy.class);
	
	private Greeting target;
	
	public GreetingProxy(Greeting inst) {
		this.target = inst;
	}

	@Override
	public void say(String name) {
		before();
		target.say(name);
		after();
	}
	
	private void before() {
		logger.info("before");
	}
	
	private void after() {
		logger.info("after");
	}

}

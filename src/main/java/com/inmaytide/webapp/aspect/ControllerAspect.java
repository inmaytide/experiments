/**
 * 
 */
package com.inmaytide.webapp.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmaytide.framework.annotation.Aspect;
import com.inmaytide.framework.annotation.Controller;
import com.inmaytide.framework.proxy.AspectProxy;

/**
 * 拦截Controller所有方法
 * @author inmaytide
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
	
	private long begin;

	@Override
	public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
		logger.debug("--------------------- begin ---------------------");
		logger.debug(String.format("Class: %s", cls.getName()));
		logger.debug(String.format("Method: %s", method.getName()));
		begin = System.currentTimeMillis();
	}

	@Override
	public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
		logger.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
		logger.debug("---------------------- end ----------------------");
	}
	
	
	
}

/**
 * 
 */
package com.inmaytide.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class JDKDynamicProxy implements InvocationHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(JDKDynamicProxy.class);
	
	private Object target;

	public JDKDynamicProxy(Object target) {
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}
	
	private void before() {
		logger.info("before");
	}
	
	private void after() {
		logger.info("after");
	}

}

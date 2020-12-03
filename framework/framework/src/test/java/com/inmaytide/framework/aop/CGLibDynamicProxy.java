package com.inmaytide.framework.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibDynamicProxy implements MethodInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(GreetingProxy.class);
	
	private CGLibDynamicProxy() {}
	
	private static final class CGLibDynamicProxyHolder {
		private static final CGLibDynamicProxy instance = new CGLibDynamicProxy();
	}
	
	public static CGLibDynamicProxy getInstance() {
		return CGLibDynamicProxyHolder.instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<?> cls) {
		return (T) Enhancer.create(cls, this);
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object result = proxy.invokeSuper(obj, args);
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

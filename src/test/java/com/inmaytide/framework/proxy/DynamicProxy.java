/**
 * 
 */
package com.inmaytide.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author inmaytide
 *
 */
public class DynamicProxy implements InvocationHandler {
	
	private Object target;
	
	public DynamicProxy(Object target) {
		this.target = target;
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
	
	public void before() {
		System.out.println("before");
	}
	
	public void after() {
		System.out.println("after");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

}

/**
 * 
 */
package com.inmaytide.framework.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理链
 * proxyIndex充当代理对象计数器，若尚未达到proxyList的上限，则重proxyList中取出Proxy对象，并调用doProxy方法。
 * 在{@link Proxy}的接口实现类中提供相应的横切逻辑，并调用{@link ProxyChain.doProxyChain}方法,随后再次调用当前ProxyChain
 * 对象的doProxyChain方法，知道proxyIndex达到proxyList的上限为止，最后调用methodProxy的invokeSuper方法，执行目标对象的业务逻辑。
 * @author inmaytide
 */
public class ProxyChain {
	
	private final Class<?> targetClass;
	private final Object targetObject;
	private final Method targetMethod;
	private final MethodProxy methodProxy;
	private final Object[] methodParams;
	private List<Proxy> proxyList = new ArrayList<Proxy>();
	private int proxyIndex = 0;
	
	public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,
			Object[] methodParams, List<Proxy> proxyList) {
		this.targetClass = targetClass;
		this.targetObject = targetObject;
		this.targetMethod = targetMethod;
		this.methodProxy = methodProxy;
		this.methodParams = methodParams;
		this.proxyList = proxyList;
	}
	
	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
	public Object doProxyChain() throws Throwable {
		Object methodResult;
		if (proxyIndex < proxyList.size()) {
			methodResult = proxyList.get(proxyIndex++).doProxy(this);
		} else {
			methodResult = methodProxy.invokeSuper(targetObject, methodParams);
		}
		return methodResult;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Object getTargetObject() {
		return targetObject;
	}

	public Method getTargetMethod() {
		return targetMethod;
	}

	public MethodProxy getMethodProxy() {
		return methodProxy;
	}

	public Object[] getMethodParams() {
		return methodParams;
	}
	
}

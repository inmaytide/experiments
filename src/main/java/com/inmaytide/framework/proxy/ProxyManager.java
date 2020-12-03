/**
 * 
 */
package com.inmaytide.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理管理器<br/>
 * 提供创建代理对象的方法, 输入一个目标类和一组Proxy接口实现
 * @author inmaytide
 * @since 1.0.0
 * @see Proxy
 */
public class ProxyManager {
	
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
		return (T) Enhancer.create(targetClass, new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				return new ProxyChain(targetClass, obj, method, proxy, args, proxyList).doProxyChain();
			}
		});
	}

}

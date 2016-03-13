/**
 * 
 */
package com.inmaytide.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 切面代理
 * @author inmaytide
 * @since 1.0.0
 */
public abstract class AspectProxy implements Proxy {
	
	public static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

	/* (non-Javadoc)
	 * @see com.inmaytide.framework.proxy.Proxy#doProxy(com.inmaytide.framework.proxy.ProxyChain)
	 */
	@Override
	public Object doProxy(ProxyChain chain) throws Throwable {
		Object result = null;
		Class<?> cls = chain.getTargetClass();
		Method method = chain.getTargetMethod();
		Object[] params = chain.getMethodParams();
		
		begin();
		try {
			if (intercept(cls, method, params)) {
				before(cls, method, params);
				result = chain.doProxyChain();
				after(cls, method, params);
			} else {
				result = chain.doProxyChain();
			}
		} catch (Exception e) {
			logger.error("proxy failure", e);
			error(cls, method, params, e);
			throw e;
		} finally {
			end();
		}
		return result;
	}

	public void end() {
	}

	public void error(Class<?> cls, Method method, Object[] params, Exception e) throws Throwable {
	}

	public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
	}

	public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
	}

	public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
		return true;
	}

	public void begin() {
		
	}

}

package com.inmaytide.framework.proxy;

/**
 * 代理接口
 * @author inmaytide
 */
public interface Proxy {
	Object doProxy(ProxyChain chain) throws Throwable;
}

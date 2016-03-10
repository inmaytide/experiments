package com.inmaytide.framework.proxy;

public class Application {

	public static void main(String[] args) {
//		IHello hello = new HelloImpl();
//		DynamicProxy proxy = new DynamicProxy(hello);
//		IHello helloProxy = proxy.getProxy();
//		helloProxy.say("Chris");
		
		HelloImpl helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
		helloProxy.say("Jack");
	}

}

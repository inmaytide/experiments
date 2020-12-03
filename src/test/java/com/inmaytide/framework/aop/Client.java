//package com.inmaytide.framework.aop;
//
//public class Client {
//	public static void main(String... args) {
//		//静态代理
//		GreetingProxy proxy = new GreetingProxy(new GreetingImpl());
//		proxy.say("Jack");
//		//JDK动态代理
//		JDKDynamicProxy jdkProxy = new JDKDynamicProxy(new GreetingImpl());
//		Greeting inst = jdkProxy.getProxy();
//		inst.say("Chris");
//		//CGLIB动态代理
//		Greeting inst1 = CGLibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
//		inst1.say("Rose");
//	}
//}

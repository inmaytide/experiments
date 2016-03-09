package com.inmaytide.framework.proxy;

public class HelloProxy implements IHello {
	
	private Hello hello;
	
	public HelloProxy() {
		hello = new Hello();
	}
	
	@Override
	public void say(String name) {
		before();
		hello.say(name);
		after();
	}
	
	public void before() {
		System.out.println("before");
	}
	
	public void after() {
		System.out.println("after");
	}

}

package com.inmaytide.framework.proxy;

public class HelloImpl implements IHello {

	@Override
	public void say(String name) {
		System.out.println("Hello! " + name);
	}

}

package com.inmaytide.framework.proxy;

public class Application {

	public static void main(String[] args) {
		HelloProxy proxy = new HelloProxy();
		proxy.say("John");
	}

}

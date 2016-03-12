package com.inmaytide.framework.aop.spring_aspectj;

import com.inmaytide.framework.aop.springdemo.Apology;

public class ApologyImpl implements Apology {

	@Override
	public void saySorry(String name) {
		System.out.println("Sorry! " + name);
	}

}

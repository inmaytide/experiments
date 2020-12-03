package com.inmaytide.plugin.security;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

public class SecurityPlugin implements ServletContainerInitializer{

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		//初始化参数
		ctx.setInitParameter("shiroConfigLocations", "classpath:security.ini");
		//注册Listener
		ctx.addListener(EnvironmentLoaderListener.class);
		//注册Filter
		FilterRegistration.Dynamic security = ctx.addFilter("SecurityFilter", SecurityFilter.class);
		security.addMappingForUrlPatterns(null, false, "/*");
	}

}

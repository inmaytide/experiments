package com.inmaytide.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmaytide.framework.annotation.Action;
import com.inmaytide.framework.annotation.Controller;
import com.inmaytide.framework.bean.Param;
import com.inmaytide.framework.bean.View;
import com.inmaytide.plugin.security.SecurityHelper;
import com.inmaytide.plugin.security.exception.AuthcException;

/**
 * 处理系统请求
 * @author inmaytide
 */
@Controller
public class SystemController {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	
	@Action("get:/")
	public View index() {
		return new View("index.jsp");
	}
	
	@Action("get:/login")
	public View login() {
		return new View("login.jsp");
	}
	
	@Action("post:/login")
	public View doLogin(Param param) {
		String username = param.getString("username");
		String password = param.getString("password");
		try {
			SecurityHelper.login(username, password);
		} catch (AuthcException e) {
			logger.error("login failure", e);
			return new View("/login");
		}
		return new View("/customer");
	}
	
	@Action("get:/logout")
	public View logout() {
		SecurityHelper.logout();
		return new View("/");
	}
	
}

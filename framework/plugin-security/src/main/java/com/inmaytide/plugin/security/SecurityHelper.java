package com.inmaytide.plugin.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmaytide.plugin.security.exception.AuthcException;

public final class SecurityHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityHelper.class);
	
	public static void login(String username, String password) throws AuthcException {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				logger.error("login failure", e);
				throw new AuthcException(e);
			}
		}
	}
	
	public static void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			currentUser.logout();
		}
	}
}

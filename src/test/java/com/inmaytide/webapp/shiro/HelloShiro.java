/**
 * 
 */
package com.inmaytide.webapp.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author inmaytide
 *
 */
public class HelloShiro {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloShiro.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("shiro", "3224781");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			logger.info("Login failure", e);
			return;
		}
		
		logger.info("Login successed! Hello " + subject.getPrincipal());
	}

}

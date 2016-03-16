package com.inmaytide.framework;

/**
 * 提供相关配置常量
 * @author inmaytide
 * 
 */
public interface ConfigConstant {
	
	String CONFIG_FILE = "framework.properties";
	
	String JDBC_DRIVER = "framework.jdbc.driver";
	String JDBC_URL = "framework.jdbc.url";
	String JDBC_USERNAME = "framework.jdbc.username";
	String JDBC_PASSWORD = "framework.jdbc.password";
	
	String APP_BASE_PACKAGE = "framework.app.base_package";
	String APP_JSP_PATH = "framework.app.jsp_path";
	String APP_ASSET_PATH = "framework.app.asset_path";
	String APP_UPLOAD_LIMIT = "framework.app.upload_limit";
}

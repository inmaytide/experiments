/**
 * 
 */
package com.inmaytide.framework.helper;

import java.util.Properties;

import com.inmaytide.framework.ConfigConstant;
import com.inmaytide.framework.utils.PropsUtil;

/**
 * 
 * 属性文件助手类
 * 
 * @author inmaytide
 *
 */
public class ConfigHelper {
	private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

	public static String getJdbcDriver() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
	}

	public static String getJdbcUrl() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
	}

	public static String getJdbcUsername() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}

	public static String getJdbcPassword() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}

	public static String getAppBasePackage() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
	}

	public static String getAppJspPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
	}

	public static String getAppAssetPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
	}

	public static Integer getAppUploadLimit() {
		return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT, 10);
	}
	
	public static String getPluginSecurityRealms() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.PLUGIN_SECURITY_REALMS);
	}
	
	public static String getPluginSecurityCustomClass() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.PLUGIN_SECURITY_CUSTOM_CLASS);
	}
	
	public static String getString(String key) {
		return PropsUtil.getString(CONFIG_PROPS, key);
	}
	
	public static String getString(String key, String defaultValue) {
		return PropsUtil.getString(CONFIG_PROPS, key, defaultValue);
	}
	
	public static boolean getBoolean(String key) {
		return PropsUtil.getBoolean(CONFIG_PROPS, key);
	}
	
	public static boolean getBoolean(String key, boolean defalutValue) {
		return PropsUtil.getBoolean(CONFIG_PROPS, key, defalutValue);
	}
}

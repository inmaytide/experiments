package com.inmaytide.plugin.security;

import com.inmaytide.framework.helper.ConfigHelper;
import com.inmaytide.framework.utils.ReflectionUtil;

public class SecurityConfig {
	public static String getRealms() {
		return ConfigHelper.getString(SecurityConstant.REALMS);
	}
	
	public static Security getSecurity() {
		String className = ConfigHelper.getString(SecurityConstant.SECURITY);
		return (Security) ReflectionUtil.newInstance(className);
	}
	
	public static String getJdbcAuthcQuery() {
		return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
	}
	
	public static String getJdbcRolesQuery() {
		return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
	}
	
	public static String getJdbcPermissionsQuery() {
		return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
	}
	
	public static boolean isCacheable() {
		return ConfigHelper.getBoolean(SecurityConstant.CACHE, true);
	}
}

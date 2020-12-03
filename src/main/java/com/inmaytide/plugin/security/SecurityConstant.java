package com.inmaytide.plugin.security;

public interface SecurityConstant {
	String REALMS = "plugin.security.realms";
	String REALMS_JDBC = "jdbc";
	String REALMS_CUSTOM = "custom";
	
	String SECURITY = "plugin.security.custom.class";
	
	String JDBC_AUTHC_QUERY = "plugin.security.jdbc.authc_query";
	String JDBC_ROLES_QUERY = "plugin.security.jdbc.roles_query";
	String JDBC_PERMISSIONS_QUERY = "plugin.security.jdbc.permissions_query";
	
	String CACHE = "plugin.security.cache";
}

package com.inmaytide.plugin.security.realm;

import org.apache.shiro.realm.jdbc.JdbcRealm;

import com.inmaytide.framework.helper.DatabaseHelper;
import com.inmaytide.plugin.security.SecurityConfig;
import com.inmaytide.plugin.security.password.Md5CredentialsMatcher;

public class InmaytideJdbcRealm extends JdbcRealm {
	public InmaytideJdbcRealm() {
		super.setDataSource(DatabaseHelper.getDataSource());
		super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
		super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
		super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
		super.setPermissionsLookupEnabled(true);
		super.setCredentialsMatcher(new Md5CredentialsMatcher());
	}
}

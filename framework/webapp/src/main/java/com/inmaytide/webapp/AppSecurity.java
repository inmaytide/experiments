package com.inmaytide.webapp;

import java.util.Set;

import com.inmaytide.framework.helper.DatabaseHelper;
import com.inmaytide.plugin.security.Security;

public class AppSecurity implements Security {

	@Override
	public String getPassword(String username) {
		String sql = "SELECT password FROM user WHERE username = ?";
		return DatabaseHelper.uniqueResult(sql, username).toString();
	}

	@Override
	public Set<String> getRoleNameSet(String username) {
		String sql = "SELECT r.role_name FROM user u, user_role ur, role r WHERE u.id = ur.user_id AND r.id = ur.role_id "
				+ "AND u.username = ?";
		return DatabaseHelper.querySet(String.class, sql, username);
	}

	@Override
	public Set<String> getPermissionNameSet(String roleName) {
		String sql = "SELECT p.permission_name FROM role r, role_permission rp, permission p WHERE r.id = rp.role_id AND "
				+ "p.id = rp.permission_id AND r.role_name = ?";
		return DatabaseHelper.querySet(String.class, sql, roleName);
	}

}

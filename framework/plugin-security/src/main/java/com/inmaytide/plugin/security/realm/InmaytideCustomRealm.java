/**
 * 
 */
package com.inmaytide.plugin.security.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.inmaytide.plugin.security.Security;
import com.inmaytide.plugin.security.SecurityConstant;
import com.inmaytide.plugin.security.password.Md5CredentialsMatcher;

/**
 * @author Administrator
 *
 */
public class InmaytideCustomRealm extends AuthorizingRealm {
	
	private final Security security;
	
	public InmaytideCustomRealm(Security security) {
		this.security = security;
		super.setName(SecurityConstant.REALMS_CUSTOM);
		super.setCredentialsMatcher(new Md5CredentialsMatcher());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("parameter principals is null");
		}
		String username = (String) super.getAvailablePrincipal(principals);
		Set<String> roleNameSet = security.getRoleNameSet(username);
		Set<String> permissionNameSet = new HashSet<String>();
		if (roleNameSet != null && roleNameSet.size() > 0) {
			roleNameSet.forEach(roleName -> {
				Set<String> currentPermissionNameSet = security.getPermissionNameSet(roleName);
				permissionNameSet.addAll(currentPermissionNameSet);
			});
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roleNameSet);
		info.setStringPermissions(permissionNameSet);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (token == null) {
			throw new AuthenticationException("parameter token is null");
		}
		
		String username = ((UsernamePasswordToken) token).getUsername();
		String password = security.getPassword(username);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
		info.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
		info.setCredentials(password);
		return info;
	}


}

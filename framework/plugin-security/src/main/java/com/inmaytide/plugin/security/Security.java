package com.inmaytide.plugin.security;

import java.util.Set;
/**
 * Security 接口
 * <br/>
 * 可在应用实现该接口，或者在framework.properties文件中提供一下基于SQL的配置项:
 * <ul>
 * 	<li>framework.plugin.security.jdbc.authc_query:根据用户名获取密码</li>
 * 	<li>framework.plugin.security.jdbc.roles_query:根据用户名获取角色名集合</li>
 * 	<li>framework.plugin.security.jdbc.permissions_query:根据角色名获取权限名集合</li>
 * </ul>
 * @author inmaytide
 *
 */
public interface Security {
	
	/**
	 * 根据用户名获取密码
	 * @param username
	 * @return
	 */
	String getPassword(String username);
	
	/**
	 * 根据用户名获取角色名集合 
	 * @param username
	 * @return
	 */
	Set<String> getRoleNameSet(String username);
	
	/**
	 * 根据角色名获取权限名集合
	 * @param uesrname
	 * @return
	 */
	Set<String> getPermissionNameSet(String roleName);
}

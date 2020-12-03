/**
 * 
 */
package com.inmaytide.plugin.security.password;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import com.inmaytide.framework.utils.CodecUtil;

/**
 * @author Administrator
 *
 */
public class Md5CredentialsMatcher implements CredentialsMatcher {

	/* (non-Javadoc)
	 * @see org.apache.shiro.authc.credential.CredentialsMatcher#doCredentialsMatch(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo)
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String submitted = String.valueOf(((UsernamePasswordToken) token).getPassword());
		String encrypted = String.valueOf(info.getCredentials());
		return CodecUtil.md5(submitted).equals(encrypted);
	}

}

package com.inmaytide.plugin.security;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import com.inmaytide.framework.utils.StringUtil;
import com.inmaytide.plugin.security.realm.InmaytideCustomRealm;
import com.inmaytide.plugin.security.realm.InmaytideJdbcRealm;

public class SecurityFilter extends ShiroFilter {
	
	@Override
	public void init() throws Exception {
		super.init();
		
		WebSecurityManager webSecurityManager = super.getSecurityManager();
		setRealms(webSecurityManager);
		setCache(webSecurityManager);
	}
	
	private void setRealms(WebSecurityManager webSecurityManager) {
		String securityRealms = SecurityConfig.getRealms();
		if (StringUtil.isNotEmpty(securityRealms)) {
			String[] securityRealmArray = securityRealms.split(",");
			if (securityRealmArray.length > 0) {
				Set<Realm> realms = new LinkedHashSet<Realm>();
				Arrays.stream(securityRealmArray).forEach(securityRealm -> {
					if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)) {
						addJdbcRealm(realms);
					} else if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)) {
						addCustomRealm(realms);
					}
				});
				RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
				realmSecurityManager.setRealms(realms);
			}
		}
		
	}

	private void addCustomRealm(Set<Realm> realms) {
		realms.add(new InmaytideJdbcRealm());
	}

	private void addJdbcRealm(Set<Realm> realms) {
		Security security = SecurityConfig.getSecurity();
		realms.add(new InmaytideCustomRealm(security));
	}
	
	private void setCache(WebSecurityManager webSecurityManager) {
		if (SecurityConfig.isCacheable()) {
			CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager;
			CacheManager cacheManager = new MemoryConstrainedCacheManager();
			cachingSecurityManager.setCacheManager(cacheManager);
		}
	}
	
}

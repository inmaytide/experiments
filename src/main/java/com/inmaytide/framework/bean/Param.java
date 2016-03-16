/**
 * 
 */
package com.inmaytide.framework.bean;

import java.util.Map;

import com.inmaytide.framework.utils.CastUtil;
import com.inmaytide.framework.utils.CollectionUtil;

/**
 * @author inmaytide
 *
 */
public class Param {
	
	private Map<String, Object> paramMap;

	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	
	public long getLong(String name) {
		return CastUtil.castLong(paramMap.get(name));
	}
	
	public int getInt(String name) {
		return CastUtil.castInt(paramMap.get(name));
	}
	
	public String getString(String name) {
		return CastUtil.castString(paramMap.get(name));
	}
	
	public double getDouble(String name) {
		return CastUtil.castDouble(paramMap.get(name));
	}
	
	public boolean getBoolean(String name) {
		return CastUtil.castBoolean(paramMap.get(name));
	}
	
	public Object getObject(String name) {
		return paramMap.get(name);
	}

	public boolean isEmpty() {
		return CollectionUtil.isEmpty(paramMap);
	}
}

/**
 * 
 */
package com.inmaytide.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.inmaytide.framework.utils.ReflectionUtil;

/**
 * Bean 助手类
 * @author Administrator
 */
public final class BeanHelper {
	/**
	 * 定义Bean映射
	 */
	private static final Map<Class<?>, Object> BEAN_MAP;
	
	static {
		Set<Class<?>> beans = ClassHelper.getBeanClassSet();
		BEAN_MAP = new HashMap<Class<?>, Object>(beans.size());
		beans.forEach(cls -> BEAN_MAP.put(cls, ReflectionUtil.newInstance(cls)));
	}

	public static Map<Class<?>, Object> getBeanMap() {
		return BEAN_MAP;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls) {
		if (!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("Can not get bean by class:" + cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
	
	public static void setBean(Class<?> cls, Object bean) {
		BEAN_MAP.put(cls, bean);
	}
}

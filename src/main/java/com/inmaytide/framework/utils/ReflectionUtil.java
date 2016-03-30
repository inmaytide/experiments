package com.inmaytide.framework.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 * 调用无参构造函数创建对象
	 * @param cls
	 * @return
	 */
	public static <T> T newInstance(Class<T> cls) {
		T instance;
		try {
			instance = cls.newInstance();
		} catch (Exception e) {
			logger.error("new instance failure", e);
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	/**
	 * 调用有参构造函数创建对象
	 * @param cls
	 * @param parameterTypes 参数类型数组
	 * @param initargs 初始化参数
	 * @return
	 */
	public static <T> T newInstance(Class<T> cls, Class<?>[] parameterTypes, Object[] initargs) {
		T instance;
		try {
			Constructor<T> constructor = cls.getConstructor(parameterTypes);
			constructor.setAccessible(true);
			instance = constructor.newInstance(initargs);
		} catch (Exception e) {
			logger.error("new instance failure", e);
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	public static Object invokeMethod(Object o, Method method, Object... args) {
		Object result = null;
		try {
			method.setAccessible(true);
			result = method.invoke(o, args);
		} catch (Exception e) {
			logger.error("invoke method failure", e);
			throw new RuntimeException(e);
		} 
		return result;
	}
	
	public static void setField(Object o, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(o, value);
		} catch (Exception e) {
			logger.error("set field failure", e);
			throw new RuntimeException(e);
		}
	}

	public static Object newInstance(String className) {
		Object instance;
		try {
			Class<?> cls = Class.forName(className);
			instance = newInstance(cls);
		} catch (ClassNotFoundException e) {
			logger.error("new instance failure", e);
			throw new RuntimeException(e);
		}
		return instance;
	}
}

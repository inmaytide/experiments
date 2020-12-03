package com.inmaytide.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.inmaytide.framework.annotation.Controller;
import com.inmaytide.framework.annotation.Service;
import com.inmaytide.framework.utils.ClassUtil;

/**
 * 类操作助手类
 * 
 * @author Administrator
 */
public class ClassHelper {

	/*
	 * 定义类的集合（用于存放所加载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;

	static {
		CLASS_SET = ClassUtil.getClassSet(ConfigHelper.getAppBasePackage());
	}

	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}

	public static Set<Class<?>> getServiceClassSet() {
		return filterClassSetByAnnotation(Service.class);
	}

	public static Set<Class<?>> getControllerClassSet() {
		return filterClassSetByAnnotation(Controller.class);
	}
	
	/**
	 * 获取应用包名下带有某注解的类
	 * @param annotation
	 * @return
	 */
	public static Set<Class<?>> filterClassSetByAnnotation(Class<? extends Annotation> annotation) {
		return CLASS_SET.stream().filter(cls -> cls.isAnnotationPresent(annotation)).collect(Collectors.toSet());
	}
	
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		classSet.addAll(getServiceClassSet());
		classSet.addAll(getControllerClassSet());
		return classSet;
	}
	/**
	 * 获取应用包名下某父类（或接口）的所有子类（或实现类）
	 * @param superClass
	 * @return
	 */
	public static Set<Class<?>> filterClassSetBySuper(Class<?> superClass) {
		return CLASS_SET.stream().filter(cls -> superClass.isAssignableFrom(cls) && !superClass.equals(cls)).collect(Collectors.toSet());
	}
}

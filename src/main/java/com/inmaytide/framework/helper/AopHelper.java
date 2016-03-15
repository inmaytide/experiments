package com.inmaytide.framework.helper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmaytide.framework.annotation.Aspect;
import com.inmaytide.framework.proxy.AspectProxy;
import com.inmaytide.framework.proxy.Proxy;
import com.inmaytide.framework.proxy.ProxyManager;
import com.inmaytide.framework.proxy.TransactionProxy;
import com.inmaytide.framework.utils.ReflectionUtil;

public class AopHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(AopHelper.class);
	
	static {
		try {
			Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
			Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
			
			targetMap.forEach((targetClass, proxyList) -> {
				Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				BeanHelper.setBean(targetClass, proxy);
			});
		} catch (RuntimeException e) {
			logger.error("aop failure", e);
		}
	}
	
	public static Set<Class<?>> createTargetClassSet(Aspect aspect) {
		Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
		Class<? extends Annotation> annotation = aspect.value();
		if (annotation != null && !annotation.equals(Aspect.class)) {
			targetClassSet.addAll(ClassHelper.filterClassSetByAnnotation(annotation));
		}
		return targetClassSet;
	}
	
	public static Map<Class<?>, Set<Class<?>>> createProxyMap() {
		Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
		addAspectProxy(proxyMap);
		addTransactionProxy(proxyMap);
		return proxyMap;
	}
	
	private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
		Set<Class<?>> serviceClassSet = ClassHelper.getServiceClassSet();
		proxyMap.put(TransactionProxy.class, serviceClassSet);
	}

	private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
		Set<Class<?>> proxyClassSet = ClassHelper.filterClassSetBySuper(AspectProxy.class);
		proxyClassSet.forEach(proxyClass -> {
			if (proxyClass.isAnnotationPresent(Aspect.class)) {
				Aspect aspect = proxyClass.getAnnotation(Aspect.class);
				Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
				proxyMap.put(proxyClass, targetClassSet);
			}
		});
	}
	
	public static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) {
		Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>(proxyMap.size(), 1);
		proxyMap.forEach((proxyClass, targetClassSet) -> {
			targetClassSet.forEach(targetClass -> {
				Proxy proxy = (Proxy) ReflectionUtil.newInstance(targetClass);
				if (targetMap.containsKey(targetClass)) {
					targetMap.get(targetClass).add(proxy);
				} else {
					List<Proxy> list = new ArrayList<Proxy>(targetClassSet.size());
					list.add(proxy);
					targetMap.put(targetClass, list);
				}
			});
		});
		return targetMap;
	}
	
}

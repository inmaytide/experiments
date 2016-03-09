package com.inmaytide.framework.utils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类操作工具类
 * 
 * @author inmaytide
 *
 */
public class ClassUtil {
	private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

	/**
	 * 获取类加载器
	 * 
	 * @return
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	/**
	 * 加载类
	 * 
	 * @param className
	 * @return
	 */
	public static Class<?> loadClass(String className, boolean isInitialized) {
		Class<?> cls = null;
		try {
			Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			logger.error("load class failure", e);
			throw new RuntimeException(e);
		}
		return cls;
	}

	/**
	 * 获取指定包名下所有类
	 * 
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (null != url) {
					String protocol = url.getProtocol();
					if ("file".equals(protocol)) {
						String packagePath = url.getPath().replaceAll("%20", " ");
						addClass(classSet, packagePath, packageName);
					} else if ("jar".equals(protocol)) {
						JarURLConnection juconn = (JarURLConnection) url.openConnection();
						if (juconn != null) {
							JarFile jarFile = juconn.getJarFile();
							if (jarFile != null) {
								Enumeration<JarEntry> entries = jarFile.entries();
								while (entries.hasMoreElements()) {
									JarEntry entry = entries.nextElement();
									String jarName = entry.getName();
									if (jarName.endsWith(".class")) {
										String className = jarName.substring(0, jarName.lastIndexOf("."))
												.replaceAll("/", ".");
										doAddClass(classSet, className);
									}
								}
							}
						}
					}
				}
			}

		} catch (IOException e) {
			logger.error("Get class set failure", e);
			throw new RuntimeException(e);
		}
		return classSet;
	}

	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		File[] files = new File(packagePath)
				.listFiles((File file) -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());
		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if (StringUtil.isNotEmpty(className)) {
					className = packageName + className;
				}
				doAddClass(classSet, className);
			} else {
				String subPackagePath = fileName;
				if (StringUtil.isNotEmpty(subPackagePath)) {
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if (StringUtil.isNotEmpty(subPackageName)) {
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
	}

	private static void doAddClass(Set<Class<?>> classSet, String className) {
		Class<?> cls = loadClass(className, false);
		classSet.add(cls);
	}
}

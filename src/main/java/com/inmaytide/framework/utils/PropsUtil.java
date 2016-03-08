package com.inmaytide.framework.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Properties资源文件工具类
 * @author Administrator
 *
 */
public class PropsUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);
	
	public static Properties loadProps(String fileName) {
		Properties props = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (is == null) {
				throw new FileNotFoundException(fileName + " file is not found.");
			}
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			logger.error("load properties file failure", e);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("close input stream failure", e);
				}
			}
		}
		return props;
	}

	public static String getString(Properties props, String key) {
		return getString(props, key, "");
	}

	public static String getString(Properties props, String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
	
	public static Integer getInt(Properties props, String key) {
		return getInt(props, key, 0);
	}

	public static Integer getInt(Properties props, String key, Integer defaultValue) {
		Integer value = defaultValue;
		if (props.contains(key)) {
			value = CastUtil.castInt(props.getProperty(key));
		}
		return value;
	}
	
	public static Boolean getBoolean(Properties props, String key, Boolean defaultValue) {
		Boolean value = defaultValue;
		if (props.contains(key)) {
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		return value;
	}
	
	public static Boolean getBoolean(Properties props, String key) {
		return getBoolean(props, key, false);
	}
}

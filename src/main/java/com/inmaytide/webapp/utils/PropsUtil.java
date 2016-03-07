package com.inmaytide.webapp.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}

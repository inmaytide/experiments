/**
 * 
 */
package com.inmaytide.framework.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 *
 */
public class StringUtil {
	
	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String[] splitString(String str, String separatorChars) {
		return StringUtils.split(str, separatorChars);
	}

}

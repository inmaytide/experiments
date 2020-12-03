/**
 * 
 */
package com.inmaytide.framework.utils;

/**
 * @author Administrator
 *
 */
public class CastUtil {
	
	public static String castString(Object value, String defaultValue) {
		return null != value ? String.valueOf(value) : defaultValue;
	}
	
	public static String castString(Object value) {
		return castString(value, "");
	}
	
	public static double castDouble(Object value, double defaultValue) {
		double doubleValue = defaultValue;
		if (value != null) {
			String str = castString(value);
			if (StringUtil.isNotEmpty(str)) {
				try {
					doubleValue = Double.parseDouble(str);
				} catch (NumberFormatException e) {
					doubleValue = defaultValue;
				}
			}
		}
		return doubleValue;
	}
	
	public static double castDouble(Object value) {
		return castDouble(value, 0);
	}
	
	public static long castLong(Object value, long defaultValue) {
		long longValue = defaultValue;
		if (null != value) {
			String str = castString(value);
			if (StringUtil.isNotEmpty(str)) {
				try {
					longValue = Long.parseLong(str);
				} catch (NumberFormatException e) {
					longValue = defaultValue;
				}
			}
		}
		return longValue;
	}
	
	public static long castLong(Object value) {
		return castLong(value, 0);
	}
	
	public static int castInt(Object value, int defaultValue) {
		int intValue = defaultValue;
		if (null != value) {
			String str = castString(value);
			if (StringUtil.isNotEmpty(str)) {
				try {
					intValue = Integer.parseInt(str);
				} catch (NumberFormatException e) {
					intValue = defaultValue;
				}
			}
		}
		return intValue;
	}

	public static int castInt(Object value) {
		return castInt(value, 0);
	}

	public static boolean castBoolean(Object value, boolean defaultValue) {
		boolean boolValue = defaultValue;
		if (value != null) {
			boolValue = Boolean.parseBoolean(castString(value));
		}
		return boolValue;
	}
	
	public static boolean castBoolean(Object value) {
		return castBoolean(value, false);
	}

}

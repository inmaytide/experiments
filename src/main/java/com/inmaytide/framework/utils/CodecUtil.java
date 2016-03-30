/**
 * 
 */
package com.inmaytide.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author inmaytide
 *
 */
public class CodecUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

	public static String encodeURL(String source) {
		String target = null;
		try {
			target = URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("encode url failure", e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	public static String decode(String source) {
		String target = null;
		try {
			target = URLDecoder.decode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("decode url failure", e);
			throw new RuntimeException(e);
		}
		return target;
	}

	public static Object md5(String submitted) {
		return DigestUtils.md5Hex(submitted);
	}
}

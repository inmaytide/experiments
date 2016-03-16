/**
 * 
 */
package com.inmaytide.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author inmaytide
 *
 */
public final class StreamUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);
	
	public static String getString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			logger.error("get string failure", e);
			throw new RuntimeException(e);
		}
		
		return sb.toString();
	}

	public static void copyStream(InputStream is, OutputStream os) {
		try {
			int len;
			byte[] buffer = new byte[4 * 1024];
			while ((len = is.read(buffer, 0, buffer.length)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
		} catch (Exception e) {
			logger.error("copy stream failure", e);
			throw new RuntimeException(e);
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				logger.error("close stream failure", e);
				throw new RuntimeException(e);
			}
		}
	}
}

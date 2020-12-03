package com.inmaytide.framework.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	public static String getRealFileName(String string) {
		return FilenameUtils.getName(string);
	}

	public static File createFile(String filePath) {
		File file;
		try {
			logger.debug("file path " + filePath);
			file = new File(filePath);
			File parentDir = file.getParentFile();
			if (!parentDir.exists()) {
				FileUtils.forceMkdir(parentDir);
			}
		} catch (Exception e) {
			logger.error("create file failure", e);
			throw new RuntimeException(e);
		}
		return file;
	}

}

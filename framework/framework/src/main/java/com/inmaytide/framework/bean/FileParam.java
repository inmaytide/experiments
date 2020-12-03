/**
 * 
 */
package com.inmaytide.framework.bean;

import java.io.InputStream;

/**
 * @author Administrator
 *
 */
public class FileParam {
	private String fieldName;
	private String fileName;
	private long fileSize;
	private String contentType;
	private InputStream is;
	
	public FileParam() {
		
	}
	
	public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream is) {
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.is = is;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}
	
}

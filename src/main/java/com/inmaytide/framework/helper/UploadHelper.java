package com.inmaytide.framework.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmaytide.framework.bean.FileParam;
import com.inmaytide.framework.bean.FormParam;
import com.inmaytide.framework.bean.Param;
import com.inmaytide.framework.utils.CollectionUtil;
import com.inmaytide.framework.utils.FileUtil;
import com.inmaytide.framework.utils.StreamUtil;
import com.inmaytide.framework.utils.StringUtil;

public class UploadHelper {
	private static final Logger logger = LoggerFactory.getLogger(UploadHelper.class);
	
	private static ServletFileUpload servletFileUpload;
	
	public static ServletFileUpload getServletFileUpload() {
		return servletFileUpload;
	}
	
	public static Param createParam(HttpServletRequest request) {
		List<FormParam> formParams = new ArrayList<FormParam>();
		List<FileParam> fileParams = new ArrayList<FileParam>();
		
		try {
			Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
			if (CollectionUtil.isNotEmpty(fileItemListMap)) {
				fileItemListMap.forEach((fieldName, fileItemList) -> {
					if (CollectionUtil.isNotEmpty(fileItemList)) {
						fileItemList.forEach(fi -> {
							if (fi.isFormField()) {
								try {
									String fieldValue = fi.getString("utf-8");
									formParams.add(new FormParam(fieldName, fieldValue));
								} catch (Exception e) {
									logger.error("create param failure", e);
									throw new RuntimeException (e);
								}
							} else {
								try {
									String fileName = FileUtil.getRealFileName(new String(fi.getName().getBytes(), "utf-8"));
									if (StringUtil.isNotEmpty(fileName)) {
										long fileSize = fi.getSize();
										String contentType = fi.getContentType();
										InputStream is = fi.getInputStream();
										fileParams.add(new FileParam(fieldName, fileName, fileSize, contentType, is));
									}
								} catch (Exception e) {
									logger.error("create param failure", e);
									throw new RuntimeException (e);
								}
							}
						});
					}
				});
			}
		} catch (FileUploadException e) {
			logger.error("create param failure", e);
			throw new RuntimeException (e);
		}
		return new Param(formParams, fileParams);
	}
	
	public static void uploadFile(String basePath, FileParam fileParam) {
		if (null != fileParam) {
			try {
				String filePath = basePath + fileParam.getFileName();
				FileUtil.createFile(filePath);
				InputStream is = new BufferedInputStream(fileParam.getIs());
				OutputStream os = new BufferedOutputStream(new FileOutputStream(filePath));
				StreamUtil.copyStream(is, os);
			} catch (Exception e) {
				logger.error("upload file failure", e);
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void uploadFile(String basePath, List<FileParam> fileParams) {
		try {
			if (CollectionUtil.isNotEmpty(fileParams)) {
				fileParams.forEach(fileParam -> {
					uploadFile(basePath, fileParam);
				});
			}
		} catch (Exception e) {
			logger.error("upload file failure", e);
			throw new RuntimeException(e);
		}
	}

	public static void init(ServletContext servletContext) {
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
		int uploadLimit = ConfigHelper.getAppUploadLimit();
		servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
	}
	
	public static boolean isMultipart(HttpServletRequest request) {
		return ServletFileUpload.isMultipartContent(request);
	}
}

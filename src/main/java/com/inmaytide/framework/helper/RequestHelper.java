package com.inmaytide.framework.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.inmaytide.framework.bean.FormParam;
import com.inmaytide.framework.bean.Param;
import com.inmaytide.framework.utils.ArrayUtil;
import com.inmaytide.framework.utils.CodecUtil;
import com.inmaytide.framework.utils.StreamUtil;
import com.inmaytide.framework.utils.StringUtil;

public class RequestHelper {
	
	public static Param createParam(HttpServletRequest request) throws IOException {
		List<FormParam> formParams = new ArrayList<FormParam>();
		formParams.addAll(parseParameterNames(request));
		formParams.addAll(paramInputStream(request));
		return new Param(formParams);
	}

	private static List<FormParam> paramInputStream(HttpServletRequest request) throws IOException {
		List<FormParam> formParams = new ArrayList<FormParam>();
		String body = CodecUtil.encodeURL(StreamUtil.getString(request.getInputStream()));
		if (StringUtil.isNotEmpty(body)) {
			String[] kvs = StringUtil.splitString(body, "&");
			if (ArrayUtil.isNotEmpty(kvs)) {
				Arrays.stream(kvs).forEach(kv -> {
					String[] array = StringUtil.splitString(kv, "=");
					if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
						formParams.add(new FormParam(array[0], array[1]));
					}
				});
			}
		}
		return formParams;
	}

	private static List<FormParam> parseParameterNames(HttpServletRequest request) {
		List<FormParam> formParams = new ArrayList<FormParam>();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String fieldName = paramNames.nextElement();
			String[] fieldValues = request.getParameterValues(fieldName);
			if (ArrayUtil.isNotEmpty(fieldValues)) {
				Object fieldValue;
				if (fieldValues.length == 1) {
					fieldValue = fieldValues[0];
				} else {
					StringBuilder sb = new StringBuilder();
					Arrays.stream(fieldValues).forEach(value -> {
						sb.append(value).append(StringUtil.SEPARATOR);
					});
					fieldValue = sb.deleteCharAt(sb.length() - 1).toString();
				}
				formParams.add(new FormParam(fieldName, fieldValue));
			}
		}
		return formParams;
	}
	
}

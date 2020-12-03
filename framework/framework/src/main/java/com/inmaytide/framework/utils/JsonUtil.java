package com.inmaytide.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public static <T> String toJson(T t) {
		String json = null;
		try {
			json = OBJECT_MAPPER.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			logger.error("convert POJO to JSON failure", e);
			throw new RuntimeException(e);
		}
		return json;
	}
	
	public static <T> T formJson(String json, Class<T> cls) {
		T pojo = null;
		try {
			pojo = OBJECT_MAPPER.readValue(json, cls);
		} catch (Exception e) {
			logger.error("convert JSON to POJO failure", e);
			throw new RuntimeException(e);
		} 
		return pojo;
	}
}

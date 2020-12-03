/**
 * 
 */
package com.inmaytide.framework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inmaytide.framework.utils.CastUtil;
import com.inmaytide.framework.utils.CollectionUtil;
import com.inmaytide.framework.utils.StringUtil;

/**
 * @author inmaytide
 *
 */
public class Param {
	
	private List<FormParam> formParams;
	
	private List<FileParam> fileParams;

	public Param(List<FormParam> formParams) {
		this.formParams = formParams;
	}
	
	public Param(List<FormParam> formParams, List<FileParam> fileParams) {
		this.formParams = formParams;
		this.fileParams = fileParams;
	}

	public Map<String, Object> getFieldMap() {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		if (CollectionUtil.isNotEmpty(formParams)) {
			formParams.forEach(formParam -> {
				String name = formParam.getFieldName();
				Object value = formParam.getFieldValue();
				if (fieldMap.containsKey(name)) {
					value = fieldMap.get(name) + StringUtil.SEPARATOR + value;
				}
				fieldMap.put(name, value);
			});
		}
		return fieldMap;
	}
	
	public Map<String, List<FileParam>> getFileMap() {
		Map<String, List<FileParam>> fileMap = new HashMap<String, List<FileParam>>();
		if (CollectionUtil.isNotEmpty(fileParams)) {
			fileParams.forEach(fileParam -> {
				String name = fileParam.getFieldName();
				List<FileParam> value;
				if (fileMap.containsKey(name)) {
					value = fileMap.get(name);
				} else {
					value = new ArrayList<FileParam>();
				}
				value.add(fileParam);
				fileMap.put(name, value);
			});
		}
		return fileMap;
	}
	
	public List<FileParam> getFileList(String name) {
		return getFileMap().get(name);
	}
	
	public FileParam getFile(String name) {
		List<FileParam> fileParams = getFileMap().get(name);
		if (CollectionUtil.isNotEmpty(fileParams) && fileParams.size() == 1) {
			return fileParams.get(0);
		}
		return null;
	}
	
	public long getLong(String name) {
		return CastUtil.castLong(getFieldMap().get(name));
	}
	
	public int getInt(String name) {
		return CastUtil.castInt(getFieldMap().get(name));
	}
	
	public String getString(String name) {
		return CastUtil.castString(getFieldMap().get(name));
	}
	
	public double getDouble(String name) {
		return CastUtil.castDouble(getFieldMap().get(name));
	}
	
	public boolean getBoolean(String name) {
		return CastUtil.castBoolean(getFieldMap().get(name));
	}
	
	public Object getObject(String name) {
		return getFieldMap().get(name);
	}

	public boolean isEmpty() {
		return CollectionUtil.isEmpty(formParams) && CollectionUtil.isEmpty(fileParams);
	}
}

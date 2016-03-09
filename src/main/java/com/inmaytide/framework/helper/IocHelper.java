/**
 * 
 */
package com.inmaytide.framework.helper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import com.inmaytide.framework.annotation.Inject;
import com.inmaytide.framework.utils.ReflectionUtil;

/**
 * 依赖注入帮助类
 * @author Administrator
 */
public class IocHelper {
	static {
		Map<Class<?>, Object> beans = BeanHelper.getBeanMap();
		beans.forEach((beanClass, beanInstance) -> {
			Field[] beanFields = beanClass.getDeclaredFields();
			Arrays.stream(beanFields).filter(field -> field.isAnnotationPresent(Inject.class)).forEach(field -> {
				Class<?> fieldClass = field.getType();
				Object bean = beans.get(fieldClass);
				if (null != bean) {
					ReflectionUtil.setField(beanInstance, field, bean);
				}
			});
		});
	}
}

package com.inmaytide.framework;

import java.util.Arrays;

import com.inmaytide.framework.helper.BeanHelper;
import com.inmaytide.framework.helper.ClassHelper;
import com.inmaytide.framework.helper.ControllerHelper;
import com.inmaytide.framework.helper.IocHelper;
import com.inmaytide.framework.utils.ClassUtil;

public class HelperLoader {
	public static void init() {
		Class<?>[] classList = {
			ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class	
		};
		Arrays.stream(classList).forEach(cls -> ClassUtil.loadClass(cls.getName(), true));
	}
}

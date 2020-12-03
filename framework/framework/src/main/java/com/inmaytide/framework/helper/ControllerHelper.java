package com.inmaytide.framework.helper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.inmaytide.framework.annotation.Action;
import com.inmaytide.framework.bean.Handler;
import com.inmaytide.framework.bean.Request;
import com.inmaytide.framework.utils.ArrayUtil;

/**
 * 
 * @author Administrator
 *
 */
public class ControllerHelper {
	private static final Map<Request, Handler> ACTION_MAP;
	
	static {
		ACTION_MAP = new HashMap<Request, Handler>();
		Set<Class<?>> classSet = ClassHelper.getControllerClassSet();
		classSet.forEach(cls -> {
			Arrays.stream(cls.getDeclaredMethods()).filter(mod -> mod.isAnnotationPresent(Action.class)).forEach(mod -> {
				Action action = mod.getAnnotation(Action.class);
				String mapping = action.value();
				if (mapping.matches("\\w+:/\\w*")) {
					String[] array = mapping.split(":");
					if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
						Request request = new Request(array[0], array[1]);
						Handler handler = new Handler(cls, mod);
						ACTION_MAP.put(request, handler);
					}
				}
			});
		});
	}
	
	public static Handler getHandler(String method, String path) {
		Request request = new Request(method, path);
		return ACTION_MAP.get(request);
	}
}

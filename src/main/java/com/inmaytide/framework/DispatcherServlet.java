/**
 * 
 */
package com.inmaytide.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inmaytide.framework.bean.Data;
import com.inmaytide.framework.bean.Handler;
import com.inmaytide.framework.bean.Param;
import com.inmaytide.framework.bean.View;
import com.inmaytide.framework.helper.BeanHelper;
import com.inmaytide.framework.helper.ConfigHelper;
import com.inmaytide.framework.helper.ControllerHelper;
import com.inmaytide.framework.utils.ArrayUtil;
import com.inmaytide.framework.utils.CodecUtil;
import com.inmaytide.framework.utils.JsonUtil;
import com.inmaytide.framework.utils.ReflectionUtil;
import com.inmaytide.framework.utils.StreamUtil;
import com.inmaytide.framework.utils.StringUtil;

/**
 * 
 * 请求转发器
 * 
 * @author inmaytide
 *
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求方法与请求路径
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		// 获取Action处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if (null != handler) {
			// 获取Controller类及其Bean实力
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			// 创建请求参数对象
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodecUtil.decode(StreamUtil.getString(request.getInputStream()));
			if (StringUtil.isNotEmpty(body)) {
				String[] params = StringUtil.splitString(body, "&");
				Arrays.stream(params).forEach(param -> {
					String[] array = StringUtil.splitString(param, "=");
					if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
						String paramName = array[0];
						String paramValue = array[1];
						paramMap.put(paramName, paramValue);
					}
				});
			}
			Param param = new Param(paramMap);
			Method actionMethod = handler.getActionMethod();
			Object result;
			//调用Action方法
			if (param.isEmpty()) {
				result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
			} else {
				result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			}
			
			if (result instanceof View) {
				//返回JSP页面
				View view = (View) result;
				String path = view.getPath();
				if (StringUtil.isNotEmpty(path)) {
					if (path.startsWith("/")) {
						response.sendRedirect(request.getContextPath() + path);
					} else {
						Map<String, Object> model = view.getModel();
						model.forEach((k, v) -> request.setAttribute(k, v));
						request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
					}
				}
			} else if (result instanceof Data) {
				//返回JSON对象
				Data data = (Data) result;
				Object model = data.getModel();
				if (null != model) {
					response.setContentType("application/json");
					response.setCharacterEncoding("utf-8");
					try (PrintWriter writer = response.getWriter()) {
						String json = JsonUtil.toJson(model);
						writer.write(json);
						writer.flush();
					}
				}
			}
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 初始化相关Helper类
		HelperLoader.init();
		// 获取ServletContext对象（用于注册Servlet）
		ServletContext servletContext = config.getServletContext();
		// 注册处理JSP的Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		// 出册处理静态资源的默认Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
	}

}

package com.inmaytide.webapp.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inmaytide.webapp.service.CustomerService;

/**
 * Servlet implementation class CustomerCreateServlet
 */
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> enumeration = request.getParameterNames();
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();
			fieldMap.put(name, request.getParameter(name));
		}
		customerService.createCustomer(fieldMap);
		request.getRequestDispatcher("/WEB-INF/view/list_customer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

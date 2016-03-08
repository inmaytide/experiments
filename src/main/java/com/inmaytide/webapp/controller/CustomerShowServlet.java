package com.inmaytide.webapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inmaytide.webapp.model.Customer;
import com.inmaytide.webapp.service.CustomerService;
import com.inmaytide.webapp.utils.CastUtil;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/customer_show")
public class CustomerShowServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private CustomerService customerService;

	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = CastUtil.castInt(request.getParameter("id"));
		Customer inst = customerService.getCustomer(id);
		request.setAttribute("inst", inst);
		request.getRequestDispatcher("/WEB-INF/view/show_customer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

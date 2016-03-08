package com.inmaytide.webapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inmaytide.framework.utils.CastUtil;
import com.inmaytide.webapp.service.CustomerService;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/customer_delete")
public class CustomerDeleteServlet extends HttpServlet {
	
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
		customerService.deleteCustomer(id);
		request.getRequestDispatcher("/WEB-INF/view/list_customer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

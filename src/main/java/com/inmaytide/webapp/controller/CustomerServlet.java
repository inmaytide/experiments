package com.inmaytide.webapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inmaytide.webapp.model.Customer;
import com.inmaytide.webapp.service.CustomerService;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	
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
		List<Customer> list = customerService.getCustomerList();
		request.setAttribute("list", list);
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

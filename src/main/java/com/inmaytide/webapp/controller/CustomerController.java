package com.inmaytide.webapp.controller;

import com.inmaytide.framework.annotation.Action;
import com.inmaytide.framework.annotation.Controller;
import com.inmaytide.framework.annotation.Inject;
import com.inmaytide.framework.bean.Data;
import com.inmaytide.framework.bean.Param;
import com.inmaytide.framework.bean.View;
import com.inmaytide.framework.helper.UploadHelper;
import com.inmaytide.webapp.service.CustomerService;

@Controller
public class CustomerController {
	
	@Inject
	private CustomerService service;
	
	@Action("get:/customer")
	public View index() {
		return new View("list_customer.jsp").addModel("list", service.getCustomerList());
	}
	
	@Action("get:/customer_create")
	public View create() {
		return new View("create_customer.jsp");
	}
	
	@Action("post:/customer_create")
	public Data createSubmit(Param param) {
		boolean result = service.createCustomer(param.getFieldMap());
		UploadHelper.uploadFile("c:/", param.getFile("photo"));
		return new Data(result);
	}
	
}

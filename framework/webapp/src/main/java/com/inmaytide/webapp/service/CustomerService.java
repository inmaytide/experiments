package com.inmaytide.webapp.service;

import java.util.List;
import java.util.Map;

import com.inmaytide.framework.annotation.Service;
import com.inmaytide.framework.annotation.Transaction;
import com.inmaytide.framework.helper.DatabaseHelper;
import com.inmaytide.webapp.model.Customer;

@Service
public class CustomerService {
	
	public List<Customer> getCustomerList() {
		String sql = "select * from customer";
		return DatabaseHelper.queryEntityList(Customer.class, sql);
	}
	
	public Customer getCustomer(Integer id) {
		String sql = "select * from customer where id = ?";
		return DatabaseHelper.queryEntity(Customer.class, sql, id);
	}
	
	@Transaction
	public boolean createCustomer(Map<String, Object> fieldMap) {
		return DatabaseHelper.insertEntity(fieldMap, Customer.class);
	}
	
	@Transaction
	public boolean updateCustomer(Integer id, Map<String, Object> fieldMap) {
		return DatabaseHelper.updateEntity(fieldMap, Customer.class, id);
	}
	
	@Transaction
	public boolean deleteCustomer(Integer id) {
		return DatabaseHelper.deleteEntity(id, Customer.class);
	}
	
}

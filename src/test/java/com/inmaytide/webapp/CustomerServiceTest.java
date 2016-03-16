/**
 * 
 */
package com.inmaytide.webapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.inmaytide.framework.HelperLoader;
import com.inmaytide.framework.helper.BeanHelper;
import com.inmaytide.framework.helper.DatabaseHelper;
import com.inmaytide.webapp.model.Customer;
import com.inmaytide.webapp.service.CustomerService;

/**
 * @author inmaytide
 *
 */
public class CustomerServiceTest {
	
	private final CustomerService customerService;
	
	static {
		HelperLoader.init();
	}
	
	public CustomerServiceTest() {
		customerService = BeanHelper.getBean(CustomerService.class);
	}

	@Before
	public void init() {
		DatabaseHelper.executeSqlFile("sql/customer_init.sql");
	}
	
	@Test
	public void getCustomerListTest() {
		List<Customer> list = customerService.getCustomerList();
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void getCustomerTest() {
		Integer id = 1;
		Customer inst = customerService.getCustomer(id);
		Assert.assertNotNull(inst);
	}
	
	@Test
	public void createCustomerTest() {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("name", "customer001");
		fieldMap.put("contact", "John");
		fieldMap.put("telephone", "13972053481");
		boolean bool = customerService.createCustomer(fieldMap);
		Assert.assertTrue(bool);
	}
	
	@Test
	public void updateCustomerTest() {
		Integer id = 1;
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("contact", "Eric");
		boolean bool = customerService.updateCustomer(id, fieldMap);
		Assert.assertTrue(bool);
	}
	
	@Test
	public void deleteCustomerTest() {
		Integer id = 1;
		boolean bool = customerService.deleteCustomer(id);
		Assert.assertTrue(bool);
	}
}

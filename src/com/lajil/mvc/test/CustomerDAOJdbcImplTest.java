package com.lajil.mvc.test;

import java.util.List;

import org.junit.Test;

import com.lajil.mvc.dao.CriteriaCustomer;
import com.lajil.mvc.dao.CustomerDAO;
import com.lajil.mvc.domain.Customer;
import com.lajil.mvc.impl.CustomerDAOJdbcImpl;

public class CustomerDAOJdbcImplTest {
	private CustomerDAO customerDAO =
			new CustomerDAOJdbcImpl();
	@Test
	public void testGetForListCriteriaCustomer(){
		CriteriaCustomer cc = new CriteriaCustomer("jerry", null, null);
		List<Customer> customers = customerDAO.getForListCriteriaCustomer(cc);
		System.out.println(customers);
	}
	@Test
	public void testGetAll() {
		List<Customer> customers = customerDAO.getAll();
		System.out.println(customers);
	}

	@Test
	public void testSave() {
		Customer customer = new Customer();
		customer.setName("jerry");
		customer.setAddress("shanghai");
		customer.setPhone("43534632");
		
		customerDAO.save(customer);
	}

	@Test
	public void testGetInteger() {
		Customer customer = customerDAO.get(1);
		System.out.println(customer);
	}

	@Test
	public void testDelete() {
		customerDAO.delete(1);
	}

	@Test
	public void testGetCountWithName() {
		Long count = customerDAO.getCountWithName("jerry");
		System.out.println(count);
	}

}

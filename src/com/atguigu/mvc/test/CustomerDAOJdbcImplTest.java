package com.atguigu.mvc.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.mvc.dao.CustomerDAO;
import com.atguigu.mvc.domain.Customer;
import com.atguigu.mvc.impl.CustomerDAOJdbcImpl;

public class CustomerDAOJdbcImplTest {
	private CustomerDAO customerDAO =
			new CustomerDAOJdbcImpl();
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

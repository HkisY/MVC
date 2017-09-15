package com.atguigu.mvc.dao;

import java.util.List;

import com.atguigu.mvc.domain.Customer;

public interface CustomerDAO {
	public List<Customer> getAll();
	public void save(Customer customer);
	public Customer get(Integer id);
	public void delete(Integer id);
	
	/**
	 * return the number of the same names;
	 * @param name
	 * @return
	 */
	public long getCountWithName(String name);
	
	
}

package com.lajil.mvc.dao;

import java.util.List;

import com.lajil.mvc.domain.Customer;

public interface CustomerDAO {
	public List<Customer> getForListCriteriaCustomer(CriteriaCustomer cc);
	public List<Customer> getAll();
	public void save(Customer customer);
	public void update(Customer customer);
	public Customer get(Integer id);
	public void delete(Integer id);
	
	/**
	 * return the number of the same names;
	 * @param name
	 * @return
	 */
	public long getCountWithName(String name);
	
	
}

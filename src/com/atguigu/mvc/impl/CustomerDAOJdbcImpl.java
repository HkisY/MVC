package com.atguigu.mvc.impl;

import java.util.List;

import com.atguigu.mvc.dao.CustomerDAO;
import com.atguigu.mvc.dao.DAO;
import com.atguigu.mvc.domain.Customer;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO{

	@Override
	public List<Customer> getAll() {
		String sql = "SELECT id,name,address,phone FROM customers";
		return getForList(sql);
	}

	@Override
	public void save(Customer customer) {
		String sql = "INSERT INTO customers(name,address,phone)VALUE(?,?,?)";
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone());
	}

	@Override
	public Customer get(Integer id) {
		String sql = "select id, name, address, phone FROM customers WHERE id = ?";
		return get(sql,id);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from customers where id = ?";
		update(sql, id);
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "select count(id) from customers where name =?";
		return getForValue(sql, name);
	}

}

package com.lajil.mvc.impl;

import java.util.List;

import com.lajil.mvc.dao.CriteriaCustomer;
import com.lajil.mvc.dao.CustomerDAO;
import com.lajil.mvc.dao.DAO;
import com.lajil.mvc.domain.Customer;

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

	
	public List<Customer> getForListCriteriaCustomer(CriteriaCustomer cc) {
		String sql = "SELECT id,name,address,phone FROM customers WHERE "+
					"name LIKE ? AND address LIKE ? AND phone LIKE ?";
		return getForList(sql, cc.getName(), cc.getAddress(), cc.getPhone());
	}

	@Override
	public void update(Customer customer) {
		String sql = "UPDATE customers SET name = ?,address = ?,phone = ? " +
				"WHERE id = ?";
		update(sql,customer.getName(),customer.getAddress(),customer.getPhone(),customer.getId());
	}

}

package com.atguigu.mvc.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.atguigu.mvc.utils.JdbcUtils;

public class JdbcUtilsTest {

	@Test
	public void testGetConnection() throws SQLException {
		Connection connection =JdbcUtils.getConnection();
		System.out.println(connection);
	}

}

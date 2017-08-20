package com.atguigu.bookstore.test;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.bookstore.utils.JDBCUtils;

public class JDBCTest {

	@Test
	public void test() {
		Connection connection = JDBCUtils.getConnection();
		System.out.println(connection);
	}

}

package com.atguigu.bookstore.test;

import org.junit.Test;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class UserDaoImplTest {

	UserDao userDao = new UserDaoImpl();
	@Test
	public void testGetUser() {
		User user = new User(null, "admin", "123456", null);
		User user2 = userDao.getUser(user);
		System.out.println(user2);
	}

	@Test
	public void testCheckUserName() {
		User user = new User(null, "admin2", null, null);
		boolean flag = userDao.checkUserName(user);
		System.out.println(flag);
	}

	@Test
	public void testSaveUser() {
		User user = new User(null, "admin", "123456", "bbb@bbb.com");
		userDao.saveUser(user);
	}

}

package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImp implements UserService {

	UserDao userDao = new UserDaoImpl();
	@Override
	public User login(User user) {
		return userDao.getUser(user);
	}

	@Override
	public boolean regist(User user) {
		return userDao.checkUserName(user);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

}

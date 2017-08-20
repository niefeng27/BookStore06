package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUser(User user) {
		// 写sql
		String sql = "select id , username , password , email from users where username = ? and password = ?";
		// 调用BaseDao中的getBean方法
		User bean = getBean(sql, user.getUsername(), user.getPassword());
		return bean;
	}

	@Override
	public boolean checkUserName(User user) {
		// 写sql
		String sql = "select id , username , password , email from users where username = ?";
		// 调用BaseDao中的getBean方法
		User bean = getBean(sql, user.getUsername());
		return bean!=null;
	}

	@Override
	public void saveUser(User user) {
		//写sql语句
		String sql = "insert into users(username,password,email) values(?,?,?)";
		//调用BaseDao中通用的增删改的方法
		update(sql, user.getUsername(),user.getPassword(),user.getEmail());
	}

}

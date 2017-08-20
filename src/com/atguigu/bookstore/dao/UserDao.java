package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.User;

public interface UserDao {

	/**
	 * 根据用户名和密码获取数据库中的记录
	 * 
	 * @param user
	 * @return User：用户名和密码正确 null：用户名或密码不正确
	 */
	public User getUser(User user);

	/**
	 * 根据用户名获取数据库中的记录
	 * 
	 * @param user
	 * @return true：用户名已存在 false：用户名可用
	 */
	public boolean checkUserName(User user);

	/**
	 * 将用户保存到数据库
	 * 
	 * @param user
	 */
	public void saveUser(User user);
}

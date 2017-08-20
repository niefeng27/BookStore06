package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.User;

public interface UserService {

	/**
	 * 用户登录的方法
	 * 
	 * @param user
	 * @return User：登录成功 null：登录失败
	 */
	public User login(User user);

	/**
	 * 用户注册的方法
	 * 
	 * @param user
	 * @return true：注册失败 false：注册成功
	 */
	public boolean regist(User user);

	/**
	 * 保存用户的方法
	 * 
	 * @param user
	 */
	public void saveUser(User user);
}

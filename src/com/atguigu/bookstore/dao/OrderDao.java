package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.beans.Order;

public interface OrderDao {

	/**
	 * 保存订单的方法
	 */
	public void saveOrder(Order order);
	
	/**
	 * 获取所有订单的方法
	 * @return
	 */
	public List<Order> getOrders();
	
	/**
	 *  获取我的订单的方法
	 */
	public List<Order> getMyOrders(int userId);
	
	/**
	 * 更新订单的状态的方法
	 */
	public void updateOrderState(String orderId, int state);
}

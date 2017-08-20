package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.beans.User;

public interface OrderService {
	
	/**
	 * 去结账生成订单的方法
	 * @param user
	 * @param cart
	 * @return
	 */
	public String createOder(User user, Cart cart);
	
	/**
	 *  获取所有订单的方法
	 */
	public List<Order> getOrders();
	
	/**
	 *  根据订单号获取对应的订单项
	 */
	public List<OrderItem> getOrderItemsByderId(String orderId);
	
	/**
	 *  根据客户的id获取其订单
	 * @param id
	 * @return
	 */
	public List<Order> getMyOrders(int userId);
	
	/**
	 *  发货和确认收货的方法
	 */
	public void updateOrderState(String orderId, int state);
}

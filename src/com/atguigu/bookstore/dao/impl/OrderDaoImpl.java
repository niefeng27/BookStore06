package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	@Override
	public void saveOrder(Order order) {
		// 写sql语句
		String sql = "insert into orders(id, order_time, total_count, total_amount, state, user_id) values(?,?,?,?,?,?)";
		update(sql, order.getId(), order.getOrderTime(), order.getTotalCount(), order.getTotalAmount(),order.getState(), order.getUserId());
	}

	@Override
	public List<Order> getOrders() {
		// 写sql语句
		String sql = "select id, order_time orderTime, total_count totalCount, total_amount totalAmount, state, user_id userId from orders";
		List<Order> beanList = getBeanList(sql);
		return beanList;
	}

	@Override
	public List<Order> getMyOrders(int userId) {
		// 写sql语句
		String sql = "select id, order_time orderTime, total_count totalCount, total_amount totalAmount, state, user_id userId from orders where user_id = ?";
		List<Order> beanList = getBeanList(sql, userId);
		return beanList;
	}

	@Override
	public void updateOrderState(String orderId, int state) {
		//  写sql语句
		String sql = "update orders set state = ? where id = ?";
		update(sql, state, orderId);
		
	}
	
	

}

package com.atguigu.bookstore.test;

import java.util.Date;

import org.junit.Test;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;

public class OrderTest {
	
	OrderDao orderDao = new OrderDaoImpl();
	OrderItemDao orderItemDao = new OrderItemDaoImpl();
	
	@Test
	public void test() {
		Order order = new Order("18458966789259", new Date(), 7, 630, 0, 1);
		//保存订单
		orderDao.saveOrder(order);
		OrderItem orderItem = new OrderItem(null, 3, 270, "打死你个龟孙", "韩总", 90, "static/img/default.jpg", "18458966789259");
		OrderItem orderItem2 = new OrderItem(null, 4, 360, "我请客", "韩总", 90, "static/img/default.jpg", "18458966789259");
		//保存订单项
		orderItemDao.saveOrderItem(orderItem);
		orderItemDao.saveOrderItem(orderItem2);
	}


}

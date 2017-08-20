package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.beans.OrderItem;

public interface OrderItemDao {
	
	/**
	 * 保存订单项
	 */
	public void saveOrderItem(OrderItem orderItem);
	
	/**
	 *  根据订单号获取对应的订单项
	 * @param orderId
	 * @return
	 */

	public List<OrderItem> getOrderItemsByOrderId(String orderId);
	
	/**
	 *  批量插入订单项的方法
	 */
	public void batchInsertOrderItems(Object[][] params);
}

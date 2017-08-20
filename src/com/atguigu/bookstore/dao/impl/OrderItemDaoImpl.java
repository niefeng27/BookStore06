package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements
		OrderItemDao {

	@Override
	public void saveOrderItem(OrderItem orderItem) {
		// 写sql语句
		String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
		update(sql, orderItem.getCount(), orderItem.getAmount(), orderItem.getTitle(), orderItem.getAuthor(),
				orderItem.getPrice(), orderItem.getImgPath(), orderItem.getOrderId());
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderId) {
		// 写sql语句
		String sql = "select id, count, amount, title, author, price, img_path imgPath, order_id orderId from order_items where order_id = ?";
		List<OrderItem> beanList = getBeanList(sql, orderId);
		return beanList;
	}

	@Override
	public void batchInsertOrderItems(Object[][] params) {
		// 写sql语句
		String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
		// 调用BaseDao中的更改方法
		batchUpdate(sql, params);
	}

}

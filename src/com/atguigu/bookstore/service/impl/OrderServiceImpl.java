package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.CartItem;
import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	OrderDao orderDao = new OrderDaoImpl();
	OrderItemDao orderItemDao = new OrderItemDaoImpl();
	BookDao bookDao = new BookDaoImpl();
	
	// 此方法是编写订单号，并将购物车中所有的订单信息全部转化到具体项中交换给数据库
	@Override
	public String createOder(User user, Cart cart) {
		// 生成订单号
		String orderId = System.currentTimeMillis() + "" + user.getId();
		// 获取购物车中的图书的总数量
		int totalCount = cart.getTotalCount();
		// 获取购物车中图书的总金额
		double totalAmount = cart.getTotalAmount();
		// 创建Order对象
		Order order = new Order(orderId, new Date(), totalCount, totalAmount, 0, user.getId());
		// 将订单保存到数据库中
		orderDao.saveOrder(order);
		// 获取购物车中所有的购物项
		List<CartItem> cartItems = cart.getCartItems();
		
		// 新设两个二维数组以备之后一次批量处理
		Object[][] itemsParams = new Object[cartItems.size()][];
		Object[][] booksParams = new Object[cartItems.size()][];
		
		// 遍历得到每一个购物项
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
		// 获取购物项中的图书的数量
			int count = cartItem.getCount();
		// 获取购物项中的金额小计
			double amount = cartItem.getAmount();
		// 获取购物项中的图书
			Book book = cartItem.getBook();
		// 获取书名
			String title = book.getTitle();
		// 获取作者
			String author = book.getAuthor();
		// 获取图书单价
			double price = book.getPrice();
		// 获取图书封面
			String imgPath = book.getImgPath();
		// 封装OrderItem对象
			// OrderItem orderItem = new OrderItem(null, count, amount, title, author, price, imgPath, orderId);
		// 将订单保存到数据库中
			// orderItemDao.saveOrderItem(orderItem);
		// 将属性添加到二维数组中
			itemsParams[i] = new Object[]{count, amount, title, author, price, imgPath, orderId};
		
			// 获取图书的库存和销量
			Integer sales = book.getSales();
			Integer stock = book.getStock();
		// 设置该图书新的库存和销量
			// book.setSales(sales + count);
			// book.setStock(stock - count);
		// 更新图书的库存和销量
			// bookDao.updateBook(book);
			booksParams[i] = new Object[]{sales + count, stock - count, book.getId()};
		}
		// 遍历结束后，一次性将二维数组写入数据库
		orderItemDao.batchInsertOrderItems(itemsParams);
		bookDao.batchUpdateSalesAndStock(booksParams);
		// 结账之后需要清空购物车
		cart.clearCart();
		// 返回订单号
		return orderId;
	}

	@Override
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}

	@Override
	public List<OrderItem> getOrderItemsByderId(String orderId) {
		return orderItemDao.getOrderItemsByOrderId(orderId);
	}

	@Override
	public List<Order> getMyOrders(int userId) {
		return orderDao.getMyOrders(userId);
	}

	@Override
	public void updateOrderState(String orderId, int state) {
		orderDao.updateOrderState(orderId, state);
	}
}

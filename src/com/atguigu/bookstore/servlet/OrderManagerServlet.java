package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 后台管理订单的Servlet
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderServiceImpl();
	
	
	// 发货的方法
	protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取订单号
		String orderId = request.getParameter("orderId");
		// 调用orderService的方法更新订单状态
		orderService.updateOrderState(orderId, 1);
		// 调用getOrders方法重新查询所有订单
		getOrders(request, response);
		
	}
	
	// 获取所有订单的方法
	protected void getOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 调用orderService的方法获取所有的订单
		List<Order> orders = orderService.getOrders();
		// 将orders放到request域中
		request.setAttribute("orders", orders);
		// 转发到显示所有订单的页面
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}
	
	// 获取订单详情
	protected void getOrderItemsById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取订单号
		String orderId = request.getParameter("orderId");
		// 调用orderService的方法获取订单中的订单项
		List<OrderItem> orderItems = orderService.getOrderItemsByderId(orderId);
		// 放到request域中
		request.setAttribute("orderItems", orderItems);
		// 转发到显示订单详情的页面
		request.getRequestDispatcher("/pages/manager/order_info.jsp").forward(request, response);
	}
}

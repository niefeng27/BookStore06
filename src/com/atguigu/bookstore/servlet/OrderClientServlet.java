package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 前台管理订单的Servlet
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderServiceImpl();
	
	// 确认收货的方法
	protected void takeOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取订单号
		String orderId = request.getParameter("orderId");
		// 调用orderService的方法更新订单状态
		orderService.updateOrderState(orderId, 2);
		// 调用getMyOrders方法重新查询我所有的订单
		getMyOrders(request, response);
	}
	
	// 去结账的方法
	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取Session对象
		HttpSession session = request.getSession();
		// 获取Session域中的用户
		User user = (User) session.getAttribute("user");
		// 获取Session域中的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		// 调用orderService的方法生成订单号
		String orderId = orderService.createOder(user, cart);
		// 将订单号放到session域中
		session.setAttribute("orderId", orderId);
		// 重定向到显示订单好的页面，注意若是转发则可能在刷新页面时造成重复提交而形成多个订单号
		response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
	}
	
	// 获取我的订单的方法
	protected void getMyOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取Session对象
		HttpSession session = request.getSession();
		// 获取用户
		User user  = (User) session.getAttribute("user");
		// 获取用户的id
		Integer id = user.getId();
		// 调用orderService的方法获取我的订单
		List<Order> orders = orderService.getMyOrders(id);
		// 将order放到request域中
		request.setAttribute("orders", orders);
		// 转发到显示所有订单的页面
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
		
	}
		
}

package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

/**
 * 操作购物车的Servlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();
	
	// 更新购物项中图书的数量
	protected void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 依据获取的图书Id获取图书的对象
		String bookId = request.getParameter("bookId");
		Book book = bookService.getBookById(bookId);
		// 获取图书的数量
		String bookCount = request.getParameter("bookCount");
		// 获取Session对象
		HttpSession session = request.getSession();
		// 获取session域中的购物车
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart != null) {
			// 更新购物项
			cart.updateCartItem(book, bookCount);
		}
		// 获取购物项中图书的金额小计
		CartItem cartItem = cart.getMap().get(bookId);
		double amount = cartItem.getAmount();
		// 获取购物车中图书的数量
		int totalCount = cart.getTotalCount();
		//获取购物车中图书的总金额
		double totalAmount = cart.getTotalAmount();
		// 创建一个Map对象
		Map<String, Object> map = new HashMap<>();
		map.put("amount", amount+"");
		map.put("totalCount", totalCount+"");
		map.put("totalAmount", totalAmount+"");
		// 创建Gson对象
		Gson gson = new Gson();
		// 将map转换位json字符串
		String json = gson.toJson(map);
		// 将json字符串响应到前端页面
		response.getWriter().write(json);
		
		// 重定向到购物车页面，此方法被上面的json所代替
		//response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
	}
	
	// 删除购物项的方法
	protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 获取session对象
		HttpSession session = request.getSession();
		// 获取session域中的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart != null) {
			//删除购物项
			cart.deleteCartItem(bookId);
		}
		// 重定向到购物车页面
		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
	}
	
	// 清空购物车的方法
	protected void emptyCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取Session对象
		HttpSession session = request.getSession();
		// 获取Session域中的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart != null) {
			// 清空购物车
			cart.clearCart();
		}
		// 重定向到购物车页面
		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
	}
	
	// 将图书添加到购物车的方法
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 获取bookService的方法获取图书的信息
		Book bookById = bookService.getBookById(bookId);
		// 获取session对象
		HttpSession session = request.getSession();
		// 获取session域中的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			// 创建一个购物车并把它放到session域中
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		// 将图书添加到购物车中
		cart.addBook2Cart(bookById);
		// 获取该图书在购物车中的数量
		Map<String, CartItem> map = cart.getMap();
		CartItem cartItem = map.get(bookId);
		int count = cartItem.getCount();
		// 获取该图书的库存量
		Integer stock = bookById.getStock();
		// 判断现在购物项中的数量是否已经超过库存
		if(count > stock) {
			// 设置一个提示消息并放到session域中
			session.setAttribute("msg", "该图书的库存现有"+stock+"本！");
			// 将购物项中图书的数量设置为最大库存
			cartItem.setCount(stock);
		}else {
			// 将书名放到session域中
			session.setAttribute("bookTitle", bookById.getTitle());
		}
		// 获取Referer属性值
		String header = request.getHeader("Referer");
		// 重定向到header
		response.sendRedirect(header);
	}

}

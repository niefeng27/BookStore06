package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 前台管理图书的Servlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookServiceImpl();
	
	// 获取带价格和分页的图书信息
	protected void getPageBooksByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取页码
		String pageNo = request.getParameter("pageNo");
		// 获取请求地址
		String path = WebUtils.getPath(request);
		// 获取客户输入的价格范围
		String minPrice = request.getParameter("min");
		String maxPrice = request.getParameter("max");
		// 调用bookService的相应方法获取带分页的图书信息
		Page<Book> pageBooksByPrice = bookService.getPageBooksByPrice(pageNo, minPrice, maxPrice);
		// 将请求路径写入对象中
		pageBooksByPrice.setPath(path);
		// 将获取的对象写入域中
		request.setAttribute("page", pageBooksByPrice);
		//转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
	}
	
	// 获取带分页的图书管理主页面
	protected void getPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取页码
		String pageNo = request.getParameter("pageNo");
		// 获取请求地址
		String path = WebUtils.getPath(request);
		// 调用bookService的方法获取带分页的图书信息
		Page<Book> pageBooks = bookService.getPageBooks(pageNo);
		//将path设置到pageBooks对象中
		pageBooks.setPath(path);
		//将pageBooks对象放到request域中
		request.setAttribute("page", pageBooks);
		//转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
		
	}
	
	// 此方法可以调用BookManagerServlet中的方法，但考虑之后其它项目条件可能会起羁绊，特此单列出来
//	protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 调用bookService的方法获取所有的图书
//		List<Book> books = bookService.getBooks();
		// 将所有的图书放到request域中
//		request.setAttribute("books", books);
		// 转发到显示图书的页面
//		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
		
//	}

}

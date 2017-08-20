package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 后台管理图书的Servlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService = new BookServiceImpl();
	
	// 获取带分页的图书
	protected void getPageBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取用户输入的页码
		String pageNo = request.getParameter("pageNo");
		//获取请求地址
		String path = WebUtils.getPath(request);
		// 调用bookService的方法获取带分页的图书信息
		Page<Book> pageBooks = bookService.getPageBooks(pageNo);
		//将path设置到pageBooks队象中
		pageBooks.setPath(path);
		// 将pageBooks对象放到request域中
		request.setAttribute("page", pageBooks);
		// 转发到显示所有图书的分页
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
	//根据导入的图书信息中id值域，分别调用相应方法处理
	protected void addOrUpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要导入图书信息
		String bookId = request.getParameter("bookId");
		// 之下的获取信息代码与新增图书相同，之后可以想法让其合并成一个方法
		String title = request.getParameter("book_name");
		String author = request.getParameter("book_author");
		String price = request.getParameter("book_price");
		String sales = request.getParameter("book_sales");
		String stock = request.getParameter("book_stock");
		// 一定要先判断后封装
		if("".equals(bookId)) {
			// 若导入id为空串，判断此操作为添加图书
			//封装Book对像
			Book book = new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock));
			//调用bookService的方法保存图书
			bookService.saveBook(book);	
		}else {
			//id不为空，则此操作为修改图书信息
			//将book信息封装成对象
			Book book = new Book(Integer.parseInt(bookId),title, author, Double.parseDouble(price), Integer.parseInt(sales),Integer.parseInt(stock));
			//调用bookService的更新方法
			bookService.updateBook(book);
		}
		// 获取一个新的Page对象，【或者将上面getPageBooks()方法中的page全局属性化，此处还得不到该集合】以备最后转页到最后页事查询用
		Page<Book> page = new Page<>();
		// 通过bookService获得所有book数量
		int size = bookService.getBooks().size();
		// 用book数量与page单页显示值获取总页数，注意此处int型，可能删切小数
		int no = size/page.PAGE_SIZE;
		// 通过去余判断，适当添加可能被删切的页数
		if(size % page.PAGE_SIZE != 0) {
			no += 1;
		}
		//System.out.println(no);
		//重定向到显示页面，注意此处后接&pageNo= + no，可直接跳到分页最后页面
		response.sendRedirect(request.getContextPath()+"/BookManagerServlet?way=getPageBooks&pageNo=" + no);
	}
	
	/*
	// 根据图书的id信息去更新图书储存信息
	protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要修改的图书信息
		String bookId = request.getParameter("bookId");
		// 之下的获取信息代码与新增图书相同，之后可以想法让其合并成一个方法
		String title = request.getParameter("book_name");
		String author = request.getParameter("book_author");
		String price = request.getParameter("book_price");
		String sales = request.getParameter("book_sales");
		String stock = request.getParameter("book_stock");
		//将book信息封装成对象
		Book book = new Book(Integer.parseInt(bookId),title, author, Double.parseDouble(price), Integer.parseInt(sales),Integer.parseInt(stock));
		//调用bookService的更新方法
		bookService.updateBook(book);
		//重定向到显示页面
		response.sendRedirect(request.getContextPath()+"/BookManagerServlet?way=getBooks");
	}
	*/
		
	//根据图书id查询该图书的方法
	protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book bookById = bookService.getBookById(bookId);
		// 将所获结果放在域中
		request.setAttribute("book", bookById);
		//转发到修改图书界面
		request.getRequestDispatcher("/pages/manager/book_modify.jsp").forward(request, response);

	}
		
	//根据图书的id删除图书的方法
	protected void deleteBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//获取用户输入的图书id
		String bookId = request.getParameter("bookId");
		bookService.deleteBookById(bookId);
		response.sendRedirect(request.getContextPath()+"/BookManagerServlet?way=getPageBooks");
	}	
	
	/*
	//添加图书的方法
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户输入的图书信息
		String title = request.getParameter("book_name");
		String author = request.getParameter("book_author");
		String price = request.getParameter("book_price");
		String sales = request.getParameter("book_sales");
		String stock = request.getParameter("book_stock");
		//System.out.println(title);
		//封装Book对像
		Book book = new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock));
		//调用bookService的方法保存图书
		bookService.saveBook(book);
		//方式一：直接调用getBooks方法再次获取一下数据库的图书
		//getBooks(request, response);
		//方式二：重定向到显示所有图书的方法
		response.sendRedirect(request.getContextPath()+"/BookManagerServlet?way=getBooks");
	}
	*/
	
	/*
	//获取所有图书的方法
	protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 调用BookService的方法获取所有图书
		List<Book> books = bookService.getBooks();
		//将books放到request域中
		request.setAttribute("books", books);
		//转发显示所有图书的页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	*/
}

package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService {
	
	BookDao bookDao = new BookDaoImpl();
	@Override
	public List<Book> getBooks() {
		return bookDao.getBooks();
	}
	
	
	@Override
	public void saveBook(Book book) {
		bookDao.addBook(book);
		
	}


	@Override
	public void deleteBookById(String bookId) {
		bookDao.deleteBookById(bookId);
		
	}


	@Override
	public Book getBookById(String bookId) {
		return bookDao.getBookById(bookId);
	}


	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}


	@Override
	public Page<Book> getPageBooks(String pageNo) {
		// 创建一个Page对像
		Page<Book> page = new Page<>();
		// 设置一个默认的页码
		int defaultPageNo = 1;
	
		// 将页面转换为int类型，如若不成功则跳转默认页面
		try {
			// 如果成功这跳转相应页面
			defaultPageNo = Integer.parseInt(pageNo);
		} catch (Exception e) {
		}
		// 将页面设置到page对象中
		page.setPageNo(defaultPageNo);
		return bookDao.getPageBooks(page);
	}


	@Override
	public Page<Book> getPageBooksByPrice(String pageNo, String minPrice,
			String maxPrice) {
		// 创建一个Page对像
		Page<Book> page = new Page<>();
		// 设置一个默认的页码
		int defaultPageNo = 1;
		
		// 设置两个默认的价格
		double defaultMinPrice = 0;
		double defaultMaxPrice = Double.MAX_VALUE;
		
		// 将页面转换为int类型，如若不成功则跳转默认页面
		try {
			// 如果成功这跳转相应页面
			defaultPageNo = Integer.parseInt(pageNo);
		} catch (Exception e) {
		}
		
		// 这里注意因为考虑客户的各种花式操作，在此谨慎操作，对两值分别验证
		try {
			// 将传入的字符串强转为double数据类型
			defaultMinPrice = Double.parseDouble(minPrice);
		} catch (Exception e) {
		}
		try {
			// 将传入的字符串强转为double数据类型
			defaultMaxPrice = Double.parseDouble(maxPrice);
		} catch (Exception e) {
		}
		
		// 强转之后，还需对两值结果大小进行正常确认排序
		if(defaultMinPrice > defaultMaxPrice) {
			double tmp = defaultMinPrice;
			defaultMinPrice = defaultMaxPrice;
			defaultMaxPrice = tmp;
		}
		
		// 将页面设置到page对象中
		page.setPageNo(defaultPageNo);
		return bookDao.getPageBooksByPrice(page, defaultMinPrice, defaultMaxPrice);
	}
}

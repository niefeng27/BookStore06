package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;

public interface BookService {
	/**
	 * 获取所有的图书
	 * @return
	 */
	public List<Book> getBooks();
	
	/**
	 * 保存图书的方法
	 * 
	 * @param book
	 */
	public void saveBook(Book book);
	
	/**
	 * 删除图书的方法
	 */
	public void deleteBookById(String bookId);
	
	/**
	 * 查取一本图书的方法
	 */
	public Book getBookById(String bookId);
	
	/**
	 * 修改图书的方法
	 * 
	 * @param book
	 */
	public void updateBook(Book book);
	
	/**
	 * 将客户输入页码转入page对象，并判断返回相应结果的方法
	 */
	public Page<Book> getPageBooks(String pageNo);
	
	/**
	 *  通过客户输入的价格范围，返回相应的图书信息
	 */
	public Page<Book> getPageBooksByPrice(String pageNo, String minPrice, String maxPrice);
}

package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;

public interface BookDao {
	/**
	 * 获取所有图书的方法
	 * @return
	 */
	public List<Book> getBooks();
	
	/**
	 * 添加图书的方法
	 */
	public void addBook(Book book);
	
	/**
	 * 删除图书的方法
	*/
	public void  deleteBookById(String bookId);
	
	/**
	 * 查取一本图书的方法
	 */
	public Book getBookById(String bookId);
	
	/**
	 * 修改一本图书的方法
	 */
	public void updateBook(Book book);
	
	/**
	 * 由客户输入的分页码，返回其相应的page类对象
	 */
	public Page<Book> getPageBooks(Page<Book> page);
	
	/**
	 *  通过客户输入的价格范围，返回相应的图书信息
	 */
	public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice);

	/**
	 *  批量更新图书的库存和销量
	 * 
	 */
	public void batchUpdateSalesAndStock(Object[][] params);
}

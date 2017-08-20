package com.atguigu.bookstore.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class BookDaoTest {
	BookDao bookDao = new BookDaoImpl();
	@Test
	public void testGetBooks() {
		List<Book> books = bookDao.getBooks();
		for (Book book : books) {
			System.out.println(book);
		}
	}

	@Test
	public void testSaveBook(){
		Book book = new Book(null, "西游记", "吴承恩", 66.00, 100, 100);
		bookDao.addBook(book);
	}
	
	@Test
	public void testDeleteBookById(){
		bookDao.deleteBookById("34");
	}
	
	@Test
	public void testGetBookById(){
		Book bookById = bookDao.getBookById("1");
		System.out.println(bookById);
	}
	
	@Test
	public void testUpdateBook(){
		Book book = new Book(61, "你才遭仙人跳呢", "就是你", 88.88, 100000, 1);
		bookDao.updateBook(book);
	}
	
	@Test
	public void testGetPageBooks(){
		Page<Book> page = new Page<>();
		page.setPageNo(6);
		Page<Book> pageBooks = bookDao.getPageBooks(page);
		System.out.println("总记录数是："+pageBooks.getTotalRecord());
		System.out.println("总页数是："+pageBooks.getTotalPageNo());
		System.out.println("当前页是："+pageBooks.getPageNo());
		List<Book> list = pageBooks.getList();
		for (Book book : list) {
			System.out.println(book);
		}
		
	}
	
	@Test
	public void testGetPageBooksByPrice(){
		Page<Book> page = new Page<>();
		page.setPageNo(6);
		Page<Book> pageBooks = bookDao.getPageBooksByPrice(page, 10, 29);
		System.out.println("总记录数是："+pageBooks.getTotalRecord());
		System.out.println("总页数是："+pageBooks.getTotalPageNo());
		System.out.println("当前页是："+pageBooks.getPageNo());
		List<Book> list = pageBooks.getList();
		for (Book book : list) {
			System.out.println(book);
		}
		
	}
	
	@Test
	public void testBatchUpdateSalesAndStock(){
		Object[][] params = new Object[3][];
		//update books set sales = ? , stock = ? where id = ?"
		params[0] = new Object[]{100,100,2};
		params[1] = new Object[]{200,200,3};
		params[2] = new Object[]{300,300,4};
		bookDao.batchUpdateSalesAndStock(params );
	}
	
}

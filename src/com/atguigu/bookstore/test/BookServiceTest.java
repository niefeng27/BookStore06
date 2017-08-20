package com.atguigu.bookstore.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

public class BookServiceTest {
	
	BookService bookService = new BookServiceImpl();
	@Test
	public void testGetPageBooks() {
		Page<Book> pageBooks = bookService.getPageBooks("3");
		List<Book> list = pageBooks.getList();
		for (Book book : list) {
			System.out.println(book);
		}
	}

}

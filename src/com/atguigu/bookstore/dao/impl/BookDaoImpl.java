package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public List<Book> getBooks() {
		//写sql语句  注意sql语句中较长或带特殊符号的词汇，一般需要替换简写，而一般在有返回值的查询时因为要封装成有简写的对象，所以也要在此替换简写，其它无需返回值的方法则一般不用写简写
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from books";
		//调用BaseDao的getBeanList方法获取一个集合
		List<Book> beanList = getBeanList(sql);
		return beanList;
	}

	@Override
	public void addBook(Book book) {
		//写sql语句
		String sql = "insert into books(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
		update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
	}

	@Override
	public void deleteBookById(String bookId) {
		//写sql语句
		String sql = "delete from books where id=?";
		update(sql, bookId);
	}

	@Override
	public Book getBookById(String bookId) {
		// sql
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from books where id=?";
		//调用BaseDao的getBean方法
		Book bean = getBean(sql, bookId);
		return bean;
	}

	@Override
	public void updateBook(Book book) {
		// 修改sql语句
		String sql = "update books set title=?,author=?,price=?,sales=?,stock=? where id=?";
		update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getId());
	}

	@Override
	public Page<Book> getPageBooks(Page<Book> page) {
		// 获取数据库中的图书的总记录数  注意此操作必须要先于求集合操作，否则会出现页码错误
		String sql = "select count(*) from books";
		// 其实此方法本可以直接调用本页中的getBooks().size就可以获得总数量
		long totalRecord = (long) getSingelValue(sql);
		//将总记录数设置到page对象中
		page.setTotalRecord((int)totalRecord);
		
		// 获取当前页面中带分页的list
		String sql2 = "select id, title, author, price, sales, stock, img_path imgPath from books limit ?,?";
		List<Book> beanList = getBeanList(sql2, (page.getPageNo()-1)*Page.PAGE_SIZE, Page.PAGE_SIZE);
		// 将带分页的集合设置到page对象中
		page.setList(beanList);
		return page;
	}

	@Override
	public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice,
			double maxPrice) {
		// 获取数据库中的图书的总记录数  注意此操作必须要先于求集合操作，否则会出现页码错误
		String sql = "select count(*) from books where price between ? and ?";
		// 其实此方法本可以直接调用本页中的getBooks().size就可以获得总数量
		long totalRecord = (long) getSingelValue(sql, minPrice, maxPrice);
		//将总记录数设置到page对象中
		page.setTotalRecord((int)totalRecord);
		
		// 获取当前页面中带分页的list
		String sql2 = "select id, title, author, price, sales, stock, img_path imgPath from books where price between ? and ? limit ?,?";
		List<Book> beanList = null;
		// 因为客户的一些操作可能致使下列程序出错，在此提前解决
		try {
			beanList = getBeanList(sql2, minPrice, maxPrice, (page.getPageNo()-1)*Page.PAGE_SIZE, Page.PAGE_SIZE);
			
		} catch (Exception e) {
			// e.printStackTrace();
			// 将编译时异常转化为运行时异常向上抛，待过滤器处理事件时集中解决
			throw new RuntimeException(e);
		}
		// 将带分页的集合设置到page对象中
		page.setList(beanList);
		return page;
	}

	@Override
	public void batchUpdateSalesAndStock(Object[][] params) {
		// 写sql语句
		String sql = "update books set sales=?,stock=? where id=?";
		// 调用BaseDao中的更改方法
		batchUpdate(sql, params);
	}

}

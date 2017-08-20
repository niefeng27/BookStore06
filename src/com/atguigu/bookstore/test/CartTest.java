package com.atguigu.bookstore.test;

import org.junit.Test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Cart;

public class CartTest {

	@Test
	public void test() {
		Book book = new Book(1, "帝国的前程", "乱步", 68.00, 100, 2);
		Book book2 = new Book(2, "九伐中原", "姜维", 82.00, 10, 100);
		
		//创建购物车对象
		Cart cart = new Cart();
		//将两本图书添加到购物车中
		cart.addBook2Cart(book);
		cart.addBook2Cart(book2);
		
		//在添加一本book
		cart.addBook2Cart(book);
		
		//删除book2
		cart.deleteCartItem("1");
		
		//更新book的数量
		cart.updateCartItem(book, "5");
		
		//清空购物车
//		cart.clearCart();
		
		System.out.println("购物车中图书的总数量为："+cart.getTotalCount());
		System.out.println("购物车中图书的总金额为："+cart.getTotalAmount());
		
	}

}

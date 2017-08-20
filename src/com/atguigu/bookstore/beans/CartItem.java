package com.atguigu.bookstore.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{

	/**
	 *  购物车中的购物项类
	 */
	private static final long serialVersionUID = 1L;
	private Book book; //图书信息
	private int count; //图书的数量
	private double amount; //图书的金额小计，通过计算得到
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	//金额小计是由图书的单价和图书的数量计算得到
	public double getAmount() {
		// 使用BigDecimal解决double类型的计算精度问题
		BigDecimal bigCount = new BigDecimal(count+"");
		BigDecimal bigPrice = new BigDecimal(book.getPrice()+"");
		// 此处的效果和a*=b类似
		BigDecimal multiply = bigCount.multiply(bigPrice);
		return multiply.doubleValue();
		//return count*book.getPrice();
	}
	//public void setAmount(double amount) {
		//this.amount = amount;
	//}

}

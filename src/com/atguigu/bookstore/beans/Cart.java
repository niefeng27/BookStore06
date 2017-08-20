package com.atguigu.bookstore.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 购物车类
 * 因为购物车中的数据和操作都只是在session域中进行，而非要与数据库交换，所以其相关的一些操作方法就直接写在这里
 */

public class Cart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// Map的key是图书的id，Map的值是购物车中的购物项
	private Map<String, CartItem> map = new LinkedHashMap<>();
	private int totalCount; // 购物车中图书的总数量，通过计算得到
	private double totalAmount; // 购物车图书的总金额，也是通过计算得到
	
	// 添加图书的方法
	public void addBook2Cart(Book book) {
		// 获取对应的购物项
		CartItem cartItem = map.get(book.getId()+"");
		// 判断购物车中是否有对应的购物项
		if(cartItem == null) {
			// 证明购物车中还没有该购物项
			cartItem = new CartItem();
			// 将图书信息设置到购物项中
			cartItem.setBook(book);
			// 设置该图书的数量为1
			cartItem.setCount(1);
			// 将购物项添加到购物车中
			map.put(book.getId()+"", cartItem);
		}else {
			// 证明购物车中已经存在该购物项，这时只需要将该购物项中的图书的数量加一
			// 先获取当前购物项中图书的数量
			int count = cartItem.getCount();
			// 在原有的基础上加一
			cartItem.setCount(count+1);
		}
	} 
	
	// 更新购物项
	public void updateCartItem(Book book, String bookCount) {
		// 获取要更新的图书
		CartItem cartItem = map.get(book.getId());
		// 判断购物车中是否有对应的购物项
		if(cartItem == null) {
			// 证明购物车中还没有该购物项
			cartItem = new CartItem();
			// 将图书信息设置到购物项中
			cartItem.setBook(book);
			try {
				// 将图书数量转换为int类型
				int parseInt = Integer.parseInt(bookCount);
				if(parseInt > 0) {
					// 修改当前购物项中的图书数量
					cartItem.setCount(parseInt);
				}
			} catch (Exception e) {}
			// 将购物项添加到购物车中
			map.put(book.getId()+"", cartItem);
			
		}else {
			// 证明购物车中已经存在该购物项，这时只需要将该购物项中的图书的数量加一
			try {
				// 将图书数量转换为int类型
				int parseInt = Integer.parseInt(bookCount);
				if(parseInt > 0) {
					// 修改当前购物项中的图书数量
					cartItem.setCount(parseInt);
				}
			} catch (Exception e) {}
		}
	}
	
	// 删除购物项的方法
	public void deleteCartItem(String bookId) {
		map.remove(bookId);
	}
	
	// 清空购物车的方法
	public void clearCart() {
		map.clear();
	}
	
	// 获取所有购物项的方法
	public List<CartItem> getCartItems() {
		Collection<CartItem> values = map.values();
		return new ArrayList<CartItem>(values);
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	// 购物车中的图书的总数量是购物项中的数量之和
	public int getTotalCount() {
		int totalCount = 0;
		// 获取所有的购物项
		List<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
			// 获取每个购物项中的图书的数量
			int count = cartItem.getCount();
			totalCount = totalCount + count;
		}
		return totalCount;
	}
	
	// 购物车的图书的总金额也是购物项中的金额小计之和
	public double getTotalAmount() {
		// 使用BigDecimal解决double类型的计算精度问题
		BigDecimal totalAmount = new BigDecimal("0");
		//double totalAmount = 0;
		// 获取所有的购物项
		List<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
			// 获取每个购物项中的图书的金额小计
			double amount = cartItem.getAmount();
			BigDecimal bigAmount = new BigDecimal(amount +"");
			//totalAmount = totalAmount + amount;
			// 此处的效果与a+=b相似
			totalAmount = totalAmount.add(bigAmount);
			
		}
		return totalAmount.doubleValue();
	}

}

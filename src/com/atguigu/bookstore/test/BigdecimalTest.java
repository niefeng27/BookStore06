package com.atguigu.bookstore.test;

import java.math.BigDecimal;

import org.junit.Test;

public class BigdecimalTest {

	@Test
	public void test() {
		//使用BigDecimal类的int类型的构造器
		BigDecimal a = new BigDecimal(10);
		BigDecimal b = new BigDecimal(2);
		//加
		BigDecimal add = a.add(b);
		System.out.println(add);
		//减
		BigDecimal subtract = a.subtract(b);
		System.out.println(subtract);
		//乘
		BigDecimal multiply = a.multiply(b);
		System.out.println(multiply);
		//除
		BigDecimal divide = a.divide(b);
		System.out.println(divide);
		
		//使用BigDecimal类的double类型的构造器
		BigDecimal c = new BigDecimal(0.01);
		BigDecimal d = new BigDecimal(0.09);
		BigDecimal add2 = c.add(d);
		System.out.println(add2);
		//对应double类型的计算精度问题，我们使用BigDecimal的String类型的构造器进行计算
		BigDecimal e = new BigDecimal("0.01");
		BigDecimal f = new BigDecimal("0.09");
		BigDecimal add3 = e.add(f);
		System.out.println(add3);
	}

}

package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 专门用来被继承的Servlet
 * @author HanYanBing
 *
 */
public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//在第一次请求时，设置字符集为UTF-8，在编写过滤器之后此处的字符集设置可以注销，在过滤器类中设置
		//request.setCharacterEncoding("UTF-8");
		// 1.获取请求参数way,即要调用的方法的方法名
		String methodName = request.getParameter("way");
		// 判断用户在登录还是在注册
		// if ("login".equals(way)) {
		// // 证明在登录
		// login(request, response);
		// } else if ("regist".equals(way)) {
		// // 证明在注册
		// regist(request, response);
		// }
		try {
			// 2.获取方法对象
			/*
			 * getDeclaredMethod()方法需要传入两种类型的参数： 第一个参数是要调用的方法的方法名
			 * 第二个参数是要调用的方法中需要传入的参数的类型
			 */
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			// 3.设置访问权限
			method.setAccessible(true);
			
			// 4.调用方法
			/*
			 * invoke()方法需要传入两种类型的参数 第一个参数是要调用那个对象的方法 第二个参数是调用方法时要传入的参数
			 */
			method.invoke(this, request, response);
		} catch (Exception e) {
			// e.printStackTrace();
			// 将编译时异常转化为运行时异常向上抛，待过滤器处理事件时集中解决
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

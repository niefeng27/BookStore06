package com.atguigu.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.beans.User;

/**
 *  验证是否登录的Filter
 */
public class LonginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获取对象
		HttpSession session = request.getSession();
		// 获取session域中的用户
		User user = (User) session.getAttribute("user");
		if(user != null) {
			// 证明已经登录，放行请求
			chain.doFilter(request, response);
		}else {
			// 证明还没有登录，设置一个错误消息并放到request域中
			request.setAttribute("msg", "该操作需要先登录");
			// 转发到登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}	
	}
}

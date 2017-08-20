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

/**
 * 专门用来被继承的一个过滤Filter
 * @author
 *
 */

public abstract class HttpFilter implements Filter{
	
	private FilterConfig filterConfig;

	// 获取过滤器配置信息的方法
	public FilterConfig getFilterVonfig() {
		return filterConfig;
	}
	
	// 专门用来被子类重写的一个方法
	public void init() {
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.init();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 强转为http
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		// 调用重载的doFilter方法
		doFilter(req, res, chain);
	}
	
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException;
	
	@Override
	public void destroy() {
	}
}

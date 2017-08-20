package com.atguigu.bookstore.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
	public static String getPath(HttpServletRequest request){
		// 获取请求地址
		String requestURI = request.getRequestURI();
		// 获取请求地址后面的请求参数--way......
		String queryString = request.getQueryString();
		String path = requestURI + "?" + queryString;
		// 判断path中是否包含&pageNo,因为一旦包含此后缀将形成多次此类后缀叠加，致使浏览器无法正常跳转
		if(path.contains("&pageNo")) {
			// 如果包含&pageNo，将它截取
			path = path.substring(0, path.indexOf("&pageNo"));
		}
		// System.out.println(path);
		return path;
	}
}

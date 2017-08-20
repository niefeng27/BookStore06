package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImp;

/**
 * 处理用户登录注册的Servlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userServie = new UserServiceImp();
	
	// 验证Ajax请求的方法
	protected void checkUserName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取用户名
		String userName = request.getParameter("username");
		// 封装User对象
		User user = new User(null, userName, null, null);
		// 调用userService的方法验证用户名是否可用
		boolean regist = userServie.regist(user);
		// 设置回传信息的字符集
		response.setContentType("text/html; charset=UTF-8");
		if(regist) {
			// 用户名已存在，不能注册，将提示信息写回
			response.getWriter().write("用户名已存在！");
		}else {
			// 用户名可用 将提示信息设置为桔色，注意此处style设置采用就近优先原则，不受总体设置影响
			response.getWriter().write("<font style='color:orange'>用户名可用！</font>");
		}
	}
	
	// 用户注销的方法
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取session对象
		HttpSession session = request.getSession();
		// 使session失效，还有另一个方法即将session对象持久时间改设为0
		session.invalidate();
		// 重定向到首页
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	// 用户注册的方法
	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户输入的用户名、密码、邮箱
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		// 获取用户输入的验证码
		String reqCode = request.getParameter("inputCode");
		// 获取jar工具已放入session域中的验证码
		HttpSession session = request.getSession();
		String sessCode = (String)session.getAttribute("code");
		// 判断用户输入的验证码和session域中的是否相等, 注意此处本应是"" 但这样没意义，但为防止出现空指针异常所以此处应写null
		if(reqCode != null && reqCode.equals(sessCode)) {
			// 如果验证码正确，正常处理请求并移除session域中的验证码
			session.removeAttribute("code");
			// 封装User对象
			User user = new User(null, userName, password, email);
			// 调用userServie的方法验证用户名是否可用
			boolean regist = userServie.regist(user);
			if (regist) {
				// 设置一个错误消息并把它放到request域中
				request.setAttribute("msg", "用户名已存在！");
				// 证明数据库中已经有该用户名，转发到注册页面
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			} else {
				// 证明用户名可用，将用户保存到数据库中并重定向到注册成功页面
				userServie.saveUser(user);
				response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
			}
		} else {
			// 验证码不正确，设置一个错误消息并转发到注册页面
			request.setAttribute("msg", "验证码不正确！");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

	// 用户登录的方法
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户输入的用户名和密码
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		// 封装User对象
		User user = new User(null, userName, password, null);
		// 调用userServie的方法验证用户名和密码
		User login = userServie.login(user);
		if (login != null) {
			// 先将成功的客户信息保存的session域中
			HttpSession session = request.getSession();
			session.setAttribute("user", login);
			// 证明数据库中有该用户，重定向到登录成功页面
			response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
		} else {
			// 设置一个错误消息，并把它放到request域中
			request.setAttribute("msg", "用户名或密码不正确！");
			// 证明数据库中没有该用户，转发到登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}
	}

}

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 对域中的用户信息进行识别，判断出此界面是否是在客户已登录状态 -->
	<c:if test="${not empty user }">
		<div>
			<span>欢迎<span class="um_span">${user.username }</span>光临尚硅谷书城</span>
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="OrderClientServlet?way=getMyOrders">我的订单</a>
			<a href="UserServlet?way=logout">注销</a>&nbsp;&nbsp;
			<a href="index.jsp">返回</a>
		</div>
	</c:if>	
	<c:if test="${empty user }">
		<div>
			<a href="pages/user/login.jsp">登录</a>  
			<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="pages/user/managerlogin.jsp">后台管理</a>
		</div>
	</c:if>	
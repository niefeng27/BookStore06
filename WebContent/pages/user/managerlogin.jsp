<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷管理员登录页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">

	$(function(){
		//获取登录按钮并给它绑定单击响应函数
		$("#sub_btn").click(function(){
			//获取用户输入的用户名和密码
			var userName = $("#username").val();
			var password = $("#password").val();
			if(userName != "niefeng"){
				alert("请输入正确的管理员名称！");
				//取消默认行为
				return false;
			}
			if(password != "123456"){
				alert("请输入正确的管理密码！");
				return false;
			}
		});
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷管理员</h1>
							</div>
							<div class="msg_cont">
								<b></b>
<!-- 								<span class="errorMsg">请输入用户名和密码</span> -->
								<%-- <span class="errorMsg"><%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg") %></span> --%>
								<span class="errorMsg">${empty msg?"请输入管理员名称和管理密码":msg }</span>
							</div>
							<div class="form">
								<form action="pages/manager/manager.jsp" method="post">
									<label>管理员名称：</label>
									<%-- <input value="<%=request.getParameter("username")==null?"":request.getParameter("username") %>" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" /> --%>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入管理名称" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>管 理 密 码：</label>
									<input class="itxt" type="password" placeholder="请输入管理密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>
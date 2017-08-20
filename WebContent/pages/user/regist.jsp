<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		//获取注册按钮并给它绑定单击响应函数
		$("#sub_btn").click(function(){
			
			//获取用户名、密码、确认密码、邮箱、验证码
			var userName = $("#username").val();
			var password = $("#password").val();
			var repwd = $("#repwd").val();
			var email = $("#email").val();
			var code = $("#code").val();
			//使用正则表达式对用户名、密码、邮箱进行验证
			var userReg = /^[a-zA-Z0-9_-]{3,16}$/;
			if(!userReg.test(userName)){
				alert("请输入3-16位的数字、字母、下划线或减号的用户名！");
				return false;
			};
			var pwdReg = /^[a-zA-Z0-9_-]{6,18}$/;
			if(!pwdReg.test(password)){
				alert("请输入6-18位的数字、字母、下划线或减号的密码！");
				return false;
			};
			if(repwd != password){
				alert("两次输入的密码不一致！");
				return false;
			}
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				alert("邮箱格式不正确！");
				return false;
			}
			if(code == ""){
				alert("验证码不能为空！");
				return false;
			}
		});
		
		// 给验证码绑定单击事件，使每次点击时刷新验证图像
		$("#imgCode").click(function(){
			// img标签中src一改变，浏览器就会重新向src的地址发送请求，？后的t值只是个标识后面跟一个随机产生的随机数做不同标识
			$(this).attr("src","code.jpg?t="+Math.random());
		});
		
		// 给输入用户名的文本框绑定change
		$("#username").change(function(){
			// 获取用户名输入的用户
			var username = $(this).val();
			// 设置请求地址
			var url = "UserServlet?way=checkUserName";
			// 设置请求参数
			var params = {"username":username};
			// 发送Ajax请求，此处发的是Post请求
			$.post(url, params, function(data){
				// 获取显示消息的span元素
				var $spanEle = $("#errorMsg");
				// 设置span元素中的文本内容，注意此中的date为后台传回的信息，所以不用像之前再判断是否为空
				$spanEle.html(data);
				// text为响应数据的类型，可设置位json
			}, "text")
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
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg" id="errorMsg">${msg }</span>
							<%-- <span class="errorMsg"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></span> --%>
							</div>
							<div class="form">
								<form action="UserServlet?way=regist" method="post">
									<label>用户名称：</label>
									<%-- <input value="<%=request.getParameter("username")==null?"":request.getParameter("username") %>" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" /> --%>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="${param.email }" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code" name="inputCode" />
									<!-- 此处需要绑定单击事件，对src值进行更改，从而引发插件对验证码进行重新编组 -->
									<img id="imgCode" alt="" src="code.jpg" style="float: right; margin-right: 40px;width: 80px;height: 40px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
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
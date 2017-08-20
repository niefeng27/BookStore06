<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误界面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word" style="color: red">出错了！</span>
			<%@ include file="/WEB-INF/include/welcome.jsp" %>
	<div id="main">
		<br><br><br><br><br><br><br><br><br>
		<h1 align="center">系统出现异常，请速按<span style="color: red">"Alt + F4"</span>！</h1>
		
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>
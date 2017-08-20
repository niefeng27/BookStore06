<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		//给所有要删除图书的超链接绑定单击事件
		$(".deleteBook").click(function(){
			//获取图书的名字
			//方法1
			//var bookName = $(this).parent().parent().children("td:first").text();
			//方法2
			//var bookName = $(this).parent("tr").children("td:first").text();
			//方法3
			var bookName = $(this).attr("id");
			var flag = confirm("确定要删除【"+bookName+"】这本图书吗？");
			//其实其本身就是一个布尔结果
			return flag;
			//或者再好理解点，用if判断一下
			// if(!flag){
			//		return false;
			// }
		});
	});
</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/WEB-INF/include/header.jsp" %>
	</div>
	<div id="main">
		<c:if test="${empty page.list }">
			<h1>没有任何图书</h1>
		</c:if>
		<c:if test="${not empty page.list }">
			<table>
				<tr>
					<td>名称</td>
					<td>价格</td>
					<td>作者</td>
					<td>销量</td>
					<td>库存</td>
					<td colspan="2">操作</td>
				</tr>
				<c:forEach items="${page.list }" var="book">		
				<tr>
					<td>${book.title }</td>
					<td>${book.price }</td>
					<td>${book.author }</td>
					<td>${book.sales }</td>
					<td>${book.stock }</td>
					<td><a href="BookManagerServlet?way=getBookById&bookId=${book.id }">修改</a></td>
					<td><a id="${book.title }" class="deleteBook" href="BookManagerServlet?way=deleteBookById&bookId=${book.id }">删除</a></td>
				</tr>	
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="pages/manager/book_modify.jsp">添加图书</a></td>
				</tr>	
			</table>		
		</c:if>
	
		<!-- 使用静态包含将页码包含进来 -->
		<%@ include file="/WEB-INF/include/page.jsp"%>
	</div>
		
	<div id="bottom">
	<span>
		尚硅谷书城.Copyright &copy;2015
	</span>
	</div>
</body>
</html>
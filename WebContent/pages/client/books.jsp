<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		// 给添加购物车的按钮绑定单击事件
		$(".addBook").click(function(){
			// 获取图书的id
			var bookId = $(this).attr("id");
			// 发送请求
			location = "CartServlet?way=addBook&bookId="+bookId;
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/welcome.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
			<form action="BookClientServlet" method="get">
			<!-- 此处只有使用get方式，携带隐藏属性才能将需要的数据传入后台 -->
				<input type="hidden" name="way" value="getPageBooksByPrice">
				<!-- 使用value="${param.min }"将客户所填价格一直显示在当前分页中 -->
				价格：<input type="text" name="min" value="${param.min }"> 元 -
				 <input type="text" name="max" value="${param.max }"> 元 
				<input type="submit" value="查询">
			</form>
			</div>
			<div style="text-align: center">
			<!-- 在主页面显示购物车中的总数量 -->
			<c:if test="${not empty cart.cartItems }">
				<span>您的购物车中有${cart.totalCount }件商品</span>
			</c:if>
			<c:if test="${empty cart.cartItems }">
				<span>您的购物车空空如也</span>
			</c:if>
			<!-- 将刚刚的添加操作结果显示到采购页面 -->
			<c:if test="${not empty bookTitle }">
				<div>
					您刚刚将<span style="color: red">${bookTitle }</span>加入到了购物车中
					<!-- 显示一次之后就清除掉 -->
					<c:remove var="bookTitle"/>
				</div>
			</c:if>
			<div>
				<span style="color:red">${msg }</span>
				<c:remove var="msg"/>
			</div>
		</div>	
		<c:forEach items="${page.list }" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath }" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.title }</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author }</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${book.price }</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales }</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock }</span>
					</div>
					<c:if test="${book.stock > 0 }">
						<div class="book_add">
							<!-- 因此处显示为遍历结果，所以此处只能用class标注，另为方便之后的数据提取，特将其id一并添加到此处 -->
							<button class="addBook" id="${book.id }">加入购物车</button>
						</div>
					</c:if>
					<c:if test="${book.stock == 0 }">
						<div class="book_add">
							<!-- 因此处显示为遍历结果，所以此处只能用class标注，另为方便之后的数据提取，特将其id一并添加到此处 -->
							<span style="color: blue">店家正在补货中</span>
						</div>
					</c:if>
				</div>
			</div>
		</c:forEach>
		</div>
		
	<!-- 使用静态包含将页码包含进来 -->
	<%@ include file="/WEB-INF/include/page.jsp" %>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>

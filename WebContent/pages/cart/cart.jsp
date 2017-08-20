<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		// 给清空购物车的超链接绑定单击事件
		$("#clearCart").click(function(){
			var flag = confirm("亲！确定要清空购物车吗？");
			return flag;
		});
		
		//给删除购物项的超链接绑定单击事件
		$(".deleteBook").click(function(){
			//获取图书的名字
			var bookTitle = $(this).attr("id");
			return confirm("您点错了吧！是要删除【"+bookTitle+"】吗？？？");
		});
		
		// 给输入图书的文本框绑定change事件
		$(".inputCount").change(function(){
			// 获取购物项中图书的数量的默认值，以备客户输入错误的数值，回滚到初值
			var defValue = this.defaultValue;
			// 获取用户输入的图书的数量
			var inputCount = $(this).val();
			// 获取要更新图书的id
			var bookId = $(this).attr("id");
			// 声明一个验证用户输入的图书数量是否合法的正则表达式
			var reg = /^\+?[1-9][0-9]*$/;
			// 判断用户输入的数量是否合法
			if(!reg.test(inputCount)){
				// 将文本中的值恢复为默认值
				this.value = defValue;
				alert("请输入一个正整数！");
				return false;
			}
			// 获取图书的库存
			var stock = $(this).attr("name");
			// 也可用此法：stock = parseInt(stock);
			stock = new Number(stock);
			if(inputCount > stock){
				// 已经超库存，将图书的数量恢复默认值
				this.value = defValue;
				alert("该图书的库存只有"+stock+"本！");
				return false;
			}else {
				// 将默认值修改为这一个即合法又不超库存的值
				this.defaultValue = this.value;
			}
			// 设置url
			var url = "CartServlet?way=updateCartItem";
			// 设置请求参数
			var params = {"bookId":bookId, "bookCount":inputCount};
			// 获取显示购物项中金额小计的td元素，以备下面获取新值后更改
			var $tdEle = $(this).parent().next().next();
			// 发送Ajax请求
			$.post(url, params, function(data){
				// 获取购物项中图书的总数量
				var totalCount = data.totalCount;
				// 将总数量设置到显示的span元素中
				$("#b_count").text(totalCount);
				
				// 获取购物车中图书的总金额
				var totalAmount = data.totalAmount;
				// 将总金额设置到显示的span元素中
				$("#b_price").text(totalAmount);
				
				// 获取购物项中金额小计
				var amount = data.amount;
				// 将金额小计设置到显示的td元素中
				$tdEle.text(amount);
			}, "json");
			// 发送请求更新图书的数量,此方法被上面的Ajax请求代替
			//location = "CartServlet?way=updateCartItem&bookId="+bookId+"&bookCount="+inputCount;
		});
	});

</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/WEB-INF/include/welcome.jsp" %>
	</div>
	
	<div id="main">
	<c:if test="${empty cart.cartItems }">
		<br><br><br><br><br><br><br><br><br>
		<h1 align="center">你的购物车中没有任何商品，快去<a href="index.jsp" style="color: red">购物</a>吧！</h1>
	</c:if>
	<c:if test="${not empty cart.cartItems }">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>	
			<c:forEach items="${cart.cartItems }" var="cartItem"> 	
				<tr>
					<td>${cartItem.book.title }</td>
					<td>
						<!-- 将购物车中图书的数量设置成可修改的条框，并绑定单击事件 -->
						<input name="${cartItem.book.stock }" id="${cartItem.book.id }" class="inputCount" type="text" value="${cartItem.count }" style="width:40px;text-align:center;">
					</td>
					<td>${cartItem.book.price }</td>
					<td>${cartItem.amount }</td>
					<td><a href="CartServlet?way=deleteCartItem&bookId=${cartItem.book.id }" class="deleteBook" id="${cartItem.book.title }">删除</a></td>
				</tr>	
			</c:forEach>
		</table>
		
		<div class="cart_info">
			<span class="cart_span">此车中共有<span class="b_count" id="b_count">${cart.totalCount }</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price" id="b_price">${cart.totalAmount }</span>元</span>
			<span class="cart_span"><a href="index.jsp">继续购物</a></span>
			<span class="cart_span"><a href="CartServlet?way=emptyCart" id="clearCart">清空此车</a></span>
			<span class="cart_span"><a href="OrderClientServlet?way=checkout">去结账</a></span>
		</div>
	</c:if>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>
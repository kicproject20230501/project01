<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header class="header">
		<div class="navbar" id="navbar">
			<a id="logo" href="${pageContext.request.contextPath}/main/index"><img
				src="../images/index/mainlogo.png"
				alt="" width="150px;" /></a>
			<div class="sign-text">
				<a href="${pageContext.request.contextPath}/jsp/singup.jsp">SingUp</a>
				<a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a>
			</div>
		</div>

		<div class="menubar" id="menubar">
			<ul>
				<!-- 카테고리 리스트 -->
				<li class="h4 text-center m-3 px-3">
					<a href="${pageContext.request.contextPath}/test/best">BEST</a> 
					<a href="${pageContext.request.contextPath}/jsp/shop.jsp">SHOP</a>
					<a href="#none">CART</a> 
					<a href="${pageContext.request.contextPath}/board/boardList">BOARD</a>
				</li>
			</ul>
		</div>
	</header>
</body>
</html>
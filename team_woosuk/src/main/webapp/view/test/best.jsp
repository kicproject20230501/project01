<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>Document</title>

<body class="body wrapper tabled">
	<!-- 제품 이미지 -->
	<div class="container text-center">
		<!--첫번째 줄-->
		<div id="row1" class="row row-cols-3 row-col-md-2">
			<div class="col col-6 d-flex flex-column justify-content-center align-items-center">
				<div class="blockquote text-center m-8">Male Perfume1,</div>
				<div class="blockquote-footer">Lorem ipsum dolor sit amet
					consectetur adipisicing elit. Totam, rem?</div>
			</div>
			<div class="col col-6 p-5">
				<a href="${pageContext.request.contextPath}/jsp/shop.jsp"> 
				<img src="${pageContext.request.contextPath}/images/shop/향수.webp" width="70%">
				</a>
			</div>
		</div>


		<!-- 두번째 줄-->
		<div id="row1" class="row row-cols-3 row-col-md-2">
			<div class="col col-6 p-5">
				<a href="${pageContext.request.contextPath}/jsp/shop.jsp"> 
				<img src="${pageContext.request.contextPath}/images/shop/향수.webp" width="70%">
				</a>
			</div>
			<div
				class="col col-6 d-flex flex-column justify-content-center align-items-center">
				<div class="blockquote text-center m-8">Male Perfume2,</div>
				<div class="blockquote-footer">Lorem ipsum dolor sit amet
					consectetur adipisicing elit. Totam, rem?</div>
			</div>
		</div>


		<!--세번째 줄-->
		<div id="row1" class="row row-cols-3 row-col-md-2">
			<div
				class="col col-6 d-flex flex-column justify-content-center align-items-center">
				<div class="blockquote text-center m-8">Male Perfume3,</div>
				<div class="blockquote-footer">Lorem ipsum dolor sit amet
					consectetur adipisicing elit. Totam, rem?</div>
			</div>
			<div class="col col-6 p-5">
				<a href="${pageContext.request.contextPath}/shop.jsp"> 
				<img src="${pageContext.request.contextPath}/images/shop/향수.webp" width="70%">
				</a>
			</div>
		</div>
</body>

</html>
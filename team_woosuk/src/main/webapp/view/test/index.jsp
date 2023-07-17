<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>메인 페이지</title>
</head>
<body>
	<!-- 배너 섹션 -->
	<section class="slide-banner">
		<div class="slider">
			<div class="slide">
				<div class="content first-content"
					style="background-image: url('${pageContext.request.contextPath}/images/index/banner01.png')">
					<h2>나에게 맞는 향수를 찾아보세요</h2>
					<button type="button">
						<a href="${pageContext.request.contextPath}/jsp/surveyStart.jsp">
						지금 바로 찾기</a>
					</button>
				</div>
			</div>
			<div class="slide">
				<div class="content second-content"
					style="background-image: url('${pageContext.request.contextPath}/images/index/banner02.jpg')"></div>
			</div>
			<div class="slide">
				<div class="content third-content"
					style="background-image: url('${pageContext.request.contextPath}/images/index/perfume01.webp')">
					<h2>베스트 제품</h2>
					<button type="button">
						<a href="${pageContext.request.contextPath}/jsp/best.jsp">상품보러가기</a>
					</button>
				</div>
			</div>
		</div>
	</section>

	<!-- 제품 이미지 -->
	<div class="goods container text-center">
		<!--첫번째 줄-->
		<div class="row row-cols-3 row-col-md-2">
			<div class="col p-5">
				<a href="${pageContext.request.contextPath}/jsp/shop.jsp"><img
					src="${pageContext.request.contextPath}/images/index/perfume01.webp"
					width="100%" />제품사진1</a><br /> <span>------ 가격</span>
			</div>
			<div class="col p-5">
				<a href="${pageContext.request.contextPath}/jsp/shop.jsp"> <img
					src="${pageContext.request.contextPath}/images/index/perfume01.webp"
					width="100%" /> 제품사진1
				</a><br /> <span>------ 가격</span>
			</div>
			<div class="col p-5">
				<a href="${pageContext.request.contextPath}/jsp/shop.jsp"> <img
					src="${pageContext.request.contextPath}/images/index/perfume01.webp"
					width="100%" /> 제품사진1
				</a><br /> <span>------ 가격</span>
			</div>
		</div>
		<!-- 두번째 줄-->
		<div class="row row-cols-3 row-col-md-2">
			<div class="col p-5">
				<a href="${pageContext.request.contextPath}/jsp/shop.jsp"><img
					src="${pageContext.request.contextPath}/images/index/perfume01.webp"
					width="100%" />제품사진1</a><br /> <span>------ 가격</span>
			</div>
			<div class="col p-5">
				<a href="${pageContext.request.contextPath}/jsp/shop.jsp"> <img
					src="${pageContext.request.contextPath}/images/index/perfume01.webp"
					width="100%" /> 제품사진1
				</a><br /> <span>------ 가격</span>
			</div>
			<div class="col p-5">
				<a href="${pageContext.request.contextPath}/jsp/shop.jsp"> <img
					src="${pageContext.request.contextPath}/images/index/perfume01.webp"
					width="100%" /> 제품사진1
				</a><br /> <span>------ 가격</span>
			</div>
		</div>
	</div>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
	<script>
	$(document).ready(function(){
		  $('.slider').slick({
		    autoplay: true,
		    autoplaySpeed: 6000,
		    dots: true,
		    arrows: true,
		    prevArrow: '<button type="button" class="slick-prev"></button>',
		    nextArrow: '<button type="button" class="slick-next"></button>'
		  });
		});
	</script>
</body>
</html>

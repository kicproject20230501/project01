<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">


<title>상품 정보 수정</title>
</head>
<body>
	<div class="container">
		<form
			action="${pageContext.request.contextPath}/product/productUpdatePro"
			enctype="multipart/form-data" method="post"
			onsubmit="return blankchk();">
			<input type="hidden" name="prodnum" value="${product.prodnum}">
			<input type="hidden" name="image" value="${product.image}"> <input
				type="hidden" name="detail" value="${product.detail}">
			<div class="jumbotron">
				<div class="container">
					<h1 class="display-3">상품 수정</h1>
				</div>
			</div>





			<div class="form-group row">
				<label class="col-sm-2">상품 이름</label>
				<div class="com-sm-3">
					<input type="text" id="name" name="name" class="form-control"
						value="${product.name}">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2">상품 가격</label>
				<div class="com-sm-3">
					<input type="text" id="price" name="price" class="form-control"
						value="${product.price}">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2">재고</label>
				<div class="com-sm-3">
					<input type="text" id="stock" name="stock" class="form-control"
						value="${product.stock}">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2">상세정보</label>
				<div class="com-sm-5">
					<textarea id="info" name="info" cols="50" rows="2"
						class="form-control">${product.info }</textarea>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2">성별${product.prodgender}</label>
				<div class="com-sm-5">
					<input type="radio" name="prodgender" id="prodgender" value="1"
						<c:if test="${product.prodgender eq 1}"> checked </c:if>>
					남성 <input type="radio" name="prodgender" id="prodgender" value="2"
						<c:if test="${product.prodgender eq 2}"> checked </c:if >>
					여성

				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2">설문정보1</label> <select class="form-select"
					name="prodans1" id="prodans1">
					<option value="" selected>설문정보1 선택</option>
					<option value="flower"
						<c:if test="${product.prodans1 == 'flower'}">selected</c:if>>꽃</option>
					<option value="fruit"
						<c:if test="${product.prodans1 == 'fruit'}">selected</c:if>>과일</option>
					<option value="wood"
						<c:if test="${product.prodans1 == 'wood'}">selected</c:if>>나무</option>
				</select>
			</div>

			<div class="form-group row">
				<label class="col-sm-2">설문정보2</label> <select class="form-select"
					name="prodans2" id="prodans2">
					<option value="" selected>설문정보2 선택</option>
					<option value="">-------꽃-------</option>
					<option value="lilac"
						<c:if test="${product.prodans2 == 'lilac'}">selected</c:if>>라일락</option>
					<option value="citrus"
						<c:if test="${product.prodans2 == 'citrus'}">selected</c:if>>시트러스</option>
					<option value="lavender"
						<c:if test="${product.prodans2 == 'lavender'}">selected</c:if>>라벤더</option>
					<option value="rose"
						<c:if test="${product.prodans2 == 'rose'}">selected</c:if>>장미</option>
					<option value="">-------과일-------</option>
					<option value="peach"
						<c:if test="${product.prodans2 == 'peach'}">selected</c:if>>라일락</option>
					<option value="cherry"
						<c:if test="${product.prodans2 == 'cherry'}">selected</c:if>>체리</option>
					<option value="strawberry"
						<c:if test="${product.prodans2 == 'strawberry'}">selected</c:if>>딸기</option>
					<option value="lemon"
						<c:if test="${product.prodans2 == 'lemon'}">selected</c:if>>레몬</option>
					<option value="">-------나무-------</option>
					<option value="sandal"
						<c:if test="${product.prodans2 == 'sandal'}">selected</c:if>>샌달우드</option>
					<option value="pyeonbaeg"
						<c:if test="${product.prodans2 == 'pyeonbaeg'}">selected</c:if>>편백나무</option>
					<option value="sonamu"
						<c:if test="${product.prodans2 == 'sonamu'}">selected</c:if>>소나무</option>
					<option value="void"
						<c:if test="${product.prodans2 == 'void'}">selected</c:if>>보이드우드</option>
				</select>
			</div>

			<div class="form-group row">
				<label class="col-sm-2">상품 이미지</label>
				<div>
					<label>${product.image }</label> <input type="file" name="f1"
						id="image" class="form-control">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2">상품 상세페이지</label>
				<div>
					<label>${product.detail }</label> <input type="file" name="f2"
						id="detail" class="form-control">
				</div>
			</div>

			<div class="form-floating form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">저장</button>
				</div>
			</div>





		</form>
	</div>

	<script>
	<!-- 빈칸 체크 -->
		function blankchk() {
			if (document.getElementById('name').value.trim() == "") {
				alert("상품 이름은 필수 입력 값입니다.")
				return false;
			}
			if (document.getElementById('price').value.trim() == "") {
				alert("상품 가격은 필수 입력 값입니다.")
				return false;
			}
			if (document.getElementById('stock').value.trim() == "") {
				alert("재고는 필수 입력 값입니다.")
				return false;
			}
			if (document.getElementById('prodans1').value.trim() == "") {
				alert("설문정보1은 필수 입력 값입니다.")
				return false;
			}
			if (document.getElementById('prodans2').value.trim() == "") {
				alert("설문정보2는 필수 입력 값입니다.")
				return false;
			}
			/*
			if(document.getElementById( 'image' ).value.trim() == "") {
				alert("상품 이미지는 필수 입력 값입니다.")
			  	return false;
			}
			if(document.getElementById( 'detail' ).value.trim() == "") {
				alert("상품 상세정보 이미지는 필수 입력 값입니다.")
			  	return false;
			} */else
				return true;
		}
	</script>

</body>
</html>
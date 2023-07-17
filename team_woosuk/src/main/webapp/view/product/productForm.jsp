<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">


<title>상품 등록</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/product/productPro" enctype="multipart/form-data"  method="post">
	<div  class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				상품 등록
			</h1>
		</div>
	</div>
	
	<div class="container">
		
			
			
			<div class="form-group row">
				<label class="col-sm-2">상품 이름</label>
				<div class="com-sm-3">
					<input type="text" id="name" name="name" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">상품 가격</label>
				<div class="com-sm-3">
					<input type="text" id="price" name="price" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">재고</label>
				<div class="com-sm-3">
					<input type="text" id="stock" name="stock" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">상세정보</label>
				<div class="com-sm-5">
					<textarea id="info" name="info" cols="50" rows="2" class="form-control"></textarea>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">성별</label>
				<div class="com-sm-5">
					<input type="radio" name="prodgender" id="prodgender" value="1"> 남성
					<input type="radio" name="prodgender" id="prodgender" value="2"> 여성
	
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">설문정보1</label>
				<div class="com-sm-3">
					<input type="text" name="prodans1" id="prodans1" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">설문정보2</label>
				<div class="com-sm-3">
					<input type="text" name="prodans2" id="prodans2" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">상품 이미지</label>
				<div>
					<input type="file" name="image" id="image" class="form-control">
				</div>
			</div>
				
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary" >등록</button>
				</div>
			</div>
			
	</div>
</form>	
</body>
</html>
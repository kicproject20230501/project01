<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판: </title>
</head>
<style>
	
</style>
<body>
	<div class="container">
	<!-- 게시판 테이블 -->
	<table class="table">
		<thead>
			<tr>
				<th scope="col" width="5%">No</th>
				<th scope="col" width="10%">분류</th>
				<th scope="col" width="20%">제목</th>
				<th scope="col" width="10%">작성자</th>
				<th scope="col" width="10%">입력일</th>
				<th scope="col" width="5%">조회수</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th scope="row"></th>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table> <!-- 게시판 테이블 끝 -->
	
	<!-- 게시글 등록 버튼 -->
		<div class="col">
			<button type="button" class="btn btn-outline-dark"
			onclick="location.href='${pageContext.request.contextPath}/board/boardForm'">
				<i class="fa-solid fa-pencil"></i> 쓰기
			</button>
		</div>
	</div>
	
	<!-- 게시판 페이지 -->
	<div class="d-flex justify-content-center">
			페이지리스트
	</div>
	
</body>
</html>
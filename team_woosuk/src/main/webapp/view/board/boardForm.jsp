<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 쓰기</title>
</head>
<body>
	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/board/boardPro"
		enctype="multipart/form-data">
			<div class="mt-3 mb-3">
				
			</div>
			<div class="mb-3">
				<label for="name">작성자</label>
				<input type="text" class="form-control" name="name">				
				<!-- <p>
					작성자: <%=session.getAttribute("id")%>
				</p><br>  나중에 작성자에 회원 id가 뜨게 바꾸기 -->
			</div>
			<div class="mb-3">
				<label for="subject">제목</label>
				<input type="text" class="form-control" name="subject">
			</div>
			<div class="mb-3">
				<label for="content">내용</label>
				<textarea class="form-control" rows="15" name="content"></textarea>
			</div>
			<div class="mb-3">
				<label for="image">사진 첨부</label>
				<input class="form-control" type="file" name="image">
			</div>
			
			<button type="button" class="btn btn-outline-dark"
			onclick="location.href='${pageContext.request.contextPath}/board/boardPro'">
				<i class="fa-solid fa-pencil"></i> 등록
			</button>
		</form>
	</div>
</body>
</html>
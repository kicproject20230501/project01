<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 댓글</title>
<style>
	.comment-td a {
		color: black;
	}
	.comment-td:hover {
		text-decoration: underline;
	}
</style>
</head>
<body>

	<form class="container" name="f"
		action="${pageContext.request.contextPath}/board/checkCommentDelete"
		method="post">
		<h2 align="center">전체 댓글 목록</h2>
		<br>
		<br>
		<table class="table">
			<tbody>
				<tr>
					<th><input type="checkbox" id="chkAll" name="chkAll"/> 전체선택 </th>				
					<th>게시글 제목</th>
					<th>댓글 내용</th>
					<th>작성자 아이디</th>
					<th>작성자 이름</th>
					<th>작성 날짜</th>
				</tr>
			</tbody>
			<tbody>
				<c:forEach var="admin" items="${adminComment}" varStatus="status">

					<tr>
						<td><input type="checkbox" name="chk" value="${admin.ser}"/></td>
						<td class="comment-td"><a href="${pageContext.request.contextPath}/board/boardComment?num=${admin.num}">
						${subjectList[status.index]}</a></td>
						<td>${admin.content}</td>
						<td>${admin.id}</td>
						<td>${admin.name}</td>
						<td> <fmt:formatDate value="${admin.regdate}" var="dateValue" pattern="yyyy-MM-dd HH:mm"/>${dateValue} </td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
		<div class="text-center">
			<input class="btn btn-outline-danger" type="submit" value="댓글삭제">
		</div>
	</form>

<script>
	$(document).ready(function() {
		$("#chkAll").click(function() {
			if ($("#chkAll").is(":checked"))
				$("input[name=chk]").prop("checked", true);
			else
				$("input[name=chk]").prop("checked", false);
		});
	});
</script>

</body>
</html>
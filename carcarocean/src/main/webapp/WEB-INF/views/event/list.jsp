<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 게시판 목록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/hjt.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<div class="content-main">
			<h2>이벤트 게시판 목록</h2>
				<div class="list-space align-right">
					<input type="button" value="글쓰기" onclick="location.href='writeForm.do'">
					<input type="button" value="목록" onclick="location.href='list.do'">
					<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				</div>
				<table>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</table>
		</div>
	</div>
</body>
</html>
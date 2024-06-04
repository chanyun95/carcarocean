<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('search_form');
	//이벤트 연결
	myForm.onsubmit=function(){
		const keyword = document.getElementById('keyword');
		if(keyword.value.trim() == ''){
			alert('검색어를 입력하세요.');
			keyword.value = '';
			keyword.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
	<h1 class="text-center">게시판 목록</h1>
	<hr size="1" width="100%" noshade="noshade">
	<form id="search_form" action="list.do" method="get" class="d-flex justify-content-center">
		<select name="keyfield" class="form-select" style="width:auto;">
			<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
			<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>작성자</option>
			<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
		</select>
		<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}" class="form-control" style="width:500px;" placeholder="검색어를 입력하세요.">
		<input type="submit" class="btn btn-warning btn-lg text-white" value="검색">
	</form>
	<div class="list-space float-end">
		<input type="button" value="글쓰기" class="btn btn-warning text-white" onclick="location.href='writeForm.do'"
			<c:if test="${empty user_num}">disabled="disabled"</c:if>>
		<input type="button" value="목록" class="btn btn-warning text-white" onclick="location.href='list.do'">
		<input type="button" value="홈으로" class="btn btn-warning text-white" onclick="${pageContext.request.contextPath}/main/main.do">
	</div>
	<c:if test="${count == 0}">
	<div class="result-display">
		표시할 게시물이 없습니다.
	</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="table table-hover text-center fw-bold">
		<tr class="table-light">
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
		</tr>
		<c:forEach var="board" items="${list}">
		<c:if test="${board.board_report < 10}">
		<tr>
			<td><a href="detail.do?board_num=${board.board_num}" class="text-dark text-decoration-none">${board.board_num}</a></td>
			<td><a href="detail.do?board_num=${board.board_num}" class="text-dark text-decoration-none">${board.board_title}</a></td>
			<td><a href="detail.do?board_num=${board.board_num}" class="text-dark text-decoration-none">${board.mem_id}</a></td>
			<td><a href="detail.do?board_num=${board.board_num}" class="text-dark text-decoration-none">${board.board_reg}</a></td>
			<td><a href="detail.do?board_num=${board.board_num}" class="text-dark text-decoration-none">${board.board_hit}</a></td>
		</tr>
		</c:if>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
</body>
</html>
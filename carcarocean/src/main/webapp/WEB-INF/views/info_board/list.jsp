<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보공유 게시판 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
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
	<form action="list.do" method="post">
		<div class="d-flex justify-content-between">
			<select name="keyfield" class="form-select" style="width:auto;">
				<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
				<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>작성자</option>
				<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
			</select>
			<input type="search" id="keyword" name="keyword" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon">
			<input type="submit" value="검색" class="btn btn-primary">
		</div>
	</form>
	<div class="float-end">
		<input type="button" value="글쓰기" onclick="location.href='writeForm.do'"
			<c:if test="${empty user_num}">disabled="disabled"</c:if>>
		<input type="button" value="목록" onclick="location.href='list.do'">
		<input type="button" value="홈으로" onclick="${pageContext.request.contextPath}/main/main.do">
	</div>
	<c:if test="${count == 0}">
	<div class="result-display">
		표시할 게시물이 없습니다.
	</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="table table-hover">
		<thead class="table-light">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="info" items="${list}">
		<c:if test="${info.info_board_report < 10}">
		<tr>
			<td><a href="detail.do?info_board_num=${info.info_board_num}">${info.info_board_num}</a></td>
			<td><a href="detail.do?info_board_num=${info.info_board_num}">${info.info_board_title}</a></td>
			<td><a href="detail.do?info_board_num=${info.info_board_num}">${info.mem_id}</a></td>
			<td><a href="detail.do?info_board_num=${info.info_board_num}">${info.info_board_reg}</a></td>
			<td><a href="detail.do?info_board_num=${info.info_board_num}">${info.info_board_hit}</a></td>
		</tr>
		</c:if>
		</c:forEach>
		</tbody>
	</table>
	<div class="text-center">${page}</div>
	</c:if>
</div>
</body>
</html>
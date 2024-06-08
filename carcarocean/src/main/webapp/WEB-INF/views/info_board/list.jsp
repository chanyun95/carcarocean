<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보공유 게시판 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
	<h2 class="pt-5 pb-3">정보공유 게시판 목록</h2>
	<div class="d-flex justify-content-between align-items-center roudned bg-light">
		<div class="text-start ms-4 mt-5 mb-5" style="font-size: 15pt;">
	        총 ${count}건의 글이 있습니다.
		</div>
		<form action="list.do" method="get" class="d-flex justify-content-center rounded bg-light p-5">
			<div class="d-flex justify-content-center">
				<select name="keyfield" class="form-select" style="width:auto;">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>작성자</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
				</select>
				<input type="search" name="keyword" id="keyword" value="${param.keyword}" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" style="width:300px;">
				<button type="submit" class="btn btn-warning fw-bold text-white btn-lg"><i class="bi bi-search"></i></button>
				<button class="btn btn-warning fw-bold text-white btn-lg ms-2" onclick="reset_btn()"><i class="bi bi-arrow-clockwise"></i></button>
			</div>
		</form>
	</div>
	<div class="text-end">
		<input type="button" class="btn btn-warning fw-bold text-white my-3" value="글쓰기" onclick="location.href='writeForm.do'"
			<c:if test="${empty user_num}">disabled="disabled"</c:if>>
	</div>
	<table class="table table-hover text-center">
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
</div>
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
</body>
</html>
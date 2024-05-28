<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta charset="UTF-8">
<title>고객 문의</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript">
	window.onload = function(){
		const myForm = document.getElementById('search_form');
		//이벤트 연결
		myForm.onsubmit = function(){
			const keyword = document.getElementById('keyword');
			if(keyword.value.trim()==''){
				alert('검색어를 입력하세요');
				keyword.value = '';
				keyword.focus();
				return false;
			}
		};
	};
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="content-main">
		<h2>고객 문의</h2>
		<form id="search_form" action="list.do" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
						<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>작성자</option>
						<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
		<div class="list-space align-right">
			<c:if test="${!empty user_num && user_auth == 2}">
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'">
			</c:if>
			<input type="button" value="목록" onclick="location.href='list.do'">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 문의내역이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
			<table>
				<tr>
					<th>글번호</th>
					<th>처리상태</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:forEach var="qa" items="${list}">
					<tr>
						<td>${qa.qa_num}</td>
						<c:if test="${qa.qa_status == 1}">
						<td>미처리</td>
						</c:if>
						<td><a href="detail.do?qa_num=${qa.qa_num}">${qa.qa_title}</a></td>
						<td>${qa.mem_id}</td>
						<td>${qa.qa_reg}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="align-center">${page}</div>
		</c:if>
		</div>
	</div>
</body>
</html>
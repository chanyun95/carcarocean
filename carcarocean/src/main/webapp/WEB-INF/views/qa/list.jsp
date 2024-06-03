<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<title>고객 문의</title>
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
		<div class="container">
		<hr size="1" noshade="noshade" width="100%">
		<h2 class="pt-5">고객 문의</h2>
		<!-- 검색바 -->
		<div class="d-flex justify-content-center bg-light p-5 mb-5">
			<form id="search_form" action="list.do" method="get" class="d-flex justify-content-center">
					<select name="keyfield" class="form-select">
						<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
						<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>작성자</option>
						<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
					</select>
					<input type="search" name="keyword" class="form-control rounded" id="keyword" placeholder="Search" aria-label="Search" aria-describedby="search-addon">
					<input type="submit" class="btn btn-warning ms-2" value="검색">
			</form>
		</div>
		<!-- 버튼 -->
		<div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
			<c:if test="${!empty user_num && user_auth == 2}">
			<input type="button" class="btn btn-warning ms-2" value="글쓰기" onclick="location.href='writeForm.do'">
			</c:if>
			<input type="button" class="btn btn-warning ms-2" value="목록" onclick="location.href='list.do'">
			<input type="button" class="btn btn-warning ms-2" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
		<!-- 목록 표 -->
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 문의내역이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
			<table class="table table-hover">
				<thead>
				<tr>
					<th>글번호</th>
					<th>처리상태</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="qa" items="${list}">
					<tr>
						<td>${qa.qa_num}</td>
						<c:if test="${qa.qa_status == 1}">
						<td>미처리</td>
						</c:if>
						<c:if test="${qa.qa_status == 2}">
						<td>처리</td>
						</c:if>
						<c:if test="${!empty user_num && (user_auth == 9 || user_num == qa.mem_num)}">
						<td><a href="detail.do?qa_num=${qa.qa_num}">${qa.qa_title}</a></td>
						</c:if>
						<c:if test="${empty user_num || (user_auth != 9 && user_num != qa.mem_num)}">
						<td>${qa.qa_title}</td>
						</c:if>						
						<td>${qa.mem_id}</td>
						<c:if test="${empty qa.qa_modify}">
						<td>${qa.qa_reg}</td>
						</c:if>	
						<c:if test="${!empty qa.qa_modify}">
						<td>${qa.qa_modify}</td>
						</c:if>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="text-center">${page}</div>
		</c:if>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<title>판매후기게시판 목록</title>

</head>

<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div style="background-color:#f5f6f9;">
<div class="container">
	<main class="pt-5 pb-5">
		<h2>판매후기게시판 목록</h2>
		<div class="d-flex justify-content-center p-5 mb-5">
		<form id="search_form" action="list.do" method="get" class="d-flex justify-content-center">
					<select name="keyfield" class="form-select" style="width:auto;">
						<%-- c:if와 selected를 쓰는건 검색을 계속 유지 시키기 위해서 --%>
						<option value="1" <c:if test="${param.keyfield ==1}">selected</c:if>>제목</option>
						<option value="2" <c:if test="${param.keyfield ==2}">selected</c:if>>작성자</option>
						<option value="3" <c:if test="${param.keyfield ==3}">selected</c:if>>제조사</option>
					</select>
				<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon">
				<input type="submit" class="btn btn-warning text-white ms-2" value="검색">
		</form>
		</div>
		<div class="row justify-content-end"> 
		<div class="col-auto pe-0">
			<input type="button" value="글쓰기" class="btn btn-warning text-white"
				onclick="location.href='writeForm.do'"	
					<c:if test="${empty user_num}">disabled="disabled" </c:if> <%-- 비홯성화 시키는 법 --%>
			> 
			</div>
			<!-- input 닫는 꺽쇄 -->
			<div class="col-auto pe-0">
			<input type="button" value="목록" class="btn btn-warning text-white" onclick="location.href='list.do'" >
			</div>
			<div class="col-auto mb-2">
			<input type="button" value="홈으로" class="btn btn-warning text-white" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			
			</div>
			
		</div>
		<c:if test="${count==0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count>0}">
		<div class="container">
			<table class="table table-hover mt-3 text-center fw-bold">
				<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>차명/제조사</th>
					<th>작성일</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="s_re" items="${list}">
				<tr>
					<td><a href="detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.s_re_num}</a></td>
					<td><a href="detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.s_re_title}</a></td>
					<td><a href="detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.mem_id}</a></td>
					<td><a href="detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.sell_cname} / ${s_re.sell_maker}</a></td>
					<td><a href="detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark"> ${s_re.s_re_reg} </a></td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			<div class="text-center">${page}</div>
		</c:if>
		</main>
	</div>
</div>
<!--챗봇 끝  -->
    <jsp:include page="/WEB-INF/views/common/chatbot.jsp" />
	<!-- 푸터 시작-->
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
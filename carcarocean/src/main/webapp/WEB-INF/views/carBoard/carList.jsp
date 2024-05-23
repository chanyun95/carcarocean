<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차량 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>차량 검색</h2>
		<form id="search_form" action="list.do" method="post">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield == 1"}>selected</c:if>>브랜드</option>
						<option value="2" <c:if test="${param.keyfield == 2"}>selected</c:if>>차종</option>
						<option value="3" <c:if test="${param.keyfield == 3"}>selected</c:if>>차명</option>
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
			<input type="button" value="">
		</div>
	</div>
</div>
</body>
</html>
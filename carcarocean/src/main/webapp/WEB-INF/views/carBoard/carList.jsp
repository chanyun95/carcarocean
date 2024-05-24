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
		<div class="align-right">
			<ul>
				<li>
					<select name="keyfield">
						<option value="4" <c:if test="${param.keyfield == 4"}>selected</c:if>>기본 정렬</option>
						<option value="5" <c:if test="${param.keyfield == 5"}>selected</c:if>>가격순</option>
						<option value="6" <c:if test="${param.keyfield == 6"}>selected</c:if>>등록일순</option>
					</select>
				</li>
			</ul>
		</div>
		<c:if test="${count ==0}">
		<div class="result-display">
			등록된 차량이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<tr>
				<td>사진</td>
				<td>차량이름</td>
				<td>가격</td>
				<td>연식,주행거리</td>
				<td>연료타입</td>
			</tr>
		</table>
		</c:if>
	</div>
</div>
</body>
</html>
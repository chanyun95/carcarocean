<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>차량 구매</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h4>차량 목록</h4>
		<div class="image-space">
			<c:forEach var="car" items="${carList}">
			<div class="horizontal-area">
				<a href="${pageContext.request.contextPath}/item/detail.do?item_num=${car.car_num}">
					<img src="${pageContext.request.contextPath}/upload/${car.photo}">
					<span>${car.name}</span>
					<br>
					<b><fmt:formatNumber value="${car.price}"/>원</b>
					<br>
					<fmt:formatNumber value="${car.mile}"/>km
					<span>${car.birth}</span>
				</a>
			</div>
			</c:forEach>
			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		</div>
	</div>
</div>
</body>
</html>








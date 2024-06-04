<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>구매 내역</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<hr size="1" width="100%" noshade="noshade">
		<h1 class="text-center">구매 내역</h1>
		<div class="d-flex flex-row justify-content-end">
			<form id="search_form" action="myBought.do" method="get"
				class="d-flex">
				<select id="keyfield" name="keyfield" class="form-select"
					style="width: auto;">
					<option value="3" <c:if test="${keyfield == 3}">selected</c:if>>차량</option>
				</select> <input type="search" id="keyword" name="keyword"
					class="form-control rounded" placeholder="Search"
					aria-label="Search" aria-describedby="search-addon"> <input
					type="submit" class="btn btn-primary" value="검색">
			</form>
		</div>
		<table
			class="table text-center align-content-center fw-bold mt-3">
			<thead>
				<tr class="table-light">
					<td>#</td>
					<td>차량</td>
					<td>구매가격</td>
					<td>구매일</td>
					<td>차량사진</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}">
					<tr class="align-middle">
						<td>${item.buy_num}</td>
						<td>${item.car_name}</td>
						<td><fmt:formatNumber type="number" value="${item.car_price}" pattern="#,###" />만원</td>
       					<td>${item.buy_reg}</td>
						<td><img
							src="${pageContext.request.contextPath}/upload/${item.car.car_photo}"
							width="100px" height="100px"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
				
		<div class="text-center">${page}</div>
	</div>
	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<title>차량 검수 신청 목록(관리자)</title>
</head>
<body>
	<!-- 헤더 고정 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<h1>차량 검수 신청 목록</h1>
		<div class="d-flex justify-content-between">
			<div class="d-flex">
				<label for="car_check">구분</label>
				<select id="car_check" name="car_check" class="form-select">
					<option value="0">미검수</option>
					<option value="1">검수중</option>
					<option value="2">검수완료</option>
				</select>
			</div>
			<div class="d-flex">
				<select id="car_check" name="car_check" class="form-select">
					<option value="0">신청자명</option>
					<option value="1">제조사</option>
					<option value="2">차명</option>
				</select>
				<input type="search" id="keyword" name="keyword" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon">
				<input type="button" class="btn btn-primary" value="search">
			</div>
		</div>
		<table class="table table-hover">
			<thead class="table-light">
				<tr>
					<th>#</th>
					<th>신청자명</th>
					<th>제조사</th>
					<th>차명</th>
					<th>판매희망일</th>
					<th>검수완료여부</th>
					<th>판매 등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sell" items="${list}">
				<tr>
					<td>${sell.sell_num}</td>
					<td>${sell.sell_name}</td>
					<td>${sell.sell_maker}</td>
					<td>${sell.sell_cname}</td>
					<td>${sell.sell_reg}</td>
					<td>
						<c:choose>
							<c:when test="${sell.sell_check == 0}">미검수</c:when>
							<c:when test="${sell.sell_check == 1}">검수중</c:when>
							<c:when test="${sell.sell_check == 2}">검수 완료</c:when>
						</c:choose>
					</td>
					<td>
                        <c:choose>
                            <c:when test="${sell.sell_date == 0}">즉시</c:when>
                            <c:when test="${sell.sell_date == 1}">7일 이내</c:when>
                            <c:when test="${sell.sell_date == 2}">30일 이내</c:when>
                            <c:otherwise>알 수 없음</c:otherwise>
                        </c:choose>
                    </td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
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
				<input type="search" id="keyword" name="keyword">
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th><th>신청자명</th><th>제조사</th><th>차명</th><th>판매희망일</th><th>검수완료여부</th><th>판매 등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sell" items="${list}">
				<tr>
					<td>${sell.sell_num}</td><td>${sell.sell_name}</td><td>${sell.sell_maker}</td><td>${sell.sell_cname}</td><td>${sell.sell_reg}</td><td>${sell.sell_check}</td><td>${sell.sell_date}</td>
				</tr>
				</c:forEach>
			</tbody>		
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>구매 예약 관리</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<hr size="1" width="100%" noshade="noshade">
		<h1 class="text-center">구매 예약 관리 (관리자)</h1>
		<div class="d-flex flex-row justify-content-end">
			<form id="search_form" action="adminBuyList.do" method="get" class="d-flex">
				<select id="keyfield" name="keyfield" class="form-select" style="width:auto;">
					<option value="0" <c:if test="${keyfield == 0}">selected</c:if>>이름</option>
					<option value="1" <c:if test="${keyfield == 1}">selected</c:if>>아이디</option>
					<option value="2" <c:if test="${keyfield == 2}">selected</c:if>>이메일</option>
					<option value="3" <c:if test="${keyfield == 3}">selected</c:if>>차량</option>
				</select>
				<input type="search" id="keyword" name="keyword" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon">
				<input type="submit" class="btn btn-warning btn-lg fw-bold text-white" value="검색">
			</form>
		</div>
		<table class="table table-hover text-center align-content-center fw-bold mt-3">
			<thead>
				<tr class="table-light">
					<th>#</th>
					<th>차량</th>
					<th>이름</th>
					<th>아이디</th>
					<th>이메일</th>
					<th>차량사진</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}">
					<tr class="align-middle">
						<td>${item.buy_num}</td>
						<td>${item.car.car_maker} ${item.car.car_name}</td>
						<td>${item.member.mem_name}</td>
						<td>${item.member.mem_id}</td>
						<td>${item.member.mem_email}</td>
						<td><img src="${pageContext.request.contextPath}/upload/${item.car.car_photo}" width="100px" height="100px" class="rounded"></td>
						<td><button class="btn btn-danger" onclick="insertBuy_btn(this)" data-carnum="${item.car.car_num}" data-memnum="${item.member.mem_num}">구매확정</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
	function insertBuy_btn(button){
		if(confirm('구매를 확정 하시겠습니까?')){
			if(confirm('진짜 확정 하시겠습니까?')){
				const car_num = button.getAttribute('data-carnum');
				const mem_num = button.getAttribute('data-memnum');
				location.href='adminInsertBuy.do?car_num='+car_num+'&mem_num='+mem_num;
			}
		}
	}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<form id="search_form" action="myBought.do" method="get" class="d-flex">
				<select id="keyfield" name="keyfield" class="form-select" style="width: auto;">
					<option value="3" <c:if test="${keyfield == 3}">selected</c:if>>차량</option>
				</select>
				<input type="search" id="keyword" name="keyword" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon">
				<button type="submit" class="btn btn-warning fw-bold text-white btn-lg"><i class="bi bi-search"></i></button>
				<button class="btn btn-warning fw-bold text-white btn-lg ms-2" onclick="reset_btn()"><i class="bi bi-arrow-clockwise"></i></button>
			</form>
		</div>
		
		<button onclick="writeForm_btn()" class="btn btn-warning text-white">후기 작성</button>
		<table class="table text-center align-content-center fw-bold mt-3">
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
				<c:if test="${fn:contains(car.car_photo, ',')}">
					<c:set var="photoList" value="${fn:split(car.car_photo, ',')}" />
					<c:set var="firstPhoto" value="${photoList[0]}"/>
				</c:if>
				<c:if test="${!fn:contains(car.car_photo, ',')}">
					<c:set var="firstPhoto" value="${car.car_photo}" />
				</c:if>

					<tr class="align-middle">
						<td>${item.buy_num}</td>
						<td>${item.car_name}</td>
						<td><fmt:formatNumber type="number" value="${item.car_price}" pattern="#,###" />만원</td>
       					<td>${item.buy_reg}</td>
						<td><img src="${pageContext.request.contextPath}/upload/${firstPhoto}" width="100px" height="100px"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
				
		<div class="text-center">${page}</div>
	</div>
	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
	function writeForm_btn(){
		if(confirm('후기를 작성하시겠습니까?')){
			let hostIndex = location.href.indexOf( location.host ) + location.host.length;
			let contextPath = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
			location.href = contextPath+'/b_re/writeForm.do';
		}
	}
	function reset_btn(){
		location.href = 'redirect:/buy/myBought.do';
	}
	</script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<title>내 관심 차량 목록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<hr size="1" width="100%" noshade>
		<h1 class="my-3 text-center">내 관심 차량 목록</h1>
		<table class="table table-hover mt-3 text-center fw-bold">
			<thead class="table-light">
				<tr>
					<th>차량</th>
					<th>판매 여부</th>
					<th>가격</th>
					<th>사진</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="car" items="${carList}">
				<c:if test="${fn:contains(car.car_photo, ',')}">
          			<c:set var="photoList" value="${fn:split(car.car_photo, ',')}" />
            		<c:set var="firstPhoto" value="${photoList[0]}"/>
             	</c:if>
     			<c:if test="${!fn:contains(car.car_photo, ',')}">
     				<c:set var="firstPhoto" value="${car.car_photo}" />
     			</c:if>
				<tr class="align-middle">
					<c:if test="${car.car_status==0}">
					<td><a href="${pageContext.request.contextPath}/buy/buyDetail.do?car_num=${car.car_num}" class="text-decoration-none text-dark">${car.car_maker} ${car.car_name}</a></td>
					<td><a href="${pageContext.request.contextPath}/buy/buyDetail.do?car_num=${car.car_num}" class="text-decoration-none text-dark">구매 가능</a></td>
					<td><a href="${pageContext.request.contextPath}/buy/buyDetail.do?car_num=${car.car_num}" class="text-decoration-none text-dark"><fmt:formatNumber value="${car.car_price}" type="number"/>만원</a></td>
					<td><a href="${pageContext.request.contextPath}/buy/buyDetail.do?car_num=${car.car_num}" class="text-decoration-none text-dark"><img src="${pageContext.request.contextPath}/upload/${firstPhoto}" width="100px" height="100px" class="rounded"></a></td>
					</c:if>
					<c:if test="${car.car_status==1}">
					<td>${car.car_maker} ${car.car_name}</td>
					<td>예약 완료</td>
					<td><fmt:formatNumber value="${car.car_price}" type="number"/>만원</td>
					<td><img src="${pageContext.request.contextPath}/upload/${firstPhoto}" width="100px" height="100px"></td>
					</c:if>
				</tr>
			</c:forEach>
			</tbody>
		</table>		
		<div class="text-center">${page}</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
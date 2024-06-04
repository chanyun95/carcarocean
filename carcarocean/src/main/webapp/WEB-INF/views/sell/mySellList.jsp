<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내 판매 신청 현황</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<hr size="1" width="100%" noshade="noshade">
		<h1 class="mb-3">내 판매 신청 현황</h1>
		<form id="check_form" action="mySellList.do" method="get" class="d-flex justify-content-between mb-3">
			<div class="align-self-center">
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="sell_check" id="sell_check1" value="0" <c:if test="${sell_check == 0}">checked</c:if>>
				  <label class="form-check-label" for="sell_check1">미검수</label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="sell_check" id="sell_check2" value="1" <c:if test="${sell_check == 1}">checked</c:if>>
				  <label class="form-check-label" for="sell_check2"><span class="text-primary">검수중</span></label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="sell_check" id="sell_check3" value="2" <c:if test="${sell_check == 2}">checked</c:if>>
				  <label class="form-check-label" for="sell_check3"><span class="text-danger">검수완료</span></label>
				</div>
			</div>
			<div class="d-flex justify-content-center">
				<select id="keyfield" name="keyfield" class="form-select" style="width:auto;">
					<option value="1" <c:if test="${keyfield == 1}">selected</c:if>>제조사</option>
					<option value="2" <c:if test="${keyfield == 2}">selected</c:if>>차명</option>
				</select>
				<input type="search" id="keyword" name="keyword" class="form-control rounded"
				 placeholder="Search" aria-label="Search" aria-describedby="search-addon" style="width:500px;">
				<input type="submit" class="btn btn-warning fw-bold btn-lg text-white" value="검색">
			</div>
		</form>
		<table class="table table-hover text-center mt-3">
			<thead class="table-light">
				<tr>
					<th>이름</th>
					<th>차량번호</th>
					<th>차량</th>
					<th>지역</th>
					<th>판매 희망 날짜</th>
					<th>판매 신청일</th>
					<th>검수 상태</th>
					<th>검수 날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sell" items="${list}">
					<tr>
						<td>${sell.sell_name}</td>
						<td>${sell.sell_cnumber}</td>
						<td>${sell.sell_maker} ${sell.sell_cname}</td>
						<td>${sell.sell_place1} ${sell.sell_place2}</td>
						<td>
							<c:if test="${sell.sell_date==0}">즉시</c:if>
							<c:if test="${sell.sell_date==1}">7일 이내</c:if>
							<c:if test="${sell.sell_date==2}">30일 이내</c:if>
						</td>
						<td>${fn:substring(sell.sell_reg,0,10)}</td>
						<td>
							<c:if test="${sell.sell_check==0}"><span class="text-dark">미검수</span></c:if>
							<c:if test="${sell.sell_check==1}"><span class="text-primary">검수중</span></c:if>
							<c:if test="${sell.sell_check==2}"><span class="text-danger">검수 완료</span></c:if>
						</td>
						<td>
							<c:if test="${sell.sell_check>0}">${fn:substring(sell.sell_modify_check,0,10)}</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>${page}</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
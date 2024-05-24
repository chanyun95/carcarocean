<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<title>검수 대기 차량 상세 정보</title>
</head>
<body>
	<!-- 헤더 고정 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<hr size="1" width="100%" noshade>
		<h1>검수 대기 차량 상세 정보</h1>
		<p class="">${sell.sell_maker} ${sell.sell_cname}</p>
		<p>${sell.sell_name} | ${sell.sell_reg}</p>
		<ul class="list-unstyled">
			<li>
				차량번호 : ${sell.sell_cnumber}
			</li>
			<li>
				제조사 : ${sell.sell_maker}
			</li>
			<li>
				차량명 : ${sell.sell_cname}
			</li>
			<li>
				주행거리 : ${sell.sell_mile}
			</li>
			<li>
				신청자명 : ${sell.sell_name}
			</li>
			<li>
				휴대폰번호 : ${sell.sell_phone}
			</li>
			<li>
				검수 희망 지역 (시/도) : ${sell.sell_place1}
			</li>
			<li>
				검수 희망 지역 (군/구) : ${sell.sell_place2}
			</li>
			<li>
				판매 희망일 : ${sell.sell_date}
			</li>
			<li>
				검수 진행 상황 : ${sell.sell_check}
			</li>
		</ul>
		<div class="d-felx justify-content-between">
			<input type="button" class="btn btn-warning" value="목록" onclick="location.href='adminSellList.do'">
			<input type="button" class="btn btn-success" value="검수 상태 변경">
			<input type="button" class="btn btn-danger" value="삭제" onclick="location.href='deleteSell.do?sell_num=${sell.sell_num}'">
		</div>
	</div>
</body>
</html>
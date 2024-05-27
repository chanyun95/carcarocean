<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내 차 팔기 메인</title>
	<script>
    	function openPopup() {
    		window.open("insertSellForm.do", "판매 신청서", "width=900,height=800,scrollbars=no,resizable=yes");
    	}
	</script>
	<style>
        .background-image {
            background-image: url('../images/service.jpg');
            background-size: cover;
            background-position: center;
            height: 450px;
        }

        .btn-xl {
            padding: 1.5rem 3.5rem !important;
            font-size: 2rem !important;
        }
    </style>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<body>
	<div>
		<!-- 헤더 고정 -->
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="container">
			<!-- 판매 신청 부분 -->
			<div class="bg-light d-flex flex-column justify-content-center background-image" style="height:450px;">
				<div class="m-3">
					<div>
						<h2>내 차 팔기!</h2>
						<h3>지금 카카오션에 자신의 차를 팔아보세요!</h3>
						<h1>흥정 없이 높은 가격으로 중고차를 판매하세요!!</h1>
					</div>
					<div class="p-3">
						<button onclick="openPopup()" class="btn btn-danger btn-lg btn-xl">간편 신청 ></button>
					</div>
				</div>
			</div>
			<!-- 이벤트 부분 -->
			<div class="bg-white d-flex flex-row justify-content-between border rounded mt-5 mb-5 p-5">
				<div>
					<p class="fs-3">내 차 팔기 이벤트!</p>
					<p class="fs-4">지금 <b>카카오션</b>에 내 차를 팔면?</p>
					<p class="fs-2">내 차 팔기 <b>당일 판매시</b> 혜택이 더욱 풍성해집니다!</p>
				</div>
				<div class="d-flex align-items-center">
					<ul class="list-unstyled">
						<li>
							대상고객 : 당일 판매 하신 분!
						</li>
						<li>
							상품안내 : 밀키트 포인트!
						</li>
						<li>
							사용처 : www.mychef.kr
						</li>
						<li>
							행사기간 : 
						</li>
					</ul>
				</div>
			</div>
			<!-- 카카오션의 차별점 -->
			<div class="bg-light text-center mb-5 p-5">
				<p class="fs-1"><b>카카오션</b>은</p>
				<p class="fs-2">무엇이 다른가요?</p>
				<div class="d-flex flex-row justify-content-between">
					<div class="border rounded mb-4">
						<p class="fs-5">기다림 NO 하루만에 내 차 팔기</p>
						<p class="fs-3">카카오션에서 빠르게 판매 해줍니다</p>
					</div>
					<div class="border rounded mb-4">
						<p class="fs-5">기다림 NO 하루만에 내 차 팔기</p>
						<p class="fs-3">카카오션에서 빠르게 판매 해줍니다</p>
					</div>
				</div>
				<div class="d-flex flex-row justify-content-between">
					<div class="border rounded">
						<p class="fs-5">기다림 NO 하루만에 내 차 팔기</p>
						<p class="fs-3">카카오션에서 빠르게 판매 해줍니다</p>
					</div>
					<div class="border rounded">
						<p class="fs-5">기다림 NO 하루만에 내 차 팔기</p>
						<p class="fs-3">카카오션에서 빠르게 판매 해줍니다</p>
					</div>
				</div>
			</div>
			<!-- 내 차 팔기 진행 방식 -->
			<div class="container bg-white mb-5">
				<p class="fs-1 text-center"><b>카카오션</b> 내 차 팔기</p>
				<p class="fs-2 text-center">이렇게 진행 됩니다.</p>
				<div class="d-flex justify-content-between">
					<div class="p-2 border">
						<p>step1</p>
						<span>1단계에서는 이런식으로 진행됩니다.</span>
					</div>
					<div class="p-2 border">
						<p>step2</p>
						<span>2단계에서는 이런식으로 진행됩니다.</span>
					</div>
					<div class="p-2 border">
						<p>step3</p>
						<span>3단계에서는 이런식으로 진행됩니다.</span>
					</div>
					<div class="p-2 border">
						<p>step4</p>
						<span>4단계에서는 이런식으로 진행됩니다.</span>
					</div>
					<div class="p-2 border">
						<p>step5</p>
						<span>5단계에서는 이런식으로 진행됩니다.</span>
					</div>
					<div class="p-2 border">
						<p>step6</p>
						<span>6단계에서는 이런식으로 진행됩니다.</span>
					</div>
				</div>
			</div>
			<!-- 최근 올라온 차량 3개만 보여주기 -->
			<div class="container mb-5">
				<h2 class="text-center">최근 등록된 차량</h2>
				<c:if test="${carList==null}">
						등록된 차량이 없습니다!
				</c:if>
				<c:if test="${carList!=null}">
				<div class="d-flex flex-row justify-content-center align-items-center">
					<div class="row">
					<c:forEach var="car" items="${carList}">
					<div class="col d-flex flex-column justify-content-center align-items-between border p-3 m-4">
						<c:set var="carPhotoList" value="${fn:split(car.car_photo, ',')}" />
						<c:set var="firstCarPhoto" value="${carPhotoList[0]}" />
						<div><a href="#"><img src="${pageContext.request.contextPath}/upload/${firstCarPhoto}" width="300" height="300"></a></div>
						<h3 class="text-center">${car.car_maker} / ${car.car_name}</h3>
						<h4 class="text-center"><b>${car.car_price}</b>원</h4>
						<h5 class="text-center"><b>${car.car_mile}</b>km</h5>
					</div>
					</c:forEach>
					</div>
				</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
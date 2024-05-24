<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내 차 팔기 메인</title>
	<script>
    	function openPopup() {
    		window.open("insertSellForm.do", "판매 신청서", "width=950,height=650,scrollbars=no,resizable=yes");
    	}
	</script>
</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<body>
	<div>
		<!-- 헤더 고정 -->
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="container">
			<!-- 판매 신청 부분 -->
			<div class="bg-light">
				<div>
					<h2>내 차 팔기!</h2>
					<h3>지금 카카오션에 자신의 차를 팔아보세요!</h3>
					<h1>흥정 없이 높은 가격으로 중고차를 판매하세요!!</h1>
				</div>
				<div>
					<button onclick="openPopup()" class="btn btn-danger btn-lg">간편 신청</button>
				</div>
			</div>
			<!-- 이벤트 부분 -->
			<div class="bg-white d-flex justify-content-between border rounded">
				<div>
					<p class="fs-3">내 차 팔기 이벤트!</p>
					<p class="fs-4">지금 <b>카카오션</b>에 내 차를 팔면?</p>
					<p class="fs-2">내 차 팔기 <b>당일 판매시</b> 혜택이 더욱 풍성해집니다!</p>
				</div>
				<div>
					<ul>
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
			<div class="bg-light text-center">
				<p class="fs-1"><b>카카오션</b>은</p>
				<p class="fs-2">무엇이 다른가요?</p>
				<div class="d-flex justify-content-between">
					<div class="border rounded">
						<p class="fs-5">기다림 NO 하루만에 내 차 팔기</p>
						<p class="fs-3">카카오션에서 빠르게 판매 해줍니다</p>
					</div>
					<div class="border rounded">
						<p class="fs-5">기다림 NO 하루만에 내 차 팔기</p>
						<p class="fs-3">카카오션에서 빠르게 판매 해줍니다</p>
					</div>
				</div>
				<div class="d-flex justify-content-between">
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
			<div class="container bg-white mt-5">
				<p class="fs-1"><b>카카오션</b> 내 차 팔기</p>
				<p class="fs-2">이렇게 진행 됩니다.</p>
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
		</div>
	</div>
</body>
</html>
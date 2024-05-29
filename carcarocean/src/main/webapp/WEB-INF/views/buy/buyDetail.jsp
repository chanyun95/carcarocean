<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>차량 상세 정보</title>
<script>
function buy_btn (){
	if(confirm("정말 예약하시겠습니까?")){
		/* location.href= "insertBuy.do?car_num=${car.car_num}"; */
		$.ajax({
			url:'insertBuy.do',
			type:'post',
			data:{car_num:${car.car_num}},
			dataType:'json',
			success:function(param){
				if(param.result=='success'){
					alert('예약이 완료되셨습니다!');
					$('#buy_btn').attr('disabled','disabled');
				} else if(param.result=='logout'){
					alert('로그인 후 이용해주세요!');
					location.href = '${pageContext.request.contextPath}/member/loginForm.do';
				}
			},error:function(){
				alert('네트워크에 오류가 발생했습니다!');
			}
		});
	}
};
</script>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h1>${car.car_maker} ${car.car_name}</h1>
		<p>
		${car.car_cnumber} · ${car.car_birth} · ${car.car_mile}km · 
		<c:if test="${car.car_fuel_type==1}">가솔린</c:if>
		<c:if test="${car.car_fuel_type==2}">디젤</c:if>
		<c:if test="${car.car_fuel_type==3}">전기</c:if>
		<c:if test="${car.car_fuel_type==4}">수소</c:if> · ${car.car_color} · 
		<c:if test="${car.car_auto==1}">auto</c:if>
		<c:if test="${car.car_auto==2}">non-auto</c:if> · ${car.car_price}만원
		</p>
		<div class="bg-secondary d-flex justify-content-between">
			<div class="p-3 ">#${car.car_design_op} #${car.car_con_op} #${car.car_drive_op}</div>
			<div class="p-3 "><i class="bi bi-telephone-fill"></i>${checker.checker_phone} | ${checker.checker_name}</div>
		</div>
		<!-- 갤러리 사진 옆으로 넘기기 구현해야 함 -->
		<div class="text-center">
			<c:if test="${fn:contains(car.car_photo, ',')}">
        		<c:set var="photoList" value="${fn:split(car.car_photo, ',')}" />
        		<c:set var="firstPhoto" value="${photoList[0]}"/>
       		</c:if>
			<c:if test="${!fn:contains(car.car_photo, ',')}">
				<c:set var="firstPhoto" value="${car.car_photo}" />
			</c:if>
			<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" width="1296px" height="700px">
		</div>
		<!-- 사진 아래 컨텐츠 -->
	    <div class="container-fluid">
	        <div class="row">
	            <!-- 차량 상세정보 들어갈 공간 -->
	            <main class="col-md-9">
	            	<div>
	            		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	            	</div>
	            </main>
	            <!-- 구매 버튼 공간 -->
	            <div class="col-md-3 sidebar p-4 border rounded">
	            	<div class="sticky-top">
		                <div class="fs-3 text-center"><i class="bi bi-telephone-fill"></i> ${checker.checker_phone}</div>
		                <hr size="1" width="100%" noshade>
		                <div class="fs-2">
		                	${car.car_maker} ${car.car_name}
	                	</div>
		                <div class="fs-5">
			                ${fn:substring(car.car_birth,0,7)} ·
			                ${car.car_mile}km ·
			                <c:if test="${car.car_fuel_type==1}">가솔린</c:if>
							<c:if test="${car.car_fuel_type==2}">디젤</c:if>
							<c:if test="${car.car_fuel_type==3}">전기</c:if>
							<c:if test="${car.car_fuel_type==4}">수소</c:if>
						</div>
						<div>
							<p class="fs-5">총 예상 구매비용 <b>10000만원</b></p>
						</div>
						<div class="border rounded bg-light p-3">
							<ul class="list-unstyled">
								<li><span class="float-start">차량가</span><span class="float-end">${car.car_price}만원</span><br></li>
								<li><span class="float-start">이전등록비</span><span class="float-end">130만원</span><br></li>
								<li><span class="float-start">관리비용</span><span class="float-end">21만원</span><br></li>
								<li><span class="float-start">등록신청대행수수료</span><span class="float-end">10만원</span><br></li>
							</ul>
						</div>
						<div class="fs-4 text-center">
							<span>합계 10034만원</span>
						</div>
						<div class="text-center">
							<c:if test="${car.car_status==0}">
							<input type="button" class="btn btn-danger" id="buy_btn" value="구매 예약" onclick="buy_btn()" style="padding: 1.5rem 3.0rem; font-size: 2rem;">
							</c:if>
							<c:if test="${car.car_status==1}">
							<input type="button" class="btn btn-danger" value="예약 완료" style="padding: 1.5rem 3.0rem; font-size: 2rem;" disabled>
							</c:if>
						</div>
						<div class="text-center">
							<a href="#">찜하기</a> <a href="#">비교하기</a> <a href="#">공유하기</a>
						</div>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
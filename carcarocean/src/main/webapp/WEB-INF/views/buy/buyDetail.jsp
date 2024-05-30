<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>차량 상세 정보</title>
</head>
<body>
	<div class="container">
	<fmt:formatNumber value="${car.car_price}" type="number" var="carPrice"/>
	<fmt:formatNumber value="${car.car_price*0.0007}" pattern="#" type="number" var="firstPrice"/>
	<fmt:formatNumber value="${car.car_price*0.00018}" pattern="#" type="number" var="secondPrice"/>
	<fmt:formatNumber value="${car.car_price*0.0001}" pattern="#" type="number" var="thirdPrice"/>
	<fmt:formatNumber value="${car.car_price+firstPrice+secondPrice+thirdPrice}" type="number" var="totalPrice"/>
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div>
			<h1>${car.car_maker} ${car.car_name}</h1>
			<div class="d-flex justify-content-between">
				<div class="mb-3">
					${car.car_cnumber} · ${car.car_birth} · ${car.car_mile}km · 
					<c:if test="${car.car_fuel_type==1}">가솔린</c:if>
					<c:if test="${car.car_fuel_type==2}">디젤</c:if>
					<c:if test="${car.car_fuel_type==3}">전기</c:if>
					<c:if test="${car.car_fuel_type==4}">수소</c:if> · ${car.car_color} · 
					<c:if test="${car.car_auto==1}">오토</c:if>
					<c:if test="${car.car_auto==2}">수동</c:if> · ${car.car_price}만원
				</div>
				<c:if test="${user_auth==9}">
				<div>
					<button class="btn btn-primary" onclick="">수정하기</button>
					<button class="btn btn-danger" onclick="">삭제하기</button>
				</div>
				</c:if>
			</div>
		</div>
		<div class="bg-secondary d-flex justify-content-between">
			<div class="p-3 text-white">#${car.car_design_op} #${car.car_con_op} #${car.car_drive_op}</div>
			<div class="p-3"><i class="bi bi-telephone-fill"> ${checker.checker_phone} | ${checker.checker_name}</i></div>
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
	            	<div class="border rounded mt-5" style="height:200px;">
	            		
	            	</div>
	            	<div class="border rounded mt-5" style="height:600px;">
	            		
	            	</div>
	            	<div class="border rounded mt-5" style="height:400px;">
	            		
	            	</div>
	            	<div class="border rounded mt-5" style="height:300px;">
	            		
	            	</div>
	            	<div class="border rounded mt-5" style="height:500px;">
	            		
	            	</div>
	            </main>
	            <!-- 구매 버튼 공간 -->
	            <div class="col-md-3 sidebar">
	            	<div class="sticky-top border rounded p-4">
		                <div class="fs-3 text-center"><i class="bi bi-telephone-fill"></i> ${checker.checker_phone}</div>
		                <hr size="1" width="100%" noshade>
		                <div class="fs-2">
		                	${car.car_maker} ${car.car_name}
	                	</div>
		                <div class="text-center pt-3">
			                ${fn:substring(car.car_birth,0,9)} ·
			                ${car.car_mile}km ·
			                <c:if test="${car.car_fuel_type==1}">가솔린</c:if>
							<c:if test="${car.car_fuel_type==2}">디젤</c:if>
							<c:if test="${car.car_fuel_type==3}">전기</c:if>
							<c:if test="${car.car_fuel_type==4}">수소</c:if>
						</div>
						<div class="p-3 text-center">
							<div class="fs-5">총 예상 구매비용</div>
							<div class="fs-5"><b>${totalPrice}만원</b></div>
						</div>
						
						<div class="border rounded bg-light p-3">
							<ul class="list-unstyled">
								<li><span class="float-start">차량가</span><span class="float-end">${carPrice}만원</span><br></li>
								<li><span class="float-start">이전등록비</span><span class="float-end">${firstPrice}만원</span><br></li>
								<li><span class="float-start">관리비용</span><span class="float-end">${secondPrice}만원</span><br></li>
								<li><span class="float-start">등록신청대행수수료</span><span class="float-end">${thirdPrice}만원</span><br></li>
								<!-- 회원 별 수수료 할인가 적용하면 좋음 -->
							</ul>
						</div>
						<div class="text-center pt-3">
							<span class="text-danger fs-2">합계 </span><span class="fs-4">${totalPrice}만원</span>
						</div>
						<div class="text-center pt-3">
							<!-- 구매 안 되었을 때 -->
							<c:if test="${car.car_status==0}">
							<input type="button" class="btn btn-danger" id="buy_btn" value="구매 예약" onclick="buy_btn()" style="padding: 1.5rem 3.0rem; font-size: 2rem;">
							</c:if>
							<!-- 구매 되었을 때-->
							<c:if test="${car.car_status==1}">
								<!-- 로그인된 회원이 구매한 차량일 때 -->
								<c:if test="${!empty buy}">
								<input type="button" class="btn btn-danger" value="구매 취소" onclick="deleteBuy_btn()" style="padding: 1.5rem 3.0rem; font-size: 2rem;">
								</c:if>
								<!-- 로그인된 회원이 구매한 차량이 아닐 때 -->
								<c:if test="${empty buy}">
								<input type="button" class="btn btn-danger" value="예약 완료" style="padding: 1.5rem 3.0rem; font-size: 2rem;" disabled>
								</c:if>
							</c:if>
						</div>
						<div class="text-center pt-3">
							<c:if test="${empty fav}">
							<button class="btn btn-outline-danger" id="fav_btn" onclick="favCar(${car.car_num})"><i id ="fav_icon" class="bi bi-heart">찜하기</i></button>
							</c:if>
							<c:if test="${!empty fav}">
							<button class="btn btn-outline-danger" id="fav_btn" onclick="favCar(${car.car_num})"><i id ="fav_icon" class="bi bi-heart-fill">찜하기</i></button>
							</c:if>
							<button class="btn btn-outline-success" id="share_btn" onclick=""><i class="bi bi-share">공유하기</i></button>
						</div>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<script>
function deleteBuy_btn(){
	if(confirm('구매 예약을 취소하시겠습니까?')){
		location.href='deleteBuy.do?car_num=${car.car_num}';
	}
}

function favCar(carnum){
	$.ajax({
		url:'favCar.do',
		type:'post',
		data:{car_num:carnum},
		dataType:'json',
		success:function(param){
			if(param.result=='success'){
	    		if(param.action=='insertFav'){
	    			alert('관심차량 등록 완료');
	    			$("#fav_icon").removeClass("bi-heart").addClass("bi-heart-fill");
				} else if(param.action=='removeFav'){
					alert('관심차량 삭제 완료');
					$("#fav_icon").removeClass("bi-heart-fill").addClass("bi-heart");
				}
	    	} else if(param.result=='logout'){
 				alert('로그인 후 이용해주세요!');
 				location.href = '${pageContext.request.contextPath}/member/loginForm.do';
	    	}else {
	    		alert('관심차량을 등록/삭제하는 과정에서 오류가 발생했습니다.');
	    	}
		},
		error:function(){
			
		}
	});
};


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
					location.href = '${pageContext.request.contextPath}/buy/buyDetail.do?car_num=${car.car_num}';
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
</html>
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
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
	<fmt:formatNumber value="${car.car_price}" type="number" var="carPrice"/>
	<fmt:formatNumber value="${car.car_price*0.0007}" pattern="#" type="number" var="firstPrice"/>
	<fmt:formatNumber value="${car.car_price*0.00018}" pattern="#" type="number" var="secondPrice"/>
	<fmt:formatNumber value="${car.car_price*0.0001}" pattern="#" type="number" var="thirdPrice"/>
	<fmt:formatNumber value="${car.car_price+firstPrice+secondPrice+thirdPrice}" type="number" var="totalPrice"/>
		<div class="p-3">
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
					<button class="btn btn-primary" onclick="update_car()" id="update_btn">수정하기</button>
				</div>
				</c:if>
			</div>
		</div>
		<div class="d-flex justify-content-between border rounded-top" style="background-color:#FEE500;">
			<div class="p-3">#${car.car_design_op} #${car.car_con_op} #${car.car_drive_op}</div>
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
			<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" width="1296px" height="700px" class="rounded-bottom">
		</div>
		<!-- 사진 아래 컨텐츠 -->
	    <div class="container-fluid">
	        <div class="row">
	            <!-- 차량 상세정보 들어갈 공간 -->
	            <main class="col-md-9">
	            	<div class="border rounded mt-5 p-4">
	            		<div class="text-center">
	            			<span><b class="fs-2 text-danger">${carPrice}</b><b class="fs-5"> 만원</b></span>
	            		</div>
	            		<div class="mt-3 text-center">
	            			<span class="text-secondary fs-5">할부 </span><span class="fs-5 text-decoration-underline">월 <b class="text-danger"><fmt:formatNumber type="number" value="${car.car_price/60}" pattern="#"/></b> 만원</span>
	            		</div>
	            		<div class="row mt-3">
	            			<div class="col-5 border rounded ms-5" style="height:90px; cursor:pointer;">
	            				세부 정보 위치로 이동
	            			</div>
	            			<div class="col-5 border rounded ms-5" style="height:90px; cursor:pointer;">
	            				차량평가사 정보 위치로 이동
	            			</div>
	            		</div>
	            	</div>
	            	
	            	<div class="mt-5">
            			<div class="border rounded p-5">
            				<div class="row">
	            				<div class="col-4">
	            					<h5>디자인 옵션</h5>
	            					<b>${car.car_design_op}</b>
	            				</div>
	            				<div class="col-4">
	            					<h5>디자인 옵션</h5>
	            					<b>${car.car_con_op}</b>
	            				</div>
	            				<div class="col-4">
	            					<h5>디자인 옵션</h5>
	            					<b>${car.car_drive_op}</b>
	            				</div>
            				</div>
            			</div>
	            	</div>
	            	
	            	<div class="mt-5">
	            		<h4>CarCarOcean이 직접 확인한 진단 결과</h4>
	            		<div class="border border-warning rounded-top p-5">
            				${car.car_checker_opinion}
	            		</div>
	            		<div class="d-flex justify-content-between border border-warning rounded-bottom bg-warning p-2">
            				<div>
           						<b>CarCarOcean ${checker.checker_name} 차량평가사</b>
           					</div>
            				<div>
            					<img src="${pageContext.request.contextPath}/upload/${checker.checker_photo}" class="img-circle" width="50" height="50">
           					</div>
            			</div>
	            	</div>
	            	
	            	<div class="mt-5">
	            		<h4>진단결과</h4>
		            	<div class="border rounded p-4">
		            		<div class="row">
		            			<div class="col-4">
		            				아이콘
		            			</div>
		            			<div class="col-4">
		            				사고진단
		            			</div>
		            			<div class="col-4">
		            				무사고
		            			</div>
		            		</div>
		            		<hr size="1" width="100%" noshade>
		            		<div class="row">
		            			<div class="col-4">
		            				아이콘
		            			</div>
		            			<div class="col-4">
		            				진단통과
		            			</div>
		            			<div class="col-4">
		            				무사고
		            			</div>
		            		</div>
		            		<hr size="1" width="100%" noshade>
		            		<div class="row">
		            			<div class="col-4">
		            				아이콘
		            			</div>
		            			<div class="col-4">
		            				알림사항
		            			</div>
		            			<div class="col-4">
		            				무사고
		            			</div>
		            		</div>
		            	</div>
	            	</div>
	            	
	            	<div class="mt-5">
	            		<h4>검수자 정보</h4>
		            	<div class="border rounded p-5">
		            		<div class="row mb-4">
			            		<div class="col-4"><b>${checker.checker_name}</b></div>
			            		<div class="col-4"><i class="bi bi-telephone-fill"> ${checker.checker_phone}</i></div>
			            		<div class="col-4">${checker.checker_company}</div>
		            		</div>
		            		<hr size="1" width="100%" noshade>
		            		<div class="text-center">
		            			<img src="${pageContext.request.contextPath}/upload/${checker.checker_photo}" width="250" height="150">
		            		</div>
		            	</div>
	            	</div>
	            	
	            	<div class="mt-5">
	            		<h4>예약 후 직영점 방문</h4>
	            		<div class="border rounded p-4">
            				<b>차량을 직접 보시고 싶다면, 직영점 당일 방문 예약을 이용해 주세요.</b>
	            			<div class="d-flex justify-content-between">
	            				<div>
		            				<ul>
		            					<li class="text-secondary">토요일은 전화를 통한 예약만 가능합니다.</li>
		            					<li class="text-secondary">예약 차량은 다른 고객에게 판매하지 않습니다.</li>
		            				</ul>
	            				</div>
	            				<div>
	            					<button class="btn btn-outline-danger px-5 py-3">예약하기</button>
            					</div>
            				</div>
	            		</div>
	            	</div>
	            	
	            	<div class="my-5">
	            		<h4>구매후기 정보</h4>
		            	<div class="border rounded p-5">
		            		<div class="row">
		            			<div class="col-6">
		            				첫번째 구매후기 정보
		            			</div>
		            			<div class="col-6">
		            				두번째 구매후기 정보
		            			</div>
		            		</div>
		            	</div>
	            	</div>
	            </main>
	            <!-- 구매 버튼 공간 -->
	            <div class="col-md-3 sidebar">
	            	<div class="sticky-top border rounded mt-5 p-4">
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
							<button class="btn btn-outline-danger" id="fav_btn" onclick="favCar(${car.car_num})"><i class="fav_icon bi bi-heart">찜하기</i></button>
							</c:if>
							<c:if test="${!empty fav}">
							<button class="btn btn-outline-danger" id="fav_btn" onclick="favCar(${car.car_num})"><i class="fav_icon bi bi-heart-fill">찜하기</i></button>
							</c:if>
							<button class="btn btn-outline-success" id="share_btn" onclick="share_btn()"><i class="bi bi-share">공유하기</i></button>
							<div class="text-center bg-light pt-1" id="share_div" style="display:none; position:absolute; transform:translate(85px,0)">
								<button class="btn btn-sm" style="background-color: #3b5998; color:white;" onclick="shareFacebook()"><i class="bi bi-facebook">페이스북</i></button>
								<button class="btn btn-sm" style="background-color: #00acee; color:white;" onclick="shareTwitter()"><i class="bi bi-twitter">트위터</i></button>
							</div>
						</div>
					</div>
	            </div>
	        </div>
	    </div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<script>
function update_car(){
	if(confirm('차량 정보를 수정하시겠습니까?')){
		location.href='${pageContext.request.contextPath}/car/carUpdateForm.do?car_num=${car.car_num}';
	}
}

function share_btn() {
    /* navigator.clipboard.writeText(location.href);
    alert("주소가 복사되었습니다"); */
    if($("#share_div").css("display") =="none"){
    	$("#share_div").show();
    }else {
    	$("#share_div").hide();
    }
}
const url = encodeURI(window.location.href);
function shareFacebook(){
	window.open("http://www.facebook.com/sharer/sharer.php?u=" + url,"FaceBook","width=410,height=500,resizable=yes");
}
function shareTwitter(){
	const text = '중고차를 판매해보세요!'
	window.open("https://twitter.com/intent/tweet?text=" + text + "&url=" +  url,"Twitter","width=410,height=500,resizable=yes")
}

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
	    			$(".fav_icon").removeClass("bi-heart").addClass("bi-heart-fill");
				} else if(param.action=='removeFav'){
					alert('관심차량 삭제 완료');
					$(".fav_icon").removeClass("bi-heart-fill").addClass("bi-heart");
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
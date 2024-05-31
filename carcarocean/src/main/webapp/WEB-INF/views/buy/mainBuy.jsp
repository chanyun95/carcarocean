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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>내 차 구매 메인</title>
	<style>
        /* 검색 창의 너비 조절*/
        #keyword {
            width: 400px;
            height: 50px;
        }
        /* 판매 완료 차량 사진 흑백 */
        .brightness {
        	filter: brightness(50%);
    	}
    	/* 사진 상자 */
    	.image-container {
            position: relative;
            display: inline-block;
            overflow: hidden;
        }
        /* 사진 */
        .image-container img {
            width: 100%;
            height: 100%;
            object-fit: cover; /* 이미지가 부모 div를 덮도록 설정 */
        }
        /* 하트 아이콘 */
        .overlay-icon {
            position: absolute;
            bottom: 10px; /* 원하는 위치에 따라 조정 가능 */
            right: 10px;  /* 원하는 위치에 따라 조정 가능 */
            font-size: 25px;
            color: red;
        }
        .nav-item a:link{
        	color:black;
        }
        .nav-item a:visited{
        	color:black;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
 <div class="container">
     <div class="row">
         <!-- 사이드바 메뉴 -->
         <nav class="col-md-2 bg-light sidebar rounded py-2">
             <ul class="nav flex-column">
                 <li class="nav-item">
                     <a class="nav-link" href="#">차종</a>
                 </li>
                 <li>
                 	<hr size="1" noshade width="100%">
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">연식</a>
                 </li>
                 <li>
                 	<hr size="1" noshade width="100%">
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">주행거리</a>
                 </li>
                 <li>
                 	<hr size="1" noshade width="100%">
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">가격</a>
                 </li>
                 <li>
                 	<hr size="1" noshade width="100%">
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">색상</a>
                 </li>
                 <li>
                 	<hr size="1" noshade width="100%">
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">옵션</a>
                 </li>
                 <li>
                 	<hr size="1" noshade width="100%">
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">연료</a>
                 </li>
                 <li>
                 	<hr size="1" noshade width="100%">
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">변속기</a>
                 </li>
                 <li>
                 	<hr size="1" noshade width="100%">
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">사고유무</a>
                 </li>
                 <li>
                 	<hr size="1" noshade width="100%">
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">구동방식</a>
                 </li>
             </ul>
         </nav>
         <!-- 사이드바 제외 메인 컨텐츠 -->
         <main class="col-md-10 pt-5 pb-5">
         	<!-- 검색바 -->
         	<div class="d-flex justify-content-center rounded bg-light p-5 mb-5">
				<form id="search_form" action="mainBuy.do" method="get" class="d-flex justify-content-center">
					<select id="keyfield" name="keyfield" class="form-select" style="width:auto;">
						<option value="1" <c:if test="${keyfield == 0}">selected</c:if>>제조사</option>
						<option value="2" <c:if test="${keyfield == 1}">selected</c:if>>차명</option>
					</select>
					<input type="search" id="keyword" name="keyword" class="form-control rounded" placeholder="원하는 제조사/차량을 검색하세요." aria-label="Search" aria-describedby="search-addon">
					<input type="submit" class="btn btn-warning ms-2" value="검색">
				</form>
			</div>
			<h4><b>총 <span class="text-danger"><fmt:formatNumber value="${count}" type="number"/></span>대</b></h4>
			<!-- 차량 리스트 -->
             <div class="container">
             	<div class="row mb-3">
             	<c:forEach var="car" items="${carList}" begin="0" end="11" varStatus="status">
   					<c:if test="${fn:contains(car.car_photo, ',')}">
           				<c:set var="photoList" value="${fn:split(car.car_photo, ',')}" />
            			<c:set var="firstPhoto" value="${photoList[0]}"/>
             		</c:if>
     				<c:if test="${!fn:contains(car.car_photo, ',')}">
     					<c:set var="firstPhoto" value="${car.car_photo}" />
     				</c:if>
             		<div class="col-4 mt-3">
		                <div class="p-3 border bg-light">
		                	<!-- 사진 상자 -->
		                	<div class="image-container ps-3">
			                	<!-- 판매중이거나 관리자라면 링크 그대로 이미지 그대로-->
			                	<c:if test="${car.car_status==0 or user_auth==9}">
			                	<a href="buyDetail.do?car_num=${car.car_num}">
			                		<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" style="width:270px; height:200px;" class="img-fluid img-thumbnail rounded img-fluid">
		                		</a>
			                	</c:if>
			                	<!-- 판매완료고 관리자가 아니면 이미지가 brightness되면서 링크 사라짐-->
			                	<c:if test="${car.car_status==1 and user_auth!=9}">
			                	<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" style="width:270px; height:200px;" class="img-fluid img-thumbnail rounded brightness img-fluid">
			                	</c:if>
              	     			<c:set var="favIconId" value="fav_icon_${status.index}" />
              	     			<c:if test="${!car.fav_check}">
		                		<i id="${favIconId}" class="bi bi-heart overlay-icon" style="cursor:pointer;" onclick="favCar(${car.car_num},'${favIconId}')"></i>
		                		</c:if>
		                		<c:if test="${car.fav_check}">
		                		<i id="${favIconId}" class="bi bi-heart-fill overlay-icon" style="cursor:pointer;" onclick="favCar(${car.car_num},'${favIconId}')"></i>
		                		</c:if>
		                	</div>
		                	<!-- 설명 -->
		                	<div>
			                	<p class="fs-5 mt-3">${car.car_maker} ${car.car_name}<c:if test="${car.car_status==1}"><b style="color:red;"> 예약완료</b></c:if></p>
			                	<p class="mt-3"><b><fmt:formatNumber value="${car.car_price}"/> 만원</b></p>
			                	<p class="mt-3" style="font-size:12px;">${fn:substring(car.car_birth,2,4)}년${fn:substring(car.car_birth,5,7)}월식 · ${car.car_mile}km · 
			                	<c:if test="${car.car_fuel_type==1}">가솔린</c:if>
			                	<c:if test="${car.car_fuel_type==2}">디젤</c:if>
			                	<c:if test="${car.car_fuel_type==3}">전기</c:if>
			                	<c:if test="${car.car_fuel_type==4}">수소</c:if></p>
		                	</div>
		                </div>
		            </div>
       			</c:forEach>
       			
           		</div>
             </div>
             <div class="text-center">${page}</div>
         </main>
     </div>
  </div>
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<script>
    function favCar(carnum,favIconId) {
        $.ajax({
        	url:'favCar.do',
        	type:'post',
        	data:{car_num:carnum},
        	dataType:'json',
        	success:function(param){
        		if(param.result=='success'){
    	    		if(param.action=='insertFav'){
    	    			alert('관심차량 등록 완료');
    	    			$("#" + favIconId).removeClass("bi-heart").addClass("bi-heart-fill");
    				} else if(param.action=='removeFav'){
    					alert('관심차량 삭제 완료');
    					$("#" + favIconId).removeClass("bi-heart-fill").addClass("bi-heart");
    				}
    	    	} else if(param.result=='logout'){
     				alert('로그인 후 이용해주세요!');
     				location.href = '${pageContext.request.contextPath}/member/loginForm.do';
    	    	}else {
    	    		alert('관심차량을 등록/삭제하는 과정에서 오류가 발생했습니다.');
    	    	}
        	},
        	error:function(){
        		alert('네트워크에 오류가 발생했습니다!');
        	}
        });
    };
    </script>
</html>
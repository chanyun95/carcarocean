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
        /* 검색 창의 너비를 조절하고 높이를 키웁니다 */
        #keyword {
            width: 250px; /* 너비 조절 */
            height: 50px; /* 높이 조절 */
        }
        .brightness {
        	filter: brightness(50%);
    	}
    	.image-container {
            position: relative;
            display: inline-block;
        }
        .overlay-icon {
            position: absolute;
            bottom: 10px; /* 원하는 위치에 따라 조정 가능 */
            right: 10px;  /* 원하는 위치에 따라 조정 가능 */
            font-size: 25px;
            color:red;
        }
    </style>
    <script>
	    function favCar(carnum,favIconId) {
	        $.ajax({
	        	url:'${pageContext.request.contextPath}/buy/favCar.do',
	        	type:'post',
	        	data:{car_num:carnum,mem_num:${user_num}},
	        	dataType:'json',
	        	success:function(param){
	        		if(param.result=='insertFav'){
	        			alert('관심상품 등록 완료');
	        			$("#" + favIconId).removeClass("bi-heart").addClass("bi-heart-fill");
					} else if(param.result=='removeFav'){
						alert('관심상품 삭제 완료');
						$("#" + favIconId).removeClass("bi-heart-fill").addClass("bi-heart");
					} else if(param.result=='logout'){
						alert('로그인 후 이용해주세요!');
						location.href = '${pageContext.request.contextPath}/member/loginForm.do';
					}
	        	},
	        	error:function(){
	        		alert('네트워크에 오류가 발생했습니다!');
	        	}
	        });
	    };
    </script>
</head>
<body>
<div class="container">
	<!-- 헤더 고정 -->
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<!-- 중간 컨텐츠 -->
 <div class="container-fluid">
     <div class="row">
         <!-- 사이드바 메뉴 -->
         <nav class="col-md-2 bg-light sidebar pt-5 pb-5">
             <ul class="nav flex-column">
                 <li class="nav-item">
                     <a class="nav-link active" href="#">Menu Item 1</a>
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">Menu Item 2</a>
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">Menu Item 3</a>
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="#">Menu Item 4</a>
                 </li>
             </ul>
         </nav>
         <!-- 사이드바 제외 메인 컨텐츠 -->
         <main class="col-md-10 pt-5 pb-5">
         	<!-- 검색바 -->
         	<div class="d-flex justify-content-center bg-light p-5 mb-5">
		<form id="search_form" action="" method="get" class="d-flex justify-content-center">
			<select id="keyfield" name="keyfield" class="form-select" style="width:auto;">
				<option value="0" <c:if test="${keyfield == 0}">selected</c:if>>제조사</option>
				<option value="1" <c:if test="${keyfield == 1}">selected</c:if>>차명</option>
				<option value="2" <c:if test="${keyfield == 2}">selected</c:if>>차종</option>
			</select>
			<input type="search" id="keyword" name="keyword" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon">
			<input type="submit" class="btn btn-warning" value="검색">
		</form>
	</div>
	
	<!-- 차량 리스트 -->
             <div class="container">
             	<div class="row mb-3">
             	<!-- 3 * 4 반복문 시작 -->
             	<!-- 한 블럭 시작 -->
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
		                	<div class="image-container">
			                	<!-- 판매중이거나 관리자라면 링크 그대로 이미지 그대로-->
			                	<c:if test="${car.car_status==0 or user_auth==9}">
			                	<a href="buyDetail.do?car_num=${car.car_num}">
			                		<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" style="width:270px; height:180px;" class="img-fluid img-thumbnail rounded img-fluid">
		                		</a>
			                	</c:if>
			                	<!-- 판매완료고 관리자가 아니면 이미지가 brightness되면서 링크 사라짐-->
			                	<c:if test="${car.car_status==1 and user_auth!=9}">
			                	<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" style="width:270px; height:180px;" class="img-fluid img-thumbnail rounded brightness img-fluid">
			                	</c:if>
              	     			<c:set var="favIconId" value="fav_icon_${status.index}" />
			                	<a href="#" onclick="favCar(${car.car_num},'${favIconId}')" ><i id="${favIconId}" class="bi bi-heart overlay-icon"></i></a>
		                	</div>
		                	<p class="fs-5 mt-3">${car.car_maker} ${car.car_name}<c:if test="${car.car_status==1}"><b style="color:red;"> 예약완료</b></c:if></p>
		                	<p class="mt-3"><b><fmt:formatNumber value="${car.car_price}"/>만원</b></p>
		                	<p class="mt-3" style="font-size:12px;">${fn:substring(car.car_birth,2,4)}년${fn:substring(car.car_birth,5,7)}월식 · ${car.car_mile}km · 
		                	<c:if test="${car.car_fuel_type==1}">가솔린</c:if>
		                	<c:if test="${car.car_fuel_type==2}">디젤</c:if>
		                	<c:if test="${car.car_fuel_type==3}">전기</c:if>
		                	<c:if test="${car.car_fuel_type==4}">수소</c:if></p>
		                </div>
		            </div>
           		</c:forEach>
            		<!-- 한 블럭 끝 -->
            		</div>
            		<!-- 3 * 4 반복문 끝 -->
             </div>
         </main>
     </div>
  </div>
</div>
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
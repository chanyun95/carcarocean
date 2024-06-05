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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>내 관심 차량 목록</title>
<style>
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
      /* 이미지에 마우스 갖다 대면 흑백 */
      .image-container:hover{
      	filter: brightness(85%);
      }
      
      /* 하트 아이콘 */
      .overlay-icon {
          position: absolute;
          bottom: 10px; /* 원하는 위치에 따라 조정 가능 */
          right: 10px;  /* 원하는 위치에 따라 조정 가능 */
          font-size: 25px;
          color: red;
      }
      .menu-name:link{
      	color:black;
      }
      .menu-name:visited{
      	color:black;
      }
      .menu-name{
      	text-decoration-line: none;
      }
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="row mb-3 p-5">
			<c:forEach var="car" items="${carList}" begin="0" end="11"  varStatus="status">
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
							<a href="${pageContext.request.contextPath}/buy/buyDetail.do?car_num=${car.car_num}" class="ps-3">
								<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" style="width:270px; height:200px;" class="img-fluid img-thumbnail rounded img-fluid">
							</a>
							<c:set var="favIconId" value="fav_icon_${status.index}" />
							<!-- 관심차량 아이콘 체크 -->
							<i id="${favIconId}" class="bi bi-heart-fill overlay-icon" style="cursor:pointer;" onclick="favCar(${car.car_num},'${favIconId}')"></i>
						</div>
						<!-- 설명 -->
						<div>
							<div class="fs-5 mt-3 fw-bold" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><a href="${pageContext.request.contextPath}/buy/buyDetail.do?car_num=${car.car_num}" class="menu-name">${car.car_maker} ${car.car_name}</a></div>
								<c:if test="${car.car_status==1}">
									<c:if test="${empty car.mem_num or car.mem_num!=user_num}"><span class="text-danger small fw-bold"> 예약완료</span></c:if>
									<c:if test="${!empty car.mem_num and car.mem_num==user_num}"><span class="text-warning small fw-bold"> 내가 예약한 차량</span></c:if>
								</c:if>
								<c:if test="${car.car_status!=1}">
									<p>&nbsp</p>
								</c:if>
								<p class="mt-3"><a href="buyDetail.do?car_num=${car.car_num}" class="menu-name"><b><fmt:formatNumber value="${car.car_price}"/> 만원</b></a></p>
								<div class="mt-3" style="font-size:12px;">${fn:substring(car.car_birth,2,4)}년${fn:substring(car.car_birth,5,7)}월식
								<div class="vr mx-1"></div>${car.car_mile}km
								<div class="vr mx-1"></div>
								<c:if test="${car.car_fuel_type==1}">가솔린</c:if>
								<c:if test="${car.car_fuel_type==2}">디젤</c:if>
								<c:if test="${car.car_fuel_type==3}">전기</c:if>
								<c:if test="${car.car_fuel_type==4}">수소</c:if>
		      				</div>
		    			</div>
		    		</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="text-center">${page}</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
		function removeFav_btn(button){
			if(confirm('관심차량을 삭제하시겠습니까?')){
				const car_num = button.getAttribute('data-carnum');
				location.href='removeFavAction.do?car_num='+car_num;
			}
		}
		function favCar(carnum,favIconId) {
			let hostIndex = location.href.indexOf( location.host ) + location.host.length;
			let contextPath = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
			$.ajax({
	        	url:contextPath+'/buy/favCar.do',
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
</body>
</html>
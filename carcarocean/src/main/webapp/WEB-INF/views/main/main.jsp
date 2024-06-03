<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<title>카카오션</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<style>
	#carouselExampleIndicators {
		width: 100vw;
		margin-left: calc(-50vw + 50%);
	}
</style>
</head>
<body>
	<!-- 헤더 시작 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<!-- 헤더 끝 -->
	<!-- 메인 컨텐츠 시작 -->
	<div class="container">
		<!-- 표지화면 시작 -->
		<div id="carouselExampleIndicators" class="carousel slide" style="background-color: #FEE500;">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1" ></button>
				<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="${pageContext.request.contextPath}/images/mainlogo.png" class="d-block w-20" style="height: 450px;" alt="1화면">
				</div>
				<div class="carousel-item">
					<img src="${pageContext.request.contextPath}/images/service.jpg" class="d-block w-20" style="height: 450px;" alt="2화면">
				</div>
				<div class="carousel-item">
					<img src="${pageContext.request.contextPath}/images/mainlogo.png" class="d-block w-20" style="height: 450px;" alt="3화면">
				</div>
			</div>
			<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
		<!-- 표지화면 끝 -->
		<!-- 모델검색 시작 -->
		<section id="model-find">
			<div class="container p-4">
				<h2>모델검색</h2>
				<div class="row" style="border: 2px solid #000; padding: 50px; margin: 50px 0;">
					<div class="input-group mb-3 row">
						<form id="search_form" action="${pageContext.request.contextPath}/buy/mainBuy.do" method="get" class="d-flex justify-content-around">
							<select id="keyfield" name="keyfield" class="form-select" style="width: auto;">
								<option value="1">제조사</option>
								<option value="2">차량</option>
							</select>
							<input type="search" id="keyword" name="keyword" class="form-control text-center" placeholder="원하는 제조사/차량을 검색하세요." aria-label="Search" aria-describedby="search-addon">
							<button type="submit" class="btn btn-warning fw-bold text-white btn-lg"><i class="bi bi-search"></i></button>
						</form>
					</div>
				</div>
			</div>
		</section>
		<!-- 모델검색 끝 -->
		<!-- 공지사항 시작 -->
		<section id="notice">
			<div class="container p-4">
				<h2>공지사항</h2>
				<div class="row gx-4 justify-content-left" style="border: 2px solid #000; padding: 50px; margin: 50px 0;">
					<div class="col-lg-8" >
						<div class="input-group mb-3">
								내용은 나중에 변수명 맞춰서 입력
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- 공지사항 끝 -->
		<!-- 이용후기 시작 -->
		<section id="notice">
			<div class="container p-4">
			<h2>구매 이용후기</h2>
		<div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
		
    <div class="carousel-inner">
    	
        <c:forEach var="b_re" items="${list}" varStatus="status">
            <div class="carousel-item 
            <c:if test='${status.index == 0}'>active</c:if>">
                <div class="card text-center mb-3">
                    <c:if test="${fn:contains(b_re.car_photo, ',')}">
                        <c:set var="photoList" value="${fn:split(b_re.car_photo, ',')}" />
                        <c:set var="firstPhoto" value="${photoList[0]}" />
                        <img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="card-img-top">
                    </c:if>
                    <c:if test="${!fn:contains(b_re.car_photo, ',')}">
                        <c:set var="firstPhoto" value="${b_re.car_photo}" />
                        <img src="${pageContext.request.contextPath}/upload/${b_re.car_photo}" class="card-img-top">
                    </c:if>
                    <div class="card-body">
                        <h5 class="card-title">${b_re.car_name}</h5>
                        <p class="card-text">${b_re.b_re_title}</p>
                        <p class="card-text">
                         <a href="${pageContext.request.contextPath}/b_re/detail.do?b_re_num=${b_re.b_re_num}" class="btn btn-warning text-white">구매후기</a>
                         <p>
                        <p class="card-text">
                            <small class="text-body-secondary">${b_re.b_re_reg}</small>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
</div>
</section>


		<%-- <h2>이용후기2</h2>
	    <div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
	        <div class="carousel-indicators">
	            <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="0" class="active"></button>
	            <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="1"></button>
	            <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="2"></button>
	            <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="3"></button>
	        </div>
	        <div class="carousel-inner">
	            <div class="carousel-item active">
	                <img src="${pageContext.request.contextPath}/images/mainlogo.png" class="d-block w-100" alt="First slide">
	                <div class="carousel-caption">
	                    <h5>First slide label</h5>
	                    <p>코알라 이미지~~</p>
	                </div>
	            </div>
	            <div class="carousel-item">
	                <img src="${pageContext.request.contextPath}/images/mainlogo.png" class="d-block w-100" alt="Second slide">
	                <div class="carousel-caption">
	                    <h5>Second slide label</h5>
	                    <p>펭귄 이미지~~</p>
	                </div>
	            </div>
	            <div class="carousel-item">
	                <img src="${pageContext.request.contextPath}/images/mainlogo.png" class="d-block w-100" alt="Third slide">
	                <div class="carousel-caption">
	                    <h5>Third slide label</h5>
	                    <p>Lighthouse 이미지~~</p>
	                </div>
	            </div>
	            <div class="carousel-item">
	                <img src="${pageContext.request.contextPath}/images/mainlogo.png" class="d-block w-100" alt="Fourth slide">
	                <div class="carousel-caption">
	                    <h5>Fourth slide label</h5>
	                    <p>Tulip 이미지~~</p>
	                </div>
	            </div>
	        </div>
	        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
	            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	            <span class="visually-hidden">Previous</span>
	        </button>
	        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
	            <span class="carousel-control-next-icon" aria-hidden="true"></span>
	            <span class="visually-hidden">Next</span>
	        </button>
		</div> --%>
	<!-- 이용후기 끝-->
    </div>
    <!-- 메인 컨텐츠 끝 -->
    <!-- 챗봇 시작-->
    <!--챗봇 끝  -->
    <jsp:include page="/WEB-INF/views/common/chatbot.jsp" />
	<!-- 푸터 시작-->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<!-- 푸터 끝 -->
	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
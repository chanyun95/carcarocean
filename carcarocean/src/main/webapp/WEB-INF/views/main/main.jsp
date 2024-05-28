<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>카카오션</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">
	<!-- 헤더 시작-->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<!-- 헤더 끝-->
	
	<!-- 표지화면 -->
	<div id="carouselExampleIndicators" class="carousel slide"
		style="background-color: #D7DF01;">
		
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1" ></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="${pageContext.request.contextPath}/images/mainlogo.png"
					class="d-block w-20" alt="1화면">
			</div>
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath}/images/mainlogo.png"
					class="d-block w-20" alt="2화묜">
			</div>
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath}/images/mainlogo.png"
					class="d-block w-20" alt="3화면">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<!-- 표지화면 끝-->
	<br><Br> 
	
	<!-- 모델검색 -->
	<section id="model-find">
		<div class="container px-4">
			<h2>모델검색</h2>
			<div class="row gx-4 justify-content-left" style="border: 2px solid #000; padding: 50px; margin: 50px 0;">
				<div class="col-lg-8" >
					<div class="input-group mb-3">
						<select class="form-select" id="inputGroupSelect02">
							<option selected>제조사</option>
							<option value="1">One</option>
							<option value="2">Two</option>
							<option value="3">Three</option>
						</select>
						<select class="form-select" class = "align-left" id="inputGroupSelect02">
							<option selected>대표모델</option>
							<option value="1">One</option>
							<option value="2">Two</option>
							<option value="3">Three</option>
						</select>
						<select class="form-select" class = "align-right" id="inputGroupSelect02">
							<option selected>세부모델</option>
							<option value="1">One</option>
							<option value="2">Two</option>
							<option value="3">Three</option>
						</select>
						<button type="submit" class="btn btn-primary">검색</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- 모델검색 끝 -->
	
	<!-- 공지사항 -->
	<section id="notice">
		<div class="container px-4">
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
	
	<!-- 이용후기-->
<div class="container">
    <h2>이용후기</h2>
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
    </div>
	<!-- 이용후기 끝-->
	
	<!-- Footer-->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<!-- end of Footer -->
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
<<<<<<< HEAD
</html>
=======
</html>
>>>>>>> branch 'main' of https://github.com/Kimseungyeon98/carcarocean.git

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>카카오션</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">
	<!-- Navigation-->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<!-- 표지화면 -->
	<div id="carouselExampleIndicators" class="carousel slide"
		style="background-color: #D7DF01;">
		>
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
	<section id="about">
		<div class="container px-4">
			<h2>모델검색</h2>
			<div class="row gx-4 justify-content-left" style="border: 2px solid #000; padding: 50px; margin: 50px 0;">
				<div class="col-lg-8" >
					<div class="input-group mb-3">
						<select class="form-select" id="inputGroupSelect02">
							<option selected>Choose...</option>
							<option value="1">One</option>
							<option value="2">Two</option>
							<option value="3">Three</option>
						</select>
						<select class="form-select" class = "align-left" id="inputGroupSelect02">
							<option selected>Choose...</option>
							<option value="1">One</option>
							<option value="2">Two</option>
							<option value="3">Three</option>
						</select>
						<select class="form-select" class = "align-right" id="inputGroupSelect02">
							<option selected>Choose...</option>
							<option value="1">One</option>
							<option value="2">Two</option>
							<option value="3">Three</option>
						</select>
						 <label class="input-group-text" for="inputGroupSelect02">검색</label> 
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- 모델검색 -->
	<section class="bg-light" id="services">
		<div class="container px-4">
			<div class="row gx-4 justify-content-center">
				<div class="col-lg-8">
					<h2>공지사항</h2>
					<p class="lead">Lorem ipsum dolor sit amet, consectetur
						adipisicing elit. Aut optio velit inventore, expedita quo
						laboriosam possimus ea consequatur vitae, doloribus consequuntur
						ex. Nemo assumenda laborum vel, labore ut velit dignissimos.</p>
				</div>   
			</div>
		</div>
	</section>
	<!-- Contact section-->
	<section id="contact">
		<div class="container px-4">
			<div class="row gx-4 justify-content-center">
				<div class="col-lg-8">
					<h2>관심차량</h2>
					<p class="lead">Lorem ipsum dolor sit amet, consectetur
						adipisicing elit. Vero odio fugiat voluptatem dolor, provident
						officiis, id iusto! Obcaecati incidunt, qui nihil beatae magnam et
						repudiandae ipsa exercitationem, in, quo totam.</p>
				</div>
			</div>
		</div>
	</section>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container px-4">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2023</p>
		</div>
	</footer>
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

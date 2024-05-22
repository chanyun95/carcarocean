<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Header</title>
<!-- bootstrap css cdn 링크 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<!-- bootstrap 아이콘 cdn 링크 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<style>
/* 메뉴 hover 방식 */
.dropdown:hover .dropdown-menu {
    display: block;
    margin-top: 0;
}
</style>

</head>
<body>
	<div class="container">
		<!-- nav태그 상단 고정 -->
		<nav class="navbar navbar-expand-lg navbar-light be-white">
			<div class="container-fluid">
				<!-- 로고 시작 -->
				<a href="#" class="navbar-brand"><img class="fa-2x" src="${pageContext.request.contextPath}/images/mainlogo.png" width="150" height="150"></a>
				<button type="button" class="navbar-toggler bg-light" data-toggle="collapse" data-target="#nav"><span class="navbar-toggler-icon"></span></button>
				<!-- 로고 끝 -->
				<!-- 메뉴 시작 -->
				<div class="collapse navbar-collapse justify-content-between" id="nav">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link text-dark font-weight-bold px-3" href="#">홈</a></li>
						<li class="nav-item dropdown"><a class="nav-link text-dark font-weight-bold px-3 dropdown-toggle" href="#" data-toggle="dropdown">차량 구매</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">차량 구매</a>
								<a class="dropdown-item" href="#">차량 구매 후기</a>
								<a class="dropdown-item" href="#">관심 차량</a>
							</div>
						</li>
						<li class="nav-item dropdown"><a class="nav-link text-dark font-weight-bold px-3 dropdown-toggle" href="#" data-toggle="dropdown">차량 판매</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">차량 판매</a>
								<a class="dropdown-item" href="#">차량 판매 후기</a>
							</div>
						</li>
						<li class="nav-item dropdown"><a class="nav-link text-dark font-weight-bold px-3 dropdown-toggle" href="#" data-toggle="dropdown">커뮤니티</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">자유게시판</a>
							</div>
						</li>
						<li class="nav-item dropdown"><a class="nav-link text-dark font-weight-bold px-3 dropdown-toggle" href="#" data-toggle="dropdown">고객 센터</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">공지사항</a>
								<a class="dropdown-item" href="#">이벤트</a>
								<a class="dropdown-item" href="#">고객 문의</a>
							</div>
						</li>
					</ul>
					<!-- 메뉴 끝 -->
					<!-- 로그인 및 회원가입 시작 -->
					<div>
						<i class="bi bi-person-circle"><a href="#">로그인</a></i>
						<a href="#">회원가입</a>
					</div>
					<!-- 로그인 및 회원가입 끝 -->
					<!-- 검색창 시작 -->
					<form class="form-inline ml-3">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Search">
							<div class="input-group-append bg-secondary">
								<button type="button" class="btn"><i class="bi bi-search"></i></button>
							</div>
						</div>
					</form>
					<!-- 검색창 끝 -->
				</div>
			</div>
		</nav>
	</div>
</body>
</html>
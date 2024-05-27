<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<title>내 차 구매 메인</title>
<style>
        /* 검색 창의 너비를 조절하고 높이를 키웁니다 */
        #keyword {
            width: 250px; /* 너비 조절 */
            height: 50px; /* 높이 조절 */
        }
    </style>
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
	                		<div class="col-4">
				                <div class="p-3 border bg-light">1</div>
				            </div>
				            <div class="col-4">
				                <div class="p-3 border bg-light">2</div>
				            </div>
				            <div class="col-4">
				                <div class="p-3 border bg-light">3</div>
				            </div>
                		</div>
                		<div class="row mb-3">
				            <div class="col-4">
				                <div class="p-3 border bg-light">5</div>
				            </div>
				            <div class="col-4">
				                <div class="p-3 border bg-light">6</div>
				            </div>
				            <div class="col-4">
				                <div class="p-3 border bg-light">7</div>
				            </div>
				        </div>
				        <div class="row mb-3">
				            <div class="col-4">
				                <div class="p-3 border bg-light">9</div>
				            </div>
				            <div class="col-4">
				                <div class="p-3 border bg-light">10</div>
				            </div>
				            <div class="col-4">
				                <div class="p-3 border bg-light">11</div>
				            </div>
				        </div>
	                </div>
	            </main>
	        </div>
	    </div>
	    <!-- 푸터 -->
	    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </div>
</body>
</html>
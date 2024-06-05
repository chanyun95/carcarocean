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
	.card-text{
	overflow:hidden; 
	text-overflow:ellipsis; 
	display: -webkit-box; 
	-webkit-line-clamp: 3; 
	-webkit-box-orient: vertical;
	}
</style>
</head>
<body>
	<!-- 로딩화면 인클루드 필요하다면 -->
	<%-- <jsp:include page="/WEB-INF/views/common/loading.jsp" /> --%>
	<!-- 헤더 시작 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<!-- 헤더 끝 -->
	<!-- 배경색 -->
	<div style="background-color:#f7f7f7;">
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
					<img src="${pageContext.request.contextPath}/images/logo.png" class="d-block w-20" style="height: 450px;" alt="1화면">
				</div>
				<div class="carousel-item">
					<img src="${pageContext.request.contextPath}/images/logo.png" class="d-block w-20" style="height: 450px;" alt="2화면">
				</div>
				<div class="carousel-item">
					<img src="${pageContext.request.contextPath}/images/logo.png" class="d-block w-20" style="height: 450px;" alt="3화면">
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
		 
		<!-- 공지사항시작 -->
		<section id="notice">
		<div class="container">
		<div class="row row-cols-1 row-cols-md-4 g-4">
			<div class="col">
			<a href="${pageContext.request.contextPath}/notice/list.do" class="text-decoration-none text-dark">
				<strong class="fs-3">공지사항</strong></a>
				<c:forEach var="notice" items="${noticeList}" varStatus="status">
					<c:if test="${status.index == 0}">
						<div class="card" style="width: 18rem;">
							<c:if test="${!empty notice.notice_photo && fn:contains(notice.notice_photo, ',')}">
                            <c:set var="photoList" value="${fn:split(notice.notice_photo, ',')}" />
                            <c:set var="firstPhoto" value="${photoList[0]}" />
                            <a href="${pageContext.request.contextPath}/notice/detail.do?notice_num=${notice.notice_num}">
                            <img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="card-img-top" style="width:286px; height:200px;">
                            </a>
                        </c:if>
                        <c:if test="${!empty notice.notice_photo && !fn:contains(notice.notice_photo, ',')}">
                            <c:set var="firstPhoto" value="${notice.notice_photo}" />
                            <a href="${pageContext.request.contextPath}/notice/detail.do?notice_num=${notice.notice_num}">
                            <img src="${pageContext.request.contextPath}/upload/${notice.notice_photo}" class="card-img-top" style="width:286px; height:200px;">
                            </a>
                        </c:if>
                        <!-- 사진이 없는경우 -->
						<c:if test="${empty notice.notice_photo}">
                        	<a href="${pageContext.request.contextPath}/notice/detail.do?event_num=${notice.notice_num}" class="d-flex justify-content-center align-items-center" style="height: 200px;">
                        	<i class="bi bi-megaphone" style="font-size: 5rem;"></i>
                        	</a>
                        </c:if>
							<div class="card-body">
								<h5 class="card-title text-truncate">
								<a href="${pageContext.request.contextPath}/notice/detail.do?notice_num=${notice.notice_num}" class="text-decoration-none text-dark">
								${notice.notice_title}</a></h5>
								<p class="card-text" style="max-width:500px;">${notice.notice_content}</p>
							</div>
							<ul class="list-group list-group-flush">
								<c:forEach var="notice" items="${noticeList}" varStatus="status">
									<c:if test="${status.index > 0}">
										<li class="list-group-item text-truncate">
										<a href="${pageContext.request.contextPath}/notice/detail.do?notice_num=${notice.notice_num}" class="text-decoration-none text-dark">
										${notice.notice_title}
										</a>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<!-- 공지사항 끝 -->
			<!-- 이벤트 시작 -->
			<div class="col">
			<a href="${pageContext.request.contextPath}/event/list.do" class="text-decoration-none text-dark">
				<strong class="fs-3">이벤트</strong></a>
				<c:forEach var="event" items="${EventList}" varStatus="status">
					<c:if test="${status.index == 0}">
						<div class="card" style="width: 18rem;">
                        <!-- 사진이 여러장일 경우 -->
						<c:if test="${!empty event.event_photo && fn:contains(event.event_photo, ',')}">
                            <c:set var="photoList" value="${fn:split(event.event_photo, ',')}" />
                            <c:set var="firstPhoto" value="${photoList[0]}" />
                            <a href="${pageContext.request.contextPath}/event/detail.do?event_num=${event.event_num}">
                            <img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="card-img-top" style="width:286px; height:200px;">
                            </a>
                        </c:if>
                        <!-- 사진이 한 장일 경우 -->
                        <c:if test="${!empty event.event_photo && !fn:contains(event.event_photo, ',')}">
                            <c:set var="firstPhoto" value="${event.event_photo}" />
                            <a href="${pageContext.request.contextPath}/event/detail.do?event_num=${event.event_num}">
                            <img src="${pageContext.request.contextPath}/upload/${event.event_photo}" class="card-img-top" style="width:286px; height:200px;">
                            </a>
                        </c:if>
                        <!-- 사진이 없는경우 -->
						<c:if test="${empty event.event_photo}">
                        	<a href="${pageContext.request.contextPath}/event/detail.do?event_num=${event.event_num}" class="d-flex justify-content-center align-items-center" style="height: 200px;">
                        	<i class="bi bi-calendar-event" style="font-size: 5rem;"></i>
                        	</a>
                        </c:if>
							<div class="card-body">
								<h5 class="card-title text-truncate">
								<a href="${pageContext.request.contextPath}/event/detail.do?event_num=${event.event_num}" class="text-decoration-none text-dark">
								${event.event_title}</a></h5>
								<span class="card-text" style="max-width:500px;">
								${event.event_content}</span>
							</div>
							<ul class="list-group list-group-flush">
								<c:forEach var="event" items="${EventList}" varStatus="status">
									<c:if test="${status.index > 0}">
										<li class="list-group-item text-truncate">
										<a href="${pageContext.request.contextPath}/event/detail.do?event_num=${event.event_num}" class="text-decoration-none text-dark">
										${event.event_title}
										</a>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<!-- 이벤트 끝 -->
			<!-- 뉴스 시작 -->
			<div class="col">
			<a href="${pageContext.request.contextPath}/news/list.do" class="text-decoration-none text-dark">
				 <strong class="fs-3">뉴스</strong></a>
            <c:forEach var="news" items="${NewsList}" varStatus="status">
               <c:if test="${status.index == 0}">
                  <div class="card" style="width: 18rem;">
                     <c:if test="${!empty news.news_photo && fn:contains(news.news_photo, ',')}">
                            <c:set var="photoList" value="${fn:split(news.news_photo, ',')}" />
                            <c:set var="firstPhoto" value="${photoList[0]}" />
                            <a href="${pageContext.request.contextPath}/news/detail.do?news_num=${news.news_num}">
                            <img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="card-img-top" style="width:260px; height:200px;">
                            </a>
                        </c:if>
                        <c:if test="${!empty news.news_photo && !fn:contains(news.news_photo, ',')}">
                            <c:set var="firstPhoto" value="${news.news_photo}" />
                            <a href="${pageContext.request.contextPath}/news/detail.do?news_num=${news.news_num}">
                            <img src="${pageContext.request.contextPath}/upload/${news.news_photo}" class="card-img-top" style="width:286px; height:200px;">
                            </a>
                        </c:if>
                         <!-- 사진이 없는경우 -->
						<c:if test="${empty news.news_photo}">
                        	<a href="${pageContext.request.contextPath}/news/detail.do?news_num=${news.news_num}" class="d-flex justify-content-center align-items-center" style="height: 200px;">
                        	<i class="bi bi-newspaper" style="font-size: 5rem;"></i>
                        	</a>
                        </c:if>
                     <div class="card-body">
                        <h5 class="card-title text-truncate">
                        <a href="${pageContext.request.contextPath}/board/detail.do?board_num=${news.news_num}" class="text-decoration-none text-dark">
                        ${news.news_title}</a></h5>
                        <span class="card-text" style="max-width:500px;">
                        ${news.news_content}</span>
                     </div>
                     <ul class="list-group list-group-flush">
                        <c:forEach var="news" items="${NewsList}" varStatus="status">
                           <c:if test="${status.index > 0}">
                              <li class="list-group-item text-truncate">
                              <a href="${pageContext.request.contextPath}/news/detail.do?news_num=${news.news_num}" class="text-decoration-none text-dark">
                              ${news.news_title}
                              </a>
                              </li>
                           </c:if>
                        </c:forEach>
                     </ul>
                  </div>
               </c:if>
            </c:forEach>
			</div>
			<!-- 뉴스 끝 -->
			<!-- 자유게시판 시작 -->
         <div class="col">
         <a href="${pageContext.request.contextPath}/board/list.do" class="text-decoration-none text-dark">
            <strong class="fs-3">자유게시판</strong></a>
            <c:forEach var="board" items="${BoardList}" varStatus="status">
               <c:if test="${status.index == 0}">
                  <div class="card" style="width: 18rem;">
                     <c:if test="${!empty board.board_photo && fn:contains(board.board_photo, ',')}">
                            <c:set var="photoList" value="${fn:split(board.board_photo, ',')}" />
                            <c:set var="firstPhoto" value="${photoList[0]}" />
                            <a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}">
                            <img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="card-img-top" style="width:286px; height:200px;">
                            </a>
                        </c:if>
                        <c:if test="${!empty board.board_photo && !fn:contains(board.board_photo, ',')}">
                            <c:set var="firstPhoto" value="${board.board_photo}" />
                            <a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}">
                            <img src="${pageContext.request.contextPath}/upload/${board.board_photo}" class="card-img-top" style="width:286px; height:200px;">
                            </a>
                        </c:if>
                         <!-- 사진이 없는경우 -->
						<c:if test="${empty board.board_photo}">
                        	<a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}" class="d-flex justify-content-center align-items-center" style="height: 200px;">
                        	<i class="bi bi-people-fill" style="font-size: 5rem;"></i>
                        	</a>
                        </c:if>
                     <div class="card-body">
                        <h5 class="card-title text-truncate">
                        <a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}" class="text-decoration-none text-dark">
                        ${board.board_title}</a></h5>
                        <span class="card-text" style="max-width:500px;">
                        ${board.board_content}</span>
                     </div>
                     <ul class="list-group list-group-flush">
                        <c:forEach var="board" items="${BoardList}" varStatus="status">
                           <c:if test="${status.index > 0}">
                              <li class="list-group-item text-truncate">
                              <a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}" class="text-decoration-none text-dark">
                              ${board.board_title}
                              </a>
                              </li>
                           </c:if>
                        </c:forEach>
                     </ul>
                  </div>
               </c:if>
            </c:forEach>
         </div>
         <!-- 자유게시판 끝 -->
		</div>
		</div>
		</section>
		
		<!-- 구매 이용후기 시작 -->
	<section id="b_re">
    <div class="container p-4 position-relative">
    <a href="${pageContext.request.contextPath}/b_re/list.do" class="text-decoration-none text-dark">
        <strong class="fs-3">구매 이용후기</strong></a>
        <div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <c:forEach var="b_re" items="${list}" varStatus="status">
                    <c:if test="${status.index % 3 == 0}"> <!-- 3개의 카드마다 새로운 슬라이드 시작 -->
                        <div class="carousel-item <c:if test='${status.index == 0}'>active</c:if>">
                            <div class="d-flex justify-content-center">
                    </c:if>
                    
                    <div class="card text-center mb-3 mx-2" style="flex: 1 0 30%;">
                        <c:if test="${fn:contains(b_re.car_photo, ',')}">
                            <c:set var="photoList" value="${fn:split(b_re.car_photo, ',')}" />
                            <c:set var="firstPhoto" value="${photoList[0]}" />
                            <a href="${pageContext.request.contextPath}/b_re/detail.do?b_re_num=${b_re.b_re_num}">
                            <img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="card-img-top" style="width:397.81px; height:200px;">
                            </a>
                        </c:if>
                        <c:if test="${!fn:contains(b_re.car_photo, ',')}">
                            <c:set var="firstPhoto" value="${b_re.car_photo}" />
                            <a href="${pageContext.request.contextPath}/b_re/detail.do?b_re_num=${b_re.b_re_num}">
                            <img src="${pageContext.request.contextPath}/upload/${b_re.car_photo}" class="card-img-top" style="width:397.81px; height:200px;">
                            </a>
                        </c:if>
                        <div class="card-body">
                            <h5 class="card-title text-truncate">
                            <a href="${pageContext.request.contextPath}/b_re/detail.do?b_re_num=${b_re.b_re_num}" class="text-decoration-none text-dark">${b_re.car_name}</a></h5>
                            <p class="card-title text-truncate">
                            <a href="${pageContext.request.contextPath}/b_re/detail.do?b_re_num=${b_re.b_re_num}" class="text-decoration-none text-dark">${b_re.b_re_title}</a></p>
                            <p class="card-text">
                             <a href="${pageContext.request.contextPath}/b_re/detail.do?b_re_num=${b_re.b_re_num}" class="btn btn-warning text-white">구매후기</a>
                             <p>
                            <p class="card-text">
                                <small class="text-body-secondary">${b_re.b_re_reg}</small>
                            </p>
                        </div>
                    </div>

                    <c:if test="${status.index % 3 == 2 || status.last}"> <!-- 슬라이드 종료 조건 -->
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <button class="carousel-control-prev position-absolute top-50 translate-middle-y" type="button" data-bs-target="#carouselExample" data-bs-slide="prev" style="left: -10%;">
                <span class="carousel-control-prev-icon" aria-hidden="true" style="filter: invert(1);"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next position-absolute top-50 translate-middle-y" type="button" data-bs-target="#carouselExample" data-bs-slide="next" style="right: -10%;">
                <span class="carousel-control-next-icon" aria-hidden="true" style="filter: invert(1);"></span>
                <span class="visually-hidden">Next</span>
            </button>
        
    
</section>
</div>
	<!-- 구매 이용후기 끝-->
    <!-- 판매 이용후기 시작 -->
		<section id="s_re1">
    <div class="container pb-1">
        <a href="${pageContext.request.contextPath}/s_re/list.do" class="text-decoration-none text-dark">
            <strong class="fs-3">판매이용후기</strong>
            <span class="float-end">더보기<i class="bi bi-chevron-right"></i></span>
        </a>
        <div class="table-responsive rounded-4 border border-secondary-subtle">
            <table class="table table-borderless mb-0">
                <tbody>
                    <c:forEach var="s_re" items="${S_relist}" varStatus="loop">
                        <c:if test="${loop.index == 0}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">
                                        <strong>${s_re.s_re_title}</strong>
                                    </a>
                                </td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">
                                        ${s_re.s_re_reg}
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark ms-5">
                                        ${s_re.sell_cname} / ${s_re.sell_maker}
                                    </a>
                                </td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark me-5">
                                        ${s_re.mem_id}
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-center m-5 p-5" >
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark ms-5 card-text">
                                        ${s_re.s_re_content}
                                    </a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <div class="container pb-1">
        <div class="table-responsive rounded-4 border border-secondary-subtle">
            <table class="table table-borderless mb-0">
                <tbody>
                    <c:forEach var="s_re" items="${S_relist}" varStatus="loop">
                        <c:if test="${loop.index == 1}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">
                                        <strong>${s_re.s_re_title}</strong>
                                    </a>
                                </td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">
                                        ${s_re.s_re_reg}
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark ms-5">
                                        ${s_re.sell_cname} / ${s_re.sell_maker}
                                    </a>
                                </td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark me-5">
                                        ${s_re.mem_id}
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-center m-5 p-5" >
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark ms-5 card-text">
                                        ${s_re.s_re_content}
                                    </a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <div class="container pb-5">
        <div class="table-responsive rounded-4 border border-secondary-subtle">
            <table class="table table-borderless mb-0">
                <tbody>
                    <c:forEach var="s_re" items="${S_relist}" varStatus="loop">
                        <c:if test="${loop.index == 2}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">
                                        <strong>${s_re.s_re_title}</strong>
                                    </a>
                                </td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">
                                        ${s_re.s_re_reg}
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark ms-5">
                                        ${s_re.sell_cname} / ${s_re.sell_maker}
                                    </a>
                                </td>
                                <td class="text-end">
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark me-5">
                                        ${s_re.mem_id}
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-center m-5 p-5" >
                                    <a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark ms-5 card-text">
                                        ${s_re.s_re_content}
                                    </a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

		<%-- <section id="s_re2">
    <div class="container pb-5">
         <div class="table-responsive rounded-4 border border-secondary-subtle">
            <table class="table table-borderless mb-0">
            <thead>
                <tr>
                    <th scope="col">순서</th>
                    <th scope="col" class="col-2">차종</th>
					<th scope="col" class="col-6">제목</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s_re" items="${S_relist}" varStatus="loop">
                <c:if test="${loop.index ==1}">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.s_re_num}</a></td>
                        <td><a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.sell_cname} / ${s_re.sell_maker}</a></td>
                        <td><a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.s_re_title}</a></td>
                        <td><a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark float-end">${s_re.s_re_reg} </a></td>
                    </tr>
                </c:if>
                </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</section>

<section id="s_re3">
    <div class="container pb-5">
         <div class="table-responsive rounded-4 border border-secondary-subtle">
            <table class="table table-borderless mb-0">
            <thead>
                <tr>
                    <th scope="col">순서</th>
                    <th scope="col" class="col-2">차종</th>
					<th scope="col" class="col-6">제목</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s_re" items="${S_relist}" varStatus="loop">
                <c:if test="${loop.index ==2}">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.s_re_num}</a></td>
                        <td><a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.sell_cname} / ${s_re.sell_maker}</a></td>
                        <td><a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark">${s_re.s_re_title}</a></td>
                        <td><a href="${pageContext.request.contextPath}/s_re/detail.do?s_re_num=${s_re.s_re_num}" class="text-decoration-none text-dark float-end">${s_re.s_re_reg} </a></td>
                    </tr>
                </c:if>
                </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</section> --%>


	<!-- 판매 이용후기 끝 -->
    <!-- 메인 컨텐츠 끝 -->
    
    </div>
    <!-- 배경색 끝 -->
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보공유 게시판 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/info.report.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/info.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/info.reply.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
    <h2 class="pt-5 pb-3">정보공유 게시판</h2>
    <hr size="1" noshade="noshade" width="100%">
    <div class="mt-4 mb-4 d-flex justify-content-between align-items-center">
        <!-- 제목 -->
        <div class="text-start fs-3 mt-1">${info.info_board_title}</div>
        <!-- 작성일 -->
        <div class="text-end">
            <c:if test="${empty info.info_board_modify}">
                ${fn:substring(info.info_board_reg, 0, 10)}
            </c:if>
            <c:if test="${!empty info.info_board_modify}">
                ${fn:substring(info.info_board_modify, 0, 10)}
            </c:if>
        </div>
    </div>
    <hr size="1" noshade="noshade" width="100%">
    <div class="mt-1 mb-1 d-flex justify-content-between align-items-center">
        <!-- 작성자 정보 -->
        <div class="d-flex align-items-center">
            <c:if test="${!empty info.mem_photo}">
                <div class="me-3 d-inline">
                    <img src="${pageContext.request.contextPath}/upload/${info.mem_photo}" class="rounded-circle" width="30" height="30">
                </div>
            </c:if>
            <c:if test="${empty info.mem_photo}">
                <div class="me-4 d-inline">
                    <img src="${pageContext.request.contextPath}/images/face.png" class="rounded-circle" width="30" height="30">
                </div>
            </c:if>
            <div class="me-4 d-inline">${info.mem_id}</div>
        </div>
        <!-- 조회수 -->
        <div class="text-end">
            <div class="d-inline">조회수 : ${info.info_board_hit}</div>
            <div class="d-inline ms-3">추천수 : <span id="output_fcount"></span></div>
        </div>
    </div>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty info.info_board_photo}">
			<div class="text-center">
				<c:forEach var="photo" items="${fn:split(info.info_board_photo, ',')}">
					<div class="col-12 text-center mb-2">
						<img src="${pageContext.request.contextPath}/upload/${photo}" width="600" height="400">
					</div>
				</c:forEach>
			</div>
		</c:if>
		<p class="fs-5">${info.info_board_content}</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub list-unstyled text-end">
			<li class="text-end">
				<!-- 신고 -->
				<p id="report_status_text"></p>
				<button class="btn btn-outline-danger" onclick="" id="output_report" data-num="${info.info_board_num}" data-checkRedirect="${check_redirect}"><i id="reportIcon" class="bi bi-exclamation-octagon-fill">신고</i></button>
			</li>
			</ul>
			<div class="text-end">
			<div>
				<c:if test="${!empty info.info_board_modify}">
					최근 수정일 : ${fn:substring(info.info_board_modify,0,10)} / 
				</c:if>
				작성일 : ${fn:substring(info.info_board_reg,0,10)}
			</div>
			<div class="mt-3">
				<c:if test="${user_num == info.mem_num}">
					<input type="button" class="btn btn-warning text-white fw-bold" value="수정" onclick="location.href='updateForm.do?info_board_num=${info.info_board_num}'">
					<input type="button" class="btn btn-warning text-white fw-bold" value="삭제" id="delete_btn">
					<script type="text/javascript">
						const delete_btn = document.getElementById('delete_btn');
						delete_btn.onclick=function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								location.replace('delete.do?info_board_num=${info.info_board_num}');
							}
						};
					</script>
				</c:if>
			</div>
		</div>
			<!-- 좋아요 -->
			<p id="fav_status_text"></p>
			<button class="btn btn-outline-warning" onclick="toggleRecommendation()" id="output_fav" data-num="${info.info_board_num}" data-checkRedirect="${check_redirect}"><i id="favIcon" class="bi bi-hand-thumbs-up-fill">추천</i></button>
			
		<script>
			// 페이지 로드 시 실행되는 함수
			window.onload = function() {
				// 로컬 스토리지에서 추천 상태 가져오기
				var isRecommended = localStorage.getItem('isRecommended');

				if (isRecommended === 'true') {
					// 추천되었을 경우 버튼 스타일 변경
					setButtonRecommendedStyle();
				}
			}

			function toggleRecommendation() {
				var button = document.getElementById('output_fav');

				if (button.classList.contains('btn-warning')) {
					// 추천을 취소할 경우 로컬 스토리지에서 추천 상태 제거
					localStorage.removeItem('isRecommended');

					// 추천 취소에 따른 버튼 스타일 변경
					setButtonUnrecommendedStyle();
				} else {
					// 추천할 경우 로컬 스토리지에 추천 상태 저장
					localStorage.setItem('isRecommended', 'true');

					// 추천에 따른 버튼 스타일 변경
					setButtonRecommendedStyle();
				}
			}

			function setButtonRecommendedStyle() {
				var button = document.getElementById('output_fav');

				// 추천되었을 때 버튼 스타일 변경
				button.classList.remove('btn-outline-warning');
				button.classList.add('btn-warning');
			}

			function setButtonUnrecommendedStyle() {
				var button = document.getElementById('output_fav');

				// 추천 취소시 버튼 스타일 원래대로 변경
				button.classList.remove('btn-warning');
				button.classList.add('btn-outline-warning');
			}
		</script>
		<!-- 댓글 시작 -->
		<div id="reply_div" class="mt-3">
			<span class="fw-bold">댓글 달기</span>
			<form id="info_board_comm_form" class="mt-3">
				<input type="hidden" name="info_board_num" value="${info.info_board_num}" id="info_board_num">
				<div class="input-group">
					<textarea rows="3" cols="50" name="info_board_comm_content" id="info_board_comm_content" 
					class="form-control rep-content"<c:if test="${empty user_num}">disabled="disabled"</c:if>><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
					<c:if test="${!empty user_num}">
						<div id="info_board_comm_second" class="input-group-append">
							<input type="submit" value="전송" class="btn btn-warning text-white pt-5 pb-5">
						</div>
					</c:if>
				</div>
				<c:if test="${!empty user_num}">
					<div id="info_board_comm_first" class="mt-2">
					<span class="letter-count">300/300</span>
					</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output" class="mt-5"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글 끝 -->
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
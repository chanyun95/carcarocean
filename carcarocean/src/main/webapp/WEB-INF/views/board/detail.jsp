<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.report.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<h2 class="pt-5 pb-3">자유 게시판</h2>
		<hr size="1" noshade="noshade" width="100%">
		<div class="mt-4 mb-4 d-flex justify-content-between align-items-center">
			<!-- 제목 -->
			<div class="text-start fs-3 mt-1">${board.board_title}</div>
			<!-- 작성일 -->
			<div class="text-end">
				<c:if test="${empty board.board_modify}">
					${fn:substring(board.board_reg, 0, 10)}
				</c:if>
				<c:if test="${!empty board.board_modify}">
					${fn:substring(board.board_modify, 0, 10)}
				</c:if>
			</div>
		</div>
    	<hr size="1" noshade="noshade" width="100%">
    	<div class="mt-1 mb-1 d-flex justify-content-between align-items-center">
    	  <!-- 작성자 정보 -->
        <div class="d-flex align-items-center">
            <c:if test="${!empty board.mem_photo}">
                <div class="me-3 d-inline">
                    <img src="${pageContext.request.contextPath}/upload/${board.mem_photo}" class="rounded-circle" width="30" height="30">
                </div>
            </c:if>
            <c:if test="${empty board.mem_photo}">
                <div class="me-4 d-inline">
                    <img src="${pageContext.request.contextPath}/images/face.png" class="rounded-circle" width="30" height="30">
                </div>
            </c:if>
            <div class="me-4 d-inline">${board.mem_id}</div>
        </div>
        <!-- 조회수 -->
        <div class="text-end">
            <div class="d-inline">조회수 : ${board.board_hit}</div>
        </div>
    </div>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty board.board_photo}">
			<div class="text-center">
				<c:forEach var="photo" items="${fn:split(board.board_photo, ',')}">
					<div class="col-12 text-center mb-2">
						<img src="${pageContext.request.contextPath}/upload/${photo}" width="600" height="400">
					</div>
				</c:forEach>
			</div>
		</c:if>
		<p class="fs-5">${board.board_content}</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub list-unstyled text-end">
			<li class="text-end">
				<!-- 신고 -->
				<p id="report_status_text"></p>
				<%-- <img id="output_report" data-num="${board.board_num}" src="${pageContext.request.contextPath}/images/report01.png" width="50" data-checkRedirect="${check_redirect}"> --%>
				<button class="btn btn-outline-danger text-end" onclick="" id="output_report" data-num="${board.board_num}" data-checkRedirect="${check_redirect}"><i id="reportIcon" class="bi bi-exclamation-octagon-fill">신고</i></button>
			</li>
		</ul>
		<div class="text-end">
			<div>
				<c:if test="${!empty board.board_modify}">
					최근 수정일 : ${fn:substring(board.board_modify,0,10)}
				</c:if>
				/작성일 : ${fn:substring(board.board_reg,0,10)}
			</div>
			<div class="mt-3">
				<c:if test="${user_num == board.mem_num}">
					<input type="button" class="btn btn-warning text-white fw-bold" value="수정" onclick="location.href='updateForm.do?board_num=${board.board_num}'">
					<input type="button" class="btn btn-warning text-white fw-bold" value="삭제" id="delete_btn">
					<script type="text/javascript">
						const delete_btn = document.getElementById('delete_btn');
						delete_btn.onclick=function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								location.replace('delete.do?board_num=${board.board_num}');
							}
						};
					</script>
				</c:if>
			</div>
		</div>
		<!-- 댓글 시작 -->
		<div id="reply_div">
			<span class="fw-bold">댓글 달기</span>
			<form id="bor_comm_form" class="mt-3">
				<input type="hidden" name="board_num" value="${board.board_num}" id="board_num">
				<div class="input-group">
					<textarea rows="3" cols="50" name="bor_comm_content" id="bor_comm_content" 
					class="form-control rep-content"<c:if test="${empty user_num}">disabled="disabled"</c:if>><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
					<c:if test="${!empty user_num}">
						<div id="bor_comm_second" class="input-group-append">
							<input type="submit" value="전송" class="btn btn-warning text-white pt-5 pb-5">
						</div>
					</c:if>
				</div>
				<c:if test="${!empty user_num}">
					<div id="bor_comm_first" class="mt-2">
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
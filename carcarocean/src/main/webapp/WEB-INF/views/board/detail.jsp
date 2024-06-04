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
		<h1 class="text-center">글 상세</h1>
		<hr size="1" width="100%" noshade>
		<h2 class="text-center">${board.board_title}</h2>
		<div class="text-end">
			<span>${board.mem_id}</span>
			<c:if test="${!empty board.mem_photo}">
			<img src="${pageContext.request.contextPath}/upload/${board.mem_photo}" class="rounded-circle" width="60" height="60">
			</c:if>
			<c:if test="${empty board.mem_photo}">
			<img src="${pageContext.request.contextPath}/images/face.png" class="rounded-circle" width="60" height="60">
			</c:if>
			<p class="fw-bold">조회 : ${board.board_hit}</p>
		</div>
		<c:if test="${!empty board.board_photo}">
		<div class="text-center">
			<img src="${pageContext.request.contextPath}/upload/${board.board_photo}" class="rounded" width="200px" height="200px">
		</div>

		</c:if>

		<p class="fs-4">

			${board.board_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">

		<ul class="detail-sub">
			<li>
				<!-- 신고 -->
				<p id="report_status_text"></p>
				<%-- <img id="output_report" data-num="${board.board_num}" src="${pageContext.request.contextPath}/images/report01.png" width="50" data-checkRedirect="${check_redirect}"> --%>
				<button class="btn btn-outline-danger" onclick="" id="output_report" data-num="${board.board_num}" data-checkRedirect="${check_redirect}"><i id="reportIcon" class="bi bi-exclamation-octagon-fill">신고</i></button>
			</li>
		</ul>

		<div class="text-end">
			<div>
				<c:if test="${!empty board.board_modify}">
					최근 수정일 : ${fn:substring(board.board_modify,0,10)}
				</c:if>
				작성일 : ${fn:substring(board.board_reg,0,10)}
			</div>
			<div>
				<!-- 신고 -->
				<span id="report_status_text" class="fw-bold"></span>
				<i class="bi bi-emoji-dizzy-fill d-inlign" id="output_report" data-num="${board.board_num}" data-checkRedirect="${check_redirect}" style="font-size:50px;"></i>
			</div>
			<div>
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
		<div id="reply_div">
			<span class="fw-bold">댓글 달기</span>
			<form id="bor_comm_form">
				<input type="hidden" name="board_num" value="${board.board_num}" id="board_num">
				<textarea rows="3" cols="50" name="bor_comm_content" class="form-control"
					<c:if test="${empty user_num}">disabled="disabled"</c:if> id="bor_comm_content" class="rep-content">
					<c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if>
				</textarea>
				<c:if test="${!empty user_num}">
					<div id="bor_comm_first">
					<span class="letter-count">300/300</span>
					</div>
					<div id="bor_comm_second" class="align-right">
						<input type="submit" value="전송" class="btn btn-warning text-white">
					</div>
				</c:if>
			</form>
		</div>
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
</div>
</body>
</html>
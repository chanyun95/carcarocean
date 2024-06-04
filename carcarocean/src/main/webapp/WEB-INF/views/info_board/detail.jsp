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
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>${info.info_board_title}</h2>
		<ul class="detail.info">
			<li>
				<c:if test="${!empty info.mem_photo}">
				<img src="${pageContext.request.contextPath}/upload/${info.mem_photo}"
									width="40" height="40" class="my-photo">
				</c:if>
				<c:if test="${empty info.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.png"
									width="40" height="40" class="my-photo">
				</c:if>
			</li>
			<li>
				${info.mem_id}<br>
				조회 : ${info.info_board_hit}
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<div class="align-center">
		<c:if test="${!empty info.info_board_photo}">
			<div>
				<c:forEach var="photo" items="${fn:split(info.info_board_photo, ',')}">
					<img src="${pageContext.request.contextPath}/upload/${photo}">
				</c:forEach>
			</div>
		</c:if>
		</div>
		<p>
			${info.info_board_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<!-- 신고 -->
				<p id="report_status_text"></p>
				<button class="btn btn-outline-danger" onclick="" id="output_report" data-num="${info.info_board_num}" data-checkRedirect="${check_redirect}"><i id="reportIcon" class="bi bi-exclamation-octagon-fill">신고</i></button>
			</li>
			<li>
				<c:if test="${!empty info.info_board_modify}">
				최근 수정일 : ${info.info_board_modify}
				</c:if>
				작성일 : ${info.info_board_reg}
				<c:if test="${user_num == info.mem_num}">
				<input type="button" value="수정" onclick="location.href='updateForm.do?info_board_num=${info.info_board_num}'">
				<input type="button" value="삭제" id="delete_btn">
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
			</li>
			<li>
			<!-- 좋아요 -->
			<img id="output_fav" data-num="${info.info_board_num}"
			src="${pageContext.request.contextPath}/images/fav01.gif" width="50">
			좋아요
			<span id="output_fcount"></span>
			</li>
		</ul>
		<div id="reply_div">
			<span class="re-title">댓글 달기</span>
			<form id="info_board_comm_form">
				<input type="hidden" name="info_board_num" value="${info.info_board_num}" id="info_board_num">
				<textarea rows="3" cols="50" name="info_board_comm_content"
					<c:if test="${empty user_num}">disabled="disabled"</c:if>
					id="info_board_comm_content"
					class="rep-content"><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
						<c:if test="${!empty user_num}">
						<div id="info_board_comm_first">
						<span class="letter-count">300/300</span>
						</div>
						<div id="info_board_comm_second" class="align-right">
							<input type="submit" value="전송">
						</div>
						</c:if>
			</form>
		</div>
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
	</div>
</div>
</body>
</html>
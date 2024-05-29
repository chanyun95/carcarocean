<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.report.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>${board.board_title}</h2>
		<ul class="detail-info">
			<li>
				<c:if test="${!empty board.mem_photo}">
				<img src="${pageContext.request.contextPath}/upload/${board.mem_photo}"
									width="40" height="40" class="my-photo">
				</c:if>
				<c:if test="${empty board.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.png"
									width="40" height="40" class="my-photo">
				</c:if>
			</li>
			<li>
				${board.mem_id}<br>
				조회 : ${board.board_hit}
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty board.board_photo}">
		<div class="align-center">
		<img src="${pageContext.request.contextPath}/upload/${board.board_photo}" class="detail-img">
		</div>
		</c:if>
		<p>
			${board.board_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<!-- 신고 -->
				<img id="output_report" data-num="${board.board_num}" src="${pageContext.request.contextPath}/images/report01.png" width="50">
				신고
				<span id="output_reportcount"></span>
			</li>
			<li>
				<c:if test="${!empty board.board_modify}">
				최근 수정일 : ${board.board_modify}
				</c:if>
				작성일 : ${board.board_reg}
				<c:if test="${user_num == board.mem_num}">
				<input type="button" value="수정" onclick="location.href='updateForm.do?board_num=${board.board_num}'">
				<input type="button" value="삭제" id="delete_btn">
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
			</li>
		</ul>
		<div id="reply_div">
			<span class="re-title">댓글 달기</span>
			<form id="re_form">
				<input type="hidden" name="board_num" value="${board.board_num}" id="board_num">
				<textarea rows="3" cols="50" name="re_content" id="re_content" <c:if test="${empty user_num}">disabled="disabled"</c:if>
						class="rep-content"><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
						<c:if test="${!empty user_num}">
						<div id="re_first">
						<span class="letter-count">300/300</span>
						</div>
						<div id="re_second" class="align-right">
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
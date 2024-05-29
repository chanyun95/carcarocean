<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 글 상세 보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hjt.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/qa_comment.js"></script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="content-main">
			<h2>${qa.qa_title}</h2>
			<hr size="1" noshade="noshade" width="100%">
			<c:if test="${!empty qa.qa_photo}">
				<div class="align-center">
				<img src="${pageContext.request.contextPath}/upload/${qa.qa_photo}" class="detail-img">
				</div>
			</c:if>
			<p>
				${qa.qa_content}
			</p>
			<hr size="1" noshade="noshade" width="100%">
			<ul class="detail-sub">
				<li>
					<c:if test="${!empty qa.qa_modify}">
					최근 수정일: ${qa.qa_modify}
					</c:if>
					작성일: ${qa.qa_reg}
						<input type="button" value="목록" onclick="location.href='list.do'">
						<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정, 삭제 가능 --%>
						<c:if test="${user_num == qa.mem_num}">
						<input type="button" value="수정" onclick="location.href='updateForm.do?qa_num=${qa.qa_num}'">
						<input type="button" value="삭제" id="delete_btn">
						<script type="text/javascript">
							const delete_btn = document.getElementById('delete_btn');
							//이벤트 연결
							delete_btn.onclick = function(){
								let choice = confirm('삭제하시겠습니까?');
								if(choice){
									location.replace('delete.do?qa_num=${qa.qa_num}');
								}
							};
						</script>
					</c:if>
				</li>
			</ul>
			<!-- 답글 시작 -->
			<c:if test="${!empty user_num && user_auth == 9}">
			<div id="reply_div">
				<span class="re-title">답변 등록</span>
				<form id="re_form">
					<input type="hidden" name="qa_num" value="${qa.qa_num}" id="qa_num">
					<textarea rows="5" cols="70" name="qa_comm_content" id="re_content" class="rep-content"></textarea>
						<div id="re_first">
							<span class="letter-count">300/300</span>
						</div>
						<div id="re_second" class="align-right">
							<input type="submit" value="등록">
						</div>
				</form>
			</div>
			<!-- 댓글 목록 출력 시작 -->
			<div id="output"></div>
			<div class="paging-button" style="display: none;">
				<input type="button" value="다음글 보기">
			</div>
			<div id="loding" style="display: none;">
				<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
			</div>
			</c:if>
			<!-- 댓글 목록 출력 끝 -->
			<!-- 답글 끝 -->
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/b_re_comment.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<ul>
			<c:if test="${fn:contains(b_re.car_photo, ',')}">
				<c:set var="photoList" value="${fn:split(b_re.car_photo, ',')}" />
				<c:set var="firstPhoto" value="${photoList[0]}"/>
				<li>
					<img src="${pageContext.request.contextPath}/upload/${photoList[0]}" class="detail-img">
				</li>
			</c:if>
			<c:if test="${!fn:contains(b_re.car_photo, ',')}">
				<c:set var="firstPhoto" value="${b_re.car_photo}" />
				<li>
					<img src="${pageContext.request.contextPath}/upload/${b_re.car_photo}" class="detail-img">
				</li>
			</c:if>
			<h2>${b_re.b_re_title}</h2>
			<li>
				<c:if test="${!empty b_re.b_re_modify}">
					${b_re.b_re_modify}
				</c:if>
				<c:if test="${empty b_re.b_re_modify}">
					${b_re.b_re_reg}
				</c:if>
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/upload/${b_re.b_re_photo}" class="detail-img">
		</div>
		<ul>
			<h2>기본정보</h2>
			<li>주행거리 : ${b_re.car_mile} km</li>
			<li>차량번호 : ${b_re.car_cnumber}</li>
			<li>제조사 : ${b_re.car_maker}</li>
			<li>차명 : ${b_re.car_name}</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<p>
			<h2>고객 후기</h2>
			${b_re.b_re_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<img src="${pageContext.request.contextPath}/upload/${b_re.checker_photo}"
						width="40" height="40" class="my-photo">
			</li>
			<li>${b_re.checker_name} 검수자</li>
			<li>
				<!-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정 가능 -->
				<c:if test="${user_num == b_re.mem_num}">
				<input type="button" value="수정"
				  onclick="location.href='modifyForm.do?b_re_num=${b_re.b_re_num}'">
				</c:if>
			</li>
		</ul>
		<!-- 댓글 시작 -->
		<div id="reply_div">
			<span class="re-title">댓글 달기</span>
			<form id="b_re_comm_form">
				<input type="hidden" name="b_re_num" value="${b_re.b_re_num}" id="b_re_num">
				<textarea rows="3" cols="50" name="b_re_comm_content"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>	
				  id="b_re_comm_content" class="rep-content"><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>    
				<c:if test="${!empty user_num}">
				<div id="b_re_comm_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="b_re_comm_second">
					<input type="submit" value="전송">
				</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글 끝 -->
	</div>
</div>
</body>
</html>








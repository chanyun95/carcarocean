<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div>
			<h1>공지사항</h1>
			<br>
			<h4>카카오션의 새로운 소식을 전달받으실 수 있습니다.</h4>
		</div>
		<hr size="1" noshade="noshade" width="80%">
		<h3>${notice.notice_title}</h3>
		<h4>관리자 | ${notice.notice_reg} | ${notice.notice_hit}</h4>
		<hr size="1" noshade="noshade" width="80%">
		<c:if test="${!empty notice.notice_photo}">
			<div>
			<c:forEach var="photo" items="${fn:split(notice_photo,',')}">
				<img src="${pageContext.request.contextPath}/upload/${notice_photo}">
			</c:forEach>
			</div>
		</c:if>
		<p>
			${notice.notice_content}
		</p>
		<input type="button" value="목록" onclick="location.href='list.do'">
		<c:if test="${user_auth == 9 }">
			<input type="button" value="수정" onclick="location.href='modifyForm.do?notice_num=${notice.notice_num}'">
		</c:if>
		
	</div>
</body>
</html>
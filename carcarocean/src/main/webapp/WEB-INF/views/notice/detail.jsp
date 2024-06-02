<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div>
			<h1>공지사항</h1>
			<br>
			<div class="text-center fs-8 text-secondary">카카오션의 새로운 소식을 전달받으실 수 있습니다.</div>
		</div>
		<hr size="1" noshade="noshade" width="100%">
		<div class="row justify-content-center">
			<div class="col fs-3">
				<b>${notice.notice_title}</b>
			</div>
			<div class="col text-end mt-2">	
				관리자 | 
				<c:if test="${empty notice.notice_modify}">
					${notice.notice_reg} 
				</c:if>
				<c:if test="${!empty notice.notice_modify}">
					${notice.notice_modify}
				</c:if>
				| ${notice.notice_hit}
			</div>
		</div>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty notice.notice_photo}">
			<div>
			<c:forEach var="photoList" items="${fn:split(notice.notice_photo,',')}">
				<img src="${pageContext.request.contextPath}/upload/${photoList}">
			</c:forEach>
			</div>
		</c:if>
		<p class="fs-5">
			${notice.notice_content}
		</p>
		<div class="text-center mb-5">
			<input type="button" class="btn btn-warning ms-2" value="목록" onclick="location.href='list.do'">
			<c:if test="${user_auth == 9 }">
				<input type="button" class="btn btn-warning ms-2" value="수정" onclick="location.href='modifyForm.do?notice_num=${notice.notice_num}'">
			</c:if>
		</div>
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
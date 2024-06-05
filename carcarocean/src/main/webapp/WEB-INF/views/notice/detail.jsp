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
			<h2 class="pt-5 pb-3">공지사항</h2>
			<br>
			<div class="text-center fs-8 text-secondary">카카오션의 새로운 소식을 전달받으실 수 있습니다.</div>
		</div>
		<hr size="1" noshade="noshade" width="100%">
		<div class="mb-4">
			<div class="fs-3">
				<b>${notice.notice_title}</b>
			</div>
			<div class="text-end mt-2">
				<div class="me-3 d-inline">관리자</div>
				<div class="me-3 d-inline">
					<c:if test="${empty notice.notice_modify}">
						${fn:substring(notice.notice_reg, 0, 10)} 
					</c:if>
					<c:if test="${!empty notice.notice_modify}">
						${fn:substring(notice.notice_modify, 0, 10)}
					</c:if>
				</div>
				<div class="d-inline">${notice.notice_hit}</div>
			</div>
		</div>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty notice.notice_photo}">
			<div class="row">
			<c:forEach var="photoList" items="${fn:split(notice.notice_photo,',')}">
				<div class="col-12 text-center mb-2">
					<img src="${pageContext.request.contextPath}/upload/${photoList}" width="600" height="400">
				</div>
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
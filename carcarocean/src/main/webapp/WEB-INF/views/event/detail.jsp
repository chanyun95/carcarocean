<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 글 상세 보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="content-main">
			<h2>${event.event_title}</h2>
			<h5>관리자 | 
				<c:if test="${!empty event.event_modify}">
					${event.event_modify}
				</c:if>
				<c:if test="${empty event.event_modify}">
					${event.event_reg}
				</c:if>
			</h5>
			<hr size="1" noshade="noshade" width="100%">
			<c:if test="${!empty event.event_photo}">
				<div class="align-center">
				<c:forEach var="photo" items="${fn:split(event.event_photo,',')}">
				<img src="${pageContext.request.contextPath}/upload/${photo}" class="detail-img">
				</c:forEach>
				</div>
			</c:if>
			<p>
				${event.event_content}
			</p>
			<hr size="1" noshade="noshade" width="100%">
			<ul class="detail-sub">
				<li>
						<input type="button" value="목록" onclick="location.href='list.do'">
						<c:if test="${!empty user_num && user_auth == 9}">
						<input type="button" value="수정" onclick="location.href='updateForm.do?event_num=${event.event_num}'">
						<input type="button" value="삭제" id="delete_btn">
						<script type="text/javascript">
							const delete_btn = document.getElementById('delete_btn');
							//이벤트 연결
							delete_btn.onclick = function(){
								let choice = confirm('삭제하시겠습니까?');
								if(choice){
									location.replace('delete.do?event_num=${event.event_num}');
								}
							};
						</script>
						</c:if>
				</li>
			</ul>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>
</body>
</html>
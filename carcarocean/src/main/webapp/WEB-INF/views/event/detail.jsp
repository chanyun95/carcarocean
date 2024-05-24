<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 글 상세 보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hjt.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="content-main">
			<h2>${event.event_title}</h2>
			<hr size="1" noshade="noshade" width="100%">
			<c:if test="${!empty event.event_photo}">
				<div class="align-center">
				<img src="${pageContext.request.contextPath}/upload/${event.event_photo}" class="detail-img">
				</div>
			</c:if>
			<p>
				${event.event_content}
			</p>
			<hr size="1" noshade="noshade" width="100%">
			<ul class="detail-sub">
				<li>
					<c:if test="${!empty event.event_modify}">
					최근 수정일: ${event.event_modify}
					</c:if>
					작성일: ${event.event_reg}
						<input type="button" value="목록" onclick="location.href='list.do'">
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
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
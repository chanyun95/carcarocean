<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자동차 뉴스 게시판 상세 보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="container">
			<h2>${news.news_title}</h2>
			<div class="col text-end mt-2 pr-3">관리자 | 
				<c:if test="${!empty news.news_modify}">
					${fn:substring(news.news_modify,0,10)}
				</c:if>
				<c:if test="${empty news.news_modify}">
					${fn:substring(news.news_reg,0,10)}
				</c:if>
				| ${news.news_hit}
			</div>
			<hr size="1" noshade="noshade" width="100%">
			<c:if test="${!empty news.news_photo}">
				<div class="text-center">
				<c:forEach var="photo" items="${fn:split(news.news_photo,',')}">
				<img src="${pageContext.request.contextPath}/upload/${photo}" class="detail-img">
				</c:forEach>
				</div>
			</c:if>
			<p>
				${news.news_content}
			</p>
			<hr size="1" noshade="noshade" width="100%">
			<div class="text-end mb-5">
			<ul class="detail-sub list-unstyled">
				<li>
						<input type="button" class="btn btn-warning" value="목록" onclick="location.href='list.do'">
						<c:if test="${!empty user_num && user_auth == 9}">
						<input type="button" class="btn btn-warning" value="수정" onclick="location.href='updateForm.do?news_num=${news.news_num}'">
						<input type="button" class="btn btn-warning" value="삭제" id="delete_btn">
						<script type="text/javascript">
							const delete_btn = document.getElementById('delete_btn');
							//이벤트 연결
							delete_btn.onclick = function(){
								let choice = confirm('삭제하시겠습니까?');
								if(choice){
									location.replace('delete.do?news_num=${news.news_num}');
								}
							};
						</script>
						</c:if>
				</li>
			</ul>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>
</body>
</html>
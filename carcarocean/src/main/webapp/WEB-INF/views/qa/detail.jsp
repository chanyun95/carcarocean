<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 글 상세 보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/qa_comment.js"></script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="container">
			<h2>${qa.qa_title}</h2>
			<div class="col text-end mt-2 pr-3"> 
				<c:if test="${!empty qa.mem_photo}">
				<img src="${pageContext.request.contextPath}/upload/${qa.mem_photo}" width="50" height="50" class="my-photo">
				</c:if>
				<c:if test="${empty qa.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="50" height="50" class="my-photo">
				</c:if>
					${qa.mem_id} |
				<c:if test="${!empty qa.qa_modify}">
					${fn:substring(qa.qa_modify,0,10)}
				</c:if>
				<c:if test="${empty qa.qa_modify}">
					${fn:substring(qa.qa_reg,0,10)}
				</c:if>
			</div>
			<hr size="1" noshade="noshade" width="100%">
			<c:if test="${!empty qa.qa_photo}">
				<div class="text-center mb-5">
				<c:forEach var="photo" items="${fn:split(qa.qa_photo,',')}">
				<img src="${pageContext.request.contextPath}/upload/${photo}" class="detail-img" width="600px" height="400px"><br>
				</c:forEach>
				</div>
			</c:if>
			<p>
				${qa.qa_content} 
			</p>
			<hr size="1" noshade="noshade" width="100%">
			<div class="text-center mb-1">
			<ul class="detail-sub list-unstyled">
				<li>
						<input type="button" class="btn btn-warning" value="목록" onclick="location.href='list.do'">	
						<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정, 삭제 가능 --%>
						<c:if test="${user_num == qa.mem_num}">
						<input type="button" class="btn btn-warning" value="수정" onclick="location.href='updateForm.do?qa_num=${qa.qa_num}'">
						<input type="button" class="btn btn-warning" value="삭제" id="delete_btn" >
						<script type="text/javascript">
							const delete_btn = document.getElementById('delete_btn');
							//이벤트 연결
							delete_btn.onclick = function(){
								let choice = confirm('정말 삭제하시겠습니까?');
								if(choice){
									location.replace('delete.do?qa_num=${qa.qa_num}');
								}
							};
						</script>
					</c:if>
				</li>
			</ul>
			</div>
			<!-- 댓글 목록 출력 시작 -->
			<h4>관리자 답변</h4>
			<c:if test="${count == 0}">
				<div class="result-display" id="answer">
					아직 답변이 등록되지 않았습니다.
				</div>
			</c:if>
			<div id="output">
			<!-- ajax로 답변 정보가 추가되는 공간 -->
			</div>
			<div class="paging-button" style="display: none;">
				<input type="button" value="다음글 보기">
			</div>
			<div id="loding" style="display: none;">
				<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
			</div>			
			<!-- 댓글 목록 출력 끝 -->	
			<!-- 답글 시작 -->		
			<div id="reply_div" class="text-center mt-5 mb-5">
				<form id="re_form">
					<input type="hidden" name="qa_num" value="${qa.qa_num}" id="qa_num">
					<c:if test="${user_auth == 9}">
					<textarea rows="7" cols="150" placeholder="관리자 답변" name="qa_comm_content" <c:if test="${user_auth != 9}">disabled="disabled"</c:if> id="re_content" 
					class="rep-content"></textarea>		
						<div id="re_first" class="text-end" style="margin-right: 40px;">
							<span class="letter-count">300/300</span>
						</div>
						<div id="re_second" class="text-end" style="margin-right: 40px;">
							<input type="submit" value="전송" class="btn btn-warning">
						</div>
					</c:if>
				</form>	
			</div>			
			<!-- 답글 끝 -->	
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>
</body>
</html>
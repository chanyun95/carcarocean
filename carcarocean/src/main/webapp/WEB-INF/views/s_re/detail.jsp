<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/s_re_comment.js"></script>
</head>
<body>
<div class="page-main">
	<div class="content-main">
		<h3>${s_re.s_re_title}</h3>
			<li>
				<c:if test="${!empty s_re.s_re_modify}">
					최근수정일 : ${s_re.s_re_modify}
				</c:if>
					작성일 : ${s_re.s_re_reg}
			</li>
		
		<hr size="1" noshade="noshade" width="100%">
		<h4>기본정보</h4><br>
			주행거리 : ${sellvo.sell_mile} km <br>
			차량번호 : ${sellvo.sell_cnumber}<br> 
			제조사 : 	${sellvo.sell_maker} <br>
			차명 :	${sellvo.sell_cname} <br>
			방문진단 요청지역 : ${sellvo.sell_place1} ${sellvo.sell_place2}<br>
			<c:if test="${sellvo.sell_check == 0}">
			검수완료여부 : 검수 미완료<br>
			</c:if>
			<c:if test="${sellvo.sell_check == 1}">
			검수완료여부 : 검수 진행중<br>
			</c:if>
			<c:if test="${sellvo.sell_check == 2}">
			검수완료여부 : 검수 완료<br>
			</c:if>
			
			 
		<hr size="1" noshade="noshade" width="100%">
		<h4>고객 후기</h4><br>
		<p>
			${s_re.s_re_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정, 삭제 가능 --%>
				<c:if test="${user_num == s_re.mem_num}">
					<input type="button" value="수정" onclick="location.href='updateForm.do?s_re_num=${s_re.s_re_num}'">
					<input type="button" value="삭제" id="delete_btn">
					<script type="text/javascript">
						const delete_btn = document.getElementById('delete_btn')
						//이벤트 연결
						delete_btn.onclick=function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								location.replace('delete.do?s_re_num=${s_re.s_re_num}');
							}
						};
					</script>
				
				</c:if>
			</li>
		</ul>
		<!-- 댓글 시작 -->
		<div id = "reply_div">
			<span class="re-title">댓글 달기</span>
			<form id="s_re_form">
				<input type="hidden" name="s_re_num" value="${s_re.s_re_num}" id="s_re_num">
				<!-- disabled는 댓글을 달지 못하도록 비활설화 시키는 역할 -->
				<textarea rows="3" cols="50" name="s_re_comm_content" 
				<c:if test="${empty user_num}">disabled="disabled"</c:if> 
				id="s_re_comm_content" class="s_re_comm_content">
				<c:if test="${empty user_num}">로그인해야 작성할 수 있습니다</c:if>
				</textarea>
				<c:if test="${!empty user_num}">
				<div id="s_re_first">
					<span class="s_re_letter-count">300/300</span><!-- 글자수 제한  -->
				</div>
				<div id="re_second" class="align-right">
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
			<div id="loading" style="display:none">
				<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
			</div>
			<!-- 댓글 목록 출력 끝 -->
		</div>
		<!-- 댓글 끝 -->
	</div>
</div>
</body>
</html>
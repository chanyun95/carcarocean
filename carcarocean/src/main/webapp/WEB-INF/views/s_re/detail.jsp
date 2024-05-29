<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
</head>
<body>
<div class="page-main">
	<div class="content-main">
		<h3>${s_re.s_re_title}</h3>
		
		<hr size="1" noshade="noshade" width="100%">
		<h4>고객 후기</h4><br>
		<p>
			${s_re.s_re_content}
		</p>
			 ${sellvo.sell_cname} 
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<c:if test="${!empty s_re.s_re_modify}">
					최근수정일 : ${s_re.s_re_modify}
				</c:if>
					작성일 : ${s_re.s_re_reg}
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
	</div>
</div>
</body>
</html>
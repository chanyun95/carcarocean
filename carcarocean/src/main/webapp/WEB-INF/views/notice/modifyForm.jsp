<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 수정(관리자)</title>
<script type="text/javascript">
	window.onload=function(){
		const myForm = document.getElementById('modify_form');
		myForm.onsubmit=function(){
			const title = document.getElementById('notice_title');
			if(title.value.trim()==''){
				alert('제목을 입력하세요!');
				title.value='';
				title.focus();
				return false;
			}
			const content = document.getElementById('notice_content');
			if(content.value.trim()==''){
				alert('내용을 입력하세요!');
				content.value='';
				content.focus();
				return false;
			}
		};
	};
</script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div>
			<h2>공지사항 글 수정(관리자)</h2>
			<form id="modify_form" action="modify.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="notice_num" value="${notice.notice_num}">
				<ul>
					<li>
						<label for="notice_title">제목(필수)</label>
						<br>
						<input type="text" name="notice_title" id="notice_title" value="${notice.notice_title}" maxlength="50">
					</li>
					<li>
						<label for="notice_content">내용(필수)</label>
						<br>
						<textarea rows="20" cols="70" name="notice_content" id="notice_content">${notice.notice_content}</textarea>
					</li>
					<li>
						<label for="notice_photo">파일첨부(선택)</label>
						<br>
						<input type="file" name="notice_photo" accept="image/gif,image/png,image/jpeg" multiple>
						<c:if test="${!empty notice.notice_photo}">
							<div id="file_detail">
								<img src="${pageContext.request.contextPath}/upload/${notice.notice_photo}" width="100">
								<br>
								<input type="button" value="파일 삭제" id="file_del">
							</div>
							<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
							<script type="text/javascript">
								$(function(){
									$('#file_del').click(function(){
										let choice = confirm('삭제하시겠습니까?');
										if(choice){
											//서버와 통신
											$.ajax({
												url:'deleteFile.do',
												type:'post',
												data:{notice_num:${notice.notice_num}},
												dataType:'json',
												success:function(param){
													if(param.result == 'success'){
														$('#file_detail').hide();
													}else{
														alert('파일 삭제 오류 발생');
													}
												},
												error:function(){
													alert('네트워크 오류 발생');
												}
											});
										}
									});
								});
							</script>
						</c:if></li>
					<li><label for="notice_impt">중요</label> <input type="checkbox" name="notice_impt" id="notice_impt" value="1"></li>
				</ul>
				<div>
					<input type="submit" value="글 쓰기">
					<input type="button" value="목록" onclick="location.href='list.do'">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
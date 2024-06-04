<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script type="text/javascript">
window.onload = function(){
	const myForm = document.getElementById('update_form');
	
	myForm.onsubmit = function(){
		const info_board_title = document.getElementById('info_board_title');
		if(info_board_title.value.trim() == ''){
			alert('제목을 입력하세요.');
			info_board_title.value='';
			info_board_title.focus();
			return false;
		}
		const info_board_content = document.getElementById('info_board_content');
		if(info_board_content.value.trim() == ''){
			alert('내용을 입력하세요.');
			info_board_content.value='';
			info_board_content.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>글 수정</h2>
		<form id="update_form" action="update.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="info_board_num" value="${info.info_board_num}">
			<ul>
				<li>
					<label for="info_board_title">제목</label>
					<input type="text" name="info_board_title" id="info_board_title" value="${info.info_board_title}" maxlength="50">
				</li>
				<li>
				<label for="info_board_content">내용</label>
				<textarea rows="5" cols="40" name="info_board_content" id="info_board_content">${info.info_board_content}</textarea>
				</li>
				<li>
					<label for="info_board_photo">이미지</label>
					<input type="file" name="info_board_photo" id="info_board_photo" accept="image/gif, image/png, image/jpeg" multiple>
					<c:if test="${!empty info.info_board_photo}">
					<div id="file_detail">
					<img src="${pageContext.request.contextPath}/upload/${info.info_board_photo}" width="100">
					<br>
					<input type="button" value="파일 삭제" id="file_del">
					</div>
					<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
					<script type="text/javascript">
						$(function(){
							$('#file_del').click(function(){
								let choice = confirm('삭제하시겠습니까?');
								if(choice){
									$.ajax({
										url : 'deleteFile.do',
										type : 'post',
										data : {info_board_num : ${info.info_board_num}},
										dataType : 'json',
										success : function(param){
											if(param.result == 'logout'){
												alert('로그인 후 사용해주세요.');
											}else if(param.result == 'success'){
												$('#file_detail').hide();
											}else if(param.result == 'wrongAccess'){
												alert('잘못된 접근입니다.');
											}else{
												alert('파일 삭제 오류');
											}
										},
										error : function(){
											alert('네트워크 오류 발생');
										}
									});
								}
							});
						});
					</script>
					</c:if>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 게시판 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hjt.css" type="text/css">
<script type="text/javascript">
window.onload = function(){
	const myForm = document.getElementById('write_form');
	//이벤트 연결
	myForm.onsubmit = function(){
		const title = document.getElementById('event_title');
		if(title.value.trim()==''){
			alert('제목을 입력하세요');
			title.value = '';
			title.focus();
			return false;
		}
		const content = document.getElementById('event_content');
		if(content.value.trim()==''){
			alert('내용을 입력하세요');
			content.value = '';
			content.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
		<div class="content-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<h2>이벤트 게시판 글쓰기</h2>
				<form id="write_form" action="write.do" method="post" enctype="multipart/form-data">
					<ul>
						<li>
							<label for="event_title">제목</label><br>
							<input type="text" name="event_title" id="event_title" maxlength="50">
						</li>
						<li>
							<label for="event_content">내용</label><br>
							<textarea rows="5" cols="40" name="event_content" id="event_content"></textarea>
						</li>
						<li>
							<label for="event_photo">파일 첨부</label><br>
							<input type="file" name="event_photo" id="event_photo" accept="image/gif, image/png, image/jpeg">
						</li>
					</ul>
					<div class="align-center">
						<input type="submit" value="등록">
						<input type="button" value="목록" onclick="location.href='list.do'">
					</div>
				</form>
		</div>
	</div>
</body>
</html>
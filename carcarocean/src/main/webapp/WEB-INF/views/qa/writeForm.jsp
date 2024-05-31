<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 문의하기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
window.onload = function(){
	const myForm = document.getElementById('write_form');
	//이벤트 연결
	myForm.onsubmit = function(){
		const title = document.getElementById('qa_title');
		if(title.value.trim()==''){
			alert('제목을 입력하세요');
			title.value = '';
			title.focus();
			return false;
		}
		const content = document.getElementById('qa_content');
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
			<h2>고객 문의하기</h2>
				<form id="write_form" action="write.do" method="post" enctype="multipart/form-data">
					<ul>
						<li>
							<label for="qa_title">제목</label><br>
							<input type="text" name="qa_title" id="qa_title" maxlength="50">
						</li>
						<li>
							<label for="qa_content">내용</label><br>
							<textarea rows="5" cols="40" name="qa_content" id="qa_content"></textarea>
						</li>
						<li>
							<label for="qa_photo">파일 첨부</label><br>
							<input type="file" name="qa_photo" id="qa_photo" accept="image/gif, image/png, image/jpeg" multiple>
						</li>
					</ul>
					<div class="align-center">
						<input type="submit" value="문의하기">
						<input type="button" value="목록" onclick="location.href='list.do'">
					</div>
				</form>
		</div>
	</div>
</body>
</html>
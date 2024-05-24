<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기(관리자)</title>
<script type="text/javascript">
	window.onload=function(){
		const myForm = document.getElementById('write_form');
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
		/*
		const impt = document.getElementById('notice_impt');
		 if(impt.value=='on'){
			impt.value=1;
		}else if(impt.value==''){
			impt.value=0;
		}
	    let check = false;
	    if (!impt.checked) {
	        impt.value = 0;
	    } else {
	        impt.value = 1;
	    }
		*/
	};
</script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div>
			<h2>공지사항 글 쓰기(관리자)</h2>
			<form id="write_form" action="write.do" method="post" enctype="multipart/form-data">
				<ul>
					<li>
						<label for="notice_title">제목(필수)</label>
						<br>
						<input type="text" name="notice_title" id="notice_title" maxlength="50">
					</li>
					<li>
						<label for="notice_content">내용(필수)</label>
						<br>
						<textarea rows="20" cols="70" name="notice_content" id="notice_content"></textarea>
					</li>
					<li>
						<label for="notice_photo">파일첨부(선택)</label>
						<br>
						<input type="file" name="notice_photo" accept="image/gif,image/png,image/jpeg" multiple>
					</li>
					<li>
						<label for="notice_impt">중요</label>
						<input type="checkbox" name="notice_impt" id="notice_impt" value="1">
					</li>
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
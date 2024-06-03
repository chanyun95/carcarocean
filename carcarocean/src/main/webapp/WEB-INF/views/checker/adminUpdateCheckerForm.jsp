<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>검수자 정보 수정 폼</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script>
	function submit_btn (){
		if(!confirm('수정하시겠습니까?')){
			return false;
		}
		alert('수정되었습니다!');
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container text-center p-5">
		<hr size="1" width="100%" noshade="noshade">
		<h1>검수자 정보 수정</h1>
		<form id="my_form" action="adminUpdateChecker.do" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${checker.checker_num}" name="checker_num">
			<ul class="list-unstyled">
				<li>
					<label for="checker_name">이름</label>
					<input type="text" id="checker_name" name="checker_name" class="checker-data form-control" maxlength="4" placeholder="예) 홍길동" value="${checker.checker_name }">
				</li>
				<li>
					<label for="checker_company">회사</label>
					<input type="text" id="checker_company" name="checker_company" class="checker-data form-control" placeholder="예) 광주수완직영점" value="${checker.checker_company }">			
				</li>
				<li>
					<label for="checker_phone">전화번호</label>
					<input type="text" id="checker_phone" name="checker_phone" class="checker-data form-control" maxlength="11" placeholder="예) 01012345678" value="${checker.checker_phone }">
				</li>
				<li>
					<label for="checker_photo">사진</label>
					<input type="file" id="checker_photo" name="checker_photo" accept="image/png, image/jpg, image/jpg, image/jpeg" class="input-check form-control">
				</li>
			</ul>
			<hr size="1" width="100%" noshade="noshade">
			<!-- 이름 사진 회사 전화번호 -->
			<div class="bg-color-gray">
				<input type="submit" class="btn btn-primary" value="수정" onclick="submit_btn()">
				<input type="button" class="btn btn-secondary" value="취소" onclick="history.back()">
			</div>
		</form>
	</div>
</body>
</html>
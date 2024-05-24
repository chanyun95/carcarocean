<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>로그인</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#login_form').submit(function() {
			if ($('#mem_id').val().trim() == '') {
				alert('아이디를 입력하세요');
				$('#mem_id').val('').focus();
				return false;
			}
			if ($('#mem_passwd').val().trim() == '') {
				alert('비밀번호를 입력하세요');
				$('#mem_passwd').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="content-main">
			<h2 class = "w-50 mx-auto">로그인</h2>
			<form id="login_form" action="login.do" method="post" class="border border-warning p-3 bg-right w-50 mx-auto">
                	<div class="w-50 p-3">
					<label for="mem_id" class="form-label" >아이디</label> 
					<input type="text" class="form-control" name = "mem_id" id="mem_id" maxlength = "12">
					<label for="mem_passwd" class="form-label">비밀번호</label> 
					<input type="password" class="form-control" name = "mem_passwd" id="mem_passwd">
					<br>
				<button type="submit" class="btn btn-warning">로그인</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>







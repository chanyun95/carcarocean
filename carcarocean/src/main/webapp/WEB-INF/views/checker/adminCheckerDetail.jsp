<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>검수자 세부정보</title>
<script>
function delete_btn() {
	if(confirm("정말 삭제하시겠습니까?")){
		location.href= "adminDeleteChecker.do?checker_num=${checker.checker_num}";
		alert("삭제되었습니다!");
	}
}
</script>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<hr size="1" noshade width="100%">
		<h1 class="text-center">검수자 세부정보</h1>
		<div>
			<ul class="list-unstyled">
				<li>
					이름 : ${checker.checker_name}
				</li>
				<li>
					회사 : ${checker.checker_company}
				</li>
				<li>
					전화번호 : ${checker.checker_phone}
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/upload/${checker.checker_photo}">
				</li>
			</ul> 
		</div>
		<hr size="1" noshade width="100%">
		<div class="float-end">
			<input type="button" value="목록" class="btn btn-warning" onclick="location.href='adminCheckerList.do'">
			<input type="button" value="수정" class="btn btn-primary" onclick="location.href='adminUpdateCheckerForm.do?checker_num=${checker.checker_num}'">
			<input type="button" value="삭제" class="btn btn-danger" onclick="delete_btn()" <c:if test="${count>0}">disabled</c:if>>
		</div>
	</div>
</body>
</html>
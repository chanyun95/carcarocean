<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 거래내역</title>
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
<form id="mytransactional_form" action="myTransactionalForm.do" method="post">
<a style="font-size: 50px;">${member.mem_name}님의 거래내역</a><br>
</form>
</body>
</html>
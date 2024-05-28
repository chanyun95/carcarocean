<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 거래내역</title>
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
	<div>
	<c:set var="sub_title" value="${member.mem_name}님의 거래내역" scope="request"/>
	<jsp:include page="/WEB-INF/views/member/myPageMenu.jsp"/>
	하루종일
	</div>
	<!-- footer -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
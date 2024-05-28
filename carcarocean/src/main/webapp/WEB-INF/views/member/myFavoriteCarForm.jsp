<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>나의 관심차량</title>
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
	<c:set var="sub_title" value="${member.mem_name}님의 관심차량" scope="request"/>
	<jsp:include page="/WEB-INF/views/member/myPageMenu.jsp"/>
</body>
<footer>
	<!-- Footer-->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<!-- end of Footer -->
</footer>
</html>
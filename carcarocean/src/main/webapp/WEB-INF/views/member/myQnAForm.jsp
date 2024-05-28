<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 문의내역</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pgh.css" type="text/css">
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
	<c:set var="sub_title" value="${member.mem_name}님의 문의내역" scope="request"/>
	<jsp:include page="/WEB-INF/views/member/myPageMenu.jsp"/>

		<!-- footer -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
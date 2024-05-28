<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내차팔기 신청현황</title>
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
	<c:set var="sub_title" value="${member.mem_name}님의 신청현황" scope="request"/>
	<jsp:include page="/WEB-INF/views/member/myPageMenu.jsp"/>
		<!-- footer -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
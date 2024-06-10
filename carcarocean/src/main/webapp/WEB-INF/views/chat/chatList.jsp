<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>나에게 온 채팅 보기</title>
</head>
<body>
	<div class="container border rounded my-5">
		<h2 class="text-center p-3">채팅 목록</h2>
		<c:forEach var="chat" items="${chatList}">
		<div class="d-flex justify-content-between bg-light border rounded my-3">
			<div>
				${chat.chat_message}
			</div>
			<div>
				<button class="btn btn-warning bnt-lg text-white fw-bold" onclick="location.href='${pageContext.request.contextPath}/chat/chat.do?item_num=${item_num}&chat_giver=${chat.receiver.mem_num}&chat_receiver=${chat.giver.mem_num}'">${chat.giver.mem_id}님의 대화</button>
			</div>
		</div>
		</c:forEach>
		<div class="d-flex justify-content-center">
			<button class="btn btn-warning bnt-lg text-white fw-bold my-3" onclick="history.back()">이전으로</button>
		</div>
	</div>
</body>
</html>
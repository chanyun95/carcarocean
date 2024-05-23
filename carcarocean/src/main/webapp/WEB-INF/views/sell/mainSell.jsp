<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 차 팔기 메인</title>
	<script>
    	function openPopup() {
    		window.open("insertSellForm.do", "판매 신청서", "width=600,height=400,scrollbars=yes");
    	}
	</script>
</head>
<body>
	<button onclick="openPopup()">Open Form</button>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>myPageMenu</title>
<!-- bootstrap css cdn 링크 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- bootstrap 아이콘 cdn 링크 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<style>
.myPageMenu {
	width: 300px;
	padding: 20px;
}

.content-main {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

.mypage-div {
	border: 1px solid #ddd;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	width: 80%;
	max-width: 1000px;
}

.menu-container {
	display: flex;
	justify-content: space-between;
	margin-bottom: 20px;
}

.menu {
	list-style-type: none;
	padding: 0;
	margin: 0;
	display: flex;
	flex-direction: column; /* 세로 정렬 */
}

.menu li {
	display: inline;
	margin-right: 10px; /* 메뉴 간격 조절 */
}

.menu a {
	color: #a1a6a2; /* 링크 색상 변경 */
	text-decoration: none; /* 밑줄 제거 */
}

.menu a:hover {
	color: black; /* 링크에 마우스를 올렸을 때 색상 변경 */
}
</style>


</head>
<body>
		<div class="myPageMenu">
   			 <h4><b>${sub_title}</b></h4>
   			 <br>
			<ul class="menu">
				<li><a href="modifyUserForm.do">회원정보</a></li>
			</ul>
			<hr width="100%" size="1" noshade="noshade">
			<ul class="menu">
				<li><a href="myFavoriteCarForm.do">관심차량</a><br></li>
				<li><a href="mySellCurrentStatusForm.do">내차팔기 신청현황</a><br></li>
				<li><a href="myTransactionalForm.do">거래내역</a></li>
			</ul>
			<hr width="100%" size="1" noshade="noshade">
			<ul class="menu">
				<li><a href="myQnAForm.do">문의내역</a><br></li>
				<li><a href="myWriteBoardForm.do">내가 쓴 글</a></li>
			</ul>
			<hr width="100%" size="1" noshade="noshade">
			<ul class="menu">
				<li><a href="deleteUserForm.do">회원탈퇴</a><br></li>
			</ul>
		</div>
</body>
</html>
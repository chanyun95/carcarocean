<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Header</title>
<!-- bootstrap css cdn 링크 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap 아이콘 cdn 링크 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<style>
/* 메뉴 hover 방식 */
.dropdown:hover .dropdown-menu {
    display: block;
    margin-top: 0;
}
/* 메뉴 간격 조정 */
.nav-item {
    margin-left: 30px;
}
/* 로고 크기 및 위치 조정 */
.navbar-brand img {
    max-height: 150px;
}
.myLink:link {
  color : black;
}
.myLink:visited {
  color : black;
}
.nav-link{
	cursor: pointer;
}
.myLink{
	text-decoration-line: none;
}
</style>
</head>
<body>
    <!-- continer 기본 padding값 때문에 로그아웃 회원가입이 밀려서 일단 임시조치로 left right 0으로 처리 -->
    <div class="container rounded-bottom" style="padding-left:0; padding-right:0; background-color:;">
      <!-- nav태그 상단 고정 -->
      <nav class="navbar navbar-expand-lg">
         <div class="container">
            <!-- 로고 시작 -->
            <div class="pe-3">
            	<a href="${pageContext.request.contextPath}/main/main.do" class="navbar-brand"><img src="${pageContext.request.contextPath}/images/mainlogo.png" width="200"></a>  
            </div>
            <!-- 로고 끝 -->
            <!-- 메뉴 시작 -->
            <div class="collapse navbar-collapse justify-content-between" id="nav">
               <ul class="navbar-nav">
               	  <!-- 첫번째 메뉴 -->
                  <li class="nav-item dropdown"><a class="nav-link text-dark px-3 fs-4" data-toggle="dropdown"><b>차량 구매</b></a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/buy/mainBuy.do">차량 구매</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/b_re/list.do">차량 구매 후기</a>
                        <a class="dropdown-item" href="#">관심 차량</a>
                     </div>
                  </li>
                  <!-- 두번째 메뉴 -->
                  <li class="nav-item dropdown"><a class="nav-link text-dark px-3 fs-4" data-toggle="dropdown"><b>차량 판매</b></a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/sell/mainSell.do">차량 판매</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/s_re/list.do">차량 판매 후기</a>
                     </div>
                  </li>
                  <!-- 세번째 메뉴 -->
                  <li class="nav-item dropdown"><a class="nav-link text-dark px-3 fs-4" data-toggle="dropdown"><b>커뮤니티</b></a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/board/list.do">자유게시판</a>
                     </div>
                  </li>
                  <!-- 네번째 메뉴 -->
                  <li class="nav-item dropdown"><a class="nav-link text-dark px-3 fs-4" data-toggle="dropdown"><b>고객 센터</b></a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/notice/list.do">공지사항</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/event/list.do">이벤트</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/qa/list.do">고객 문의</a>
                     </div>
                  </li>
                  <c:if test="${!empty user_num && user_auth ==9}">
                  
                   <li class="nav-item dropdown"><a class="nav-link text-dark px-3 fs-4" data-toggle="dropdown"><b>관리자</b></a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/sell/adminSellList.do">검수 요청 목록</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/checker/adminCheckerList.do">검수자 정보 목록</a>
                     </div>
                  </li>
                  </c:if>
               </ul>
               <!-- 로그인 및 회원가입 시작 -->
               <c:if test="${empty user_num}">
               <div class="d-flex justify-content-center">
                  <i class="bi bi-person-fill pe-3"> <a class="myLink" href="${pageContext.request.contextPath}/member/loginForm.do"><b>로그인</b></a></i>
                  <div class="border-end"></div>
                  <i class="bi bi-person-fill-add ps-3"> <a class="myLink" href="${pageContext.request.contextPath}/member/registerUserForm.do"><b>회원가입</b></a></i>
               </div>
               </c:if>
               <!-- 로그인 및 회원가입 끝 -->
               <!-- 로그인시 로그인 , 로그아웃 -->
               <c:if test="${!empty user_num}">
               <div class="d-flex justify-content-center">
               	  <c:if test = "${!empty user_num && user_auth != 9}">
                  <i class="bi bi-person-fill-gear pe-3"><a class="myLink" href="${pageContext.request.contextPath}/member/myPage.do"><b>MyPage</b></a></i>
                  <div class="border-end"></div>
                  </c:if>
                  <i class="bi bi-person-fill-dash ps-3"><a class="myLink" href="${pageContext.request.contextPath}/member/logout.do"><b>로그아웃</b></a></i>
               </div>
               </c:if>
            </div>
         </div>
      </nav>
   </div>
</body>
</html>
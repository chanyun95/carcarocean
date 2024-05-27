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
/* nav-item 간격 조정 */
.nav-item {
    margin-right: 90px;
}
/* 로고 크기 및 위치 조정 */
.navbar-brand img {
    max-height: 150px;
}
</style>


</head>
<body>
   <div class="container">
      <!-- nav태그 상단 고정 -->
      <nav class="navbar navbar-expand-lg navbar-light be-white">
         <div class="container-fluid">
            <!-- 로고 시작 -->
            <a href="${pageContext.request.contextPath}/main/main.do" class="navbar-brand"><img class="fa-2x" src="${pageContext.request.contextPath}/images/mainlogo.png" width="150" height="150"></a>
            <button type="button" class="navbar-toggler bg-light" data-toggle="collapse" data-target="#nav"><span class="navbar-toggler-icon"></span></button>  
            <!-- 로고 끝 -->
            <!-- 메뉴 시작 -->
            <div class="collapse navbar-collapse justify-content-between" id="nav">
               <ul class="navbar-nav">
                  <li class="nav-item"><a class="nav-link text-dark font-weight-bold px-3" href="${pageContext.request.contextPath}/main/main.do">홈</a></li>
                  <li class="nav-item dropdown"><a class="nav-link text-dark font-weight-bold px-3 dropdown-toggle" href="#" data-toggle="dropdown">차량 구매</a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/sell/mainSell.do">차량 구매</a>
                        <a class="dropdown-item" href="#">차량 구매 후기</a>
                        <a class="dropdown-item" href="#">관심 차량</a>
                     </div>
                  </li>
                  <li class="nav-item dropdown"><a class="nav-link text-dark font-weight-bold px-3 dropdown-toggle" href="#" data-toggle="dropdown">차량 판매</a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/sell/mainSell.do">차량 판매</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/s_re/s_re>list.do">차량 판매 후기</a>
                     </div>
                  </li>
                  <li class="nav-item dropdown"><a class="nav-link text-dark font-weight-bold px-3 dropdown-toggle" href="#" data-toggle="dropdown">커뮤니티</a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">자유게시판</a>
                     </div>
                  </li>
                  <li class="nav-item dropdown"><a class="nav-link text-dark font-weight-bold px-3 dropdown-toggle" href="#" data-toggle="dropdown">고객 센터</a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/notice/list.do">공지사항</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/event/list.do">이벤트</a>
                        <a class="dropdown-item" href="#">고객 문의</a>
                     </div>
                  </li>
                  <c:if test="${!empty user_num && user_auth ==9}">
                  
                   <li class="nav-item dropdown"><a class="nav-link text-dark font-weight-bold px-3 dropdown-toggle" href="#" data-toggle="dropdown">관리자</a>
                     <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/sell/adminSellList.do">검수 요청 목록</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/checker/adminCheckerList.do">검수자 정보 목록</a>
                        <a class="dropdown-item" href="#">고객 문의</a>
                     </div>
                  </li>
                  </c:if>
               </ul>
               <!-- 로그인 및 회원가입 시작 -->
               <c:if test="${empty user_num}">
               <div>
                  <i class="bi bi-person-circle"><a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a></i>
                  <i class="bi bi-person-plus"><a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a></i>
               </div>
               </c:if>
               <!-- 로그인 및 회원가입 끝 -->
               <!-- 로그인시 로그인 , 로그아웃 -->
               <c:if test="${!empty user_num}">
               <div>
                  <i class="bi bi-person-circle"><a href="${pageContext.request.contextPath}/member/myPage.do">마이페이지</a></i>
                  <i class="bi bi-person-circle"><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></i>
               </div>
               </c:if>
            </div>
         </div>
      </nav>
   </div>
</body>
</html>
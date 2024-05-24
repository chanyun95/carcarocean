<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>마이페이지</title>
<style>
    .page-main {
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
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
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
    }

    .menu li {
        display: inline;
        margin-right: 10px; /* 메뉴 간격 조절 */
    }

    .profile {
        margin-left: 20px;
        flex-grow: 1;
    }

    hr {
        margin: 10px 0;
    }
</style>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
</head>
<body>
<div class="page-main">
    <div class="content-main">
        <div class="mypage-div">
            <form>
            <h3>마이페이지</h3>
                <div class="menu-container">
                    <div>
                        <ul class="menu">
                            <li><a href="#">회원정보</a></li>
                        </ul>
                        <hr width="30%" size="1" noshade="noshade">
                        <ul class="menu">
                            <li><a href="myFavoriteCarForm.do">관심차량</a><br></li>
                            <li><a href="mySellCurrentStatusForm.do">내차팔기 신청현황</a><br></li>
                            <li><a href="myTransactionalForm.do">거래내역</a></li>
                        </ul>
                        <hr width="30%" size="1" noshade="noshade">
                        <ul class="menu">
                            <li><a href="myQnAForm.do">문의내역</a><br></li>
                            <li><a href="myWriteBoardForm.do">내가 쓴 글</a></li>
                        </ul>
                    </div>
                    <div class="profile">
                        <ul>
                            <li>
                               <a style="font-size: 50px;">${member.mem_name} 님</a><br>
                               <a href="#">정보수정</a><br>
                                <c:if test="${empty member.mem_photo}"> <!-- 이미지가 비어있는 경우 -->
                                    <img src="${pageContext.request.contextPath}/images/face.png" 
                                         width="100" height="100" class="my-photo">
                                </c:if>
                                <c:if test="${!empty member.mem_photo}"> <!-- 이미지가 비어있지 않는 경우 -->
                                    <img src="${pageContext.request.contextPath}/upload/${member.mem_photo}"
                                         width="200" height="200" class="my-photo">
                                </c:if>
                            </li>
                            
                        </ul>
                    </div>
                </div>
                <div class="mypage-div">
            <h3>내가 쓴글</h3>
            <table>
                <tr>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>등록일</th>
                </tr>
               <!-- 내용 명시해야됨 -->
               <%-- <c:forEach var="board" items="${boardList}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}" target="_blank">${fn:substring(board.title,0,12)}</a></td>
                    <td>${board.id}</td>
                    <td>${board.reg_date}</td>
                </tr>
                </c:forEach> --%>
            </table>
        </div> 
        <div class="mypage-div">
            <h3>내차팔기 신청현황</h3>
            <table>
                <tr>
                    <th>제목</th>
                    <th>담당자</th>
                    <th>작성일</th>
                </tr>
                <!-- 내용 명시해야됨 -->
            </table>
        </div> 
        <div class="mypage-div">
            <h3>문의 내역</h3>
            <table>
                <tr>
                    <th>제목</th>
                    <th>등록일</th>
                </tr>
                <!-- 내용 명시해야됨 -->
            </table>
        </div> 
            </form>
            
        </div>
        
        <div class="mypage-end"></div>
    </div>
</div>
</body>
</html>

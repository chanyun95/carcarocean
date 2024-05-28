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

    .profile {
        display: flex;
        align-items: center; /* 세로 정렬 */
        margin-left: 20px;
        flex-grow: 1;
    }

    .profile-info {
        margin-right: 20px;
    }

    .profile img {
        margin-left: 20px;
    }

    hr {
        margin: 10px 0;
    }
    .mypage{

    }
</style>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/member/myPageMenu.jsp" />
	<div class="page-main">
		<div class="content-main">
			<div class="mypage-div">

				<div class="menu-container">

					<div class="profile">
						<div class="profile-info">
							<a style="font-size: 50px;">${member.mem_name} 님</a><br> <a
								href="${pageContext.request.contextPath}/member/modifyUserForm.do">정보수정</a><br>
						</div>
						<c:if test="${empty member.mem_photo}">
							<!-- 이미지가 비어있는 경우 -->
							<img src="${pageContext.request.contextPath}/images/face.png"
								width="100" height="100" class="my-photo">
						</c:if>
						<c:if test="${!empty member.mem_photo}">
							<!-- 이미지가 비어있지 않는 경우 -->
							<img
								src="${pageContext.request.contextPath}/upload/${member.mem_photo}"
								width="200" height="200" class="my-photo">
						</c:if>
					</div>
				</div>
				<div class="border border-warning p-3 bg-right w-80 mx-auto">
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
				<br>
				<div class="border border-warning p-3 bg-right w-80 mx-auto">
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
				<br>
				<div class="border border-warning p-3 bg-right w-80 mx-auto">
					<h3>문의 내역</h3>
					<table>
						<tr>
							<th>제목</th>
							<th>등록일</th>
						</tr>
						<!-- 내용 명시해야됨 -->
					</table>
				</div>

			</div>
		</div>
	</div>
</body>
<footer>
	<!-- Footer-->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<!-- end of Footer -->
</footer>
</html>

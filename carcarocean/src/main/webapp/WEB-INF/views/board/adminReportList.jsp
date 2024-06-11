<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 누적된 글</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
	<div class="container">
		<div class="row justify-content-center">
			<main class="col-md-10 pt-5 pb-5">
			<h2 class="text-danger"><b>신고 누적된 글</b></h2>
				<br><br>
                <div class="mypage-div">
             	    <h3>자유게시판</h3>
                    <table class="table text-center align-content-center fw-bold mt-3">
                        <thead>
                            <tr>
                                <th>글번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="board" items="${list}">
                           		<c:if test="${board.board_report >= 10}">
                                <tr>
                                    <td width = "250px">${board.board_num}</td>
                                    <td width = "250px"><a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}" class="link-warning link-offset-2 link-underline-opacity-0 link-underline-opacity-0-hover">${board.board_title}</a></td>
                                   	<td width = "250px">${board.mem_id}</td>
                                    <c:if test = "${empty board.board_modify}">
										<td>${fn:substring(board.board_reg,0,10)}</td>
									</c:if>
                                    <c:if test = "${!empty board.board_modify}">
										<td>${fn:substring(board.board_modify,0,10)}</td>
									</c:if>
									<td>${board.board_hit}</td>
                                </tr>
                               </c:if> 
                            </c:forEach>
                        </tbody>
                    </table>
                	<h3>정보공유 게시판</h3>
                    <table class="table text-center align-content-center fw-bold mt-3">
                        <thead>
                            <tr>
                                <th>글번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="info" items="${list2}">
                           	 	<c:if test="${info.info_board_report >= 10}">
	                                <tr>
	                                    <td width = "250px">${info.info_board_num}</td>
	                                    <td width = "250px"><a href="${pageContext.request.contextPath}/info_board/detail.do?info_board_num=${info.info_board_num}" class="link-warning link-offset-2 link-underline-opacity-0 link-underline-opacity-0-hover">${info.info_board_title}</a></td>
	                                   	<td width = "250px">${info.mem_id}</td>
	                                    <c:if test = "${empty info.info_board_modify}">
											<td>${fn:substring(info.info_board_reg,0,10)}</td>
										</c:if>
	                                    <c:if test = "${!empty info.info_board_modify}">
											<td>${fn:substring(info.info_board_modify,0,10)}</td>
										</c:if>
										<td>${info.info_board_hit}</td>
	                                </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </main>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 문의내역</title>
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
	<div class="container">
		<div class="row">
			<c:set var="sub_title" value="${member.mem_name}님 문의내역"
				scope="request" />
			<jsp:include page="/WEB-INF/views/member/myPageMenu.jsp" />
			<main class="col-md-10 pt-5 pb-5">
                <div class="mypage-div">
                	<h2>내 문의내역</h2><a href = "${pageContext.request.contextPath}/qa/list.do">더보기..</a>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>제목</th>
                                <th>작성일</th>
                                <th>수정일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="qa" items="${list}">
                                <tr>
                                    <td>${qa.qa_num}</td>
                                    <td>${qa.qa_title}</td>
                                   	<td>${fn:substring(qa.qa_reg,0,10)}</td>
                                    <c:if test = "${!empty qa.qa_modify}">
									<td>${fn:substring(qa.qa_modify,0,10)}</td>
									</c:if>
									<c:if test = "${empty qa.qa_modify}">
                                    <td>데이터없음</td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </main>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
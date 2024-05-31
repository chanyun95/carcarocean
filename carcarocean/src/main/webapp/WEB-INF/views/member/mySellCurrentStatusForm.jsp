<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내차팔기 신청현황</title>
    <!-- 여기에 필요한 CSS 파일이나 JavaScript 파일을 연결하세요 -->
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <div class="container">
        <div class="row">
            <c:set var="sub_title" value="${member.mem_name}님 판매현황" scope="request" />
            <jsp:include page="/WEB-INF/views/member/myPageMenu.jsp" />
            <main class="col-md-10 pt-5 pb-5">
                <div class="mypage-div">
                	<h2>내 차 팔기 신청현황</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>차명</th>
                                <th>검수완료여부</th>
                                <th>검수수정일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="sell" items="${list}">
                                <tr>
                                    <td>${sell.sell_num}</td>
                                    <!-- 링크 추가해야함 -->
                                    <td><a href="${pageContext.request.contextPath}/qa/detail.do?qa_num=${qa.qa_num}" class="link-warning link-offset-2 link-underline-opacity-0 link-underline-opacity-0-hover">${sell.sell_cname}</a></td>
                                    <c:if test = "${sell.sell_check==0}">
                                    <td>검수전</td>
                                    </c:if>
                                    <c:if test = "${sell.sell_check==1}">
                                    <td>검수중</td>
                                    </c:if>
                                    <c:if test = "${sell.sell_check==2}">
                                    <td>검수완료</td>
                                    </c:if>
                                    <c:if test = "${!empty sell.sell_modify_check}">
									<td>${fn:substring(sell.sell_modify_check,0,10)}</td>
									</c:if>
									<c:if test = "${empty sell.sell_modify_check}">
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
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>

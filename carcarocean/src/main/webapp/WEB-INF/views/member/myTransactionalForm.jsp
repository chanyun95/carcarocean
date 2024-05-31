<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 거래내역</title>
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
	<div class="container">
		<div class="row">
			<c:set var="sub_title" value="${member.mem_name}님 거래내역"
				scope="request" />
			<jsp:include page="/WEB-INF/views/member/myPageMenu.jsp" />
			<main class="col-md-10 pt-5 pb-5">
                <div class="mypage-div">
                	<h4>판매내역</h4>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>차명</th>
                                <th>판매신청일</th>
                                <th>거래완료일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="sell" items="${mlist}">
                                <tr>
                                    <td>${sell.sell_num}</td>
                                    <!-- 링크 추가해야함 -->
                                    <td>${sell.sell_cname}</td>
                                    <td>${fn:substring(sell.sell_reg,0,10)}
									<td>${fn:substring(sell.sell_modify_check,0,10)}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <h4>판매내역</h4>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>차명</th>
                                <th>판매신청일</th>
                                <th>거래완료일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="sell" items="${mlist}">
                                <tr>
                                    <td>${sell.sell_num}</td>
                                    <!-- 링크 추가해야함 -->
                                    <td>${sell.sell_cname}</td>
                                    <td>${fn:substring(sell.sell_reg,0,10)}
									<td>${fn:substring(sell.sell_modify_check,0,10)}</td>
									
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
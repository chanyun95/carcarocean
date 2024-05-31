<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>나의 관심차량</title>
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<body>
	<div class="container">
		<div class="row">
			<c:set var="sub_title" value="${member.mem_name}님 관심차량"
				scope="request" />
			<jsp:include page="/WEB-INF/views/member/myPageMenu.jsp" />
			<main class="col-md-10 pt-5 pb-5">
                <div class="mypage-div">
                	<h2>내 관심차량</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>제조사</th>
                                <th>차명</th>
                                <th>가격</th>
                                <th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="car" items="${carList}" status="var">
                                <tr>
                                    <td>${car.car_num}</td>
                                    <td>${car.mem_num}</td>
                                    <td>${car.car_num}</td>
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
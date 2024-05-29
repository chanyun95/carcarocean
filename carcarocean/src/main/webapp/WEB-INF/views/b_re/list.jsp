<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 후기</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div>
			<h1>구매 후기 목록</h1>
			<c:if test="${count == 0}">
				<div>
					표시할 게시물이 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0}">
					<table>
					<tr>
						<th>차명</th>
						<th>제목</th>
						<th>내용</th>
						<th>등록일</th>
						<th>차사진</th>
					</tr>
					<c:forEach var="b_re" items="${list}">
						<tr>
							<td>${b_re.car_name}</td>
							<td>${b_re.b_re_title}</td>
							<td><a href="detail.do?b_re_num=${b_re.b_re_num}">${b_re.b_re_content}</a></td>
							<td>${b_re.b_re_reg}</td>
							<c:if test="${fn:contains(b_re.car_photo, ',')}">
								<c:set var="photoList" value="${fn:split(b_re.car_photo, ',')}" />
								<c:set var="firstPhoto" value="${photoList[0]}"/>
								<td>${photoList[0]}</td>
							</c:if>
							<c:if test="${!fn:contains(b_re.car_photo, ',')}">
								<c:set var="firstPhoto" value="${b_re.car_photo}" />
								<td>${b_re.car_photo}</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				<div>${page}</div>
			</c:if>
		</div>
	</div>
</body>
</html>
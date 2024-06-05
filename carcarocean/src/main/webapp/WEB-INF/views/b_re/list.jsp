<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<title>차량 구매 후기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<hr size="1" noshade width="100%">
		<h2 class="pt-5">차량 구매 후기</h2>
			<c:if test="${count == 0}">
				<div>
					표시할 게시물이 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0}">
					<table class="table table-hover text-center mt-5">
					<thead class="table-light">
						<tr>
							<th>제조사</th>
							<th>차명</th>
							<th>제목</th>
							<th>등록일</th>
							<th>차사진</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="b_re" items="${list}">
						<tr class="align-middle">
							<td><a class="text-decoration-none text-dark" href="detail.do?b_re_num=${b_re.b_re_num}"><b>${b_re.car_maker}</b></a></td>
							<td><a class="text-decoration-none text-dark" href="detail.do?b_re_num=${b_re.b_re_num}"><b>${b_re.car_name}</b></a></td>
							<td><a class="text-decoration-none text-dark" href="detail.do?b_re_num=${b_re.b_re_num}"><b>${b_re.b_re_title}</b></a></td>
							<td>
								<c:if test="${empty b_re.b_re_modify}">
									  <a class="text-decoration-none text-dark" href="detail.do?b_re_num=?${b_re.b_re_num}"><b>${fn:substring(b_re.b_re_reg, 0, 10)}</b></a>
								</c:if>
								<c:if test="${!empty b_re.b_re_modify}">
									 <a class="text-decoration-none text-dark" href="detail.do?b_re_num=?${b_re.b_re_num}"><b>${fn:substring(b_re.b_re_modify, 0, 10)}</b></a>
								</c:if>
							</td>
							<c:if test="${fn:contains(b_re.car_photo, ',')}">
								<c:set var="photoList" value="${fn:split(b_re.car_photo, ',')}" />
								<c:set var="firstPhoto" value="${photoList[0]}"/>
								<td><a href="detail.do?b_re_num=${b_re.b_re_num}"><img src="${pageContext.request.contextPath}/upload/${firstPhoto}" width="100px" height="100px"></a></td>
							</c:if>
							<c:if test="${!fn:contains(b_re.car_photo, ',')}">
								<c:set var="firstPhoto" value="${b_re.car_photo}" />
								<td><a href="detail.do?b_re_num=${b_re.b_re_num}"><img src="${pageContext.request.contextPath}/upload/${firstPhoto}" width="100px" height="100px"></a></td>
							</c:if>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="text-center mb-4">${page}</div>
			</c:if>
		</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
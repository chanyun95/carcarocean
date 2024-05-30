<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>검수자 목록 (관리자)</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<hr size="1" width="100%" noshade="noshade">
		<h1 class="text-center">검수자 정보 목록</h1>
		<table class="table table-hover">
			<thead class="table-light">
				<tr>
					<th>#</th>
					<th>이름</th>
					<th>회사</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="checker" items="${list}">
				<tr>
					<td>${checker.checker_num}</td>
					<td><a href="adminCheckerDetail.do?checker_num=${checker.checker_num}">${checker.checker_name}</a></td>
					<td>${checker.checker_company}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-center">${page}</div>
		<hr size="1" width="100%" noshade="noshade">
		<div class="float-end">
			<input type="button" value="검수자 등록" class="btn btn-primary" onclick="location.href='adminWriteCheckerForm.do'">
		</div>
	</div>
</body>
</html>
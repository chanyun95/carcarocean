<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>상품 상세</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<h1>상품 상세</h1>
		<hr>
		<div class="text-end">
			<c:if test="${user_num==item.member.mem_num}">
				<button class="btn btn-warning fw-bold text-white" onclick="location.href='updateItemForm.do?item_num=${item.item_num}'">수정</button>
				<button class="btn btn-warning fw-bold text-white" onclick="delete_btn()">삭제</button>
			</c:if>
		</div>
		<div class="border rounded">
			<div class="text-center">
				<c:if test="${fn:contains(item.item_photo, ',')}">
					<c:set var="photoList" value="${fn:split(item.item_photo, ',')}" />
					<c:set var="firstPhoto" value="${photoList[0]}"/>
				</c:if>
				<c:if test="${!fn:contains(item.item_photo, ',')}">
					<c:set var="firstPhoto" value="${item.item_photo}" />
				</c:if>
				<c:if test="${!empty item.item_photo}">
					<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="border rounded">
				</c:if>
				<c:if test="${empty item.item_photo}">
					<img src="${pageContext.request.contextPath}/images/logo.png" class="border rounded">
				</c:if>
			</div>
			<div class="d-flex justify-content-between my-3">
				<div class="d-flex justify-content-start ms-2">
					<div class="d-flex align-items-center">
						<c:if test="${!empty item.member.mem_photo}">
							<img class="rounded-circle mx-2" src="${pageContext.request.contextPath}/upload/${item.member.mem_photo}" width="40px" height="40px">
						</c:if>
						<c:if test="${empty item.member.mem_photo}">
							<img class="rounded-circle mx-2" src="${pageContext.request.contextPath}/images/face.png" width="40px" height="40px">
						</c:if>
					</div>
					<div class="col">
						<div class="row-6 text-center fw-bold">${item.member.mem_id}</div>
						<div class="row-6">${item.member.mem_address1} ${item.member.mem_address2}</div>
					</div>
				</div>
				<div class="d-flex align-items-center">
					등급: ${item.member.mem_grade}
				</div>
			</div>
			<hr>
			<div class="my-4">
				<div class="fw-bold fs-4">${item.item_name}</div>
				<div class="small text-secondary">${item.item_reg}</div>
				<div class="fw-bold"><fmt:formatNumber value="${item.item_price}" type="number"/>원</div>
			</div>
			<div class="my-4">
				${item.item_detail}
			</div>
			<div class="my-4 text-secondary small">
				채팅 0 ∙ 조회 ${item.item_views}
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
		function delete_btn(){
			if(confirm('정말 상품을 삭제하시겠습니까?')){
				location.href='deleteItem.do?item_num=${item.item_num}';
			}
		};
	</script>
</body>
</html>
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
<style>
	.carousel-item img{
		width:700px;
		height:500px;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<h1>상품 상세</h1>
		<hr>
		<div class="text-end">
			<c:if test="${user_num==item.member.mem_num}">
				<button class="btn btn-warning fw-bold text-white" onclick="updateStatus_btn()">판매 확정</button>
				<button class="btn btn-warning fw-bold text-white" onclick="update_btn()">수정</button>
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
				
				<div id="itemPhoto" class="carousel carousel-dark slide" data-bs-ride="carousel">
					<div class="carousel-inner">
						<c:if test="${!empty item.item_photo}">
							<c:if test="${fn:contains(item.item_photo, ',')}">
								<c:forEach var="photo" items="${photoList}" varStatus="status">
									<c:if test="${status.first}">
										<div class="carousel-item active">
											<img src="${pageContext.request.contextPath}/upload/${photo}" class="border rounded">
										</div>
									</c:if>
									<c:if test="${!status.first}">
										<div class="carousel-item">
											<img src="${pageContext.request.contextPath}/upload/${photo}" class="border rounded">
										</div>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${!fn:contains(item.item_photo, ',')}">
								<div class="carousel-item active">
									<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="border rounded">
								</div>
							</c:if>
						</c:if>
						<c:if test="${empty item.item_photo}">
							<div class="carousel-item active">
								<img src="${pageContext.request.contextPath}/images/logo.png" class="border rounded">
							</div>
						</c:if>
					</div>
					<button class="carousel-control-prev" type="button" data-bs-target="#itemPhoto" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">이전</span>
					</button>
					<button class="carousel-control-next" type="button" data-bs-target="#itemPhoto" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">다음</span>
					</button>
				</div>
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
					<div><img src="${pageContext.request.contextPath}/images/grade.png" width="45px" height="45px"></div>
					<div class="fw-bold text-secondary">${mem_grade}</div>
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
		function update_btn(){
			if(confirm('정말 상품을 수정하시겠습니까?')){
				location.href='updateItemForm.do?item_num=${item.item_num}';
			}
		};
		function updateStatus_btn(){
			if(confirm('정말 상품 판매를 확정하시겠습니까?')){
				location.href='updateItemStatus.do?item_num=${item.item_num}';
			}
		};
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
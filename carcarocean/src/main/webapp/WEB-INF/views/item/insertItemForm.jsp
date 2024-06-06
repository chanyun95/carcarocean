<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>상품 등록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<h1>상품 등록</h1>
		<hr>
		<form class="form-control" action="insertItem.do" method="post" enctype="multipart/form-data">
			<label for="item_name">상품명</label>
			<input type="text" class="form-control" id="item_name" name="item_name">
			<label for="item_photo">상품사진</label>
			<input type="file" class="form-control" id="item_photo" name="item_photo" accept="image/gif,image/png,image/jpeg">
			<label for="item_price">상품가격</label>
			<input type="number" class="form-control" id="item_price" name="item_price">
			<label for="item_detail">상품상세</label>
			<textarea rows="3" cols="50" class="form-control" id="item_detail" name="item_detail"></textarea>
			<hr>
			<input type="submit" value="등록" class="btn btn-warning">
			<button class="btn btn-warning" onclick="">뒤로가기</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
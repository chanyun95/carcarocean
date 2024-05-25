<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<title>검수 대기 차량 상세 정보</title>
<script>
	function delete_btn() {
		if(confirm("정말 삭제하시겠습니까?")){
			location.href= "deleteSell.do?sell_num=${sell.sell_num}";
			alert("삭제되었습니다.");
		}
	}

</script>
</head>
<body>
	<!-- 헤더 고정 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<hr size="1" width="100%" noshade>
		<h1 class="text-center p-4">검수 대기 차량 상세 정보</h1>
		<span class="fs-3 float-start">${sell.sell_maker} ${sell.sell_cname}</span><br>
		<span class="float-end p-4"><b>${sell.sell_name} | ${sell.sell_reg}</b></span><br>
		<div class="mt-3 p-4">
			<ul class="list-unstyled">
				<li>
					차량번호 : ${sell.sell_cnumber}
				</li>
				<li>
					제조사 : ${sell.sell_maker}
				</li>
				<li>
					차량명 : ${sell.sell_cname}
				</li>
				<li>
					주행거리 : ${sell.sell_mile}
				</li>
				<li>
					신청자명 : ${sell.sell_name}
				</li>
				<li>
					휴대폰번호 : ${sell.sell_phone}
				</li>
				<li>
					검수 희망 지역 (시/도) : ${sell.sell_place1}
				</li>
				<li>
					검수 희망 지역 (군/구) : ${sell.sell_place2}
				</li>
				<li>
					판매 희망일 : <c:choose>
                            	  <c:when test="${sell.sell_date == 0}">즉시</c:when>
                            	  <c:when test="${sell.sell_date == 1}">7일 이내</c:when>
                            	  <c:when test="${sell.sell_date == 2}">30일 이내</c:when>
                            	  <c:otherwise>알 수 없음</c:otherwise>
                        	  </c:choose>
				</li>
				<li>
					검수 진행 상황 : <strong class="fs-3"><c:choose>
								 	 <c:when test="${sell.sell_check == 0}">미검수</c:when>
								     <c:when test="${sell.sell_check == 1}">검수중</c:when>
									 <c:when test="${sell.sell_check == 2}">검수 완료</c:when>
								 </c:choose></strong>
				</li>
				<c:if test="${!empty sell.sell_modify_check}">검수 상태 변경일 : ${sell.sell_modify_check}</c:if>
			</ul>
		</div>
		<hr size="1" width="100%" noshade>
		<div class="d-felx justify-content-between m-3">
			<form action="updateSellCheck.do" method="post" class="float-start">
				<div class="d-flex">
					<input type="hidden" name="sell_num" value="${sell.sell_num}">
					<select class="form-select" name="sell_check" style="width:auto;">
						<option value="0" <c:if test="${sell.sell_check==0}">selected</c:if>>미검수</option>
						<option value="1" <c:if test="${sell.sell_check==1}">selected</c:if>>검수중</option>
						<option value="2" <c:if test="${sell.sell_check==2}">selected</c:if>>검수 완료</option>
					</select>
					<input type="submit" class="btn btn-success" value="검수 상태 변경">
				</div>
			</form>
			<div class="float-end">
				<input type="button" class="btn btn-warning" value="목록" onclick="location.href='adminSellList.do'">
				<input type="button" class="btn btn-danger" value="삭제" onclick="delete_btn()">
			</div>
		</div>
	</div>
</body>
</html>
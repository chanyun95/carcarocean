<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
			<div>
				<h2 class="pt-5">공지사항</h2>
				<form id="search_form" action="list.do" method="get">
					<div>
						<div class="text-start mt-3" style="font-size:15pt;">
							총 ${count}건의 글이 있습니다.
						</div>
						<div class="d-flex justify-content-end mb-4">
							<select name="keyfield" class="form-select" style="width:auto;">
								<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
								<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
							</select>
							<input type="search" size="20" name="keyword" id="keyword" class="form-control rounded" placeholder="검색할 정보를 입력하세요" 
									value="${param.keyword}" style="width:400px;">
							<input type="submit" class="btn btn-warning ms-2" value="검색">
							<input type="button" class="btn btn-warning ms-2" value="검색초기화" onclick="location.href='list.do'">
						</div>
					</div>
				</form>
			</div>
			<c:if test="${count == 0}">
				<div>
					표시할 게시물이 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0}">
			<div>
				<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>등록일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="notice" items="${list}">
					<c:if test="${notice.notice_impt == 1}">
							<tr>
								<td><b class="text-danger">[중요]</b></td>
								<td><a href="detail.do?notice_num=${notice.notice_num}" class="text-decoration-none text-dark"><b>${notice.notice_title}</b></a></td>
								<td>
								<c:if test="${empty notice.notice_modify}">
									${notice.notice_reg} 
								</c:if>
								<c:if test="${!empty notice.notice_modify}">
									${notice.notice_modify}
								</c:if>
								</td>
								<td>${notice.notice_hit}</td>
							</tr>
					</c:if>				
					</c:forEach>
					<c:forEach var="notice" items="${list}">
					<c:if test="${notice.notice_impt != 1}">
						<tr>
							<td>${notice.notice_num}</td>
							<td><a href="detail.do?notice_num=${notice.notice_num}" class="text-decoration-none text-dark">${notice.notice_title}</a></td>
							<td>${notice.notice_reg}</td>
							<td>${notice.notice_hit}</td>
						</tr>
					</c:if>
					</c:forEach>
				</tbody>
				</table>
			</div>
				<div class="text-center mb-3">${page}</div>
				<c:if test="${user_auth == 9}">
				<div class="text-end mb-4">
					<input type="button" class="btn btn-warning" value="글 쓰기" onclick="location.href='writeForm.do'">
				</div>
				</c:if>
			</c:if>
		</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<script type="text/javascript">
	window.onload=function(){
		const myForm = document.getElementById('search_form');
		//이벤트 연결
		myForm.onsubmit=function(){
			const keyword = document.getElementById('keyword');
			if(keyword.value.trim()==''){
				alert('검색어를 입력하세요');
				keyword.value = '';
				keyword.focus();
				return false;
			}
		};
	};
</script>
</html>
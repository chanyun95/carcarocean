<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div>
			<h2>공지사항</h2>
			<form id="search_form" action="list.do" method="get">
				<div>
					총 ${count}건의 글이 있습니다.
				</div>
				<ul>
					<li>
						<select name="keyfield">
							<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
							<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
						</select>
					</li>
					<li>
						<input type="search" size="20" name="keyword" id="keyword" value="${param.keyword}">
					</li>
					<li>
						<input type="submit" value="검색">
						<input type="button" value="검색초기화" onclick="location.href='list.do'">
					</li>
				</ul>
			</form>
			<c:if test="${count == 0}">
				<div>
					표시할 게시물이 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0}">
				<table>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>등록일</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="notice" items="${list}">
					<c:if test="${notice.notice_impt == 1}">
							<tr>
								<td>[중요]</td>
								<td><a href="detail.do?notice_num=${notice.notice_num}">${notice.notice_title}</a></td>
								<td>${notice.notice_reg}</td>
								<td>${notice.notice_hit}</td>
							</tr>
					</c:if>				
					</c:forEach>
					<c:forEach var="notice" items="${list}">
					<c:if test="${notice.notice_impt != 1}">
						<tr>
							<td>${notice.notice_num}</td>
							<td><a href="detail.do?notice_num=${notice.notice_num}">${notice.notice_title}</a></td>
							<td>${notice.notice_reg}</td>
							<td>${notice.notice_hit}</td>
						</tr>
					</c:if>
					</c:forEach>
				</table>
				<div>${page}</div>
				<c:if test="${user_auth == 9}">
				<div>
					<input type="button" value="글 쓰기" onclick="location.href='writeForm.do'">
				</div>
				</c:if>
			</c:if>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 후기 게시판 글 쓰기</title>
<script type="text/javascript">
	window.onload=function(){
		const myForm = document.getElementById('write_form');
		myForm.onsubmit=function(){
			const title = document.getElementById('b_re_title');
			if(title.value.trim()==''){
				alert('제목을 입력하세요!');
				title.value='';
				title.focus();
				return false;
			}
			const buy = document.getElementById('buy_num');
			if(buy.value.trim()==''){
				alert('거래내역을 선택하세요!');
				buy.value='';
				buy.focus();
				return false;
			}
			const content = document.getElementById('b_re_content');
			if(content.value.trim()==''){
				alert('내용을 입력하세요!');
				content.value='';
				content.focus();
				return false;
			}
			const photo = document.getElementById('b_re_photo');
			if(photo.value.trim()==''){
				alert('사진을 첨부하세요!');
				photo.value='';
				photo.focus();
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
			<h2>구매 후기 게시판 글 쓰기</h2>
			<form id="write_form" action="write.do" method="post" enctype="multipart/form-data">
				<ul>
					<li>
						<label for="b_re_title">제목(필수)</label>
						<br>
						<input type="text" name="b_re_title" id="b_re_title" maxlength="50">
					</li>
					<li>
						<label for="car_num">거래 내역(필수)</label>
						<br>
						<select name="car_num" id="car_num">
                            <option value="">거래 내역을 선택하세요</option>
                            <c:forEach var="buy" items="${buyList}">
                                <option value="${buy.car_num}">${buy.buy_num}. ${buy.car_name} / ${buy.buy_reg}</option>
                                <input type="hidden" value="${buy.buy_num}" name="buy_num">
                            </c:forEach>
                        </select>
					</li>
					<li>
						<label for="b_re_content">내용(필수)</label>
						<br>
						<textarea rows="20" cols="70" name="b_re_content" id="b_re_content"></textarea>
					</li>
					<li>
						<label for="b_re_photo">파일첨부(필수)</label>
						<br>
						<input type="file" name="b_re_photo" id="b_re_photo" accept="image/gif,image/png,image/jpeg" multiple>
					</li>
				</ul>
				<div>
					<input type="submit" value="글 쓰기">
					<input type="button" value="목록" onclick="location.href='list.do'">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
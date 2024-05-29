<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 후기 게시판 글 수정</title>
<script type="text/javascript">
	window.onload=function(){
		const myForm = document.getElementById('modify_form');
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
			<form id="modify_form" action="modify.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="b_re_num" value="${b_re.b_re_num}">
				<ul>
					<li>
						<label for="b_re_title">제목(필수)</label>
						<br>
						<input type="text" name="b_re_title" id="b_re_title" value="${b_re.b_re_title}" maxlength="50">
					</li>
					<li>
						<label for="buy_num">거래 내역(필수)</label>
						<br>
						<select name="buy_num" id="buy_num">
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
						<textarea rows="20" cols="70" name="b_re_content" id="b_re_content">${b_re.b_re_content}</textarea>
					</li>
					<li>
						<label for="b_re_photo">파일첨부(필수)</label>
						<br>
						<input type="file" name="b_re_photo" accept="image/gif,image/png,image/jpeg" multiple>
						<div id="file_detail">
							<img src="${pageContext.request.contextPath}/upload/${b_re.b_re_photo}" width="100">
							<br>
							<input type="button" value="파일 삭제" id="file_del">
						</div>
						<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
						<script type="text/javascript">
							$(function(){
								$('#file_del').click(function(){
									let choice = confirm('삭제하시겠습니까?');
									if(choice){
										//서버와 통신
										$.ajax({
											url:'deleteFile.do',
											type:'post',
											data:{b_re_num:${b_re.b_re_num}},
											dataType:'json',
											success:function(param){
												if(param.result == 'success'){
													$('#file_detail').hide();
												}else{
													alert('파일 삭제 오류 발생');
												}
											},
											error:function(){
												alert('네트워크 오류 발생');
											}
										});
									}
								});
							});
						</script>
					</li>
				</ul>
				<div>
					<input type="submit" value="글 수정">
					<input type="button" value="목록" onclick="location.href='list.do'">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
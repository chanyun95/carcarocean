<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
	<div>
		<ul>
			<c:if test="${fn:contains(b_re.car_photo, ',')}">
				<c:set var="photoList" value="${fn:split(b_re.car_photo, ',')}" />
				<c:set var="firstPhoto" value="${photoList[0]}"/>
				<li>
					<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="detail-img">
				</li>
			</c:if>
			<c:if test="${!fn:contains(b_re.car_photo, ',')}">
				<c:set var="firstPhoto" value="${b_re.car_photo}" />
				<li>
					<img src="${pageContext.request.contextPath}/upload/${firstPhoto}" class="detail-img">
				</li>
			</c:if>
			<h2>${b_re.b_re_title}</h2>
			<li>
				<c:if test="${!empty b_re.b_re_modify}">
					${b_re.b_re_modify}
				</c:if>
				<c:if test="${empty b_re.b_re_modify}">
					${b_re.b_re_reg}
				</c:if>
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<div>
			<c:if test="${fn:contains(b_re.b_re_photo, ',')}">
				<c:set var="photoList2" value="${fn:split(b_re.b_re_photo, ',')}" />
					<c:forEach var="photoList3" items="${fn:split(b_re.b_re_photo, ',')}">
						<img src="${pageContext.request.contextPath}/upload/${photoList3}" class="detail-img">
					</c:forEach>
			</c:if>
			<c:if test="${!fn:contains(b_re.b_re_photo, ',')}">
					<img src="${pageContext.request.contextPath}/upload/${b_re.b_re_photo}" class="detail-img">
			</c:if>
		</div>
		<ul>
			<h2>기본정보</h2>
			<li>주행거리 : ${b_re.car_mile} km</li>
			<li>차량번호 : ${b_re.car_cnumber}</li>
			<li>제조사 : ${b_re.car_maker}</li>
			<li>차명 : ${b_re.car_name}</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<p>
			<h2>고객 후기</h2>
			${b_re.b_re_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<img src="${pageContext.request.contextPath}/upload/${b_re.checker_photo}"
						width="40" height="40" class="my-photo">
			</li>
			<li>${b_re.checker_name} 검수자</li>
			<li>
				<!-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정 가능 -->
				<c:if test="${user_num == b_re.mem_num}">
				<input type="button" value="수정"
				  onclick="location.href='modifyForm.do?b_re_num=${b_re.b_re_num}'">
				</c:if>
			</li>
		</ul>
		<!-- 댓글 시작 -->
		<div id="reply_div">
			<span class="re-title">댓글 달기</span>
			<form id="b_re_comm_form">
				<input type="hidden" name="b_re_num" value="${b_re.b_re_num}" id="b_re_num">
				<textarea rows="3" cols="50" name="b_re_comm_content"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>	
				  id="b_re_comm_content" class="rep-content"><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>    
				<c:if test="${!empty user_num}">
				<div id="b_re_comm_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="b_re_comm_second">
					<input type="submit" value="전송">
				</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글 끝 -->
	</div>
</div>
</body>
<script type="text/javascript">
$(function(){
	let rowCount = 10;
	let currentPage;
	let count;
	/* ===============================
	 * 댓글 목록
	 * =============================== */
	//댓글 목록
	function selectList(pageNum){
		currentPage = pageNum;
		//로딩 이미지 노출
		$('#loading').show();
		//서버와 통신
		$.ajax({
			url:'listComm.do',
			type:'post',
			data:{pageNum:pageNum,rowCount:rowCount,b_re_num:$('#b_re_num').val()},
			dataType:'json',
			success:function(param){
				//로딩 이미지 감추기
				$('#loading').hide();
				count = param.count;
				
				if(pageNum==1){
					//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
					$('#output').empty();
				}
				
				$(param.list).each(function(index,item){
					let output = '<div class="item">';
					output += '<h4>' + item.mem_id + '</h4>';
					output += '<div class="sub-item">';
					output += '<p>' + item.b_re_comm_content + '</p>';
					output += '<span>등록일 : ' + item.b_re_comm_reg + '</span>';
					//output += ' <input type="button" data-renum="' + item.b_re_comm_num + '" value="신고" class="report-btn">';
					output += '<hr size="1" noshade width="100%">';
					output += '</div>';
					output += '</div>';
					
					//문서 객체에 추가
					$('#output').append(output);
				});
				
				//page button 처리
				if(currentPage>=Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음 페이지가 존재
					$('.paging-button').show();
				}
			},
			error:function(){
				$('#loading').hide();
				alert('네트워크 오류 발생');
			}
		});
	}
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function(){
		selectList(currentPage + 1);
	});
	/* ===============================
	 * 댓글 등록
	 * =============================== */
	//댓글 등록
	$('#b_re_comm_form').submit(function(event){
		if($('#b_re_comm_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#b_re_comm_content').val('').focus();
			return false;
		}
		
		//form 이하의 태그에 입력한 데이터를 모두 읽어서 쿼리 스트링으로 반환
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url:'writeComm.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					//폼 초기화
					initForm();
					//댓글 작성이 성공하면 새로 삽입한 글을 포함해서 첫 번째
					//페이지의 게시글 목록을 다시 호출함
					selectList(1);
				}else{
					alert('댓글 등록 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});		
		
		//기본 이벤트 제거
		event.preventDefault();
	});
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#b_re_comm_first .letter-count').text('300/300');
	}

	/* ===============================
	 * 댓글 등록 및 수정 공통
	 * =============================== */
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
		//입력한 글자수 구함
		let inputLength = $(this).val().length;
		
		if(inputLength > 300){//300자를 넘어선 경우
			$(this).val($(this).val().substring(0,300));
		}else{//300자 이하인 경우
			let remain = 300 - inputLength;
			remain += '/300';
			if($(this).attr('id') == 'b_re_comm_content'){
				//등록폼 글자수
				$('#b_re_comm_first .letter-count').text(remain);
			}
		}
	});
	/* ===============================
	 * 초기 데이터(목록) 호출
	 * =============================== */
	selectList(1);
});
</script>
</html>

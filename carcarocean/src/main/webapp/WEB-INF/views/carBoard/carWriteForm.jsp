<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차량 등록</title>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"> --%>
<script type="text/javascript">
window.onload = function(){
	const myForm = document.getElementById('write_form');
	//이벤트 연결
	myForm.onsubmit = function(){
		const radio = document.querySelectorAll('input[type=radio]:checked');
		if(radio.length < 0){
			alert('상품판매여부를 지정하세요');
			return false;
		}
		const items = document.querySelectorAll('.input-check');
		for(let i=0; i<items.length; i++){
			if(items[i].value.trim()==''){
				const label = document.querySelector(
						       'label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
			}
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
	<h2>차량 등록</h2>
	<form id="write_form" action="carWrite.do" method="post" enctype="multipart/form-data">
	<ul>
		<li>
			<label for="car_maker">제조사</label>
			<input type="text" name="car_maker" id="car_maker" maxlength="50" class="input-check">
		</li>
		<li>
			<label for="car_name">차량명</label>
			<input type="text" name="car_name" id="car_name" maxlength="50" class="input-check">
		</li>
		<li>
			<label for="car_size">차종</label>
			<input type="number" name="car_size" id="car_size" min="1" max="6" class="input-check">
		</li>
		<li>
			<label for="car_birth">연식</label>
			<input type="date" name="car_birth" id="car_birth" class="input-check">
		</li>
		<li>
			<label for="car_cc">배기량</label>
			<input type="number" name="car_cc" id="car_cc" class="input-check">
		</li>
		<li>
			<label for="car_fuel_type">연료 타입</label>
			<input type="number" name="car_fuel_type" id="car_fuel_type" min="1" max="4" class="input-check">
		</li>
		<li>
			<label for="car_fuel_efficiency">연비</label>
			<input type="text" name="car_fuel_efficiency" id="car_fuel_efficiency" maxlength="50" class="input-check">
		</li>
		<li>
			<label for="car_mile">주행거리</label>
			<input type="number" name="car_mile" id="car_mile" class="input-check">
		</li>
		<li>
			<label for="car_price">차량 가격</label>
			<input type="number" name="car_price" id="car_price" class="input-check">
		</li>
		<li>
			<label for="car_color">차량 색상</label>
			<input type="text" name="car_color" id="car_color" maxlength="20" class="input-check">
		</li>
		<li>
			<label for="car_photo">차량 사진</label>
			<input type="file" name="car_photo" id="car_photo" accept="image/gif,image/png,image/jpeg" class="input-check" multiple>
		</li>
		<li>
			<label for="car_auto">변속기 타입</label>
			<input type="number" name="car_auto" id="car_auto" min="1" max="2" class="input-check">
		</li>
		<li>
			<label for="car_use">사용기간</label>
			<input type="number" name="car_use" id="car_use" class="input-check">
		</li>
		<li>
			<label for="car_accident">사고이력</label>
			<input type="text" name="car_accident" id="car_accident" maxlength="100" class="input-check">
		</li>
		<li>
			<label for="car_owner_change">소유주 변경 이력</label>
			<input type="number" name="car_owner_change" id="car_owner_change" class="input-check">
		</li>
		<li>
			<label for="car_design_op">디자인 옵션</label>
			<input type="text" name="car_design_op" id="car_design_op" maxlength="400" class="input-check">
		</li>
		<li>
			<label for="car_con_op">편의 옵션</label>
			<input type="text" name="car_con_op" id="car_con_op" maxlength="400" class="input-check">
		</li>
		<li>
			<label for="car_drive_op">주행 옵션</label>
			<input type="text" name="car_drive_op" id="car_drive_op" maxlength="400" class="input-check">
		</li>
	</ul>
	<div class="align-center">
		<input type="submit" value="등록">
		<input type="button" value="차량 등록대기 목록" onclick="location.href=''"> <!-- 검수 차량 목록 -->
	</div>
	</form>
	</div>
</div>
</body>
</html>
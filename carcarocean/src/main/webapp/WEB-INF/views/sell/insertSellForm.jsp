<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>판매 신청서 팝업창</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script>
	/* 제출 버튼 */
	function submitForm() {
	    const myForm = document.getElementById("my_form");
		const data = document.querySelectorAll(".sell-data");
		for(let i=0; i<data.length; i++){
			const label = document.querySelector('label[for="'+data[i].id+'"]');
			if(data[i].value.trim()==''){
				alert(label.textContent+' 항목을 입력해주세요!');
				data[i].value = '';
				data[i].focus();
				return;
			}
			
			/* 차량 번호는 9자리(띄어쓰기 포함) */
			if(data[i].id=='sell_cnumber'){
				if(data[i].value.length!=9){
					alert('차량번호는 000가 0000 형식으로 적어주세요!');
					data[i].value = '';
					data[i].focus();
					return;
				}
			}
			/* 신청자명은 2자에서 4자까지 인정 */
			if(data[i].id=='sell_name'){
				if(data[i].value.length<2 || data[i].value.length>5){
					alert('신청자명은 본명으로 입력해주세요! (3~4자)');
					data[i].value = '';
					data[i].focus();
					return;
				}
			}
			/* 주행거리 숫자만 인정 */
			if(data[i].id=='sell_mile'){
				if(isNaN(data[i].value)){
					alert('주행거리는 숫자로 입력해주세요!');
					data[i].value = '';
					data[i].focus();
					return;
				}
			}
			/* 휴대폰번호는 11자로 된 숫자만 인정 */
			if(data[i].id=='sell_phone'){
				if(isNaN(data[i].value)){
					alert('핸드폰 번호는 숫자로 입력해주세요!');
					data[i].value = '';
					data[i].focus();
					return;
				}
				if(data[i].value.length!=11){
					alert("휴대폰 번호는 '-'없이 11자로 입력해주세요!")
					data[i].value = '';
					data[i].focus();
					return;
				}
			}
		}
		alert('신청이 완료되었습니다!');
		myForm.submit();
		setTimeout(() => {
            window.close();
        }, 1000);
	}
	/* 취소 버튼 */
	function closePopup() {
	    window.close();
	}
	
</script>
</head>
<body>
	<div class="container text-center p-5">
		<h1>내 차 팔기 신청서</h1>
		<form id="my_form" action="insertSell.do" method="post">
			<%-- <input type="hidden" name="mem_num" value="${user_num}"> --%>
			<ul class="list-unstyled">
				<li>
					<label for="sell_cnumber">차량번호</label>
					<input type="text" id="sell_cnumber" name="sell_cnumber" class="sell-data form-control" maxlength="9" placeholder="예) 123가 1234">
				</li>
				<li>
					<label for="sell_maker">제조사</label>
					<input type="text" id="sell_maker" name="sell_maker" class="sell-data form-control" placeholder="예) 기아">			
				</li>
				<li>
					<label for="sell_cname">차량명</label>
					<input type="text" id="sell_cname" name="sell_cname" class="sell-data form-control" placeholder="예) 셀토스">
				</li>
				<li>
					<label for="sell_mile">주행거리</label>
					<input type="text" id="sell_mile" name="sell_mile" class="sell-data form-control" min="0" placeholder="예) 14000">
				</li>
				<li>
					<label for="sell_name">신청자명</label>
					<input type="text" id="sell_name" name="sell_name" class="sell-data form-control" maxlength="4" placeholder="예) 홍길동">
				</li>
				<li>
					<label for="sell_phone">휴대폰번호</label>
					<input type="text" id="sell_phone" name="sell_phone" class="sell-data form-control" maxlength="11" placeholder="예) '-'없이 숫자만 입력. 예)01012345678">
				</li>
			</ul>
			<ul class="list-unstyled">
				<!-- 시/도 선택 시작 -->
				<li>
				<label for="sell_place1">방문진단 요청 지역(시/도)</label>
				<select id="sell_place1" name="sell_place1" class="form-select">
			        <option value="서울특별시">서울특별시</option>
			        <option value="부산광역시">부산광역시</option>
			        <option value="대구광역시">대구광역시</option>
			        <option value="인천광역시">인천광역시</option>
			        <option value="광주광역시">광주광역시</option>
			        <option value="대전광역시">대전광역시</option>
			        <option value="울산광역시">울산광역시</option>
			        <option value="세종특별자치시">세종특별자치시</option>
			        <option value="경기도">경기도</option>
			        <option value="강원도">강원도</option>
			        <option value="충청북도">충청북도</option>
			        <option value="충청남도">충청남도</option>
			        <option value="전라북도">전라북도</option>
			        <option value="전라남도">전라남도</option>
			        <option value="경상북도">경상북도</option>
			        <option value="경상남도">경상남도</option>
			        <option value="제주특별자치도">제주특별자치도</option>
			    </select>
				</li>
				<!-- 시/도 선택 끝 -->
				<!-- 군/구 선택 시작 -->
				<li>
<!-- 시/도 선택하는 거 별로 해당 시/도의 군/구가 떠야함 -->
				<label for="sell_place2">방문진단 요청 지역(군/구)</label>
			    <select id="sell_place2" name="sell_place2" class="form-select">
				    <option selected>일단 서울 고정</option>
				    <option value="종로구">종로구</option>
			        <option value="중구">중구</option>
			        <option value="용산구">용산구</option>
			        <option value="성동구">성동구</option>
			        <option value="광진구">광진구</option>
			        <option value="동대문구">동대문구</option>
			        <option value="중랑구">중랑구</option>
			        <option value="성북구">성북구</option>
			        <option value="강북구">강북구</option>
			        <option value="도봉구">도봉구</option>
			        <option value="노원구">노원구</option>
			        <option value="은평구">은평구</option>
			        <option value="서대문구">서대문구</option>
			        <option value="마포구">마포구</option>
			        <option value="양천구">양천구</option>
			        <option value="강서구">강서구</option>
			        <option value="구로구">구로구</option>
			        <option value="금천구">금천구</option>
			        <option value="영등포구">영등포구</option>
			        <option value="동작구">동작구</option>
			        <option value="관악구">관악구</option>
			        <option value="서초구">서초구</option>
			        <option value="강남구">강남구</option>
			        <option value="송파구">송파구</option>
			        <option value="강동구">강동구</option>
			    </select>
			    <!-- 군/구 선택 끝 -->
				</li>
			</ul>
			<ul class="list-unstyled">
				<li>
					<label>검수 희망일</label>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="sell_date" id="sell_date0" value="0" checked>
					  <label class="form-check-label" for="sell_date0">즉시</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="sell_date" id="sell_date1" value="1">
					  <label class="form-check-label" for="sell_date1">7일 이내</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="sell_date" id="sell_date2" value="2">
					  <label class="form-check-label" for="sell_date2">30일 이내</label>
					</div>
				</li>
			</ul>
			<div class="bg-color-gray">
				<input type="button" class="btn btn-primary" value="제출" onclick="submitForm()">
				<input type="button" class="btn btn-secondary" value="취소" onclick="closePopup()">
			</div>
		</form>
	</div>
</body>
</html>
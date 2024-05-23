<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>판매 신청서 팝업창</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container text-center">
		<h1>내 차 팔기 신청서</h1>
		<div class="main-page">
			<form id="form_box" action="insertSell.do" method="post">
				<input type="hidden" value="${user_num}">
				<ul class="list-unstyled">
					<li>
						<label for="sell_cnumber">차량번호</label>
						<input type="text" id="sell_cnmuber" name="sell_cnumber" class="sell-data" placeholder="예) 123가 1234">
					</li>
					<li>
						<label for="sell_maker">제조사</label>
						<input type="text" id="sell_maker" name="sell_maker" class="sell-data" placeholder="예) 기아">			
					</li>
					<li>
						<label for="sell_cname">차량명</label>
						<input type="text" id="sell_cname" name="sell_cname" class="sell-data" placeholder="예) 셀토스 시그니처">
					</li>
					<li>
						<label for="sell_mile">주행거리</label>
						<input type="text" id="sell_mile" name="sell_mile" class="sell-data" placeholder="예) 14,000">
					</li>
					<li>
						<label for="sell_name">신청자명</label>
						<input type="text" id="sell_name" name="sell_name" class="sell-data" placeholder="예) 홍길동">
					</li>
					<li>
						<label for="sell_phone">휴대폰번호</label>
						<input type="text" id="sell_phone" name="sell_phone" class="sell-data" placeholder="예) '-'없이 숫자만 입력. 예)01012345678">
					</li>
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
					<li>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="0">
						  <label class="form-check-label" for="inlineRadio1">즉시</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="1">
						  <label class="form-check-label" for="inlineRadio2">7일 이내</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="2">
						  <label class="form-check-label" for="inlineRadio3">30일 이내</label>
						</div>
					</li>
				</ul>
				<div class="bg-color-gray">
					<input type="submit" class="btn btn-primary" value="제출">
					<input type="button" class="btn btn-secondary" onclick="#" value="취소">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
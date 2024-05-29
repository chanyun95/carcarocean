$(function(){
	/*============================
	 * 신고선택 여부와 선택한 총 개수 읽기
	 * =========================== */
	function selectReport(){
		//서버와 통신
		$.ajax({
			url:'getReport.do',
			type:'post',
			data:{board_num:$('#output_report').attr('data-num')},
			dataType:'json',
			success:function(param){
				displayReport(param);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	/*============================
	 * 신고 등록(및 삭제) 이벤트 연결
	 * =========================== */
	$('#output_report').click(function(){
		//서버와 통신
		$.ajax({
			url:'writeReport.do',
			type:'post',
			data:{board_num:$(this).attr('data-num')},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 신고를 눌러주세요!');
				}else if(param.result == 'success'){
					displayReport(param);
				}else if(param.result == 'alreadyCheck'){
					alert('이미 신고된 글입니다.');
				}else{
					alert('신고 등록/삭제 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	/*============================
	 * 신고 표시 함수
	 * =========================== */
	function displayReport(param){
		let output;
		if(param.status == 'yesReport'){
			output = '../images/report01.png';
		}else{
			output = '../images/report01.png';
		}
		//문서 객체에 설정
		$('#output_report').attr('src',output);
		$('#output_reportcount').text(param.count);
	}
	/*============================
	 * 초기 데이터 호출
	 * =========================== */
	selectReport();
});






<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>채팅</title>
</head>
<body>
	<div class="container">
		<h2 class="text-center">채팅 연습</h2>
		<hr>
		
		<div class="border rounded" id="chatRoom">
			
		</div>
		
		<hr>
		<form id="form">
			<label for="message">채팅</label>
			<input type="text" class="form-control" id="message" name="message">
			<input type="button" class="btn btn-warning btn-lg text-white fw-bold" onclick="submit_btn()" value="전송">
		</form>
	</div>
	<script>
		window.onload = function(){
			getChat(${item_num},${chat_receiver},${chat_giver});
		};
		
		function submit_btn(){
			$.ajax({
				url:'chatAjax.do',
				type:'get',
				data:{item_num:"${item_num}",message:$('#message').val(),chat_receiver:"${chat_receiver}",chat_giver:"${chat_giver}"},
				dataType:'json',
				success:function(param){
					$('#message').val('').focus();
					getChat(param.item_num, param.chat_receiver, param.chat_giver);
				}
			});
		};
		function getChat(item_num,chat_receiver,chat_giver){
			const chatRoom = document.getElementById('chatRoom');
			$('#chatRoom').empty();
			$.ajax({
				url:'getChatAjax.do',
				type:'get',
				data:{item_num:item_num,chat_receiver:chat_receiver,chat_giver:chat_giver},
				dataType:'json',
				success:function(param){
					for(let i=0; i<param.chatList.length; i++){
						const chat = param.chatList[i];
						//내가 받은 채팅임
						if(chat.receiver.mem_num==${user_num}){
							const newDiv = document.createElement('div');
							newDiv.className = 'border rounded-4 bg-warning text-white fw-bold text-start';
							const newText = document.createTextNode(param.receiver.mem_id+'님 : ' + chat.chat_message);
							newDiv.appendChild(newText);
							chatRoom.appendChild(newDiv);
						}
						
						//내가 보낸 채팅임
						else if(chat.giver.mem_num==${user_num}){ // 내가 채팅을 보낸 입장일 때
							const newDiv = document.createElement('div');
							newDiv.className = 'border rounded-4 bg-warning text-white fw-bold text-end';
							const newText = document.createTextNode(param.giver.mem_id+'님 : ' + chat.chat_message);
							newDiv.appendChild(newText);
							chatRoom.appendChild(newDiv);
						}
					}
				}
			});
		};
	</script>
</body>
</html>
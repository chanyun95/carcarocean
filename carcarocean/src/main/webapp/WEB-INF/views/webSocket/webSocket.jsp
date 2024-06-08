<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Socket Example</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container p-5">
		<h1 class="text-center">채팅 연습장</h1>
		<textarea id="messageTextArea" rows="30" cols="50" class="form-control"></textarea>
		<form class="form-control">
			<!-- 송신 메세지를 작성하는 텍스트 박스 -->
			<label for="textMessage">채팅</label>
			<input id="textMessage" type="text" class="form-control">
			<!-- 메시지 송신을 하는 버튼 -->
			<input onclick="sendMessage()" value="Send" type="button" class="btn btn-primary">
			<!-- WebSocket 접속 종료하는 버튼 -->
			<input onclick="disconnect()" value="Disconnect" type="button" class="btn btn-secondary">
		</form>
	</div>
	<script>
		//WebSocketEx 는 프로젝트명
		//WebSocket 는 호스트 명
		//WebSocket 오브젝트 생성 (자동으로 접속 시작한다 - onopen 함수 호출)
		var webSocket = new WebSocket("ws://localhost:8080/carcarocean/websocket");
		//콘솔 텍스트 에리어 오브젝트
		var messageTextArea = document.getElementById("messageTextArea");
		//WebSocket 서버와 접속이 되면 호출되는 함수
		webSocket.onopen = function(message){
			//콘솔 텍스트에 메시지를 출력한다.
			messageTextArea.value += "서버 연결 시작\n";
		};
		//WebSockcet 서버와 접속이 끊기면 호출되는 함수
		webSocket.onclose = function(message){
			//콘솔 텍스트에 메시지를 출력한다.
			messageTextArea.value += "서버 연결 종료\n";
		};
		//WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
		webSocket.onerror = function(message){
			//콘솔 텍스트에 메시지를 출력한다.
			messageTextArea.value += "서버 에러 발생\n";
		};
		//WebSocket 서버로 부터 메시지가 오면 호출되는 함수
		webSocket.onmessage = function(message){
			//콘솔 텍스트에 메시지를 출력한다.
			messageTextArea.value += "서버로 부터 온 메세지 => " + message.data + "\n";
		};
		
		//send 버튼을 누르면 호출되는 함수
		function sendMessage(){
			//송신 메시지를 작성하는 텍스트 박스 오브젝트를 취득한다.
			var message = document.getElementById("textMessage");
			//콘솔 텍스트에 메시지를 출력한다.
			messageTextArea.value += "서버에 전송하는 메세지 => " + message.value + "\n";
			//WebSocket 서버에 메시지를 송신한다.
			webSocket.send(message.value);
			//송신 메시지를 작성하는 텍스트 박스를 초기화한다.
			message.value ="";
		};
		
		//Disconnect 버튼을 누르면 호출되는 함수
		function disconnect(){
			webSocket.close();
		};
	</script>
</body>
</html>
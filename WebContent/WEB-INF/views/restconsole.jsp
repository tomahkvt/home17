<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta charset = utf-8>
<title>Активная консоль</title>
<link rel = stylesheet href="css/console.css">
<script src="js/jquery-2.1.4.min.js"></script>
</head>
<body>
<a href="/home17">Вернуться</a><BR>
Вы авторизировались как  <security:authentication property="principal.username" />!
<a href="logout" style="float: right;">logout</a><br>
		<script type="text/javascript">
		//Add macro
		var url = 'ws://localhost:8080/home17/marco';
		console.log(url);
		var sock = new WebSocket(url);

		$(document)
				.ready(
						function() {
							$('#control').children().prop('disabled', true);
							
							$("#label_status").text("Disconected");
							$("#console_log")
									.change(
											function() {

												var psconsole = $('#console_log');
												if (psconsole.length)
													psconsole
															.scrollTop(psconsole[0].scrollHeight
																	- psconsole
																			.height());
											});

							sock.onopen = function() {
								$('#control').children().prop('disabled', false);
								$('#bt_pause').prop('disabled', true);
								$('#bt_stop').prop('disabled', true);
								$("#label_status").text("Connected");
								console.log('Opening');
								sayMarco();
							}

							sock.onmessage = function(e) {
								console.log('Received message: ', e.data);
								$('#console_log').append(
										'Received "' + e.data + '"&#10;');
								setTimeout(function() {
									sayMarco()
								}, 500);

							}

							sock.onclose = function() {
								console.log('Closing');
								$('#control').children().prop('disabled', true);
								$("#label_status").text("Disconected");
							}

							function sayMarco() {
								/*
								console.log('Sending Marco!');
								$('#console_log').append('Sending "Marco!"<br/>');
								sock.send("Marco!");
								*/
								var psconsole = $('#console_log');
								if (psconsole.length)
									psconsole
											.scrollTop(psconsole[0].scrollHeight
													- psconsole
															.height());
							}

							$('#bt_stop').click(function() {
								$.get('api/log/stop');
								//sock.close()
								$('#bt_stop').prop('disabled', true);
								$('#bt_start').prop('disabled', false);
								$('#bt_pause').prop('disabled', false);
								$('#bt_pause').html('pause');
							});
							
							$('#bt_start').click(function() {
								$.get('api/log/start');
								$('#bt_stop').prop('disabled', false);
								$('#bt_pause').prop('disabled', false);
								$('#bt_start').prop('disabled', true);
								
							});
							$('#bt_pause').click(function() {
								
								
								$.get( "api/log/pause", function( data ) {
										$('#bt_pause').html(data);							  

									});
								
								
							
							
								});
								
							});
							

						
	</script>

    <h1>Страница активной консоли</h1>
    <div id="control">
    Status:<br>
    <label id="label_status"></label><br><br>
	<button id="bt_start">Start</button><br><br>
	<button id="bt_pause">Pause</button><br><br>
	<button id="bt_stop">Stop</button>
    </div>
    <div id="div_console_log">
	<textarea id="console_log" name="comment" cols="150" rows="40"></textarea>
    </div>


</body>
</html>

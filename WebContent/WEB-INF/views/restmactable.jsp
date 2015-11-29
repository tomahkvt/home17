<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Welcome</title>


<script src="js/jquery-2.1.4.min.js"></script>
<script
	src="js/DataTables-1.10.9/media/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="js/DataTables-1.10.9/media/css/jquery.dataTables.min.css">
<script src="js/restmactable.js"></script>

</head>

<body>
<a href="/home17">Вернуться</a><BR>
Вы авторизировались как  <security:authentication property="principal.username" />!
<a href="logout" style="float: right;">logout</a><br>
<fieldset >
    <legend>Дополнительная информация</legend>
    <input type="checkbox" id="check_arp_static">
    <label for="check_arp_static">Arp Static</label><br>
    <input type="checkbox" id="check_dhcp_table">
    <label for="check_dhcp_table">Dhcp Table</label><br>
    <button id = "button_refresh">Обновить</button>
</fieldset>    
	<table id="mac_table" class="display">
		<thead>
			<tr>
				<th>Device Ip</th>
				<th>Device Name</th>
				<th>Device Port</th>
				<th>MAC</th>
				<th>Time collection</th>
			</tr>
		</thead>


		<tbody>

		</tbody>
	</table>


</body>

</html>
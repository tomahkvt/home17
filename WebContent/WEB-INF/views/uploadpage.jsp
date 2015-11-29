<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>
<a href="/home17">Вернуться</a><BR>
Вы авторизировались как  <security:authentication property="principal.username" />!
<a href="logout" style="float: right;">logout</a><br>
	Файл должен иметь формат<BR>
	IP-адрес клиента,Имя,Истечение срока аренды,Тип,Уникальный код,Описание,Защита доступа к сети,Срок действия надзора,Профиль фильтра,Политика<BR>
	
	<form method="POST" action="uploadFile" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /> 
		<br /> 
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
	
</body>
</html>
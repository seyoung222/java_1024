<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  안녕하세요. 제 이름은 ${name1}입니다. </P>
<p> 제 나이는 ${age}살입니다. </p>
<form action="/spring/" method="post"> 
<!-- action은 아예 비우거나, /spring을 통째로 같이 써야함 -->
	<input type="text" name="name"> <br>
	<input type="text" name="age"> <br>
	<button>전송</button>
</form>
</body>
</html>

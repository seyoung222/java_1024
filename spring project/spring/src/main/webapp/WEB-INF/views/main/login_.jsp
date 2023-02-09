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
	로그인
</h1>

<form action="/spring/login" method="post">
<!-- action을 입력해야 input으로 입력한 값을 받아올 수 있음!! -->
	<input type="text" name="id" placeholder="id">
	<input type="password" name="pw" placeholder="pw">
	<button>로그인</button>
</form>
</body>
</html>

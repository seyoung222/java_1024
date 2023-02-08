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

${info1} <!-- 하면 InfoVO [name=홍길동, num=24] -->
<!-- 필드 이름을 썼을 때 집접 호출하는게 아니라 getter를 호출
sxxx.name을 쏘면 xxx.getName()이 호출된다 -->
${info1.name}, ${info1.num}
</body>
</html>

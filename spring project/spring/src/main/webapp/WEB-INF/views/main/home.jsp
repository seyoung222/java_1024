<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container-fluid">
	<h1>스프링</h1>
	
	<a href="/spring/ex1">데이터 전송 예제1</a><br>
	-> 링크를 누르면 해당 링크값을 보내옴. 
	-> /spring/을 꼭 써야함.. 값은 없기 때문에 null로 받아들임 <br>
	<a href="/spring/ex2">데이터 전송 예제2</a> <br>
	<a href="/spring/ex3">데이터 전송 예제3</a> <br>
	<a href="/spring/ex4">데이터 전송 예제4</a> <br>
	<a href="/spring/ex5?num=2022123001">DB 연결 예제5</a> <br>
</div>


<!-- 
<h1>
	Hello world!  
</h1>
<P>  안녕하세요. 제 이름은 ${name1}입니다. </P>
<p> 제 나이는 ${age}살입니다. </p>
<form action="/spring/" method="post"> 
<!-- action은 아예 비우거나, /spring을 통째로 같이 써야함 
	<input type="text" name="name"> <br>
	<input type="text" name="age"> <br>
	<button>전송</button>
</form>
 -->
</body>
</html>

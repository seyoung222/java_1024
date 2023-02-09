<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container-fluid">
	<h1>예제1</h1>
	<p>화면에서 서버로 데이터를 get방식으로 전달하는 예제</p>
	<h2>예제 1-1</h2>
	<a href="/spring/ex1?name=홍길동">서버로 홍길동 전송</a><br>
	-> 링크를 누르면 name=홍길동이라는 데이터를 보내옴
	<h2>예제1-2</h2>
	<form action="/spring/ex1" method="get">
		<input type="text" name="name"> 
		<input type="text" name="age"> 
		<button>전송</button> <br>
		name명이 변수 이름이 됨 -> input으로 적은 값이 name,age의 값으로 저장되어 get방식으로 보내짐
	</form>
</div>
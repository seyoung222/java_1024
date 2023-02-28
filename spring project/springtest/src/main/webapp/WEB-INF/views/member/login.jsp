<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	label.error{color:red;}
</style>
<h1>로그인</h1>
<form action="<c:url value='/login'></c:url>" method="post">
	<div class="form-group">
	  <label for="me_id">아이디:</label>
	  <input type="text" class="form-control" id="id" name="me_id">
	</div>
	<div class="form-group">
	  <label for="me_pw">비밀번호:</label>
	  <input type="password" class="form-control" id="pw" name="me_pw">
	</div>
	<button class="btn btn-outline-success col-12">로그인</button>
</form>	
<script>
$('#id').focus();
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="<c:url value='/resources/css/summernote-bs4.min.css'></c:url>" rel="stylesheet">
<script src="<c:url value='/resources/js/summernote-bs4.min.js'></c:url>"></script>
<h1>게시글 작성</h1>

<form action="<c:url value='/board/insert'></c:url>" method="post" enctype="multipart/form-data">
	<div class="form-group">
		<label>타입</label>
		<select name="bo_bt_num" class="form-control">
			<option value="0">게시판을 선택하세요.</option>
			<c:forEach items="${typeList}" var="type">
				<option value="${type.bt_num}">${type.bt_name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label for="title">제목:</label>
		<input type="text" class="form-control" id="title" name="bo_title">
	</div>
	<div class="form-group">
		<label for="content">내용:</label>
		<textarea id="content" name="bo_content"></textarea>
	</div>
	<div class="form-group">
		<label for="file">첨부파일:</label>
		<input type="file" class="form-control" name="files">
		<input type="file" class="form-control" name="files">
		<input type="file" class="form-control" name="files">
	</div>
	<button class="col-12 btn btn-outline-success mt-5 mb-2">작성</button>
</form>
<a class="btn btn-outline-primary" href="<c:url value='board/list'></c:url>">목록</a>
<script>
$('[name=bo_content]').summernote({
	placeholder: '내용을 입력하세요.',
	tabsize: 2,
	height: 300
});
$('form').submit(function(){
	let type=$('[name=bo_bt_num]').val();
	if(type==0){
		alert('게시판을 선택하세요.');
		return false;
	}
	let title=$('[name=bo_title]').val();
	if(title.trim().length == 0){
		alert('제목을 입력하세요.');
		return false;
	}
	let content = $('[name=bo_content]').summernote('code');
	if(content.trim().length==0){
		alert('내용을 입력하세요.');
		return false;
	}
});
</script>
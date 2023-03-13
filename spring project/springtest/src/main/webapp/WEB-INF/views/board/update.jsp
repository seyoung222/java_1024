<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="<c:url value='/resources/css/summernote-bs4.min.css'></c:url>" rel="stylesheet">
<script src="<c:url value='/resources/js/summernote-bs4.min.js'></c:url>"></script>
<h1>게시글 수정</h1>

<form action="<c:url value='/board/update/${board.bo_num}'></c:url>" method="post" enctype="multipart/form-data">
	<div class="form-group">
		<label>타입</label>
		<select name="bo_bt_num" class="form-control">
			<option value="0">게시판을 선택하세요.</option>
			<c:forEach items="${btList}" var="type">
				<option value="${type.bt_num}" <c:if test="${type.bt_num==board.bo_bt_num}">selected</c:if>>${type.bt_name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label for="title">제목:</label>
		<input type="text" class="form-control" id="title" name="bo_title" value="${board.bo_title}">
	</div>
	<div class="form-group">
		<label for="content">내용:</label>
		<textarea id="content" name="bo_content"></textarea>
	</div>
	<div class="form-group file-group">
		<label>첨부파일:</label>
		<c:forEach items="${fileList}" var="file">
			<a href="<c:url value='file/${file.fi_name}'></c:url>" download="${file.fi_ori_name}" class="form-control mb-1" style="position:relative;">
				${file.fi_ori_name}
				<span class="btn-times" style="position:absolute;top:0;right:0;bottom:0;width:30px;line-height:36px;text-align:center;" data-num="${file.fi_num}">X</span>
			</a>
		</c:forEach>
		<c:forEach begin="1" end="${3- fileList.size()}">
			<input type="file" class="form-control mb-1" name="files">
		</c:forEach>
	</div>
	<button class="col-12 btn btn-outline-success mt-5 mb-2">수정완료</button>
</form>
<script>
$('[name=bo_content]').summernote({
	placeholder: '내용을 입력하세요.',
	tabsize: 2,
	height: 300
});
$('[name=bo_content]').summernote('code','${board.bo_content}');


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

$('.btn-times').click(function(e){
	e.preventDefault();
	$(this).parents('.file-group').append('<input type="file" class="form-control mb-1" name="files">');
	$(this).parents('.file-group').append('<input type="hidden" name="fileNums" value="'+$(this).data('num')+'">');
	$(this).parent().remove();
});
</script>

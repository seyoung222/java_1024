<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>게시글 상세</h1>
<div class="form-group">
	<label>제목</label>
	<div class="form-control">${board.bo_title }</div>
</div>
<div class="form-group">
	<label>게시판</label>
	<div class="form-control">${board.bt_name }</div>
</div>
<div class="form-group">
	<label>작성자</label>
	<div class="form-control">${board.bo_me_id }</div>
</div>
<div class="form-group">
	<label>작성일</label>
	<div class="form-control">${board.bo_register_date_str }</div>
</div>
<c:if test="${board.bo_update_date != null }">
	<div class="form-group">
		<label>수정일</label>
		<div class="form-control">${board.bo_update_date_str }</div>
	</div>
</c:if>
<div class="form-group">
	<label>조회수</label>
	<div class="form-control">${board.bo_views }</div>
</div>
<div>
	<button class="btn btn-outline-success btn-up" data-state="1">추천(${board.bo_up})</button>
	<button class="btn btn-outline-danger btn-down" data-state="-1">비추천(${board.bo_down})</button>
</div>
<div class="form-group">
	<label>내용</label>
	<div class="form-control" style="min-height: 400px">${board.bo_content}</div>
</div>
<c:if test="${fileList != null && fileList.size() != 0 }">
	<div class="form-group">
		<label>첨부파일</label>
		<c:forEach items="${fileList}" var="file">
			<a class="form-control" href="<c:url value='/file${file.fi_name}'></c:url>" download="${file.fi_ori_name}" >${file.fi_ori_name}</a>
		</c:forEach>
	</div>
</c:if>
<a class="btn btn-success" href="<c:url value='/board/list'></c:url>">목록</a>
<c:if test="${user!=null && user.me_id==board.bo_me_id}">
	<form action="<c:url value='/board/delete/${board.bo_num}'></c:url>" method="post" style="display:inline-block;">
		<button class="btn btn-outline-danger">삭제</button>
	</form>
	<a href="<c:url value='/board/update/${board.bo_num}'></c:url>" class="btn btn-outline-primary">수정</a>
</c:if>
<script>
$('.btn-up', '.btn-down').click(function(){
	let li_state = $(this).date('state');
	let bo_num = '${board.bo_num}';
	let url = '<c:url value="/board/like/"></c:url>'+bo_num+'/'+li_state;
		
	if($(this).hasClass('.btn-down'))
		li_state = -1;
    $.ajax({
        async:true, //asyne는 비동기라는 뜻 = 서버의 요청을 기다리지 않고 다음 작업을 함.
        //추천은 비동기로 해도되는데 회원가입은 중복검사 때문에 동기로 해야함.(결과를 받기전에 진행하면 안됨)
        type:'GET',
        //data : 서버에 보낼 데이터. 지금은 url에 있어서 ㄱㅊ음
        url: url,
        dataType:"json",//여기 data는 서버에서 보낸 데이터 타입. Map으로 받을거라 json으로 해야함
		//contentType:"application/json; charset=UTF-8",
        success : function(data){ 
            $('.btn-up').removeClass('.btn-success').addClass('.btn-outlint-success');
            $('.btn-down').removeClass('.btn-danger').addClass('.btn-outlint-danger');
            if(data.res==1){
            	alert('추천 했습니다.');
	            $('.btn-up').addClass('.btn-success').removeClass('.btn-outlint-success');
            }else if(data.res==-1){
            	alert('비추천 했습니다.')
            	$('.btn-up').addClass('.btn-danger').removeClass('.btn-outlint-danger');
            }else{
            	if(li_state==1)
            		alert('추천을 취소했습니다.')
            	else
            		alert('비추천을 취소했습니다.')
            }
        }
    });
})
</script>
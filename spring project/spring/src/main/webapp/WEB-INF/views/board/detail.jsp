<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<c:url value='/resources/css/summernote-bs4.min.css'></c:url>" rel="stylesheet">
<script src="<c:url value='/resources/js/summernote-bs4.min.js'></c:url>"></script>
<div class="container">
	<h1>게시글 확인</h1>
	<div class="form-group">
		<label >게시판:</label>
		<div class="form-control" readonly>${board.bt_name}</div>
	</div>
	<div class="form-group">
		<label>제목:</label>
		<div class="form-control">${board.bo_title}</div>
	</div>
	<div class="form-group">
		<label>작성자:</label>
		<div class="form-control">${board.bo_me_id}</div>
	</div>
	<div class="form-group">
		<label >작성일:</label>
		<div class="form-control">${board.bo_register_date_str}</div>
	</div>
	<div class="form-group">
		<label>조회수:</label>
		<div class="form-control">${board.bo_views}</div>
	</div>
	<c:if test="${board.bt_type == '일반'}">
		<div id="common">
			<div class="form-group">
				<label for="content">내용:</label>
				<div class="form-control" style="min-height:300px;">${board.bo_content}</div>
			</div>
			<c:if test="${file.size() != 0}">
				<div class="form-group mt-3">
					<label>첨부파일:</label>
					<c:forEach items="${files}" var="file">
						<a class="form-control" href="<c:url value='/download${file.fi_name}'></c:url>" download="${file.fi_ori_name}">${file.fi_ori_name}</a>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</c:if>
	<div id="image" style="display: none">
		<div class="form-group mt-3">
			<label>첨부파일:</label>
			<input type="file" class="form-control" name="files" accept="image/*">
			<input type="file" class="form-control" name="files" accept="image/*">
			<input type="file" class="form-control" name="files" accept="image/*">
		</div>
	 </div>
	 <div class="pagination justify-content-center" style="margin:20px 0">
	 	<button class="btn btn-outline-success btn-up">추천</button>
	 	<button class="btn btn-outline-danger btn-down">비추천</button>
	 </div>
</div>
<script>
$(function(){
	// 추천버튼을 누르면 로그인하도록.. 예 누르면 로그인화면, 아니오 누르면 그 페이지 그대로있도록
	$('.btn-up, .btn-down').click(function(){
		if('${user}'==''){
			let res = confirm('로그인한 회원만 추천을 할 수 있습니다. \n로그인 페이지로 이동하겠습니까?');
			if(res){
				location.href="<c:url value='/login'></c:url>";
			}
		}
		let li_state = 1; //기본 추천으로 설정하고 비추천버튼이 눌리면 -1로 바뀌도록.. 눌렀다 취소했다 기능 똑같이 함
		if($(this).hasClass('btn-down'))
			li_state = -1;
		//ajx를 이용하여 추천/비추천 작업
		$.ajax({
	        //비동기화 : 사용
			//동기화는 ajax작업이 다 끝난 후 아래 코드가 실행
			//비동기화는 ajax가 작업이 끝나든말든 아래 코드가 실행(기다리지말고 다음 작업하세요)
			async:true,
	        type:'GET',
	        //data:JSON.stringify(obj), //원래는 POST에 이렇게 JSON 명령어에 객체를 담아서 처리하는데 이건 간단히 GET으로 함
	        url:"<c:url value='/board/like/"+li_state+"/${board.bo_num}'></c:url>",
	        dataType:"json", //서버에서 받는 데이터 타입
	        //contentType:"application/json; charset=UTF-8", //서버에서 보내는 데이터 타입
	        success : function(data){
	            console.log(data);
	        }
	    });
		//
		//console.log(date);
	})
});
</script>
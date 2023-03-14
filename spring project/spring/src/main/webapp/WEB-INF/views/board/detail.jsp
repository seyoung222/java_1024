<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<c:url value='/resources/css/summernote-bs4.min.css'></c:url>" rel="stylesheet">
<script src="<c:url value='/resources/js/summernote-bs4.min.js'></c:url>"></script>
<!-- Link Swiper's CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
<!-- Swiper JS -->
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<style>
.swiper {
      width: 100%;
      height: 100%;
    }

    .swiper-slide {
      text-align: center;
      font-size: 18px;
      background: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .swiper-slide2 img {
      display: block;
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
</style>
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
	<c:if test="${board.bt_type == '이미지'}">
		<div id="image">
			<div class="form-group mt-3">
				<label>이미지:</label>
				<div class="swiper mySwiper">
				    <div class="swiper-wrapper">
						<c:forEach items="${files}" var="file">
						    <div class="swiper-slide">
						    	<img src="<c:url value='/download${file.fi_name}'></c:url>" height="200" width="auto">
						    </div>
						</c:forEach>
				    </div>
				    <div class="swiper-button-next"></div>
				    <div class="swiper-button-prev"></div>
				    <div class="swiper-pagination"></div>
				</div>
			</div>
		 </div>
	</c:if>
	<div class="pagination justify-content-center" style="margin:20px 0">
	 	<c:if test="${likes!=null && likes.li_state==1 }">
		 	<button class="btn btn-success btn-up">추천</button>
	 	</c:if>
	 	<c:if test="${likes==null || likes.li_state!=1 }">
		 	<button class="btn btn-outline-success btn-up">추천</button>
	 	</c:if>
	 	<c:if test="${likes!=null && likes.li_state==-1 }">
	 		<button class="btn btn-danger btn-down ml-2">비추천</button>
	 	</c:if>
	 	<c:if test="${likes==null || likes.li_state!=-1 }">
	 		<button class="btn btn-outline-danger btn-down ml-2">비추천</button>
	 	</c:if>
	 </div>
	<c:if test="${user!=null && user.me_id == board.bo_me_id}">
		<div>
			<a href="<c:url value='/board/update/${board.bo_num}'></c:url>">
				<button class="btn btn-outline-primary btn-update">수정</button>
			</a>	
			<a href="<c:url value='/board/delete/${board.bo_num}'></c:url>">
				<button class="btn btn-outline-primary btn-delete">삭제</button>
			</a>
		</div>
	</c:if>
	<c:if test="${board.bo_num == board.bo_ori_num }">
		<a href="<c:url value='/board/insert?bo_ori_num=${board.bo_num}'></c:url>">
			<button class="btn btn-outline-primary btn-reply">답글</button>
		</a>
	</c:if>
	<!-- 댓글 -->
	<div class="comment-list mt-4">
		
	</div>
	<div class="comment-pagination mt-2">
		
	</div>
	<div class="comment-box mt-2">
		 <div class="input-group mb-3">
		 	<textarea class="form-control" placeholder="댓글을 입력하세요." name="co_content"></textarea>
		 	<div class="input-group-append">
		 		<button class="btn btn-success btn-comment-insert" type="submit">댓글 등록</button>
		 	</div>
		 </div>
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
	        	//추천 버튼 초기 상태로
	        	$('.btn-up').removeClass('btn-success').addClass('btn-outline-success');
	        	//비추천 버튼 초기 상태로
	        	$('.btn-down').removeClass('btn-danger').addClass('btn-outline-danger');
	        	if(data.res==1){
	        		alert('추천 했습니다.');
		        	$('.btn-up').addClass('btn-success').removeClass('btn-outline-success');
	        	}else if(data.res==-1){
	        		alert('비추천 했습니다.')
		        	$('.btn-down').addClass('btn-danger').removeClass('btn-outline-danger');
	        	}else{
	        		if(li_state==1)
	        			alert('추천을 취소했습니다.');
	        		else
	        			alert('비추천을 취소했습니다.');
	        	}
	        }
	    });
		//
		//console.log(data);
	})
});
var swiper = new Swiper(".mySwiper", {
	navigation: {
		nextE1: ".swiper-button-next",
		prevE1: ".swiper-button-prev",
	},
    pagination: {
      el: ".swiper-pagination",
    },
    loop: true,
  });
</script>
<script>
$('.btn-comment-insert').click(function(){
	//로그인 여부 체크
	if('${user.me_id}'==''){
		alert('로그인하세요.');
		return;
	}
	//ajax를 이용하여 댓글 등록
	//-> 댓글 정보를 가진 객체를 생성
	let co_content = $('[name=co_content]').val();
	if(co_content.trim().length == 0){
		alert('내용을 입력하세요.');
		return;
	}
	let comment = {
			co_content : co_content,
			co_bo_num : '${board.bo_num}'
	}
	ajax('POST', comment, '<c:url value="/comment/insert"></c:url>', 
		function(data){
			console.log(data);
		}
	)
});
function ajax(method, obj, url, successFunc, errorFunc){
	$.ajax({
        async:false,
        type: method,
        data: JSON.stringify(obj),
        url: url,
        dataType:"json", //success에 있는 data타입(주는거)
        contentType:"application/json; charset=UTF-8", //위에있는 data타입(받는거)
        success : successFunc
	});
}
</script>
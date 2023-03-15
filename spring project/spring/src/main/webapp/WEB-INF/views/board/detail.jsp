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
	<div class="comment-list mt-4 border rounded-sm border-success">
		<div class="comment-row p-3">
			<div class="co_me_id">qwe123</div>
			<div class="co_content">11</div>
			<div class="co_register_date">2023-03-15</div>
			<button class="btn btn-outline-success btn-reply">답글</button>
			<button class="btn btn-outline-success btn-update">수정</button>
			<button class="btn btn-outline-success btn-delete">삭제</button>
			<div class="input-group mb-3 mt-3 reply-box">
			 	<textarea class="form-control" placeholder="답글을 입력하세요." name="co_content_reply"></textarea>
			 	<div class="input-group-append">
			 		<button class="btn btn-success btn-reply-insert" type="submit">답글 등록</button>
			 	</div>
			 </div>
			<hr>
		</div>
	</div>
	<ul class="comment-pagination mt-2 pagination justify-content-center">
		<li class="page-item" data-page=""><a class="page-link" href="#">이전</a></li>
		<li class="page-item" data-page=""><a class="page-link" href="#">1</a></li>
		<li class="page-item" data-page=""><a class="page-link" href="#">다음</a></li>
	</ul>
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
//댓글등록버튼클릭
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
			co_bo_num : bo_num
	}
	insertComment(comment);
});
/* 댓글 페이지네이션 이벤트  
: 페이지네이션은 js로 등록되었기 때문에 그냥 .click으로 등록하면 이벤트 일어나지 않음
  => document를 써서 항시 이벤트 발생하도록 해야함. */
$(document).on('click', '.comment-pagination a', function(e){
	e.preventDefault();
	page = $(this).parent().data('page');
	selectCommentList(page, bo_num);
});
/* 댓글 삭제버튼 클릭 이벤트 */
$(document).on('click', '.comment-list .btn-delete', function(){
	//댓글 번호 확인
	let co_num = $(this).data('num');
	let comment = {
			co_num : co_num
	}
	//deleteComment 호출
	deleteComment(comment,page); //이 때 page는 전역변수 page
});
/* 댓글 답글버튼 클릭 이벤트 */
$(document).on('click','.comment-list .btn-reply',function(){
	if('${user.me_id}' ==''){
		alert('로그인을 해야합니다.');
		return;
	}
	let co_ori_num = $(this).data('num');
	let str = '';
	str +=
	'<div class="input-group mb-3 mt-3 reply-box">'+
	 	'<textarea class="form-control" placeholder="답글을 입력하세요." name="co_content_reply"></textarea>'+
	 	'<div class="input-group-append">'+
	 		'<button class="btn btn-success btn-reply-insert" type="submit" data-num="'+co_ori_num+'">답글 등록</button>'+
	 	'</div>'+
	 '</div>';
	 initCommentBox();
	 $(this).parent().siblings('hr').before(str);
	 //답글버튼 누르면 본인과 그 옆 버튼들도 안보이게 처리
	 $(this).hide().siblings('button').hide(); 
	 
	 
})
/* 답댓글 등록 */
$(document).on('click', '.btn-reply-insert', function(){
	let co_ori_num = $(this).data('num');
	let co_content = $('[name=co_content_reply]').val();
	let comment = {
		co_content: co_content,
		co_ori_num : co_ori_num,
		co_bo_num : bo_num
	 }
	insertComment(comment);
})
/* 수정 버튼 클릭 이벤트 */
$(document).on('click','.btn-update',function(){
	initCommentBox();
	let co_content = $(this).siblings('.co_content').text();
	let str = '';
	let co_num = $(this).data('num');
	str +=
	'<div class="input-group mb-3 box-co_content">'+
	 	'<textarea class="form-control" placeholder="댓글을 입력하세요." name="co_content_update">'+co_content+'</textarea>'+
	 	'<div class="input-group-append">'+
	 		'<button class="btn btn-success btn-comment-update" type="submit" data-num="'+co_num+'">댓글 수정</button>'+
	 	'</div>'+
	'</div>';
	$(this).siblings('.co_content').after(str);
	$(this).siblings('.co_content').hide();
	$(this).hide().siblings('button').hide();
})
/* 댓글 수정 버튼 클릭이벤트 */
$(document).on('click', '.btn-comment-update',function(){
	let co_num = $(this).data('num');
	let co_content = $('[name=co_content_update]').val();
	//comment 객체 생성
	let comment = {
			co_num : co_num,
			co_content : co_content
	}
	//updateComment메소드를 추가 및 호출
	updateComment(comment, page);
});

const bo_num = '${board.bo_num}';
let page = 1; //댓글 페이지
selectCommentList(1, bo_num);

//댓글 수정하는 함수
function updateComment(comment, page){
	ajax('POST', comment, '<c:url value="/comment/update"></c:url>', 
		function(data){
			if(data.result){
				alert('댓글을 수정했습니다.');
				//댓글 조회
				selectCommentList(page, bo_num);
			}else{
				alert('댓글 수정에 실패했습니다');
			}
		}
	)
}
//답글,수정버튼을 누르기 전 초기 세팅으로 돌려주는 함수
function initCommentBox(){
	$('.reply-box').remove(); //다른 댓글의 답글을 누르면 누르던 답글창이 사라지게함
	$('.comment-row button').show();
	$('.box-co_content').remove();
	$('co_content').show();
}
//댓글 삭제하는 함수
function deleteComment(comment, page){
	ajax('POST', comment, '<c:url value="/comment/delete"></c:url>', 
		function(data){
			if(data.result){
				alert('댓글을 삭제했습니다.');
				//댓글 조회
				selectCommentList(page, bo_num);
			}else{
				alert('댓글 삭제에 실패했습니다');
			}
		}
	)
}
//페이지네이션에 따라 댓글을 보여주는 함수
function selectCommentList(page,bo_num){
	//현재 페이지 정보
	let cri = {
			page : page
	}
	ajax('POST', cri, '<c:url value="/comment/list/'+bo_num+'"></c:url>', 
		function(data){
			let str = '';
			let list = data.list;
			for(i=0; i<list.length; i++){
				let pl = 0;
				if(list[i].co_num != list[i].co_ori_num)
					pl=60;
				str += 
				'<div class="comment-row p-3">'+
				'<div style="padding-left:'+pl+'px">'+
					'<div class="co_me_id">'+list[i].co_me_id+'</div>'+
					'<div class="co_content">'+list[i].co_content+'</div>'+
					'<div class="co_register_date">'+list[i].co_register_date_str+'</div>';
				if(list[i].co_num == list[i].co_ori_num)
					str +=
					'<button class="btn btn-outline-success btn-reply" data-num="'+list[i].co_num+'">답글</button>';
				if('${user.me_id}'== list[i].co_me_id){
					str +=
					'<button class="btn btn-outline-success btn-update ml-2" data-num="'+list[i].co_num+'">수정</button>'+
					'<button class="btn btn-outline-success btn-delete ml-2" data-num="'+list[i].co_num+'">삭제</button>';
				}
				str += 
					'</div>'+
					'<hr>'+
				'</div>';
			}
			$('.comment-list').html(str); 
			str='';
			let pm = data.pm;
			if(pm.prev)
				str +=
				'<li class="page-item" data-page="'+(pm.startPage-1)+'"><a class="page-link" href="#">이전</a></li>'
			for(i=pm.startPage; i<=pm.endPage; i++){
				let active = i==pm.cri.page ? 'active' : ''; //active들어가면 색상들어감
				str +=
				'<li class="page-item '+active+'" data-page="'+i+'"><a class="page-link" href="#">'+i+'</a></li>'
			}
			if(pm.next)
				str +=
				'<li class="page-item" data-page="'+(pm.endPage+1)+'"><a class="page-link" href="#">다음</a></li>'
			$('.comment-pagination').html(str);
		}
	)
}

//댓글 객체가 주어지면 댓글을 등록하는 함수
function insertComment(comment){
	ajax('POST', comment, '<c:url value="/comment/insert"></c:url>', 
		function(data){
			if(data.result){
				alert('댓글을 등록했습니다.');
				//등록 후 댓글 조회 다시 해줘야함
				selectCommentList(1, bo_num);
			}else{
				alert('댓글 등록에 실패했습니다');
			}
		}
	)
}
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
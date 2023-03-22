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
	<c:if test="${like==null || like.li_state != 1}">
		<button class="btn btn-outline-success btn-up" data-state="1">추천(<span class="count">${board.bo_up}</span>)</button>
	</c:if>
	<c:if test="${like!=null && like.li_state == 1}">
		<button class="btn btn-success btn-up" data-state="1">추천(<span class="count">${board.bo_up}</span>)</button>
	</c:if>
	<c:if test="${like==null || like.li_state != -1}">
		<button class="btn btn-outline-danger btn-down" data-state="-1">비추천(<span class="count">${board.bo_down}</span>)</button>
	</c:if>
	<c:if test="${like!=null && like.li_state == -1}">
		<button class="btn btn-danger btn-down" data-state="-1">비추천(<span class="count">${board.bo_down}</span>)</button>
	</c:if>
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
<hr>
<h4>댓글</h4>
<hr>
<div class="comment-list">
	<div class="comment">
		<div class="comment-id">아이디</div>
		<div class="comment-date">시간</div>
		<div class="comment-content">내용</div>
		<div class="btn-group">
			<button type="button" class="btn btn-outline-success btn-reply" data-num="">답글</button>
			<button type="button" class="btn btn-outline-success btn-update" data-num="">수정</button>
			<button type="button" class="btn btn-outline-success btn-delete" data-num="">삭제</button>
		</div>
	</div>
</div>
<ul class="comment-pagination pagination justify-content-center">
	<li class="page-item">
		<a class="page-link" href="#">이전</a>
	</li>
	<li class="page-item">
		<a class="page-link" href="#">1</a>
	</li>
	<li class="page-item">
		<a class="page-link" href="#">다음</a>
	</li>
</ul>
<hr>
<div class="input-group mb-3">
  <textarea class="form-control" placeholder="댓글을 입력하세요." name="co_content"></textarea>
  <div class="input-group-append">
    <button class="btn btn-success btn-comment-insert" type="button">댓글 작성</button>
  </div>
</div>
<script>
$('.btn-up, .btn-down').click(function(){
	if('${user.me_id}'==''){
		alert('로그인한 회원만 추천/비추천 할 수 있습니다.');
		return;
	}
	
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
        	//추천/비추천수 수정
        	$('.btn-up>.count').text(data.board.bo_up);
        	$('.btn-down>.count').text(data.board.bo_down);
        	//추천/비추천상태에 따라 버튼 색 다르게 보이게하기
            $('.btn-up').removeClass('btn-success').addClass('btn-outline-success');
            $('.btn-down').removeClass('btn-danger').addClass('btn-outline-danger');
            //state를 이용하여 알림창 및 버튼 색상 처리
            if(data.state == 1){
            	alert('추천했습니다.');
                $('.btn-up').addClass('btn-success').removeClass('btn-outline-success');
            }else if(data.state == -1){
            	alert('비추천했습니다.');
                $('.btn-down').addClass('btn-danger').removeClass('btn-outline-danger');
            }else{
            	if(li_state==1)
            		alert('추천을 취소했습니다.');
            	else
            		alert('비추천을 취소했습니다.');
            }
        }
    });
})
</script>
<script>
//댓글과 관련된 전역 변수들
const bo_num = '${bo_num}';
let cri = {
		page : 1,
		perPageNum : 5
}

/* 댓글 작성 클릭 이벤트 */
 $('.btn-comment-insert').click(function(){
	if('${user.me_id}' == ''){
		if(confirm('로그인 후 이용 가능한 서비스입니다. \n로그인 화면으로 이동하시겠습니까?')){
			location.href = '<c:url value="/login"></c:url>'
		}
		return;
	}
	let co_content = $('[name=co_content]').val();
	if(co_content.trim().length == 0){
		alert('내용을 입력하세요.');
		return;
	}
	let comment = {
			co_bo_num : bo_num,
			co_content : co_content
	}
	insertComment(comment, 1);
 });

selectCommentList(cri);

function listSuccess(data){
	console.log(data);
	addCommentList(data.list);
	addPagination(data.pm);
}
function addCommentList(list){
	str = '';
	for(i=0; i<list.length; i++){
		str += createComment(list[i]);
	}
	$('.comment-list').html(str);
	//답글 클릭 이벤트 추가
	$('.btn-reply').click(function(){
		commentInit();
		//버튼들을 감춤
		$(this).parent().hide();
		//답글 입력창을 추가(버튼을 포함)
		str = '';
		str +=
		'<div class="reply-box input-group mb-3">'+
			'<textarea class="form-control" placeholder="댓글을 입력하세요." name="co_content"></textarea>'+
			'<div class="input-group-append">'+
				'<button class="btn btn-success btn-reply-insert" type="button" data-num="'+$(this).data('num')+'">댓글 작성</button>'+
			'</div>'+
		'</div>';
		$(this).parent().after(str);
		//답글등록 클릭 이벤트
		$('.btn-reply-insert').click(function(){
			let co_content = $(this).parents('.reply-box').find('[name=co_content]').val();
			if(!checkLogin('로그인한 회원만 댓글 답글을 추가할 수 있습니다.'))
				return;
			let comment = {
					co_content : co_content,
					co_bo_num : bo_num,
					co_ori_num : co_ori_num
			}
			console.log(comment)
			insertComment(comment, cri.page);
		});
		
	});
	//삭제 클릭 이벤트 추가
	$('.btn-delete').click(function(){
		if(!checkLogin('작성자만 댓글을 삭제할 수 있습니다.'))
			return;
	let co_num = $(this).data('num');
	let comment = {
			co_num : co_num
	}
	ajaxPost(comment, '<c:url value="/comment/delete"></c:url>', deleteSuccess);
	})
	//수정 클릭 이벤트 추가
	$('.btn-update').click(function(){
		if(!checkLogin('작성자만 댓글을 수정할 수 있습니다.'))
			return;
		commentInit();
		$(this).parent().hide();//선택된 댓글의 버튼들 안보이게
		//수정창 추가
		let co_content = $(this).parents('.comment').find('.comment-content').text();
		
		str = '';
		str +=
		'<div class="update-box input-group mb-3">'+
			'<textarea class="form-control" placeholder="댓글을 입력하세요." name="co_content">'+co_content+'</textarea>'+
			'<div class="input-group-append">'+
				'<button class="btn btn-success btn-update-insert" type="button" data-num="'+$(this).data('num')+'">댓글 수정</button>'+
			'</div>'+
		'</div>';
		$(this).parent().prev().hide(); //이미 적혀있던 댓글내용 감춤
		$(this).parent().before(str);
		//댓글 수정 등록버튼 클릭 이벤트 추가
		$('.btn-update-insert').click(function(){
			if(!checkLogin('작성자만 댓글을 수정할 수 있습니다.'))
				return;
			let co_content = $(this).parents('.update-box').find('[name=co_content]').val();
			let co_num = $(this).data('num');
			console.log(co_num); //얘가 undefined 뜨는데.....................................
			let comment = {
					co_content : co_content,
					co_num : co_num
			}
			ajaxPost(comment,'<c:url value="/comment/update"></c:url>',updateSuccess);
		})
	})
}
function updateSuccess(data){
	console.log(data);
	if(data.res==-1)
		alert('작성자만 수정할 수 있습니다.')
	else if(data.res==0)
		alert('댓글 수정에 실패했습니다.')
	else
		alert('댓글이 수정되었습니다.');
	selectCommentList(cri);
}
function checkLogin(msg){
	if('${user.me_id}'==''){
		if(confirm(msg+'\n로그인 하시겠습니까?'))
			location.href = '<c:url value="/login"></c:url>';
		return false;
	}
	return true;
}
//댓글의 보기설정 초기화하는 함수(답글,수정버튼 눌렀을 때 동시에 안되게하려고 하나에 묶음)
function commentInit(){
	//다른 댓글버튼들, 덧글내용 보이게
	$('.btn-group').show();
	$('.comment-content').show();
	//다른 댓글 수정창 제거
	$('.update-box').remove();
	
	//다른 답글 입력창을 제거
	$('.reply-box').remove();
	
}
function deleteSuccess(data){
	if(data.res==-1)
		alert('작성자만 삭제할 수 있습니다.')
	else if(data.res==0)
		alert('댓글 삭제에 실패했습니다.')
	else
		alert('댓글을 삭제했습니다.');
	selectCommentList(cri);
}
function createComment(comment){
	str = '';
	str +=
	'<div class="comment">'+
		'<div class="comment-id">'+comment.co_me_id+'</div>'+
		'<div class="comment-date">'+comment.co_register_date_str+'</div>'+
		'<div class="comment-content">'+comment.co_content+'</div>'+
		'<div class="btn-group">'+
			'<button type="button" class="btn btn-outline-success btn-reply" data-num="'+comment.co_num+'">답글</button>';
	if(comment.co_me_id=='${user.me_id}'){
			str +=
			'<button type="button" class="btn btn-outline-success btn-update" data-num="'+comment.co_num+'">수정</button>'+
			'<button type="button" class="btn btn-outline-success btn-delete" data-num="'+comment.co_num+'">삭제</button>';
	}
		str +=
		'</div>'+
	'</div>';
	return str;
}
function addPagination(pm){
	let prev = pm.prev? '': 'disabled';
	let next = pm.next? '': 'disabled';
	str = '';
	str +=
	'<li class="page-item '+prev+'">'+
		'<a class="page-link" href="#" data-page="'+(pm.startPage-1)+'">이전</a>'+
	'</li>';
	for(i=pm.startPage; i<=pm.endPage; i++){
		let page = pm.cri.page==i ? 'active' : '';
		str +=
		'<li class="page-item '+page+'">'+
			'<a class="page-link" href="#" data-page="'+i+'">'+i+'</a>'+
		'</li>';
	}
	str +=
	'<li class="page-item '+next+'">'+
		'<a class="page-link" href="#" data-page="'+(pm.endPage+1)+'">다음</a>'+
	'</li>';
	$('.comment-pagination').html(str);
	//페이지네이션 이벤트 등록(다음 페이지 누르면 page정보 바꾸고 새로 로딩)
	$('.comment-pagination .page-link').click(function(e){
		e.preventDefault();
		let page = $(this).data('page');
		cri.page = page;
		selectCommentList(cri);
	})
}
function selectCommentList(cri){
	ajaxPost(cri, '<c:url value="/comment/list/'+bo_num+'"></c:url>', listSuccess);
}
function insertComment(comment, page){
	ajaxPost(comment, '<c:url value="/comment/insert"></c:url>',insertSuccess)
	cri.page = page;
	selectCommentList(cri);
}
function insertSuccess(data){
	if(data.res){
		alert('댓글을 등록했습니다.');
		$('[name=co_content]').val();
	}else{
		alert('댓글을 등록하지 못했습니다.');
	}
	cri.page = 1;
	selectCommentList(cri);
}
function ajaxPost(obj, url, successFunction){
	 $.ajax({
       async:false,
       type:'POST',
       data: JSON.stringify(obj),
       url:url,
       dataType:"json", 
       contentType:"application/json; charset=UTF-8", 
       success : successFunction
	});
}
</script>
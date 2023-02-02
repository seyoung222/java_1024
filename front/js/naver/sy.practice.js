/* 뉴스페이지 롤링 이벤트 관련 
$(function(){
	$('.box-body-left2 .list-press').hide();
	$('.box-body-left2 .list-press.selected').show();
	if($('.box-body-left2 .list-press').first().has('selected'))
		$('.btn-prev').hide();
	if($('.box-body-left2 .list-press').last().has('selected'))
		$('.btn-next').hide();
	$('.box-body-left2 .btn-next').click(function(e){
		e.preventDefault();
		nextPage();
	})
})

function nextPage(){
		let selected = $('.box-body-left2 .list-press.selected');
		selected.removeClass('selected');
		selected.next().addClass('selected');
}
*/

/* left3 메뉴 탭 바꾸기 
$(function(){
	$('.box-body-left3 .btn-menu').click(function(){
		$('.box-body-left3 .btn-menu').removeClass('selected');
		$(this).addClass('selected');
	})

	$('.box-body-left3 .btn-next').click(function(){
		let obj = $('.box-body-left3 .box-menu .btn-menu[aria-selected=true]');
		if(obj.hasClass('living'))
			$('.box-body-left3 .list-menu').animate({
				marginLeft:'-186px'
			},1000);
	})
})*/

/* 메뉴바 더보기-접기 전환 */
$(function(){
	$('.container-menu .btn-more').click(function(){
		$(this).html('<a href="#" class="btn-close">접기<i class="icon-more img1"></i></a>')
	})
	$('.container-menu .btn-close').click(function(){
		$(this).html('<a href="#" class="btn-more">더보기<i class="icon-more img1"></i></a>')
	})
})
// $(document).on('click','.container-menu .btn-close',function(){
// 	$(this).html('<a href="#" class="btn-more">더보기<i class="icon-more img1"></i></a>');
// });
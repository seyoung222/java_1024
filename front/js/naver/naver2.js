/* 롤링 관련 이벤트 */
$(function(){
	$('.box-body-right2 .btn-next').click(function(e){
		e.preventDefault();
		moveLeft(liRight2, ulRight2, timeRight2);
	});
	
	$('.box-body-right2 .btn-prev').click(function(e){
		e.preventDefault();
		moveRight(liRight2, ulRight2, timeRight2);
	});
	
	
	$('.box-body-right2').hover(function(){
		clearInterval(rollingRight2);
	}, function(){
		rollingRight2 = rollingLeft(rollingObjRigh2.li, rollingObjRigh2.ul, rollingObjRigh2.time, rollingObjRigh2.duration);
	});
	
	$('.box-body-left2 .list-issue').hover(function(){
		clearInterval(rollingLeft2);
	}, function(){
		rollingLeft2 = rollingTop(rollingObjLeft2);
	});
});
/* 페이지네이션 버튼 이벤트 */
$(function(){
	$('.box-shop-in .btn-prev').click(function(e){
		e.preventDefault();
		let boxShopIn = $(this).parents('.box-shop-in');
		// let curPage = $('.box-shop-in1 .box-shop-pagination .current-page').text();
		// let maxPage = $('.box-shop-in1 .box-shop-pagination .max-page').text();
		let curPage = boxShopIn.find('.current-page').first().text();
		let maxPage = boxShopIn.find('.max-page').first().text();
		curPage = +curPage;
		curPage = --curPage == 0 ? maxPage : curPage;
		$('.box-shop-in1 .current-page').text(curPage);
	});
	$('.box-shop-in1 .btn-next').click(function(e){
		e.preventDefault();
		let curPage = $('.box-shop-in1 .current-page').eq(0).text();
		let maxPage = $('.box-shop-in1 .max-page').first().text();
		curPage = +curPage;
		curPage = ++curPage > maxPage ? 1 : curPage;
		$('.box-shop-in1 .current-page').text(curPage);
	});
});
/* 왼쪽 두번째 뉴스 리스트 버튼 관리 */
$(function(){
	selectNewsList($('.box-body-left2 .list-press').eq(3));

	$('.box-body-left2 .btn-prev').click(function(e){
		e.preventDefault();
		let obj = $('.box-body-left2 .list-press.selected');
		if(obj.prev().length!=0){
			selectNewsList(obj.prev());
		}
	});
	$('.box-body-left2 .btn-next').click(function(e){
		e.preventDefault();
		let obj = $('.box-body-left2 .list-press.selected');
		if(obj.next().length==0 || !obj.next().hasClass('list-press'))
			return;
		selectNewsList(obj.next());
	});
})
function selectNewsList(el){
	$('.box-body-left2 .list-press').removeClass('selected').hide();
	el.show();
	el.addClass('selected')
	if(el.prev().length == 0){
		$('.box-body-left2 .btn-prev').hide();
	}else{
		$('.box-body-left2 .btn-prev').show();
	}
	if(el.next().length==0 || !el.next().hasClass('list-press')){
		$('.box-body-left2 .btn-next').hide();
	}else{
		$('.box-body-left2 .btn-next').show();
	}
}
//왼쪽 3번째 컨텐츠 관련 이벤트
$(function(){
	$('.box-body-left3 .box-menu .item-menu .btn-menu').click(function(e){
		e.preventDefault();
		$('.box-body-left3 .box-menu .item-menu .btn-menu').attr('aria-selected',false);
		$(this).attr('aria-selected',true);
		if($(this).parent().prev().length==0){
			$('.box-body-left3 .btn-prev').hide();
		}else{
			$('.box-body-left3 .btn-prev').show();
		}
		if($(this).parent().next().length==0){
			$('.box-body-left3 .btn-next').hide();
		}else{
			$('.box-body-left3 .btn-next').show();
		}
	})
	$('.box-body-left3 .btn-next').click(function(e){
		e.preventDefault();
		let obj = $('.box-body-left3 .box-menu .item-menu .btn-menu').filter('[aria-selected=true]');
		if(obj.parent().next().length!=0)
			obj.parent().next().children().click();
		if(obj.hasClass('living')){
			obj.parents('.list-menu').animate({
				marginLeft: '-185px'
			},500);
		}
	})
	$('.box-body-left3 .btn-prev').click(function(e){
		e.preventDefault();
		let obj = $('.box-body-left3 .box-menu .item-menu .btn-menu').filter('[aria-selected=true]');
		if(obj.parent().prev().length!=0)
			obj.parent().prev().children().click();
		if(obj.hasClass('car')){
			obj.parents('.list-menu').animate({
				marginLeft: 0
			},500);
		}
	})
})
/* 메뉴 관련 이벤트 */
$(function(){
	$('.group-menu .btn-more').click(function(e){
		e.preventDefault();
		$(this).toggleClass('fold');
		$('.container-menu .container-service').toggle(); //더보기 누르면 아래 메뉴창뜨게
		$('.group-menu .box-btn-area').toggle(); //더보기 누르면 전체보기 설정뜨게
		setMenuServiceBtn(true); //더보기 펼치면 무조건 전체보기가 보이도록
	})
	$('.group-menu .box-btn-area .btn-set').click(function(e){
		e.preventDefault();
		setMenuServiceBtn();//flag를 생략하면 undefined가 돼서 자동 false처리
	})
})
function setMenuServiceBtn(flag){
	$('.group-menu .box-btn-area .btn').removeClass('display-none');
	$('.container-service .group-service').removeClass('display-none');
	$('.container-menu .list-favority-menu').removeClass('display-none');
	$('.container-menu .list-empty-box').removeClass('display-none');
	if(flag){ 
		$('.group-menu .box-btn-area .btn-reset').addClass('display-none');
		$('.group-menu .box-btn-area .btn-save').addClass('display-none');
		$('.container-service .group-service').last().addClass('display-none');
		$('.container-menu .list-empty-box').addClass('display-none');
	}else{
		$('.group-menu .box-btn-area .btn-set').addClass('display-none');
		$('.group-menu .box-btn-area .btn-favorite-all').addClass('display-none');
		$('.container-service .group-service').first().addClass('display-none');
		$('.container-menu .list-favority-menu').addClass('display-none');
	}
}
/* 메뉴 즐겨찾기 설정 관련 이벤트(주말과제) */
$(function(){
	$('.container-service .group-service input').click(function(){
		if($(this).is(':checked')){
			if($('.group-menu .item-box.select').next().length==0){
				alert('4개 까지만 선택 가능합니다.')
				return;
			}
			$('.group-menu .item-box.select').text($(this).val());
			tmpArr.push($(this).val());
			console.log(tmpArr);
			$('.group-menu .item-box.select').next().addClass('select');
			$('.group-menu .item-box.select').first().removeClass('select');
		}else{ //클릭한걸 다시 누르면 지워지도록 해야함 
			$('.group-menu .item-box.select').text('');
			tmpArr.push($(this).val());
			console.log(tmpArr);
			$('.group-menu .item-box.select').next().addClass('select');
			$('.group-menu .item-box.select').first().removeClass('select');
		}
		
	});
	$('.group-menu .btn-save')
	$('.group-menu .btn-fold').click(function(){
		
	})
})
let tmpArr = [];
let arr = [];




let liRight2 = '.box-body-right2 .item-stock';
let ulRight2 = '.box-body-right2 .list-stock';
let timeRight2 = 1000;
let durationRight2 = 2000;

let rollingObjRigh2 = {
	li : '.box-body-right2 .item-stock',
 	ul : '.box-body-right2 .list-stock',
	time : 1000,
	duration : 2000
}
let rollingObjLeft2 = {
	liSelector : '.box-body-left2 .item-issue',
	ulSelector : '.box-body-left2 .list-issue',
	duration : 2000,
	animationTime : 1000
}
let rollingRight2 = rollingLeft(rollingObjRigh2.li, rollingObjRigh2.ul, rollingObjRigh2.time, rollingObjRigh2.duration);
let rollingLeft2 = rollingTop(rollingObjLeft2);

function moveLeft(liSelector, ulSelector, time){
	let width = $(liSelector).first().innerWidth();
	if(!$(liSelector).first().is(':animated')){
		$(liSelector).first().animate({
			marginLeft : `-${width}px`
		},time, function(){
			$(this).detach().appendTo(ulSelector).removeAttr('style');
		});
	}
}
function moveRight(liSelector, ulSelector, time){
	let width = $(liSelector).first().innerWidth();
	if(!$(liSelector).first().is(':animated')){
		$(liSelector)
			.last()
			.detach()
			.prependTo(ulSelector)
			.css('marginLeft',`-${width}px`)
			.animate({
				marginLeft : 0
			}, time);
	}
}
function rollingLeft(liSelector, ulSelector, animationTime, duration){
	return setInterval(moveLeft,duration,liSelector, ulSelector, animationTime);
}
function moveTop(liSelector, ulSelector, animationTime){
	let height = $(liSelector).first().innerHeight();
	if(!$(liSelector).first().is(':animated')){
		$(liSelector).first().animate({
				marginTop : `-${height}px`
			}, animationTime, function(){
				$(this).detach().appendTo(ulSelector).removeAttr('style');
			});
	}
}
function rollingTop(rollingObj){
	return setInterval(moveTop,rollingObj.duration,rollingObj.liSelector, 
		rollingObj.ulSelector, rollingObj.animationTime);
}
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
	$('.group-service-check [type=checkbox]').click(function(){
		//클릭한 요소의 value를 가져옴
		let val = $(this).val();
		//전역 배열에 value가 있는지 확인 (또는 클릭한 요소의 checked여부를 확인)
		let index = tmpMenuArr.indexOf(val);
		//배열에 value가 있으면 (또는 클릭한 요소가 checked가 해제되면)
		if(index >= 0){ //index != -1 도 가능
			//배열에 value를 제거
			tmpMenuArr.splice(index,1); //index번지부터 1개 삭제
		}else{//없으면
			//배열의 길이가 4이상이면 현재 선택한 요소를 checked해제하고 알림창 띄운 후 종료
			if(tmpMenuArr.length>=4){
				$(this).prop('checked',false);
				alert('최대 4개까지로 설정할 수 있습니다.');
				return;
			}
			//배열에 value를 추가
			tmpMenuArr.push(val);
		}
		//주어진 전역배열을 기준으로 list-empty-box요소를 배치(함수로 따로 만듦)
		drawEmptyboxMenu(tmpMenuArr);
	});
	//저장버튼
	$('.group-menu .btn-save').click(function(){
		if(tmpMenuArr.length==0)
			alert('선택된 메뉴가 없습니다. 초기설정으로 돌아갑니다.')
		selectedMenuArr = tmpMenuArr.splice(0); //tmp배열에 있는걸 옮김
		init();
		$('.group-menu .btn-more').click();
	})
	//초기화
	$('.group-menu .btn-reset').click(function(){
		alert('초기설정으로 돌아갑니다.')
		selectedMenuArr = tmpMenuArr = [];
		init();
		$('.group-menu .btn-more').click();
	})
});
//선택된 메뉴에 따른 메뉴 박스 관리 및 체크박스 관리하는 함수
function init(){
//선택된 메뉴가 없는 경우 => 고정된 메뉴 출력
	if(selectedMenuArr.length==0){
		$('.list-favority-menu').show();
		$('.list-select-menu').hide();
	}
	//선택됨 메뉴가 있는 경우 => 선택된 메뉴 출력
	else{ 
		$('.list-favority-menu').hide();
		$('.list-select-menu').show();

		/* 저장했을 때 여백 보이는 상태 
		$('.list-select-menu .item-box').text('');
		for(index in selectedMenuArr){
			$('.list-select-menu .item-box').eq(index).text(selectedMenuArr[index]);
		}*/

		//여백도 안보이게 하려고.. 배열요소 넣을때 li태그를 생성
		$('.list-select-menu').html('');
		for(index in selectedMenuArr){
			let str = `<li class="item-box">${selectedMenuArr[index]}</li>`;
			$('.list-select-menu').append(str);
		}
	}
}
function drawEmptyboxMenu(tmpMenuArr){
	//select : 녹색박스, select 클래스를 제거->녹색박스 제거
	//모든 박스에 있는 글자들을 ''(빈문자열)로 초기화
	$('.list-empty-box .item-box').removeClass('select').text('');
	for(index in tmpMenuArr){
		$('.list-empty-box .item-box').eq(index).text(tmpMenuArr[index]);
	};
	$('.list-empty-box .item-box').eq(tmpMenuArr.length).addClass('select');
	//체크박스 관리
	$('.group-service-check [type=checkbox]').each(function(){
		let val = $(this).val();
		if(tmpMenuArr.indexOf(val) != -1)
			$(this).prop('checked',true);
		else
			$(this).prop('checked',false);
	})
}
let tmpMenuArr = []; //선택할 메뉴를 저장할 임시 배열(저장 전)
let selectedMenuArr = []; //선택한 메뉴를 저장할 배열(저장 완료)
function setMenuServiceBtn(flag){
	$('.group-menu .box-btn-area .btn').removeClass('display-none');
	$('.container-service .group-service').removeClass('display-none');
	$('.container-menu .list-favority-menu').removeClass('display-none');
	$('.container-menu .list-select-box').removeClass('display-none');
	$('.container-menu .list-empty-box').removeClass('display-none');
	//접기 눌렀을 때
	if(flag){ 
		$('.group-menu .box-btn-area .btn-reset').addClass('display-none');
		$('.group-menu .box-btn-area .btn-save').addClass('display-none');
		$('.container-service .group-service').last().addClass('display-none');
		$('.container-menu .list-empty-box').addClass('display-none');
	}
	//더보기 눌렀을 때
	else{
		$('.group-menu .box-btn-area .btn-set').addClass('display-none');
		$('.group-menu .box-btn-area .btn-favorite-all').addClass('display-none');
		$('.container-service .group-service').first().addClass('display-none');
		$('.container-menu .list-favority-menu').addClass('display-none');
		$('.container-menu .list-select-box').addClass('display-none');
		tmpMenuArr = selectedMenuArr.slice(0); //부분문자열 추출
		drawEmptyboxMenu(tmpMenuArr);
	}
}
/* 메뉴 즐겨찾기 설정 관련 이벤트(주말과제)
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
			let deleteIndex = tmpArr.findIndex($(this).val());
			tmpArr.splice(index,1);
			console.log(tmpArr);
			$('.group-menu .item-box.select').text('');
			$('.group-menu .item-box.select').next().addClass('select');
			$('.group-menu .item-box.select').first().removeClass('select');
		}
		
	});
	$('.group-menu .btn-save')
	$('.group-menu .btn-fold').click(function(){
		
	})
})
let tmpArr = [];
let arr = []; */




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
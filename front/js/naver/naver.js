// 이벤트를 안에 넣어줘서 준비되면 실행되게함
$(function(){
	$('.box-body-right2').hover(function(){
		clearInterval(rollingRight2);
	}, function(){
		rollingRight2=rolling(liRight2,ulRight2,timeRight2,duration);
	})
	$('.box-body-right2 .btn-next').click(function(e){
		e.preventDefault(); //a태그라서 상단으로 넘어가지 않게하려고
		moveLeft(liRight2, ulRight2, timeRight2);
	});
	$('.box-body-right2 .btn-prev').click(function(e){
		e.preventDefault(); //a태그라서 상단으로 넘어가지 않게하려고
		moveRight(liRight2, ulRight2, timeRight2);
	})
	/*
	$('.box-body-left2').hover(function(){
		clearInterval(id);
	}, function(){
		id=rolling(liRight2,ulRight2,timeRight2,duration);
	})
	*/
});

// 전역변수
let liRight2 = '.box-body-right2 .item-stock';
let ulRight2 = '.box-body-right2 .list-stock';
let timeRight2= 1000;
let duration = 2000;
let rollingObjRight2 = { //이름 여러번쓰기싫을때 전역변수 선언or객체로도 선언가능
	li : '.box-body-right2 .item-stock',
	ul : '.box-body-right2 .list-stock',
	time: 1000,
	duration : 2000
}
let rollingObjLeft2 = { 
	liSelector : '.box-body-left2 .item-issue',
	ulSelector : '.box-body-left2 .list-issue',
	animationTime: 1000,
	duration : 2000
}
let rollingRight2 = rollingLeft(liRight2,ulRight2,timeRight2,duration);
// let rollingRight2 = rollingLeft(rollingObjRight2.li, rollingObjRight2.ul, rollingObjRight2.time);
let rollingLeft2 = rollingTop(rollingObjLeft2);


// 함수
function moveLeft(liSelector, ulSelector, time){
	let width = $(liSelector).first().innerWidth();
		if(!$(liSelector).first().is(':animated')){
			$(liSelector).first().animate({
				marginLeft: `-${width}px`
			},time,function(){
				$(this).detach().appendTo(ulSelector).removeAttr('style');
			});
		};	
}
function moveRight(liSelector, ulSelector, time){
	let width = $(liSelector).first().innerWidth();
	if(!$(liSelector).fist().is(':animated')){
		$(liSelector)
			.last()
			.detach()
			.prependTo(ulSelector)
			.css('marginLeft',`-${width}px`)
			.animate({marginLeft : 0},time);
	}
}
function rollingLeft(liSelector, ulSelector, animationTime,duration){
	return setInterval(moveLeft(liSelector,ulSelector,animationTime),duration);
}
function moveTop(liSelector, ulSelector, animationTime){
	let height = $(liSelector).first().innerHeight();
	if(!$(liSelector).first().is(':animaited')){
		$(liSelector).first().animate({
			marginTop : `-${height}px`
		},animationTime,function(){
			$(this).detach().appendTo(ulSelector).removeAttr('style');
		});
	}
}
function rollingTop(rollingObj){;
	return setInterval(moveTop,rollingObj.duration,rollingObj.liSelector, 
		rollingObj.ulSelector, rollingObj.animationTime)
}
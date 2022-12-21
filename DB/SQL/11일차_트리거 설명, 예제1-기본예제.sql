/*
> 트리거 기본 형태
- 테이블에 부착되는 프로그램 코드로, 해당 테이블에 데이터 삽입/수정/삭제 작업이 발생하면 자동으로 실행
- 트리거에서 추가/수정/삭제된 데이터를 가져올 때 new|old를 사용
-- insert : new(추가된 데이터)
	-- insert된 데이터는 트리거에서 수정할 수 없음
-- update : old(값이 변경 전 데이터), new(값이 변경 후 데이터)
-- delete : old(삭제된 데이터)

drop trigger if exists 트리거명;

delimiter //
create trigger 트리거명
-- before : 테이블에 데이터가 삭제|추가|수정 되기 전에 트리거가 실행
-- after : 테이블에 데이터가 삭제|추가|수정 된 후에 트리거가 실행
before|after delete|insert|update
on 테이블명
for each row
begin
-- 실행 코드
end //
delimiter ;
*/
use shoppingmall;

-- 제품을 구매했을 때 동작하는 트리거
drop trigger if exists insert_buy;

delimiter //
create trigger insert_buy after insert
on buy
for each row
begin
-- 추가된 데이터는 new를 통해 가져옴.
update product 
	set pr_amount = pr_amount - new.bu_amount -- 추가된수량
where pr_num = new.bu_pr_num; -- 추가된제품기본키
end //
delimiter ;

insert into buy(bu_num, bu_amount, bu_address, bu_post_num, bu_me_id, bu_pr_num)
values('qwe202212211712', 1,'서울시 강남', '12345', 'qwe', 2);
-- 예매를 하면, 해당 영화상영시간의 예매 가능 좌석을 수정해주는 트리거를 생성하고, 테스트해보세요.
-- ticketing 테이블에 데이터가 추가되면 트리거 동작
use cgv;
drop trigger if exists insert_ticketing;
delimiter //
create trigger insert_ticketing after insert
on ticketing
for each row
begin
update screen_schedule
	set ss_possible_seat = ss_possible_seat - new.ti_amount
where ss_num = new.ti_ss_num;
end //
delimiter ;
insert ticketing(ti_amount, ti_me_id, ti_ss_num, ti_total_price)
values (

/* 내가 한 거
drop trigger if exists update_seat;
delimiter //
create trigger update_seat after insert 
on ticketing
for each row
begin
update screen_schedule
	set ss_possible_seat = ss_possible_seat - new.ti_amount
where ss_num = new.ti_ss_num;
end //
delimiter ;

insert ticketing(ti_amount, ti_me_id, ti_ss_num, ti_total_price)
values(2, 'abc', 1, 20000);
*/
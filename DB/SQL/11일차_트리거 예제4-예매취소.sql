use cgv;

-- 예매를 취소했을 때 예매 가능 좌석을 수정하는 트리거를 추가하고, 확인하세요.

drop trigger if exists delete_ticketing;
delimiter //
create trigger delete_ticketing before delete
on ticketing
for each row
begin
	-- 예매된 좌석을 삭제
	delete from ticketing_seat where ts_ti_num = old.ti_num;
    -- 예매 가능 좌석 수를 증가
    update screen_schedule
		set ss_possible_seat = ss_possible_seat + old.ti_amount
        where ss_num = old.ti_ss_num;
	-- call member_movie_count(old.ti_me_id);  얘는 안에 select문이 있어서 트리거 안에 사용할 수 없음
end //
delimiter ;

delete from ticketing where ti_num = 1;

/*
drop trigger if exists delete_ticketing;
delimiter //
create trigger delete_ticketing before delete
on ticketing
for each row
begin
update screen_schedule
	set ss_possible_seat = ss_possible_seat + old.ti_amount
    where ss_num = old.ti_ss_num;
delete from ticketing_seat where ts_ti_num = old.ti_num;
end //
delimiter ;

delete from ticketing where ti_num = 4;
*/
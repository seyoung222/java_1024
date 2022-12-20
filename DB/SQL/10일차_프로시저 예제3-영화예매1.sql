/*
영화를 예매하면, 예매 내역을 추가하는 프로시저를 작성하세요.
-- 해당 프로시저는 좌석을 3개 고정으로 선택
-- 아이디, 상영번호, 좌석명을 전달

-- 예매 시 쿼리 기본 뼈대
1.  예매를 등록
insert into ticketing(ti_amount, ti_me_id, ti_ss_num, ti_total_price)
values(3, 'abc', 25, 30000);
2. 예매 좌석을 등록
insert into ticketing_seat(ts_ti_num, ts_se_num)
values(2, 11), (2, 13),(2, 15);
3. 예매 가능 좌석을 수정 
update screen_schedule set ss_possible_seat = ss_possible_seat - 3
	where ss_num = 25;
*/
use cgv;

DROP PROCEDURE IF EXISTS movie_ticketing;
DELIMITER //
CREATE PROCEDURE movie_ticketing(
	in _id varchar(20),
    in _ss_num int,
    in _seat1 varchar(10),
    in _seat2 varchar(10),
    in _seat3 varchar(10)
)
BEGIN
	declare _count int default 0;
    declare _total_price int default 0;
    declare _se_num int default 0;
    declare _ti_num int;
    
    if _seat1 is not null then
		set _count = _count + 1;
        set _total_price 
			= _total_price + (select se_price from screen_schedule
				join cinema on ss_ci_num = ci_num 
				join seat on se_ci_num = ci_num
				where ss_num = _ss_num and se_name = _seat1);
	end if;
	if _seat2 is not null then
		set _count = _count + 1;
		set _total_price 
			= _total_price + (select se_price from screen_schedule
				join cinema on ss_ci_num = ci_num 
				join seat on se_ci_num = ci_num
				where ss_num = _ss_num and se_name = _seat2);
	end if;
    if _seat3 is not null then
		set _count = _count + 1;
		set _total_price 
			= _total_price + (select se_price from screen_schedule
				join cinema on ss_ci_num = ci_num 
				join seat on se_ci_num = ci_num
				where ss_num = _ss_num and se_name = _seat3);
	end if;
    
    -- 예매 등록
	insert into ticketing(ti_amount, ti_me_id, ti_ss_num, ti_total_price)
	values(_count, _id, _ss_num, _total_price);

    -- 예매 좌석 등록
    set _ti_num = (select max(ti_num) from ticketing);
    
    if _seat1 is not null then
		set _se_num 
			= (select se_num from screen_schedule
				join cinema on ss_ci_num = ci_num 
				join seat on se_ci_num = ci_num
				where ss_num = _ss_num and se_name = _seat1);
		insert into ticketing_seat(ts_ti_num, ts_se_num) values(_ti_num, _se_num);
	end if;
    
    if _seat2 is not null then
		set _se_num 
			= (select se_num from screen_schedule
				join cinema on ss_ci_num = ci_num 
				join seat on se_ci_num = ci_num
				where ss_num = _ss_num and se_name = _seat2);
		insert into ticketing_seat(ts_ti_num, ts_se_num) values(_ti_num, _se_num);
	end if;
    
    if _seat3 is not null then
		set _se_num 
			= (select se_num from screen_schedule
				join cinema on ss_ci_num = ci_num 
				join seat on se_ci_num = ci_num
				where ss_num = _ss_num and se_name = _seat3);
		insert into ticketing_seat(ts_ti_num, ts_se_num) values(_ti_num, _se_num);
	end if;
    
    -- 예매 가능 좌석 수 수정
	update screen_schedule set ss_possible_seat = ss_possible_seat - _count
		where ss_num = _ss_num;
END //
DELIMITER ;

CALL movie_ticketing('abc', 27, 'A1','A2', null);


-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- 내가 원하는 좌석의 가격을 가져오는 쿼리
select se_price from screen_schedule
	join cinema on ss_ci_num = ci_num -- 좌석 가격을 알기위해 조인
    join seat on se_ci_num = ci_num
    where ss_num = 25 and se_name = 'A1';
    


/* 내가 한 거
drop procedure if exists ticketing_three;
delimiter //
create procedure ticketing_three(
	in _id varchar(20),
	in _schedule_num int,
    in _seat1 int, in _seat2 int, in _seat3 int
)
begin
	insert into ticketing(ti_amount, ti_me_id, ti_ss_num, ti_total_price)
		values(3, _id, _schedule_num, 30000);
	insert into ticketing_seat(ts_ti_num, ts_se_num)
		values(_schedule_num, _seat1), (_schedule_num, _seat2),(_schedule_num, _seat3);
	update screen_schedule set ss_possible_seat = ss_possible_seat - 3
		where ss_num = _schedule_num;
end //
delimiter ;

call ticketing_three('abc',25, 1,2,3);
*/
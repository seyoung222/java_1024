
-- 이 때 !!!!!!!!!!!! 프로시저의 매개변수를 기본키로 하면 따로 select/join하지 않아도 돼서 쿼리가 훨씬 단순해짐

DROP PROCEDURE IF EXISTS movie_ticketing;
DELIMITER //
CREATE PROCEDURE movie_ticketing( -- 좌석명이 아니라 좌석번호를 매개변수로 할 경우 검색해도되지않아서 쿼리가 짧아짐
	in _id varchar(20),
    in _ss_num int,
    in _seat1 int,
    in _seat2 int,
    in _seat3 int
)
BEGIN
	declare _count int default 0;
    declare _total_price int default 0;
    declare _se_num int default 0;
    declare _ti_num int;
    
    if _seat1 is not null then
		set _count = _count + 1;
        set _total_price 
			= _total_price + (select se_price from seat where se_num = _seat1);
	end if;
	if _seat2 is not null then
		set _count = _count + 1;
		set _total_price 
			= _total_price + (select se_price from seat where se_num = _seat2);
	end if;
    if _seat3 is not null then
		set _count = _count + 1;
		set _total_price 
			= _total_price + (select se_price from seat where se_num = _seat3);
	end if;
    
    -- 예매 등록
	insert into ticketing(ti_amount, ti_me_id, ti_ss_num, ti_total_price)
	values(_count, _id, _ss_num, _total_price);

    -- 예매 좌석 등록
    set _ti_num = (select max(ti_num) from ticketing);
    
    if _seat1 is not null then
		insert into ticketing_seat(ts_ti_num, ts_se_num) values(_ti_num, _seat1);
	end if;
    
    if _seat2 is not null then
		insert into ticketing_seat(ts_ti_num, ts_se_num) values(_ti_num, _seat2);
	end if;
    
    if _seat3 is not null then
		insert into ticketing_seat(ts_ti_num, ts_se_num) values(_ti_num, _seat3);
	end if;
    
    -- 예매 가능 좌석 수 수정
	update screen_schedule set ss_possible_seat = ss_possible_seat - _count
		where ss_num = _ss_num;
END //
DELIMITER ;

CALL movie_ticketing('abc', 27, 57 , null, null);
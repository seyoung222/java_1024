USE CGV;

-- 회원별로 예매한 총 횟수 구하는 프로시저
DROP PROCEDURE IF EXISTS MEMBER_MOVIE_COUNT;
DELIMITER //
CREATE PROCEDURE MEMBER_MOVIE_COUNT(
	IN _ID VARCHAR(20) -- 지역변수, 매개변수 헷갈릴 수 있으므로 변수명에 _나 구분가능한 형태로 짓는게 좋음
)
BEGIN
	-- 지역변수 선언은 위에 모아줘야함 (declare로 선언)
	declare _movie_count int default 0;
	with ticketing_count(ss_mo_num, movie_ticketing_count)
		as
		(select ss_mo_num, count(*) FROM ticketing
			join screen_schedule on ti_ss_num = ss_num
			where ti_me_id like _id
			group by ss_mo_num) -- 영화별 예매 횟수, 예매한 전체 영화 수 아님.
		select @num := count(*) from ticketing_count; -- > abc 회원이 예매한 총 횟수
		set _movie_count = (select @num);
        update member set me_movie_count = _movie_count where me_id = _id;
END //
DELIMITER ;

CALL MEMBER_MOVIE_COUNT('abc');


-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- 본문으로 들어가는 쿼리 설명 

SELECT count(*) FROM ticketing
	join screen_schedule on ti_ss_num = ss_num
    where ti_me_id like 'abc'
    group by ss_mo_num; -- > 한 영화를 각각 몇번 예매 했는지 조회됨
    
with ticketing_count(ss_mo_num, movie_ticketing_count)
as
(select ss_mo_num, count(*) FROM ticketing
	join screen_schedule on ti_ss_num = ss_num
    where ti_me_id like 'abc'
    group by ss_mo_num) -- 영화별 예매 횟수, 예매한 전체 영화 수 아님.
select @num := count(movie_ticketing_count) from ticketing_count; -- > abc 회원이 예매한 총 횟수
select @num;
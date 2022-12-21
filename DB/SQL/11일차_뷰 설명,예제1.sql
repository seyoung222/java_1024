/*
뷰 : 가상의 테이블, select문을 실행해 나온 결과
- 장점 : 보안에 도움(추가/수정/삭제X 조회만 가능), 복잡한 쿼리 단순화(만들어두면 마치 테이블처럼 사용(조회)가능)
*/
use cgv;
-- 예매한 영화이름, 영화관, 좌석이름, 아이디, 상영일, 상영시간을 한 번에 조회
select mo_title, ss_date, ss_time, ci_name, se_name, ti_me_id from ticketing
	join ticketing_seat on ts_ti_num = ti_num
    join seat on ts_se_num = se_num
    join screen_schedule on ss_num = ti_ss_num
    join movie on mo_num = ss_mo_num
    join cinema on ci_num = ss_ci_num;
    
-- 예매한 영화이름, 영화관, 좌석이름, 아이디, 상영일, 상영시간을 한 번에 조회하는 뷰 생성 <- 다 조인해놓고 필요한것만 골라써도됨
create view ticket_detail1
as 
select mo_title, ss_date, ss_time, ci_name, se_name, ti_me_id from ticketing
	join ticketing_seat on ts_ti_num = ti_num
    join seat on ts_se_num = se_num
    join screen_schedule on ss_num = ti_ss_num
    join movie on mo_num = ss_mo_num
    join cinema on ci_num = ss_ci_num;
select * from ticket_detail1;

-- 위에는 깔끔하게 원하는 정보를 보여주지만 그 외의 정보는 보여주지 않기 때문에 아래처럼 *로 만든 테이블을 뷰로 생성하기도 함
create view ticket_detail2
as 
select * from ticketing
	join ticketing_seat on ts_ti_num = ti_num
    join seat on ts_se_num = se_num
    join screen_schedule on ss_num = ti_ss_num
    join movie on mo_num = ss_mo_num
    join cinema on ci_num = ss_ci_num;
select * from ticket_detail2;

select * from ticket_detail2;

-- abc 회원이 영화 예매를 위해 사용한 금액은?
select sum(se_price) as '예매 금액' from ticket_detail2;

/* 내가 한거
select ti_me_id as 회원, sum(ti_total_price) as 총사용액 from ticket_detail2
	group by ti_me_id
    having ti_me_id = 'abc';
*/
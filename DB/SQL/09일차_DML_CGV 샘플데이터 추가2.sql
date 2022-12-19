-- 영화인 등록 
insert into movie_person(mp_name, mp_birth, mp_country)
values('안태진',NULL,'한국'),('류준열','1986-09-25','한국'),
('유해진','1970-01-04','한국'),('최무성','1968-01-12','한국'),
('조성하','1966-08-08','한국'),('박명훈','1975-05-28','한국'),
('김성철','1991-12-31','한국'),('안은진','1991-05-06','한국'),
('조윤서','1993-01-04','한국');
-- 영화 등록 
insert into movie(mo_title, mo_contents, mo_age, mo_run, mo_opening_date, mo_state)
values('올빼미','맹인이지만 뛰어난 침술 실력을 지닌 ‘경수’는 ...','15세이상',118,'2022-11-23', '현재상영중');
-- 영화 장르 등록
insert into movie_genre(mg_ge_name, mg_mo_num)
values('스릴러',2);
-- 영화 캐스팅 등록 
insert into movie_casting(mc_mp_num, mc_mo_num, mc_role)
values(9, 2, '감독'),(10, 2, '배우'),(11, 2, '배우')
,(12, 2, '배우'),(13, 2, '배우'),(14, 2, '배우')
,(15, 2, '배우'),(16, 2, '배우'),(17, 2, '배우');
-- 트레일러 등록
insert into trailer(tr_title, tr_file_name, tr_mo_num)
values('[올빼미]박스오피스 1위 리뷰 예고편','http://h.vod.cgv.co.kr:80/vodCGVa/86481/86481_210223_1200_128_960_540.mp4',2);
-- 스틸컷 등록 
insert into stillcut(st_file_name, st_mo_num)
values('https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000086/86481/86481210724_727.jpg', 2);
-- 상영 스케줄
insert into screen_schedule(ss_date, ss_time, ss_total_seat, ss_possible_seat, ss_mo_num, ss_ci_num)
values('2022-12-20', '13:50', 10, 10, 2, 2),
('2022-12-20', '11:00', 10, 10, 2, 6),
('2022-12-20', '17:10', 10, 10, 2, 6);

-- abc 회원이 올빼미 12월 20일 13:50을 3장 예매 A1, B1, C1
-- 예매를 등록
insert into ticketing(ti_amount, ti_me_id, ti_ss_num, ti_total_price)
values(3, 'abc', 25, 30000);
-- 예매 좌석을 등록
insert into ticketing_seat(ts_ti_num, ts_se_num)
values(2, 11), (2, 13),(2, 15);
-- 예매 가능 좌석을 수정 
update screen_schedule set ss_possible_seat = ss_possible_seat - 3
	where ss_num = 25;

-- 날짜가 지났을 때 변경되어야할 쿼리를 작성
-- 회원이 본 영화 수를 체크(조회-'abc' 회원이 본 영화 목록 개수 참고)
update member 
set
	me_movie_count = 
		(select count(distinct ss_mo_num) from ticketing
		join screen_schedule on ss_num = ti_ss_num
		where ss_date < now())
where me_id = 'abc';


-- 'abc'회원이 예매한 올빼미 12월 20일 13:50 예매를 취소할 때 실행해야 하는 쿼리를 작성
-- delete 예약 내역을 삭제 (외래키 지정되어 있는 기본키는 삭제거부로 설정해놔서 안되므로 꼭 예약 좌석 삭제 -> 예약 내역 삭제 순으로)
/* 내가 한거: 예약 좌석 삭제, 이상..)
delete (select * from ticketing_seat
	join ticketing on ti_num = ts_ti_num
    join screen_schedule on ti_ss_num = ss_num
    join movie = ss_mo_num = mo_num
    where ss_date='2022-12-20' and ss_time='13:50' and mo_title like '올빼미')
from ticketing_seat; */

-- 주어진 시간에 해당되는 ss_num을 조회
select ss_num from screen_schedule
	where ss_mo_num=2 and ss_date='2022-12-20' and ss_time='13:50'; -- ss_num=25
-- 해당되는 예매번호를 검색 
select ti_num from ticketing
	where ti_ss_num = 
		(select ss_num from screen_schedule
		where ss_mo_num=2 and ss_date='2022-12-20' and ss_time='13:50');
-- 예매번호에 해당되는 예매좌석 삭제
delete from ticketing_seat
where
	ts_ti_num = 
		(select ti_num from ticketing
		where ti_ss_num = 
			(select ss_num from screen_schedule
			where ss_mo_num=2 and ss_date='2022-12-20' and ss_time='13:50')
		and ti_me_id = 'abc'
		);
-- 해당 예약 내역을 삭제
delete from ticketing
where
	ti_ss_num = 
		(select ss_num from screen_schedule
		where ss_mo_num=2 and ss_date='2022-12-20' and ss_time='13:50')
	and ti_me_id = 'abc';
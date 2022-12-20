/*
@는 지역변수로 워크벤치 프로그램 접속 종료하면 사라지는 변수
@@는 시스템 변수. 전역변수로, 다른 사용자가 로그인 하더라도 사용할 수 있는 변수, 명령어
*/
set @num = 10; -- @num에 10을 저장
select @num := 20; -- @num에 20을 저장한 후, 출력
select @num;
select @@sql_mode; -- 시스템변수(글로벌변수) sql_mode를 조회

show variables; -- 이 시스템에서 사용하고 있는 시스템변수들 조회

-- 실수 1.23을 정수 1로 변환
select cast(1.23 as signed integer);
select convert(1.23, signed integer);
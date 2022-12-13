/*
CRUD
- insert : 데이터를 DBMS에 추가
- select : DBMS에 있는 데이터를 조회
- update : DBMS에 있는 데이터를 수정
- delete : DBMS에 있는 데이터를 삭제 

select
	- 기본 select ___ 형태. now() 같은 함수가 와도 됨
	select * from product; -- *과 all은 다름. all은 중복처리 개념
	select all * from product; 

	select all pr_pc_num from product; -- 중복제거x
	select distinct pr_pc_num from product; -- 중복제거O

create table ... select
 - 기존 테이블 검색 결과를 이용하여 새 테이블을 생성
 - 제약 조건은 복사가 안됨(외래키와 같은, auto_increment도 사라짐)
 - 검색된 데이터들이 추가됨
 - 기존 있는 데이터를 이용해 테이블을 만들때는 ㄱㅊ지만 요소만들기 귀찮아서 이런 방법을 쓰는건 추천하지 않음..
 - 생략된 속성(쓰지 않은 거)은 추가되지 않음
*/
select * from board;
create table board2(
	select
		bo_num as num,
        bo_title as title,
        bo_contents as contents,
        bo_reg_date as reg_date,
        bo_views as views,
        bo_me_id as writer
	from board);
select * from board2;
/*
DML
 - insert : 데이터 추가
 - update : 데이터 수정
 - delete : 데이터 삭제
 - select : 데이터 조회

insert
- 문법
insert into 테이블명(속성1, 속성2, ..., 속성n)
	values(값1, 값2, ..., 값n);
insert into 테이블명 values(값1, 값2, ..., 값n)
	-> 이 때 값의 개수는 전체 테이블 속성 개수와 같고, 순서가 맞아야함 
 insert into 테이블명(속성1, 속성2, ..., 속성n)
	select 값1, 값2, ..., 값m, 속성1, 속성2, 속성n-m 
		from 테이블명 [where절]
	- 추가하려는 값이 검색을 통해 넣어야하는 경우 사용
	-> 값으로 넘어오지 않은 애들 중에서 검색을 통해 넣어야하는 것들을 속성으로 넣음
    -> 값으로 직접 넣은 것들은 검색을 해도 변하지 않고 그대로 나오기 때문에
    
update
- 문법
update 테이블명 set 속성1=값1, 속성2=값2, ..., 속성n=값n where절

delete
- 문법
delete from 테이블명 where절

select
- 문법
select [distinct] 속성1, 속성2, ..., 속성n from 테이블
	where절
    order by
    limit절
- where절
  between A and B
   in()
  all()
  any()
  서브쿼리
- order by절
  asc : 오름차순, desc : 내림차순
*/
insert into member values('qweqweqwe'); -- 열과 값의 개수가 일치하지 않아서 에러
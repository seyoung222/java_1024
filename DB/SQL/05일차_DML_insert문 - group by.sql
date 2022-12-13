/*
SELECT select_expr
	[FROM table_references]
    [WHERE where_condition]
    [GROUP BY {col_name | expr | position}]
    [HAVING where_condition]
    [ORDER BY {col_name | expr | position}]
    
group by
- 같은 속성을 가지는 값으로 묶을 때 사용.
- 여러 속성을 같이 사용하여, 여러 속성이 같은 행들을 묶어서 사용.
- 집계함수와 같이 사용 (조건을 걸때 where로 걸 수 없고, having을 이용해야 함)
*/
SET sql_mode=(SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY','')); -- sql모드를 변경하는 명령어
select * from product group by pr_pc_num; -- 하면 분류별로 물건 하나씩만 뜸..분류별로 그룹으로 묶은 것

/*
집계함수
- AVG() 평균
- MIN() 최소
- MAX() 최대
- COUNT() 행의 개수
- COUNT(DISTINCT) 행의개수(중복은 1개만 인정)
- STCEV() 표준편차
- VAR_SAMP() 분산
- SUM() 합계
*/
-- 카테고리 별 등록된 제품 수
select pr_pc_num as '카테고리번호', count(pr_num) as '카테고리별 등록된 제품 수'
	from product group by pr_pc_num;
-- 카테고리 별 등록된 제품 수가 최소 2개 이상인 카테고리번호들
select pr_pc_num as 카테고리번호, count(pr_num) as '카테고리 별 등록된 제품 수' -- 문자열에 공백 없으면 따옴표 없어도 됨..
	from product
    group by pr_pc_num
	-- having count(pr_num) >= 2;
	having `카테고리 별 등록된 제품 수` >= 2; -- 둘다 가능. 역따옴표로 적어야 속성키로 인식됨 
    
-- as는 별명 붙일때에는 꼭 붙이지 않아도 됨
-- but 검색결과를 view로 새로 만드는 경우(자주 쓰는 걸 저장해서 검색성능 향상) -> as 를 꼭 붙여야함
create view product_view as select * from product;
select * from product_view;
drop view product_view;
    
-- 카테고리 별 가장 비싼 제품의 가격을 조회
select pr_pc_num as 카테고리번호, max(pr_price) as '카테고리 중 가장 비싼 제품 가격' from product
    group by pr_pc_num;
-- 사용자 별 총 제품 구매 개수 조회
select bu_me_id as 사용자, sum(bu_amount) as 개수 from buy group by bu_me_id;
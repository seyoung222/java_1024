use shoppingmall;
/*
- union과 union all : 합집합
  - 단, 속성 이름들이 같아야 함
- union : 중복을 제거
- union all : 중복을 제거하지 않음. union보다 빠름(중복 제거하지 않기 때문에)
*) 검색이 복잡할때 union으로 합치면 간단. 꼭 외래키 같은 걸로 묶여있지 않아도 검색결과로 함께 합쳐질 수 있음~
**) JOIN과의 차이점: join은 속성으로 이어진 테이블을 옆으로 이어주는 것이고, union은 겁색된 결과를 값으로서 행을 추가해 붙인 것
*/
select * from product where pr_amount > 10
	union
select * from product where pr_price > 10000;

select * from product where pr_amount > 10
	union all
select * from product where pr_price > 10000;

select pr_num as 번호, pr_title as 내용 from product
	union
select pc_num as 번호, pc_name as 내용 from product_category;

/*
not in과 in
- not in : 첫번째 쿼리 결과 중에서 두번째 쿼리에 해당하는 것을 제외하고 출력
*/
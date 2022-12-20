use shoppingmall;

/*
★ 순위 함수 : row_number(), dense_rank(), rank(), ntile()
- row_number() : 같은 값이 있을 때 등수가 서로 다름
- dense_rank() : 같은 값이 있을 때 등수가 같음, 같은 값이 있는 행 다음 등수는 같은 값이 있는 등수 +1
- rank() : 같은 값이 있을 때 등수가 같음, 같은 값이 있는 행 다음 등수는 같은 값이 있는 등수 + 같은 값이 있는 개수
- ntile(n) : 행을 n개로 나눔. ex)상/중/하위권 분류
*/
select 
	row_number() over(order by pr_price desc) as 가격순위,
	pr_title as 제품명, 
    pr_price as 보유량 
from product;

select 
	dense_rank() over(order by pr_price desc) as 가격순위,
	pr_title as 제품명, 
    pr_price as 보유량 
from product;

select 
	rank() over(order by pr_price desc) as 가격순위,
	pr_title as 제품명, 
    pr_price as 보유량 
from product;

select 
	ntile(3) over(order by pr_price desc) as 가격순위,
	pr_title as 제품명, 
    pr_price as 보유량 
from product;


/*
분석 함수
- lead(속성) :  속성의 다음 값을 가져옴
- lead(속성, n, x) : 속성의 다음 n개의 값을 가져오고 없으면 x로 대체
- lag(속성) :  속성의 이전 값을 가져옴
- lag(속성, n, x) : 속성의 이전 n개의 값을 가져오고 없으면 x로 대체
- first_value(속성) : 속성의 첫번째 값
*/
select *, lead(pr_title) over(order by pr_num asc) as 다음행값 from product;
select 
	row_number() over(order by pr_price desc)
    pr_title, pr_price, 
    lead(pr_price) over(order by pr_price asc) as 다음행값 
from product;
select 
	row_number() over(order by pr_price desc)
    pr_title, pr_price, 
    pr_price - lead(pr_price) over(order by pr_price desc) as 가격차이
from product;
select 
	row_number() over(order by pr_price desc)
    pr_title, pr_price, 
    pr_price - lead(pr_price,1,0) over(order by pr_price desc) as 가격차이
    -- 다음 값이 없을 때 0으로 처리한다는 뜻
    -- pr_price - lead(pr_price,1,pr_price) over(order by pr_price desc) as 가격차이
    -- 값이 없을 때 자기 자신 -> 차이를 굳이 구할필요 없으니 0으로 넣는 식으로 응용 가능
from product;

select 
	row_number() over(order by pr_price desc)
    pr_title, pr_price, 
    pr_price - lag(pr_price,1,pr_price) over(order by pr_price desc) as 가격차이
from product;

select 
	row_number() over(order by pr_price desc)
    pr_title, pr_price, 
    pr_price - first_value(pr_price) over(order by pr_price desc) as 가격차이
    -- 가장 비싼 것과의 가격 차이를 출력
from product;

/*
백분율 구하기
-cume_dise() : 전체 등수 중 상위 몇퍼센트인지 구함. 같은 값가지면 둘다 큰퍼센트로 나옴. 0~1사이 값나오므로 *100해야함.
*/
select 
	row_number() over(order by pr_price desc)
    pr_title, pr_price, 
    cume_dist() over(order by pr_price desc)*100 as 백분율
from product;

-- CTE : 자주 조회하는 값을 CTE로 만들면 마치 테이블처럼 사용가능
select pr_pc_num as 분류번호, avg(pr_price) as 평균가격 
	from product
    group by(pr_pc_num);
    
with product_avg(분류번호, 평균가격)
	as
	(select pr_pc_num, avg(pr_price)
		from product
		group by(pr_pc_num))
select * from product_avg order by 분류번호 asc;
select now(); -- 현재시간 출력 
select 123+345; -- 숫자 출력, 연산도 가능
/*
select 
	[distinct] -- 행의 중복을 제거
	(속성, 식 등) -- ,(콤마)를 통해 여러개 입력 가능
	[from 테이블명]
    [where 조건절] -- 조건에 맞는 결과를 조회함, ==가 아닌 =을, 같지 않은 경우는 !=를 이용!
    -- 속성명1로 정렬(오름차순|내림차순)한 후, 속성명1의 값이 같은 행들을 속성명2로 정렬(오름|내림)
    -- 정렬방법이 생략되면 acs(오름차순)
    [order by 속성명1 [asc | desc], 속성명2 [asc | desc]] 
    -- 검색 결과의 개수를 제한
    -- limit 번지, 개수 : 지정번지부터 개수만큼 
    -- limit 개수 : 0번지부터 개수만큼 
    [limit절]
    ;
*/

use shoppingmall;

-- member테이블의 me_id와 me_pw 조회
select me_id, me_pw
	from member;
-- member 테이블에 있는 모든 회원 정보를 조회 : all을 의미하는 * 를 사용
select * from member;
-- member 테이블에 아이디가 abc인 회원 정보를 조회
select * from member where me_id = 'abc';
-- 관리자의 아이디를 조회
select me_id from member where me_authority='ADMIN';
-- 일반 회원의 아이디를 조회
select me_id from member where me_authority ='MEMBER';
select me_id from member where me_authority !='ADMIN';
select me_id from member where me_authority <>'ADMIN'; -- '다르다'를 뜻하는 비교연산자

-- 회원들을 아이디순 오름차순 정렬
select * from member order by me_id;
-- 회원들을 생일 내림차순 정렬
select * from member order by me_birth desc;

-- 회원들을 아이디순으로 오름차순으로 정렬하여 처음 2명만 조회
select * from member order by me_id limit 2;
select * from member 
	order by me_id 
	limit 0, 2; -- 요걸 추천. 번지 쓰는게 좋음
-- 회원들을 아이디순으로 오름차순으로 정렬하여 2번째 2명만 조회
select * from member order by me_id limit 2, 2;
-- 회원들을 아이디순으로 오름차순으로 정렬하여 3번째 2명만 조회
select * from member order by me_id limit 4, 2;
-- n번째 x개 조회 : limit (n-1)*x, x 

-- 게시글에서 조회수가 1이상 100이하인 게시글들을 조회
select * from board where bo_views>=1 and bo_views<=100;
-- A이상 B이하 :  between A and B
select * from board where bo_views between 1 and 100;
-- 게시글에서 카테고리 번호가 1번(공지사항) 또는 2번(자유)인 게시글 전체를 조회
select * from board where bo_bc_num=1 or bo_bc_num=2;
-- ▶ 속성 in(a,b) : 속성이 a 또는 b 값을 가지는 경우
-- 같은 속성, =으로 비교, 전부 or로 이루어진 비교일때 사용 가능
select * from board where bo_bc_num in(1,2);

/* ▶ 문자열 검색
- =는 두 문자열이 완전히 동일해야 true 
- like를 이용하여 문자열1이 문자열2에 있는지 확인 할 수 있음
	... where 속성명 like 문자열; 
- _ : 한 글자를 의미
	... where bo_title like '___'(언더바 3개) : 제목이 3글자인 게시글들을 가져옴
- % : 0글자 이상을 의미
	... where bo_title like '공지%' : 제목이 공지로 시작하는 모든 게시글
    ... where bo_title like '%공지' : 제목이 공지로 끝나는 모든 게시글
    ... where bo_title like '%공지' : 제목에 공지가 들어가는 모든 게시글
*/
-- 2022년도에 작성된 게시글들을 조회 (날짜를 문자열처럼 취급)
select * from board where bo_reg_date like '2022%';
select * from board where bo_reg_date 
	between '2022-01-01 00:00:00' and '2022-12-31 23:59:59'; -- betweem ~ and ~ 도 사용가능
    
-- ▶ 서브쿼리
-- 카테고리가 공지사항인 게시글들을 조회
select * from board 
	where bo_bc_num = 
		(select bc_num from board_category
			where bc_name = '공지사항');
            
/* ▶ 연산자
ANY : 하나라도 만족하면 true
ALL : 괄호 속 조건을 모두 만족하는 것을 true
=ANY(서브쿼리)는 IN(서브쿼리)로 바꿔 쓸 수 있음)
*/
-- 가전제품(제품카테고리 1번) 중 가장 비싼 제품보다 가격이 높은 제품들
select * from product where pr_price > all(select pr_price from product where pr_pc_num=1);
-- 가전제품(제품카테고리 1번) 중 가장 싼 제품보다 가격이 높은 제품들
select * from product where pr_price > any(select pr_price from product where pr_pc_num=1);
-- 가전제품(제품카테고리 1번)에 있는 제품과 같은 가격의 제품들
select * from product where pr_price = any(select pr_price from product where pr_pc_num=1);
select * from product where pr_price in(select pr_price from product where pr_pc_num=1);

-- 모든 회원의 권한을 출력(권한만 출력하기 때문에 중복이 가능)
select me_authority from member;
select distinct me_authority from member; -- distinct 붙이면 중복된 내용 제거

-- ▶ A as B : A의 이름을 B로 수정(일시적) 
	-- select문의 검색 결과를 출력할 때 보이는 글자를 바꾸는거지 정말로 바꾸려면 DDl-alter문을 써야함
select distinct me_authority as '권한' from member;
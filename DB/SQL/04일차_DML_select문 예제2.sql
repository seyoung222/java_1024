/* 어떠한 상황이 일어났을 때 쿼리문을 어떤식으로 작성해야할지 생각하면서 써야함 */

-- 가전 제품을 조회
select * from product 
	where pr_pc_num=
		(select pc_num from product_category where pc_name='가전');
-- 제품명으로 TV를 검색
select * from product where pr_title like '%TV%';

-- abc 회원이 구매확정한 목록을 조회 : buy 테이블 조회
select * from buy where bu_me_id = 'abc' and bu_state='구매확정';
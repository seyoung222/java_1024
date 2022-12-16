-- 의류 제품들을 높은 가격순으로 조회
select mc_name as 분류, pr_title as 제품명, pr_price as 가격 from product 
	join sub_category on sc_num = pr_sc_num
    join main_category on sc_mc_num = mc_num
    where mc_name='의류'
    order by pr_price desc;
    
-- 의류 제품들을 낮은 가격순으로 조회
	-- 데이터가 많을 경우 join을 많이하면 성능이 떨어짐 
    -- >테이블 대신 select 검색결과를 테이블처럼 사용 (as를 통해 테이블 명명해야함)
select mc_name as 분류, pr_title as 제품명, pr_price as 가격 from product 
	join sub_category on sc_num = pr_sc_num
    join (select * from main_category where mc_name = '의류') as mc on mc_num = sc_mc_num
		-- 이 때 on에서 테이블명을 붙였다면 main_category가 아니라 mc로 바꿔야함!
    order by pr_price asc;

SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
-- 최신버전에 적용된 full group by를 해제해줘야함 

-- 제품명, 제품별 판매량을 조회. 제품별 판매량으로 정렬. 
	-- inner join하면 구매된 항목들만 출력됨 -> outer join사용
select pr_title as 제품명, ifnull(sum(ol_amount),0) as 판매량 from order_list
	right join product_option on po_num = ol_po_num
	right join product on po_pr_num = pr_num
    group by po_pr_num
    order by 판매량 desc;
/* 내가 한거
select pr_title as 제품명, count(ol_num) as '제품별 판매랑' from product
	join product_option on po_pr_num = pr_num
    left join order_list on ol_po_num = po_num
    group by 제품명;
*/

-- 의류 제품 중 제품명, 제품별 판매량을 조회. 제품별 판매량으로 정렬. 
select mc_name as 분류, pr_title as 제품명, ifnull(sum(ol_amount),0) as 판매량 from order_list
	right join product_option on po_num = ol_po_num
	right join product on po_pr_num = pr_num
    join sub_category on sc_num = pr_sc_num
    join main_category on mc_num = sc_mc_num
    where mc_name = '의류'
    group by po_pr_num
    order by 판매량 desc;
-- pr_amount를 판매량으로 재정의(속성추가)하여 반정규화 했을 경우 쿼리 변경 (join을 할 필요가 없어서 쿼리 짧아짐)
select mc_name as 분류, pr_title as 제품명, pr_amount as 판매량 from product
    join sub_category on sc_num = pr_sc_num
    join main_category on mc_num = sc_mc_num
    where mc_name = '의류'
    order by 판매량 desc;
-- 아래는 내가한거.. 틀린듯
select pr_title as 제품명, sum(ol_amount) as 판매량 from order_list
	right join product_option on ol_po_num = po_num
    right join product on pr_num = po_pr_num
    where po_pr_num = 1
    group by pr_num
    order by 판매량 desc;

-- 제품별 최신순으로 조회, 60개씩 보기
select * from product
    order by pr_reg_date desc
    limit 60; -- limit 0, 60

select * from product;
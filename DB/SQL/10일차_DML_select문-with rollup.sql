use shoppingmall;
/*
with rollup: 부분합(분류별 합계 및 총합 구하기) group by의 분류에 따름!!!
*/
select pr_pc_num, sum(pr_amount) from product group by pr_pc_num with rollup;

use cgv;
select ss_mo_num, ss_ci_num, sum(ss_possible_seat) as '예매 가능 좌석'
	from screen_schedule
	group by ss_mo_num, ss_ci_num
    with rollup; -- 가장 아래에 총 예매 가능 좌석의 수를 추가로 표시해줌


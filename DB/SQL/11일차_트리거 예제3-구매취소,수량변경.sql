use shoppingmall;

-- 제품구매를 취소 했을 때 동작하는 트리거
drop trigger if exists update_buy;

delimiter //
create trigger update_buy after update
on buy
for each row
begin
-- 제품 수량을 변경
if new.bu_amount = old.bu_amount and new.bu_state = '구매 취소' then
	update product
		set pr_amount = pr_amount + old.bu_amount 
	where pr_num = new.bu_pr_num; -- 안바뀌므로 new/old 상관없음
else
	if new.bu_amount != old.bu_amount then
		update product
			set pr_amount = pr_amount + old.bu_amount - new.bu_amount
		where pr_num = old.bu_pr_num;
	end if;
end if;	
end //
delimiter ;

update buy set bu_state = '구매 취소' where bu_num = 'abc1';
update buy set bu_amount = 2 where bu_num = 'qwe202212211712';
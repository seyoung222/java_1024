use shoppingmall;

-- 제품번호, 수량, 배송지, 우편번호, 아이디가 주어지면 제품을 구매하는 프로시저를 작성하세요.
drop procedure if exists buy_product;
delimiter //
create procedure buy_product(
	in _num int,
    in _amount int,
    in _address varchar(50),
    in _post_num varchar(10),
    in _id varchar(15)
)
begin
	declare _pr_amount int default 0;
    set _pr_amount = (select pr_amount from product where pr_num = _num);
	if _pr_amount >= _amount then -- 수량이 모자르면 구매하지 않음
		insert buy(bu_num, bu_amount, bu_address, bu_post_num, bu_me_id, bu_pr_num)
		values(concat(_id,left(now(),10)), _amount, _address, _post_num, _id, _num);
		update product set pr_amount = pr_amount - _amount where pr_num = _num;
    end if;
end //
delimiter ;

call buy_product(1, 2, '서울시 강남', '12345', 'asd');

/* 내거
drop procedure if exists buy_product;
delimiter //
create procedure buy_product(
	in _pr_num int,
    in _amount int,
    in _address varchar(50),
    in _post_num char(5),
    in _id varchar(15)
)
begin
	insert buy(bu_state, bu_amount, bu_address, bu_post_num, bu_me_id, bu_pr_num)
    values('구매완료', _amount, _address, _post_num, _id, _pr_num);
end //
delimiter ;

call buy_product(3, 1, '서울 강남구', '12345', 'abc');
*/
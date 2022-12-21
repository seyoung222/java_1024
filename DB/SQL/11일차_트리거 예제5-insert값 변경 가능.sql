use shoppingmall;

-- insert 추가하는 값은 변경 못한다고 했지만 before일때는 바꿀 수 있다.(이전에 조작하는거니까)
drop trigger if exists tt;
delimiter //
create trigger tt before insert
on test
for each row
begin
	set new.name='안녕2';
end //
delimiter ;

insert into test(name) values('홍길동');
select * from shoppingmall.test;
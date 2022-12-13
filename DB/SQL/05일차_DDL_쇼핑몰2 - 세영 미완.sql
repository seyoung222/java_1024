create database if not exists shoppingmall2;
use shoppingmall2;

create table if not exists member(
	me_id varchar(15) primary key,
    me_pw varchar(20) not null,
    me_name varchar(15) not null,
    me_birth date not null,
    me_authority varchar(6) not null default 'MEMBER'
);
-- 문의(문의번호, 답변, 상태, 비번, 내용, 아이디FK, 제품번호FK)
create table if not exists inquiry(
	in_num int auto_increment primary key,
    in_answer longtext,
    in_state varchar(10) not null default '답변 대기',
    in_pw varchar(20) not null,
    in_contents longtext not null,
    in_me_id varchar(15) not null,
    in_pr_num int not null,
    constraint fk_in_me_id foreign key(in_me_id) references member(me_id),
    constraint fk_in_pr_num foreign key(in_pr_num) references product(pr_num)
);
-- 제품(제품번호, 제품명, 상세, 수량, 가격, 중분류번호FK)
create table if not exists product(
	pr_num int auto_increment primary key,
    pr_title varchar(50) not null,
    pr_contents longtext,
    pr_amount int not null,
    pr_price int not null,
    pr_mdc_num int not null,
    constraint fk_pr_mdc_num foreign key(pr_mdc_num) references middle_category(mdc_num)
);
-- 제품대분류(대분류번호, 대분류명)
create table if not exists main_category(
	mac_num int auto_increment primary key,
    mac_name varchar(10) not null
);
-- 제품중분류(중분류번호, 중분류명, 대분류번호FK)
create table if not exists middle_category(
	mdc_num int auto_increment primary key,
    mdc_name varchar(10) not null
    mdc_mac_num int not null,
     constraint fk_pr_mdc_num foreign key(pr_mdc_num) references middle_category(mdc_num)
);
-- 옵션(옵션번호, 옵션명, 수량, 제품번호FK)
create table if not exists category(
	ca_num int auto_increment primary key,
    ca_name varchar(30) not null,
    ca_amount int ,
    ca_pr_num
);
-- 주소록(주소록번호, 기본주소, 상세주소, 우편번호, 이름, 아이디FK)
create table if not exists address_book(
	
);
-- 주문내역(주문번호, 상태, 총가격, 적립P, 사용P, 결제금액, 주소록번호FK, 아이디FK)
create table if not exists order_history(
	
);
-- 주문목록(주문목록번호, 수량, 총가격, 주문번호FK, 옵션번호FK)
create table if not exists order_list(
	
);
-- 장바구니(장바구니번호, 수량, 옵션번호FK, 아이디FK)
create table if not exists shopping_basket(
	
);
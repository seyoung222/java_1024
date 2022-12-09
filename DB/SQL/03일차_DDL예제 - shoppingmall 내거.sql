create database if not exists shoppingmall2;
use shoppingmall2;
create table member(
	id varchar(13) primary key,
    pw varchar(20) not null,
    name varchar(13) not null,
    birth_day varchar(8),
    autority varchar(10)
);
create table product_ategory(
	num int not null primary key,
    name varchar(20) not null
);
create table product(
	product_num int primary key not null,
    product_name varchar(20) not null,
    detail longtext,
    amount int,
    price int not null,
    product_category_num varchar(20) not null,
    foreign key(product_category_num) references product_category(num)
);
create table board(
	num int auto_increment primary key not null,
    title varchar(20) not null,
    contents longtext not null,
    writer varchar(13) not null,
    date varchar(10) not null,
    views int not null,
    category_num int,
    foreign key(category_num) references board_category(num)
);
create table board_category(
	num int auto_increment primary key not null,
    name varchar(20) not null
);
create table buy(
	payment_num int auto_increment primary key not null,
    state varchar(10) not null,
    amount int not null,
    price int not null,
    address varchar(50) not null,
    우편번호
    아이디FK
    제품번호FK
);
create ask(
	문의번호
    답변
    상태
    비번
    내용
    아이디fk
    제폼번호FK
);


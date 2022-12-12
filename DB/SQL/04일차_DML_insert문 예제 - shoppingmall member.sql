use shoppingmall;
/* DB 호출 :
위 DB를 적지 않고, 다른 DB 선택 후에 아래를 전체 실행할 경우 에러 발생(다른 DB에는 member라는 테이블이 없어서)
이런 경우 shoppingmall.member 와 같이 DB명.테이블명 으로 호출해서 사용가능하나 쿼리문 길이가 길어지므로 
처음에 use DB명 해준 뒤에 시작하는게 편하다 */

-- DML - insert
-- insert를 사용해 새로운 멤버를 형성하여 테이블에 추가하는 과정
insert into member(me_id, me_pw, me_name, me_birth, me_authority)
	values('abc','123','홍길동','2000-01-01','MEMBER');

-- 권한을 생략한 멤버 생성 : 권한의 기본값을 MEMBER로 설정해뒀기 때문에 넣지않아도 자동으로 들어감
-- 전체 열명을 나열하지 않아 가능. 단, 생략한 열이 null 허용이거나 기본값이 존재해야함
insert  into member(me_id, me_pw, me_name, me_birth)
	values('qwe','123','고길동','2001-01-01');

-- 열 순서를 바꿔도 가능. 단, 열 순서에 맞게 값 순서를 잘 설정.
insert  into member(me_id, me_name, me_birth, me_pw)
	values('asd','이순신','2002-01-01','123');

-- 열 입력 자체를 전체 생략해도 가능. 단, 테이블 생성 시 추가했던 열 순으로 값들을 입력해야 함.
-- 괄호를 이용해 여러개의 값을 입력 가능
insert into member values('qwew123', 'qwe123', '홍씨', '2003-01-01', 'MEMBER'),
	('zxc123', 'zxc123', '박씨', '2003-02-01', 'ADMIN');

select * from member;


insert into board_category(bc_name) values('공지'),('자유'),('문의');
insert into board(bo_title, bo_contents, bo_me_id, bo_bc_num)
	values('공지 제목1', '내용1', 'qwe', 1);
-- 이때 bo_me_id 작성 시, 외래키를 주의할 것! 작성자는 실제 존재하는 멤버만 가능, 아무거나 넣으면 에러 발생
    
select * from board_category;
select * from board;

-- 이건 insert value문이고 insert select문을 사용하면 특정 데이터를 지정해 속성 추가 가능
-- 워크벤치에서 바로 등록하고 싶으면 아래 뜨는 표에 직접 값을 적어넣고 오른쪽 아래의 apply 누르면 쿼리문이 자동으로 적힘
use small_jik;
-- null 체크는 is null 또는 is not null
select * from member where me_name is null;

-- 문자열에서 특수문자 표현 -> \ 역슬래시 이용
select * from member where me_id like '%\_%'; 
	-- _는 와일드카드로 한글자를 의미. 즉 '_'는 _가 포함된 아이디가 아니라 1글자 이상의 아이디를 가져옴
    
/* 
DML 데이터 조작어(데이터 추가, 수정, 삭제, 조회)
insert : DB 사용자가 컴퓨터 DB에 데이터를 추가(등록)하기 위해 사용하는 언어
select : DB 사용자가 컴퓨터 DB에 등록된 데이터를 조회하기 위해 사용하는 언어
update : DB 사용자가 컴퓨터 DB에 등록된 데이터를 수정하기 위해 사용하는 언어
delete : DB 사용자가 컴퓨터 DB에 등록된 데이터를 삭제하기 위해 사용하는 언어

DDL 데이터 정의어(데이터 객체 생성, 삭제, 수정)
create : DB 객체를 생성(DB, 테이블, 사용자 등) 할때 사용하는 언어
drop : DB 객체를 삭제할 때 사용하는 언어
alter : DB 객체를 수정할 때 사용하는 언어 

DCL 데이터 제어어(사용자 추가, 권한 부여, 트랜젝션 제어 등)
TCL : 트랜젝션 제어어 (기준점을 잡고 작업을 하다 완성하면 커밋으로 확정, 롤백을 통해 기준점으로 되돌아가는 등)

char(10) : 고정 문자열. 10자를 저장 - 주민번호, 우편번호처럼 자리수 고정된 경우 사용
varchar(10) : 가변 문자열. 최대 문자열 10자를 저장 
int(4) : zf옵션을 선택하면 1을 저장할 때 0001로 저장됨

슈퍼키 : 튜플을 유일하게 식별하게 하는 속성들의 집합
후보키 : 슈퍼키에서 불필요한 속성들은 제거한 키 
	=> 최소한의 속성으로 튜플을 유일하게 식별하게 하는 속성들의 집합
기본키 : 후보키에서 선정된 키 => 의미상 적절해야 함.
대리키 : 테이블의 기본키가 복합속성으로 되어있는 경우 일련번호로 대체하는 속성을 기본키로 선정
대체키 : 후보키 중 기본키로 선정되지 못한 키
외래키 : 다른 테이블과 연결하기 위한 키

개체 무결성 : 기본키는 중복될 수 없고, null일 수 없다.
참조 무결성 : 외래키는 null이거나 참조하는 테이블에 이쓴 값만 사용할 수 있다.

not null : 속성값에 null을 허용하지 않음

- 현재시간
now() : mysql
SYSDATE() : 오라클
- 속성값이 null인 경우 처리
ifnull(속성명, 값) : mysql
NVL : 오라클에서 null인 경우 값 처리
- 정수를 날짜로 변환
date_format(날짜, 형식) : mysql
to_date : 오라클
	%Y : 4자리 년도, %y : 2자리 년도
    %M 긴 월(영문), %b 짧은 월(영문)
    %m 숫자월(2자리 고정), %c 숫자월(1자리는 1자리로 표기)
    %d 일(2자리 고정), %e 일(1자리는 1자리로 표기)
    %I 시간(12시간 기준) %H 시간(24시 기준)
    %W 긴 요일(영문), %a 짧은 요일(영문)
*/
select me_birth from member; -- null인 값은 그냥 빈칸으로 뜸
select ifnull(me_birth, '입력없음') as 생일 from member; -- null인 경우 입력없음으로 뜸

select date_format(20221214, '%Y년 %m월 %d일');
select date_format(now(), '%Y-%m-%d %I시');

/* 
- 조건문
decode(속성, 값, 같으면, 다르면) : 오라클
if(조건식, 참, 거짓) : mysql
case
	when 조건식
    then 값
    when 조건식
    then 값
    else 값
end
*/
select pr_title, if(pr_price <20000, '저렴', '비쌈') as '가격' from product;
select pr_title, if(pr_price <20000, '저렴', if (pr_price<30000,'적당','비쌈')) as '가격' from product;
select pr_title,
	case
		when pr_price<20000
        then '저렴'
        when pr_price<30000
        then '적당'
        else '비쌈'
	end as 가격
    from product;
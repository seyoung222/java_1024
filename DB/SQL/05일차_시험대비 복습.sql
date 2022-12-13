use small_jik;
-- null 체크는 is null 또는 is not null
select * from member where me_name is null;

-- 문자열에서 특수문자 표현 -> \ 역슬래시 이용
select * from member where me_id like '%\_%'; 
	-- _는 와일드카드로 한글자를 의미. 즉 '_'는 _가 포함된 아이디가 아니라 1글자 이상의 아이디를 가져옴
    
/* 
insert : DB 사용자가 컴퓨터 DB에 데이터를 추가(등록)하기 위해 사용하는 언어
select : DB 사용자가 컴퓨터 DB에 등록된 데이터를 조회하기 위해 사용하는 언어
update : DB 사용자가 컴퓨터 DB에 등록된 데이터를 수정하기 위해 사용하는 언어
delete : DB 사용자가 컴퓨터 DB에 등록된 데이터를 삭제하기 위해 사용하는 언어

create : DB 객체를 생성(DB, 테이블, 사용자 등) 할때 사용하는 언어
drop : DB 객체를 삭제할 때 사용하는 언어
alert : DB 객체를 수정할 때 사용하는 언어 

char(10) : 고정 문자열 10자를 저장
varchat(10) : 최대 문자열 10자를 저장 
int(4)

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
*/
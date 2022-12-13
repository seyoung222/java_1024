-- test 계정 추가. 비번 설정 없음
create user test;

-- test1 계정 추가. 비번이 1234
create user test1 identified by '1234';

-- test1 계정에게 small_jik DB에 있는 모든 테이블의 select 권한을 부여
	-- '%' : 외부에서 접근 허용, 'localhost' : 내부에서 접근 허용
	-- 다른 컴퓨터의 IP주소를 알면 외부에서도 DB에 접근 가능
	-- grant 각종 권한 on DB명.테이블명 to 권한부여대상@'접근가능범위'
grant select on small_jik.* to test1@'%'; 
-- test1 계정에게 small_jik DB에 있는 모든 테이블의 모든 권한을 부여(grant제외)
grant all privileges on small_jik.* to test1@'%'; 

-- 권한 회수 
revoke all on small_jik.* from test1;

flush privileges;

-- 계정 삭제
drop user test1;
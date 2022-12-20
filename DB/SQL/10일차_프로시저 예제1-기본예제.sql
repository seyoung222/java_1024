/*
drop procedure if exists procedure_name; -- 이미 프로시저가 정의되어 있다면 삭제
delimiter // -- //대신 다른 문자로 대처 가능 (그냥 잘 사용되지 않는 특수문자를 두개 쓴거임)
create procedure procedure_name
([
	[ in| out ] parameter_name
	{ parameter_type | array of parameter_type }, ...
])
[ declare variable_declaration;...[;] ]
begin
	procedure_body_statement;...[;]
end //
delimiter ;
*/

DROP PROCEDURE IF EXISTS TEST1;
DELIMITER //
CREATE PROCEDURE TEST1()
BEGIN
	SHOW DATABASES;
END //
DELIMITER ;

CALL TEST1();


DROP PROCEDURE IF EXISTS TEST2;
DELIMITER //
CREATE PROCEDURE TEST2(
	IN NUM INT,
    OUT OUT_NUM INT
)
BEGIN
	SELECT NUM;
    SET OUT_NUM = NUM; -- =을 대입연산자로 사용하기위에서는 SET을 적어줘야함
END //
DELIMITER ;

SET @T_NUM = 0;
CALL TEST2(2, @T_NUM); -- IN과 OUT을 사용해서 프로시드 이용해보기...
SELECT @T_NUM;
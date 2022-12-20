/*
DBMS : DB 관리하는 시스템
DDL
- CREATE, ALTER, DROP : 개체를 생성, 수정, 삭제
타입
- DATE : 날짜, 년,월,일,시,분,초
- TIME : 시,분,초
- VARCHAR : 문자열
	등 ...
JOIN
- INNER JOIN : 키값을 기준으로 교집합을 출력하는 방법으로, 각 테이블의 NULL값을 포함하지 않음
- OUTER JOIN : 여러 테이블 중에서 한 쪽에 데이터가 있고, 한 쪽에는 데이터가 없는 경우 모두 출력하는 방법
문자열 함수
- CONCAT : 문자열 이어 붙이기
- SUBSTRING : 부분 문자열 추출
테이블
- 데이터를 일종의 표로 표현한 것
RESULT SET
- SELECT를 이용하여 조회한 결과를 표로 나타낸 것

문자열을 날짜로 변환
EX) 20221222 -> 2022년 12월 22일
*/
select date_format('20221222', '%Y년 %m월 %d일');
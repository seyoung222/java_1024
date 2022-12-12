/*
-- delete문 : 조건절에 해당하는 행을 전부 삭제함
delete from 테이블명 where 조건절;
-- 조건절에는 일반적으로 기본키가 옴
-- 조건절 생략하면 전체삭제됨
*/

-- 3번 카테고리인 문의 카테고리를 제거하는 쿼리문 작성.
delete from board_category where bc_num = 3;

select * from board_category;
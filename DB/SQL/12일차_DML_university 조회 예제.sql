-- 2022년 1학기 컴퓨터 개론을 수강하는 학생들 명단 조회
select co_st_num as 학번, st_name as 수강생 from course
	join student on co_st_num = st_num
    join lecture on co_le_num = le_num
	where le_name like '컴퓨터 개론' and le_year = 2022 and le_term like 1;
    
-- 2022년 1학기에 2022160001 학생이 수강한 과목명을 조회
select le_num as 강의번호, le_name as 강의명 from course
    join lecture on co_le_num = le_num
	where co_st_num like '2022160001' ;
    
-- 2022년 1학기 컴퓨터 개론을 수강하는 학생이름과 학점을 조회
select co_st_num as 학번, st_name as 수강생, co_grade as 학점 from course
	join student on co_st_num = st_num
    join lecture on co_le_num = le_num
	where le_name like '컴퓨터 개론' and le_year = 2022 and le_term like 1;
    
-- 컴퓨터 공학부 학생명단과 교수명단을 함께 조회 (union)
select pr_name as 이름, '교수' as 직위 from professor where pr_de_num = 160
    union
select st_name as 이름, '학생' as 직위 from student
	join major on ma_st_num = st_num
    where ma_de_num = 160;
    
-- 2022106001 학생의 성적을 조회
select * from course where co_st_num = '2022160001';

-- 2022160001 학생의 2022년 1학기 평균 학점을 조회
-- A: 4.5, B: 3.5, C: 2.5, D: 1.5, F:0
-- (4.5*3 + 1.5*3) / (3+3) = 3.0
select sum(
	case 
		when co_grade like 'A' then 4.5
        when co_grade like 'B' then 3.5
        when co_grade like 'C' then 2.5
        when co_grade like 'D' then 1.5
        when co_grade like 'F' then 0
    end * le_point) / sum(le_point) as 평점 from course 
	join lecture on co_le_num = le_num
    where co_st_num = '2022160001' and le_year = 2022 and le_term = '1';
        

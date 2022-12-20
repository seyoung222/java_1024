use shoppingmall;
-- 내장함수

-- concat(문자열1, 문자열2, ...) : 이어붙여줌
-- 제품명에 TV가 들어가는 제품들을 조회
select * from product where pr_title like '%TV%';
select * from product where pr_title like concat('%','TV','%'); -- 검색할 때 'TV'자리에 변수 응용 가능
set @a = 'TV';
select * from product where pr_title like '%@a%'; -- 제대로 된 검색X
select * from product where pr_title like concat('%',@a,'%');

-- format(숫자, 소수점자릿수) : 소수점자릿수까지 표현하고 세자리마다 , 붙여줌 (금액표현에 많이 사용)
select format(1000000,0);

-- left(문자열, 길이), right(문자열, 길이) : 왼/오른쪽에서 길이만큼 추출 (확장자 추출에 많이 사용)
select right('text.txt', 3);

-- adddate, subdate: 날짜 기준으로 차이를 더하거나 뺀 날짜를 반환(결제일로부터 기간 제한 설정, 특정 기간 전 조회 등...)
select adddate(now(), interval 1 day);
select adddate(now(), interval 1 week);
select adddate(now(), interval 1 month);
select subdate(now(), interval 1 year);
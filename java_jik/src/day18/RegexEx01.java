package day18;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegexEx01 {

	public static void main(String[] args) {
		String regex = "^...$";
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		boolean res = Pattern.matches(regex, str);
		if(res)
			System.out.println(str+"은 3글자로 되어있습니다.");
		else
			System.out.println(str+"는 3글자가 아닙니다.");
		scan.close();
	}
}

		/*
		정규표현식 Regex : Regular expression
		- 문자열의 패턴을 파악하기 위한 식
		- 회원가입: 아이디, 비번체크, 이메일주소, 전화번호 확인
		- regex101 사이트에서 간단하게 확인 가능
		메타문자
		- \d : 숫자 한글자
		- \s : 공백, 탭, 엔터
		- . (점) : 글자 하나(공백, 영한숫특수문자 상관x)
		- \. : 실제 .(점)을 나타냄
		- [문자들] : [ ] 안에있는 문자 한글자만 허용
		- [a-zA-Z0-9ㄱ-힣?!\] : a부터 z까지, A부터 Z까지, 0부터 9까지, 한글 등
		- ^[ 문자들 ] : 문장의 시작
		- [ ^문자들 ] : 해당 문자들 빼고 해당
		- [문자들]$: 문장의 끝
		ex) ^[^abc]$ : abc가 아닌 한글자
		- + : 1개 이상 ex) ^a[a-z]+$ : a로 시작하고 2글자 이상
		- ? :  0개 또는 1개
		- * : 0개 이상
		- {숫자} : 반복  
			ex) [a-zA-Z]{2,10} : 2글자에서 10글자/ {2, } : 2글자 이상
		- ( 값1 | 값2 ) : 패턴분석. 값1 또는 값2만 인정함
			ex) ^(02|010)$ : 02나 010만 찾음
		
		예제 1)
		http://
		https://
		둘다 url로 인식되도록 정규표현식으로 작성
		: ^https?://$
		
		예제 2)
		문자열이 다음과 패턴이 되도록 정규 표현식을 작성
		2022-11-16
		: ^\d{4}-\d{2}-\d{2}$
		
		*/
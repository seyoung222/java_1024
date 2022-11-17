package day19;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegexEmailEx01 {

	public static void main(String[] args) {
		/* 이메일 주소를 입력받아 이메일 형식이 맞는지 확인하는 코드를 작성하세요.
		 * 단, 정규표현식 이용.
		 * abc123@naver.com
		 * abc123@xxx.or.kr
		 * */
		/*String regex = "^[a-zA-Z0-9\-_]+@[a-z0-9]+(\.com|\.or\.kr)$";*/
		String regex = "^[a-zA-Z0-9\\-_]+@[a-zA-Z0-9\\-]{2,}(\\.[a-zA-Z]+){1,2}$";
		

		Scanner scan = new Scanner(System.in);
		System.out.print("이메일 입력> ");
		String email = scan.next();
		boolean res = Pattern.matches(regex, email);
		if(res)
			System.out.println(email+"은 이메일 형식입니다.");
		else
			System.out.println(email+"은 이메일 형식이 아닙니다.");
		scan.close();
	}
	
}

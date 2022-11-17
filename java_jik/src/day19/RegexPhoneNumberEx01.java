package day19;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegexPhoneNumberEx01 {

	public static void main(String[] args) {
		/* 전화번호 정규표현식을 작성하여 입력받은 번호가 전화번호가 맞는지 출력하는 코드를 작성
		 * 규칙1. 
		 * 010-1234-5678 [o]
		 * 01012345678   [x]
		 * 011-1234-5678 [x]
		 * */
//		String regex = "^010-[0-9]{4}-[0-9]{4}$";
		String regex = "010(-\\d{4}){2}";

		Scanner scan = new Scanner(System.in);
		System.out.print("전화번호 입력> ");
		String str = scan.next();
		
		boolean res = Pattern.matches(regex, str);
		if(res)
			System.out.println(str+"은 전화번호입니다.");
		else
			System.out.println(str+"은 전화번호가 아닙니다.");
		scan.close();
	}

}

package day18;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegexEx02 {

	public static void main(String[] args) {
		String idRegex = "^[a-z0-9_\\-]{5,20}$"; //역슬래쉬 여기서 두개해야함..
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 입력> ");
		String id = scan.next();
		if(Pattern.matches(idRegex, id))
			System.out.println("올바른 아이디입니다.");
		else
			System.out.println("아이디는 5~20자의 영문소문자, 숫자와 특수기호 -와 _로 이루어져야합니다.");
		scan.close();
	}
}
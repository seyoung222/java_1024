package day02;

public class PrintEx01 {

	public static void main(String[] args) {
		/* println() : 원하는 값을 출력한 후 엔터, ()안의 값을 생략 가능
		 * print() : 원하는 값을 출력, ()안의 값을 생략 불가
		 * 문자열 + 정수 => 문자열
		 * 정수 + 문자열 => 문자열
		 * 문자 + 문자열 => 문자열
		 */
		String name = "홍길동";
		int age = 20;
		System.out.println(name+"의 나이는 "+age+"살 입니다.");
		
		int num1 = 10;
		int num2 = 20;
		char operator = '+'; //아스키코드로 43이라 숫자와 계산하면 숫자로 인식
		// 10+20 이 출력하도록 해보세요
		System.out.println("" + num1 + operator + num2);
//""+num1이 문자열이므로 거기에 operator을 더하면 +로 출력, num1에 바로 operator 더하면 숫자로 인식
		
	}
}

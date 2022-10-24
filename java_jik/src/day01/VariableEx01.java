package day01;

public class VariableEx01 {

	public static void main(String[] args) {
		/* - 변수선언
		 * 자료형 변수명;
		 * 자료형 변수명 = 초기값;
		 * - 자료형 
		 */
		// 기본 자료형
		boolean isEven = false; //참거짓이므로 물어보는 형태로 많이 지음
		char operator = '+'; // 따옴표와 쌍따옴표 구분할 것!
		int age = 30;
		float pi1 = 3.1415f; // float는 접미사 f나 F를 반드시 붙여야한다.
		double pi2 = 3.1415;
		// 참조형 문자열
		String str = "Hi"; 
		
		// 사과의 개수를 저장하기 위한 변수를 선언해보세요.
		int appleCount;
		// 학생 평군 성적을 저장하기 위한 변수를 선언해보세요.
		double scoreAvg;
		
		int num;
//		double num;// Duplicate local variable num; 중복된 변수를 선언
		int NUM; // 대소문자 구분
		int num1;
//		int 1num; // 숫자로 시작할 수 없다.
		int _num, $num;
//		int !num2; // _와 & 외의 특수문자 사용
//		int int; // 예약어 사용
		
		/* 관례 : 안지켜도 되지만 많은 개발자들이 지킴
		 * - 변수는 소문자로 시작
		 * - 카멜 표기법을 따라서 두번째 글자부터 첫글자를 대문자로
		 * - 클래스와 인터페이스는 대문자로 시작
		 */
	}

}

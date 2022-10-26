package day03;

import java.util.Scanner;

public class SwitchEx02 {

	public static void main(String[] args) {
		/*	switch(식 또는 변수명){
		 * case 값1:		if(식==값1)
		 * 		실행문;
		 *  	break;
		 * case 값2:		else if(식==값2)
		 *  	실행문2;
		 *  	break;
		 * default:		else
		 * 		실행문3;
		 * }
		 * - break가 없으면 다음 case의 실행문으로 이동(다음 case의 값과 달라도 이동)
		 *   여러 경우를 같이 처리하고 싶으면
		 * 	 case 값1, 값2, 값3
		 * 	 case 값1: case 값2: case 값3:
		 * 
		 * 두 정수와 산술연산자를 입력받아 산술연산결과를 출력하는 코드를 switch문으로 작성하세요 
		 */
		Scanner scan = new Scanner(System.in);
		System.out.print("두 정수와 산술연산자를 입력하세요> ");
		int num1 = scan.nextInt();
		char op = scan.next().charAt(0);
		int num2 = scan.nextInt();
		
		
		switch(op) {
		case '+':
			System.out.println(""+num1+op+num2+"="+(num1+num2));
			break;
		case '-':
			System.out.println(""+num1+op+num2+"="+(num1-num2));
			break;
		case '*':
			System.out.println(""+num1+op+num2+"="+(num1*num2));
			break;
		case '/':
			System.out.println(""+num1+op+num2+"="+(num1/(double)num2));
			break;
		case '%':
			System.out.println(""+num1+op+num2+"="+(num1%num2));
			break;
		default:
			System.out.println("산술연산자를 잘못 입력하셨습니다.");
		}
		
		scan.close();
	}
}

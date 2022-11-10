package day14;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionEx02 {

	public static void main(String[] args) {
		/* Scanner를 통해 정수를 입력받아 입력받은 정수를 출력하는 코드를 작성하세요.
		 * 정수 대신 문자열을 입력하면 예외가 발생하는데, 이 예외를 처리하는 코드를 작성하세요.*/
		
		Scanner scan = new Scanner(System.in);
		System.out.print("정수 입력> ");
		//catch문 밖에서도 num 쓰고 싶다면 여기서 int num=0; 선언/초기화 필요
		try {
			int num = scan.nextInt();
			System.out.println("입력된 정수: "+num);
		}catch(InputMismatchException e) {
			System.out.println("정수 입력에 실패했습니다. 다시 입력하세요.");
		}catch(Exception e) { //예외클래스명을 잘 모르면 그냥 Exception을 치면 됨
			System.out.println("정수를 입력하세요.");
		}
		
		scan.close();
	}

}

package day14;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionEx03 {

	public static void main(String[] args) {
		/* 다음과 같은 메뉴를 출력하고 정수를 입력받는 코드를 작성하세요.
		 * 단, 종료를 선택하면 프로그램이 종료되고, 예외처리를 적용하여 정수가 아닌 문자열이
		 * 입력돼도 프로그램이 계속 진행되도록 하세요
		 * 메뉴
		 * 1. 플레이
		 * 2. 기록확인
		 * 3. 종료
		 * 메뉴 선택: 
		 * */
		
		int menu=-1;
		Scanner scan = new Scanner(System.in);
		do {
			printMenu();
			try {
				menu = scan.nextInt();
			}catch(Exception e) {
				System.out.println("입력이 잘못되었습니다. 정수를 입력하세요.");
				scan.nextLine(); //입력버퍼에 남은 값을 초기화해줌
			}
			
		}while(menu != 3);
		
		
		/*내가 한거
		int menu=0;
		while(menu!=3) {
			printMenu();
			
			Scanner scan = new Scanner(System.in);
			try {
				menu=scan.nextInt();
				if(menu==1) {
					System.out.println("게임을 플레이합니다.");
				}else if(menu==2) {
					System.out.println("기록을 확인합니다.");
				}else if(menu==3) {
					break;
				}	
			}catch(InputMismatchException e){
				System.out.println("메뉴를 정수로 입력하세요");
				continue;
			}
		}
		System.out.println("프로그램이 종료되었습니다.");
		*/
	}
	public static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 플레이");
		System.out.println("2. 기록 확인");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택> ");
	}
}

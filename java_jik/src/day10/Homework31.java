package day10;

import java.util.Scanner;

public class Homework31 {

	public static void main(String[] args) {
		/* 컴퓨터랑 베라31게임하기 
		 * 메뉴
		 * 1. 플레이
		 * //컴퓨터가 랜덤 1~3개의 숫자를 부름
		 * //사용자가 몇개의 숫자를 부를지 입력
		 * 2. 기록확인
		 * 3. 종료
		 * */
		/* 예시
		 * [컴퓨터] 1 2 3 
		 * 입력> 2 
		 * [사용자] 4 5 
		 * [컴퓨터] 6 
		 * 입력> 3 
		 * [사용자] 7 8 9 
		 * */
		int menu;
		int comWin=0, userWin=0;
		do {
			printMenu();
			menu=inputInt();
			runMenu(menu, comWin, userWin);
		}while(menu!=3);
	}
	
	
	
	public static void runMenu(int menu, int comWin,int userWin) {
		switch(menu) {
		case 1:
			
			int [] game;
			int lastNum=0;
			
			do{
				//31칸짜리 배열을 만들고
				game = new int[31];
				//컴퓨터가 1~3 랜덤숫자를 입력, lastNum리턴
				//31넘는지 확인 => 사용자 1승 추가
				if(lastNum>=31) {
					userWin++;
					break;
				}
				//사용자가 1~3 랜덤숫자를 입력, lastNum리턴
				//31넘는지 확인 => 컴퓨터 1승 추가
				if(lastNum>=31) {
					comWin++;
					break;
				}
			}while(lastNum<31);
			//컴퓨터
			break;
		case 2:
			printScore(comWin, userWin);
			break;
		case 3:
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	
	//주어진 배열에서 비어있는 칸부터 1~3랜덤수를 이어서 채워넣고, 채워넣은 숫자들을 출력하는 메소드
	public static int callNum(int[]game) {
		int lastNum;
		
		return 0;
	}
	
	public static void printScore(int comWin, int userWin) {
		System.out.println("*** 현재까지 스코어 ***");
		System.out.println("컴퓨터: "+comWin+"승");
		System.out.println("사용자: "+userWin+"승");
	}



	public static int random(int min,int max) {
		return (int)(Math.random()*(max-min+1)+min);
	}
	
	public static int inputInt() {
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}

	public static void printMenu() {
		System.out.println("------------");
		System.out.println("   메뉴");
		System.out.println("1. 플레이");
		System.out.println("2. 기록 확인");
		System.out.println("3. 종료");
		System.out.println("------------");
		System.out.print("메뉴를 선택하세요> ");
	}
}

	

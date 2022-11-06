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
			int winner = runMenu(menu, comWin, userWin);
			//게임 후 리턴된 값이 1이면 컴 1승추가, 2면 사용자 1승추가
			if(winner==1) {
				comWin++;
			} else {
				userWin++;
			}
			
		}while(menu!=3);
	}
	
	
	
	public static int runMenu(int menu, int comWin,int userWin) {
		switch(menu) {
		case 1:
			//게임을 플레이하고 컴퓨터가 이기면 1, 사용자가 이기면 2를 리턴
			int winner = newGame();
			if(winner==1) {
				return 1;
			} else {
				return 2;
			}
		case 2:
			printScore(comWin, userWin);
			return 0;
		case 3:
			System.out.println("프로그램을 종료합니다.");
			return 0;
		default:
			System.out.println("잘못된 메뉴입니다.");
			return 0;
		}
	}
	
	
	private static int newGame() {
		int [] game=new int[31];
		int lastIndex=-1;
		boolean doesComWin=false;
		
		do{
			//컴퓨터가 1~3 랜덤숫자를 입력, lastIndex갱신
			int callNum = random(1,3);
			System.out.print("[컴퓨터] ");
			for(int i=lastIndex+1; i<=lastIndex+callNum; i++) {
				if(i<31) {
					game[i]=i+1;
					System.out.print(game[i]+" ");
				} else if(i>=31) {
					break;
				}
			}
			System.out.println();
			lastIndex += callNum;
			//31넘는지 확인 => 사용자 1승 추가
			if(lastIndex>=30) {
				System.out.println("사용자가 승리하였습니다.");
				doesComWin=false;
				break;
			}
			
			//사용자가 1~3 랜덤숫자를 입력, lastIndex갱신
			Scanner scan = new Scanner(System.in);
			System.out.print("입력> ");
			callNum = scan.nextInt();
			System.out.print("[사용자] ");
			for(int i=lastIndex+1; i<=lastIndex+callNum; i++) {
				if(i<31) {
					game[i]=i+1;
					System.out.print(game[i]+" ");
				} else if(i>=31) {
					break;
				}
			}
			System.out.println();
			lastIndex += callNum;
			//31넘는지 확인 => 컴퓨터 1승 추가
			if(lastIndex>=30) {
				System.out.println("컴퓨터가 승리하였습니다.");
				doesComWin=true;
				break;
			}
		}while(lastIndex<30);
		System.out.println("게임을 종료합니다.");
		
		return doesComWin ? 1 : 2;
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

	

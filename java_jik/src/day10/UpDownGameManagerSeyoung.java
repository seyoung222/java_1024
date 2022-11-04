package day10;

import java.util.Scanner;

import day08.MethodBaseballGameEx01;

public class UpDownGameManagerSeyoung {

	public static void main(String[] args) {
		/* UpDown게임을 구현하세요.
		 * 1. 플레이
		 * 2. 기록 확인
		 * 3. 종료
		 * */
		
		int menu;
		Record[] records = new Record[5];
		do {
		//메뉴 출력
		printMenu();
		//메뉴 선택
		menu = selectMenu();
		//메뉴에 따른 실행
		runMenu(menu,records);
			//1. 플레이
			//랜덤수를 생성
			//입력값이 랜덤수보다 작으면 up, 크면 down, 맞으면 게임 종료
			//기록 등록
			//2.기록확인
			//3. 종료
		}while(menu!=3);
	}

	private static void runMenu(int menu, Record[]records) {
		switch(menu) {
		case 1:
			int answer =MethodBaseballGameEx01.random(1,100);
			System.out.println("답: "+answer);
			int num, playTime=0;
			do {
				playTime++;
				Scanner scan=new Scanner(System.in);
				System.out.print("정수를 입력하세요(1~100)> ");
				num= scan.nextInt();
				if(num==answer) {
					break;
				} else if(num>answer) {
					System.out.println("Down");
				} if(num<answer) {
					System.out.println("UP");
				}
			}while(true);
			System.out.println("게임을 종료합니다.");
			
			int rank=countRegisters(records);
			int score=getWorstRecord(records);
			if(rank<5 || score>playTime) {
				addRecord(playTime,records);
			}
			break;
		case 2:
			printResult(records);
			break;
		case 3:
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			System.out.println("메뉴 선택이 잘못되었습니다.");
		}
	}
	private static void addRecord(int playTime, Record[]records) {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름을 입력하세요> ");
		String name = scan.next();
		Record r = new Record(playTime, name); 
		
		int index=0;
		for(Record record : records) {
			if(record!=null && playTime>=record.getCount()) {
				index++;
			}
		}
		
		for(int i=records.length-1; i>index; i--) {
//			if(records[i]!=null) { //비어있어도 뒤로 밀어도 되니까 이거 쓸 필요없음
				records[i] = records[i-1];
//			}
		}
		records[index]=r;
	}
	
	private static int countRegisters(Record[] records) {
		int num=0;
		for(Record record : records) {
			if(record != null) {
				num++;
			}
		}
		return num;
	}

	private static int getWorstRecord(Record[] records) {
		int worst=0;
		for(Record record : records) {
			if(record != null) {
				worst = record.getCount();
			}
		}
		return worst;
	}
	
	private static void printResult(Record[]records) {
		for(int i=0; i<records.length; i++) {
			System.out.print((i+1)+"위. ");
			if(records[i]!=null) {
				records[i].print();
			} else {
				System.out.println();
			}
		}
	}

	private static int selectMenu() {
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}

	private static void printMenu() {
		System.out.println("------------");
		System.out.println("   메뉴");
		System.out.println("1. 플레이");
		System.out.println("2. 기록 확인");
		System.out.println("3. 종료");
		System.out.println("------------");
		System.out.print("메뉴를 선택하세요> ");
	}

}

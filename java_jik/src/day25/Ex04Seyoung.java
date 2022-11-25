package day25;

import java.util.Scanner;

public class Ex04Seyoung {
	/* 컴퓨터와 가위 바위 보를 하는 프로그램을 작성하세요.
	 * 예:
	 * 사용자: 가위
	 * 컴퓨터: 보
	 * 사용자가 이겼습니다. 더 하시겠습니까?(y/n) : y
	 * 사용자 : 가위
	 * 컴퓨터 : 주먹
	 * 컴퓨터가 이겼습니다. 더 하시겠습니까?(y/n) : n
	 * */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char again=0;
		do {
			System.out.print("사용자 : ");
			String userPick = scan.next();
			String comPick = RockSissorPaper();
			System.out.println("컴퓨터: "+comPick);
			again = winOrLose(userPick,comPick);
		}while(again=='y');
	}
	private static char winOrLose(String userPick, String comPick) {
		Scanner scan = new Scanner(System.in);
		switch(userPick) {
		case "가위":
			switch(comPick) {
			case "가위":
				System.out.print("무승부입니다. 더 하시겠습니까?(y/n) : ");
				return scan.next().charAt(0);
			case "바위":
				System.out.print("컴퓨터가 이겼습니다. 더 하시겠습니까?(y/n) : ");
				return scan.next().charAt(0);
			case "보":
				System.out.print("사용자가 이겼습니다. 더 하시겠습니까?(y/n) : ");
				return scan.next().charAt(0);
			}
			break;
		case "바위":
			switch(comPick) {
			case "가위":
				System.out.print("사용자가 이겼습니다. 더 하시겠습니까?(y/n) : ");
				return scan.next().charAt(0);
			case "바위":
				System.out.print("무승부입니다. 더 하시겠습니까?(y/n) : ");
				return scan.next().charAt(0);
			case "보":
				System.out.print("컴퓨터가 이겼습니다. 더 하시겠습니까?(y/n) : ");
				return scan.next().charAt(0);
			}
			break;
		case "보":
			switch(comPick) {
			case "가위":
				System.out.print("컴퓨터가 이겼습니다. 더 하시겠습니까?(y/n) : ");
				return scan.next().charAt(0);
			case "바위":
				System.out.print("사용자가 이겼습니다. 더 하시겠습니까?(y/n) : ");
				return scan.next().charAt(0);
			case "보":
				System.out.print("무승부입니다. 더 하시겠습니까?(y/n) : ");
				return scan.next().charAt(0);
			}
		}
		return 'n';
	}
	public static String RockSissorPaper() {
		int r = (int)(Math.random()*3+1);
		String choise;
		switch(r) {
		case 1:
			choise = "가위";
			return choise;
		case 2:
			choise = "바위";
			return choise;
		case 3:
			choise = "가위";
			return choise;
		default: System.out.println("오류");
		}
		return null;
	}

}

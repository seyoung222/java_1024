package day18;

import java.util.Scanner;

public class EnumEx02 {

	public static void main(String[] args) {
		/* 오늘의 요일을 영어로 입력받아 한글로 출력하는 코드를 작성하세요.
		 * 예
		 * 요일: WEDNESDAY
		 * 수요일
		 * */
		Scanner scan = new Scanner(System.in);
		System.out.print("요일: ");
		String day = scan.next();
		switch(day) {
			case "MONDAY": 		System.out.println("월요일"); break;
			case "TUESDAY": 	System.out.println("화요일"); break;
			case "WEDNESDAY": 	System.out.println("수요일"); break;
			case "THURSDAY": 	System.out.println("목요일"); break;
			case "FRIDAY": 		System.out.println("금요일"); break;
			case "SATURDAY": 	System.out.println("토요일"); break;
			case "SUNDAY": 		System.out.println("일요일"); break;
			default: 			System.out.println("잘못 입력했습니다.");
		}
		Week week = Week.valueOf(day);
		try {
			switch(week) {
				case MONDAY: 	System.out.println("월요일"); break;
				case TUESDAY: 	System.out.println("화요일"); break;
				case WEDNESDAY: System.out.println("수요일"); break;
				case THURSDAY:	System.out.println("목요일"); break;
				case FRIDAY:	System.out.println("금요일"); break;
				case SATURDAY:	System.out.println("토요일"); break;
				case SUNDAY: 	System.out.println("일요일"); break;
				//default가 나올 수 없어서 적을 필요 없는 대신.. 예외처리 따로 해야할 것...
			}
		}catch(Exception e) {
			System.out.println("잘못 입력했습니다.");
		}
		
		
		/*내가한거
		Scanner scan = new Scanner(System.in);
		System.out.print("요일> ");
		Week input= Week.valueOf(scan.next());
		switch(input) {
		case MONDAY:
			System.out.println("월요일");
			break;
		case TUESDAY:
			System.out.println("화요일");
			 break;
		case WEDNESDAY:
			System.out.println("수요일");
			break;
		case THURSDAY:
			System.out.println("목요일");
			break;
		case FRIDAY:
			System.out.println("금요일");
			break;
		case SATURDAY:
			System.out.println("토요일");
			break;
		case SUNDAY:
			System.out.println("일요일");
			break;
		default:
			System.out.println("입력값이 잘못되었습니다.");
		}
		scan.close();
		*/
	}

}
enum Week{
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
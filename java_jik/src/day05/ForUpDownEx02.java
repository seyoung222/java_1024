package day05;

import java.util.Scanner;

public class ForUpDownEx02 {

	public static void main(String[] args) {
		/* 랜덤한 정수를 맞추는 UP DOWN 게임
		 * 예시 : (생성한 랜덤한 수 70)
		 * 1~100사이의 랜덤한 수를 맞추세요.
		 * 숫자를 입력하세요 : 50
		 * UP
		 * 숫자를 입력하세요 : 75
		 * DOWN
		 * 숫자를 입력하세요 : 70
		 * 정답입니다.
		 * 더 하시겠습니까?(y/n) : y
		 * 숫자를 입력하세요 : 75
		 * DOWN
		 * 숫자를 입력하세요 : 70
		 * 정답입니다.
		 * 더 하시겠습니까?(y/n) : n
		 * 프로그램을 종료합니다.
		 * */
		
		/* 내가한거(강사님 풀이 확인할 것!!)
		int r, num;
		char replay = 'y';
		Scanner scan = new Scanner(System.in);
		
		while(replay != 'n') {
			int min = 1, max = 100;
			r = (int)(Math.random()*(max-min+1)+1);
			System.out.println(r);
		
			num = min-1;
			for( ; num!=r ; ) {
				
				System.out.print("1~100사이 랜덤한 수를 맞추세요> ");
				num = scan.nextInt();
				if(num==r) {
					System.out.println("정답입니다.");
					System.out.print("더 하시겠습니까?(y/n) > ");
					replay = scan.next().charAt(0);
					break;
				} else if(num>r) {
					System.out.println("DOWN");
				} else {
					System.out.println("UP");
				}
			}
			if(replay=='n') {
				System.out.println("종료합니다.");
				break;
			} else {
				continue;
			}
		}
		scan.close();
		*/
		
		
		// 강사님 풀이
		int r, num;
		int min = 1, max = 100;
		Scanner scan = new Scanner(System.in);
		
		for( ; ; ) { //무한반복에서 if문만 추가
			
			r = (int)(Math.random()*(max-min+1)+1);
			System.out.println(min+"~"+max+"사이의 랜덤한 수를 맞추세요.");
			System.out.println(r);
		
			num = min-1;
			for( ; num!=r ; ) {
				System.out.print("숫자를 입력하세요> ");
				num = scan.nextInt();
				if(num==r) {
					System.out.println("정답입니다.");
				} else if(num>r) {
					System.out.println("DOWN");
				} else {
					System.out.println("UP");
				}
			}
			System.out.print("더 하시겠습니까?(y/n) > ");
			if(scan.next().charAt(0)=='n') { //한번만 쓰이는 수라 변수에 저장x
				break;
			}
		}
		System.out.println("종료합니다.");
		scan.close();
		
		
	}
}

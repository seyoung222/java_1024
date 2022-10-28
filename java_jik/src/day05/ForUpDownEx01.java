package day05;

import java.util.Scanner;

public class ForUpDownEx01 {

	public static void main(String[] args) {
		/* 랜덤한 수를 생성하여 해당 수를 맞추는 코드를 작성하세요.
		 * 예시 : (생성한 랜덤한 수 70)
		 * 1~100사이의 랜덤한 수를 맞추세요.
		 * 숫자를 입력하세요 : 50
		 * UP
		 * 숫자를 입력하세요 : 75
		 * DOWN
		 * 숫자를 입력하세요 : 70
		 * 정답입니다.
		 * */
		
		//랜덤한 수 생성
		
		/* 랜덤수를 맞추기
		 * 반복횟수 : 맞출때까지=>랜덤수 r과 입력한 수가 일치하지 않을 때까지
		 * 규칙성 : 
		 *  -1. 숫자를 입력하세요 : 안내문구 출력
		 *  -2. 정수를 입력 받음
		 *  -3. 입력받은 정수가 r과 같으면 정답입니다. 출력
		 *  	r보다 크면 DOWN, r보다 작으면 UP을 출력
		 * 반복분 종료 후 : 없음
		 * */
		
		/* 내가 한거 (풀이볼것~)
		int min=1; int max=100;
		int r = (int)(Math.random()*(max-min+1)+min);
		
		Scanner scan = new Scanner(System.in);
		System.out.print("1~100사이의 랜덤한 수를 맞추세요> ");
		int input = scan.nextInt();
		
		while(input != r) {
			if(input==r) {
				System.out.println("정답입니다.");
				break;
			} else if(input>r) {
				System.out.println("DOWN");
			} else {
				System.out.println("UP");
			}
			System.out.print("1~100사이의 랜덤한 수를 맞추세요> ");
			input = scan.nextInt();
		}
		
		scan.close();
		*/
		
		
		// 강사님 풀이
		int min=1; int max=100;
		int r = (int)(Math.random()*(max-min+1)+min);
		System.out.println(r);
		
		int num = min -1;
		Scanner scan = new Scanner(System.in);
		for( ; r != num ; ) {
			//1. 숫자를 입력하세요 : 안내문구 출력
			System.out.print("숫자를 입력하세요: ");
			//2. 정수를 입력받음
			num = scan.nextInt();
			//3. 입력받은 정수가 r과 같으면 정답입니다/크면 DOWN/작으면 UP 출력
			if(num==r) {
				System.out.println("정답입니다.");
			} else if(num>r) {
				System.out.println("DOWN");
			} else {
				System.out.println("UP");
			}
		}
		
		scan.close();
	}
}

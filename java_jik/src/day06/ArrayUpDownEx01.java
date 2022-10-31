package day06;

import java.util.Scanner;

public class ArrayUpDownEx01 {

	public static void main(String[] args) {
		/* UpDown 게임을 무조건 3번 반복해서 플레이하고,
		 * 각 게임당 맞춘 횟수를 배열에 저장하여 출력하는 코드를 작성하세요.
		 * */
		
		int coin = 3;
		int record[] = new int[coin];
		int min=1, max=100;
		Scanner scan = new Scanner(System.in);
		while(coin-->0) {
			int r=(int)(Math.random()*(max-min+1)+min);
			System.out.println(r);
			System.out.println(min+"~"+max+"사이 랜덤한 수를 맞추세요.");
			int num;
			int tryCount=0;
			do { 
				tryCount++;
				System.out.print("숫자 입력 : ");
				num = scan.nextInt();
				if(r==num) {
					System.out.println("정답입니다.");
					record[record.length-coin-1]=tryCount;
				} else if(r>num) {
					System.out.println("UP");
				} else {
					System.out.println("DOWN");
				}
			}while(r!=num);
		}
		for(int i=0; i<record.length; i++) {
			System.out.println((i+1)+"번째 게임 횟수: "+record[i]);
		}
		scan.close();
		
		/* 내가 한거
		int min=1, max=100;
		int count[] = new int[3];
		Scanner scan = new Scanner(System.in);
		
		for(int i=0; i<3; i++) {
			System.out.println((i+1)+"번째 게임 시작");
			int r=(int)(Math.random()*(max-min+1)+min);
			System.out.println(r);
			for( ; ; ) {
				System.out.print("1~100사이 정수를 입력하세요> ");
				int num=scan.nextInt();
				if(num==r) {
					System.out.println("정답입니다.");
					count[i]++;
					break;
				} else if(num>r) {
					System.out.println("DOWN");
					count[i]++;
				} else if(num<r) {
					System.out.println("UP");
					count[i]++;
				}
			}
		}
		
		for(int tmp : count) {
			System.out.print(tmp+" ");
		}
		scan.close();
		*/
		
	}
}

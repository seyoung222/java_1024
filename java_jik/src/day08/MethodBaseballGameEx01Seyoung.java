package day08;

import java.util.Scanner;

public class MethodBaseballGameEx01Seyoung {

	public static void main(String[] args) {
		/* 1~9사이의 중복되지 않은 3개의 숫자가 랜덤으로 생성되고, 생성된 숫자를 맞추는 게임
		 * 규칙
		 * - 숫자가 같은 자리에 있으면 Strike
		 * - 숫자가 있지만 다른 자리에 있으면 Ball
		 * - 일치하는 숫자가 하나도 없으면 Out
		 * 예시 (랜덤 숫자 : 5 9 8)
		 * 입력 : 1 2 3
		 * O
		 * 입력 : 4 5 6
		 * 1B
		 * 입력 : 7 8 9
		 * 2B
		 * 입력 : 7 9 8
		 * 2S
		 * 입력 : 7 9 5
		 * 1S1B
		 * 입력 : 5 9 8
		 * 3S
		 * 게임 종료
		 * */
		
		int min=1, max=9, size=3;
		int [] game = createRandomArray(min, max, size);
		System.out.print("랜덤 숫자: ");
		printArray(game);
		
		int [] user = new int[3];
		Scanner scan = new Scanner(System.in);
		
		while(countStrike(game,user)!=3) {
			System.out.print("입력 : ");
			for(int i=0; i<user.length; i++) {
				user[i] = scan.nextInt();
			}
			printResult(game, user);
		}
		System.out.println("게임 종료");
		
		scan.close();
	}
	
	public static void printResult(int[] arr1, int[] arr2) {
		if(arr1==null || arr2==null) {
			return;
		}
		int strike=countStrike(arr1,arr2);
		int ball=countBall(arr1,arr2);
		if(strike==0 && ball==0) {
			System.out.println("O");
		} else if(strike==0 && ball>0) {
			System.out.println(ball+"B");
		} else if(strike>0 && ball==0) {
			System.out.println(strike+"S");
		} else if(strike>0 && ball>0) {
			System.out.println(strike+"S"+ball+"B");
		}
	}
	public static int countStrike(int[] arr1, int[] arr2) {
		if(arr1==null || arr2==null) {
			return 0;
		}
		int strike=0;
		for(int i=0; i<arr1.length; i++) {
			if(arr1[i]==arr2[i]) {
				strike++;
			}
		}
		return strike;
	}
	
	public static int countBall(int[] arr1, int[] arr2) {
		if(arr1==null || arr2==null) {
			return 0;
		}
		int ball=0;
		for(int tmp : arr1) {
			if(contains(arr2,tmp)) {
				ball++;
			}
		}
		ball -= countStrike(arr1, arr2);
		return ball;
	}
	
	public static void printArray(int arr[]) {
		if(arr==null) {
			return;
		}
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	public static int [] createRandomArray(int min, int max, int size) {
		if(max-min+1 <= size) {
			return null;
		}
		int arr[] = new int[size];
		for(int i=0; i<arr.length; ) {
			int r = random(min,max);
			if(!contains(arr, r)) {
				arr[i++] = r;
			} 
		}
		return arr;
	}
	public static int random(int min, int max) {
		if(min>max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		return (int)(Math.random()*(max-min+1)+min);
	}
	
	public static boolean contains(int []arr, int num) {
		if(arr==null || arr.length==0) {
			return false;
		}
		for(int tmp : arr) {
			if(num==tmp) {
				return true;
			}
		}
		return false;
	}
}

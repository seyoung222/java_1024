package day06;

import java.util.Scanner;

public class ArrayLottoEx01 {

	public static void main(String[] args) {
		/* 로또 번호를 랜덤으로 생성하고, 당첨번호를 입력하여 몇등 당첨됐는지 출력하는 코드작성.
		 * 숫자 범위 : 1 ~ 45
		 * 1등 : 6개
		 * 2등 : 5개 + 보너스 번호
		 * 3등 : 5개
		 * 4등 : 4개
		 * 5등 : 3개
		 * 로또 번호 : 번호 6개와 + 보너스
		 * */
		
		//랜덤으로 6개의 로또 번호 생성
		int size = 6;
		int min=1, max=45;
		int count=0;
		int lotto[] = new int[size];
		
		while(count<size) {
			int r=(int)(Math.random()*(max-min+1)+min);
			boolean isDuplicated = false;
			for(int i=0; i<count; i++) {
				if(lotto[i]==r) {
					isDuplicated = true;
				}
			}
			if(isDuplicated) {
				continue;
			}
		lotto[count++]=r;
		}
		System.out.print("당첨번호 출력: ");
		for(int tmp : lotto) {
			System.out.print(tmp+" ");
		}
		//보너스 번호를 랜덤으로 생성(위에서 생성한 번호와 중복되지않게)
		int bonus;
		while(true) {
			int r=(int)(Math.random()*(max-min+1)+min);
			boolean isDuplicated = false;
			for(int i=0; i<size; i++) {
				if(lotto[i]==r) {
					isDuplicated = true;
				}
			}
			if(isDuplicated) {
				continue;
			}
			bonus = r;
			break;
		}
		System.out.println("보너스 : "+bonus);
		
		//당첨번호 6개를 입력받음
		Scanner scan = new Scanner(System.in);
		System.out.print("\n당첨 번호 입력: ");
		int user[] = new int[size];
		for(int i=0; i<size; i++) {
			user[i]=scan.nextInt();
		}
		
		//일치하는 번호 개수를 구함(보너스 번호 제외)
		int sameCount = 0;
		for(int i=0; i<lotto.length; i++) {
			for(int j=0; j<user.length; j++) {
				if(lotto[i]==user[j]) {
					sameCount++;
				}
			}
		}
		
		//등수 판별
		switch(sameCount) {
		case 6:
			System.out.println("1등");
			break;
		case 5:
			boolean isDuplicated = false;
			for(int i=0; i<user.length; i++) {
				if(user[i]==bonus) {
					isDuplicated = true;
				}
			}
			if(isDuplicated) {
				System.out.println("2등");
			} else {
				System.out.println("3등");
			}
			break;
		case 4: 
			System.out.println("4등");
			break;
		case 3:
			System.out.println("5등");
			break;
		default:
			System.out.println("꽝!");
		}
		
		
//		내가한거1
//		//랜덤으로 6개의 로또 번호 생성
//		int size = 6;
//		int min=1, max=45;
//		int count=0;
//		int arr[] = new int[size];
//		
//		while(count<size) {
//			int r=(int)(Math.random()*(max-min+1)+min);
//			boolean isDuplicated = false;
//			for(int i=0; i<count; i++) {
//				if(arr[i]==r) {
//					isDuplicated = true;
//				}
//			}
//			if(isDuplicated) {
//				continue;
//			}
//			arr[count++]=r;
//		}
//		//보너스 번호를 랜덤으로 생성(위에서 생성한 번호와 중복되지않게)
//		count=0;
//		int bonus=0;
//		while(count<1) {
//			int r=(int)(Math.random()*(max-min+1)+min);
//			boolean isDuplicated = false;
//			for(int i=0; i<size; i++) {
//				if(arr[i]==r) {
//					isDuplicated = true;
//				}
//			}
//			if(isDuplicated) {
//				continue;
//			}
//			bonus = r;
//			count++;
//		}
//		System.out.print("당첨번호 출력: ");
//		for(int tmp : arr) {
//			System.out.print(tmp+" ");
//		}
//		System.out.println("\n보너스번호: "+bonus);
//		
//		
//		System.out.println();
//		//당첨번호 6개를 입력받음
//		int correct=0;
//		boolean isBonus=false;
//		int num[] = new int[size];
//		Scanner scan = new Scanner(System.in);
//		System.out.print("번호를 6개 입력하세요> ");
//		for(int i=0; i<size; i++) {
//			num[i]=scan.nextInt();
//		}
//		
//		//일치하는 번호 개수를 구함(보너스 번호 제외)
//		for(int i=0; i<size; i++) {
//			for(int j=0; j<size; j++) {
//				if(num[i]==arr[j]) {
//					correct++;
//					break;                             
//				}
//			}
//			if(num[i]==bonus) {
//				isBonus=true;
//			}
//		}
//		//등수 판별
//		if(correct==6) {
//			System.out.println("1등입니다.");
//		} else if (correct==5 && isBonus) {
//			System.out.println("2등입니다.");
//		} else if(correct==5) {
//			System.out.println("3등입니다.");
//		} else if(correct==4) {
//			System.out.println("4등입니다.");
//		} else if(correct==3) {
//			System.out.println("5등입니다.");
//		} else {
//			System.out.println("당첨되지 않았습니다.");
//		}
		
		
		
		
		/* 내가한거2 (랜덤배열을 7칸으로 만들어 마지막수를 보너스로 지정)
		int size = 6;
		int min=1, max=45;
		int count=0;
		
		int arr[] = new int[size+1];
		while(count<7) {
			int r=(int)(Math.random()*(max-min+1)+min);
			boolean isDuplicated = false;
			for(int i=0; i<size; i++) {
				if(arr[i]==r) {
					isDuplicated = true;
				}
			}
			if(isDuplicated) {
				continue;
			}
			arr[count++]=r;
		}
		
		System.out.print("당첨번호 출력: ");
		for(int tmp : arr) {
			System.out.print(tmp+" ");
		}
		
		System.out.println();
		
		int correct=0;
		boolean bonus=false;
		int num[] = new int[size];
		Scanner scan = new Scanner(System.in);
		System.out.print("번호를 6개 입력하세요> ");
		for(int i=0; i<size; i++) {
			num[i]=scan.nextInt();
		}
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(num[i]==arr[j]) {
					correct++;
					break;
				}
			}
			if(num[i]==arr[6]) {
				bonus=true;
			}
		}
		
		if(correct==6) {
			System.out.println("1등입니다.");
		} else if (correct==5 && bonus) {
			System.out.println("2등입니다.");
		} else if(correct==5) {
			System.out.println("3등입니다.");
		} else if(correct==4) {
			System.out.println("4등입니다.");
		} else if(correct==3) {
			System.out.println("5등입니다.");
		} else {
			System.out.println("당첨되지 않았습니다.");
		}
		*/
		
		
	}
}

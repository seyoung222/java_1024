package day06;

import java.util.Scanner;

public class ArrayRandomEx01 {

	public static void main(String[] args) {
		//랜덤으로 1~9사이의 숫자를 3개 생성하여 배열에 저장하는 코드를 작성하세요.
		
		/* 내가 한거
		int min=1, max=9;
		int random[] = new int[3];
		for(int i=0; i<random.length; i++) {
			random[i] = (int)(Math.random()*(max-min+1)+min);
		}
		
		System.out.print("랜덤 수 3개: ");
		for(int i=0; i<random.length; i++) {
			System.out.print(random[i]+" ");
		}
		*/
		
		int min=1, max=9;
		int arr[] = new int [3];
		for(int i=0; i<3; i++) {
			arr[i]=(int)(Math.random()*(max-min+1)+min);
			System.out.print(arr[i]+" ");
		}
		
		
		System.out.println();
		//정수를 입력받아 랜덤한 수에 있는지 없는지 확인해서 알려주는 코드 작성.
		
		/* 내가 한거
		Scanner scan = new Scanner(System.in);
		System.out.print("정수를 입력하세요> ");
		int num = scan.nextInt();
		for(int i=0; i<3; i++) {
			if(num==arr[i]) {
				System.out.println(num+"은 포함됩니다.");
				break;
			}
			if(i==2) {
				System.out.println(num+"은 포함되지 않습니다.");
			}
		}
		scan.close();
		*/
		
		Scanner scan = new Scanner(System.in);
		System.out.print("정수를 입력하세요> ");
		int num = scan.nextInt();
		boolean isDuplicated = false;//중복이 안됨으로 초기화
		
		for(int i=0; i<arr.length; i++) {
			//i번지에 있는 값과 num이 같으면
			if(arr[i]==num) {
				//중복됐다고 저장
				isDuplicated = true;
				break;
			}
		}
		//중복 됐으면 있다고 출력하고 아니면 없다고 출력
		if(isDuplicated) {
			System.out.println(num+"가 있습니다.");
		} else {
			System.out.println(num+"가 없습니다.");
		}
		scan.close();
		
		
	}

}

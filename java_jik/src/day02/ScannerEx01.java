package day02;

import java.util.Scanner;

public class ScannerEx01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); //표준입력을 받아서 스캐너로 만듦
		System.out.println("정수를 입력하세요 : ");
		int num = scan.nextInt(); // 정수 입력
		System.out.println(num);
		
		System.out.println("실수를 입력하세요 : ");
		double num2 = scan.nextDouble(); // 실수 입력
		System.out.println(num2);
		
		System.out.println("정수A와 정수B를 입력하세요 : ");
		int a = scan.nextInt();
		int b = scan.nextInt();
		System.out.println(a);
		System.out.println(b);
		
		scan.close();
	}

}

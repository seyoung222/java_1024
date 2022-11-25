package day25;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex02 {
	/* 정수를 5번 입력한 배열/리스트에 저장하는 코드를 작성하세요
	 * 정수가 홀수이면 그대로 저장, 정수가 짝수이면 -를 붙여서 저장
	 * */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size = 5;
		ArrayList<Integer>list = new ArrayList<Integer>();
		int[]arr=new int[size];
		for(int i=0; i<size; i++) {
			System.out.print(i+1+"번째 숫자 입력> ");
			int num = scan.nextInt();
			if(num%2 != 1) { //음수도 홀짝 판별하려면 ==1이 아니라 !=0으로 해야 정확함
				list.add(num);
				arr[i]=num;
			}else {
				list.add(-num);
				arr[i]=-num;
			}
		}
		System.out.println("list: "+list);
		System.out.print("arr: ");
		for(int num : arr) {
			System.out.print(num+" ");
		}
	}

}

package day05;

import java.util.Scanner;

public class ArrayEx04 {

	public static void main(String[] args) {
		/* 학생 5명의 국어성적(정수)를 입력받고 출력하는 코드를 작성하세요.
		 * 반복횟수 : i는 0부터 배열의 크기보다 작을때까지 1씩 증가
		 * 규칙성 : 콘솔에서 입력받은 정수를 배열 i번지에 저장 후 배열 i번지에 있는 값을 출력
		 * */
		
		Scanner scan = new Scanner(System.in);
		
		int score[] = new int[5];
		for(int i=0; i<score.length; i++) {
			System.out.print((i+1)+"번째 학생의 국어성적을 입력하세요> ");
			score[i] = scan.nextInt();
		}
		for(int i=0; i<score.length; i++) {
			System.out.println((i+1)+"번째 학생의 국어성적 : "+score[i]);
		}
		
		/* 학생 성적의 평균을 구하는 코드를 작성하세요.
		 * 반복횟수 : i는 0부터 배열의 크기보다 작을때깢 1씩 증가(향상된 for문 이용가능)
		 * 규칙성 : sum에 배열 i번지에 있는 값을 누적
		 * 반복문 종료 후 : sum을 배열의 크기로 나눈 값을 출력(나누기 주의)
		 * */
		int sum=0;
		for(int i=0; i<score.length; i++) {
			sum += score[i];
		}
		System.out.println("평균성적은 "+(double)sum/score.length+"점 입니다.");
		
		//향상된 for문 이용
		sum=0;
		for(int tmp : score) {
			sum += tmp;
		}
		System.out.println("평균성적은 "+(double)sum/score.length+"점 입니다.");
		
		
		scan.close();
	}
}

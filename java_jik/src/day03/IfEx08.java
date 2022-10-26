package day03;

import java.util.Scanner;

public class IfEx08 {

	public static void main(String[] args) {
		/* 점수를 정수로 입력받아 입력받은 성적에 맞는 학점을 출력하는 코드를 작성하세요.
		 * A: 90~100
		 * B: 80~89
		 * C: 70~79
		 * D: 60~69
		 * F: 0~59
		 * 잘못된 성적: 그 외
		 */
		
		/* 정수를 입력받아 score에 저장
		 * 
		 * score가 90보다 크거나 같고 score가 100보다 작거나 같으면 A라고 출력
		 * score가 80보다 크거나 같고 score가 89보다 작거나 같으면 A라고 출력
		 * score가 70보다 크거나 같고 score가 79보다 작거나 같으면 A라고 출력
		 * score가 60보다 크거나 같고 score가 69보다 작거나 같으면 A라고 출력
		 * score가 0보다 크거나 같고 score가 59보다 작거나 같으면 A라고 출력
		 * score가 위의 조건을 만족하지 않으면 잘못된 점수라고 출력
		 */
		
		Scanner scan = new Scanner(System.in);
		System.out.print("시험 점수를 입력하세요> ");
		int score = scan.nextInt();
		
		//방법1
		if(score>=90 && score<=100) {
			System.out.println("학점은 A 입니다.");
		} else if(score>=80 && score<=89) {
			System.out.println("학점은 B 입니다.");
		} else if(score>=70 && score<=79) {
			System.out.println("학점은 C 입니다.");
		} else if(score>=60 && score<=69) {
			System.out.println("학점은 D 입니다.");
		} else if(score>=50 && score<=59) {
			System.out.println("학점은 F 입니다.");
		} else {
			System.out.println("잘못된 성적입니다.");
		} 
		
		//방법2
		if(score>100 || score<0) {
			System.out.println("잘못된 성적입니다.");
		} else if(score>=90) {
			System.out.println("학점은 A 입니다.");
		} else if(score>=80) {
			System.out.println("학점은 B 입니다.");
		} else if(score>=70) {
			System.out.println("학점은 C 입니다.");
		} else if(score>=60) {
			System.out.println("학점은 D 입니다.");
		} else {
			System.out.println("학점은 F 입니다.");
		} 
		
		scan.close();
	}
}

package day04;

public class NestingForEx01 {

	public static void main(String[] args) {
		/* 구구단 3단을 출력하는 코드를 작성하세요. */
		
		for(int i=2; i<=9; i++) {
			for(int j=1; j<=9; j++) {
				System.out.println(i+" x "+j+" = "+i*j);
			}
			System.out.println();
		}
		
	}
}

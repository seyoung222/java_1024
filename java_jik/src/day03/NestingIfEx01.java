package day03;

public class NestingIfEx01 {

	public static void main(String[] args) {
		//중첩 if문을 이용한 2,3,6의 배수 예제
		int num = 6;
		
		//2의 배수 체크
		if(num%2==0) {
			//2의 배수이면서 3의 배수
			if(num%3==0) {
				System.out.println(num+"은 6의 배수입니다.");
			} 
			//2의 배수
			else {
				System.out.println(num+"은 2의 배수입니다.");
			}
		} else if(num%3==0) {
			System.out.println(num+"은 3의 배수입니다.");
		} else {
			System.out.println(num+"은 2,3,6의 배수가 아닙니다..");
		}
		
	}
}

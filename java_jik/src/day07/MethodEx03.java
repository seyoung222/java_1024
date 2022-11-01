package day07;

public class MethodEx03 {

	public static void main(String[] args) {
		/* 주어진 정수 num가 소수인지 아닌지 판별하는 코드를 작성하세요.
		 * 단, 메소드를 이용할 것
		 * */
		printPrimeNumber(5); //응용성이 낮아서 아래가 좀 더 좋음..
		System.out.println(isPrimeNumber(12));
	}
	/* 기능 : 정수가 주어지면 소수인지 아닌지 출력하는 메소드
	 * 매개변수 : 정수 => int num
	 * 리턴타입 : 출력 => 없음 => void
	 * 메소드명 : printPrimeNumber
	 * */
	public static void printPrimeNumber(int num) {
		int count=0;
		for(int i=1; i<=num; i++) {
			if(num%i==0) {
				count++;
			}
		}
		if(count==2) {
			System.out.println(num+"은 소수입니다.");
		} else {
			System.out.println(num+"은 소수가 아닙니다.");
		}
	}
	/* 기능 : 정수가 주어지면 소수인지 아닌지 알려주는 메소드
	 * 매개변수 : 정수 => int num
	 * 리턴타입 : 소수인지 아닌지 => boolean
	 * 메소드명 : isPrimeNumber
	 * */
	public static boolean isPrimeNumber(int num) {
		for(int i=2; i<num; i++) {
			if(num%i==0) { //약수가 하나라도 있으면 false 리턴
				return false;
			}
		}
		return num<=1 ? false : true;
	}
	
	/*내가 한거
	public static boolean isPrimeNumber(int num) {
		int count=0;
		boolean isPrimeNumber=false;
		for(int i=1; i<=num; i++) {
			if(num%i==0) {
				count++;
			}
		}
		if(count==2) {
			isPrimeNumber=true;
		} 
		return isPrimeNumber;
	}
	*/
}

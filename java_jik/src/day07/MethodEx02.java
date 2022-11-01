package day07;

public class MethodEx02 {

	public static void main(String[] args) {
		//7단을 출력하는 코드를 작성하세요. 단, 메소드를 이용할 것
		int num=7;
		printTimesTable(num);
		System.out.println("================");
		printTimesTable2(num,1,5);
		System.out.println("================");
		for(int i=1; i<=9; i++) {
			System.out.println(num+" x "+i+" = "+multi(num,i));
		}
	}
	/* 기능 : 단이 주어지면 해당하는 단을 콘솔에 출력하는 메소드
	 * 매개변수 : 단 => 정수 => int num
	 * 리턴타입 : 없음 => void
	 * 메소드명 : printTimesTable
	 * */
	public static void printTimesTable(int num) {
		System.out.println(num+"단을 출력합니다.");
		for(int i=1; i<=9; i++) {
			System.out.println(num+" x "+i+" = "+num*i);
		}
		
	}
	/* 기능 : 단과 시작정수, 끝정수가 주어지면 해당하는 단을
	 * 단x시작숫자 에서 단x끝숫자 까지 콘솔에 출력하는 메소드
	 * 매개변수 : 단, 시작숫자, 끝숫자 => int num, int start, int end
	 * 리턴타입 : 없음 => void
	 * 메소드명 : printTimesTable2
	 * */
	public static void printTimesTable2(int num, int start, int end) {
		for(int i=start; i<=end; i++) {
			System.out.println(num+" x "+i+" = "+num*i);
		}
	}
	/* 기능 : 두 정수가 주어지면 두 정수의 곱을 알려주는 메소드
	 * 매개변수 : 두 정수 => int num1, int num2
	 * 리턴타입 : 두 정수의 곱 => 정수 => int
	 * 메소드명 : multi
	 * */
	public static int multi(int num1, int num2) {
		return num1*num2;
	}
}

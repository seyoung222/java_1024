package day09;

public class FieldInitEx01 {
//class 속 필드의 초기화 순서
	public static void main(String[] args) {
		Test04 t = new Test04(4);
		t.print(); //num1: 0, num2: 2, num3: 3, num4: 4

	}

}
class Test04{
	private int num1; //1. 기본 초기값으로 초기화
	private int num2 = 2; //2. 명시적 초기화
	private int num3 = 2; //2. 명시적 초기화
	private int num4 = 2; //2. 명시적 초기화
	
	{
		num3 = 3; //3. 초기화 블록
		num4 = 3; //3. 초기화 블록
	}
	public Test04(int num4) {
		this.num4 = num4; //4. 생성자 초기화
	}
	public void print() {
		System.out.print("num1: "+num1);
		System.out.print(", num2: "+num2);
		System.out.print(", num3: "+num3);
		System.out.println(", num4: "+num4);

	}
}
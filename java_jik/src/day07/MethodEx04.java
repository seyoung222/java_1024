package day07;

public class MethodEx04 {

	public static void main(String[] args) {
		int num=10;
		Test t = new Test();
		printNum(num); //10
		printNum(num); //10
		printTest(t); //0 :처음엔 값 저장안돼서 0으로 출력됨
		printTest(t); //20
	}
	//main에 있는 num와 printNum에 있는 num은 이름은 같지만 서로 다른 변수
	//값을 복사해서 가져옴
	//매개변수가 기본 자료형인 경우, printNum에서 숫자를 바꿔도 main에서 num은 안바뀜
	public static void printNum(int num) {
		System.out.println(num);
		num=20;
	}
	//매개변수가 참조변수(배열이나 객체처럼 주소를 저장하는 것)인 경우,
	//다른 곳에서 숫자를 바꾸면 main에 있는 값도 바뀔 수 있다.
	public static void printTest(Test t) {
		System.out.println(t.a);
		t.a=20;
	}
}
class Test{
	int a;
}
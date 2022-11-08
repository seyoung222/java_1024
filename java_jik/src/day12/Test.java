package day12;

public class Test {

	public static void main(String[] args) {
		// ~ 이해 정도 ~
		//1. 예제가 주어졌을 때 예제를 이해한다.
		//2. 예제를 풀기 위해 사용해야하는 문법을 파악할 수 있다.
		//3. 예제를 해결하기 위해 단계를 구분할 수 있다. (주석)
		//4. 예제를 풀기 위해 필요한 변수, 배열, 객체 등을 선정할 수 있다.
		//5. 부분적으로 (3번에서 작성한 단계별) 코딩을 통해 해결할 수 있다.
		//6. 원하는 코딩을 작성할 수 있다.
		//7. 작성한 코딩을 효율적으로 정리할 수 있다.
	}
	//접근제한자 예약어 리턴타입 메소드명(매개변수);
	//추상클래스를 상속받은 일반클래스는 부모의 추상메소드를 반드시 오버라이딩 해야함
	//추상클래스를 상속받은 추상클래스는 부모의 추상메소드를 반드시 오버라이딩 해야하는 건 아님
	
}
interface Calculator{
	int max = 30; //변수는 적지않아도 무조건 public static final
	//static은 기울임체, final은 볼드체
	int sum(int num1, int num2);
	int sub(int num1, int num2);
	int num(int num1, int num2);
	double div(int num1, int num2);
	int mod(int num1, int num2);
	void run();
}
/*
abstract class TestA{
	public abstract void test();
}

class TestB extends TestA{ //일반클래스는 무조건 오버라이딩 해야함~

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	} 
	
}
abstract class TestC extends TestA{
	
}



interface TestAA{
	void print();
}
interface TestAB{
	void print();
}
class TestAC implements TestAA, TestAB{
	public void print() {
		System.out.println("되남");
	}
}
*/
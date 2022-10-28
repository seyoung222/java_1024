package day05;

public class VariableEx01 {

	public static void main(String[] args) {
		/* 변수는 선언에 위치에서 같은 {}내에서 사용 가능
		 * */
		int i = 0;
		//반복문 초기화에서 변수를 선언하면 이미 선언된 변수는 초기화 할 수 없다.
		for(int j=1/*, i=5 //앞에서 i가 이미 선언돼있어서 에러*/; j<=5; j++) {
			System.out.println(j);
		}
//		System.out.println(j); //에러발생. j는 위 반복문내에서만 사용가능
		
		
	}
}
package day19;

public class ChainingEx01 {
//메소드 뒤에 메소드가 .으로 계속 이어지는 걸 체이닝기법이라고 함
	public static void main(String[] args) {
		/* 체이닝 : .이 연달아 나와 메소드를 연속해서 호출
		 * 앞에 있는 메소드 리턴 값(객체)에 이어서 메소드를 호출 */
		String str = "Hello";
		/* 문자열 str에 2번지부터 부분문자열을 추출한 후, 0번지 문자를 가져옴
		 * 0번지 문자를 가져왔기 때문에 charAt()다음에는 .을 통해 메소드를 호출할 수 없음*/
		System.out.println(str.substring(2).charAt(0));
		
		System.out.println(1);
		System.out.println(toString(1).charAt(0));
		//toString을 통해 얻은 값은 객체(String)이기 때문에 .을 써서 기능을 이어붙일 수 있음
		
		/* print()는 리턴타입이 없기 때문에 체이닝을 쓸 수 없다.
		 * 리턴타입이 일반변수(int, char 등)인 경우도 체이닝을 쓸 수 없다. */
		print(10); 
	}
	public static String toString(int num) {
		return num+"";
	}
	public static void print(int num) {
		System.out.println(num);
	}
}

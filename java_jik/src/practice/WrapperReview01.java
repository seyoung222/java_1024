package practice;

public class WrapperReview01 {

	public static void main(String[] args) {
		//Wrapper 클래스: Integer, Double 등등...
		// 기본자료형을 클래스 단위로 객체화한 것 -> 스레드, 리스트 등에 사용하기 위해
		//박싱: 기본자료형을 객체화 시키는 것
		//언박싱: 객체화 된 것을 기본자료형으로 꺼내는 것, 객체주소값이 null인 경우를 주의
		int num1 = 1;
		Integer num2 = num1;
		int num3 = num2;
		System.out.println(num3);
		
		//문자열을 정수로
		String str = "1234";
		int num4 = Integer.parseInt(str);
		
		//정수를 문자열로
		int num5 = 123;
		String str2 = Integer.valueOf(num5).toString();
		System.out.println(str2);
	}

}

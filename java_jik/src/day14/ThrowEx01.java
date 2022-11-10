package day14;

public class ThrowEx01 {

	public static void main(String[] args) {
		/* throws : 메소드가 실행됐을 때 발생할 수 있는 예외를 표시
		 * 			여러개 가능
		 * 			RuntimeException과 자식 예외클래스들은 생략 가능
		 * 			그 외 예외 클래스들은 생략 불가능
		 * throw : 예외를 발생시킴. 조건에 따라 의도적으로 예외를 발생시킴
		 * 		   예외 객체를 던져줌. 
		 * 		   throw를 통해 예외가 발생하면 메소드를 빠져 나옴
		 * */
		System.out.println(mod(1,0));
		
		try {
			throwTest();//얘는 런타임예외가 아니라서 처리 꼭 해줘야함
		} catch(Exception e) {
			System.out.println("예외 발생");
		}
		
	}
	
	public static int mod(int num1, int num2) throws ArithmeticException{
		return num1 % num2;
	}
	public static void throwTest() throws Exception{//생략불가한 클래스라서 throws 기재
		throw new Exception(); //객체를 생성해서 예외 발생시킴
	}
}

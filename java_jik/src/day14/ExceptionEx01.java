package day14;

public class ExceptionEx01 {

	public static void main(String[] args) {
		/* printStackTrace() : 
		 *  - 예외가 발생했을 때 실행된 메소드들을 차례대로 역순으로 출력
		 *  - 발생한 예외와 예외 메세지(생성자에 입력한 문자열 인자)도 같이 출력
		 * getMessage() : 
		 *  - 예외가 발생한 이유를 출력
		 *  - 사용자가 발생시킨 예외라면 생상자에 넘겨준 문자열이 출력
		 * */
		try {
			throwException();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());//예외 생성시 만든 문자열 가져옴
		}
	}
	public static void throwException() throws Exception{
		throw new Exception("예외 발생");
	}
}

package day14;

public class WrapperEx01 {

	public static void main(String[] args) {
		int num1 =1;
		Integer num2 = num1; //박싱(boxing):기본자료형을 Integer로 객체화
//		num2=null;
		int num3 = num2; //언박싱(unboxing):객체화된 Integer를 기본자료형으로
		//num2인 Integer 객체를 int로 언박식하는 경우 num2가 null일 수 있어서 조심해야함
		System.out.println(num3);
		
		//문자열을 정수로
		String str = "1234";
		System.out.println(str+1); //문자열 1234 뒤에 1이 붙은 12341 출력
		int num4 = Integer.parseInt(str); //실수는 Double.parseDouble(str);
		System.out.println(num4+1); // 숫자 1234에 1을 더한 1235가 출력
		
		//정수를 문자열로
		int num5 = 123;
		String str2 = Integer.valueOf(num5).toString(); 
		//Integer로 바꾼 뒤 오버라이딩된 toString으로 그 값을 출력
		System.out.println(str2+1);
		String str3 = ""+num5; //문자열+정수=문자열 
		System.out.println(str3+1);
	}
}

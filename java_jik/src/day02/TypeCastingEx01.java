package day02;

public class TypeCastingEx01 {

	public static void main(String[] args) {
		//접미사 f(F)가 없는 실수 리터럴은 double형
		//1.23은 8바이트인 double이고, num1은 4바이트인 float이기 때문에 자동형변환이 불가능
		// => 접미사를 붙이거나 강제형변환
		float num1 = (float)1.23;
		//정수 리터럴은 자료형에 맞는 타입으로 설정됨
		byte num2 = 3;
		long num3 = 3;
		int num4 = (int)12345678901L;
		
	}

}

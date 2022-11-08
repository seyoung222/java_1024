package day12;

public class ObjectEx01 {

	public static void main(String[] args) {
		Student std1 = new Student();
		Student std2 = new Student();
		
		System.out.println(std1); //toString()을 오버라이딩 해서 필드값들을 전부 출력가능
		System.out.println(std1);
		System.out.println("두 개체를 ==로 비교: "+(std1==std2)); //완전히 공유해야 true
		System.out.println("두 개체를 equals로 비교: "+(std1.equals(std2)));
	}

}

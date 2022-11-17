package day19;

import java.util.ArrayList;
import java.util.function.Consumer;


public class ConsumerEx01 {
//Consumer는 리턴타입이 없고 매개변수가 1개인 함수형 인터페이스
	public static void main(String[] args) {
		ArrayList<Student>list = new ArrayList<Student>();
		list.add(new Student(1, 1, 1, "홍길동"));
		list.add(new Student(1, 2, 1, "임꺽정"));
		list.add(new Student(1, 1, 2, "고길동"));
		
		print(list, (a)->System.out.println(a)); //toString이 덮어씌워져서..
		
		print(list, (a)-> {
			System.out.println("-------------------");
			System.out.println("학년: "+a.getGrade());
			System.out.println("반: "+a.getClassNum());
			System.out.println("번호: "+a.getNum());
			System.out.println("이름: "+a.getName());
			System.out.println("-------------------");
		});
		//print메소드 하나에 대해서 양식을 다르게 지정할 수 있음
	}

	public static void print(ArrayList<Student>list, Consumer<Student> con) {
		for(Student tmp : list) {
			con.accept(tmp);
		}
	}
}

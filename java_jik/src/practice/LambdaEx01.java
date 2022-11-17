package practice;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import day19.Student;

public class LambdaEx01 {

	public static void main(String[] args) {
		ArrayList<Student>list = new ArrayList<Student>();
		list.add(new Student(1, 1, 1, "홍길동", 100, 90, 80));
		list.add(new Student(1, 2, 1, "임꺽정", 90, 90, 80));
		list.add(new Student(1, 1, 2, "고길동", 10, 30, 60));
		
		//consumer 예제
		//출력 방식 등을 제어
		print(list,a->System.out.println(a));
		print(list,a->{
			System.out.println("학년 : "+a.getGrade());
			System.out.println("반 : "+a.getClassNum());
			System.out.println("번호 : "+a.getNum());
			System.out.println("이름 : "+a.getName());
		});
		
		//supplier 예제
		System.out.println(num( ()->(int)(Math.random()*10)) );
		
		//function 예제
		//학생들의 국어성적만 출력하는 코드 작성
		print(list,"국어", f->f.getKor()); 
		
		//predicate 예제
		//1학년1반 학생들만 출력하는 코드 작성
		print(list,p->p.getGrade()==1 && p.getClassNum()==1);
	}
	//Consumer는 매개변수 있고, 리턴값은 없음
	public static void print(ArrayList<Student>list, Consumer<Student>c) {
		for(Student tmp : list) {
			c.accept(tmp);
		}
	}
	//Supplier는 매개변수 없고, 리턴값은 있음
	public static int num(Supplier<Integer>s) {
		return s.get();
	}
	
	//Function은 매개변수와 리턴값이 둘다 있음, 매개타입을 변환하여 리턴할 때 많이 사용
	//Function<T,R> 로 선언하고 .apply(T)로 사용
	public static void print(ArrayList<Student>list, String subject, Function<Student,Integer>f) {
		for(Student tmp : list) {
			System.out.println(subject+" : "+f.apply(tmp));
		}
	}
	
	//Predicate는 매개변수가 있고, 리턴값이 boolean으로 고정
	//Predicatd<T> 로 선언하고 .test(T)로 사용. 조건을 따져서 출력하는 경우 등에 사용
	public static void print(ArrayList<Student>list, Predicate<Student>p) {
		for(Student tmp : list) {
			if(p.test(tmp)) {
				System.out.println(tmp);
			}
		}
	}
}

package day19;

import java.util.ArrayList;
import java.util.function.Function;

public class FunctionEx01 {
//Function은 매개변수와 리턴타입이 둘다 있을때 사용. 매개변수를 리턴타입으로 변환할때 사용
	public static void main(String[] args) {
		ArrayList<Student>list = new ArrayList<Student>();
		list.add(new Student(1, 1, 1, "홍길동", 100, 90, 80));
		list.add(new Student(1, 2, 1, "임꺽정", 90, 90, 80));
		list.add(new Student(1, 1, 2, "고길동", 10, 30, 60));
		/* 학생들의 국어 성적만 출력하는 코드를 작성하세요.
		 * 학생들의 영어 성적만 출력하는 코드를 작성하세요.
		 * 학생들의 수학 성적만 출력하는 코드를 작성하세요.
		 * */
//		printKor(list);
//		printEng(list);
//		printMath(list);
		
		
		print(list, "국어", s->s.getKor());
		print(list, "영어", s->s.getEng());
		print(list, "수학", s->s.getMath());
		print(list, "총합", s->s.getKor()+s.getEng()+s.getMath());
	}
	public static void print(ArrayList<Student>list, String subject, Function<Student,Integer> f) {
		for(Student tmp : list) {
			System.out.println(subject+" : "+f.apply(tmp));
		}
	}
	
	public static void printEng(ArrayList<Student>list) {
		for(Student tmp : list) {
			System.out.println("영어: "+ tmp.getEng());
		}
	}
	public static void printKor(ArrayList<Student>list) {
		for(Student tmp : list) {
			System.out.println("국어: "+tmp.getKor());
		}
	}
	public static void printMath(ArrayList<Student>list) {
		for(Student tmp : list) {
			System.out.println("수학: "+tmp.getMath());
		}
	}

}

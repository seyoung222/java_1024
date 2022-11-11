package day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import day14.Student;

public class SortEx03 {

	public static void main(String[] args) {
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(1,1,3));
		list.add(new Student(3,1,3));
		list.add(new Student(2,1,3));
		list.add(new Student(1,1,2));
		list.add(new Student(1,1,1));
		
		System.out.println(list);
		//Integer랑 String는 Comparable 인터페이스가 구현되어있어서 자동으로 오름차순 가능
		//이건 내가 해줘야함. 하나씩 다 정렬을 구현
		Collections.sort(list, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				if(o1.getGrade() != o2.getGrade()) {
					return o1.getGrade() - o2.getGrade();
				}
				if(o1.getClassNum() != o2.getClassNum()) {
					return o1.getClassNum() - o2.getClassNum();
				}
				return o1.getNum() - o2.getNum();
			}
			
		}); 
		System.out.println(list);
	}

}

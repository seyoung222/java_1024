package day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LambdaEx01 {

	public static void main(String[] args) {
		/* 람다식을 이용하여 생성된 것은?
		 * - 객체 (참조변수에 저장될 수 있는 건 객체임)
		 * 인터페이스를 이용하여 객체를 만들 때 구현클래스나 익명클래스가 필요
		 * => 익명클래스나 구현클래스 없이 바로 객체를 생성할 수 있게 하는 것이 람다식
		 * ex) Predicate의 객체를 만들고, test를 오버라이딩하여 원하는 기능 구현
		 * */
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(10);
		list.add(8);
		list.add(0);
		System.out.println(list);
		Comparator<Integer> comparator = (a,b)->a-b;
		Collections.sort(list, comparator); 
		//comparator는 compare메소드하나만 가지고 있어 그걸로 비교
		System.out.println(list);
		System.out.println(comparator.compare(1, 2));
	}

}

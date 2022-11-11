package day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortEx01 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(10);
		list.add(3);
		list.add(20);
		list.add(-1);
		System.out.println(list);
		Collections.sort(list); //오름차순으로 정렬
		System.out.println(list);
		//Comparator 인터페이스를 구현한 구현클래스가 필요, 1회용 익명클래스를 만들어 넣은거임...
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});

		System.out.println(list);
	}

}

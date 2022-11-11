package day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortEx02 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("apple");
		list.add("orange");
		list.add("banana");
		list.add("ship");
		list.add("a");
		System.out.println(list);
		Collections.sort(list); //오름차순으로 정렬
		System.out.println(list);
		//Comparator 인터페이스를 구현한 구현클래스가 필요, 1회용 익명클래스를 만들어 넣은거임...
		Collections.sort(list, new Comparator<String>() {
					
			@Override
			public int compare(String o1, String o2) {
				return -o1.compareTo(o2); //양수음수는 사전순으로 앞이냐 뒤냐의 차이
			}
		});
		System.out.println(list);
	}

}

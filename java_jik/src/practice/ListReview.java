package practice;

import java.util.ArrayList;

public class ListReview {
//add, remove, contains, indexOf, get, 리스트 데이터 출력 해보기
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("가");
		list.add("나");
		list.add("다");
		list.add("라");
		list.add(1,"하");
		list.remove(3); //3번지에 있는 값 지움
		list.remove((String)"다"); //정수 10을 지움
		System.out.println(list.contains("마"));
		System.out.println(list.indexOf("라"));
		System.out.println(list.get(1));
		
		//출력
		System.out.println(list); //대괄호 안에 나열 출력
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+" "); //하나씩 출력 + 반복
		}
		System.out.println();
		for(String tmp : list) {
			System.out.print(tmp+" ");
		}
	}

}

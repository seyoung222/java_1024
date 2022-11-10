package day14;

import java.util.ArrayList;
import java.util.Iterator;

public class ListEx01 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(0,10); //0번지에 10들어가고 나머지가 밀림
		list.remove(1); //1번지의 값을 지움
		list.remove((Integer)10); //정수 10을 삭제함. integer안쓰면 10번지를 삭제
		System.out.println(list);//list는 출력으로 안에 있는 값 간단하게 나열 가능
		System.out.println("1이 있나요?: "+list.contains(1));
		System.out.println("1이 어디에 있나요?: "+list.indexOf(1)+"번지");
		System.out.println("1번지에 있는 값: "+list.get(1));
		
		//방법1. 기본적으로 반복문, 향상된 for문으로 배열의 값을 하나씩 출력가능
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
		System.out.println();
		for(Integer tmp : list) {
			System.out.print(tmp+" ");
		}
		
		//방법2. iterator 사용: List나 Set에선 잘 안쓰지만 Map에서 사용
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) { //.hasNext():다음 값이 있으면 true 리턴
			Integer tmp = it.next(); //다음 값이 있을 때 다음으로 넘어감
			System.out.print(tmp+" ");
		}
		System.out.println();
	}

}

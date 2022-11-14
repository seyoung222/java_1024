package day16;

import java.util.HashSet;

public class SetEx01 {
//순서보장X, 중복허용X
//반복문으로 전부를 꺼낼 순있지만 .get(i)로 특정 인덱스 하나에 접근하는 건 못하도록 되어있음
//기능이 리스트와 똑같아서 중복하지 않는 배열을 만들때만 Set쓰고 나머지는 리스트 씀
	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>();
		//해시코드를 이용해 먼저 분류한 뒤에 이퀄스를 이용해서 같은게 있는지 확인, 없으면 넣고 있으면 안넣음
		set.add(10);
		set.add(10);
		set.add(5);
		set.add(10);
		set.add(100);
		set.add(1);
		System.out.println(set); //1,100,5,10 => 넣은 순서 저장되지X
	}

}

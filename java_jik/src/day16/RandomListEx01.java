package day16;

import java.util.ArrayList;
import java.util.HashSet;

public class RandomListEx01 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int maxCount =3, min=1, max=9;
		HashSet<Integer> set = new HashSet<Integer>();
		while(set.size()<maxCount) {
			int r=(int)(Math.random()*(max-min+1)+min);
			set.add(r); //생성한 랜덤수를 set에 다 넣음. set은 중복되지 않는 수만 저장됨
		}
		list.addAll(set);//괄호안에.. Integer를 가지는 Collection객체를 넣을 수 있음
		System.out.println(list);
		
	}

}

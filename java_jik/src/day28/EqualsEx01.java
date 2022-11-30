package day28;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;

public class EqualsEx01 {

	public static void main(String[] args) {
		/* List에서 제공하는 indexOf와 contains
		 * */
		List<Test> list = new ArrayList<Test>();
		list.add(new Test(1,1));
		list.add(new Test(1,2));
		list.add(new Test(2,1));
		list.add(new Test(3,2));
		list.add(new Test(4,1));
		System.out.println(list);
		
		int index = list.indexOf(new Test(2,2));
		System.out.println("index: "+index);
		Test t = new Test(2,1);
		System.out.println("equals: "+ t.equals(new Test(2,2)));
		
		
		/* 리스트.indexOf(비교객체)에서 Objects.equals를 호출하는데
		 * Objects.equals(비교객체, 리스트에서 꺼낸 객체)를 호출하기 때문에
		 * 비교객체.equals(리스트에서 꺼낸 객체)로 비교
		 * 아래 indexOf는 Integer.equals(Test 객체)를 하기 때문에 -1이 나옴
		 * */
		index = list.indexOf((Integer)2); //괄호안에 리스트를 넣을 순 없음 ->같은지 확인은 contatin를 이용
		System.out.println("index: "+index);
		System.out.println("equals: "+ t.equals((Integer)2));
		System.out.println(Objects.equals(new Test(2,1),(Integer)2)); //true
		System.out.println(Objects.equals((Integer)2,new Test(2,1))); //false
		//앞에거.equals(뒤에거) 하기 때문에 위치를 바꾸면 결과가 바뀜

		List<Test> list2 = new ArrayList<Test>();
		list2.add(new Test(1,1));
		list2.add(new Test(1,2));
		System.out.println("두 리스트 비교: "+list.containsAll(list2));//리스트안에 다 있는지 확인
		list2.add(new Test(10,1));
		System.out.println("두 리스트 비교: "+list.containsAll(list2));
		
	}

}
@Data
class Test{
	int num1;
	int num2;
	public Test(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			if(obj instanceof Integer) {
				if(num1==(Integer)obj)
					return true;
			}
			return false;
		}
		Test other = (Test) obj;
		if (num1 != other.num1)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num1;
		return result;
	}
}
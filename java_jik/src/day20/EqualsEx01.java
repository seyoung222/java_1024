package day20;

import java.util.ArrayList;

import lombok.Data;

public class EqualsEx01 {

	public static void main(String[] args) {
		Test t1 = new Test();
		t1.setNum(10);
		System.out.println("Test객체와 Integer객체 비교(equals): "
				+t1.equals((Integer)10)); //true
		
		ArrayList<Test> list = new ArrayList<Test>();
		list.add(t1);
		/* 리스트의 contains와 indexOf는 Test객체의 equals를 바로 호출하는 것이 아니라
		 * Objects.equals(o1,o2)를 호출한 뒤, 내부에서 o1과 o2가 같은 클래스의 객체이면
		 * Test의 equals를 호출 ==> 즉 다른 클래스면 강제형변환해도 올바른 결과 출력X
		 * - 두 객체가 같은 클래스인 경우
		 * contains() -> Objects.equals() -> Test.equals()이용해 true/false리턴
		 * - 두 객체가 다른 클래스인 경우
		 * contains() -> Objects.equals() -> false 리턴
		 * ==> contains랑 indexof는 사용불가, 반복문을 이용해서 비교하는 수 밖에 없음
		 * */
		System.out.println("Test객체와 Integer객체 비교(contains): "
				+ list.contains((Integer)10)); //false
		System.out.println("Test객체와 Integer객체 비교(indexOf): "
				+ list.indexOf((Integer)10)); //-1 (indexOf는 없으면 -1 리턴)
	}

}
@Data
class Test{
	private int num;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {//기본적으로 클래스가 다르면 다르다고 인식됨
			//클래스가 다른 경우 obj가 Integer로 변환이 가능(해서 num랑 비교가능)하면
			if(obj instanceof Integer) {
				return num==(Integer)obj;//비교해서 같은지 아닌지 결과 리턴
			}
			return false;
		}
		Test other = (Test) obj;
		if (num != other.num)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		return result;
	}
	
}
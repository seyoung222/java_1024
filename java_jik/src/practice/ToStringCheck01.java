package practice;

import java.util.ArrayList;

public class ToStringCheck01 {
//list 출력이랑 list[i] 출력 차이, toString의 수정부분 확인용 클래스
	//배열과 달리 리스트는 list[i]라고 부르지 않음....
	//list.add(new객체)로 바로 추가하고 list.get(index)로 출력하는 형태.. 명시하지X
	//양식을 다르게 출력할 수는 없는 듯... 일부만 출력하고 싶으면 getter를 이용해 그것만 받는 수 밖에..
	public static void main(String[] args) {
		ArrayList<Flower> list = new ArrayList<Flower>();
		list.add(new Flower("프리지아",1));
		list.add(new Flower("백합",2));
		list.add(new Flower("코스모스"));
		System.out.println(list);
		for(Flower tmp : list) {
			System.out.println(tmp+ " ");
		}
		Flower[] list2 = new Flower[5];
		list2[0]= new Flower("프리지아",1);
		list2[0].printFlowerInfo();
//		for(int i=0; i<list.size(); i++) {
//			list.printFlowerInfo();
//		}
		
	}

}
class Flower{
	String name;
	int num;
	public void printFlowerInfo() {
		System.out.println("이름: "+name+", 등록번호: "+num);
	}
	public String toString() {
		return num+" - "+name;
	}
	public Flower(String name) {
		this.name = name;
		num=0;
	}
	public Flower(String name, int num) {
		this.name = name;
		this.num=num;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flower other = (Flower) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
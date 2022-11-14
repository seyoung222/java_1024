package day16;

import java.util.HashMap;

import lombok.Data;

public class MapEx02 {

	public static void main(String[] args) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		//value 자리를 Object로 설정하면 다양한 객체가 올 수 있음, 보통은 옵션같은 걸 넣음...
		map.put("user", new User("abc","def"));
		map.put("age", 22);
		map.put("address", "서울시");
		System.out.println(map.get("user"));
		System.out.println(map.get("age"));
		System.out.println(map.get("address"));
		System.out.println(map.get("123")); //맞는 value없으면 null이 리턴됨		
	}

}
@Data
class User{
	private String id, pw;
	public User(String id, String pw) {
		this.id=id;
		this.pw=pw;
	}
}
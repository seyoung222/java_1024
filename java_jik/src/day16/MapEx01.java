package day16;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MapEx01 {
//챕터상으로는 컬렉션이지만 컬렉션에 포함되지는 않음
//순서보장X, 
	public static void main(String[] args) {
		HashMap<String, String> userList = new HashMap<String, String>();
		userList.put("id1", "pw1");//key와 value로 구성, key는 중복불가/value는 중복가능
		userList.put("id2", "pw2");
		userList.put("id3", "pw2");
		userList.put("id3", "pw3");//key값이 같으면 value 덮어쓰기 됨
//		System.out.println(userList);
		// Map의 출력
		//i번지를 출력하는 메소드X, get은 key값을 주면 value값을 가져오는 메소드임
		//값을 두개씩 가지고 있으므로 향상된 for문을 이용한 출력 불가->Iterator이용
		
		//방법1. keySet을 이용
		//keySet()은 map에 있는 key들을 하나의 set에 담아 반환하는 메소드
		Set<String> keySet = (Set<String>)userList.keySet();
		for(String tmp : keySet) {
			System.out.println(tmp+" : "+userList.get(tmp));
		}
		
		//방법2. EntrySet을 이용 (entry: key와 value로 이루어진 하나의 객체)
		//객체들을 set에 하나씩 저장하는거임..
		Set<Entry<String, String>> entrySet = userList.entrySet();
		for(Entry<String, String> tmp : entrySet) {
			System.out.println(tmp.getKey()+" : "+tmp.getValue());
		}
	}

}

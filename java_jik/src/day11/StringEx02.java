package day11;

public class StringEx02 {

	public static void main(String[] args) {
		/* 문자열 str에 search가 몇번 등장하는지 확인하는 코드를 작성하세요*/
		String str = "Hello world! My name is Hong";
		String search="o";
		
//		int index=0;
//		int count=0;
//		while(index!=-1) {
//			index = str.indexOf(search);
//			if(index==-1) {
//				break;
//			}
//			count++;
//			str=str.substring(index+search.length());
//			//찾은 문자열 뒤부터 이어지게 하려면 index에서 문자열의 길이만큼을 더 해줘야함
//		}
//		System.out.println("str에서 search 문자열이 들어간 횟수: "+count);
		
		
		//str에서 search가 있는 위치를 찾음
		//-1이 아니면 부분문자열을 추출하고, count +1증가
		//-1이면 반복문 종료
		
		String tmp = str;
		int count=0;
		while(true) {
			//System.out.println(tmp);
			int index = tmp.indexOf(search);
			if(index==-1) {
				break;
			}
			tmp = tmp.substring(index + search.length());
			count++;
		}
		System.out.println(str+"에는 "+search+"가 "+count+"번 있습니다.");
	}

}

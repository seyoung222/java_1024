package day32;

import java.util.StringTokenizer;

public class StringTokenizerEx01 {

	public static void main(String[] args) {
		
		String str = "abc,def,.phi";
		//split : 구분자 속 첫 글자를 기준으로 분리, 정규표현식 적용됨!
		String [] arr = str.split(",.");
		for(String tmp : arr) {
			System.out.println(tmp);
		}
		System.out.println("-------------");
		// Token : .이나 , 둘 중 하나가 구분자로 작용
		StringTokenizer st = new StringTokenizer(str,".,");
		while(st.hasMoreTokens()) {
			String tmp = st.nextToken();
			System.out.println(tmp);
		}
		System.out.println("-----------");
		st = new StringTokenizer(str, ".,", true);
		System.out.println("개수: "+st.countTokens());
		while(st.hasMoreTokens()) {
			String tmp = st.nextToken();
			System.out.println(tmp);
		}
	}

}

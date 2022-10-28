package day05;

public class ArrayEx05 {

	public static void main(String[] args) {
		/* 5개짜리 배열을 생성하여 2부터 5개의 소수를 배열에 저장하고 출력하세요.
		 * 반복횟수 : num가 2부터 1씩 증가
		 * 규칙성 : 
		 * 1. num가 소수이면 배열 index번지에 num을 저장
		 * 2. index를 1증가
		 * 3. index가 배열의 크기와 같으면 반복문 종료
		 * 반복문 종료 후 : 배열의 값을 출력(반복문 이용)
		 * */
		int arr[] = new int[5];
		int index=0;
		for(int i=2; ; i++) {
			int count=0;
			for(int j=1; j<=i; j++) {
				if(i%j==0) {
					count++;
				}
			}
			if(count==2) {
				arr[index]=i;
				System.out.print(arr[index]+" ");
				index++;
			}
			if(index==arr.length) {
				break;
			}
		}
		
		
		
		
		
		
		
	}
}

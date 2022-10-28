package day05;

public class ForEx01 {

	public static void main(String[] args) {
		
		//;에 의해 for문/if문의 실행문이 의도와 다르게 출력되거나 에러가 나는 경우
		
		int i;
		for(i=1; i<=5; i++); //여기에 ;찍으면 for문은 여기서 끝남.
		{
			System.out.println(i); //출력값 6나옴. for문 뒤의 ; 때문에
		}
		i=5;
		if(i%2==0); //여기도 ;찍으면 if문 여기서 끝남
		{
			System.out.println("짝수"); //출력되면 안되는데 ;때문에 짝수출력됨
		}
		
		/*else { //if문은 ;에서 끝나버렸기 때문에 else 사용시 에러발생
			System.out.println("홀수");
		}*/
		
		//조건문, 반복문은 실행문이 1줄이면 {} 생략 가능
		for(i=1; i<=5; i++) //여기서 실수로 ;붙여서 실수가 많이 남!!!
			System.out.println(i);
		// 한줄이어도 {}를 가급적 넣고, 들여쓰기를 잘 할 것.
		
		
	}
}

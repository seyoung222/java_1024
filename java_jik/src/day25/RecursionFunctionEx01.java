package day25;

public class RecursionFunctionEx01 {
	/* 재귀함수 : 함수의 실행문으로 자신을 다시 호출
	 *  - 잘못 작성하면 스택 오버 플로우가 발생할 수 있음(자신을 무한호출해서 stack메모리가 꽉 참)
	 *	//일반적인 상황에서는 잘 쓸일이 없지만 알고리즘에서 재귀함수를 많이 씀
	 * */
	public static void main(String[] args) {
//		print(10); //스택 오버 플로우  발생
		/* 양수 정수 n!을 구하는 코드를 작성하세요.
		 * n! = 1*2*3 ... *n
		 * n*(n-1)!
		 * n*(n-1)*(n-2)!
		 * */
		System.out.println(factorial(5));
	}
	public static long factorial(int num) {
		if(num<0)
			throw new RuntimeException("예외발생: 음수는 팩토리얼을 구할 수 없습니다.");
		if(num==1 || num==0) //재귀함수는 탈출조건을 같이 적어줌.. 
			return 1;
		return num*factorial(num-1);
	}
	public static void print(int num) {
		System.out.println(num);
		print(num);
	}
}

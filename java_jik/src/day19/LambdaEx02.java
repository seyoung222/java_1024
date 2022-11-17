package day19;

import java.util.function.Consumer;

public class LambdaEx02 {

	public static void main(String[] args) {
		//람다식 구현의 기본 형태
		//람다문은 객체만들때 new를 안씀, a,b의 타입도 생략(인터페이스에 타입명시 돼있으므로)
//		Sum sum = (double a, double b) ->{ 
//			return a+b;
//		};
		
		//실행문이 한줄일 때 중괄호도 생략가능 (void일때만!)
//		Sum sum = (a, b) ->{ 
//			System.out.println(a+b);
//		};
		//리턴타입이 있을때는 중괄호 생략할때 return이라는 글자도 같이 없애야함!
		Sum sum = (a, b) -> a+b ;
		System.out.println(sum.run(1,2));
		Print<Integer> print = num-> System.out.println(num); //매개변수 하나일땐 괄호생략가능
		print.run(10);
		Print<String> print2 = num-> System.out.println(num);
		print2.run("10");
		Consumer<String> print3 = str->System.out.println(str);
		print3.accept("10"); //매개변수 1개, 리턴값없는 건 Consumer<T> 
	}

}
interface Sum{
	double run(double a, double b);
}
interface Print<T>{ //제네릭도 가능: 어차피 기능이 하나기 때문에 다양한 타입을 넣어서 사용할 수 있도록
	void run(T num);  //여러 타입도 가능: Print<T,G> { ~~ run(T num, G num2); }
}
interface Print2{
	void run(int num);
}

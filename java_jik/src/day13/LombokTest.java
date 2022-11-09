package day13;

import lombok.Data;
//lombok은 클래스를 만드는데 조금 더 편하게 해줌
public class LombokTest {

	public static void main(String[] args) {
		TestA a = new TestA();
		a.setNum(10);
		System.out.println(a.getNum());
		System.out.println(a);//toString() 확인
		TestA b = new TestA();
		b.setNum(10);
		System.out.println(a.equals(b)); //equals() 확인
	}

}
@Data //@Getter, @Setter, @ToString @EqualsAndHashCode, 
//@RequiredArgsConstructor: 기본생성자를 추가해줌
//다만 구성이 다른 equals를 만들때 이젠 해시코드를 지우면 안되고 같이 만들어둬야함~~
class TestA{
	private int num;
}
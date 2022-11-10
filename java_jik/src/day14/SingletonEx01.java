package day14;

public class SingletonEx01 {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		//생성자가 private라서 getInstanc 메소드를 이용해서 새 객체를 생성
	}

}
class Singleton{
	private static Singleton obj; //특징. 본인을 static 객체로 가지고 있음
	private Singleton() {} //특징. 생성자가 private
	
	public static Singleton getInstance() {
		obj = new Singleton();
		return obj;
	}
}
package day09;

public class StaticEx01 {

	public static void main(String[] args) {
//		ClassEx01.main(null); //다른 클래스의 이름을 통해 main를 호출해옴(main은 static 붙으므로)
	
		KiaCar k1 = new KiaCar("모닝");
		KiaCar k2 = new KiaCar("레이");
		KiaCar k3 = new KiaCar("K3");
		k1.print();
		k2.print();
		k3.print();
		k1.name = "K5";
		k1.logo = "Kia"; //가능은 하지만 일반적으로 static 붙은 필드는
		KiaCar.logo = "Kia";//이것처럼 클래스명으로 호출
		System.out.println("=========");
		k1.print(); //static 붙은 필드/메소드는 모든 객체가 공유중이라 한번에 전부 바뀜 !!!!!!!!!
		k2.print();
		k3.print();
	}

}

class KiaCar{
	public static String logo = "KIA";
	public String name;
	public KiaCar(String name) {
		this.name = name;
	}
	public void print() {
		System.out.println(logo);
		System.out.println(" : "+name);
		//static이 붙지 않은 객체메소드 안에서는 static필드와 객체필드를 둘다 사용가능
	}
	public static void printLogo() {
		System.out.println(logo);
//		System.out.println(" : "+name); 
		//name은 객체가 만들어져야 사용가능한 필드라서, static메소드 안에서는 사용불가
	}
}
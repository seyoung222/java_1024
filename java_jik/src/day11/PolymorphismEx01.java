package day11;

public class PolymorphismEx01 {

	public static void main(String[] args) {
		connectToComputer(new Mouse());
		connectToComputer(new KeyBoard());
		connectToComputer(new Speaker());
	}
	/* ★★★매개변수의 다형성★★★ => 메소드 오버로딩을 줄여줌
	 * 매개변수로 부모클래스의 객체를 전달하여, 같은 부모를 가진 자식클래스 객체를 
	 * 매개변수로 활용할 수 있게하는 것
	 * */
//어떤 타입의 오브젝트랑 연결해도 아래와 같이 출력되도록 묶어서 한번에 관리 : 매개변수의 다형성
	public static void connectToComputer(UsbProduct obj) {
		System.out.println(obj.type+"와 연결되었습니다.");
	}
	
}
class UsbProduct{
	public String type;
}
class Mouse extends UsbProduct{
	{
		type = "마우스";
	}
}
class KeyBoard extends UsbProduct{
	{
		type = "키보드";
	}
}
class Speaker extends UsbProduct{
	{
		type = "스피커";
	}
}
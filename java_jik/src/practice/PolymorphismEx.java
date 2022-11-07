package practice;

public class PolymorphismEx {

	public static void main(String[] args) {
		SmartPhone [] phones = new SmartPhone[5]; //업캐스팅
		phones[0] = new Samsung(12341234);
		phones[1] = new Apple(56785678);
		
		for(SmartPhone tmp : phones) {
			if(tmp==null) {
				continue;
			}
			if(tmp instanceof Samsung) {
				((Samsung) tmp).usePay();
			} else if(tmp instanceof Apple) {
				((Apple) tmp).synchronize();
			}
		}
		SmartPhone p1 = new SmartPhone(12345678, "무명");
		p1.printLogo(new Samsung(12123434)); //???? 뭔가.. 사용이 이상함
		//SmartPhone 상속받는 케이블들을 만들어서 적용하는게 나은듯
	}

}
class SmartPhone{
	int phoneNum;
	boolean power;
	String company;
	public void print() {
		System.out.println("휴대폰 번호: "+phoneNum);
		System.out.println("회사: "+company);
	}
	public void printLogo(SmartPhone obj) {
		System.out.println("회사: "+obj.company);
	}
	public void powerOn() {
		power = true;
	}
	public void powerOff() {
		power = false;
	}
	public SmartPhone(int phoneNum,String company) {
		this.phoneNum=phoneNum;
		this.company=company;
	}
}
class Camera{
	int pixel;
	public void savePicture() {
		System.out.println("사진이 저장되었습니다.");
	}
	public void deletePicture() {
		System.out.println("사진이 삭제되었습니다.");
	}
}
class MP3{}
class Samsung extends SmartPhone{
	{
		company= "삼성";
	}
	public void usePay() {
		System.out.println("삼성페이를 사용합니다.");
	}
	public Samsung(int phoneNum) {
		super(phoneNum, "삼성");
	}
}
class Apple extends SmartPhone{
	{
		company= "애플";
	}
	public void synchronize() {
		System.out.println("동기화를 시작합니다.");
	}
	public Apple(int phoneNum) {
		super(phoneNum, "애플");
	}
}

package day22;

public class ThreadEx02 {
//쓰레드 호출방법
	public static void main(String[] args) {
//		Thread t = Thread.currentThread();
//		System.out.println("현재 쓰레드명: "+t.getName());
		
		//방법3. Runnable 인터페이스를 구현한 클래스 생성
			//객체를 직접 만드는게 아니라 Thread의 생성자를 이용. ( run()을 가져오기 위함 )
//		Thread t2 = new Thread(new Thread02());
		
		//하지만 메소드 하나 구현을 위해 클래스 생성하기 좀 그럼 -> 람다식 이용
		Thread t2 = new Thread( ()->{
			for(int i=0; i<10000; i++) 
				System.out.print("-");
		});
		t2.start(); 
		for(int i=0; i<10000; i++) {
			System.out.print("|");
		} 

	}

}

class Thread02 implements Runnable{
	@Override
	public void run() {
		for(int i=0; i<10000; i++) {
			System.out.print("-");
		}
	}
}
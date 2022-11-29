package day27;

public class ThreadEx04 {
//쓰레드를 만드는 두가지 방법
	//방법2. Runnalbe을 implements한 클래스를 만들고 이를 매개로 하는 Thread 생성
	public static void main(String[] args) {
		//new Runnable을 Thread의 매갱 넣을 수 있는데,
		//얘는 메소드가 하나 있는 함수적 인터페이스라서 람다식으로 표현 가능
		Thread t = new Thread( ()->{
			for(int i=0; i<10;i++){
				try {
					System.out.println("쓰레드 실행");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		});
		t.start();
		Thread thisThread = Thread.currentThread();
		for(int i=0; i<20;i++){
			try {
				System.out.println(thisThread.getName()+"쓰레드 실행");
				Thread.sleep(400);//sleep을 하면 1000밀리세컨드만큼 멈춤, 예외처리 해야함
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}

}

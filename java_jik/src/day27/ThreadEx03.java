package day27;

public class ThreadEx03 {
//쓰레드를 만드는 두가지 방법
	//방법2. Runnalbe을 implements한 클래스를 만들고 이를 매개로 하는 Thread 생성
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
		//새 클래스를 만들지 않고 바로 구현하는 익명 클래스를 생성
			@Override
			public void run() {
				for(int i=0; i<10;i++){
					try {
						System.out.println("쓰레드 실행");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
			}
			
		};
		Thread t = new Thread(runnable);
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

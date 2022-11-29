package day27;

public class ThreadEx01 {
//쓰레드를 만드는 두가지 방법
	//방법1. Thread를 상속받는 새 클래스를 만듦
	public static void main(String[] args) {
		NewThread t = new NewThread();
		t.start();//start()를 사용하면 Thread속 run() 메소드를 실행 
		//-> run()은 비어있기 때문에 오버라이딩 해줘야 의미있음
		Thread thisThread = Thread.currentThread();
		for(int i=0; i<10;i++){
			try {
				System.out.println(thisThread.getName()+"쓰레드 실행");
				Thread.sleep(1000);//sleep을 하면 1000밀리세컨드만큼 멈춤, 예외처리 해야함
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}

}
//방법 1. 
class NewThread extends Thread{
	@Override
	public void run() {
		for(int i=0; i<10;i++){
			try {
				System.out.println(this.getName()+"쓰레드 실행");
				Thread.sleep(1000);//sleep을 하면 1000밀리세컨드만큼 멈춤, 예외처리 해야함
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
}
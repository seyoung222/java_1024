package day22;

public class ThreadEx01 {
//쓰레드 호출방법
	public static void main(String[] args) {
		
		//방법 1. Thread클래스의 .currentThread() 메소드로 호출
		Thread t = Thread.currentThread();//클래스명.으로 호출되는건 static메소드
		System.out.println("현재 쓰레드명: "+t.getName());//객체명으로 호출된 getName()은 아님
	
		//방법2. 쓰레드를 상속받는 클래스 생성
		Thread01 t1 = new Thread01();
		t1.start(); //run()직접실행하는게 아니라 start() 메소드를 호출하여 동작
		for(int i=0; i<10000; i++) {
			System.out.print("|");
		} 
		//-와 |가 번갈아 나옴. 나오는 양도 계속 달라짐
		//위에서부터 순서대로 하는게 아니라 쓰레드는 왔다갔다하면서 실행. 한시점에 여러개를 동시작업할때 사용
	}

}

class Thread01 extends Thread{
	//꼭 오버라이딩 안해도되지만 안하면 이걸 만드는 의미가 없음 
	// Thread클래스 속 run() 메소드를 통해 동작하는데 기본적으로 비어있음-> 나중에 실행이 안됨
	
	@Override
	public void run() {
		for(int i=0; i<10000; i++) {
			System.out.print("-");
		}
	}
}
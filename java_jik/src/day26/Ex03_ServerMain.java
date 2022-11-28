package day26;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex03_ServerMain {
//스레드 생성
	public static void main(String[] args) {
		/* 클라이언트가 연결을 요청하면 연결을 하고, 연결이 종료되기 전까지
		 * 클라이언트가 전송한 문자열을 계속 출력함
		 * */
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(5001));
			while(true) {
				System.out.println("[연결 대기 중]");
				Socket socket = serverSocket.accept();
//				InetSocketAddress isa=(InetSocketAddress)socket.getRemoteSocketAddress();
//				System.out.println("[연결 수락함]"+isa.getAddress());
				//다중 클라이언트 연결을 위해 스레드를 생성
					//run()메소드 하나만 가지고 있기 때문에 구현부를 람다식으로 작성 가능
				read(socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/* 연결될 때마다 새 스레드 만들어 문자열 읽어오기를 반복하는 메소드 */
	public static void read(Socket socket) {
		Thread t = new Thread( ()->{
			try {
				System.out.println("[연결 완료]"+socket.getRemoteSocketAddress());
				InputStream is = socket.getInputStream();
				while(true) {
					byte[] bytes = new byte[1024];
					int readCount = is.read(bytes);//아무것도 안넘어오면 -1리턴 ->아래서 오류 발생
					if(readCount==-1)
						break;
					String str = new String(bytes,0,readCount,"UTF-8");
					if(str.equals("exit"))
						break;
					System.out.println(str);
				}
				is.close(); 
			}catch(Exception e) {
//				e.printStackTrace();
				System.out.println("[클라이언트가 접속을 종료했습니다.]");
			}
		});
		t.start();
		//이렇게 하면 여러명이 서버에 접속가능하지만 이 경우 느려지는 등의 문제가 생기므로
		//스레드 풀을 이용하여 일정 명수 이상 접속하면 서버접속까지 대기하도록 만들어야함 ->다음 시간에
	}
	
}

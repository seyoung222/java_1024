package day26;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Ex04_ClientMain {
//스레드 생성
	public static void main(String[] args) {
		/* 서버에 연결을 요청하고, 연결되면 exit를 입력하기 전까지 문자열을 서버에 전송함
		 * */
		Socket socket = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(5001));
//			socket.connect(new InetSocketAddress("192.168.10.6",5001));
			System.out.println("[연결 완료]");
			Client c = new Client(socket); //Client객체와 메소드 이용
			c.send();
			c.read();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

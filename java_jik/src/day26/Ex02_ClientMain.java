package day26;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Ex02_ClientMain {

	public static void main(String[] args) {
		/* 서버에 연결을 요청하고, 연결되면 exit를 입력하기 전까지 문자열을 서버에 전송함
		 * */
		Socket socket = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(5001));
//			socket.connect(new InetSocketAddress("192.168.10.6",5001));
			//이렇게하면 남의 컴퓨터 서버가 실행중일때 연결할 수 있음
			System.out.println("[연결 완료]");
			
			OutputStream os = socket.getOutputStream();
			while(true) {
				String str=scan.nextLine();
				if(str.equals("exit"))
					break;
				byte[] bytes = str.getBytes("UTF-8");
				os.write(bytes);
				os.flush();
			}
			System.out.println("[연결 종료]");
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package practice;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ServerPracticeClient {

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(5002));
			System.out.println("[연결 완료]");
			//소켓 객체 생성 후 connect로 연결하면 바로 실행 종료
			
			//문자열 보내기 : outputStream
			OutputStream os = socket.getOutputStream();
			//문자열을 바이트배열로 쪼개서 보낼 것임
			//문자열을 바이트 배열로 쪼개 넣고, 그 바이트 배열을 전송
			String str = scan.nextLine();
			byte[] bytes = str.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			os.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

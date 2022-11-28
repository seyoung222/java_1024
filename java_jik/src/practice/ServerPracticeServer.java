package practice;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPracticeServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			//서버 소켓 객체 생성
			serverSocket = new ServerSocket();
			//포트 번호 지정
			serverSocket.bind(new InetSocketAddress(5002));
			//반복문으로 응답대기
			while(true) {
				System.out.println("[연결 대기 중]");
				//accept()로 수락하면, 통신용 소켓 객체를 하나 생성
				Socket socket = serverSocket.accept();
				//확인용으로 연결된 IP를 출력하기 위해 주소 불러옴
				InetSocketAddress isa=(InetSocketAddress)socket.getRemoteSocketAddress();
				System.out.println("[연결 수락함]"+isa.getAddress());
				//클라이언트의 연결 요청을 수락하고 나면 다시 대기 상태로 돌입
				
				//클라이언트가 보낸 문자열을 수신하여 출력 : InputStream
				InputStream is = socket.getInputStream();
				byte[] bytes = new byte[1024]; //어느 정도의 양씩 받아올 지 결정
				int readCount = is.read(bytes); //수신한 데이터를 bytes 배열에 넣고,
				//배열이 차있는 index의 값을 반환해 readCount에 저장
				//배열속 차있는 데이터만큼 문자열로 만듦
				String str = new String(bytes,0,readCount,"UTF-8");
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
